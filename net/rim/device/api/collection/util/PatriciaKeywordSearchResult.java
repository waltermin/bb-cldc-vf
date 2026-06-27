package net.rim.device.api.collection.util;

import net.rim.device.api.util.BitSet;

public class PatriciaKeywordSearchResult {
   public PatriciaKeywordFilterList _list;
   public byte[] _hitCount;
   public int _wordNumber;
   public BitSet _primarySet;
   public BitSet _theSet;

   public PatriciaKeywordSearchResult(PatriciaKeywordFilterList var1, byte[] var2, BitSet var3) {
      this._list = var1;
      this._hitCount = var2;
      this._primarySet = var3;
   }
}
