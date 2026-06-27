package net.rim.device.internal.media;

import javax.microedition.media.Control;
import javax.microedition.media.protocol.ContentDescriptor;
import javax.microedition.media.protocol.DataSource;
import javax.microedition.media.protocol.SourceStream;
import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.cldc.io.tunnel.TunnelListener;

public final class RTSPDataSource extends DataSource implements SourceStream, TunnelListener {
   private boolean _connected;
   private boolean _started;
   private String _apn;
   private String _apnUsername;
   private String _apnPassword;
   private Tunnel _tunnel;
   private int _tunnelStatus;
   private String _userAgent;
   private Object _tunnelSyncObject = new Object();
   private static final int MAX_RETRIES;
   private static final int SESSION_TIMEOUT;

   public RTSPDataSource(String var1, String var2, String var3, String var4, String var5) {
      super(var1);
      this._userAgent = var2;
      this._apn = var3;
      this._apnUsername = var4;
      this._apnPassword = var5;
   }

   @Override
   public final void connect() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void disconnect() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void start() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final String getContentType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void stop() {
      if (this._started && this._connected) {
         this._started = false;
      }
   }

   public final int getAccessPointNumber() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final String getUserAgent() {
      return this._userAgent;
   }

   @Override
   public final SourceStream[] getStreams() {
      return new SourceStream[]{this};
   }

   @Override
   public final ContentDescriptor getContentDescriptor() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final long getContentLength() {
      return -1;
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      return 0;
   }

   @Override
   public final int getTransferSize() {
      return -1;
   }

   @Override
   public final long seek(long var1) {
      return 0;
   }

   @Override
   public final long tell() {
      return 0;
   }

   @Override
   public final int getSeekType() {
      return 2;
   }

   @Override
   public final Control[] getControls() {
      if (!this._connected) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public final Control getControl(String var1) {
      if (!this._connected) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public final void statusChanged(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
