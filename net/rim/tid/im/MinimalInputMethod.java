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

   public boolean setFilter(TextFilter filter) {
      this._filter = filter;
      return true;
   }

   public char getPeriodSymbol() {
      return '.';
   }

   protected boolean isEnteringRollerCharacter() {
      return this._rollerCharacterIndex != -1;
   }

   protected synchronized void processRollEvent(KeyEvent evt) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void sendComposedText(StringBuffer text) {
      this._buffer.set(text);
      this._iContext.dispatchInputMethodEvent(1100, this._buffer, 0, text.length(), 0, TextHitInfo.leading(0), null);
   }

   public boolean setKeyLayoutLocale(Locale aLocale) {
      throw new RuntimeException("cod2jar: ldc");
   }

   void setLayout(SLKeyLayout aLayout) {
      if (aLayout != null) {
         this._lnkLayout = aLayout;
      }
   }

   void sendComposedText(AttributedString text) {
      this._iContext.dispatchInputMethodEvent(1100, text, 0, text.length(), 0, TextHitInfo.leading(0), null);
   }

   public SLKeyLayout getKeyLayout() {
      return this._lnkLayout;
   }

   protected synchronized void processKeyRepeate(KeyEvent evt) {
      int code = evt.getKeyCode();
      StringBuffer keyChars = this._lnkLayout.getKeyChars(code, evt.getModifiers(), false);
      char keyChar = keyChars.charAt(0);
      evt.setKeyChar(keyChar);
      if (!this.isControl(keyChar)) {
         if (this.iNotFromKeypad) {
            int position = this._iContext.getComposedTextStart() - this._iContext.getLabelLength();
            if (position >= 1) {
               AttributedString res = this._iContext.getAttributedText();
               if (res != null && res.length() != 0) {
                  char ch = res.getText().charAt(position - 1);
                  evt.setKeyChar(ch);
                  this._buffer.delete(this._buffer.length());
                  this._buffer.insert(ch);
                  this.sendComposedText(this._buffer);
                  evt.consume();
               }
            }
         } else {
            if (!this.iKeyRepeateProcessed && !this.isEnteringRollerCharacter()) {
               if (CharacterUtilities.isUpperCase(keyChar) || CharacterUtilities.isLowerCase(keyChar)) {
                  this.iKeyRepeateProcessed = true;
                  int startPos = this._iContext.getComposedTextStart();
                  int position = startPos - this._iContext.getLabelLength();

                  for (int i = 0; i < keyChars.length(); i++) {
                     char upCasedChar = Utils.toUpperCase(keyChars.charAt(i));
                     if (this._filter != null && !this._filter.validate(upCasedChar, null, position - 1)) {
                        keyChars.delete(i, i + 1);
                        i--;
                     } else {
                        keyChars.setCharAt(i, upCasedChar);
                     }
                  }

                  if (keyChars.length() == 0) {
                     evt.consume();
                     return;
                  }

                  evt.setKeyChar(keyChars.charAt(0));
                  if (keyChars.length() > 0 && this._iContext.getComposedTextStart() == this._priorInserPosition + 1 && position > 0) {
                     this._iContext.setComposedText(startPos - 1, startPos);
                  }

                  this.sendComposedText(keyChars);
                  evt.consume();
                  return;
               }

               if (CharacterUtilities.isLetter(keyChar)) {
                  this.iKeyRepeateProcessed = true;
                  evt.consume();
                  return;
               }
            } else {
               evt.consume();
            }
         }
      }
   }

   protected boolean isControl(char ch) {
      return Arrays.binarySearch(this._iControl, ch & 65535) >= 0;
   }

   @Override
   public void reconvert() {
   }

   @Override
   public void dispatchEvent(Event event) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean isCompositionEnabled() {
      return true;
   }

   @Override
   public int setTextInputStyle(int style) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void setCompositionEnabled(boolean enable) {
   }

   @Override
   public Locale getLocale() {
      return this._iLocale;
   }

   @Override
   public boolean setLocale(Locale aLocale, int state) {
      if (this._iLocale != null && this._iLocale.equals(aLocale) && this._iLocale.getKeyboardID() == aLocale.getKeyboardID()) {
         return true;
      }

      for (int i = 0; i < this._iAvailableLocale.length; i++) {
         if (this._iAvailableLocale[i].equals(aLocale)) {
            if (this.setKeyLayoutLocale(aLocale)) {
               this._iLocale = aLocale;
               Keypad.setKeypadLocale(aLocale);
               return true;
            }

            return false;
         }
      }

      return false;
   }

   @Override
   public boolean setLocale(Locale aLocale) {
      return this.setLocale(aLocale, 0);
   }

   @Override
   public void setInputMethodContext(InputMethodContext context) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void notifyClientWindowChange(XYRect bounds) {
   }

   @Override
   public void activate() {
   }

   @Override
   public void deactivate(boolean isTemporary) {
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
   public void reset(int type) {
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
   public int loadLinguisticData(LinguisticData aData) {
      return 1;
   }

   @Override
   public int unloadLinguisticData(int id) {
      return 1;
   }

   @Override
   public void setIMProperties(byte propID, byte[] IMProperties) {
      switch (propID) {
         case 1:
            this._IMProp = IMProperties;
      }
   }

   @Override
   public byte[] getIMProperties(byte propID) {
      switch (propID) {
         case 1:
            return this._IMProp;
         default:
            return null;
      }
   }

   @Override
   public boolean isCorrectWord(StringBufferGap sbg, int startIndex, int length) {
      return true;
   }

   @Override
   public int actionPerformed(Object src, int action, Object parameter) {
      return 0;
   }

   @Override
   public int setListener(InputModeChangeListener listener) {
      return 3;
   }

   @Override
   public InputModeChangeListener getListener() {
      return null;
   }

   @Override
   public CustomWordsRepository getRepository(int type) {
      return null;
   }

   @Override
   public CustomDictionary getCustomDictionary(int type) {
      return null;
   }

   private boolean isKeyUpProcessOnly(KeyEvent event) {
      return this._keyUpProcessOnly && event.isInputEvent();
   }

   public MinimalInputMethod(Locale[] aAvailableLocale) {
   }

   private String getKeyboardType(int aLocaleCode) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
