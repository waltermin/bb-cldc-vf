package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.autotext.AutoText;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

public class AutoTextEditField extends EditField {
   private boolean _keyRepeatProcessed;
   private boolean _processingKeyHeldWhileRolling;
   private int[] _bookmarks;
   private short _lastAutoTextAction;
   private short _lastAutoPeriodAction;
   private short _lastAutoCapAction;
   private String _replacedString;
   private String _replacementString;
   private char _lastAutoCapChar;
   private int _pendingAutoCapBookmark;
   private short _pendingAutoCapAction;
   private StringBuffer _deletedText;
   private int _leadingBackspaceCount;
   private int _deleteCount;
   private boolean _shiftPressed;
   private String _autoQuoteOpen;
   private String _autoQuoteClosed;
   private int _cachedLocaleCode;
   private static final long AUTOREPLACE_MASK;
   public static final long AUTOREPLACE;
   public static final long AUTOREPLACE_OFF;
   private static final long AUTOCAP_MASK;
   public static final long AUTOCAP;
   public static final long AUTOCAP_OFF;
   private static final long AUTOPERIOD_MASK;
   private static final long AUTOPERIOD;
   public static final long AUTOPERIOD_OFF;
   private static final long AUTOQUOTE_MASK;
   private static final long AUTOQUOTE;
   public static final long AUTOQUOTE_OFF;
   private static final short BOOKMARK_AUTOTEXT;
   private static final short BOOKMARK_AUTOPERIOD;
   private static final short BOOKMARK_AUTOCAP;
   private static final short ACTION_NONE;
   private static final short ACTION_REPLACE;
   private static final short ACTION_UNDO;
   private static AutoText _autoTextEngine;
   private static String CLAUSE_SEPARATORS;
   private static String AUTOCAP_DELIMITERS;
   private static String AUTOLIST_CONTENT;
   private static String AUTOLIST_DELIMITERS;
   private static String AUTOLIST_SPACE;
   private static ResourceBundleFamily _inputFamily;

   public AutoTextEditField() {
      this(null, null, 1000000, 4503599627370496L);
   }

   public AutoTextEditField(String var1, String var2) {
      this(var1, var2, 1000000, 4503599627370496L);
   }

   public AutoTextEditField(String var1, String var2, int var3, long var4) {
   }

   @Override
   protected boolean backspace() {
      if (this.isSelecting()) {
         return super.backspace();
      }

      if (this.isPreviousCharacterAtBookmark(0)) {
         if (this._lastAutoTextAction != 2 && this.undoAutoText()) {
            return true;
         }
      } else if (this.isPreviousCharacterAtBookmark(1)) {
         if (this._lastAutoPeriodAction != 2) {
            this._bookmarks[1] = this.getCaretPosition() - 1;
            this._lastAutoPeriodAction = 2;
         } else {
            this.resetBookmark(1);
         }
      } else if (this.isPreviousCharacterAtBookmark(2) && this._lastAutoCapAction != 2) {
         this._pendingAutoCapBookmark = this.getCaretPosition() - 1;
         this._pendingAutoCapAction = 2;
      }

      return super.backspace();
   }

   private synchronized boolean handleClauseSeparator(char var1) {
      if (var1 == ' ' && this._shiftPressed) {
         return true;
      } else if (var1 == ' ' && this.getCaretPosition() > 0 && this.getAttributedText().charAt(this.getCaretPosition() - 1) == ' ') {
         this.handleAutoPeriod(var1);
         return true;
      } else {
         return this.handleAutoText(var1) && this.handleAutoQuote(var1);
      }
   }

   @Override
   protected boolean insert(char var1, int var2) {
      if (this.isSelecting()) {
         this.selectionDelete();
      }

      boolean var3 = true;
      if (_autoTextEngine.isClauseSeparator(var1)) {
         var3 = this.handleClauseSeparator(var1);
      } else if (Character.isLowerCase(var1) && !this._processingKeyHeldWhileRolling) {
         var1 = this.handleLowerCaseCharacter(var1);
      }

      if (this._bookmarks[2] != -1 && this._bookmarks[2] == this.getCaretPosition() && this._lastAutoCapAction == 2) {
         this.resetBookmark(2);
      }

      if (var3) {
         return super.insert(var1, var2);
      }

      this.fieldChangeNotify(0);
      return false;
   }

