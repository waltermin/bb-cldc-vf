package net.rim.device.api.collection.util;

import net.rim.device.api.util.BitSet;
import net.rim.device.api.util.ByteVector;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.api.util.Persistable;

public class KeywordPrefixManager implements Persistable {
   private BigIntVector _prefixes;
   private BigLongVector _prefixesLong;
   private ByteVector _intWordIndexes;
   private ByteVector _longWordIndexes;
   private BitSet _validPrefixTuples;
   private BitSet _validTuples;
   public byte DEFAULT_INDEX = 127;
   private int[] _tmpStartIndices = new int[64];
   private int[] _tmpEndIndices = new int[64];
   private boolean _sorted;
   private boolean _sortedLong;
   private int _maxId;
   private boolean _haltSearch;
   private static final int MASK_BIT_COUNT;
   private static final long MASK_BIT_COUNT_LONG;
   private static final int PREFIX_SHIFT;
   private static final int PREFIX_MASK_1;
   private static final long PREFIX_MASK_1_LONG;
   private static final int PREFIX_MASK_2;
   private static final long PREFIX_MASK_2_LONG;
   private static final int PREFIX_MASK_3;
   private static final long PREFIX_MASK_3_LONG;
   private static final int PREFIX_MASK;
   private static final long PREFIX_MASK_LONG;
   private static final int FIRST_WORD_MASK;
   private static final int ID_MASK;
   private static final int PREFIX_LENGTH;
   private static final byte[] charUnifier;
   private static final char[] PREFIX_CODE_TO_CHAR_MAP;

   public KeywordPrefixManager() {
      this.reset();
   }

   private int getKey(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int getKey(char[] var1, int var2, int var3) {
      if (var1.length < var3 - var2) {
         throw new Object();
      } else {
         return this.getKey0((char)(var3 > 0 ? var1[var2] : 0), (char)(var3 > 1 ? var1[var2 + 1] : 0), (char)(var3 > 2 ? var1[var2 + 2] : 0));
      }
   }

   private int getKey0(char var1, char var2, char var3) {
      if (var1 > 255) {
         var1 = 0;
      }

      if (var2 > 255) {
         var2 = 0;
      }

      if (var3 > 255) {
         var3 = 0;
      }

      return charUnifier[var1] << 26 | charUnifier[var2] << 21 | charUnifier[var3] << 16;
   }

   private long getKeyLong(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private long getKeyLong(char[] var1, int var2, int var3) {
      if (var1.length < var3 - var2) {
         throw new Object();
      } else {
         return this.getKeyLong0(var3 > 0 ? var1[var2] : '\u0000', var3 > 1 ? var1[var2 + 1] : '\u0000', var3 > 2 ? var1[var2 + 2] : '\u0000');
      }
   }

   private long getKeyLong0(char var1, char var2, char var3) {
      var1 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(var1, 1701707776));
      var2 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(var2, 1701707776));
      var3 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(var3, 1701707776));
      return (long)(var1 < 255 ? charUnifier[var1] : var1) << 48
         | (long)(var2 < 255 ? charUnifier[var2] : var2) << 32
         | (long)(var3 < 255 ? charUnifier[var3] : var3) << 16;
   }

   private int nextKey(int var1) {
      if ((var1 & 2031616) != 0) {
         return var1 + 65536;
      } else {
         return (var1 & 65011712) != 0 ? var1 + 2097152 : var1 + 67108864;
      }
   }

   private long nextKey(long var1) {
      if ((var1 & 4294901760L) != 0) {
         return var1 + 65536;
      } else {
         return (var1 & 281470681743360L) != 0 ? var1 + 4294967296L : var1 + 281474976710656L;
      }
   }

   public void reset() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getPrefixCount() {
      return this._prefixes.size() + this._prefixesLong.size();
   }

   public void addWords(int var1, String[] var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public void addWords(int var1, String var2, boolean var3) {
      this.addWords(var1, var2, var3, this.DEFAULT_INDEX);
   }

   public void addWords(int var1, String var2, boolean var3, byte var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void addWord(int var1, String var2, boolean var3) {
      this.addWord(var1, var2, var3, this.DEFAULT_INDEX);
   }

   public void addWord(int var1, String var2, boolean var3, byte var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int getTuple(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void addTuples(String var1, int var2, int var3) {
      if (var3 >= 2) {
         int var4 = this.getTuple(var1, var2, 2);
         if (var4 != 0) {
            this._validPrefixTuples.fastSet(var4);
         }

         if (var3 >= 3) {
            var4 = this.getTuple(var1, var2, var3);
            if (var4 != 0) {
               this._validPrefixTuples.fastSet(var4);
            }

            while (var3 > 3) {
               var4 = this.getTuple(var1, ++var2, --var3);
               if (var4 != 0) {
                  this._validTuples.fastSet(var4);
               }
            }
         }
      }
   }

   private boolean allTuplesPresent(String[] var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void add(int var1, byte var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void add(long var1, byte var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void delete(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void sort() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void haltSearch() {
      this._haltSearch = true;
   }

   public String[] getLongWords(String[] var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public boolean getMatch(char[] var1, int var2, int var3, int[] var4) {
      if (var4[1] == -1) {
         this.rangeSearch(var1, var2, var3, var4);
      }

      if (var4[1] == -1 || var4[2] == -1 || var4[1] == var4[2]) {
         return false;
      } else if (var4[0] == 0) {
         var4[3] = this._prefixes.elementAt(var4[1]) & 32767;
         var4[4] = this._intWordIndexes.elementAt(var4[1]);
         return true;
      } else {
         var4[3] = (int)this._prefixesLong.elementAt(var4[1]) & 32767;
         var4[4] = this._longWordIndexes.elementAt(var4[1]);
         return true;
      }
   }

   private void rangeSearch(char[] var1, int var2, int var3, int[] var4) {
      int var5 = -1;
      int var6 = -1;
      if (!this._sorted || !this._sortedLong) {
         this.sort();
      }

      if (var4[0] == 0) {
         int var7 = this.getKey(var1, var2, var3);
         var5 = this._prefixes.binarySearch(var7);
         var6 = this._prefixes.binarySearch(this.nextKey(var7));
         if (var5 < 0) {
            var5 = -(var5 + 1);
         }

         if (var6 < 0) {
            var6 = -(var6 + 1);
         }

         if (var6 < var5) {
            var6 = this._prefixes.size();
         }
      } else {
         long var11 = this.getKeyLong(var1, var2, var3);
         var5 = this._prefixesLong.binarySearch(var11);
         var6 = this._prefixesLong.binarySearch(this.nextKey(var11));
         if (var5 < 0) {
            var5 = -(var5 + 1);
         }

         if (var6 < 0) {
            var6 = -(var6 + 1);
         }

         if (var6 < var5) {
            var6 = this._prefixesLong.size();
         }
      }

      var4[1] = var5;
      var4[2] = var6;
   }

   public KeywordPrefixSearchResult search(String[] var1) {
      return this.search(var1, null);
   }

   public KeywordPrefixSearchResult search(String[] var1, KeywordPrefixCache var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void adjustWideCharacterSearch(String var1, int var2, BitSet var3, BitSet var4, int var5, byte[] var6) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static char getPrefixChar(int var0) {
      return var0 >= 0 && var0 <= 31 ? PREFIX_CODE_TO_CHAR_MAP[var0] : '\u0000';
   }

   public static boolean startsWithUsingMapping(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
