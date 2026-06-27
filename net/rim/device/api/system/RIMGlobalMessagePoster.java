package net.rim.device.api.system;

public final class RIMGlobalMessagePoster {
   private static ApplicationManagerImpl _ami;

   private RIMGlobalMessagePoster() {
   }

   public static final boolean postGlobalEvent(long var0) {
      return postGlobalEvent(var0, 0, 0, null, null);
   }

   public static final boolean postGlobalEvent(long var0, int var2, int var3) {
      return postGlobalEvent(var0, var2, var3, null, null);
   }

   public static final boolean postGlobalEvent(long var0, int var2, int var3, Object var4, Object var5) {
      return _ami.postInternalGlobalEvent(var0, var2, var3, var4, var5);
   }

   public static final boolean postGlobalEvent(int var0, long var1, int var3, int var4, Object var5, Object var6) {
      return _ami.postInternalGlobalEvent(var0, var1, var3, var4, var5, var6);
   }
}
