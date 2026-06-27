package net.rim.device.api.ui;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.resources.Resource;
import net.rim.device.resources.Resource$Internal;

public final class FontRegistry {
   private byte[][][] _fontData;
   private int _index;
   private String[] _typefaceNameTable;
   private int _typefaceCount;
   private Font _defaultFont;
   private Hashtable _table;
   private boolean isShufle;
   private IntHashtable _fontInfo;
   private int _handle;
   public static final int NO_FONT_DATA;
   public static final int ALREADY_LOADED;
   public static final int FONTS_ARRAY_FULL;
   public static final int MISSING_TYPEFACE_NAME;
   public static final int ILLEGAL_NUMBER_OF_FILES;
   public static final int FAILED_TO_LOAD_FILE;
   public static String DEFAULT_FAMILY;
   public static final int DEFAULT_SIZE;
   public static final int DEFAULT_STYLE;
   private static final long REGISTRY_NAME;
   private static final int MAX_FONTS;
   private static final int MAX_TYPEFACE_NAMES;
   private static final int CBTF_FONT_SIGNATURE;
   private static final int SFF4_FONT_SIGNATURE;
   private static final int TTF_FONT_SIGNATURE;
   private static FontRegistry _registry;

   public static final FontRegistry getInstance() {
      return _registry;
   }

   private final native void submit();

   public static final int loadFont(String var0, String var1, String var2) {
      return loadFont(var0, var1, var2, false);
   }

   public static final int loadFont(String var0, String var1, String var2, boolean var3) {
      Resource var4 = Resource$Internal.getResourceClass(var1);
      if (var4 == null) {
         return -1;
      }

      byte[] var5 = var4.getResource(var0);
      int var6 = CodeModuleManager.getModuleHandle(var1);
      return loadFont(var6, var5, var2, var3);
   }

   public static final int loadSplitFont(String var0, int var1, String var2, String var3, boolean var4) {
      return getInstance().loadSplitFontInternal(var0, var1, var2, var3, var4);
   }

   private final synchronized int loadSplitFontInternal(String var1, int var2, String var3, String var4, boolean var5) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final int loadFont(byte[] var0, String var1, boolean var2) {
      return getInstance().loadFont0(-1, var0, var1, var2);
   }

   public static final int loadFont(int var0, byte[] var1, String var2, boolean var3) {
      return getInstance().loadFont0(var0, var1, var2, var3);
   }

   public static final String getTypefaceName(int var0) {
      return getInstance().getTypefaceName0(var0);
   }

   private final String getTypefaceName0(int var1) {
      Object var2;
      if (var1 >= 0 && (var2 = this._fontInfo.get(var1)) != null) {
         FontRegistry$FontInfo var3 = (FontRegistry$FontInfo)var2;
         return var3._typefaceName;
      } else {
         return null;
      }
   }

   private final synchronized int loadFont0(int var1, byte[] var2, String var3, boolean var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final boolean probablyIdentical(byte[] var0, byte[] var1) {
      if (var0 != null && var1 != null) {
         if (var0.length != var1.length) {
            return false;
         }

         byte var2 = 10;
         if (var0.length < 30) {
            var2 = 1;
         }

         for (int var3 = 0; var3 < var2; var3++) {
            if (var0[0 + var3] != var1[0 + var3]) {
               return false;
            }

            if (var0[var0.length / 2 + var3] != var1[var0.length / 2 + var3]) {
               return false;
            }

            if (var0[var0.length - 1 - var3] != var1[var0.length - 1 - var3]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private final FontRegistry$FontInfo getFontInfo(String var1, int var2) {
      int var3 = 0;
      Enumeration var4 = this._fontInfo.elements();
      FontRegistry$FontInfo var5 = null;

      while (var4.hasMoreElements()) {
         var5 = (FontRegistry$FontInfo)var4.nextElement();
         if (var1.equals(var5._typefaceName)) {
            if (++var3 == var2) {
               return var5;
            }
         }
      }

      return null;
   }

   public static final int unloadFont(int var0) {
      return var0 < 0 ? -1 : getInstance().unloadFont0(var0);
   }

   public static final int unloadFont(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final int unloadFont0(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int unloadFont0(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int unloadFont0(FontRegistry$FontInfo var1) {
      int var2 = -1;
      Enumeration var3 = this._fontInfo.elements();
      int var4 = 0;
      Object var5 = null;

      while (var3.hasMoreElements()) {
         var5 = (FontRegistry$FontInfo)var3.nextElement();
         if (var1._renderingEngineHandle == ((FontRegistry$FontInfo)var5)._renderingEngineHandle) {
            var4++;
         }
      }

      if (var4 > 0) {
         return 0;
      }

      var2 = unloadFontResource(var1._renderingEngineHandle);
      if (var2 != 0) {
         return var2;
      }

      for (int var6 = 0; var6 < var1._count; var6++) {
         this._fontData[var1._index + var6] = null;
      }

      if (var1._codFileHandle != -1) {
         CodeModuleManager.deleteModuleEx(var1._codFileHandle, false);
      }

      this.reload();
      return var2;
   }

   public final synchronized int getTypefaceType(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final synchronized int[] getHeightsForTypeface(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final FontFamily get(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getTypefaceNameByIndex(int var0) {
      return getInstance()._typefaceNameTable[var0];
   }

   public final int getTypefaceNameIndex(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   static final Font _getDefaultFont() {
      FontRegistry var0 = getInstance();
      if (_registry._defaultFont == null) {
         _registry._defaultFont = FontPersist.getDefaultFont();
         if (_registry._defaultFont == null) {
            return null;
         }
      }

      return _registry._defaultFont;
   }

   public static final Font getDefaultFont() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isDefaultFontSet() {
      return FontPersist.getDefaultFont() != null;
   }

   public static final int getDefaultHeight(int var0) {
      int var1 = DEFAULT_SIZE;
      if (getDefaultFont() != null) {
         var1 = getDefaultFont().getHeight();
      }

      return Ui.convertSize(var1, 0, var0);
   }

   public static final String[] getFontFamilies() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void setDefaultFont(Font var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void setDefaultFont(String var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final synchronized void reload() {
      Enumeration var1 = this._table.elements();

      while (var1.hasMoreElements()) {
         ((FontFamily)var1.nextElement()).reload();
      }
   }

   private static final native int loadFontResource(byte[] var0, String var1, boolean var2, int var3);

   private static final native int loadSplitFontResource(byte[][][] var0, String var1, boolean var2, int var3, int var4);

   private static final native int unloadFontResource(int var0);

   private static final native void setPreferredFallback(String var0, int var1);
}
