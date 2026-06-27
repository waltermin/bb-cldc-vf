package net.rim.tid.im.layout;

import java.io.InputStream;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.util.CharacterUtilities;

public class SLKeyLayout {
   private short[][][] _ranges;
   private short[] _special;
   private int _headerLength;
   private int _indexesLength;
   private byte[] _data;
   private StringBuffer _defResult;
   private Locale _locale;
   private byte[] _cache;
   private int _cacheSize;
   private boolean _isReduced;
   private byte _mask;
   private int _KeyCodeCache;
   private int[] _iAltedKeys;
   private StringBuffer _bytes2StringCache;
   private String _nameID;
   private static final int STATUS_CHARACTER;
   private static final int STATUS_UNALT;
   private static String MAP_COMMON_PREFIX;
   private static String MAP_COMMON_SUFFIX;
   private static int[] _modifiers;
   private static String[] _maps;

   public SLKeyLayout(Locale var1, boolean var2, byte var3, InputStream var4) {
   }

   public SLKeyLayout(Locale var1, boolean var2, byte var3, byte[] var4) {
   }

   private boolean openMapFile(InputStream var1) {
      byte[] var2 = new byte[var1.available()];
      var1.read(var2);
      return this.openMapFile(var2);
   }

   public String getNameID() {
      return this._nameID;
   }

