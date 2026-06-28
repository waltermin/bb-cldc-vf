package net.rim.device.api.collection.util;

import net.rim.device.api.collection.BulkUpdateCollectionListener;
import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionLock;
import net.rim.device.api.collection.CollectionWithVersion;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.util.BitSet;
import net.rim.vm.Array;
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

   protected int addToIndex(int index, Object element) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   protected void commit(boolean afterReload) {
   }

   public boolean matches(Object object, String suffix) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void endBulkUpdate(Collection collection) {
      this._commitsSuspended = false;
      this.doCommit(collection, true);
   }

   @Override
   public void beginBulkUpdate(Collection collection) {
      this._commitsSuspended = true;
   }

   @Override
   public int getVersion() {
      return this._filterListData._version;
   }

   @Override
   public void elementAdded(Collection collection, Object element) {
      boolean fireEvent = false;

      try {
         synchronized (CollectionLock.getGlobalLock()) {
            synchronized (this) {
               this.clearPrefixCache();
               int index = super._source.getIndex(element);
               if (index != -1) {
                  this.addToIndex(index, element);
                  fireEvent = this.doAddCheck(element);
                  this.doCommit(collection, false);
               }
            }
         }

         if (fireEvent) {
            this.fireElementAdded(element);
            return;
         }
      } catch (ArrayIndexOutOfBoundsException e) {
         this.reset(collection);
      }
   }

   @Override
   public void elementUpdated(Collection collection, Object oldElement, Object newElement) {
      boolean changed = false;

      try {
         synchronized (CollectionLock.getGlobalLock()) {
            synchronized (this) {
               this.clearPrefixCache();
               int oldId = this.removeFromIndex(oldElement);
               int index = super._source.getIndex(newElement);
               if (index != -1) {
                  this.addToIndex(index, newElement);
                  changed = this.doRemoveCheck(oldId, oldElement);
                  changed |= this.doAddCheck(newElement);
                  this.doCommit(collection, false);
               }
            }
         }

         if (changed) {
            this.fireElementUpdated(oldElement, newElement);
            return;
         }
      } catch (ArrayIndexOutOfBoundsException e) {
         this.reset(collection);
      }
   }

   @Override
   public void elementRemoved(Collection collection, Object element) {
      boolean fireEvent = false;
      synchronized (CollectionLock.getGlobalLock()) {
         synchronized (this) {
            this.clearPrefixCache();
            int oldId = this.removeFromIndex(element);
            fireEvent = this.doRemoveCheck(oldId, element);
            this.doCommit(collection, false);
         }
      }

      if (fireEvent) {
         this.fireElementRemoved(element);
      }
   }

   @Override
   public void persistentContentStateChanged(int state) {
      super.persistentContentStateChanged(state);
      if (state == 1) {
         this.reload(super._source);
      }
   }

   @Override
   protected void reset() {
      super.reset();
      this.resetFilterResults();
      this._objectList.removeAll();
      this._prefixList.reset();
      this._orderList = new BigIntVector(64);
      this._filterListData._orderList = this._orderList;
      this._filterListData._version = -1;
   }

   @Override
   protected void haltSearch() {
      this._prefixList.haltSearch();
   }

   private void reload(ReadableList source) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean matches(Object object) {
      return this.matches(object, null);
   }

   public PrefixKeywordFilterList(ReadableList source, KeywordIndexerHelper helper, PrefixKeywordFilterListData filterListData) {
   }

   @Override
   public KeywordPrefixSearchResult search(String[] words) {
      KeywordPrefixSearchResult result = this._prefixList.search(words, this.getPrefixCache());
      KeywordSearcher searcher = this.getSearcher();
      String[] longWords = this._prefixList.getLongWords(words);
      if (result != null && longWords != null) {
         BitSet matchSet = result.getPrimaryMatches();

         for (int i = 0; i < 2; matchSet = result.getSecondaryMatches()) {
            int id = matchSet.getFirstSet();
            synchronized (CollectionLock.getGlobalLock()) {
               for (; !searcher._interrupted && id != -1; id = matchSet.getNextSet(id + 1)) {
                  if (!this._keywordHelper.checkForMatch(this._objectList.get(id), longWords)) {
                     matchSet.clear(id);
                  }
               }
            }

            i++;
         }
      }

      if (searcher._interrupted) {
         result = null;
      }

      return result;
   }

   @Override
   public Object[] getElements(KeywordPrefixSearchResult result) {
      synchronized (this) {
         Object[] matchElements = new Object[0];
         int dest = 0;
         Array.resize(matchElements, result.getMatchCount());
         if (this._firstWordBias) {
            BitSet primaryMatches = result.getPrimaryMatches();
            BitSet secondaryMatches = result.getSecondaryMatches();
            int count = primaryMatches.getNumSet();

            for (int src = 0; dest < count; src++) {
               int id = this._orderList.elementAt(src);
               if (primaryMatches.isSet(id)) {
                  matchElements[dest++] = this._objectList.get(id);
               }
            }

            count = dest + secondaryMatches.getNumSet();

            for (int var14 = 0; dest < count; var14++) {
               int id = this._orderList.elementAt(var14);
               if (secondaryMatches.isSet(id)) {
                  matchElements[dest++] = this._objectList.get(id);
               }
            }
         } else {
            BitSet matches = new BitSet(result.getPrimaryMatches());
            matches.or(result.getSecondaryMatches());
            int count = matches.getNumSet();

            for (int src = 0; dest < count; src++) {
               int id = this._orderList.elementAt(src);
               if (matches.isSet(id)) {
                  matchElements[dest++] = this._objectList.get(id);
               }
            }
         }

         Array.resize(matchElements, dest);
         return matchElements;
      }
   }

   public PrefixKeywordFilterList(ReadableList source, KeywordIndexerHelper helper, boolean firstWordBias) {
      this(source, helper, new PrefixKeywordFilterListData(new SparseList(), new KeywordPrefixManager(), new BigIntVector(64), firstWordBias, 0));
   }

   private boolean doAddCheck(Object element) {
      throw new RuntimeException("cod2jar: type check");
   }

   private int removeFromIndex(Object element) {
      synchronized (this) {
         int id = this._objectList.getKey(element);
         if (id != -1) {
            this._objectList.removeAt(id);
            this._prefixList.delete(id);

            for (int i = this._orderList.size() - 1; i >= 0; i--) {
               if (this._orderList.elementAt(i) == id) {
                  this._orderList.removeElementAt(i);
                  break;
               }
            }
         }

         return id;
      }
   }

   private boolean doRemoveCheck(int id, Object element) {
      boolean changed = false;
      synchronized (this) {
         if (super._filterResult != null) {
            BitSet matches = super._filterResult.getPrimaryMatches();
            if (matches.isSet(id)) {
               changed = true;
               matches.clear(id);
            } else {
               matches = super._filterResult.getSecondaryMatches();
               if (matches.isSet(id)) {
                  changed = true;
                  matches.clear(id);
               }
            }
         } else {
            changed = true;
         }

         if (changed) {
            this.clearFilteredElementList();
         }

         return changed;
      }
   }

   @Override
   public void reset(Collection collection) {
      throw new RuntimeException("cod2jar: type check");
   }

   public PrefixKeywordFilterList(ReadableList source, KeywordIndexerHelper helper) {
      this(source, helper, false);
   }

   private void doCommit(Collection collection, boolean afterReload) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void persistentContentModeChanged(int generation) {
      throw new RuntimeException("cod2jar: type check");
   }
}
