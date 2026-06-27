package net.rim.tid.im;

public class AwnnInterface {
   private int _encoding;
   private byte[] _encodingData;
   private int _encodingDataOffset;
   public static final int LEARN_DIC_BAD_SIZE;
   public static final int LEARN_DIC_CORRUPTED;
   public static final int OUT_OF_MEMORY;
   public static final int BAD_ARGUMENT;
   public static final int CORRUPTED_DIC;
   public static final int CORRUPTED_ENCODING;
   public static final int MISC;
   public static final int RELOAD_USER_DATA;
   public static final int AWNN_POS_MEISI;
   public static final int AWNN_POS_JINMEI;
   public static final int AWNN_POS_CHIMEI;
   public static final int AWNN_POS_KIGOU;
   public static final int VARIANT_SIZE_PERCENT;
   public static final int SEGMENT_NUMBER_PERCENT;
   private static AwnnInterface _instance;
   private static boolean _dicErrorReported;

   private AwnnInterface(int var1, byte[] var2, int var3) {
      this._encoding = var1;
      this._encodingData = var2;
      this._encodingDataOffset = var3;
   }

   private int getEncoding() {
      return this._encoding;
   }

   private int getEncodingDataOffset() {
      return this._encodingDataOffset;
   }

   private byte[] getEncodingData() {
      return this._encodingData;
   }

   public static AwnnInterface getInstance() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static int init() {
      return initialize();
   }

