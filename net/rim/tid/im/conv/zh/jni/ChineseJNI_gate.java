package net.rim.tid.im.conv.zh.jni;

import net.rim.device.api.util.Arrays;
import net.rim.tid.itie.LinguisticData;

public class ChineseJNI_gate {
   private byte[][][] _wordlist;
   private int[] _indexes = new int[0];
   private boolean _isNativeSupportBuilt = true;
   private static final long CH_JNI_GATE_REGISTRY_NAME;
   private static ChineseJNI_gate _this;

   public static ChineseJNI_gate getChineseJNIGate() {
      return _this;
   }

   public boolean isNativeSupportBuilt() {
      return this._isNativeSupportBuilt;
   }

   public boolean setLocale(int var1) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public boolean initWordlist(LinguisticData var1) {
      boolean var2 = var1.getType() >> 4 == 1;
      int var3 = initWordlistReader((byte[][][])var1.getData(), var2);
      if (var3 != 0) {
         byte[][] var4 = var1.getData();
         if (!var2 && this._wordlist.length != 0) {
            Arrays.insertAt(this._indexes, var4.length, 0);

            for (int var6 = 0; var6 < var4.length; var6++) {
               Arrays.insertAt(this._wordlist, var4[var6], var6);
            }
         } else {
            Arrays.add(this._indexes, var4.length);

            for (int var5 = 0; var5 < var4.length; var5++) {
               Arrays.add(this._wordlist, var4[var5]);
            }
         }
      }

      return var3 != 0;
   }

   private void removeWordlist(int var1) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public int unloadLinguisticData(int var1) {
      byte var2 = 2;
      if (removeWordlistReader(var1)) {
         this.removeWordlist(var1);
         Arrays.removeAt(this._indexes, var1);
         var2 = 1;
      }

      return var2;
   }

   private native void init();

   private ChineseJNI_gate() {
   }

   private static native boolean setLocaleToNative(int var0);

   public static native int getPredictiveSylCandidates(int[] var0, int var1, char[] var2);

   public static native void enableFuzzySyllable(char var0, boolean var1);

   public static native boolean checkFuzzy(char var0, byte[] var1, int var2);

   public static native char findBopomofoSyllable(int var0, int var1);

   public static native boolean checkPinyinChar(char[] var0, byte[] var1, int var2, int var3);

   public static native boolean isPinyinSyllableExist(char var0, int var1);

   public static native int getChineseCharactersForSyllable(int var0, int var1, int[] var2, char[] var3, int var4);

   public static native int getMaxOneCharWordFreq(int var0, int var1);

   public static native int getMaxFrequencyFor(int[] var0, int var1);

   public static native int getIndexOfCharacterForPin(char var0, int[] var1, char var2);

   public static native char getCandidateByIndex(char var0, byte var1, byte var2);

   public static native int getPinyinBufferByIndex(char var0, byte[] var1);

   public static native boolean isValidFuzzyPrefixFor(char var0, byte[] var1, int var2);

   private static native int initWordlistReader(byte[][][] var0, boolean var1);

   private static native boolean removeWordlistReader(int var0);

   public static native boolean isVariantExist(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5);

   public static native Object getWords(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5);

   public static int getPredictiveWords(
      char[] var0,
      byte[] var1,
      byte[] var2,
      char[] var3,
      int var4,
      int var5,
      int var6,
      int[] var7,
      int var8,
      char[] var9,
      byte[] var10,
      byte[] var11,
      char[] var12,
      byte[] var13,
      byte[] var14,
      boolean var15,
      boolean var16,
      boolean var17
   ) {
      return getPredictiveWords(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, null, var11, var12, var13, var14, var15, var16, var17);
   }

   public static native int getPredictiveWords(
      char[] var0,
      byte[] var1,
      byte[] var2,
      char[] var3,
      int var4,
      int var5,
      int var6,
      int[] var7,
      int var8,
      char[] var9,
      byte[] var10,
      byte[] var11,
      byte[] var12,
      char[] var13,
      byte[] var14,
      byte[] var15,
      boolean var16,
      boolean var17,
      boolean var18
   );

   public static native int getWordsFor(
      char[] var0, byte[] var1, byte[] var2, int var3, int var4, char[] var5, byte[] var6, byte[] var7, byte[] var8, boolean var9
   );

   public static native void initLearningWordlist(byte[] var0);

   public static native int loadLearningWordlist(byte[] var0);

   public static native void clearLearningWordlist(byte[] var0);

   public static native int modifyData(byte[] var0, int var1, boolean var2, boolean var3, byte[] var4, byte[] var5);

   public static native Object getAllWords(byte[] var0);

   public static native int getLearningWordlistSize(byte[] var0);

   public static int getSegmentation(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5, byte[] var6) {
      return getSegmentation(var0, var1, var2, var3, var4, var5, var6, true);
   }

   public static int getSegmentation(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5, byte[] var6, byte[] var7) {
      return getSegmentation(var0, var1, var2, var3, var4, var5, var6, var7, true);
   }

   public static native int getSegmentation(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5, byte[] var6, boolean var7);

   public static native int getSegmentation(char[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5, byte[] var6, byte[] var7, boolean var8);

   public static native void setAlgorithmDirection(boolean var0);

   public static native int getComplexSegmentation(char[] var0, byte[] var1, int var2, byte[] var3, int[] var4, byte[] var5);

   public static native int buildLearningIndex(byte[] var0);

   public static native int getLearningIndex(char[] var0);

   public static native int addLearningEntry(byte[] var0, byte[] var1, int[] var2);

   public static native int eraseLearningDictionaryChunk(byte[] var0, int[] var1);

   public static native int getLearningEntries(byte[] var0, byte[] var1, char[] var2, char[] var3, byte[] var4, int var5);

   public static native int entryExists(byte[] var0, byte[] var1);

   public static native int addLearningEntry(byte[] var0, char[] var1, int[] var2);

   public static native int getLearningEntries(byte[] var0, char[] var1, char[] var2, char[] var3, byte[] var4, int var5);

   public static native int entryExists(byte[] var0, char[] var1);

   public static native int convertIdToIndex(int var0);
}
