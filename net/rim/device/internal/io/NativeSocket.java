package net.rim.device.internal.io;

public final class NativeSocket {
   private int _socketId;
   private Object _operationLock;
   private int _operationResult;
   private static final int SOC_ERROR_TIMEOUT;
   public static final int SOC_PENDING;
   public static final int SOC_OK;
   public static final int SOCK_STREAM;
   public static final int SOCK_DGRAM;
   public static final int SOCK_RAW;
   public static final int IPPROTO_IP;
   public static final int IPPROTO_ICMP;
   public static final int IPPROTO_TCP;
   public static final int IPPROTO_UDP;
   private static final long LOW_DWORD;
   private static final int TIMEOUT;

   public NativeSocket() {
      this._socketId = -1;
      this._operationLock = new Object();
   }

   private NativeSocket(int var1) {
      this._socketId = var1;
   }

   public final void create(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native boolean isMultiRATSupported();

   public final int getSocketId() {
      return this._socketId;
   }

   public final void connectIPv4(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void listen(int var1) {
      checkError(listen0(this._socketId, var1));
   }

   public final NativeSocket accept() {
      long var1 = accept0(this._socketId);
      checkError(var1 & 4294967295L);
      return new NativeSocket((int)(var1 >> 32));
   }

   public final void shutdown() {
      checkError(shutdown0(this._socketId));
   }

   public final void close() {
      checkError(close0(this._socketId));
   }

   public final int send(byte[] var1, int var2, int var3, int var4) {
      long var5 = send0(this._socketId, var1, var2, var3, var4);
      checkError(var5 & 4294967295L);
      return (int)(var5 >> 32);
   }

   public final int send(int var1, int var2) {
      long var3 = send0(this._socketId, var1, var2);
      checkError(var3 & 4294967295L);
      return (int)(var3 >> 32);
   }

   public final int sendToIPv4(byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      long var7 = sendToIPv4_0(this._socketId, var1, var2, var3, var4, var5, var6);
      checkError(var7 & 4294967295L);
      return (int)(var7 >> 32);
   }

   public final int available() {
      int var1 = available0(this._socketId);
      if (var1 < 0) {
         throw new SocketIOException(-6);
      } else {
         return var1;
      }
   }

   public final int receive(byte[] var1, int var2, int var3, int var4) {
      long var5 = receive0(this._socketId, var1, var2, var3, var4);
      checkError(var5 & 4294967295L);
      return (int)(var5 >> 32);
   }

   public final int receiveFrom0(byte[] var1, int var2, int var3, int var4) {
      long var5 = receiveFrom0(this._socketId, var1, var2, var3, var4);
      checkError(var5 & 4294967295L);
      return (int)(var5 >> 32);
   }

   public final void setSocketOption(int var1, int var2, int var3) {
      checkError(setOption0(this._socketId, var1, var2, var3));
   }

   public final int getSocketOption(int var1, int var2) {
      long var3 = getOption0(this._socketId, var1, var2);
      checkError(var3 & 4294967295L);
      return (int)(var3 >> 32);
   }

   public final void socketConnected(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void socketDisconnected() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void flush() {
      checkError(flush0(this._socketId));
   }

   private static final native long allocate0(int var0, int var1);

   private static final native int bindIPv4_0(int var0, int var1, int var2);

   private static final native int connectIPv4_0(int var0, int var1, int var2);

   private static final native int listen0(int var0, int var1);

   private static final native long accept0(int var0);

   private static final native int shutdown0(int var0);

   private static final native int close0(int var0);

   private static final native long send0(int var0, int var1, int var2);

   private static final native long send0(int var0, byte[] var1, int var2, int var3, int var4);

   private static final native int sendToIPv4_0(int var0, byte[] var1, int var2, int var3, int var4, int var5, int var6);

   private static final native int available0(int var0);

   private static final native long receive0(int var0, byte[] var1, int var2, int var3, int var4);

   private static final native long receiveFrom0(int var0, byte[] var1, int var2, int var3, int var4);

   private static final native int setOption0(int var0, int var1, int var2, int var3);

   private static final native long getOption0(int var0, int var1, int var2);

   private static final native void getPeerName0(int var0);

   private static final native void getSockName0(int var0);

   private static final native int flush0(int var0);

   public static final void checkError(long var0) {
      if (var0 != 1 && var0 != 0) {
         throw new SocketIOException((int)var0);
      }
   }
}
