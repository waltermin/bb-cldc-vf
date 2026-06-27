package net.rim.device.api.collection.util;

import net.rim.device.api.util.Persistable;

public final class PrefixKeywordFilterListData implements KeywordFilterListData, Persistable {
   SparseList _objectList;
   KeywordPrefixManager _prefixList;
   BigIntVector _orderList;
   boolean _firstWordBias;
   int _version;

   PrefixKeywordFilterListData(SparseList var1, KeywordPrefixManager var2, BigIntVector var3, boolean var4, int var5) {
      this._objectList = var1;
      this._prefixList = var2;
      this._orderList = var3;
      this._firstWordBias = var4;
      this._version = var5;
   }
}
