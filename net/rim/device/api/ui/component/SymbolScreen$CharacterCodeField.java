package net.rim.device.api.ui.component;

import com.sun.cldc.i18n.Helper;
import java.io.UnsupportedEncodingException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

class SymbolScreen$CharacterCodeField extends Field {
   StringBuffer _buf;
   private Font _f;
   private int _height;
   private int _width;
   private int _type;
   private int _charCode;
   private int _row;
   private int _cell;
   private char _char;
   private boolean _isInput;
   private byte[] _twoBytes;
   private char[] _oneChar;
   private int COLOUR_ERROR;
   private int COLOUR_NORMAL;
   private int _colour;
   private final SymbolScreen this$0;

   SymbolScreen$CharacterCodeField(SymbolScreen _1, int type, Font font) {
      super(0);
      this.this$0 = _1;
      this._buf = new StringBuffer();
      this._type = 0;
      this._charCode = -1;
      this._row = -1;
      this._cell = -1;
      this._char = 0;
      this._isInput = true;
      this._twoBytes = new byte[2];
      this._oneChar = new char[1];
      this.COLOUR_ERROR = 16711680;
      this.COLOUR_NORMAL = 0;
      this._colour = this.COLOUR_NORMAL;
      this._f = font;
      this.setFont(font);
      this.setType(type);
   }

   public void setType(int type) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean setCode(int code) {
      this._charCode = code;
      int first = code >>> 8 & 0xFF;
      code &= 255;
      boolean odd = false;
      this._row = 1;
      this._cell = 1;
      if ((129 <= first && first <= 239 || 250 <= first && first <= 252) && 64 <= code && code <= 252) {
         if (code <= 158) {
            odd = true;
            if (code <= 126) {
               this._cell = code - 63;
            } else {
               this._cell = code - 64;
            }
         } else {
            this._cell = code - 158;
         }

         if (first <= 159) {
            this._row = (first - 128) * 2;
         } else if (224 <= first) {
            this._row = (first - 192) * 2;
         }

         if (odd) {
            this._row--;
         }

         if (this._row < 1) {
            this._row = 1;
         }
      }

      return this.processInputCode(false);
   }

   public int getCode() {
      return this._charCode;
   }

   public void setHeight(int height) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public boolean isFocusable() {
      return true;
   }

   @Override
   public int getPreferredHeight() {
      return this._height;
   }

   @Override
   public int getPreferredWidth() {
      return this._width;
   }

   @Override
   protected void layout(int width, int height) {
      this.setExtent(this._width, this._height);
   }

   @Override
   protected void paint(Graphics graphics) {
      int textWidth = this._f.getBounds(this._buf);
      if (textWidth > this._width) {
         textWidth = this._width;
      }

      graphics.drawRoundRect(0, 0, this._width, this._height, this._width / 10, this._width / 10);
      graphics.setColor(this._colour);
      graphics.drawText(this._buf, 0, this._buf.length(), (this._width - textWidth) / 2, (this._height - this._f.getHeight()) / 2, 0, this._width);
      graphics.setColor(this.COLOUR_NORMAL);
   }

   @Override
   public int processKeyEvent(int event, char key, int keycode, int time) {
      if (event == 513) {
         if (key != 0) {
            return 0;
         }

         char ch = (char)(keycode >>> 16);
         switch (ch) {
            case '\n':
               this.enter();
               return 0;
            case '\u001b':
            case ' ':
               this.this$0.close();
               return 0;
            case '\u007f':
               this.this$0._symbols.keyDown(keycode, time);
               return 0;
         }

         if (ch != '\b' && (keycode & 2) == 0 && (keycode & 1) == 0 && ch != 'A' && ch != 'B') {
            ch = Keypad.getAltedChar(ch);
         }

         if (ch > 'ﻠ') {
            ch -= 'ﻠ';
         }

         if (ch != '\b' && (ch < '0' || ch > '9') && (this._type == 0 || this._type == 3 || ch < 'A' || ch > 'F')) {
            return this.this$0._symbols.processKeyEvent(event, key, keycode, time);
         }

         if (!this._isInput) {
            this._buf.setLength(0);
            this._colour = this.COLOUR_NORMAL;
            this._isInput = true;
         }

         int len = this._buf.length();
         if (ch == '\b') {
            if (len > 0) {
               this._buf.deleteCharAt(len - 1);
            }
         } else {
            this._buf.append(ch);
         }

         this.invalidate();
         if (this._buf.length() > 4 || this._buf.length() == 4 && this.convertDecToInt(this._buf, 0, 2) != 11) {
            this.processInputCode(true);
         }
      }

      return 0;
   }

