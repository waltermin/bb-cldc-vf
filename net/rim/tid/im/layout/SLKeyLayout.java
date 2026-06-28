package net.rim.tid.im.layout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.tid.util.Utils;
import net.rim.vm.Array;

public class SLKeyLayout {
   private short[][][] _ranges;
   private short[] _special;
   private int _headerLength;
   private int _indexesLength;
   private byte[] _data;
   private StringBuffer _defResult = new StringBuffer("");
   private Locale _locale;
   private byte[] _cache;
   private int _cacheSize = -1;
   private boolean _isReduced;
   private byte _mask;
   private int _KeyCodeCache = -1;
   private int[] _iAltedKeys;
   private StringBuffer _bytes2StringCache;
   private String _nameID;
   private static final int STATUS_CHARACTER;
   private static final int STATUS_UNALT;
   private static String MAP_COMMON_PREFIX;
   private static String MAP_COMMON_SUFFIX;
   private static int[] _modifiers;
   private static String[] _maps;

   public SLKeyLayout(Locale locale, boolean reduced, byte modifierMask, InputStream is) {
      this._isReduced = reduced;
      this._mask = modifierMask;
      this._locale = locale;

      try {
         this.openMapFile(is);
      } catch (Exception e) {
         System.err.println("Error while opening map file: " + locale.getLanguage() + " | " + e);
      }
   }

   public SLKeyLayout(Locale locale, boolean reduced, byte modifierMask, byte[] data) {
      this._isReduced = reduced;
      this._mask = modifierMask;
      this._locale = locale;

      try {
         this.openMapFile(data);
      } catch (Exception e) {
         System.err.println("Error while opening map file: " + locale.getLanguage() + " | " + e);
      }
   }

   private boolean openMapFile(InputStream is) {
      byte[] data = new byte[is.available()];
      is.read(data);
      return this.openMapFile(data);
   }

   public String getNameID() {
      return this._nameID;
   }

   public void setNameID(String nameID) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private boolean openMapFile(byte[] data) {
      throw new RuntimeException("cod2jar: array creation");
   }

   private void createAltKeys() {
      int size = 0;
      int len = this._ranges.length;

      for (int i = 0; i < len; i++) {
         size += this._ranges[i][1] - this._ranges[i][0];
      }

      size += this._special.length;
      this._iAltedKeys = new int[size];
      int index = 0;

      for (int i = 0; i < this._ranges.length; i++) {
         int rLen = (int)this._ranges[i][1];

         for (int j = (int)this._ranges[i][0]; j < rLen; j++) {
            this._iAltedKeys[index++] = CharacterUtilities.toUpperCase(this.getKeyChars(j, 0, false).charAt(0), this._locale.getCode());
         }
      }

      len = this._special.length;

      for (int i = 0; i < len; i++) {
         this._iAltedKeys[index++] = CharacterUtilities.toUpperCase(this.getKeyChars(this._special[i], 0, false).charAt(0), this._locale.getCode());
      }
   }

   private int findAltedKeyCode(int ch) {
      int len = this._iAltedKeys.length;

      for (int i = 0; i < len; i++) {
         if (ch == this._iAltedKeys[i]) {
            int index = 0;

            for (int j = 0; j < this._ranges.length; j++) {
               if (index + (this._ranges[j][1] - this._ranges[j][0]) > i) {
                  return this._ranges[j][0] + (i - index);
               }

               index += this._ranges[j][1] - this._ranges[j][0];
            }

            return this._special[i - index];
         }
      }

      return -1;
   }

   public char getAltedChar(char ch) {
      int keyCode = -1;
      switch (ch) {
         case 'Σ':
            keyCode = 83;
            break;
         case 'ς':
            keyCode = 87;
            break;
         default:
            keyCode = this.findAltedKeyCode(CharacterUtilities.toUpperCase(ch, this._locale.getCode()));
      }

      return keyCode != -1 ? this.getKeyChars(keyCode, 8, false).charAt(0) : '\u0000';
   }

