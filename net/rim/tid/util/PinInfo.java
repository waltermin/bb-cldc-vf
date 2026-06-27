package net.rim.tid.util;

public class PinInfo {
   private String _word;
   private char[] _syllables;
   private byte[] _tones;
   private byte[] _indexes;
   private int _length;
   private static final int MAX_LENGTH;

   public PinInfo() {
   }

   public PinInfo(PinInfo var1) {
   }

   public void getRecord(StringBuffer var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int length() {
      return this._length;
   }

   public void setWord(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getWord() {
      return this._word;
   }

   public char[] getPinIds() {
      return this._syllables;
   }

   public byte[] getTones() {
      return this._tones;
   }

   public byte[] getCharsIndexes() {
      return this._indexes;
   }

   public void set(PinInfo var1) {
      this._word = var1._word;
      this._length = var1._length;
      System.arraycopy(var1._syllables, 0, this._syllables, 0, this._length);
      System.arraycopy(var1._tones, 0, this._tones, 0, this._length);
      System.arraycopy(var1._indexes, 0, this._indexes, 0, this._length);
   }

   public void setSingle(PinInfo var1, int var2) {
      this._length = 1;
      System.arraycopy(var1._syllables, var2, this._syllables, 0, this._length);
      System.arraycopy(var1._tones, var2, this._tones, 0, this._length);
      System.arraycopy(var1._indexes, var2, this._indexes, 0, this._length);
   }

   public void set(char[] var1, byte[] var2, byte[] var3, int var4) {
      this._length = var4;
      System.arraycopy(var1, 0, this._syllables, 0, this._length);
      System.arraycopy(var2, 0, this._tones, 0, this._length);
      System.arraycopy(var3, 0, this._indexes, 0, this._length);
   }

   public void setFR(char[] var1, byte[] var2, byte[] var3, int var4) {
      System.arraycopy(var1, 0, this._syllables, this._length, var4);
      System.arraycopy(var2, 0, this._tones, this._length, var4);
      System.arraycopy(var3, 0, this._indexes, this._length, var4);
      this._length += var4;
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
