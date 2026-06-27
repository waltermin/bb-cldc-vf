package net.rim.vm;

public final class EventLog implements EventLogConstants {
   private EventLog() {
   }

   public static final void registerApp(long var0, int var2, String var3) {
      registerApp(var0, var2, var3.getBytes());
   }

   public static final String getRegisteredAppName(long var0) {
      byte[] var2 = getRegisteredAppNameArray(var0);
      return (String)(var2 == null ? Long.toString(var0, 16) : new Object(var2));
   }

   public static final native int getRegisteredAppEventType(long var0);

   private static final native byte[] getRegisteredAppNameArray(long var0);

   private static final native void registerApp(long var0, int var2, byte[] var3);

   public static final native void logEvent(long var0, long var2, byte var4, byte[] var5);

   public static final native int[] getSnapshot();

   public static final native void freeSnapshot(int[] var0);

   public static final native long[] getRegisteredGUIDs();

   public static final native long getGUID(int var0);

   public static final native long getTime(int var0);

   public static final native byte[] getData(int var0);

   public static final native byte getSeverity(int var0);

   public static final native void clear();
}
