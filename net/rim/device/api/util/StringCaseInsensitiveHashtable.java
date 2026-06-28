package net.rim.device.api.util;

import java.util.Enumeration;

public class StringCaseInsensitiveHashtable implements Persistable {
   private String[] _key;
   private int[] _hash;
   private Object[] _value;
   private String _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public StringCaseInsensitiveHashtable(int initialCapacity) {
   }

   public StringCaseInsensitiveHashtable() {
      this(11);
   }

   public int size() {
      return this._numberOfKeys;
   }

   public boolean isEmpty() {
      return this._numberOfKeys == 0;
   }

   public synchronized Enumeration keys() {
      return new HashtableObjectEnumerator(this._key, this._empty);
   }

   public synchronized Enumeration elements() {
      return new HashtableObjectEnumerator(this._value, this._empty);
   }

   public synchronized boolean contains(Object value) {
      Object empty = this._empty;
      String[] keys = this._key;
      Object[] values = this._value;
      int len = values.length;

      while (--len >= 0) {
         if (keys[len] != null && keys[len] != empty && values[len].equals(value)) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean containsKey(String key) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized boolean containsKey(String str, int fromIndex, int toIndex) {
      int index = this.find(str, fromIndex, toIndex, StringUtilities.hashCode(str, fromIndex, toIndex, true));
      return this._key[index] != null && this._key[index] != this._empty;
   }

   public synchronized void clear() {
      for (int len = this._key.length; --len >= 0; this._key[len] = null) {
         this._value[len] = null;
      }

      this._numberOfKeys = 0;
   }

   public synchronized Object remove(String key) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object get(String key) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object get(String str, int fromIndex, int toIndex) {
      int index = this.find(str, fromIndex, toIndex, StringUtilities.hashCode(str, fromIndex, toIndex, true));
      return this._value[index];
   }

   protected void rehash() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object put(String key, Object value) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int find(String str, int fromIndex, int toIndex, int hashcode) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
