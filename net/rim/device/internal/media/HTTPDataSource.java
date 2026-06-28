package net.rim.device.internal.media;

import java.io.IOException;
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

   public void setEstimatedTime(long time) {
      this._estimatedTime = time;
      if (this._buffer != null) {
         this._buffer.setEstimatedTime(time);
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
   public int read(byte[] b, int off, int len) {
      int numRead = this._inputStream.read(b, off, len);
      if (numRead > 0) {
         this._pos += numRead;
      }

      return numRead;
   }

   @Override
   public int getTransferSize() {
      return -1;
   }

   @Override
   public long seek(long where) {
      throw new RuntimeException("cod2jar: ldc");
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
      return new ContentDescriptor(this._contentType);
   }

   @Override
   public void connect() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public HTTPDataSource(HTTPBufferingCallback callback, HTTPBufferingManager buffer, String contentUrl, String contentType) {
   }

   @Override
   public void disconnect() {
      if (this._buffer != null) {
         if (this._started) {
            try {
               this.stop();
            } catch (IOException var2) {
            }
         }

         this._pos = 0;
         this._buffer.shutdown();
         this._buffer = null;
      }
   }

   @Override
   public void start() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void stop() {
      if (this._started && this._buffer != null) {
         if (this._inputStream != null) {
            try {
               this._inputStream.close();
            } catch (IOException var2) {
            }
         }

         this._inputStream = null;
         this._started = false;
      }
   }

   @Override
   public SourceStream[] getStreams() {
      return new SourceStream[]{this};
   }

   @Override
   public Control[] getControls() {
      if (this._buffer == null) {
         throw new IllegalStateException();
      } else {
         return null;
      }
   }

   @Override
   public Control getControl(String controlType) {
      if (this._buffer == null) {
         throw new IllegalStateException();
      } else {
         return null;
      }
   }

   @Override
   public String getContentType() {
      return this._contentType;
   }
}
