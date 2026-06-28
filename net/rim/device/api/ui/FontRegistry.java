package net.rim.device.api.ui;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntEnumeration;
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

   public static final int loadFont(String font, String location, String typefaceName) {
      return loadFont(font, location, typefaceName, false);
   }

   public static final int loadFont(String font, String location, String typefaceName, boolean isPublic) {
      Resource resourceClass = Resource$Internal.getResourceClass(location);
      if (resourceClass == null) {
         return -1;
      }

      byte[] data = resourceClass.getResource(font);
      int handle = CodeModuleManager.getModuleHandle(location);
      return loadFont(handle, data, typefaceName, isPublic);
   }

   public static final int loadSplitFont(String aFileName, int aFileCount, String aLocation, String aTypefaceName, boolean aIsPublic) {
      return getInstance().loadSplitFontInternal(aFileName, aFileCount, aLocation, aTypefaceName, aIsPublic);
   }

   private final synchronized int loadSplitFontInternal(String aFileName, int aFileCount, String aLocation, String aTypefaceName, boolean aIsPublic) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int loadFont(byte[] data, String typefaceName, boolean isPublic) {
      return getInstance().loadFont0(-1, data, typefaceName, isPublic);
   }

   public static final int loadFont(int codFileHandle, byte[] data, String typefaceName, boolean isPublic) {
      return getInstance().loadFont0(codFileHandle, data, typefaceName, isPublic);
   }

   public static final String getTypefaceName(int fontHandle) {
      return getInstance().getTypefaceName0(fontHandle);
   }

   private final String getTypefaceName0(int fontHandle) {
      Object obj;
      if (fontHandle >= 0 && (obj = this._fontInfo.get(fontHandle)) != null) {
         FontRegistry$FontInfo fi = (FontRegistry$FontInfo)obj;
         return fi._typefaceName;
      } else {
         return null;
      }
   }

   private final synchronized int loadFont0(int codFile, byte[] data, String typefaceName, boolean isPublic) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final boolean probablyIdentical(byte[] data1, byte[] data2) {
      if (data1 != null && data2 != null) {
         if (data1.length != data2.length) {
            return false;
         }

         int range = 10;
         if (data1.length < 30) {
            range = 1;
         }

         for (int i = 0; i < range; i++) {
            if (data1[0 + i] != data2[0 + i]) {
               return false;
            }

            if (data1[data1.length / 2 + i] != data2[data1.length / 2 + i]) {
               return false;
            }

            if (data1[data1.length - 1 - i] != data2[data1.length - 1 - i]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private final FontRegistry$FontInfo getFontInfo(String typefaceName, int occurance) {
      int index = 0;
      Enumeration e = this._fontInfo.elements();
      FontRegistry$FontInfo fi = null;

      while (e.hasMoreElements()) {
         fi = (FontRegistry$FontInfo)e.nextElement();
         if (typefaceName.equals(fi._typefaceName)) {
            if (++index == occurance) {
               return fi;
            }
         }
      }

      return null;
   }

   public static final int unloadFont(int aHandle) {
      return aHandle < 0 ? -1 : getInstance().unloadFont0(aHandle);
   }

   public static final int unloadFont(String typefaceName) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final int unloadFont0(String typefaceName) {
      int rc = -1;
      synchronized (this.getClass()) {
         IntEnumeration e = this._fontInfo.keys();
         FontRegistry$FontInfo fi = null;

         while (e.hasMoreElements()) {
            int key = e.nextElement();
            fi = (FontRegistry$FontInfo)this._fontInfo.get(key);
            if (typefaceName.equals(fi._typefaceName) && (rc = this.unloadFont0(key)) != 0) {
               break;
            }
         }

         return rc;
      }
   }

   private final int unloadFont0(int aHandle) {
      int rc = -1;
      synchronized (this.getClass()) {
         if (aHandle < 0) {
            return rc;
         } else {
            Object obj;
            if ((obj = this._fontInfo.get(aHandle)) != null) {
               this._fontInfo.remove(aHandle);
               return this.unloadFont0((FontRegistry$FontInfo)obj);
            } else {
               return rc;
            }
         }
      }
   }

   private final int unloadFont0(FontRegistry$FontInfo fi) {
      int rc = -1;
      Enumeration e = this._fontInfo.elements();
      int i = 0;
      FontRegistry$FontInfo tfi = null;

      while (e.hasMoreElements()) {
         tfi = (FontRegistry$FontInfo)e.nextElement();
         if (fi._renderingEngineHandle == tfi._renderingEngineHandle) {
            i++;
         }
      }

      if (i > 0) {
         return 0;
      }

      rc = unloadFontResource(fi._renderingEngineHandle);
      if (rc != 0) {
         return rc;
      }

      for (int j = 0; j < fi._count; j++) {
         this._fontData[fi._index + j] = null;
      }

      if (fi._codFileHandle != -1) {
         CodeModuleManager.deleteModuleEx(fi._codFileHandle, false);
      }

      this.reload();
      return rc;
   }

   public final synchronized int getTypefaceType(String aTypeface) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final synchronized int[] getHeightsForTypeface(String aTypeface) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final FontFamily get(String name) {
      Object result = null;
      FontRegistry registry = getInstance();
      synchronized (registry) {
         result = registry._table.get(name);
         if (result == null) {
            result = new FontFamily(name);
            registry._table.put(name, result);
         }
      }

      return (FontFamily)result;
   }

   public static final String getTypefaceNameByIndex(int id) {
      return getInstance()._typefaceNameTable[id];
   }

   public final int getTypefaceNameIndex(String typeface) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   static final Font _getDefaultFont() {
      FontRegistry registry = getInstance();
      if (_registry._defaultFont == null) {
         _registry._defaultFont = FontPersist.getDefaultFont();
         if (_registry._defaultFont == null) {
            return null;
         }
      }

      return _registry._defaultFont;
   }

   public static final Font getDefaultFont() {
      synchronized (getInstance()) {
         if (_registry._defaultFont == null) {
            _registry._defaultFont = FontPersist.getDefaultFont();
            if (_registry._defaultFont == null) {
               _registry._defaultFont = get(DEFAULT_FAMILY).getFont(DEFAULT_STYLE, DEFAULT_SIZE, 3);
            }
         }
      }

      return _registry._defaultFont;
   }

   public static final boolean isDefaultFontSet() {
      return FontPersist.getDefaultFont() != null;
   }

   public static final int getDefaultHeight(int units) {
      int dsize = DEFAULT_SIZE;
      if (getDefaultFont() != null) {
         dsize = getDefaultFont().getHeight();
      }

      return Ui.convertSize(dsize, 0, units);
   }

   public static final String[] getFontFamilies() {
      FontRegistry registry = getInstance();
      synchronized (registry) {
         Hashtable t = new Hashtable();
         Enumeration en = registry._fontInfo.elements();

         while (en.hasMoreElements()) {
            FontRegistry$FontInfo fi = (FontRegistry$FontInfo)en.nextElement();
            if (fi._isPublic) {
               t.put(fi._typefaceName, fi._typefaceName);
            }
         }

         String[] rc = new String[t.size()];
         Enumeration e = t.keys();
         int j = 0;

         while (e.hasMoreElements()) {
            rc[j++] = (String)e.nextElement();
         }

         Arrays.sort(rc, new FontRegistry$1());
         return rc;
      }
   }

   public static final void setDefaultFont(Font defaultFont) {
      synchronized (getInstance()) {
         if (_registry._defaultFont == defaultFont) {
            return;
         }

         _registry._defaultFont = defaultFont;
         FontPersist.setDefaultFont(defaultFont);
      }

      ThemeManager.onSystemFontChangeInternal();
      RIMGlobalMessagePoster.postGlobalEvent(-4394903006263251010L);
   }

   public static final void setDefaultFont(String family, int style, int height, int units) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final synchronized void reload() {
      Enumeration e = this._table.elements();

      while (e.hasMoreElements()) {
         ((FontFamily)e.nextElement()).reload();
      }
   }

   private static final native int loadFontResource(byte[] var0, String var1, boolean var2, int var3);

   private static final native int loadSplitFontResource(byte[][][] var0, String var1, boolean var2, int var3, int var4);

   private static final native int unloadFontResource(int var0);

   private static final native void setPreferredFallback(String var0, int var1);
}
