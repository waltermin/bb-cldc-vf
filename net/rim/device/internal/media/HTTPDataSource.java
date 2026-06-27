package net.rim.device.internal.media;

import java.io.InputStream;
import javax.microedition.media.Control;
import javax.microedition.media.protocol.ContentDescriptor;
import javax.microedition.media.protocol.DataSource;
import javax.microedition.media.protocol.SourceStream;

public class HTTPDataSource extends DataSource implements SourceStream {
   private String _contentType;
   private long _pos;
   private InputStream _inputStream;
   private boolean _started;
   private HTTPBufferingManager _buffer;
   private HTTPBufferingCallback _callback;
   private String _contentUrl;
   private long _estimatedTime;

   public void setEstimatedTime(long var1) {
      this._estimatedTime = var1;
      if (this._buffer != null) {
         this._buffer.setEstimatedTime(var1);
      }
   }

   public HTTPBufferingManager getBufferingManager() {
      return this._buffer;
   }

   @Override
   public long getContentLength() {
      return this._buffer != null ? this._buffer.getStreamLength() : -1;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      int var4 = this._inputStream.read(var1, var2, var3);
      if (var4 > 0) {
         this._pos += var4;
      }

      return var4;
   }

   @Override
   public int getTransferSize() {
      return -1;
   }

   @Override
   public long seek(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public long tell() {
      return this._pos;
   }

   @Override
   public int getSeekType() {
      return this._buffer != null && this._buffer.bufferContainsAllContent() ? 2 : 1;
   }

   @Override
   public ContentDescriptor getContentDescriptor() {
      return (ContentDescriptor)(new Object(this._contentType));
   }

   @Override
   public void connect() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public HTTPDataSource(HTTPBufferingCallback var1, HTTPBufferingManager var2, String var3, String var4) {
   }

   @Override
   public void disconnect() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void start() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void stop() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public SourceStream[] getStreams() {
      return new SourceStream[]{this};
   }

   @Override
   public Control[] getControls() {
      if (this._buffer == null) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public Control getControl(String var1) {
      if (this._buffer == null) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public String getContentType() {
      return this._contentType;
   }
}
