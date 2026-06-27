package net.rim.device.api.bugreport;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.ControlledAccess;
import net.rim.vm.TraceBack;

public class BugReportManager {
   public static final long GUID;
   private static BugReportable _appReportable;
   private static String _reportLocation;
   private static boolean _screenshotAllowed;

   protected BugReportManager() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(1));
   }

   public static BugReport createReport() {
      BugReportManager var0 = (BugReportManager)ApplicationRegistry.getApplicationRegistry().get(-483760535199085048L);
      return var0 != null ? var0.createReportInternal() : null;
   }

   public BugReport createReportInternal() {
      throw null;
   }

   public static BugReportable getApplicationReportable() {
      return _appReportable;
   }

   public static String getReportLocation() {
      return _reportLocation;
   }

   public static boolean isScreenshotAllowed() {
      return _screenshotAllowed;
   }

   public static void setApplicationReportable(BugReportable var0) {
      _appReportable = var0;
   }

   public static void setReportLocation(String var0) {
      _reportLocation = var0;
   }

   public static void setScreenshotAllowed(boolean var0) {
      _screenshotAllowed = var0;
   }
}
