package net.rim.device.internal.media;

import java.io.InputStream;
import javax.microedition.media.Control;
import javax.microedition.media.protocol.ContentDescriptor;
import javax.microedition.media.protocol.DataSource;
import javax.microedition.media.protocol.SourceStream;

public class DataSourceImpl extends DataSource implements SourceStream {
   String _locator;
   boolean _connected;
   boolean _started;
   InputStream _is;
   String _contentType;
   long _contentLength = -1;
   long _position;
   int _seekType = 0;
   public static final String MIME_AUDIO_MIDI;
   public static final String MIME_AUDIO_X_MIDI;
   public static final String MIME_AUDIO_MID;
   public static final String MIME_AUDIO_X_NOKIA_RINGTONE;
   public static final String MIME_AUDIO_MP3;
   public static final String MIME_AUDIO_WAV;
   public static final String MIME_AUDIO_AMR;
   public static final String MIME_AUDIO_RAW_PCM;
   public static final String MIME_IMAGE_GIF;
   public static final String MIME_IMAGE_PNG;
   public static final String MIME_MEDIA_PME;
   public static final String MIME_MEDIA_PMB;

   public DataSourceImpl(String var1) {
      super(null);
      this._locator = var1;
   }

   @Override
   public String getContentType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void connect() {
      throw new RuntimeException("cod2jar: ldc");
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

   public InputStream getInputStream() {
      return this._connected && this._started ? this._is : null;
   }

   public void setInputStream(InputStream var1) {
      this._is = var1;
      if (this._is.markSupported()) {
         this._seekType = 2;
      }

      this._connected = true;
   }

   public void setContentType(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public ContentDescriptor getContentDescriptor() {
      return (ContentDescriptor)(new Object(this._contentType));
   }

   @Override
   public long getContentLength() {
      return this._contentLength;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      int var4 = this._is.read(var1, var2, var3);
      if (var4 != -1) {
         this._position += var4;
      }

      return var4;
   }

   @Override
   public int getTransferSize() {
      return -1;
   }

   @Override
   public long seek(long var1) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (this._seekType == 0) {
         return this._position;
      }

      if (this._seekType == 1 && var1 != 0) {
         return this._position;
      }

      if (var1 > this._contentLength && this._contentLength > 0) {
         var1 = this._contentLength;
      }

      if (var1 < this._position) {
         this._is.reset();
         this._is.mark(Integer.MAX_VALUE);
         this._position = this._is.skip(var1);
      } else {
         this._position = this._position + this._is.skip(var1 - this._position);
      }

      return this._position;
   }

   @Override
   public long tell() {
      return this._position;
   }

   @Override
   public int getSeekType() {
      return this._seekType;
   }

   @Override
   public Control[] getControls() {
      if (!this._connected) {
         throw new Object();
      } else {
         return null;
      }
   }

   @Override
   public Control getControl(String var1) {
      if (!this._connected) {
         throw new Object();
      } else {
         return null;
      }
   }
}
