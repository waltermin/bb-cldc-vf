package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.MissingResourceException;
import net.rim.device.api.i18n.ResourceBundle;
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

   public AutoTextEditField(String label, String initialValue) {
      this(label, initialValue, 1000000, 4503599627370496L);
   }

   public AutoTextEditField(String label, String initialValue, int maxNumChars, long style) {
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

   private synchronized boolean handleClauseSeparator(char character) {
      if (character == ' ' && this._shiftPressed) {
         return true;
      } else if (character == ' ' && this.getCaretPosition() > 0 && this.getAttributedText().charAt(this.getCaretPosition() - 1) == ' ') {
         this.handleAutoPeriod(character);
         return true;
      } else {
         return this.handleAutoText(character) && this.handleAutoQuote(character);
      }
   }

   @Override
   protected boolean insert(char key, int status) {
      if (this.isSelecting()) {
         this.selectionDelete();
      }

      boolean performInsert = true;
      if (_autoTextEngine.isClauseSeparator(key)) {
         performInsert = this.handleClauseSeparator(key);
      } else if (Character.isLowerCase(key) && !this._processingKeyHeldWhileRolling) {
         key = this.handleLowerCaseCharacter(key);
      }

      if (this._bookmarks[2] != -1 && this._bookmarks[2] == this.getCaretPosition() && this._lastAutoCapAction == 2) {
         this.resetBookmark(2);
      }

      if (performInsert) {
         return super.insert(key, status);
      }

      this.fieldChangeNotify(0);
      return false;
   }

   private boolean isAutoCapLocation() {
      if (this.getCursorPosition() == 0) {
         return true;
      }

      int prevNotIgnoredCharOffset = this.previousIndexOf(AUTOCAP_DELIMITERS, false);
      char prevNotIgnoredChar = 0;
      if (prevNotIgnoredCharOffset >= this.getLabelLength()) {
         prevNotIgnoredChar = this.getAttributedText().charAt(prevNotIgnoredCharOffset);
      }

      switch (prevNotIgnoredChar) {
         case '\n':
         case '\u2029':
            return true;
         default:
            if (prevNotIgnoredCharOffset < this.getCaretPosition() - 1 && _autoTextEngine.isSentenceTerminator(prevNotIgnoredChar)) {
               return true;
            } else {
               int endListIndex = this.previousIndexOf(AUTOLIST_SPACE, false);
               if (endListIndex > this.getLabelLength()) {
                  boolean isDelimiter = AUTOLIST_DELIMITERS.indexOf(this.getAttributedText().charAt(endListIndex)) >= 0;
                  if (isDelimiter) {
                     int index = this.previousIndexOf(AUTOLIST_CONTENT, false);
                     char startList = index >= 0 ? this.getAttributedText().charAt(index) : '\u0000';
                     switch (startList) {
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

   private boolean isPreviousCharacterAtBookmark(int bookmarkIndex) {
      return this._bookmarks[bookmarkIndex] != -1 && this.getCaretPosition() - 1 == this._bookmarks[bookmarkIndex];
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected int replace(
      int aStart,
      int aEnd,
      AttributedString$Iterator aIterator,
      long aIteratorMask,
      long aIteratorXMask,
      int aCommittedLen,
      int aPosInsideComposedText,
      boolean aMoveCursor,
      int aContext
   ) {
      if (aContext == 0 && aCommittedLen == 1 && aIterator.length() == 1 && this.getComposedTextEnd() == this.getComposedTextStart() && this._bookmarks != null
         )
       {
         this.keyChar(aIterator.text().charAt(aIterator.pos()), 0, 0);
         return 0;
      } else {
         return super.replace(aStart, aEnd, aIterator, aIteratorMask, aIteratorXMask, aCommittedLen, aPosInsideComposedText, aMoveCursor, aContext);
      }
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      this._keyRepeatProcessed = false;
      return super.keyDown(keycode, time);
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      boolean result = false;
      char charkey = Keypad.map(keycode);
      if (this.isEnteringRollerCharacter() || !this.isEditable()) {
         result = super.keyRepeat(keycode, time);
      } else if (charkey == '\n' || charkey == '\b') {
         result = super.keyRepeat(keycode, time);
      } else if (Character.isLowerCase(charkey) || Character.isUpperCase(charkey)) {
         if (SymbolScreen.contains(this.getLastKeyPressed())) {
            this.insert((char)this.getLastKeyPressed(), 0);
            result = true;
         } else if (!this._keyRepeatProcessed) {
            if (Character.isLowerCase(charkey)) {
               charkey = Character.toUpperCase(charkey);
            }

            this.backspace();
            this.insert(charkey, 0);
         }

         result = true;
      }

      this._keyRepeatProcessed = true;
      return result;
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   protected int moveFocus(int amount, int status, int time) {
      int ret = 0;
      boolean var7 = false /* VF: Semaphore variable */;

      try {
         var7 = true;
         if ((status & 8) != 0) {
            this._processingKeyHeldWhileRolling = true;
         }

         ret = super.moveFocus(amount, status, time);
         var7 = false;
      } finally {
         if (var7) {
            this._processingKeyHeldWhileRolling = false;
         }
      }

      this._processingKeyHeldWhileRolling = false;
      return ret;
   }

   @Override
   protected void setText(String text, int context) {
      this.resetBookmark(0);
      this.resetBookmark(1);
      this.resetBookmark(2);
      super.setText(text, context);
   }

   private void handleAutoPeriod(char character) {
      if (this.isAutoPeriodOn() && InputContext.getInstance().isAutoPeriodOn()) {
         int prevNonSpaceOffset = this.getPrevNonSpaceCharOffset();
         char prevNonSpaceChar = prevNonSpaceOffset == -1 ? '\u0000' : this.getAttributedText().charAt(prevNonSpaceOffset);
         int offset = prevNonSpaceOffset == -1 ? this.getLabelLength() : prevNonSpaceOffset + 1;
         if (this._bookmarks[1] == -1 || this._lastAutoPeriodAction != 2 || this._bookmarks[1] != offset) {
            if (!_autoTextEngine.isNoAutoPeriodCharacter(prevNonSpaceChar)) {
               FieldChangeListener oldListener = this.getChangeListener();
               this.setChangeListener(null);
               int periodOffset = offset;
               this.getAttributedText().delete(periodOffset, periodOffset + 1);
               this.getAttributedText().insert(periodOffset, this.getPeriodSymbol());
               this._bookmarks[1] = periodOffset;
               this._lastAutoPeriodAction = 1;
               this.setChangeListener(oldListener);
            }
         }
      }
   }

   private char getPeriodSymbol() {
      SLControlObject cObj = (SLControlObject)this.getInputContext().getInputMethodControlObject();
      return cObj.getPeriodSymbol();
   }

   private boolean handleAutoQuote(char character) {
      this.checkLocale();
      if (!this.isAutoQuoteOn()) {
         return true;
      }

      if (character != '"') {
         return true;
      }

      boolean opening = false;
      char prevChar = this.getCaretPosition() > 0 ? this.getAttributedText().charAt(this.getCaretPosition() - 1) : '\u0000';
      switch (prevChar) {
         case '\u0000':
         case '\n':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case ' ':
         case '\u200b':
         case '\u200c':
         case '\u200d':
         case '\u2028':
         case '\u2029':
         case '　':
            opening = true;
         default:
            String replacement = opening ? this._autoQuoteOpen : this._autoQuoteClosed;
            this.replace("", replacement);
            return false;
      }
   }

   private boolean handleAutoText(char character) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String checkEntry(String original, int macroIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String replaceMacros(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean isAllUpperCase(String s) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String adjustCase(String replacement, String original, int caseType) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char handleLowerCaseCharacter(char character) {
      if (!this.isAutoCapsOn()) {
         return character;
      } else if (this._bookmarks[2] != -1
         && this._bookmarks[2] == this.getCaretPosition()
         && this._lastAutoCapAction == 2
         && character == this._lastAutoCapChar) {
         return character;
      } else if (this.isAutoCapLocation()) {
         this._lastAutoCapChar = character;
         this._pendingAutoCapBookmark = this.getCaretPosition();
         this._pendingAutoCapAction = 1;
         return Character.toUpperCase(character);
      } else {
         return character;
      }
   }

   private String replace(String replaceString, String replacementString) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void update(int delta) {
      super.update(delta);
      if (delta > 0) {
         this.updateBookmarks(this.getCaretPosition() - delta, this.getCaretPosition() - 1, false);
      } else {
         if (delta < 0) {
            this.updateBookmarks(this.getCaretPosition(), this.getCaretPosition() - delta - 1, true);
         }
      }
   }

   private void updateBookmark(int bookmarkIndex, int changeStartOffset, int changeEndOffset, boolean delete) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void updateBookmarks(int changeStartOffset, int changeEndOffset, boolean delete) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void resetBookmark(int bookmarkIndex) {
      this._bookmarks[bookmarkIndex] = -1;
      switch (bookmarkIndex) {
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

   private static boolean getBooleanResource(int id) {
      String value = _inputFamily.getString(id);
      return unfoldBooleanResource(value);
   }

   private static boolean unfoldBooleanResource(String value) {
      throw new RuntimeException("cod2jar: string-special");
   }

   void checkLocale() {
      int currentCode = Locale.getDefault().getCode();
      if (this._cachedLocaleCode != currentCode) {
         this._cachedLocaleCode = currentCode;
         ResourceBundleFamily family = ResourceBundle.getBundle(8562590855522002223L, "net.rim.device.internal.resource.Input");
         this._autoQuoteOpen = family.getString(23);
         this._autoQuoteClosed = family.getString(24);
      }
   }

   private static long validateStyle(long style) {
      if ((style & 196608) == 0) {
         boolean value = getBooleanResource(14);
         if (value) {
            style |= 65536;
         } else {
            style |= 131072;
         }
      }

      if ((style & 786432) == 0) {
         boolean value = getBooleanResource(13);
         if (value) {
            style |= 262144;
         } else {
            style |= 524288;
         }
      }

      if ((style & 1048576) == 0) {
         ResourceBundle bundle = ResourceBundle.getBundle(8562590855522002223L, "net.rim.device.internal.resource.Input")
            .getBundle(Locale.getDefaultInputForSystem());
         boolean value = false;
         if (bundle != null) {
            try {
               String textValue = bundle.getString(15);
               value = unfoldBooleanResource(textValue);
            } catch (MissingResourceException var5) {
            }
         }

         if (value) {
            style |= 1048576;
         } else {
            style |= 2097152;
         }
      }

      if ((style & 12582912) == 0) {
         style |= 8388608;
      }

      return style;
   }

   @Override
   public void selectionDelete() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int insert(String text, int context, boolean stripInvalid, boolean validateText) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent event) {
      if (event.getID() != 1103) {
         AttributedString insertText = event.getText();
         int inserted_len = insertText.length();
         int committed_count = event.getCommittedCharacterCount();
         if (inserted_len == 1 && committed_count == 0 && this.getComposedTextStart() == this.getComposedTextEnd()) {
            char key = insertText.getText().charAt(0);
            if (_autoTextEngine.isClauseSeparator(key)) {
               this.handleClauseSeparator(key);
            }
         }
      }

      return super.inputMethodTextChanged(event);
   }

   private int getPrevNonSpaceCharOffset() {
      int pos = this.getCaretPosition();
      AttributedString text = this.getAttributedText();
      int labelLen = this.getLabelLength();

      for (int i = pos - 1; i >= labelLen; i--) {
         if (text.charAt(i) != ' ') {
            return i;
         }
      }

      return -1;
   }

   private int previousIndexOf(String s, boolean match) {
      int pos = this.getCaretPosition();
      AttributedString text = this.getAttributedText();
      int labelLen = this.getLabelLength();

      for (int i = pos - 1; i >= labelLen; i--) {
         boolean actualMatch = s.indexOf(text.charAt(i)) != -1;
         if (match == actualMatch) {
            return i;
         }
      }

      return -1;
   }
}
