package net.rim.device.internal.media;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.RadioInfo;
import net.rim.vm.Array;

public class MediaPlayer implements MediaEventListener {
   private Object _lock;
   private Object _myLock = new Object();
   private MediaStreamingManager$StreamingSession _streamingSession;
   private int _mediaHandle;
   private final String _mimeType;
   private boolean _mediaLoaded;
   private int _streams;
   private int _width;
   private int _height;
   private byte[] _screenBitmap;
   private Application _app;
   private MediaEventListener _listener;
   private static String MIME_TYPE_AMR;
   public static final int MEDIA_STREAM_AUDIO;
   public static final int MEDIA_STREAM_VIDEO;
   public static final int MEDIA_SUCCESS;
   public static final int MEDIA_ERROR_BUSY;
   public static final int MEDIA_ERROR_PARAM;
   public static final int MEDIA_ERROR_MEMORY;
   public static final int MEDIA_ERROR_NEED_MORE_DATA;
   public static final int MEDIA_ERROR_UNSPECIFIED;
   public static final int MEDIA_ERROR_FORMAT;

   boolean isAlive() {
      return this._mediaHandle != -1;
   }

   boolean hasVideo() {
      return (this._streams & 2) != 0;
   }

   void play() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void pause() {
      throw new RuntimeException("cod2jar: exception table");
   }

   Bitmap getPauseBitmap() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void seek(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void seekComplete() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void signifyNoMoreData() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void unload() {
      throw new RuntimeException("cod2jar: exception table");
   }

   int getLength() {
      return MediaNatives.getLength0(this._mediaHandle);
   }

   boolean isSeekable() {
      return MediaNatives.isSeekable0(this._mediaHandle);
   }

   int getPlayableStreams() {
      return MediaNatives.getPlayableStreams0(this._mediaHandle);
   }

   void resize(int var1, int var2) {
      this._width = var1;
      this._height = var2;
      Array.resize(this._screenBitmap, var1 * var2 << 1);
      MediaNatives.resize0(this._mediaHandle, var1, var2);
      this.parametersChanged();
   }

   void relocate(int var1, int var2) {
      MediaNatives.relocate0(this._mediaHandle, var1, var2);
      this.parametersChanged();
   }

   void resizeAndRelocate(int var1, int var2, int var3, int var4) {
      this._width = var3;
      this._height = var4;
      Array.resize(this._screenBitmap, var3 * var4 << 1);
      MediaNatives.resizeAndRelocate0(this._mediaHandle, var1, var2, var3, var4);
      this.parametersChanged();
   }

   int getContentWidth() {
      return MediaNatives.getContentWidth0(this._mediaHandle);
   }

   int getContentHeight() {
      return MediaNatives.getContentHeight0(this._mediaHandle);
   }

   void passCredentials(String var1) {
      if (this._mediaHandle != -1) {
         MediaNatives.passCredentials0(this._mediaHandle, var1);
      }
   }

   public void addListener(MediaEventListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeListener(MediaEventListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   boolean isSinkSupported(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void mediaStopped(int var1) {
      if (this._mediaLoaded && var1 == this._mediaHandle) {
         this._mediaHandle = -1;
         if (this._app != null) {
            MediaNatives.removeListener(this._app, this);
            this._app = null;
         }

         MediaEventListener var2 = this._listener;
         if (var2 != null) {
            var2.mediaStopped(var1);
         }
      }
   }

   @Override
   public void mediaPauseComplete(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void mediaParametersChangedComplete(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void mediaSeek(int var1, int var2) {
      if (var1 == this._mediaHandle) {
         MediaEventListener var3 = this._listener;
         if (var3 != null) {
            var3.mediaSeek(var1, var2);
         }
      }
   }

   @Override
   public void mediaError(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void mediaLoaded(int var1) {
      if (var1 == this._mediaHandle) {
         this._mediaLoaded = true;
         int var2 = this.getPlayableStreams();
         if (var2 != -1 && (var2 & 2) == 0) {
            this._streams = var2;
            this._screenBitmap = null;
         }

         MediaEventListener var3 = this._listener;
         if (var3 != null) {
            var3.mediaLoaded(var1);
         }
      }
   }

   @Override
   public void mediaStatusUpdate(int var1, int var2) {
      if (var1 == this._mediaHandle) {
         MediaEventListener var3 = this._listener;
         if (var3 != null) {
            var3.mediaStatusUpdate(var1, var2);
         }
      }
   }

   @Override
   public void mediaAuthenticationRequired(int var1, Object var2, Object var3) {
      if (var1 == this._mediaHandle) {
         MediaEventListener var4 = this._listener;
         if (var4 != null) {
            var4.mediaAuthenticationRequired(var1, var2, var3);
         }
      }
   }

   private void initializePlayer(MediaStreamingManager$StreamingSession var1, int var2, int var3, int var4, int var5) {
      this._streamingSession = var1;
      this._mediaHandle = var2;
      this._streams = var3;
      this._width = var4;
      this._height = var5;
      var1.registerMediaPlayer(this);
   }

   private MediaPlayer(Object var1, String var2) {
      this._lock = var1;
      this._mimeType = var2;
      this._mediaLoaded = false;
   }

   private boolean isQualcommHardware() {
      return RadioInfo.getNetworkType() == 4;
   }

   private void checkStatus(int var1) {
      if (var1 != 0) {
         MediaEventListener var2 = this._listener;
         int var3 = this._mediaHandle;
         if (var2 != null && var3 != -1) {
            var2.mediaError(var3, var1);
         }
      }
   }

   static MediaPlayer initialize(
      MediaEventListener var0,
      int var1,
      MediaStreamingManager$StreamingSession var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      boolean var8,
      int var9,
      int var10,
      String var11,
      String var12,
      int var13,
      String var14
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void parametersChanged() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
