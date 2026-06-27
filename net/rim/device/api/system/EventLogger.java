package net.rim.device.api.system;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.vm.EventLog;
import net.rim.vm.TraceBack;

public final class EventLogger {
   private int _logLevelId;
   private int _logLevel;
   private byte[] _intBuffer;
   private byte[] _longBuffer;
   private byte[] _byteBuffer;
   public static final long EVLV_GUID;
   public static final long SYSTEM_LOG_GUID;
   public static final int DEBUG_INFO;
   public static final int INFORMATION;
   public static final int WARNING;
   public static final int ERROR;
   public static final int SEVERE_ERROR;
   public static final int ALWAYS_LOG;
   public static final int VIEWER_NUMBER;
   public static final int VIEWER_STRING;
   public static final int VIEWER_EXCEPTION;
   private static long LOG_LEVEL;
   private static EventLogger _theLogger;
   private static EventLogger$MyPersistentContentListener _myPersistentContentListener;
   public static final long PERSISTENT_CONTENT_LISTENER_ID;

   EventLogger() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   public static final boolean register(long var0, String var2, int var3) {
      EventLog.registerApp(var0, var3, var2);
      return true;
   }

   public static final boolean register(long var0, String var2) {
      return register(var0, var2, 1);
   }

   public static final int getRegisteredViewerType(long var0) {
      return EventLog.getRegisteredAppEventType(var0);
   }

   public static final String getRegisteredAppName(long var0) {
      return EventLog.getRegisteredAppName(var0);
   }

   public static final boolean logEvent(long var0, byte[] var2, int var3) {
      return checkLevel(var3) ? true : logEventInternal(var0, var2, var3);
   }

   private static final boolean logEventInternal(long var0, byte[] var2, int var3) {
      if (var2 == null) {
         return false;
      }

      EventLog.logEvent(var0, System.currentTimeMillis(), (byte)var3, var2);
      return true;
   }

   public static final boolean logEvent(long var0, byte[] var2) {
      return logEvent(var0, var2, 0);
   }

   public static final boolean logEvent(long var0, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean logEvent(long var0, int var2, byte[] var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean logEvent(long var0, long var2, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean logEvent(long var0, int var2, int var3, int var4, int var5) {
      return logEvent(var0, var2, (long)var3, var4, var5);
   }

   public static final boolean logEvent(long var0, int var2, long var3, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean logEvent(long var0, int var2) {
      return logEvent(var0, var2, 0);
   }

   public static final void logStackTrace(long var0, String var2) {
      Object var3 = new Object();
      Object var4 = new Object((OutputStream)var3);
      if (var2 != null) {
         ((PrintStream)var4).println(var2);
      }

      TraceBack.printStackTrace((PrintStream)var4);
      ((PrintStream)var4).flush();
      logEvent(var0, ((ByteArrayOutputStream)var3).toByteArray());
   }

   private static final boolean checkLevel(int var0) {
      return var0 > getMinimumLevel();
   }

   public static final int getMinimumLevel() {
      return _theLogger._logLevel > 4 && _myPersistentContentListener.isEncryptionEnabled() ? 4 : _theLogger._logLevel;
   }

   public static final void setMinimumLevel(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void clearLog() {
      assertPermission();
      EventLog.clear();
   }

   public static final int getInt(byte[] var0) {
      int var1 = (var0[3] & 255) << 0;
      var1 += (var0[2] & 255) << 8;
      var1 += (var0[1] & 255) << 16;
      return var1 + ((var0[0] & 0xFF) << 24);
   }

   public static final boolean startEventLogViewer() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