   public char getUnaltedChar(char ch) {
      ch = CharacterUtilities.toUpperCase(ch, this._locale.getCode());

      for (int i = 0; i < this._ranges.length; i++) {
         int rLen = (int)this._ranges[i][1];

         for (int j = (int)this._ranges[i][0]; j < rLen; j++) {
            char normal = this.getKeyChars(j, 8, false).charAt(0);
            if (CharacterUtilities.toUpperCase(normal, this._locale.getCode()) == ch) {
               return this.getKeyChars(j, 0, false).charAt(0);
            }
         }
      }

      int len = this._special.length;

      for (int i = 0; i < len; i++) {
         char normal = this.getKeyChars(this._special[i], 8, false).charAt(0);
         if (CharacterUtilities.toUpperCase(normal, this._locale.getCode()) == ch) {
            return this.getKeyChars(this._special[i], 0, false).charAt(0);
         }
      }

      return '\u0000';
   }

   public int getOriginalKeyCode(char ch, int modifier) {
      for (int i = 0; i < this._ranges.length; i++) {
         int rLen = (int)this._ranges[i][1];

         for (int j = (int)this._ranges[i][0]; j < rLen; j++) {
            StringBuffer chars = this.getKeyChars(j, modifier, false);
            if (this.indexOf(chars, ch) != -1) {
               return j;
            }
         }
      }

      int len = this._special.length;

      for (int i = 0; i < len; i++) {
         StringBuffer chars = this.getKeyChars(this._special[i], modifier, false);
         if (this.indexOf(chars, ch) != -1) {
            return this._special[i];
         }
      }

      return 0;
   }

   private int bytesToInt(byte byte1, byte byte2) {
      int result = 0;
      result |= byte1 & 255;
      result <<= 8;
      return result | byte2 & 0xFF;
   }

   public boolean isReduced() {
      return this._isReduced;
   }

   public byte getModifierMask() {
      return this._mask;
   }

