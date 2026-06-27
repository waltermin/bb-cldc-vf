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
      short var1 = 171;
      this._primaryCache = (Hashtable)(new Object(var1));
      this._secondaryCache = (Hashtable)(new Object(var1));
      this._returnResultCache = (Hashtable)(new Object(var1));
      this._timestamps = (ToIntHashtable)(new Object(var1));
   }

   void reset() {
      this._primaryCache.clear();
      this._secondaryCache.clear();
      this._returnResultCache.clear();
      this._timestamps.clear();
      this._timestamp = 0;
   }

   private void removeOldest() {
      Enumeration var1 = this._timestamps.keys();
      Object var2 = null;
      int var3 = this._timestamp;

      while (var1.hasMoreElements()) {
         Object var4 = var1.nextElement();
         int var5 = this._timestamps.get(var4);
         if (var5 < var3) {
            var2 = var4;
            var3 = var5;
         }
      }

      this._primaryCache.remove(var2);
      this._secondaryCache.remove(var2);
      this._timestamps.remove(var2);
   }

   synchronized BitSet getPrimaryEntry(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized BitSet getSecondaryEntry(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized BitSet getReturnResultEntry(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putPrimaryEntry(String var1, BitSet var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putSecondaryEntry(String var1, BitSet var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void putReturnResultEntry(String var1, BitSet var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
