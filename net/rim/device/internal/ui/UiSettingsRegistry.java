package net.rim.device.internal.ui;

public class UiSettingsRegistry {
   private UiSettingsRegistry() {
   }

   public static int getParamInt(long var0, int var2) {
      return UiOptionsRegistry.getInstance().getInt(var0);
   }

   public static String getParamString(long var0) {
      return UiOptionsRegistry.getInstance().getString(var0);
   }

   public static void setParamInt(long var0, int var2) {
      UiOptionsRegistry.getInstance().setInt(var0, var2);
   }

   public static void setParamString(long var0, String var2) {
      UiOptionsRegistry.getInstance().setString(var0, var2);
   }
}
