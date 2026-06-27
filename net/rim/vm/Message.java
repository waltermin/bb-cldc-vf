package net.rim.vm;

public final class Message {
   int _device;
   int _event;
   int _subMessage;
   int _dataLength;
   int _dataPtr_UNUSED;
   int _data0;
   int _data1;
   Object _object0;
   Object _object1;

   public final native boolean get();

   public static final native void abortGet(Thread var0);

   public final native void post();

   public Message() {
   }

   public Message(int var1, int var2) {
      this._device = var1;
      this._event = var2;
   }

   public Message(int var1, int var2, int var3) {
      this._device = var1;
      this._event = var2;
      this._subMessage = var3;
   }

   public Message(int var1, int var2, int var3, int var4, int var5) {
      this._device = var1;
      this._event = var2;
      this._subMessage = var3;
      this._data0 = var4;
      this._data1 = var5;
   }

   public Message(int var1, int var2, Object var3, Object var4) {
      this._device = var1;
      this._event = var2;
      this._object0 = var3;
      this._object1 = var4;
   }

   public Message(int var1, int var2, int var3, int var4, int var5, Object var6, Object var7) {
      this._device = var1;
      this._event = var2;
      this._subMessage = var3;
      this._data0 = var4;
      this._data1 = var5;
      this._object0 = var6;
      this._object1 = var7;
   }

   public final int getDevice() {
      return this._device;
   }

   public final int getEvent() {
      return this._event;
   }

   public final int getSubMessage() {
      return this._subMessage;
   }

   public final int getDataLength() {
      return this._dataLength;
   }

   public final int getData0() {
      return this._data0;
   }

   public final int getData1() {
      return this._data1;
   }

   public final Object getObject0() {
      return this._object0;
   }

   public final Object getObject1() {
      return this._object1;
   }

   public final void setDevice(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setEvent(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setSubMessage(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setDataLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setData0(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setData1(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setObject0(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setObject1(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void copy(Message var1) {
      this._device = var1._device;
      this._event = var1._event;
      this._subMessage = var1._subMessage;
      this._dataLength = var1._dataLength;
      this._data0 = var1._data0;
      this._data1 = var1._data1;
      this._object0 = var1._object0;
      this._object1 = var1._object1;
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
