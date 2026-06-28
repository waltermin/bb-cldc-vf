package net.rim.device.internal.media;

import java.io.IOException;
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

   public RTSPDataSource(String locator, String userAgent, String apn, String apnUsername, String apnPassword) {
      super(locator);
      this._userAgent = userAgent;
      this._apn = apn;
      this._apnUsername = apnUsername;
      this._apnPassword = apnPassword;
   }

   @Override
   public final void connect() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void disconnect() {
      if (this._connected) {
         if (this._started) {
            try {
               this.stop();
            } catch (IOException var2) {
            }
         }

         if (this._tunnel != null) {
            this._tunnel.close();
            this._tunnel = null;
         }

         this._connected = false;
      }
   }

   @Override
   public final void start() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void close() {
      try {
         this.stop();
      } catch (IOException var2) {
      }
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
      throw new RuntimeException("cod2jar: ldc");
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
   public final int read(byte[] b, int off, int len) {
      return 0;
   }

   @Override
   public final int getTransferSize() {
      return -1;
   }

   @Override
   public final long seek(long where) {
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
   public final Control getControl(String controlType) {
      if (!this._connected) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public final void statusChanged(int status, int code) {
      synchronized (this._tunnelSyncObject) {
         this._tunnelStatus = status;
         this._tunnelSyncObject.notify();
      }
   }
}
