package net.rim.device.api.ui.theme;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FontRegistry;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.api.util.ToIntHashtable;
import net.rim.device.internal.ui.Border;
import net.rim.device.internal.ui.Image;
import net.rim.device.internal.ui.NamedIconCollection;
import net.rim.device.internal.util.StringUtilitiesInternal;
import net.rim.vm.TraceBack;
import net.rim.vm.WeakReference;

public class Theme {
   private Bitmap[] _themeBitmaps = new Bitmap[4];
   private int _borderStyle = 0;
   private int _themeLoadingCount = 0;
   private WeakReference _weakReference = new WeakReference(this);
   private LongHashtable _attributeSets = new LongHashtable(20);
   private Hashtable _moduleDefaults;
   private Hashtable _defaultImageDescriptors;
   private Hashtable _themeImageDescriptors = new Hashtable(200);
   private Hashtable _borders = new Hashtable(10);
   private Hashtable _scrollbars = new Hashtable(10);
   private Theme$LayoutFactory _layoutFactory;
   private Hashtable _fonts = new Hashtable(10);
   private Hashtable _registeredFontNames = new Hashtable(10);
   private int[] _fontHandles = new int[0];
   private Hashtable _ringtones = new Hashtable(10);
   private ToIntHashtable _palette = new ToIntHashtable(10);
   private ToIntHashtable _iconCollectionNames;
   private EncodedImage[] _osIcons = new EncodedImage[11];
   private int _osKeyIconWidth;
   private boolean _ribbonIconSizeSet;
   private int _ribbonIconWidth = 28;
   private int _ribbonIconHeight = 28;
   private boolean _isKeyStateIconsVisible = true;
   private boolean _isRadioIconsVisible = true;
   private boolean _isLabelOnOwnLine;
   private Theme$Writer _writer = new Theme$Writer(this);
   private boolean _idleScreenNameSet;
   private String _idleScreenName;
   private String _thumbnailName;
   private boolean _thumbnailNameSet;
   private Hashtable _aliasList = new Hashtable();
   private int _appIconSize;
   private Hashtable _appIcons = new Hashtable();
   private ResourceBundle _resourceBundle;
   private Hashtable _options = new Hashtable();
   public static final int FOCUS_STYLE_INVERT;
   public static final int FOCUS_STYLE_INVERT_STRIKE;
   public static final int FOCUS_STYLE_INVERT_BOX;
   public static final int FOCUS_STYLE_DRAW;
   public static final int FOCUS_STYLE_CUSTOM;
   public static final int FOCUS_STYLES;
   public static final int STATE_ALL;
   public static final int STATE_FIRST_CHILD;
   public static final int STATE_LINK;
   public static final int STATE_VISITED;
   public static final int STATE_ACTIVE;
   public static final int STATE_HOVER;
   public static final int STATE_FOCUS;
   public static final int STATE_DISABLED;
   public static final int STATE_DISABLED_FOCUS;
   public static final int TEXT_ALIGN_INHERIT;
   public static final int TEXT_ALIGN_LEFT;
   public static final int TEXT_ALIGN_RIGHT;
   public static final int TEXT_ALIGN_CENTER;
   public static final int TEXT_ALIGN_JUSTIFY;
   public static final int TEXT_ALIGN_LEADING;
   public static final int TEXT_ALIGN_TRAILING;
   public static final int THEME_UPARROW;
   public static final int THEME_DOWNARROW;
   public static final int THEME_SCROLLBAR_ELEVATOR;
   public static final int THEME_SCROLLBAR_INDICATOR;
   private static final int THEME_BITMAPS;
   private static final int BORDER_STYLES;
   public static final int BORDER_ROUNDED;
   public static final int BORDER_BOX;
   public static final int BORDER_CUSTOM;
   public static final int METHOD_COMPRESSED;
   public static final int METHOD_NO_DEFAULT;
   public static final int METHOD_FOLDER;
   public static final int METHOD_SCALE_TO_FIT;
   public static final int METHOD_CONVERT_TO_GREY;
   public static final int REMOVE_OK;
   public static final int REMOVE_LATER;
   public static final int REMOVE_FAILED;
   public static final int REMOVE_IGNORED;
   private static final int[] OS_ICONS;
   private static final int[] OS_ICONS_KEY_STATE;
   private static final int[] OS_ICONS_RADIO;

