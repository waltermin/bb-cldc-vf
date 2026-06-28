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

   public PinInfo(PinInfo info) {
   }

   public void getRecord(StringBuffer result) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int length() {
      return this._length;
   }

   public void setWord(String word) {
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

   public void set(PinInfo info) {
      this._word = info._word;
      this._length = info._length;
      System.arraycopy(info._syllables, 0, this._syllables, 0, this._length);
      System.arraycopy(info._tones, 0, this._tones, 0, this._length);
      System.arraycopy(info._indexes, 0, this._indexes, 0, this._length);
   }

   public void setSingle(PinInfo info, int position) {
      this._length = 1;
      System.arraycopy(info._syllables, position, this._syllables, 0, this._length);
      System.arraycopy(info._tones, position, this._tones, 0, this._length);
      System.arraycopy(info._indexes, position, this._indexes, 0, this._length);
   }

   public void set(char[] syllables, byte[] tones, byte[] indexes, int length) {
      this._length = length;
      System.arraycopy(syllables, 0, this._syllables, 0, this._length);
      System.arraycopy(tones, 0, this._tones, 0, this._length);
      System.arraycopy(indexes, 0, this._indexes, 0, this._length);
   }

   public void setFR(char[] syllables, byte[] tones, byte[] indexes, int length) {
      System.arraycopy(syllables, 0, this._syllables, this._length, length);
      System.arraycopy(tones, 0, this._tones, this._length, length);
      System.arraycopy(indexes, 0, this._indexes, this._length, length);
      this._length += length;
   }

   @Override
   public boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }
}
