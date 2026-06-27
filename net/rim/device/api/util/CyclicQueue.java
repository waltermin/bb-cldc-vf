package net.rim.device.api.util;

import net.rim.vm.Array;

public final class CyclicQueue {
   private int _initialCapacity;
   private Object[] _queue;
   private int _capacity;
   private int _modulus;
   private int _head;
   private int _tail;
   public static final int DEFAULT_INITIAL_CAPACITY;

   public CyclicQueue() {
      this(8);
   }

   public CyclicQueue(int var1) {
      var1 = roundCapacity(var1);
      this._initialCapacity = var1;
      this._queue = new Object[var1];
      this._capacity = var1;
      this._modulus = var1 - 1;
   }

   public final void enqueue(Object var1) {
      this._queue[this._head] = var1;
      this._head = this._head + 1 & this._modulus;
      if (this._head == this._tail) {
         this.expandCapacity();
      }
   }

   public final Object dequeue() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void flush() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final boolean isEmpty() {
      return this._head == this._tail;
   }

   public final int size() {
      return this._head - this._tail & this._modulus;
   }

   private static final int roundCapacity(int var0) {
      if (var0 <= 8) {
         return 8;
      }

      var0--;

      byte var1;
      for (var1 = 8; var0 > 7; var1 <<= 1) {
         var0 >>>= 1;
      }

      return var1;
   }

   private final void expandCapacity() {
      int var1 = this._capacity << 1;
      Array.resize(this._queue, var1);
      if (this._head <= this._capacity >> 1) {
         this._head = this._head + this._capacity;
         if (this._tail > 0) {
            System.arraycopy(this._queue, 0, this._queue, this._capacity, this._tail);

            for (int var2 = 0; var2 < this._tail; var2++) {
               this._queue[var2] = null;
            }
         }
      } else {
         this._tail = this._tail + this._capacity;
         System.arraycopy(this._queue, this._head, this._queue, this._tail, this._capacity - this._head);

         for (int var3 = this._head; var3 < this._capacity; var3++) {
            this._queue[var3] = null;
         }
      }

      this._capacity = var1;
      this._modulus = var1 - 1;
   }
}