   Theme(Hashtable moduleDefaults, Hashtable defaultImageDescriptors, ResourceFetcher resourceFetcher) {
      this._moduleDefaults = moduleDefaults;
      this._defaultImageDescriptors = defaultImageDescriptors;
   }

   public final void $init0() {
      String moduleName = TraceBack.getCallingModuleName(0);
      this.addDefaultResources(moduleName);
   }

   private void setResourceBundle(ResourceBundle resourceBundle) {
      if (this._resourceBundle == null) {
         this._resourceBundle = resourceBundle;
      }
   }

   private synchronized void addDefaultResources(String moduleName) {
      if (!this._moduleDefaults.containsKey(moduleName)) {
         this._moduleDefaults.put(moduleName, this._moduleDefaults);
         DefaultResourceFetcher fetcher = new DefaultResourceFetcher(moduleName);
         this.addResources(fetcher, true);
      }
   }

   protected void addFont(ResourceFetcher resourceFetcher, String name) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void addImage(ResourceFetcher resourceFetcher, String name, boolean isDefault) {
      Theme$ImageDescriptor descriptor = new Theme$ImageDescriptor(name, resourceFetcher, isDefault);
      if (isDefault) {
         this._defaultImageDescriptors.put(descriptor.getName(), descriptor);
      } else {
         boolean passed = this._themeLoadingCount == 0;
         boolean isIconCollection = false;
         int iconIndex = name.indexOf(ThemeConstants.ICONS_SUFFIX);
         if (iconIndex >= 0) {
            isIconCollection = true;
            String subName = name.substring(0, iconIndex);
            if (this._iconCollectionNames == null) {
               this._iconCollectionNames = new ToIntHashtable(100);
            }

            int themeIndex = this._iconCollectionNames.get(subName);
            if (themeIndex < 0 || themeIndex == this._themeLoadingCount) {
               passed = true;
            }

            if (themeIndex < 0) {
               this._iconCollectionNames.put(subName, this._themeLoadingCount);
            }
         }

         if (!passed && !isIconCollection) {
            Theme$ImageDescriptor extant = (Theme$ImageDescriptor)this._themeImageDescriptors.get(descriptor.getName());
            if (extant == null) {
               passed = true;
            }
         }

         if (passed) {
            this._themeImageDescriptors.put(descriptor.getName(), descriptor);
         }
      }
   }

   synchronized void addResources(ResourceFetcher resourceFetcher, boolean isDefault) {
      Enumeration enumeration = resourceFetcher.listResources();

      while (enumeration.hasMoreElements()) {
         String name = (String)enumeration.nextElement();
         if (name.indexOf(47) == -1) {
            if (name.endsWith(ThemeConstants.EXT_GIF) || name.endsWith(ThemeConstants.EXT_PNG)) {
               this.addImage(resourceFetcher, name, isDefault);
            }

            if (name.endsWith(ThemeConstants.EXT_CBTF) || name.endsWith(ThemeConstants.EXT_SFF4)) {
               this.addFont(resourceFetcher, name);
            }

            if (name.endsWith(ThemeConstants.EXT_MID)) {
               this.addTune(resourceFetcher, name);
            }
         }
      }
   }

