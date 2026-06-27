package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.util.NumberUtilities;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

public class HexEditField extends EditField {
   private boolean _firstNibble;
   private int _maxBytes;
   private char _firstNibbleChar;
   private StringBuffer _strBuf;
   private int _nibbleInsertionIndex;
   AttributedString _tempString = (AttributedString)(new Object());
   AttributedString$Iterator _iterator = this._tempString.getIterator();

   public HexEditField(String var1) {
      this(var1, Integer.MAX_VALUE);
   }

   public HexEditField(String var1, byte[] var2) {
      this(var1, var2, 0, var2.length, Integer.MAX_VALUE);
   }

   public HexEditField(String var1, byte[] var2, int var3, int var4) {
      this(var1, var2, var3, var4, Integer.MAX_VALUE);
   }

   public HexEditField(String var1, byte[] var2, int var3, int var4, int var5) {
      super(var1, null, Integer.MAX_VALUE, 1140850688);
      this._firstNibble = true;
      this._maxBytes = var5;
      this._strBuf = (StringBuffer)(new Object(4));
      this.setData(var2, var3, var4);
   }

   public HexEditField(String var1, int var2) {
      super(var1, null, Integer.MAX_VALUE, 1140850688);
      this._firstNibble = true;
      this._maxBytes = var2;
      this._strBuf = (StringBuffer)(new Object(4));
   }

   @Override
   protected boolean insert(char var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void selectionDelete() {
      if (this.isSelecting()) {
         super.selectionDelete();
      } else if (this.getCursorPosition() != super.getTextLength()) {
         if (this._firstNibble) {
            int var1 = this.getCursorPosition() + 3;
            if (var1 > this.getTextLength()) {
               var1--;
            }

            super.setCursorPosition(var1);
            super.backspace(var1);
         } else {
            super.setCursorPosition(this.getCursorPosition() + 3);
            super.backspace(3);
            this._firstNibble = true;
            this.setCursorPosition(this.getCursorPosition() + 1);
         }

         this.fieldChangeNotify(0);
      }
   }

   @Override
   protected boolean backspace() {
      if (this.getCursorPosition() == 0) {
         return false;
      }

      byte var1 = 3;
      if (!this._firstNibble) {
         if (super.getTextLength() == this.getCursorPosition()) {
            var1 = 2;
         } else {
            super.setCursorPosition(this.getCursorPosition() + 1);
         }
      }

      super.backspace(var1);
      this._firstNibble = true;
      this.fieldChangeNotify(0);
      return true;
   }

   public int getNumBytes() {
      return (super.getTextLength() + 2) / 3;
   }

   public byte[] getAsBytes() {
      byte[] var1 = new byte[this.getNumBytes()];
      int var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var3 < var1.length; var4++) {
         char var5 = var4 < super.getTextLength() ? super.charAt(var4) : ' ';
         if (var5 != ' ') {
            var2 <<= 4;
            var2 |= NumberUtilities.hexDigitToInt(var5);
         } else {
            var1[var3] = (byte)(var2 & 0xFF);
            var2 = 0;
            var3++;
         }
      }

      return var1;
   }

   public short[] getAsShorts() {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      if (this.getNumBytes() % 2 != 0) {
         return null;
      }

      short[] var1 = new short[this.getNumBytes() / 2];

      for (int var5 = 0; var2 < var1.length; var5++) {
         char var6 = var5 < super.getTextLength() ? super.charAt(var5) : ' ';
         if (var6 != ' ') {
            var4 <<= 4;
            var4 |= NumberUtilities.hexDigitToInt(var6);
         } else if ((++var3 & 1) == 0) {
            var1[var2] = (short)(var4 & 65535);
            var2++;
            var4 = 0;
         }
      }

      return var1;
   }

   public int[] getAsInts() {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      if (this.getNumBytes() % 4 != 0) {
         return null;
      }

      int[] var1 = new int[this.getNumBytes() / 4];

      for (int var5 = 0; var2 < var1.length; var5++) {
         char var6 = var5 < super.getTextLength() ? super.charAt(var5) : ' ';
         if (var6 != ' ') {
            var4 <<= 4;
            var4 |= NumberUtilities.hexDigitToInt(var6);
         } else if ((++var3 & 3) == 0) {
            var1[var2] = var4;
            var2++;
            var4 = 0;
         }
      }

      return var1;
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void dispatchEvent(Event var1) {
      if (var1.getID() != 514 && var1.getID() != 519) {
         super.dispatchEvent(var1);
      }
   }

   @Override
   public boolean paste(Clipboard var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setData(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void setData(int[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void setData(short[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public void setMaxSize(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