   private boolean isAutoCapLocation() {
      if (this.getCursorPosition() == 0) {
         return true;
      }

      int var1 = this.previousIndexOf(AUTOCAP_DELIMITERS, false);
      char var2 = 0;
      if (var1 >= this.getLabelLength()) {
         var2 = this.getAttributedText().charAt(var1);
      }

      switch (var2) {
         case '\n':
         case '\u2029':
            return true;
         default:
            if (var1 < this.getCaretPosition() - 1 && _autoTextEngine.isSentenceTerminator(var2)) {
               return true;
            } else {
               int var3 = this.previousIndexOf(AUTOLIST_SPACE, false);
               if (var3 > this.getLabelLength()) {
                  boolean var4 = AUTOLIST_DELIMITERS.indexOf(this.getAttributedText().charAt(var3)) >= 0;
                  if (var4) {
                     int var5 = this.previousIndexOf(AUTOLIST_CONTENT, false);
                     char var6 = var5 >= 0 ? this.getAttributedText().charAt(var5) : '\u0000';
                     switch (var6) {
                        case '\u0000':
                        case '\n':
                        case '\u2029':
                           return true;
                     }
                  }
               }

               return false;
            }
      }
   }

   private boolean isAutoCapsOn() {
      return !this.isStyle(524288);
   }

   private boolean isAutoPeriodOn() {
      return !this.isStyle(2097152);
   }

   private boolean isAutoQuoteOn() {
      return false;
   }

   private boolean isAutoTextOn() {
      return !this.isStyle(131072);
   }

