package net.rim.vm;

import java.io.InputStream;
import java.io.OutputStream;

public class DebugSupport {
   private DebugSupport() {
   }

   public static native boolean isDesktopVM();

   public static native void benchmarkHook(int var0);

   public static native void benchmarkHook(int var0, Object var1);

   public static native void setDebugMode(boolean var0);

   private static native void logToFile(byte[] var0, byte[] var1);

   public static void log(String var0, String var1) {
      logToFile(var0.getBytes(), var1.getBytes());
   }

   public static native void addProfileCount(long var0);

   public static InputStream getInputStream(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static OutputStream getOutputStream(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static native void logStackTraces();

   public static native void dumpLoggedStackTraces();

   public static native String getenv(String var0);

   public static native boolean isDebuggerAttached();

   private static native int openFile(byte[] var0, boolean var1);

   private static native void write(int var0, byte[] var1, int var2, int var3);

   private static native int read(int var0, byte[] var1, int var2, int var3);

   private static native void closeFile(int var0);

   public static native int exec(byte[] var0);

   public static native void unlink(byte[] var0);

   public static native void startTimingNatives();

   public static native void stopTimingNatives();
}