   public void setNameID(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private boolean openMapFile(byte[] var1) {
      throw new RuntimeException("cod2jar: array creation");
   }

   private void createAltKeys() {
      int var1 = 0;
      int var2 = this._ranges.length;

      for (int var3 = 0; var3 < var2; var3++) {
         var1 += this._ranges[var3][1] - this._ranges[var3][0];
      }

      var1 += this._special.length;
      this._iAltedKeys = new int[var1];
      int var9 = 0;

      for (int var4 = 0; var4 < this._ranges.length; var4++) {
         short[] var5 = this._ranges[var4][1];

         for (short[] var6 = this._ranges[var4][0]; var6 < var5; var6++) {
            this._iAltedKeys[var9++] = CharacterUtilities.toUpperCase(this.getKeyChars((int)var6, 0, false).charAt(0), this._locale.getCode());
         }
      }

      var2 = this._special.length;

      for (int var10 = 0; var10 < var2; var10++) {
         this._iAltedKeys[var9++] = CharacterUtilities.toUpperCase(this.getKeyChars(this._special[var10], 0, false).charAt(0), this._locale.getCode());
      }
   }

   private int findAltedKeyCode(int var1) {
      int var2 = this._iAltedKeys.length;

      for (int var3 = 0; var3 < var2; var3++) {
         if (var1 == this._iAltedKeys[var3]) {
            int var4 = 0;

            for (int var5 = 0; var5 < this._ranges.length; var5++) {
               if (var4 + (this._ranges[var5][1] - this._ranges[var5][0]) > var3) {
                  return this._ranges[var5][0] + (var3 - var4);
               }

               var4 += this._ranges[var5][1] - this._ranges[var5][0];
            }

            return this._special[var3 - var4];
         }
      }

      return -1;
   }

   public char getAltedChar(char var1) {
      int var2 = -1;
      switch (var1) {
         case 'Σ':
            var2 = 83;
            break;
         case 'ς':
            var2 = 87;
            break;
         default:
            var2 = this.findAltedKeyCode(CharacterUtilities.toUpperCase(var1, this._locale.getCode()));
      }

      return var2 != -1 ? this.getKeyChars(var2, 8, false).charAt(0) : '\u0000';
   }

   public char getUnaltedChar(char var1) {
      var1 = CharacterUtilities.toUpperCase(var1, this._locale.getCode());

      for (int var2 = 0; var2 < this._ranges.length; var2++) {
         short[] var3 = this._ranges[var2][1];

         for (short[] var4 = this._ranges[var2][0]; var4 < var3; var4++) {
            char var5 = this.getKeyChars((int)var4, 8, false).charAt(0);
            if (CharacterUtilities.toUpperCase(var5, this._locale.getCode()) == var1) {
               return this.getKeyChars((int)var4, 0, false).charAt(0);
            }
         }
      }

      int var7 = this._special.length;

      for (int var8 = 0; var8 < var7; var8++) {
         char var9 = this.getKeyChars(this._special[var8], 8, false).charAt(0);
         if (CharacterUtilities.toUpperCase(var9, this._locale.getCode()) == var1) {
            return this.getKeyChars(this._special[var8], 0, false).charAt(0);
         }
      }

      return '\u0000';
   }

   public int getOriginalKeyCode(char var1, int var2) {
      for (int var3 = 0; var3 < this._ranges.length; var3++) {
         short[] var4 = this._ranges[var3][1];

         for (short[] var5 = this._ranges[var3][0]; var5 < var4; var5++) {
            StringBuffer var6 = this.getKeyChars((int)var5, var2, false);
            if (this.indexOf(var6, var1) != -1) {
               return (int)var5;
            }
         }
      }

      int var7 = this._special.length;

      for (int var8 = 0; var8 < var7; var8++) {
         StringBuffer var9 = this.getKeyChars(this._special[var8], var2, false);
         if (this.indexOf(var9, var1) != -1) {
            return this._special[var8];
         }
      }

      return 0;
   }

   private int bytesToInt(byte var1, byte var2) {
      int var3 = 0;
      var3 |= var1 & 255;
      var3 <<= 8;
      return var3 | var2 & 0xFF;
   }

   public boolean isReduced() {
      return this._isReduced;
   }

   public byte getModifierMask() {
      return this._mask;
   }

   public boolean contains(char var1) {
      var1 = CharacterUtilities.toUpperCase(var1, this._locale.getCode());

      for (int var2 = 0; var2 < this._ranges.length; var2++) {
         short[] var3 = this._ranges[var2][1];

         for (short[] var4 = this._ranges[var2][0]; var4 < var3; var4++) {
            for (int var5 = 0; var5 < 7; var5++) {
               if (var5 != 4 && var5 != 5) {
                  char var6 = this.getKeyChars((int)var4, _modifiers[var5], false).charAt(0);
                  if (CharacterUtilities.toUpperCase(var6, this._locale.getCode()) == var1) {
                     return true;
                  }
               }
            }
         }
      }

      int var8 = this._special.length;

      for (int var9 = 0; var9 < var8; var9++) {
         for (int var10 = 0; var10 < 7; var10++) {
            if (var10 != 4 && var10 != 5) {
               char var11 = this.getKeyChars(this._special[var9], _modifiers[var10], false).charAt(0);
               if (CharacterUtilities.toUpperCase(var11, this._locale.getCode()) == var1) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean getDataFor(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized StringBuffer getComplementaryChars(char var1, int var2) {
      for (int var3 = 0; var3 < this._ranges.length; var3++) {
         short[] var4 = this._ranges[var3][1];

         for (short[] var5 = this._ranges[var3][0]; var5 < var4; var5++) {
            StringBuffer var6 = this.getKeyChars((int)var5, var2, false);
            if (this.indexOf(var6, var1) != -1) {
               return var6;
            }
         }
      }

      int var7 = this._special.length;

      for (int var8 = 0; var8 < var7; var8++) {
         StringBuffer var9 = this.getKeyChars(this._special[var8], var2, false);
         if (this.indexOf(var9, var1) != -1) {
            return var9;
         }
      }

      return null;
   }

   private int indexOf(StringBuffer var1, char var2) {
      for (int var3 = 0; var3 < var1.length(); var3++) {
         if (var1.charAt(var3) == var2) {
            return var3;
         }
      }

      return -1;
   }

   private int getModifierIndex(int var1) {
      for (int var2 = 0; var2 < _modifiers.length; var2++) {
         if (_modifiers[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public synchronized StringBuffer getKeyChars(int var1, int var2) {
      return this.getKeyChars(var1, var2, false);
   }

   public synchronized StringBuffer getKeyChars(int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private synchronized StringBuffer getKeyChars0(byte[] var1, int var2, int var3, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private StringBuffer bytesToString(byte[] var1, int var2, int var3, byte var4) {
      if (var3 == 0) {
         return this.getDefBuffer();
      }

      if (this._bytes2StringCache == null) {
         this._bytes2StringCache = (StringBuffer)(new Object());
      }

      this._bytes2StringCache.setLength(var3);
      if (var4 == 1) {
         for (int var5 = 0; var5 < var3; var5++) {
            this._bytes2StringCache.setCharAt(var5, (char)(var1[var2 + var5] & 0xFF));
         }
      } else {
         for (int var7 = 0; var7 < var3; var7++) {
            int var6 = var2 + var7 * 2;
            this._bytes2StringCache.setCharAt(var7, (char)this.bytesToInt(var1[var6], var1[var6 + 1]));
         }
      }

      return this._bytes2StringCache;
   }

   public static int convertStatusToModifiers(int var0) {
      if ((var0 & 32768) != 0) {
         return 32768;
      }

      var0 &= 28695;
      if (var0 == 0) {
         return 0;
      }

      if ((var0 & 4) != 0 && (var0 & 16) != 0) {
         var0 &= -7;
      }

      if ((var0 & 4) != 0) {
         var0 &= -3;
         if ((var0 & 1) != 0) {
            var0 &= -5;
         }
      }

      if ((var0 & 16) != 0) {
         var0 &= -2;
         if ((var0 & 2) != 0) {
            var0 &= -17;
         }
      }

      byte var1 = 0;
      if ((var0 & 2) != 0) {
         var1 |= 1;
      }

      if ((var0 & 1) != 0) {
         var1 |= 8;
      }

      if ((var0 & 4) != 0) {
         var1 |= 2;
      }

      if ((var0 & 16) != 0) {
         var1 |= 10;
      }

      return var1;
   }

   public static int convertModifiersToStatus(int var0) {
      byte var1 = 0;
      if ((var0 & 1) != 0) {
         var1 |= 2;
      }

      if ((var0 & 8) != 0) {
         var1 |= 1;
      }

      return var1;
   }

   private StringBuffer getDefBuffer() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public Locale getLocale() {
      return this._locale;
   }

   private static boolean isExists(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static String getMapID(int var0, String var1, Locale var2, String var3, boolean var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static SLKeyLayout getLayout(Locale var0, boolean var1, int var2, String var3, Locale var4, String var5, boolean var6) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static InputStream getLayoutData(int var0, String var1, Locale var2, String var3, boolean var4) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static String getKeyboardType(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
