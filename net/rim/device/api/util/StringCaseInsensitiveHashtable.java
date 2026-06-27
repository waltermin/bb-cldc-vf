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

   public StringCaseInsensitiveHashtable(int var1) {
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
      return (Enumeration)(new Object(this._key, this._empty));
   }

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this._value, this._empty));
   }

   public synchronized boolean contains(Object var1) {
      String var2 = this._empty;
      String[] var3 = this._key;
      Object[] var4 = this._value;
      int var5 = var4.length;

      while (--var5 >= 0) {
         if (var3[var5] != null && var3[var5] != var2 && var4[var5].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean containsKey(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized boolean containsKey(String var1, int var2, int var3) {
      int var4 = this.find(var1, var2, var3, StringUtilities.hashCode(var1, var2, var3, true));
      return this._key[var4] != null && this._key[var4] != this._empty;
   }

   public synchronized void clear() {
      for (int var1 = this._key.length; --var1 >= 0; this._key[var1] = null) {
         this._value[var1] = null;
      }

      this._numberOfKeys = 0;
   }

   public synchronized Object remove(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object get(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object get(String var1, int var2, int var3) {
      int var4 = this.find(var1, var2, var3, StringUtilities.hashCode(var1, var2, var3, true));
      return this._value[var4];
   }

   protected void rehash() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized Object put(String var1, Object var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int find(String var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
