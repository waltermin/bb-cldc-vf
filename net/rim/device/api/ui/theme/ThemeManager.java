package net.rim.device.api.ui.theme;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.lowmemory.LowMemoryManager;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Branding;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.FontRegistry;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.ui.IconCollection;
import net.rim.device.internal.ui.Image;
import net.rim.device.internal.ui.UiOptionsRegistry;
import net.rim.device.internal.ui.UiSettings;
import net.rim.vm.TraceBack;

public class ThemeManager {
   private Theme$Factory[] _factories = new Theme$Factory[0];
   private Theme$LayoutFactory[] _layoutFactories = new Theme$LayoutFactory[0];
   private String _activeThemeName;
   private Theme _activeTheme;
   private boolean _activated;
   private Theme$Factory _defaultThemeFactory;
   private int _generation = 1;
   private Hashtable _iconCollections = (Hashtable)(new Object(60));
   private int _nextTagId = 2;
   private IntHashtable _intToTag = (IntHashtable)(new Object(80));
   private Hashtable _nameToTag = (Hashtable)(new Object(80));
   private ThemeManager$Listeners _listeners = (ThemeManager$Listeners)(new Object());
   private Hashtable _moduleDefaults = (Hashtable)(new Object(10));
   private Hashtable _defaultImageDescriptors = (Hashtable)(new Object(200));
   private final Comparator _comparatorFactoryFactory = (Comparator)(new Object(this));
   private final Comparator _comparatorStringFactory = (Comparator)(new Object(this));
   public static final long GUID_THEME_CHANGED;
   public static final long GUID_THEME_ADDED;
   public static final long GUID_THEME_RESET;
   public static final int THEME_CHANGED_FROM_ACTION;
   public static final int THEME_CHANGED_FROM_RESTORE;
   public static final Tag ATTRIBUTE_INHERIT;
   private static final Tag ATTRIBUTE_ROOT;
   private static final long GUID;
   private static ThemeManager _instance;

   protected ThemeManager() {
      this.$initColors();
      this._nameToTag.put(ATTRIBUTE_ROOT.toString(), ATTRIBUTE_ROOT);
      this._intToTag.put(ATTRIBUTE_ROOT.hashCode(), ATTRIBUTE_ROOT);
      this.verifyActiveTheme();
      this._defaultThemeFactory = (Theme$Factory)(new Object());
      this.addInternal(this._defaultThemeFactory);
      Object var1 = new Object();
      ((DefaultResourceFetcher)var1).setResourcesFromModule(TraceBack.getCallingModuleName(0));
      this._activeTheme = (Theme)(new Object(this._moduleDefaults, this._defaultImageDescriptors, (ResourceFetcher)var1));
      UiSettings.addListener(this._listeners);
      LowMemoryManager.addLowMemoryListener(this._listeners);
      Proxy.getInstance().addGlobalEventListener(this._listeners);
   }

   public static void activateTheme() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void add(Theme$Factory var0) {
      ApplicationControl.assertThemeDataAllowed(CodeModuleManager.getModuleHandleForObject(var0));
      _instance.addInternal(var0);
   }

