package net.rim.vm;

public final class MessageQueue {
   private int[] _device;
   private int[] _event;
   private int[] _subMessage;
   private int[] _dataLength;
   private int[] _data0;
   private int[] _data1;
   private Object[] _object0;
   private Object[] _object1;
   private int _start = 0;
   private int _end = 0;
   private int _capacity = 10;
   private int _maxCapacity;
   private int _processState = 0;
   private boolean _isSystemProcess;
   private static final int START_CAPACITY;
   private static final int CAPACITY_INCREMENT;
   public static final int KEY_DROP_WATERMARK;
   public static final int NATIVE_SOCKET_DROP_WATERMARK;
   public static final int PROCESS_STATE_STARTUP;
   public static final int PROCESS_STATE_HANDLING_EVENTS;
   private static final int MAX_PROCESS_STATES;
   private static final int[] MAX_CAPACITIES_NORMAL_PROCESS;
   private static final int[] MAX_CAPACITIES_SYSTEM_PROCESS;

   public MessageQueue() {
      this._maxCapacity = MAX_CAPACITIES_NORMAL_PROCESS[0];
   }

   public final void setProcessState(int var1) {
      if (var1 >= 0 && var1 <= 2) {
         this._processState = var1;
         this.updateMaxCapacity();
      } else {
         throw new Object();
      }
   }

   public final void setSystemProcess() {
      this._isSystemProcess = true;
      this.updateMaxCapacity();
   }

   private final void updateMaxCapacity() {
      int var1;
      if (this._isSystemProcess) {
         var1 = MAX_CAPACITIES_SYSTEM_PROCESS[this._processState];
      } else {
         var1 = MAX_CAPACITIES_NORMAL_PROCESS[this._processState];
      }

      if (this._maxCapacity < var1) {
         this._maxCapacity = var1;
      }
   }

   private final void growTo(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final Object growArray(Object var1, Object var2) {
      if (this._end <= this._start) {
         System.arraycopy(var1, this._start, var2, 0, this._capacity - this._start);
         System.arraycopy(var1, 0, var2, this._capacity - this._start, this._end);
         return var2;
      } else {
         System.arraycopy(var1, this._start, var2, 0, this._end - this._start);
         return var2;
      }
   }

   public final boolean enqueue(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean replaceTail(Message var1) {
      if (this._start == this._end) {
         return false;
      }

      int var2 = this.minusminus(this._end);
      this._device[var2] = var1._device;
      this._event[var2] = var1._event;
      this._subMessage[var2] = var1._subMessage;
      this._dataLength[var2] = var1._dataLength;
      this._data0[var2] = var1._data0;
      this._data1[var2] = var1._data1;
      this._object0[var2] = var1._object0;
      this._object1[var2] = var1._object1;
      return true;
   }

   public final boolean dequeue(Message var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void flush() {
      while (this._start != this._end) {
         this._object0[this._start] = null;
         this._object1[this._start] = null;
         this._start = this.plusplus(this._start);
      }
   }

   public final boolean alreadyPresent(Message var1) {
      for (int var2 = this._start; var2 != this._end; var2 = this.plusplus(var2)) {
         if (var1._device == this._device[var2]
            && var1._event == this._event[var2]
            && var1._subMessage == this._subMessage[var2]
            && var1._dataLength == this._dataLength[var2]
            && var1._data0 == this._data0[var2]
            && var1._data1 == this._data1[var2]
            && var1._object0 == this._object0[var2]
            && var1._object1 == this._object1[var2]) {
            return true;
         }
      }

      return false;
   }

   public final int getSize() {
      int var1 = this._end - this._start;
      if (var1 < 0) {
         var1 += this._capacity;
      }

      return var1;
   }

   public final int getCapacity() {
      return this._capacity - 1;
   }

   public final int getMaxCapacity() {
      return this._maxCapacity - 1;
   }

   public final void setMaxCapacity(int var1) {
      if (var1 + 1 < this._maxCapacity) {
         throw new Object();
      }

      this._maxCapacity = var1 + 1;
   }

   private final int plusplus(int var1) {
      if (++var1 == this._capacity) {
         var1 = 0;
      }

      return var1;
   }

   private final int minusminus(int var1) {
      return var1 == 0 ? this._capacity - 1 : var1 - 1;
   }

   private static final void appendObjType(StringBuffer var0, Object var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final String toString() {
      Object var1 = new Object();
      this.dumpMessage((StringBuffer)var1, this.minusminus(this._start), true);

      for (int var2 = this._start; var2 != this._end; var2 = this.plusplus(var2)) {
         this.dumpMessage((StringBuffer)var1, var2, false);
      }

      return ((StringBuffer)var1).toString();
   }

   private final void dumpMessage(StringBuffer var1, int var2, boolean var3) {
      if (var3) {
         var1.append('[');
      }

      var1.append(Integer.toHexString(this._device[var2]));
      var1.append(' ');
      var1.append(Integer.toHexString(this._event[var2]));
      if (var3) {
         appendObjType(var1, this._object0[var2]);
         appendObjType(var1, this._object1[var2]);
         var1.append(']');
      }

      var1.append('\n');
   }
}
