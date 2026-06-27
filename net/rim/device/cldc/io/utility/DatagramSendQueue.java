package net.rim.device.cldc.io.utility;

import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;

public final class DatagramSendQueue extends Thread {
   private Datagram[] _sendQueue;
   private Datagram[] _cacheQueue;
   private int _nextDequeue;
   private int _nextEnqueue;
   private int _size;
   private int _cacheSize;
   private DatagramConnection _sendConnection;

   public DatagramSendQueue(int var1, DatagramConnection var2) {
      this._sendQueue = new Datagram[var1];
      this._cacheQueue = new Datagram[var1];
      this._nextDequeue = -1;
      this._nextEnqueue = 0;
      this._size = 0;
      this._cacheSize = 0;
      this._sendConnection = var2;
   }

   public final synchronized void send(Datagram var1) {
      this.enqueue(var1);
   }

   private final void enqueue(Datagram var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final Datagram dequeue() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final void dequeueAll() {
      if (this._cacheQueue.length != this._sendQueue.length) {
         this._cacheQueue = new Datagram[this._sendQueue.length];
      }

      this._cacheSize = this.size();

      for (int var1 = 0; var1 < this._cacheSize; var1++) {
         this._cacheQueue[var1] = this.dequeue();
      }
   }

   public final int size() {
      return this._size;
   }

   private final void grow() {
      Datagram[] var1 = new Datagram[this._sendQueue.length * 2];
      int var2 = this._nextEnqueue;
      int var3 = this._sendQueue.length - this._nextEnqueue;
      this._nextDequeue = var1.length - var3;
      System.arraycopy(this._sendQueue, 0, var1, 0, var2);
      System.arraycopy(this._sendQueue, this._nextEnqueue, var1, this._nextDequeue, var3);
      this._sendQueue = var1;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
