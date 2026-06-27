package net.rim.device.api.ui.component;

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

   SymbolScreen$CharacterCodeField(SymbolScreen var1, int var2, Font var3) {
      super(0);
      this.this$0 = var1;
      this._buf = (StringBuffer)(new Object());
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
      this._f = var3;
      this.setFont(var3);
      this.setType(var2);
   }

   public void setType(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean setCode(int var1) {
      this._charCode = var1;
      int var2 = var1 >>> 8 & 0xFF;
      var1 &= 255;
      boolean var3 = false;
      this._row = 1;
      this._cell = 1;
      if ((129 <= var2 && var2 <= 239 || 250 <= var2 && var2 <= 252) && 64 <= var1 && var1 <= 252) {
         if (var1 <= 158) {
            var3 = true;
            if (var1 <= 126) {
               this._cell = var1 - 63;
            } else {
               this._cell = var1 - 64;
            }
         } else {
            this._cell = var1 - 158;
         }

         if (var2 <= 159) {
            this._row = (var2 - 128) * 2;
         } else if (224 <= var2) {
            this._row = (var2 - 192) * 2;
         }

         if (var3) {
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

   public void setHeight(int var1) {
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
   protected void layout(int var1, int var2) {
      this.setExtent(this._width, this._height);
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = this._f.getBounds(this._buf);
      if (var2 > this._width) {
         var2 = this._width;
      }

      var1.drawRoundRect(0, 0, this._width, this._height, this._width / 10, this._width / 10);
      var1.setColor(this._colour);
      var1.drawText(this._buf, 0, this._buf.length(), (this._width - var2) / 2, (this._height - this._f.getHeight()) / 2, 0, this._width);
      var1.setColor(this.COLOUR_NORMAL);
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      if (var1 == 513) {
         if (var2 != 0) {
            return 0;
         }

         char var5 = (char)(var3 >>> 16);
         switch (var5) {
            case '\n':
               this.enter();
               return 0;
            case '\u001b':
            case ' ':
               this.this$0.close();
               return 0;
            case '\u007f':
               this.this$0._symbols.keyDown(var3, var4);
               return 0;
         }

         if (var5 != '\b' && (var3 & 2) == 0 && (var3 & 1) == 0 && var5 != 'A' && var5 != 'B') {
            var5 = Keypad.getAltedChar(var5);
         }

         if (var5 > 'ﻠ') {
            var5 -= 'ﻠ';
         }

         if (var5 != '\b' && (var5 < '0' || var5 > '9') && (this._type == 0 || this._type == 3 || var5 < 'A' || var5 > 'F')) {
            return this.this$0._symbols.processKeyEvent(var1, var2, var3, var4);
         }

         if (!this._isInput) {
            this._buf.setLength(0);
            this._colour = this.COLOUR_NORMAL;
            this._isInput = true;
         }

         int var6 = this._buf.length();
         if (var5 == '\b') {
            if (var6 > 0) {
               this._buf.deleteCharAt(var6 - 1);
            }
         } else {
            this._buf.append(var5);
         }

         this.invalidate();
         if (this._buf.length() > 4 || this._buf.length() == 4 && this.convertDecToInt(this._buf, 0, 2) != 11) {
            this.processInputCode(true);
         }
      }

      return 0;
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
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
   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      if (this._isInput) {
         this.restoreBuf();
         this._isInput = false;
         this.invalidate();
         return false;
      }

      if ((var2 & 1) == 0) {
         return super.trackwheelRoll(var1, var2, var3);
      }

      switch (this._type) {
         case -1:
            break;
         case 0:
         default:
            if (this._row > 0 && this._cell > 0) {
               this._cell += var1;
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
               this._charCode += var1;
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

   public boolean processInputCode(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int unicodeToSJIS(char var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private int convertDecToInt(StringBuffer var1, int var2, int var3) {
      int var4 = var1.length();
      if (var2 >= var4) {
         return -1;
      }

      if (var2 + var3 < var4) {
         var4 = var2 + var3;
      }

      int var6 = 0;

      for (int var7 = var2; var7 < var4; var7++) {
         var6 *= 10;
         char var5 = this._buf.charAt(var7);
         if ('0' > var5 || var5 > '9') {
            return -1;
         }

         var6 += var5 - '0';
      }

      return var6;
   }

   private int convertHexToInt(StringBuffer var1, int var2, int var3) {
      int var4 = var1.length();
      if (var2 >= var4) {
         return -1;
      }

      if (var2 + var3 < var4) {
         var4 = var2 + var3;
      }

      int var6 = 0;

      for (int var7 = var2; var7 < var4; var7++) {
         var6 <<= 4;
         int var5 = this.convertHexCharToDigit(var1.charAt(var7));
         if (var5 < 0) {
            return -1;
         }

         var6 += var5;
      }

      return var6;
   }

   private int convertHexCharToDigit(char var1) {
      if ('0' <= var1 && var1 <= '9') {
         return var1 - 48;
      } else {
         return 65 <= var1 && var1 <= 70 ? var1 - 55 : -1;
      }
   }

   private void convertIntToBuf(int var1, StringBuffer var2) {
      char var3 = '\uf000';

      for (int var6 = 3; var6 >= 0; var6--) {
         int var4 = (var1 & var3) >>> var6 * 4;
         var3 >>>= 4;
         char var5 = (char)(var4 <= 9 ? 48 + var4 : 55 + var4);
         var2.append(var5);
      }
   }

   private void convert2DecToBuf(int var1, StringBuffer var2) {
      if (var1 >= 0 && var1 <= 99) {
         if (var1 <= 9) {
            this._buf.append('0');
         }

         this._buf.append(var1);
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
