package net.rim.device.cldc.io.tcpsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.SocketConnection;
import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.internal.io.BoundNativeSocketListener;
import net.rim.device.internal.io.NativeSocket;
import net.rim.device.internal.io.NativeSocketEventDispatcher;
import net.rim.device.internal.io.PortAssigner;

final class TcpSocket implements SocketConnection, BoundNativeSocketListener {
   private Object _readLock;
   private Object _writeLock;
   private NativeSocket _nativeSocket;
   private int _outputStreamState;
   private int _inputStreamState;
   private InputStream _inStream;
   private OutputStream _outStream;
   private byte[] _readBuffer = new byte[1500];
   private int _readBufferLength;
   private int _readBufferOffset;
   private Tunnel _tunnel;
   private TcpAddress _address;
   private static final int STREAM_STATE_NOT_OPENED;
   private static final int STREAM_STATE_OPENED;
   private static final int STREAM_STATE_SHUTDOWN;
   private static final int STREAM_STATE_CLOSED;
   private static final int READ_BUFFER_SIZE;
   private static final String STR_STREAM_IS_ALREADY_CLOSED;
   private static final String STR_STREAM_IS_ALREADY_OPEN;

   @Override
   public final void close() {
      if ((this._outputStreamState == 0 || this._outputStreamState == 2) && this._outStream != null) {
         this._outStream.close();
      }

      if ((this._inputStreamState == 0 || this._inputStreamState == 2) && this._inStream != null) {
         this._inStream.close();
      }

      this.releaseSocketIfNecessary();
   }

   final void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void connect(int var1, int var2) {
      if (this._nativeSocket == null) {
         throw new Object();
      }

      this._nativeSocket.connectIPv4(var1, var2);
   }

   final void write(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final int read(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final int available() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final int read() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void inputClosed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void outputClosed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void init() {
      this._nativeSocket.create(this._tunnel.getIdentifier(), 1, 6, this._address.getLocalPort());
      this._inStream = (InputStream)(new Object(this));
      this._outStream = (OutputStream)(new Object(this));
   }

   @Override
   public final DataInputStream openDataInputStream() {
      return (DataInputStream)(new Object(this.openInputStream()));
   }

   @Override
   public final OutputStream openOutputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final DataOutputStream openDataOutputStream() {
      return (DataOutputStream)(new Object(this.openOutputStream()));
   }

   @Override
   public final InputStream openInputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int getPort() {
      return this._address.getConnectionDestinationPort();
   }

   @Override
   public final String getAddress() {
      return this._address.getConnectionAddress();
   }

   @Override
   public final int getLocalPort() {
      return this._address.getLocalPort();
   }

   @Override
   public final String getLocalAddress() {
      return this._address.getLocalAddress();
   }

   @Override
   public final int getSocketOption(byte var1) {
      return 0;
   }

   @Override
   public final void setSocketOption(byte var1, int var2) {
   }

   @Override
   public final int getSocketId() {
      return this._nativeSocket == null ? -1 : this._nativeSocket.getSocketId();
   }

   @Override
   public final void socketDataReady() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void socketWriteReady() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void socketDisconnected() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public TcpSocket(Tunnel var1, TcpAddress var2) {
      this._readLock = new Object();
      this._writeLock = new Object();
      this._tunnel = var1;
      this._nativeSocket = (NativeSocket)(new Object());
      this._address = var2;
      PortAssigner.getInstance(6).registerConnection(var2.getLocalPort(), this, var1.getConfig().getName());
      NativeSocketEventDispatcher.addListener(this);
   }

   private final void releaseSocketIfNecessary() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
