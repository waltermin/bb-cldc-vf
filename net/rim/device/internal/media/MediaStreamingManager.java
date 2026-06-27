package net.rim.device.internal.media;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.util.RingBuffer;

public class MediaStreamingManager {
   static final long GUID;
   public static final int NO_SESSION;
   public static final int CODEC_VIDEO;

   MediaStreamingManager() {
   }

   public static MediaStreamingManager getInstance() {
      if (!InternalServices.isSoftwareCapable(2)) {
         return null;
      }

      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      return (MediaStreamingManager)var0.waitFor(8461041122205944746L);
   }

   public void addListener(MediaStreamingListener var1) {
      throw null;
   }

   public MediaStreamingManager$StreamingSession recordStream(MediaStreamingCallback var1, RingBuffer var2, int var3, int var4) {
      throw null;
   }

   public MediaStreamingManager$StreamingSession openStream(MediaStreamingCallback var1, RingBuffer var2, int var3, int var4, int var5, boolean var6) {
      throw null;
   }

   public MediaStreamingManager$StreamingSession playStream(MediaStreamingCallback var1, RingBuffer var2, int var3, int var4, int var5, int var6) {
      throw null;
   }

   public MediaStreamingManager$StreamingSession reserveSession(int var1, int var2) {
      throw null;
   }

   public void stopSingleChannelAudio() {
      throw null;
   }
}