   public static int convert(StringBuffer var0, int var1, int var2, int var3, int var4, int[] var5, char[] var6, int[] var7, byte[] var8) {
      if (var0 != null
         && var2 <= var0.length()
         && var2 >= 0
         && var1 >= 0
         && var4 >= 0
         && var2 >= var1 + var4
         && var2 <= getMaxInputLength() / 2
         && var3 >= 0
         && var3 < getMaxSegmentNumber()
         && var5 != null
         && var6 != null
         && var7 != null
         && var8 != null) {
         AwnnInterface var9 = getInstance();
         if (var9 != null) {
            byte[] var10 = var9.getEncodingData();
            int var11 = var9.getEncoding();
            if (var10 != null && var11 != 0) {
               return convertWithEncoding(var0, var1, var2, var3, var4, var5, var6, var7, var8, var11, var10, var9.getEncodingDataOffset());
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int getPredictions(StringBuffer var0, int var1, int var2, char[] var3, byte[] var4, byte[] var5, boolean var6) {
      if (var0 != null
         && var2 >= 0
         && var1 >= 0
         && var0.length() >= var1 + var2
         && var2 <= getMaxInputLength() / 2
         && var3 != null
         && var4 != null
         && var5 != null) {
         AwnnInterface var7 = getInstance();
         if (var7 != null) {
            byte[] var8 = var7.getEncodingData();
            int var9 = var7.getEncoding();
            if (var8 != null && var9 != 0) {
               return getPredictionsWithEncoding(var0, var1, var2, var3, var4, var5, var6, var9, var8, var7.getEncodingDataOffset());
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int getVariants(byte[] var0, int var1, char[] var2, byte[] var3, byte[] var4) {
      if (var0 != null && var1 >= 0 && var1 < getMaxSegmentNumber() && var2 != null && var3 != null && var4 != null) {
         AwnnInterface var5 = getInstance();
         if (var5 != null) {
            byte[] var6 = var5.getEncodingData();
            int var7 = var5.getEncoding();
            if (var6 != null && var7 != 0) {
               return getVariantsWithEncoding(var0, var1, var2, var3, var4, var7, var6, var5.getEncodingDataOffset());
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int getDicWords(int var0, int var1, char[] var2, byte[] var3, char[] var4, byte[] var5) {
      if (var1 > 0 && var2 != null && var3 != null && var4 != null && var5 != null) {
         AwnnInterface var6 = getInstance();
         if (var6 != null) {
            byte[] var7 = var6.getEncodingData();
            int var8 = var6.getEncoding();
            if (var7 != null && var8 != 0) {
               return getDicWordsWithEncoding(var0, var1, var2, var3, var4, var5, var8, var7, var6.getEncodingDataOffset());
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int getWordFrom_NJ_RESULT(byte[] var0, char[] var1, char[] var2) {
      if (var0 != null && var1 != null && var2 != null) {
         AwnnInterface var3 = getInstance();
         if (var3 != null) {
            byte[] var4 = var3.getEncodingData();
            int var5 = var3.getEncoding();
            if (var4 != null && var5 != 0) {
               return getWordFromNjResultWithEncoding(var0, var1, var2, var5, var4, var3.getEncodingDataOffset());
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static native int loadLearnDic(byte[] var0);

   public static native int loadAddrBookLearnDic(byte[] var0);

   public static native int getLearnDic(byte[] var0);

   public static native int getAddrBookLearnDic(byte[] var0);

   public static int addCustomWord(String var0, String var1, int var2) {
      if (var0 != null && var1 != null && var2 >= 0 && var2 <= 3) {
         AwnnInterface var3 = getInstance();
         if (var3 != null) {
            byte[] var4 = var3.getEncodingData();
            int var5 = var3.getEncoding();
            if (var4 != null && var5 != 0) {
               int var6 = addCustomWordWithEncoding(var0, var1, var2, var5, var4, var3.getEncodingDataOffset());
               if (var6 < 0) {
                  handleAwnnDicError(getLearningDictionaryId());
               }

               return var6;
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int addAddrWord(String var0, String var1, int var2) {
      if (var0 != null && var1 != null) {
         AwnnInterface var3 = getInstance();
         if (var3 != null) {
            byte[] var4 = var3.getEncodingData();
            int var5 = var3.getEncoding();
            if (var4 != null && var5 != 0) {
               int var6 = addAddrWordWithEncoding(var0, var1, var2, var5, var4, var3.getEncodingDataOffset());
               if (var6 < 0) {
                  handleAwnnDicError(getLearningDictionaryId());
               }

               return var6;
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int addLearnedWord(StringBuffer var0, StringBuffer var1, int var2, int var3) {
      if (var0 != null && var1 != null && var2 >= 0 && var2 <= 3) {
         AwnnInterface var4 = getInstance();
         if (var4 != null) {
            byte[] var5 = var4.getEncodingData();
            int var6 = var4.getEncoding();
            if (var5 != null && var6 != 0) {
               int var7 = addLearnedWordWithEncoding(var0, var1, var2, var3, var6, var5, var4.getEncodingDataOffset());
               if (var7 < 0) {
                  handleAwnnDicError(getLearningDictionaryId());
               }

               return var7;
            }
         }

         return 4194304;
      } else {
         return 1048576;
      }
   }

   public static int handleAwnnDicError(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static int deleteWord(String var0, String var1, int var2) {
      if (var0 == null) {
         return 1048576;
      }

      AwnnInterface var3 = getInstance();
      if (var3 != null) {
         byte[] var4 = var3.getEncodingData();
         int var5 = var3.getEncoding();
         if (var4 != null && var5 != 0) {
            int var6 = deleteWordWithEncoding(var0, var1, var5, var4, var3.getEncodingDataOffset(), var2);
            if (var6 < 0) {
               handleAwnnDicError(getLearningDictionaryId());
            }

            return var6;
         }
      }

      return 4194304;
   }

   public static int njlearn(byte[] var0, int var1, int var2) {
      int var3 = learn(var0, var1, var2);
      if (var3 < 0) {
         var3 = handleAwnnDicError(getLearningDictionaryId());
      }

      return var3;
   }

   private static native int learn(byte[] var0, int var1, int var2);

   public static native int undoLearn(int var0);

   private static native int initialize();

   private static native int convertWithEncoding(
      StringBuffer var0, int var1, int var2, int var3, int var4, int[] var5, char[] var6, int[] var7, byte[] var8, int var9, byte[] var10, int var11
   );

   private static native int getVariantsWithEncoding(byte[] var0, int var1, char[] var2, byte[] var3, byte[] var4, int var5, byte[] var6, int var7);

   private static native int getPredictionsWithEncoding(
      StringBuffer var0, int var1, int var2, char[] var3, byte[] var4, byte[] var5, boolean var6, int var7, byte[] var8, int var9
   );

   private static native int getDicWordsWithEncoding(int var0, int var1, char[] var2, byte[] var3, char[] var4, byte[] var5, int var6, byte[] var7, int var8);

   private static native int addCustomWordWithEncoding(String var0, String var1, int var2, int var3, byte[] var4, int var5);

   private static native int addAddrWordWithEncoding(String var0, String var1, int var2, int var3, byte[] var4, int var5);

   private static native int addLearnedWordWithEncoding(StringBuffer var0, StringBuffer var1, int var2, int var3, int var4, byte[] var5, int var6);

   private static native int deleteWordWithEncoding(String var0, String var1, int var2, byte[] var3, int var4, int var5);

   public static native int getMaxInputLength();

   public static native int getMaxVariantNumber();

   public static native int get_NJ_RESULT_size();

   public static native int getMaxSegmentNumber();

   public static native int getLearningDictionarySize();

   public static native int getUserDictionarySize();

   public static native int getUserDictionaryId();

   public static native int getAddrBookDictionaryId();

   public static native int getLearningDictionaryId();

   public static native int checkUserDictionary(int var0);

   public static native int getStaticBufferVerificationVar();

   public static native int resetUserDictionary(int var0);

   private static native int getWordFromNjResultWithEncoding(byte[] var0, char[] var1, char[] var2, int var3, byte[] var4, int var5);
}
