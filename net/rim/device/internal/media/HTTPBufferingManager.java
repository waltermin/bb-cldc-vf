package net.rim.device.internal.media;

import java.io.InputStream;
import javax.microedition.io.HttpConnection;
import net.rim.device.api.system.Application;
import net.rim.vm.Array;

public final class HTTPBufferingManager extends Thread {
   private InputStream _subIn;
   private HttpConnection _inputConnection;
   private byte[] _buffer;
   private int _readOffset;
   private int _writeOffset;
   private int _dataLength;
   private long _totalBytesBuffered;
   private long _totalInputLength;
   private final Object _lock;
   private boolean _shutdown;
   private int _progressStatusId;
   private HTTPBufferingCallback _callback;
   private long _estimatedTime;
   private int _consumeRate;
   static final int MAX_STREAMING_BUFFER_SIZE;
   static final int MAX_BUFFER_SIZE_FOR_COMPLETE_READ;
   static final int DEFAULT_STREAMING_BUFFER_SIZE;
   static final int RESIZE_INTERVAL;
   static final int READ_INTERVAL;
   private static final int WATERMARK_CHECK_INTERVAL;

   public HTTPBufferingManager(HttpConnection var1, InputStream var2, HTTPBufferingCallback var3) {
      this._inputConnection = var1;
      this._subIn = var2;
      this._callback = var3;
      this._lock = this;
      this._totalInputLength = this.getInputLength(var1);
      if (this._totalInputLength != -1 && this._totalInputLength <= 1048576) {
         this._buffer = new byte[(int)this._totalInputLength];
      } else {
         this._buffer = new byte[524288];
      }

      this._progressStatusId = Application.getApplication().invokeLater(new HTTPBufferingManager$UpdateBufferRunner(this), 1000, true);
   }

   private final long getInputLength(HttpConnection var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final synchronized void start() {
      if (!this.isAlive()) {
         super.start();
      }
   }

   public final void shutdown() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setHTTPBufferingCallback(HTTPBufferingCallback var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final byte[] getBuffer() {
      return this._buffer;
   }

   public final long getStreamLength() {
      return this.bufferContainsAllContent() ? this._buffer.length : this._totalInputLength;
   }

   public final void setStreamLength(long var1) {
      this._totalInputLength = var1;
   }

   public final boolean bufferWillContainAllContent() {
      return this.bufferContainsAllContent() || this._totalInputLength == this._buffer.length;
   }

   public final boolean bufferContainsAllContent() {
      return this._totalInputLength == -1
         ? this._shutdown && this._totalBytesBuffered == this._buffer.length
         : this._totalInputLength == this._totalBytesBuffered && this._totalInputLength == this._buffer.length;
   }

   public final InputStream getInputStream() {
      return (InputStream)(this.bufferContainsAllContent() ? new Object(this._buffer) : new HTTPBufferingManager$HTTPBufferedInputStream(this));
   }

   public final void setEstimatedTime(long var1) {
      if (this._totalInputLength != this._buffer.length) {
         this._estimatedTime = var1;
         if (this._totalInputLength != -1 && var1 > 0) {
            this.setBandwidthRequired((int)((float)this._totalInputLength / ((float)var1 / 1148846080)));
         }
      }
   }

   public final void setBandwidthRequired(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private final void resizeBuffer(int var1) {
      int var2 = this._buffer.length;
      Array.resize(this._buffer, var1);
      if (this._readOffset >= this._writeOffset) {
         int var3 = var1 - var2;
         System.arraycopy(this._buffer, this._readOffset, this._buffer, this._readOffset + var3, var2 - this._readOffset);
         this._readOffset += var3;
      }
   }

   private final void notifyCallbackBufferReady(HTTPBufferingCallback var1) {
      if (var1 != null) {
         var1.streamingBufferReady();
      }
   }

   private final void notifyCallbackStreamingDone(HTTPBufferingCallback var1, double var2) {
      if (var1 != null) {
         var1.streamingDone(var2);
      }
   }

   private final void notifyCallbackBufferStatus(HTTPBufferingCallback var1, long var2, long var4) {
      if (var1 != null) {
         var1.updateStreamingBufferStatus(var2, var4);
      }
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final int access$512(HTTPBufferingManager var0, int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   static final int access$620(HTTPBufferingManager var0, int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
