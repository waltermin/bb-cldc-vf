package net.rim.device.api.collection.util;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.util.BitSet;
import net.rim.device.api.util.ToIntHashtable;

class KeywordPrefixCache {
   private Hashtable _primaryCache;
   private Hashtable _secondaryCache;
   private Hashtable _returnResultCache;
   private ToIntHashtable _timestamps;
   private int _timestamp;
   private static final int MAX_ENTRY_COUNT;

   KeywordPrefixCache() {
      int initialSize = 171;
      this._primaryCache = new Hashtable(initialSize);
      this._secondaryCache = new Hashtable(initialSize);
      this._returnResultCache = new Hashtable(initialSize);
      this._timestamps = new ToIntHashtable(initialSize);
   }

   void reset() {
      this._primaryCache.clear();
      this._secondaryCache.clear();
      this._returnResultCache.clear();
      this._timestamps.clear();
      this._timestamp = 0;
   }

   private void removeOldest() {
      Enumeration enumerator = this._timestamps.keys();
      String oldestKey = null;
      int oldestTimestamp = this._timestamp;

      while (enumerator.hasMoreElements()) {
         String key = (String)enumerator.nextElement();
         int timestamp = this._timestamps.get(key);
         if (timestamp < oldestTimestamp) {
            oldestKey = key;
            oldestTimestamp = timestamp;
         }
      }

      this._primaryCache.remove(oldestKey);
      this._secondaryCache.remove(oldestKey);
      this._timestamps.remove(oldestKey);
   }

   synchronized BitSet getPrimaryEntry(String key) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized BitSet getSecondaryEntry(String key) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized BitSet getReturnResultEntry(String key) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putPrimaryEntry(String key, BitSet matches) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putSecondaryEntry(String key, BitSet matches) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putReturnResultEntry(String key, BitSet result) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
