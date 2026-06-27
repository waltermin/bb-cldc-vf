package net.rim.device.internal.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Menu;

public class Backdoor implements Runnable {
   private Menu _menu;
   private static final int DUMP_SCREEN;
   private static final int KEYSTROKE_TIMING;
   private static final int REPAINT;
   private static final int TIME_REPAINT;
   private static final int CACHE_STATS;
   private static final int CLEAR_CACHE_STATS;
   public static final int REPEAT_COUNT;
   private static final String ui_package;
   private static String[] CACHE_STAT_NAMES;

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void timeRepaint() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private String styleToString(long var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private String formatClassName(Object var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void dumpField(Field var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void validateManager(Manager var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private void validate(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static String sayTime(long var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void dumpCacheStats() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
