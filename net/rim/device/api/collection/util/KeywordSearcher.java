package net.rim.device.api.collection.util;

import net.rim.device.api.collection.FilterStatusListener;
import net.rim.device.api.util.BitSet;
import net.rim.tid.im.conv.repository.IDataSearchRepository;
import net.rim.vm.WeakReference;

public class KeywordSearcher implements IDataSearchRepository {
   private AbstractKeywordFilterList _list;
   protected FilterStatusListener _listener;
   protected boolean _interrupted;
   protected WeakReference _wordBufferWR = (WeakReference)(new Object(null));

   protected AbstractKeywordFilterList getList() {
      return this._list;
   }

   protected void halt() {
      this._interrupted = true;
      this._list.haltSearch();
   }

   protected synchronized BitSet search(String[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void search(String var1, FilterStatusListener var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void search(String[] var1, FilterStatusListener var2) {
      throw new RuntimeException("cod2jar: array creation");
   }

   protected synchronized void search(String[][][] var1, FilterStatusListener var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setFilterStatusListener(FilterStatusListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public synchronized void waitForComplete() {
   }

   @Override
   public BitSet searchPrefixes(String[] var1) {
      return this.search(var1);
   }

   private BitSet search(String[] var1, KeywordPrefixSearchResult var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected KeywordSearcher(AbstractKeywordFilterList var1) {
      this._list = var1;
   }
}
