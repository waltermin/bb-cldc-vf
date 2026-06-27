package net.rim.device.api.collection.util;

import net.rim.device.api.collection.BulkUpdateCollectionListener;
import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionWithVersion;
import net.rim.device.api.collection.ReadableList;
import net.rim.vm.WeakReference;

public class PrefixKeywordFilterList extends AbstractKeywordFilterList implements ChainableCollection, CollectionWithVersion, BulkUpdateCollectionListener {
   private PrefixKeywordFilterListData _filterListData;
   protected SparseList _objectList;
   protected KeywordPrefixManager _prefixList;
   protected BigIntVector _orderList;
   protected KeywordIndexerHelper _keywordHelper;
   private boolean _firstWordBias;
   protected WeakReference _keywordsWR;
   private boolean _commitsSuspended;
   private static final int GROW_SIZE;
   protected static final int KEYWORDS_INITIAL_SIZE;

   public int getPrefixCount() {
      return this._prefixList.getPrefixCount();
   }

   protected boolean getCommitsSuspended() {
      return this._commitsSuspended;
   }

   public PrefixKeywordFilterListData getFilterListData() {
      return this._filterListData;
   }

   protected int addToIndex(int var1, Object var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   protected void commit(boolean var1) {
   }

   public boolean matches(Object var1, String var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void endBulkUpdate(Collection var1) {
      this._commitsSuspended = false;
      this.doCommit(var1, true);
   }

   @Override
   public void beginBulkUpdate(Collection var1) {
      this._commitsSuspended = true;
   }

   @Override
   public int getVersion() {
      return this._filterListData._version;
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void persistentContentStateChanged(int var1) {
      super.persistentContentStateChanged(var1);
      if (var1 == 1) {
         this.reload(super._source);
      }
   }

   @Override
   protected void reset() {
      super.reset();
      this.resetFilterResults();
      this._objectList.removeAll();
      this._prefixList.reset();
      this._orderList = (BigIntVector)(new Object(64));
      this._filterListData._orderList = this._orderList;
      this._filterListData._version = -1;
   }

   @Override
   protected void haltSearch() {
      this._prefixList.haltSearch();
   }

   private void reload(ReadableList var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean matches(Object var1) {
      return this.matches(var1, null);
   }

   public PrefixKeywordFilterList(ReadableList var1, KeywordIndexerHelper var2, PrefixKeywordFilterListData var3) {
   }

   @Override
   public KeywordPrefixSearchResult search(String[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Object[] getElements(KeywordPrefixSearchResult var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public PrefixKeywordFilterList(ReadableList var1, KeywordIndexerHelper var2, boolean var3) {
      this(
         var1,
         var2,
         (PrefixKeywordFilterListData)(new Object((SparseList)(new Object()), (KeywordPrefixManager)(new Object()), (BigIntVector)(new Object(64)), var3, 0))
      );
   }

   private boolean doAddCheck(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private int removeFromIndex(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean doRemoveCheck(int var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void reset(Collection var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public PrefixKeywordFilterList(ReadableList var1, KeywordIndexerHelper var2) {
      this(var1, var2, false);
   }

   private void doCommit(Collection var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