   public boolean contains(char ch) {
      ch = CharacterUtilities.toUpperCase(ch, this._locale.getCode());

      for (int i = 0; i < this._ranges.length; i++) {
         int rLen = (int)this._ranges[i][1];

         for (int j = (int)this._ranges[i][0]; j < rLen; j++) {
            for (int g = 0; g < 7; g++) {
               if (g != 4 && g != 5) {
                  char normal = this.getKeyChars(j, _modifiers[g], false).charAt(0);
                  if (CharacterUtilities.toUpperCase(normal, this._locale.getCode()) == ch) {
                     return true;
                  }
               }
            }
         }
      }

      int len = this._special.length;

      for (int i = 0; i < len; i++) {
         for (int g = 0; g < 7; g++) {
            if (g != 4 && g != 5) {
               char normal = this.getKeyChars(this._special[i], _modifiers[g], false).charAt(0);
               if (CharacterUtilities.toUpperCase(normal, this._locale.getCode()) == ch) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean getDataFor(int keyCode) {
      try {
         int ptr = 0;
         boolean found = false;
         int skipInIndexes = 0;
         int offsetStart = 0;

         for (int i = 0; i < this._ranges.length; i++) {
            int start = this._ranges[i][0] & '\uffff';
            int finish = this._ranges[i][1] & '\uffff';
            if (keyCode >= start && keyCode < finish) {
               skipInIndexes += (keyCode - start) * 2;
               found = true;
               break;
            }

            skipInIndexes += (finish - start) * 2;
         }

         if (!found) {
            for (int i = 0; i < this._special.length; i++) {
               if (keyCode == this._special[i]) {
                  skipInIndexes += 2 * i;
                  found = true;
                  break;
               }
            }
         }

         int toRead = 0;
         if (!found) {
            return false;
         }

         ptr = 2 + this._headerLength + skipInIndexes;
         byte readedBytes = 2;
         offsetStart = this.bytesToInt(this._data[ptr++], this._data[ptr++]);
         if (skipInIndexes + 2 == this._indexesLength) {
            toRead = -1;
         } else {
            readedBytes = 4;
            toRead = this.bytesToInt(this._data[ptr++], this._data[ptr++]) - offsetStart;
         }

         if (toRead == 0) {
            return false;
         }

         ptr += this._indexesLength - (skipInIndexes + readedBytes) + offsetStart;
         if (toRead == -1) {
            toRead = this._data.length - ptr;
         }

         if (this._cache == null) {
            this._cache = new byte[toRead];
         } else if (this._cache.length < toRead) {
            Array.resize(this._cache, toRead);
         }

         System.arraycopy(this._data, ptr, this._cache, 0, toRead);
         this._cacheSize = toRead;
         return true;
      } catch (Exception e) {
         System.err.println("Error while requesting data from map file: " + this._locale.getLanguage());
         e.printStackTrace();
         return false;
      }
   }

   public synchronized StringBuffer getComplementaryChars(char ch, int modifier) {
      for (int i = 0; i < this._ranges.length; i++) {
         int rLen = (int)this._ranges[i][1];

         for (int j = (int)this._ranges[i][0]; j < rLen; j++) {
            StringBuffer temp = this.getKeyChars(j, modifier, false);
            if (this.indexOf(temp, ch) != -1) {
               return temp;
            }
         }
      }

      int len = this._special.length;

      for (int i = 0; i < len; i++) {
         StringBuffer temp = this.getKeyChars(this._special[i], modifier, false);
         if (this.indexOf(temp, ch) != -1) {
            return temp;
         }
      }

      return null;
   }

   private int indexOf(StringBuffer sb, char element) {
      for (int i = 0; i < sb.length(); i++) {
         if (sb.charAt(i) == element) {
            return i;
         }
      }

      return -1;
   }

   private int getModifierIndex(int modifier) {
      for (int i = 0; i < _modifiers.length; i++) {
         if (_modifiers[i] == modifier) {
            return i;
         }
      }

      return -1;
   }

   public synchronized StringBuffer getKeyChars(int keyCode, int modifier) {
      return this.getKeyChars(keyCode, modifier, false);
   }

   public synchronized StringBuffer getKeyChars(int keyCode, int modifier, boolean isCapsOn) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private synchronized StringBuffer getKeyChars0(byte[] data, int length, int modifier, boolean isCapsOn) {
      byte needCaps = (byte)(data[0] & 1);
      byte byteStructure = (byte)((data[0] & 2) != 0 ? 1 : 2);
      if (isCapsOn && needCaps == 1) {
         switch (modifier) {
            case -1:
               break;
            case 0:
            default:
               modifier = 1;
               break;
            case 1:
               modifier = 0;
         }
      }

      int mIndex = this.getModifierIndex(modifier);
      int charsLength = (length - 1) / byteStructure;
      if (charsLength <= _modifiers.length) {
         return this.bytesToString(data, (mIndex < charsLength ? mIndex : charsLength - 1) * byteStructure + 1, 1, byteStructure);
      }

      int bIdx = 0;
      int eIdx = byteStructure == 1 ? data[bIdx + 1] : this.bytesToInt(data[bIdx * 2 + 1], data[bIdx * 2 + 2]);

      for (int i = 0; i < _modifiers.length; i++) {
         if (bIdx > charsLength || eIdx > charsLength) {
            System.err.println("Error in map file for key code ");
            break;
         }

         try {
            if (i == mIndex) {
               return this.bytesToString(data, (bIdx + 1) * byteStructure + 1, eIdx - bIdx, byteStructure);
            }
         } catch (Exception e) {
            e.printStackTrace();
            return null;
         }

         bIdx = eIdx + 1;
         if (bIdx >= charsLength) {
            break;
         }

         eIdx = bIdx + (byteStructure == 1 ? data[bIdx + 1] : this.bytesToInt(data[bIdx * 2 + 1], data[bIdx * 2 + 2]));
      }

      return this.getDefBuffer();
   }

   private StringBuffer bytesToString(byte[] data, int start, int len, byte byteStructure) {
      if (len == 0) {
         return this.getDefBuffer();
      }

      if (this._bytes2StringCache == null) {
         this._bytes2StringCache = new StringBuffer();
      }

      this._bytes2StringCache.setLength(len);
      if (byteStructure == 1) {
         for (int i = 0; i < len; i++) {
            this._bytes2StringCache.setCharAt(i, (char)(data[start + i] & 0xFF));
         }
      } else {
         for (int i = 0; i < len; i++) {
            int pos = start + i * 2;
            this._bytes2StringCache.setCharAt(i, (char)this.bytesToInt(data[pos], data[pos + 1]));
         }
      }

      return this._bytes2StringCache;
   }

   public static int convertStatusToModifiers(int status) {
      if ((status & 32768) != 0) {
         return 32768;
      }

      status &= 28695;
      if (status == 0) {
         return 0;
      }

      if ((status & 4) != 0 && (status & 16) != 0) {
         status &= -7;
      }

      if ((status & 4) != 0) {
         status &= -3;
         if ((status & 1) != 0) {
            status &= -5;
         }
      }

      if ((status & 16) != 0) {
         status &= -2;
         if ((status & 2) != 0) {
            status &= -17;
         }
      }

      int modifier = 0;
      if ((status & 2) != 0) {
         modifier |= 1;
      }

      if ((status & 1) != 0) {
         modifier |= 8;
      }

      if ((status & 4) != 0) {
         modifier |= 2;
      }

      if ((status & 16) != 0) {
         modifier |= 10;
      }

      return modifier;
   }

   public static int convertModifiersToStatus(int modifier) {
      int status = 0;
      if ((modifier & 1) != 0) {
         status |= 2;
      }

      if ((modifier & 8) != 0) {
         status |= 1;
      }

      return status;
   }

   private StringBuffer getDefBuffer() {
      if (this._defResult.length() != 1) {
         this._defResult = new StringBuffer("");
      }

      return this._defResult;
   }

   public Locale getLocale() {
      return this._locale;
   }

   private static boolean isExists(String mapName) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static String getMapID(int aKeyboardId, String aKeyboardType, Locale anInputLocale, String mapLocation, boolean useDefault) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static SLKeyLayout getLayout(
      Locale originatingLocale, boolean reduced, int aKeyboardId, String aKeyboardType, Locale anInputLocale, String mapLocation, boolean useDefault
   ) {
      String mapID = getMapID(aKeyboardId, aKeyboardType, anInputLocale, mapLocation, useDefault);
      byte[] data = null;
      SLKeyLayout result = null;
      if (mapID != null) {
         data = Utils.loadRimRes(mapLocation, MAP_COMMON_PREFIX + mapID + MAP_COMMON_SUFFIX);
         if (data != null) {
            result = new SLKeyLayout(originatingLocale, reduced, (byte)0, data);
            result.setNameID(mapID);
         }
      }

      return result;
   }

   public static InputStream getLayoutData(int aKeyboardId, String aKeyboardType, Locale anInputLocale, String mapLocation, boolean useDefault) {
      String mapID = getMapID(aKeyboardId, aKeyboardType, anInputLocale, mapLocation, useDefault);
      byte[] data = null;
      if (mapID != null) {
         data = Utils.loadRimRes(mapLocation, MAP_COMMON_PREFIX + mapID + MAP_COMMON_SUFFIX);
      }

      return data == null ? null : new ByteArrayInputStream(data);
   }

   public static String getKeyboardType(int aLocaleCode) {
      String ret = "qwerty";
      switch (aLocaleCode & -65536) {
         case 1684340736:
            ret = "qwertz";
         default:
            return ret;
         case 1718747136:
            return "azerty";
      }
   }
}
