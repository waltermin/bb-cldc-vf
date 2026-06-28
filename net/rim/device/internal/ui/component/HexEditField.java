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

   public HexEditField(String label) {
      this(label, Integer.MAX_VALUE);
   }

   public HexEditField(String label, byte[] value) {
      this(label, value, 0, value.length, Integer.MAX_VALUE);
   }

   public HexEditField(String label, byte[] value, int offset, int length) {
      this(label, value, offset, length, Integer.MAX_VALUE);
   }

   public HexEditField(String label, byte[] value, int offset, int length, int maxBytes) {
      super(label, null, Integer.MAX_VALUE, 1140850688);
      this._firstNibble = true;
      this._maxBytes = maxBytes;
      this._strBuf = (StringBuffer)(new Object(4));
      this.setData(value, offset, length);
   }

   public HexEditField(String label, int maxBytes) {
      super(label, null, Integer.MAX_VALUE, 1140850688);
      this._firstNibble = true;
      this._maxBytes = maxBytes;
      this._strBuf = (StringBuffer)(new Object(4));
   }

   @Override
   protected boolean insert(char key, int status) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int moveFocus(int amount, int status, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void selectionDelete() {
      if (this.isSelecting()) {
         super.selectionDelete();
      } else if (this.getCursorPosition() != super.getTextLength()) {
         if (this._firstNibble) {
            int toDelete = this.getCursorPosition() + 3;
            if (toDelete > this.getTextLength()) {
               toDelete--;
            }

            super.setCursorPosition(toDelete);
            super.backspace(toDelete);
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

      int count = 3;
      if (!this._firstNibble) {
         if (super.getTextLength() == this.getCursorPosition()) {
            count = 2;
         } else {
            super.setCursorPosition(this.getCursorPosition() + 1);
         }
      }

      super.backspace(count);
      this._firstNibble = true;
      this.fieldChangeNotify(0);
      return true;
   }

   public int getNumBytes() {
      return (super.getTextLength() + 2) / 3;
   }

   public byte[] getAsBytes() {
      byte[] vals = new byte[this.getNumBytes()];
      int val = 0;
      int byteCount = 0;

      for (int i = 0; byteCount < vals.length; i++) {
         char ch = i < super.getTextLength() ? super.charAt(i) : ' ';
         if (ch != ' ') {
            val <<= 4;
            val |= NumberUtilities.hexDigitToInt(ch);
         } else {
            vals[byteCount] = (byte)(val & 0xFF);
            val = 0;
            byteCount++;
         }
      }

      return vals;
   }

   public short[] getAsShorts() {
      int shortCount = 0;
      int byteCount = 0;
      int sVal = 0;
      if (this.getNumBytes() % 2 != 0) {
         return null;
      }

      short[] vals = new short[this.getNumBytes() / 2];

      for (int i = 0; shortCount < vals.length; i++) {
         char ch = i < super.getTextLength() ? super.charAt(i) : ' ';
         if (ch != ' ') {
            sVal <<= 4;
            sVal |= NumberUtilities.hexDigitToInt(ch);
         } else if ((++byteCount & 1) == 0) {
            vals[shortCount] = (short)(sVal & 65535);
            shortCount++;
            sVal = 0;
         }
      }

      return vals;
   }

   public int[] getAsInts() {
      int intCount = 0;
      int byteCount = 0;
      int iVal = 0;
      if (this.getNumBytes() % 4 != 0) {
         return null;
      }

      int[] vals = new int[this.getNumBytes() / 4];

      for (int i = 0; intCount < vals.length; i++) {
         char ch = i < super.getTextLength() ? super.charAt(i) : ' ';
         if (ch != ' ') {
            iVal <<= 4;
            iVal |= NumberUtilities.hexDigitToInt(ch);
         } else if ((++byteCount & 3) == 0) {
            vals[intCount] = iVal;
            intCount++;
            iVal = 0;
         }
      }

      return vals;
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent event) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void dispatchEvent(Event rEvent) {
      if (rEvent.getID() != 514 && rEvent.getID() != 519) {
         super.dispatchEvent(rEvent);
      }
   }

   @Override
   public boolean paste(Clipboard cb) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setData(byte[] val, int offset, int length) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void setData(int[] val, int offset, int length) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void setData(short[] val, int offset, int length) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public void setMaxSize(int maxSize) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
