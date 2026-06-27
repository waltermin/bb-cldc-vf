package net.rim.device.internal.media;

import java.lang.ref.WeakReference;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.Arrays;

public class MediaPlayerState {
   private WeakReference[] _players = new WeakReference[0];
   private WeakReference _mediaPlayer;
   private static final long APP_REGISTRY_KEY;

   public static void setMediaPlayer(MediaPlayerStateInstance var0) {
      MediaPlayerState var1 = getInstance();
      if (var1 != null) {
         if (var0 == null) {
            var1._mediaPlayer = null;
            return;
         }

         var1._mediaPlayer = (WeakReference)(new Object(var0));
      }
   }

   public static void registerPlayer(MediaPlayerStateInstance var0) {
      MediaPlayerState var1 = getInstance();
      if (var1 != null) {
         Object var2 = new Object(var0);
         Arrays.add(var1._players, var2);
      }

      clean();
   }

   public static void deregisterPlayer(MediaPlayerStateInstance var0) {
      MediaPlayerState var1 = getInstance();
      if (var1 != null) {
         WeakReference[] var2 = var1._players;
         WeakReference var3 = null;

         for (int var4 = var2.length - 1; var4 >= 0; var4--) {
            var3 = var2[var4];
            MediaPlayerStateInstance var5 = (MediaPlayerStateInstance)var3.get();
            if (var5 != null && var5 == var0) {
               break;
            }
         }

         if (var3 != null) {
            Arrays.remove(var1._players, var3);
         }
      }

      clean();
   }

   private static void clean() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference[] var1 = var0._players;

         for (int var2 = var1.length - 1; var2 >= 0; var2--) {
            WeakReference var3 = var1[var2];
            if (var3.get() == null) {
               Arrays.remove(var0._players, var3);
            }
         }

         if (var1.length == 0) {
            ApplicationRegistry.getApplicationRegistry().remove(-4927398290786462096L);
         }
      }
   }

   public static boolean areAnyPlayersRegistered() {
      clean();
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference[] var1 = var0._players;
         return var1.length > 0 || isMediaPlayerRegistered();
      } else {
         return false;
      }
   }

   public static boolean isPlaying() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference[] var1 = var0._players;

         for (int var2 = var1.length - 1; var2 >= 0; var2--) {
            WeakReference var3 = var1[var2];
            MediaPlayerStateInstance var4 = (MediaPlayerStateInstance)var3.get();
            if (var4 != null && var4.isPlayerPlaying()) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean isMediaPlayerPlaying() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference var1 = var0._mediaPlayer;
         if (var1 != null) {
            MediaPlayerStateInstance var2 = (MediaPlayerStateInstance)var1.get();
            if (var2 != null) {
               return var2.isPlayerPlaying();
            }
         }
      }

      return false;
   }

   public static boolean isMediaPlayerPaused() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference var1 = var0._mediaPlayer;
         if (var1 != null) {
            MediaPlayerStateInstance var2 = (MediaPlayerStateInstance)var1.get();
            if (var2 != null) {
               return var2.isPlayerPaused();
            }
         }
      }

      return false;
   }

   public static String getMediaPlayerURL() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference var1 = var0._mediaPlayer;
         if (var1 != null) {
            MediaPlayerStateInstance var2 = (MediaPlayerStateInstance)var1.get();
            if (var2 != null) {
               return var2.getPlayingURL();
            }
         }
      }

      return null;
   }

   public static boolean isMediaPlayerRegistered() {
      MediaPlayerState var0 = getInstance();
      if (var0 != null) {
         WeakReference var1 = var0._mediaPlayer;
         if (var1 != null) {
            MediaPlayerStateInstance var2 = (MediaPlayerStateInstance)var1.get();
            if (var2 != null) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   private static MediaPlayerState getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      MediaPlayerState var1 = (MediaPlayerState)var0.get(-4927398290786462096L);
      if (var1 == null) {
         var1 = new MediaPlayerState();
         var0.replace(-4927398290786462096L, var1);
      }

      return var1;
   }
}
