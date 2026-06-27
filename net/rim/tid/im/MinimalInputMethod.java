package net.rim.tid.im;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.KeyEvent;
import net.rim.tid.awt.im.repository.CustomDictionary;
import net.rim.tid.awt.im.repository.CustomWordsRepository;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputMethodContext;
import net.rim.tid.awt.im.spi.InputModeChangeListener;
import net.rim.tid.im.layout.SLKeyLayout;
import net.rim.tid.itie.LinguisticData;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.TextHitInfo;
import net.rim.tid.util.Utils;

public class MinimalInputMethod implements InputMethod {
   InputMethodContext _iContext;
   SLKeyLayout _lnkLayout;
   int _rollerCharacterIndex;
   AttributedString _buffer;
   TextFilter _filter;
   private Locale _iLocale;
   private SLControlObject _iControlObject;
   private boolean iKeyRepeateProcessed;
   private Locale[] _iAvailableLocale;
   private int lastKeyPressed;
   private boolean iNotFromKeypad;
   private int[] _iControl;
   private int _priorInserPosition;
   private int _keyboardID;
   private boolean _keyUpProcessOnly;
   private byte[] _IMProp;
   public static final char KEYPAD_TYPE_DELIMITER;

   protected String getKeyMapLibName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean setFilter(TextFilter var1) {
      this._filter = var1;
      return true;
   }

   public char getPeriodSymbol() {
      return '.';
   }

   protected boolean isEnteringRollerCharacter() {
      return this._rollerCharacterIndex != -1;
   }

   protected synchronized void processRollEvent(KeyEvent var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void sendComposedText(StringBuffer var1) {
      this._buffer.set(var1);
      this._iContext.dispatchInputMethodEvent(1100, this._buffer, 0, var1.length(), 0, TextHitInfo.leading(0), null);
   }

   public boolean setKeyLayoutLocale(Locale var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   void setLayout(SLKeyLayout var1) {
      if (var1 != null) {
         this._lnkLayout = var1;
      }
   }

   void sendComposedText(AttributedString var1) {
      this._iContext.dispatchInputMethodEvent(1100, var1, 0, var1.length(), 0, TextHitInfo.leading(0), null);
   }

   public SLKeyLayout getKeyLayout() {
      return this._lnkLayout;
   }

   protected synchronized void processKeyRepeate(KeyEvent var1) {
      int var2 = var1.getKeyCode();
      StringBuffer var3 = this._lnkLayout.getKeyChars(var2, var1.getModifiers(), false);
      char var4 = var3.charAt(0);
      var1.setKeyChar(var4);
      if (!this.isControl(var4)) {
         if (this.iNotFromKeypad) {
            int var9 = this._iContext.getComposedTextStart() - this._iContext.getLabelLength();
            if (var9 >= 1) {
               AttributedString var10 = this._iContext.getAttributedText();
               if (var10 != null && var10.length() != 0) {
                  char var11 = var10.getText().charAt(var9 - 1);
                  var1.setKeyChar(var11);
                  this._buffer.delete(this._buffer.length());
                  this._buffer.insert(var11);
                  this.sendComposedText(this._buffer);
                  var1.consume();
               }
            }
         } else {
            if (!this.iKeyRepeateProcessed && !this.isEnteringRollerCharacter()) {
               if (CharacterUtilities.isUpperCase(var4) || CharacterUtilities.isLowerCase(var4)) {
                  this.iKeyRepeateProcessed = true;
                  int var5 = this._iContext.getComposedTextStart();
                  int var6 = var5 - this._iContext.getLabelLength();

                  for (int var7 = 0; var7 < var3.length(); var7++) {
                     char var8 = Utils.toUpperCase(var3.charAt(var7));
                     if (this._filter != null && !this._filter.validate(var8, null, var6 - 1)) {
                        var3.delete(var7, var7 + 1);
                        var7--;
                     } else {
                        var3.setCharAt(var7, var8);
                     }
                  }

                  if (var3.length() == 0) {
                     var1.consume();
                     return;
                  }

                  var1.setKeyChar(var3.charAt(0));
                  if (var3.length() > 0 && this._iContext.getComposedTextStart() == this._priorInserPosition + 1 && var6 > 0) {
                     this._iContext.setComposedText(var5 - 1, var5);
                  }

                  this.sendComposedText(var3);
                  var1.consume();
                  return;
               }

               if (CharacterUtilities.isLetter(var4)) {
                  this.iKeyRepeateProcessed = true;
                  var1.consume();
                  return;
               }
            } else {
               var1.consume();
            }
         }
      }
   }

   protected boolean isControl(char var1) {
      return Arrays.binarySearch(this._iControl, var1 & 65535) >= 0;
   }

   @Override
   public void reconvert() {
   }

   @Override
   public void dispatchEvent(Event var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean isCompositionEnabled() {
      return true;
   }

   @Override
   public int setTextInputStyle(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void setCompositionEnabled(boolean var1) {
   }

   @Override
   public Locale getLocale() {
      return this._iLocale;
   }

   @Override
   public boolean setLocale(Locale var1, int var2) {
      if (this._iLocale != null && this._iLocale.equals(var1) && this._iLocale.getKeyboardID() == var1.getKeyboardID()) {
         return true;
      }

      for (int var3 = 0; var3 < this._iAvailableLocale.length; var3++) {
         if (this._iAvailableLocale[var3].equals(var1)) {
            if (this.setKeyLayoutLocale(var1)) {
               this._iLocale = var1;
               Keypad.setKeypadLocale(var1);
               return true;
            }

            return false;
         }
      }

      return false;
   }

   @Override
   public boolean setLocale(Locale var1) {
      return this.setLocale(var1, 0);
   }

   @Override
   public void setInputMethodContext(InputMethodContext var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void notifyClientWindowChange(XYRect var1) {
   }

   @Override
   public void activate() {
   }

   @Override
   public void deactivate(boolean var1) {
   }

   @Override
   public void hideWindows() {
   }

   @Override
   public void removeNotify() {
   }

   @Override
   public void endComposition() {
   }

   @Override
   public void reset(int var1) {
   }

   @Override
   public void dispose() {
   }

   @Override
   public Object getControlObject() {
      if (this._iControlObject == null) {
         this._iControlObject = new SLControlObject(this);
      }

      return this._iControlObject;
   }

   @Override
   public int loadLinguisticData(LinguisticData var1) {
      return 1;
   }

   @Override
   public int unloadLinguisticData(int var1) {
      return 1;
   }

   @Override
   public void setIMProperties(byte var1, byte[] var2) {
      switch (var1) {
         case 1:
            this._IMProp = var2;
      }
   }

   @Override
   public byte[] getIMProperties(byte var1) {
      switch (var1) {
         case 1:
            return this._IMProp;
         default:
            return null;
      }
   }

   @Override
   public boolean isCorrectWord(StringBufferGap var1, int var2, int var3) {
      return true;
   }

   @Override
   public int actionPerformed(Object var1, int var2, Object var3) {
      return 0;
   }

   @Override
   public int setListener(InputModeChangeListener var1) {
      return 3;
   }

   @Override
   public InputModeChangeListener getListener() {
      return null;
   }

   @Override
   public CustomWordsRepository getRepository(int var1) {
      return null;
   }

   @Override
   public CustomDictionary getCustomDictionary(int var1) {
      return null;
   }

   private boolean isKeyUpProcessOnly(KeyEvent var1) {
      return this._keyUpProcessOnly && var1.isInputEvent();
   }

   public MinimalInputMethod(Locale[] var1) {
   }

   private String getKeyboardType(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
