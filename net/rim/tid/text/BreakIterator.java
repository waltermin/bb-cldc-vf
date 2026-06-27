package net.rim.tid.text;

import net.rim.device.api.i18n.Locale;
import net.rim.device.internal.ui.StringBufferGap;

public final class BreakIterator {
   private byte[][][] _wordlistData;
   private Locale _locale;
   private String _str;
   private StringBuffer _strB;
   private StringBufferGap _strBG;
   private char[] _charArr;
   private int _dataType;
   private int _iterType;
   private int _currentPos;
   private static final int UNDEFINED;
   private static final int STR;
   private static final int STRB;
   private static final int STRBG;
   private static final int CHARARR;
   public static final int ECharacter;
   public static final int EWord;
   public static final int ELine;
   public static final int DONE;

   public static final BreakIterator getInstance(int var0, Locale var1) {
      return new BreakIterator(var0, var1);
   }

   public static final BreakIterator getInstance(int var0) {
      return getInstance(var0, null);
   }

   private BreakIterator(int var1, Locale var2) {
   }

   public final void setText(String var1) {
      this._str = var1;
      this._dataType = 0;
   }

   public final void setText(StringBuffer var1) {
      this._strB = var1;
      this._dataType = 1;
   }

   public final void setText(StringBufferGap var1) {
      this._strBG = var1;
      this._dataType = 2;
   }

   public final void setText(char[] var1) {
      this._charArr = var1;
      this._dataType = 3;
   }

   final StringBufferGap getText() {
      switch (this._dataType) {
         case -1:
            return null;
         case 0:
         default:
            return (StringBufferGap)(new Object(this._str));
         case 1:
            return (StringBufferGap)(new Object(this._strB));
         case 2:
            return this._strBG;
         case 3:
            return (StringBufferGap)(new Object((String)(new Object(this._charArr))));
      }
   }

   public final Locale getLocale() {
      return this._locale;
   }

   public final int getIteratorBreakingType() {
      return this._iterType;
   }

   public final int first() {
      if (this._dataType != -1 && this._iterType != -1) {
         this._currentPos = 0;
         return this._currentPos;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public final int last() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final int following(int var1) {
      if (this._dataType != -1 && this._iterType != -1) {
         this._currentPos = Integer.MAX_VALUE;
         switch (this._dataType) {
            case -1:
               break;
            case 0:
            default:
               this._currentPos = this.scan(this._str, var1, 1, this._iterType, this._wordlistData);
               break;
            case 1:
               this._currentPos = this.scan(this._strB, var1, 1, this._iterType, this._wordlistData);
               break;
            case 2:
               this._currentPos = this.scan(this._strBG, var1, 1, this._iterType, this._wordlistData);
               break;
            case 3:
               this._currentPos = this.scan(this._charArr, var1, 1, this._iterType, this._wordlistData);
         }

         return this._currentPos;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public final int preceding(int var1) {
      if (this._dataType != -1 && this._iterType != -1) {
         this._currentPos = Integer.MAX_VALUE;
         switch (this._dataType) {
            case -1:
               break;
            case 0:
            default:
               this._currentPos = this.scan(this._str, var1, 0, this._iterType, this._wordlistData);
               break;
            case 1:
               this._currentPos = this.scan(this._strB, var1, 0, this._iterType, this._wordlistData);
               break;
            case 2:
               this._currentPos = this.scan(this._strBG, var1, 0, this._iterType, this._wordlistData);
               break;
            case 3:
               this._currentPos = this.scan(this._charArr, var1, 0, this._iterType, this._wordlistData);
         }

         return this._currentPos;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public final int next() {
      return this.next(1);
   }

   public final int next(int var1) {
      if (this._dataType != -1 && this._iterType != -1) {
         while (var1-- > 0) {
            switch (this._dataType) {
               case -1:
                  break;
               case 0:
               default:
                  this._currentPos = this.scan(this._str, this._currentPos, 1, this._iterType, this._wordlistData);
                  break;
               case 1:
                  this._currentPos = this.scan(this._strB, this._currentPos, 1, this._iterType, this._wordlistData);
                  break;
               case 2:
                  this._currentPos = this.scan(this._strBG, this._currentPos, 1, this._iterType, this._wordlistData);
                  break;
               case 3:
                  this._currentPos = this.scan(this._charArr, this._currentPos, 1, this._iterType, this._wordlistData);
            }

            if (this._currentPos == Integer.MAX_VALUE) {
               break;
            }
         }

         return this._currentPos;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public final int previous() {
      return this.previous(1);
   }

   public final int previous(int var1) {
      if (this._dataType != -1 && this._iterType != -1) {
         while (var1-- > 0) {
            switch (this._dataType) {
               case -1:
                  break;
               case 0:
               default:
                  this._currentPos = this.scan(this._str, this._currentPos, 0, this._iterType, this._wordlistData);
                  break;
               case 1:
                  this._currentPos = this.scan(this._strB, this._currentPos, 0, this._iterType, this._wordlistData);
                  break;
               case 2:
                  this._currentPos = this.scan(this._strBG, this._currentPos, 0, this._iterType, this._wordlistData);
                  break;
               case 3:
                  this._currentPos = this.scan(this._charArr, this._currentPos, 0, this._iterType, this._wordlistData);
            }

            if (this._currentPos == Integer.MAX_VALUE) {
               break;
            }
         }

         return this._currentPos;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   private final native int scan(String var1, int var2, int var3, int var4, byte[][][] var5);

   private final native int scan(StringBuffer var1, int var2, int var3, int var4, byte[][][] var5);

   private final native int scan(StringBufferGap var1, int var2, int var3, int var4, byte[][][] var5);

   private final native int scan(char[] var1, int var2, int var3, int var4, byte[][][] var5);
}
