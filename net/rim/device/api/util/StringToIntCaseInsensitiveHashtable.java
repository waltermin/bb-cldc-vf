package net.rim.device.api.util;

import java.util.Enumeration;

public class StringToIntCaseInsensitiveHashtable implements Persistable {
   private String[] _key;
   private int[] _hash;
   private int[] _value;
   private String _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public StringToIntCaseInsensitiveHashtable(int initialCapacity) {
   }

   public StringToIntCaseInsensitiveHashtable() {
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

   public synchronized IntEnumeration elements() {
      return new HashtableIntEnumerator(this._value, this._key, this._empty);
   }

   public synchronized boolean contains(int value) {
      Object empty = this._empty;
      String[] keys = this._key;
      int[] values = this._value;
      int len = values.length;

      while (--len >= 0) {
         if (keys[len] != null && keys[len] != empty && values[len] == value) {
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
      int len = this._key.length;

      while (--len >= 0) {
         this._key[len] = null;
      }

      this._numberOfKeys = 0;
   }

   public synchronized int remove(String key) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized int get(String key) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized int get(String str, int fromIndex, int toIndex) {
      int index = this.find(str, fromIndex, toIndex, StringUtilities.hashCode(str, fromIndex, toIndex, true));
      return this._key[index] != null && this._key[index] != this._empty ? this._value[index] : -1;
   }

   protected void rehash() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized int put(String key, int value) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int find(String str, int fromIndex, int toIndex, int hashcode) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
