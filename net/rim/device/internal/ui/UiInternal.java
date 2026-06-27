package net.rim.device.internal.ui;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.tid.im.layout.DefaultKeyLayout;
import net.rim.tid.im.layout.SLKeyLayout;
import net.rim.tid.im.layout.UILocaleKeyLayout;

public final class UiInternal {
   public static final ResourceBundleFamily BUNDLE;
   public static final int BM_CACHE_HITS;
   public static final int BM_CACHE_MISSES;
   public static final int BM_CACHE_FLUSH_COUNT;
   public static final int BM_CACHE_AVG_BITMAP_SIZE;
   public static final int BM_CACHE_AVG_BITMAP_COUNT_WHEN_FLUSHED;
   public static final int BM_CACHE_ADD_COUNT;
   public static final int BM_CACHE_TOTAL_CACHE_SIZE;
   public static final int BM_CACHE_CACHE_FREE;
   public static final int BM_CACHE_NUM_STATISTICS;

   public static final native void clearCacheStatistics();

   public static final int[] getCacheStatistics() {
      int[] var0 = new int[9];
      getCacheStatistics(var0);
      return var0;
   }

   public static final native void getCacheStatistics(int[] var0);

   public final native void nopFinal();

   public static final native void nopStatic();

   public static final native void promote(Bitmap var0, Graphics var1);

   public static final void setKeyStateIconsVisible(boolean var0) {
      ThemeManager.getActiveTheme().applyKeyStateIcons(var0);
   }

   public static final void setRadioIconsVisible(boolean var0) {
      ThemeManager.getActiveTheme().applyRadioIcons(var0);
   }

   private static final native void setThemeIcon(int var0, int var1, Bitmap[] var2);

   public static final void setThemeIcon(int var0, EncodedImage var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native void setThemeIconToDefault(int var0);

   public static final void frontBufferSnapshot(Graphics var0, int var1, int var2, int var3, int var4, Bitmap var5) {
      Display.screenshot(var5, var1, var2, var3, var4);
   }

   public static final native int getARGBPoint(Graphics var0, int var1, int var2);

   public static final native void lcdClearDisplay();

   public static final native void lcdPutStringXY(int var0, int var1, String var2);

   public static final char map(int var0) {
      return map(Keypad.key(var0), Keypad.status(var0));
   }

   public static final char mapFromFallbackLayout(int var0) {
      return mapFromFallbackLayout(Keypad.key(var0), Keypad.status(var0));
   }

   public static final char map(int var0, int var1) {
      return getKeyChars(var0, var1, false).charAt(0);
   }

   public static final char mapFromFallbackLayout(int var0, int var1) {
      return getKeyChars(var0, var1, true).charAt(0);
   }

   public static final void map(int var0, int var1, StringBuffer var2) {
      var2.append(getKeyChars(var0, var1, false));
   }

   public static final void mapFromFallbackLayout(int var0, int var1, StringBuffer var2) {
      var2.append(getKeyChars(var0, var1, true));
   }

   private static final StringBuffer getKeyChars(int var0, int var1, boolean var2) {
      var1 &= 28695;
      if ((var1 & 4) != 0 && (var1 & 16) != 0) {
         var1 &= -7;
      }

      if ((var1 & 4) != 0) {
         var1 &= -3;
         if ((var1 & 1) != 0) {
            var1 &= -5;
         }
      }

      if ((var1 & 16) != 0) {
         var1 &= -2;
         if ((var1 & 2) != 0) {
            var1 &= -17;
         }
      }

      SLKeyLayout var3 = var2 ? DefaultKeyLayout.getDefaultKeyLayout() : UILocaleKeyLayout.getUIKeyLayout();
      if (var3 == null) {
         Object var4 = new Object();
         ((StringBuffer)var4).append('\u0000');
         return (StringBuffer)var4;
      } else {
         return var3.getKeyChars(var0, SLKeyLayout.convertStatusToModifiers(var1), false);
      }
   }
}