   @Override
   protected boolean trackwheelClick(int status, int time) {
      return this.enter();
   }

   private boolean enter() {
      if (this._isInput) {
         while (this._buf.length() < 4) {
            this._buf.insert(0, '0');
         }

         this.processInputCode(true);
      }

      if (this._char > 0) {
         this.this$0._symbols.processSymbol(this._char);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean trackwheelRoll(int amount, int status, int time) {
      if (this._isInput) {
         this.restoreBuf();
         this._isInput = false;
         this.invalidate();
         return false;
      }

      if ((status & 1) == 0) {
         return super.trackwheelRoll(amount, status, time);
      }

      switch (this._type) {
         case -1:
            break;
         case 0:
         default:
            if (this._row > 0 && this._cell > 0) {
               this._cell += amount;
               if (this._cell > 94) {
                  this._cell %= 94;
                  if (this._row >= 92 && (this._row < 115 || this._row >= 120)) {
                     if (this._row == 92) {
                        this._row = 115;
                     } else {
                        this._cell = 94;
                     }
                  } else {
                     this._row++;
                  }
               }

               if (this._cell < 1) {
                  this._cell += 94;
                  if ((this._row <= 1 || this._row > 92) && this._row <= 115) {
                     if (this._row == 115) {
                        this._row = 92;
                     } else {
                        this._cell = 1;
                     }
                  } else {
                     this._row--;
                  }
               }

               this.processInputCode(false);
               return true;
            }
            break;
         case 1:
         case 2:
            if (this._charCode >= 0) {
               this._charCode += amount;
               if (this._charCode > 65535) {
                  this._charCode = 65535;
               }

               if (this._charCode < 0) {
                  this._charCode = 0;
               }

               this.processInputCode(false);
            }
      }

      return true;
   }

   public boolean processInputCode(boolean fromBuffer) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int unicodeToSJIS(char unicode) {
      int sjis = 0;
      byte[] result = null;
      this._oneChar[0] = unicode;

      try {
         result = Helper.charToByteArray(this._oneChar, 0, 1, "Shift_JIS");
      } catch (UnsupportedEncodingException e) {
         Dialog.alert("Encoding Shift_JIS is not supported");
      }

      int len;
      if (result != null && (len = result.length) > 0) {
         if (len == 1) {
            return result[0] & 0xFF;
         }

         if (len == 2) {
            sjis = ((result[0] & 255) << 8) + (result[1] & 255);
         }
      }

      return sjis;
   }

   private int convertDecToInt(StringBuffer buf, int begin, int length) {
      int len = buf.length();
      if (begin >= len) {
         return -1;
      }

      if (begin + length < len) {
         len = begin + length;
      }

      int result = 0;

      for (int i = begin; i < len; i++) {
         result *= 10;
         int ch = this._buf.charAt(i);
         if (48 > ch || ch > 57) {
            return -1;
         }

         result += ch - 48;
      }

      return result;
   }

   private int convertHexToInt(StringBuffer buf, int begin, int length) {
      int len = buf.length();
      if (begin >= len) {
         return -1;
      }

      if (begin + length < len) {
         len = begin + length;
      }

      int result = 0;

      for (int i = begin; i < len; i++) {
         result <<= 4;
         int digit = this.convertHexCharToDigit(buf.charAt(i));
         if (digit < 0) {
            return -1;
         }

         result += digit;
      }

      return result;
   }

   private int convertHexCharToDigit(char ch) {
      if ('0' <= ch && ch <= '9') {
         return ch - 48;
      } else {
         return 65 <= ch && ch <= 70 ? ch - 55 : -1;
      }
   }

   private void convertIntToBuf(int n, StringBuffer buf) {
      int mask = 61440;

      for (int i = 3; i >= 0; i--) {
         int digit = (n & mask) >>> i * 4;
         mask >>>= 4;
         char ch = (char)(digit <= 9 ? 48 + digit : 55 + digit);
         buf.append(ch);
      }
   }

   private void convert2DecToBuf(int n, StringBuffer buf) {
      if (n >= 0 && n <= 99) {
         if (n <= 9) {
            this._buf.append('0');
         }

         this._buf.append(n);
      } else {
         this._buf.append('?');
         this._buf.append('?');
      }
   }

   private void restoreBuf() {
      this._buf.setLength(0);
      if (this._type == 0) {
         if (this._row > 0 && this._cell > 0) {
            this.convert2DecToBuf(this._row, this._buf);
            this.convert2DecToBuf(this._cell, this._buf);
            return;
         }
      } else if (this._charCode >= 0) {
         this.convertIntToBuf(this._charCode, this._buf);
      }
   }
}
