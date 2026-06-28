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

   private int getKey(String str, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int getKey(char[] data, int offset, int length) {
      if (data.length < length - offset) {
         throw new Object();
      } else {
         return this.getKey0((char)(length > 0 ? data[offset] : 0), (char)(length > 1 ? data[offset + 1] : 0), (char)(length > 2 ? data[offset + 2] : 0));
      }
   }

   private int getKey0(char letter1, char letter2, char letter3) {
      if (letter1 > 255) {
         letter1 = 0;
      }

      if (letter2 > 255) {
         letter2 = 0;
      }

      if (letter3 > 255) {
         letter3 = 0;
      }

      return charUnifier[letter1] << 26 | charUnifier[letter2] << 21 | charUnifier[letter3] << 16;
   }

   private long getKeyLong(String str, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private long getKeyLong(char[] data, int offset, int length) {
      if (data.length < length - offset) {
         throw new Object();
      } else {
         return this.getKeyLong0(length > 0 ? data[offset] : '\u0000', length > 1 ? data[offset + 1] : '\u0000', length > 2 ? data[offset + 2] : '\u0000');
      }
   }

   private long getKeyLong0(char letter1, char letter2, char letter3) {
      letter1 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(letter1, 1701707776));
      letter2 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(letter2, 1701707776));
      letter3 = CharacterUtilities.getOriginal(CharacterUtilities.toLowerCase(letter3, 1701707776));
      return (long)(letter1 < 255 ? charUnifier[letter1] : letter1) << 48
         | (long)(letter2 < 255 ? charUnifier[letter2] : letter2) << 32
         | (long)(letter3 < 255 ? charUnifier[letter3] : letter3) << 16;
   }

   private int nextKey(int key) {
      if ((key & 2031616) != 0) {
         return key + 65536;
      } else {
         return (key & 65011712) != 0 ? key + 2097152 : key + 67108864;
      }
   }

   private long nextKey(long key) {
      if ((key & 4294901760L) != 0) {
         return key + 65536;
      } else {
         return (key & 281470681743360L) != 0 ? key + 4294967296L : key + 281474976710656L;
      }
   }

   public void reset() {
      this._prefixes = new BigIntVector(64);
      this._prefixesLong = new BigLongVector(64);
      synchronized (this._prefixes) {
         this._sorted = false;
      }

      synchronized (this._prefixesLong) {
         this._sortedLong = false;
      }

      this._intWordIndexes = new ByteVector(64);
      this._longWordIndexes = new ByteVector(64);
      this._validTuples = new BitSet(32768);
      this._validPrefixTuples = new BitSet(32768);
   }

   public int getPrefixCount() {
      return this._prefixes.size() + this._prefixesLong.size();
   }

   public void addWords(int id, String[] words) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public void addWords(int id, String words, boolean firstFlag) {
      this.addWords(id, words, firstFlag, this.DEFAULT_INDEX);
   }

   public void addWords(int id, String words, boolean firstFlag, byte propertyIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void addWord(int id, String word, boolean firstFlag) {
      this.addWord(id, word, firstFlag, this.DEFAULT_INDEX);
   }

   public void addWord(int id, String word, boolean firstFlag, byte wordIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int getTuple(String word, int start, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void addTuples(String word, int start, int length) {
      if (length >= 2) {
         int tuple = this.getTuple(word, start, 2);
         if (tuple != 0) {
            this._validPrefixTuples.fastSet(tuple);
         }

         if (length >= 3) {
            tuple = this.getTuple(word, start, length);
            if (tuple != 0) {
               this._validPrefixTuples.fastSet(tuple);
            }

            while (length > 3) {
               tuple = this.getTuple(word, ++start, --length);
               if (tuple != 0) {
                  this._validTuples.fastSet(tuple);
               }
            }
         }
      }
   }

   private boolean allTuplesPresent(String[] words) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void add(int key, byte wordIndex) {
      synchronized (this._prefixes) {
         if (!this._sorted) {
            this._prefixes.addElement(key);
            this._intWordIndexes.addElement(wordIndex);
         } else {
            int index = this._prefixes.binarySearch(key);
            if (index < 0) {
               index = -(index + 1);
            }

            this._prefixes.insertElementAt(key, index);
            this._intWordIndexes.insertElementAt(wordIndex, index);
         }

         int id = key & 32767;
         if (id > this._maxId) {
            this._maxId = id;
         }
      }
   }

   private void add(long key, byte wordIndex) {
      synchronized (this._prefixesLong) {
         if (!this._sortedLong) {
            this._prefixesLong.addElement(key);
            this._longWordIndexes.addElement(wordIndex);
         } else {
            int index = this._prefixesLong.binarySearch(key);
            if (index < 0) {
               index = -(index + 1);
            }

            this._prefixesLong.insertElementAt(key, index);
            this._longWordIndexes.insertElementAt(wordIndex, index);
         }

         int id = (int)key & 32767;
         if (id > this._maxId) {
            this._maxId = id;
         }
      }
   }

   public void delete(int id) {
      synchronized (this._prefixes) {
         int src = 0;
         int prefixCount = this._prefixes.size();
         this._maxId = 0;

         while (src < prefixCount) {
            int tmpId = this._prefixes.elementAt(src) & 32767;
            if (tmpId == id) {
               this._prefixes.removeElementAt(src);
               this._intWordIndexes.removeElementAt(src);
               prefixCount--;
            } else {
               if (tmpId > this._maxId) {
                  this._maxId = tmpId;
               }

               src++;
            }
         }
      }

      synchronized (this._prefixesLong) {
         int prefixCount = this._prefixesLong.size();
         int src = 0;

         while (src < prefixCount) {
            int tmpId = (int)this._prefixesLong.elementAt(src) & 32767;
            if (tmpId == id) {
               this._prefixesLong.removeElementAt(src);
               this._longWordIndexes.removeElementAt(src);
               prefixCount--;
            } else {
               if (tmpId > this._maxId) {
                  this._maxId = tmpId;
               }

               src++;
            }
         }
      }
   }

   protected void sort() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public void haltSearch() {
      this._haltSearch = true;
   }

   public String[] getLongWords(String[] words) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public boolean getMatch(char[] prefixes, int offset, int length, int[] resultData) {
      if (resultData[1] == -1) {
         this.rangeSearch(prefixes, offset, length, resultData);
      }

      if (resultData[1] == -1 || resultData[2] == -1 || resultData[1] == resultData[2]) {
         return false;
      } else if (resultData[0] == 0) {
         resultData[3] = this._prefixes.elementAt(resultData[1]) & 32767;
         resultData[4] = this._intWordIndexes.elementAt(resultData[1]);
         return true;
      } else {
         resultData[3] = (int)this._prefixesLong.elementAt(resultData[1]) & 32767;
         resultData[4] = this._longWordIndexes.elementAt(resultData[1]);
         return true;
      }
   }

   private void rangeSearch(char[] prefixes, int offset, int length, int[] resultData) {
      int low = -1;
      int high = -1;
      if (!this._sorted || !this._sortedLong) {
         this.sort();
      }

      if (resultData[0] == 0) {
         int key = this.getKey(prefixes, offset, length);
         low = this._prefixes.binarySearch(key);
         high = this._prefixes.binarySearch(this.nextKey(key));
         if (low < 0) {
            low = -(low + 1);
         }

         if (high < 0) {
            high = -(high + 1);
         }

         if (high < low) {
            high = this._prefixes.size();
         }
      } else {
         long key = this.getKeyLong(prefixes, offset, length);
         low = this._prefixesLong.binarySearch(key);
         high = this._prefixesLong.binarySearch(this.nextKey(key));
         if (low < 0) {
            low = -(low + 1);
         }

         if (high < 0) {
            high = -(high + 1);
         }

         if (high < low) {
            high = this._prefixesLong.size();
         }
      }

      resultData[1] = low;
      resultData[2] = high;
   }

   public KeywordPrefixSearchResult search(String[] words) {
      return this.search(words, null);
   }

   public KeywordPrefixSearchResult search(String[] words, KeywordPrefixCache cache) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void adjustWideCharacterSearch(String word, int offset, BitSet primarySet, BitSet theSet, int wordNumber, byte[] hitCount) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static char getPrefixChar(int prefixCode) {
      return prefixCode >= 0 && prefixCode <= 31 ? PREFIX_CODE_TO_CHAR_MAP[prefixCode] : '\u0000';
   }

   public static boolean startsWithUsingMapping(String string, String prefix) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