   protected void addTune(ResourceFetcher resourceFetcher, String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized void apply() {
      throw new RuntimeException("cod2jar: ldc");
   }

   synchronized String getRegisteredFontNameHack(String name) {
      String registeredName = (String)this._registeredFontNames.get(name);
      return registeredName != null ? registeredName : name;
   }

   synchronized void applyFont() {
      Enumeration enumeration = this._attributeSets.elements();

      while (enumeration.hasMoreElements()) {
         ThemeAttributeSet attributes = (ThemeAttributeSet)enumeration.nextElement();
         attributes.apply();
      }
   }

   private void applyIconCollections() {
      ThemeManager.clearIconCollections();
      Enumeration collections = ThemeManager.enumerateIconCollections();

      while (collections.hasMoreElements()) {
         NamedIconCollection collection = (NamedIconCollection)collections.nextElement();
         this.initializeIconCollectionHelper(collection, this._defaultImageDescriptors);
         this.initializeIconCollectionHelper(collection, this._themeImageDescriptors);
      }
   }

   public synchronized void initializeFastReset() {
      this.applyOsIcons(OS_ICONS, true);
      this.applyKeyStateIcons(this._isKeyStateIconsVisible);
      this.applyRadioIcons(this._isRadioIconsVisible);
   }

   public synchronized void applyKeyStateIcons(boolean visible) {
      this._isKeyStateIconsVisible = visible;
      this.applyOsIcons(OS_ICONS_KEY_STATE, visible);

      for (int lv = OS_ICONS_KEY_STATE.length - 1; lv >= 0; lv--) {
         int iconIndex = OS_ICONS_KEY_STATE[lv];
         EncodedImage image = this._osIcons[iconIndex];
         if (image != null) {
            this._osKeyIconWidth = Math.max(this._osKeyIconWidth, image.getWidth());
         }
      }
   }

   public synchronized void applyRadioIcons(boolean visible) {
      this._isRadioIconsVisible = visible;
      this.applyOsIcons(OS_ICONS_RADIO, visible);
   }

   private void applyOsIcons(int[] icons, boolean visible) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void applyOsIcon(String name, Theme$ImageDescriptor descriptor) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void applyUiIcon(String name, Theme$ImageDescriptor descriptor) {
      int id = -1;
      if (name.equals(ThemeConstants.NAVIGATION_UP_ARROW)) {
         id = 0;
      } else if (name.equals(ThemeConstants.NAVIGATION_DOWN_ARROW)) {
         id = 1;
      } else if (name.equals(ThemeConstants.SCROLLBAR_ELEVATOR)) {
         id = 2;
      } else if (name.equals(ThemeConstants.SCROLLBAR_INDICATOR)) {
         id = 3;
      }

      if (id != -1 && this._themeBitmaps[id] == null) {
         this._themeBitmaps[id] = descriptor.getImage().getBitmap();
      }
   }

   protected void calcIconSize(EncodedImage image) {
      if (!this._ribbonIconSizeSet) {
         if (image.getWidth() > this._ribbonIconWidth) {
            this._ribbonIconWidth = image.getWidth();
         }

         if (image.getHeight() > this._ribbonIconHeight) {
            this._ribbonIconHeight = image.getHeight();
         }
      }
   }

   void clearAppIconCache() {
      this._appIcons.clear();
   }

   void decrementThemeLoadingCount() {
      this._themeLoadingCount--;
      if (this._iconCollectionNames != null) {
         this._iconCollectionNames = null;
      }
   }

   public void dispose() {
      for (int lv = this._themeBitmaps.length - 1; lv >= 0; lv--) {
         this._themeBitmaps[lv] = null;
      }

      this._themeBitmaps = null;
      this._attributeSets.clear();
      this._themeImageDescriptors.clear();
      this._themeImageDescriptors = null;
      this._defaultImageDescriptors = null;
      this._moduleDefaults = null;
      this._borders.clear();
      this._borders = null;

      for (int lv = this._osIcons.length - 1; lv >= 0; lv--) {
         this._osIcons[lv] = null;
      }

      this._osIcons = null;
      this._fonts = null;
      this._registeredFontNames = null;
      this._ringtones = null;
   }

   public void disposeFonts() {
      for (int lv = this._fontHandles.length - 1; lv >= 0; lv--) {
         FontRegistry.unloadFont(this._fontHandles[lv]);
      }

      this._fontHandles = null;
   }

   boolean freeStaleObject(int priority) {
      boolean ret = false;
      Enumeration enumeration = this._attributeSets.elements();

      while (enumeration.hasMoreElements()) {
         ThemeAttributeSet tas = (ThemeAttributeSet)enumeration.nextElement();
         ret = tas.freeStaleObject(priority) || ret;
      }

      return ret;
   }

   private String getNameWithState(String name, int state) {
      StringBuffer _buffer = StringUtilitiesInternal.getScratchBuffer();
      synchronized (_buffer) {
         _buffer.append(name);
         _buffer.append('~');
         _buffer.append(this.getNameForState(state));
         String nameWithState = _buffer.toString();
         _buffer.setLength(0);
         return nameWithState;
      }
   }

   private String getNameWithAppState(String name, String appState) {
      if (appState == null) {
         return name;
      }

      StringBuffer _buffer = StringUtilitiesInternal.getScratchBuffer();
      synchronized (_buffer) {
         _buffer.append(name);
         _buffer.append('.');
         _buffer.append(appState);
         String nameWithAppState = _buffer.toString();
         _buffer.setLength(0);
         return nameWithAppState;
      }
   }

   public String getString(int id) {
      return this._resourceBundle.getString(id);
   }

   public String getOption(String key) {
      return (String)this._options.get(key);
   }

   public Image getApplicationIcon(String name, int state, int size, Image defaultValue, int method) {
      return this.getApplicationIcon(name, null, state, size, defaultValue, method);
   }

   public Image getApplicationIcon(String name, String appState, int state, int size, Image defaultValue, int method) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public ThemeAttributeSet getAttributeSet(Tag tag) {
      if (tag == null) {
         return null;
      }

      long lkey = this.getKey(tag.hashCode(), null, 0);
      return (ThemeAttributeSet)this._attributeSets.get(lkey);
   }

   public ThemeAttributeSet getAttributeSet(Tag tag, String idname, int state) {
      return this.getAttributeSet(tag, idname, state, false);
   }

   public ThemeAttributeSet getAttributeSet(Tag tag, String idname, int state, boolean allowsNull) {
      if (tag == null) {
         return null;
      }

      ThemeAttributeSet attributes = null;

      while (true) {
         long key = this.getKey(tag.hashCode(), idname, state);
         attributes = (ThemeAttributeSet)this._attributeSets.get(key);
         if (allowsNull || attributes != null) {
            break;
         }

         if (state != 0) {
            state = 0;
         } else if (idname != null) {
            idname = null;
         } else {
            if (tag.hashCode() == 0) {
               break;
            }

            tag = Tag.get(null);
         }
      }

      return attributes;
   }

   public ThemeAttributeSet getAttributeSet(Field element) {
      return !this._attributeSets.isEmpty() && element != null ? this.getAttributeSet(element.getTag(), element.getId(), element.getState()) : null;
   }

   public ThemeAttributeSet getAttributeSet(Field element, int state) {
      return !this._attributeSets.isEmpty() && element != null ? this.getAttributeSet(element.getTag(), element.getId(), state) : null;
   }

   public Bitmap getBitmap(String name) {
      Bitmap bitmap = null;
      EncodedImage image = this.getImage(name);
      if (image != null) {
         bitmap = image.getBitmap();
      }

      return bitmap;
   }

   public int getColor(String name) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public String getIdleScreenName() {
      return this._idleScreenName;
   }

   public String getThumbnailName() {
      return this._thumbnailName;
   }

   public EncodedImage getImage(String name) {
      String moduleName = TraceBack.getCallingModuleName(0);
      return this.getImage(name, moduleName, false);
   }

   public EncodedImage getImage(String name, boolean allowNull) {
      String moduleName = TraceBack.getCallingModuleName(0);
      return this.getImage(name, moduleName, allowNull);
   }

   public EncodedImage getImage(String name, String moduleName, boolean allowNull) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private long getKey(int tag, String idname, int state) {
      if (idname == null && state == 0) {
         return tag;
      }

      long key = tag | state << 24;
      if (idname != null) {
         key |= (long)idname.hashCode() << 32;
      }

      return key;
   }

   public Manager getLayout(String name, Object context) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int getRibbonIconHeight() {
      return this._ribbonIconHeight;
   }

   public int getRibbonIconWidth() {
      return this._ribbonIconWidth;
   }

   public Border getBorder(String name) {
      return (Border)this._borders.get(name);
   }

   public Bitmap[] getScrollbar(String name) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getBorderStyle() {
      return this._borderStyle;
   }

   public Enumeration ringtones() {
      return this._ringtones.keys();
   }

   public byte[] getRingtone(String name) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getWidthOfKeyStateIcons() {
      return this._osKeyIconWidth;
   }

   Theme$Writer getWriter() {
      return this._writer;
   }

   public Theme$Writer getWriterInternalDeprecated() {
      return this._writer;
   }

   private String getNameForState(int state) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static Bitmap getThemeBitmap(int type) {
      if (type >= 0 && type < 4) {
         return ThemeManager.getActiveTheme()._themeBitmaps[type];
      } else {
         throw new IllegalArgumentException();
      }
   }

   public WeakReference getWeakReference() {
      return this._weakReference;
   }

   void incrementThemeLoadingCount() {
      this._themeLoadingCount++;
   }

   void initializeIconCollection(NamedIconCollection collection, String moduleName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   void initializeIconCollectionHelper(NamedIconCollection collection, Hashtable descriptors) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public boolean isLabelOnOwnLine() {
      return this._isLabelOnOwnLine;
   }
}
