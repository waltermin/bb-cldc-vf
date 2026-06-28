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
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.ui.IconCollection;
import net.rim.device.internal.ui.Image;
import net.rim.device.internal.ui.NamedIconCollection;
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
      DefaultResourceFetcher fetcher = (DefaultResourceFetcher)(new Object());
      fetcher.setResourcesFromModule(TraceBack.getCallingModuleName(0));
      this._activeTheme = (Theme)(new Object(this._moduleDefaults, this._defaultImageDescriptors, fetcher));
      UiSettings.addListener(this._listeners);
      LowMemoryManager.addLowMemoryListener(this._listeners);
      Proxy.getInstance().addGlobalEventListener(this._listeners);
   }

   public static void activateTheme() {
      String saved = UiOptionsRegistry.getInstance().getString(-7276267599751932452L);
      if (saved != null) {
         try {
            setActiveTheme(saved);
         } catch (IllegalStateException var3) {
         }
      }

      if (!_instance._activated) {
         try {
            setActiveTheme(_instance.getDefaultId());
         } catch (IllegalStateException var2) {
         }
      }

      if (!_instance._activated) {
         Theme theme = createTheme(_instance._defaultThemeFactory);
         _instance._activeTheme = theme;
      }
   }

   public static void add(Theme$Factory factory) {
      ApplicationControl.assertThemeDataAllowed(CodeModuleManager.getModuleHandleForObject(factory));
      _instance.addInternal(factory);
   }

   private void addInternal(Theme$Factory factory) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void addLayoutFactory(Theme$LayoutFactory factory) {
      synchronized (_instance) {
         Arrays.add(_instance._layoutFactories, factory);
      }
   }

   public static void clearIconCollections() {
      Enumeration elements = _instance._iconCollections.elements();

      while (elements.hasMoreElements()) {
         IconCollection collection = (IconCollection)elements.nextElement();
         collection.clear();
      }
   }

   public static Theme createTheme(Theme$Factory factory) {
      Theme theme = (Theme)(new Object(_instance._moduleDefaults, _instance._defaultImageDescriptors, factory.getResourceFetcher()));
      theme.$init0();
      createThemeHelper(factory, theme);
      return theme;
   }

   public static void createThemeHelper(Theme$Factory factory, Theme theme) {
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
      for (int lv = _instance._factories.length - 1; lv >= 0; lv--) {
         Theme$Factory factory = _instance._factories[lv];
         if (factory.isVendorIDValid(Branding.getVendorId())) {
            return factory;
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

   private static int log2(int number) {
      number >>= 1;

      int log;
      for (log = 0; number != 0; log++) {
         number >>= 1;
      }

      return log;
   }

   public static String[] getNameChoices() {
      String[] result = new String[_instance._factories.length];

      for (int lv = result.length - 1; lv >= 0; lv--) {
         result[lv] = getPersistableIdForName(_instance._factories[lv].getName());
      }

      return result;
   }

   public static String[] getNameChoices(Locale locale) {
      String[] result = new String[_instance._factories.length];

      for (int lv = result.length - 1; lv >= 0; lv--) {
         result[lv] = _instance._factories[lv].getName(locale);
      }

      return result;
   }

   static synchronized IconCollection getIconCollection(String name) {
      IconCollection collection = (IconCollection)_instance._iconCollections.get(name);
      return collection;
   }

   public static IconCollection getIconCollection(String name, int columns, int rows, String moduleName) {
      synchronized (_instance) {
         NamedIconCollection collection = (NamedIconCollection)_instance._iconCollections.get(name);
         if (collection == null) {
            collection = (NamedIconCollection)(new Object(name, columns, rows, moduleName));
            getActiveTheme().initializeIconCollection(collection, moduleName);
            _instance._iconCollections.put(name, collection);
         } else {
            collection.verifyModule(moduleName);
         }

         return collection;
      }
   }

   private static int getIndex(String name) {
      if (name != null) {
         Theme$Factory[] factories = _instance._factories;

         for (int lv = factories.length - 1; lv >= 0; lv--) {
            String temp = getPersistableId(factories[lv]);
            if (name.equals(temp)) {
               return lv;
            }
         }
      }

      return -1;
   }

   public static Manager getLayout(String name, Object context) {
      Manager manager = null;
      Theme$LayoutFactory[] factories = _instance._layoutFactories;
      int last = factories.length;

      for (int lv = 0; lv < last && manager == null; lv++) {
         manager = factories[lv].getLayout(name, context);
      }

      return manager;
   }

   public static String getPersistableId(Theme$Factory factory) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static String getPersistableIdForName(String name) {
      int index = Arrays.binarySearch(_instance._factories, name, _instance._comparatorStringFactory, 0, _instance._factories.length);
      String id = null;
      if (index >= 0) {
         id = getPersistableId(_instance._factories[index]);
      }

      return id;
   }

   static Tag createTag(String tagName) {
      throw new RuntimeException("cod2jar: string-special");
   }

   static Tag getTag(String tagName) {
      if (tagName == null) {
         return ATTRIBUTE_INHERIT;
      }

      Tag tag = (Tag)_instance._nameToTag.get(tagName);
      return tag;
   }

   public static Theme getTheme(String name) {
      Theme$Factory factory = getThemeFactory(name);
      return factory != null ? createTheme(factory) : null;
   }

   public static Image getThemeAwareImage(String name) {
      String moduleName = TraceBack.getCallingModuleName(0);
      return (Image)(new Object(name, moduleName));
   }

   public static Theme$Factory getThemeFactory(String name) {
      if (name == null) {
         return null;
      }

      if (name.indexOf(58) == -1) {
         name = getPersistableIdForName(name);
      }

      int index = getIndex(name);
      return index != -1 ? _instance._factories[index] : null;
   }

   private void internalSetActiveTheme(Theme$Factory factory, boolean restore) {
      Theme theme = createTheme(factory);
      Theme oldTheme = this._activeTheme;
      this._activeThemeName = getPersistableId(factory);
      this._activeTheme = theme;
      this._generation++;
      UiOptionsRegistry.getInstance().setString(-7276267599751932452L, this._activeThemeName);
      if (oldTheme != null) {
         oldTheme.disposeFonts();
      }

      this._activeTheme.apply();
      if (!FontRegistry.isDefaultFontSet()) {
         resetDefaultFont();
      }

      UiOptionsRegistry.getInstance().setString(-3809895234519942708L, this._activeThemeName);
      RIMGlobalMessagePoster.postGlobalEvent(2573494863350550132L, restore ? 2 : 1, 0, null, null);
      if (oldTheme != null) {
         oldTheme.dispose();
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

   public static Bitmap getPredefinedBitmap(int id) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static boolean isActivatable(String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static void onSystemFontChangeInternal() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      getActiveTheme().applyFont();
   }

   public static void remove(String name) {
      if (name.indexOf(58) == -1) {
         name = getPersistableIdForName(name);
      }

      synchronized (_instance) {
         int index = getIndex(name);
         if (index >= 0) {
            Theme$Factory factory = _instance._factories[index];
            if (!factory.isRemovable()) {
               throw new Object();
            }

            boolean removingActive = _instance._defaultThemeFactory == factory;
            int rc = factory.remove();
            int message;
            if (rc == 0) {
               removeFromList(index, removingActive);
               message = 401;
            } else if (rc == 1) {
               removeFromList(index, removingActive);
               message = 402;
            } else {
               message = 403;
            }

            if (rc != 3) {
               UiApplication.getUiApplication().invokeLater((Runnable)(new Object(message)));
            }
         } else {
            throw new Object();
         }
      }
   }

   private static void removeFromList(int index, boolean removingActive) {
      Arrays.removeAt(_instance._factories, index);
      if (removingActive) {
         activateTheme();
      }
   }

   public static boolean isRemoveable(String name) {
      if (name.indexOf(58) == -1) {
         name = getPersistableIdForName(name);
      }

      synchronized (_instance) {
         int index = getIndex(name);
         if (index >= 0) {
            Theme$Factory factory = _instance._factories[index];
            return factory.isRemovable();
         } else {
            throw new Object();
         }
      }
   }

   public static boolean allowUserWallpaper(String name) {
      if (name == null) {
         return true;
      }

      if (name.indexOf(58) == -1) {
         name = getPersistableIdForName(name);
      }

      synchronized (_instance) {
         int index = getIndex(name);
         if (index >= 0) {
            Theme$Factory factory = _instance._factories[index];
            return factory.allowUserWallpaper();
         } else {
            return true;
         }
      }
   }

   private static void setActiveTheme(String name, boolean restore) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void setActiveTheme(String name) {
      setActiveTheme(name, false);
   }

   private void verifyActiveTheme() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
