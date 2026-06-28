package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.text.IPTextFilter;

public class IPEditField extends EditField {
   private boolean keyRepeatProcessed;
   private int lastKeyPosition;

   public IPEditField(String label, String initialValue) {
      super(label, initialValue);
      this.setFilter(new IPTextFilter());
   }

   public IPEditField(String label, String initialValue, int maxNumChars) {
      super(label, initialValue, maxNumChars, 0);
      this.setFilter(new IPTextFilter());
   }

   public IPEditField(String label, String initialValue, int maxNumChars, int filterFlags) {
      super(label, initialValue, maxNumChars, 0);
      this.setFilter(new IPTextFilter(filterFlags));
   }

   @Override
   protected boolean insert(char key, int status) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      this.keyRepeatProcessed = false;
      this.lastKeyPosition = this.getCursorPosition();
      return super.keyDown(keycode, time);
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      boolean handled = false;
      if (!this.keyRepeatProcessed) {
         char altedKeyChar = Keypad.getAltedChar(Keypad.map(keycode));
         if (Character.isDigit(altedKeyChar)) {
            if (this.lastKeyPosition != this.getCursorPosition()) {
               this.backspace();
            }

            this.insert(altedKeyChar, 0);
            this.keyRepeatProcessed = true;
            handled = true;
         }
      }

      if (!handled) {
         handled = super.keyRepeat(keycode, time);
      }

      return handled;
   }

   public static final int parseIpAddr(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void appendIpAddr(StringBuffer strBuf, int ip) {
      for (int shift = 24; shift >= 0; shift -= 8) {
         int temp = ip >>> shift & 0xFF;
         strBuf.append(temp);
         if (shift != 0) {
            strBuf.append('.');
         }
      }
   }

   public static final void appendIpAddr(StringBuffer strBuf, byte[] ip) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