   private void addInternal(Theme$Factory var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void addLayoutFactory(Theme$LayoutFactory var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void clearIconCollections() {
      Enumeration var0 = _instance._iconCollections.elements();

      while (var0.hasMoreElements()) {
         Object var1 = var0.nextElement();
         ((IconCollection)var1).clear();
      }
   }

   public static Theme createTheme(Theme$Factory var0) {
      Object var1 = new Object(_instance._moduleDefaults, _instance._defaultImageDescriptors, var0.getResourceFetcher());
      ((Theme)var1).$init0();
      createThemeHelper(var0, (Theme)var1);
      return (Theme)var1;
   }

   public static void createThemeHelper(Theme$Factory var0, Theme var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static Enumeration enumerateIconCollections() {
      return _instance._iconCollections.elements();
   }

   public static String getActiveName() {
      return _instance._activeThemeName;
   }

   public static Theme getActiveTheme() {
      return _instance._activeTheme;
   }

   public static int getCount() {
      return _instance._factories.length;
   }

   private Theme$Factory getBrandingTheme() {
      for (int var1 = _instance._factories.length - 1; var1 >= 0; var1--) {
         Theme$Factory var2 = _instance._factories[var1];
         if (var2.isVendorIDValid(Branding.getVendorId())) {
            return var2;
         }
      }

      return null;
   }

   public static String getNameOfDefaulTheme() {
      return _instance.getDefaultId();
   }

   private String getDefaultId() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static int log2(int var0) {
      var0 >>= 1;

      int var1;
      for (var1 = 0; var0 != 0; var1++) {
         var0 >>= 1;
      }

      return var1;
   }

   public static String[] getNameChoices() {
      String[] var0 = new String[_instance._factories.length];

      for (int var1 = var0.length - 1; var1 >= 0; var1--) {
         var0[var1] = getPersistableIdForName(_instance._factories[var1].getName());
      }

      return var0;
   }

   public static String[] getNameChoices(Locale var0) {
      String[] var1 = new String[_instance._factories.length];

      for (int var2 = var1.length - 1; var2 >= 0; var2--) {
         var1[var2] = _instance._factories[var2].getName(var0);
      }

      return var1;
   }

   static synchronized IconCollection getIconCollection(String var0) {
      return (IconCollection)_instance._iconCollections.get(var0);
   }

   public static IconCollection getIconCollection(String var0, int var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static int getIndex(String var0) {
      if (var0 != null) {
         Theme$Factory[] var1 = _instance._factories;

         for (int var2 = var1.length - 1; var2 >= 0; var2--) {
            String var3 = getPersistableId(var1[var2]);
            if (var0.equals(var3)) {
               return var2;
            }
         }
      }

      return -1;
   }

   public static Manager getLayout(String var0, Object var1) {
      Manager var2 = null;
      Theme$LayoutFactory[] var3 = _instance._layoutFactories;
      int var4 = var3.length;

      for (int var5 = 0; var5 < var4 && var2 == null; var5++) {
         var2 = var3[var5].getLayout(var0, var1);
      }

      return var2;
   }

   public static String getPersistableId(Theme$Factory var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static String getPersistableIdForName(String var0) {
      int var1 = Arrays.binarySearch(_instance._factories, var0, _instance._comparatorStringFactory, 0, _instance._factories.length);
      String var2 = null;
      if (var1 >= 0) {
         var2 = getPersistableId(_instance._factories[var1]);
      }

      return var2;
   }

   static Tag createTag(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static Tag getTag(String var0) {
      return (Tag)(var0 == null ? ATTRIBUTE_INHERIT : _instance._nameToTag.get(var0));
   }

   public static Theme getTheme(String var0) {
      Theme$Factory var1 = getThemeFactory(var0);
      return var1 != null ? createTheme(var1) : null;
   }

   public static Image getThemeAwareImage(String var0) {
      String var1 = TraceBack.getCallingModuleName(0);
      return (Image)(new Object(var0, var1));
   }

   public static Theme$Factory getThemeFactory(String var0) {
      if (var0 == null) {
         return null;
      }

      if (var0.indexOf(58) == -1) {
         var0 = getPersistableIdForName(var0);
      }

      int var1 = getIndex(var0);
      return var1 != -1 ? _instance._factories[var1] : null;
   }

   private void internalSetActiveTheme(Theme$Factory var1, boolean var2) {
      Theme var3 = createTheme(var1);
      Theme var4 = this._activeTheme;
      this._activeThemeName = getPersistableId(var1);
      this._activeTheme = var3;
      this._generation++;
      UiOptionsRegistry.getInstance().setString(-7276267599751932452L, this._activeThemeName);
      if (var4 != null) {
         var4.disposeFonts();
      }

      this._activeTheme.apply();
      if (!FontRegistry.isDefaultFontSet()) {
         resetDefaultFont();
      }

      UiOptionsRegistry.getInstance().setString(-3809895234519942708L, this._activeThemeName);
      RIMGlobalMessagePoster.postGlobalEvent(2573494863350550132L, var2 ? 2 : 1, 0, null, null);
      if (var4 != null) {
         var4.dispose();
      }
   }

   public static void resetDefaultFont() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void $initColors() {
   }

   public static int getGeneration() {
      return _instance._generation;
   }

   public static Bitmap getPredefinedBitmap(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static boolean isActivatable(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void onSystemFontChangeInternal() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      getActiveTheme().applyFont();
   }

   public static void remove(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void removeFromList(int var0, boolean var1) {
      Arrays.removeAt(_instance._factories, var0);
      if (var1) {
         activateTheme();
      }
   }

   public static boolean isRemoveable(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean allowUserWallpaper(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void setActiveTheme(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void setActiveTheme(String var0) {
      setActiveTheme(var0, false);
   }

   private void verifyActiveTheme() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