   private boolean isPreviousCharacterAtBookmark(int var1) {
      return this._bookmarks[var1] != -1 && this.getCaretPosition() - 1 == this._bookmarks[var1];
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected int replace(int var1, int var2, AttributedString$Iterator var3, long var4, long var6, int var8, int var9, boolean var10, int var11) {
      if (var11 == 0 && var8 == 1 && var3.length() == 1 && this.getComposedTextEnd() == this.getComposedTextStart() && this._bookmarks != null) {
         this.keyChar(var3.text().charAt(var3.pos()), 0, 0);
         return 0;
      } else {
         return super.replace(var1, var2, var3, var4, var6, var8, var9, var10, var11);
      }
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      this._keyRepeatProcessed = false;
      return super.keyDown(var1, var2);
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      boolean var3 = false;
      char var4 = Keypad.map(var1);
      if (this.isEnteringRollerCharacter() || !this.isEditable()) {
         var3 = super.keyRepeat(var1, var2);
      } else if (var4 == '\n' || var4 == '\b') {
         var3 = super.keyRepeat(var1, var2);
      } else if (Character.isLowerCase(var4) || Character.isUpperCase(var4)) {
         if (SymbolScreen.contains(this.getLastKeyPressed())) {
            this.insert((char)this.getLastKeyPressed(), 0);
            var3 = true;
         } else if (!this._keyRepeatProcessed) {
            if (Character.isLowerCase(var4)) {
               var4 = Character.toUpperCase(var4);
            }

            this.backspace();
            this.insert(var4, 0);
         }

         var3 = true;
      }

      this._keyRepeatProcessed = true;
      return var3;
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected void setText(String var1, int var2) {
      this.resetBookmark(0);
      this.resetBookmark(1);
      this.resetBookmark(2);
      super.setText(var1, var2);
   }

   private void handleAutoPeriod(char var1) {
      if (this.isAutoPeriodOn() && InputContext.getInstance().isAutoPeriodOn()) {
         int var2 = this.getPrevNonSpaceCharOffset();
         char var3 = var2 == -1 ? '\u0000' : this.getAttributedText().charAt(var2);
         int var4 = var2 == -1 ? this.getLabelLength() : var2 + 1;
         if (this._bookmarks[1] == -1 || this._lastAutoPeriodAction != 2 || this._bookmarks[1] != var4) {
            if (!_autoTextEngine.isNoAutoPeriodCharacter(var3)) {
               FieldChangeListener var5 = this.getChangeListener();
               this.setChangeListener(null);
               int var6 = var4;
               this.getAttributedText().delete(var6, var6 + 1);
               this.getAttributedText().insert(var6, this.getPeriodSymbol());
               this._bookmarks[1] = var6;
               this._lastAutoPeriodAction = 1;
               this.setChangeListener(var5);
            }
         }
      }
   }

   private char getPeriodSymbol() {
      Object var1 = this.getInputContext().getInputMethodControlObject();
      return ((SLControlObject)var1).getPeriodSymbol();
   }

   private boolean handleAutoQuote(char var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean handleAutoText(char var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String checkEntry(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String replaceMacros(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static boolean isAllUpperCase(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String adjustCase(String var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char handleLowerCaseCharacter(char var1) {
      if (!this.isAutoCapsOn()) {
         return var1;
      } else if (this._bookmarks[2] != -1 && this._bookmarks[2] == this.getCaretPosition() && this._lastAutoCapAction == 2 && var1 == this._lastAutoCapChar) {
         return var1;
      } else if (this.isAutoCapLocation()) {
         this._lastAutoCapChar = var1;
         this._pendingAutoCapBookmark = this.getCaretPosition();
         this._pendingAutoCapAction = 1;
         return Character.toUpperCase(var1);
      } else {
         return var1;
      }
   }

   private String replace(String var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void update(int var1) {
      super.update(var1);
      if (var1 > 0) {
         this.updateBookmarks(this.getCaretPosition() - var1, this.getCaretPosition() - 1, false);
      } else {
         if (var1 < 0) {
            this.updateBookmarks(this.getCaretPosition(), this.getCaretPosition() - var1 - 1, true);
         }
      }
   }

   private void updateBookmark(int var1, int var2, int var3, boolean var4) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void updateBookmarks(int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void resetBookmark(int var1) {
      this._bookmarks[var1] = -1;
      switch (var1) {
         case 0:
         default:
            this._lastAutoTextAction = 0;
            return;
         case 1:
            this._lastAutoPeriodAction = 0;
            return;
         case 2:
            this._lastAutoCapAction = 0;
         case -1:
      }
   }

   private boolean undoAutoText() {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean getBooleanResource(int var0) {
      String var1 = _inputFamily.getString(var0);
      return unfoldBooleanResource(var1);
   }

   private static boolean unfoldBooleanResource(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   void checkLocale() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static long validateStyle(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void selectionDelete() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int insert(String var1, int var2, boolean var3, boolean var4) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent var1) {
      if (var1.getID() != 1103) {
         AttributedString var2 = var1.getText();
         int var3 = var2.length();
         int var4 = var1.getCommittedCharacterCount();
         if (var3 == 1 && var4 == 0 && this.getComposedTextStart() == this.getComposedTextEnd()) {
            char var5 = var2.getText().charAt(0);
            if (_autoTextEngine.isClauseSeparator(var5)) {
               this.handleClauseSeparator(var5);
            }
         }
      }

      return super.inputMethodTextChanged(var1);
   }

   private int getPrevNonSpaceCharOffset() {
      int var1 = this.getCaretPosition();
      AttributedString var2 = this.getAttributedText();
      int var3 = this.getLabelLength();

      for (int var4 = var1 - 1; var4 >= var3; var4--) {
         if (var2.charAt(var4) != ' ') {
            return var4;
         }
      }

      return -1;
   }

   private int previousIndexOf(String var1, boolean var2) {
      int var3 = this.getCaretPosition();
      AttributedString var4 = this.getAttributedText();
      int var5 = this.getLabelLength();

      for (int var6 = var3 - 1; var6 >= var5; var6--) {
         boolean var7 = var1.indexOf(var4.charAt(var6)) != -1;
         if (var2 == var7) {
            return var6;
         }
      }

      return -1;
   }
}
