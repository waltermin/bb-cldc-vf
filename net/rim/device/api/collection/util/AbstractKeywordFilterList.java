package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.FilterCollection;
import net.rim.device.api.collection.FilterStatusListener;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.api.ui.AccessibleEventDispatcher;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.accessibility.AccessibleValue;
import net.rim.tid.awt.im.InputContext;

public class AbstractKeywordFilterList
   implements KeywordFilterList,
   FilterCollection,
   CollectionEventSource,
   ReadableList,
   PersistentContentListener,
   AccessibleContext {
   private CollectionListenerManager _listeners;
   protected ReadableList _source;
   private KeywordPrefixCache _prefixCache;
   private Object _filterCriteria;
   private String _filterSuffix;
   private Object[] _filteredElements;
   protected KeywordPrefixSearchResult _filterResult;
   private KeywordSearcher _searcher;
   private Object _searchRequestLock;
   private AbstractKeywordFilterList$SearchRequest _currentSearchRequest;
   private AbstractKeywordFilterList$SearchRequest _nextSearchRequest;
   private int _accessibleStateSet = 1;

   protected void clearPrefixCache() {
      if (this._prefixCache != null) {
         this._prefixCache.reset();
      }
   }

   protected void reset() {
      this.clearPrefixCache();
   }

   protected KeywordPrefixCache getPrefixCache() {
      return this._prefixCache;
   }

   protected void setSearcher(KeywordSearcher var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected synchronized void filteringComplete() {
      super.notifyAll();
   }

   public boolean isInProgress() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void accessibleEventOccurred(int var1, Object var2, Object var3, AccessibleContext var4) {
      AccessibleEventDispatcher.dispatchAccessibleEvent(var1, var2, var3, var4);
   }

   protected void removeAccessibleState(int var1) {
      this._accessibleStateSet &= ~var1;
   }

   protected void addAccessibleState(int var1) {
      if (this.isAccessibleStateSet(1)) {
         this.removeAccessibleState(1);
      }

      this._accessibleStateSet |= var1;
   }

   protected void fireReset() {
      this._listeners.fireReset(this);
   }

   protected void fireElementAdded(Object var1) {
      this._listeners.fireElementAdded(this, var1);
   }

   protected void fireElementUpdated(Object var1, Object var2) {
      this._listeners.fireElementUpdated(this, var1, var2);
   }

   protected void fireElementRemoved(Object var1) {
      this._listeners.fireElementRemoved(this, var1);
   }

   protected void haltSearch() {
      throw null;
   }

   public void halt() {
      this._searcher.halt();
   }

   public KeywordPrefixSearchResult search(String[] var1) {
      throw null;
   }

   public Object[] getElements(KeywordPrefixSearchResult var1) {
      throw null;
   }

   protected synchronized void resetFilterResults() {
      this.clearFilteredElementList();
      this._filterCriteria = null;
      this._filterResult = null;
   }

   void setFilterResult(Object var1, KeywordPrefixSearchResult var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void recalculateResults() {
      this.clearFilteredElementList();
      this.buildFilteredElementList();
   }

   protected void clearFilteredElementList() {
      this._filteredElements = null;
   }

   @Override
   public int getIndex(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setSuffix(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getSuffix() {
      return this._filterSuffix;
   }

   @Override
   public KeywordSearcher getSearcher() {
      return this._searcher;
   }

   @Override
   public synchronized void searchPrefixes(String[] var1) {
      KeywordSearcher var2 = this.getSearcher();
      if (var2 != null) {
         var2.searchPrefixes(var1);
      }
   }

   @Override
   public boolean matches(Object var1) {
      throw null;
   }

   @Override
   public void reset(Collection var1) {
      throw null;
   }

   @Override
   public int getAt(int var1, int var2, Object[] var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Object getAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int size() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setCriteria(Object var1, FilterStatusListener var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public synchronized Object getCriteria() {
      return this._filterCriteria;
   }

   @Override
   public void persistentContentStateChanged(int var1) {
      if (var1 == 2) {
         this.resetFilterResults();
         this.reset();
      }
   }

   @Override
   public void persistentContentModeChanged(int var1) {
   }

   @Override
   public String getAccessibleName() {
      return this.toString();
   }

   @Override
   public String getAccessibleDescription() {
      return null;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return null;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listeners.removeCollectionListener(var1);
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listeners.addCollectionListener(var1);
   }

   @Override
   public int getAccessibleStateSet() {
      return this._accessibleStateSet;
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (this._accessibleStateSet & var1) != 0;
   }

   @Override
   public int getAccessibleRole() {
      return 26;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return null;
   }

   @Override
   public int getAccessibleChildCount() {
      return this._filteredElements != null ? this._filteredElements.length : 0;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      Object var2 = this.getAt(var1);
      return (AccessibleContext)(var2 != null ? new Object(var2.toString()) : null);
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return false;
   }

   @Override
   public synchronized void waitForComplete() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void buildFilteredElementList() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected AbstractKeywordFilterList(ReadableList var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._source = var1;
      this._listeners = new CollectionListenerManager();
      this._searchRequestLock = new Object();
      this._currentSearchRequest = new AbstractKeywordFilterList$SearchRequest();
      this._nextSearchRequest = new AbstractKeywordFilterList$SearchRequest();
      if (InputContext.getInstance(false).hasSureType()) {
         this._prefixCache = new KeywordPrefixCache();
      }

      PersistentContent.addWeakListener(this);
   }
}
