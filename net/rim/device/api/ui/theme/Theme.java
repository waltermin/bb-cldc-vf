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
import net.rim.vm.TraceBack;
import net.rim.vm.WeakReference;

public class Theme {
   private Bitmap[] _themeBitmaps = new Bitmap[4];
   private int _borderStyle = 0;
   private int _themeLoadingCount = 0;
   private WeakReference _weakReference = (WeakReference)(new Object(this));
   private LongHashtable _attributeSets = (LongHashtable)(new Object(20));
   private Hashtable _moduleDefaults;
   private Hashtable _defaultImageDescriptors;
   private Hashtable _themeImageDescriptors = (Hashtable)(new Object(200));
   private Hashtable _borders = (Hashtable)(new Object(10));
   private Hashtable _scrollbars = (Hashtable)(new Object(10));
   private Theme$LayoutFactory _layoutFactory;
   private Hashtable _fonts = (Hashtable)(new Object(10));
   private Hashtable _registeredFontNames = (Hashtable)(new Object(10));
   private int[] _fontHandles = new int[0];
   private Hashtable _ringtones = (Hashtable)(new Object(10));
   private ToIntHashtable _palette = (ToIntHashtable)(new Object(10));
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
   private Hashtable _aliasList = (Hashtable)(new Object());
   private int _appIconSize;
   private Hashtable _appIcons = (Hashtable)(new Object());
   private ResourceBundle _resourceBundle;
   private Hashtable _options = (Hashtable)(new Object());
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

   Theme(Hashtable var1, Hashtable var2, ResourceFetcher var3) {
      this._moduleDefaults = var1;
      this._defaultImageDescriptors = var2;
   }

   public final void $init0() {
      String var1 = TraceBack.getCallingModuleName(0);
      this.addDefaultResources(var1);
   }

   private void setResourceBundle(ResourceBundle var1) {
      if (this._resourceBundle == null) {
         this._resourceBundle = var1;
      }
   }

   private synchronized void addDefaultResources(String var1) {
      if (!this._moduleDefaults.containsKey(var1)) {
         this._moduleDefaults.put(var1, this._moduleDefaults);
         DefaultResourceFetcher var2 = new DefaultResourceFetcher(var1);
         this.addResources(var2, true);
      }
   }

   protected void addFont(ResourceFetcher var1, String var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   protected void addImage(ResourceFetcher var1, String var2, boolean var3) {
      Theme$ImageDescriptor var4 = new Theme$ImageDescriptor(var2, var1, var3);
      if (var3) {
         this._defaultImageDescriptors.put(var4.getName(), var4);
      } else {
         boolean var6 = this._themeLoadingCount == 0;
         boolean var7 = false;
         int var8 = var2.indexOf(ThemeConstants.ICONS_SUFFIX);
         if (var8 >= 0) {
            var7 = true;
            String var5 = var2.substring(0, var8);
            if (this._iconCollectionNames == null) {
               this._iconCollectionNames = (ToIntHashtable)(new Object(100));
            }

            int var9 = this._iconCollectionNames.get(var5);
            if (var9 < 0 || var9 == this._themeLoadingCount) {
               var6 = true;
            }

            if (var9 < 0) {
               this._iconCollectionNames.put(var5, this._themeLoadingCount);
            }
         }

         if (!var6 && !var7) {
            Theme$ImageDescriptor var10 = (Theme$ImageDescriptor)this._themeImageDescriptors.get(var4.getName());
            if (var10 == null) {
               var6 = true;
            }
         }

         if (var6) {
            this._themeImageDescriptors.put(var4.getName(), var4);
         }
      }
   }

   synchronized void addResources(ResourceFetcher var1, boolean var2) {
      Enumeration var3 = var1.listResources();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         if (((String)var4).indexOf(47) == -1) {
            if (((String)var4).endsWith(ThemeConstants.EXT_GIF) || ((String)var4).endsWith(ThemeConstants.EXT_PNG)) {
               this.addImage(var1, (String)var4, var2);
            }

            if (((String)var4).endsWith(ThemeConstants.EXT_CBTF) || ((String)var4).endsWith(ThemeConstants.EXT_SFF4)) {
               this.addFont(var1, (String)var4);
            }

            if (((String)var4).endsWith(ThemeConstants.EXT_MID)) {
               this.addTune(var1, (String)var4);
            }
         }
      }
   }

   protected void addTune(ResourceFetcher var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized void apply() {
      throw new RuntimeException("cod2jar: ldc");
   }

   synchronized String getRegisteredFontNameHack(String var1) {
      Object var2 = this._registeredFontNames.get(var1);
      return (String)(var2 != null ? var2 : var1);
   }

   synchronized void applyFont() {
      Enumeration var1 = this._attributeSets.elements();

      while (var1.hasMoreElements()) {
         Object var2 = var1.nextElement();
         ((ThemeAttributeSet)var2).apply();
      }
   }

   private void applyIconCollections() {
      ThemeManager.clearIconCollections();
      Enumeration var1 = ThemeManager.enumerateIconCollections();

      while (var1.hasMoreElements()) {
         Object var2 = var1.nextElement();
         this.initializeIconCollectionHelper((NamedIconCollection)var2, this._defaultImageDescriptors);
         this.initializeIconCollectionHelper((NamedIconCollection)var2, this._themeImageDescriptors);
      }
   }

   public synchronized void initializeFastReset() {
      this.applyOsIcons(OS_ICONS, true);
      this.applyKeyStateIcons(this._isKeyStateIconsVisible);
      this.applyRadioIcons(this._isRadioIconsVisible);
   }

   public synchronized void applyKeyStateIcons(boolean var1) {
      this._isKeyStateIconsVisible = var1;
      this.applyOsIcons(OS_ICONS_KEY_STATE, var1);

      for (int var2 = OS_ICONS_KEY_STATE.length - 1; var2 >= 0; var2--) {
         int var3 = OS_ICONS_KEY_STATE[var2];
         EncodedImage var4 = this._osIcons[var3];
         if (var4 != null) {
            this._osKeyIconWidth = Math.max(this._osKeyIconWidth, var4.getWidth());
         }
      }
   }

   public synchronized void applyRadioIcons(boolean var1) {
      this._isRadioIconsVisible = var1;
      this.applyOsIcons(OS_ICONS_RADIO, var1);
   }

   private void applyOsIcons(int[] var1, boolean var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void applyOsIcon(String var1, Theme$ImageDescriptor var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void applyUiIcon(String var1, Theme$ImageDescriptor var2) {
      byte var3 = -1;
      if (var1.equals(ThemeConstants.NAVIGATION_UP_ARROW)) {
         var3 = 0;
      } else if (var1.equals(ThemeConstants.NAVIGATION_DOWN_ARROW)) {
         var3 = 1;
      } else if (var1.equals(ThemeConstants.SCROLLBAR_ELEVATOR)) {
         var3 = 2;
      } else if (var1.equals(ThemeConstants.SCROLLBAR_INDICATOR)) {
         var3 = 3;
      }

      if (var3 != -1 && this._themeBitmaps[var3] == null) {
         this._themeBitmaps[var3] = var2.getImage().getBitmap();
      }
   }

   protected void calcIconSize(EncodedImage var1) {
      if (!this._ribbonIconSizeSet) {
         if (var1.getWidth() > this._ribbonIconWidth) {
            this._ribbonIconWidth = var1.getWidth();
         }

         if (var1.getHeight() > this._ribbonIconHeight) {
            this._ribbonIconHeight = var1.getHeight();
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
      for (int var1 = this._themeBitmaps.length - 1; var1 >= 0; var1--) {
         this._themeBitmaps[var1] = null;
      }

      this._themeBitmaps = null;
      this._attributeSets.clear();
      this._themeImageDescriptors.clear();
      this._themeImageDescriptors = null;
      this._defaultImageDescriptors = null;
      this._moduleDefaults = null;
      this._borders.clear();
      this._borders = null;

      for (int var2 = this._osIcons.length - 1; var2 >= 0; var2--) {
         this._osIcons[var2] = null;
      }

      this._osIcons = null;
      this._fonts = null;
      this._registeredFontNames = null;
      this._ringtones = null;
   }

   public void disposeFonts() {
      for (int var1 = this._fontHandles.length - 1; var1 >= 0; var1--) {
         FontRegistry.unloadFont(this._fontHandles[var1]);
      }

      this._fontHandles = null;
   }

   boolean freeStaleObject(int var1) {
      boolean var2 = false;
      Enumeration var3 = this._attributeSets.elements();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         var2 = ((ThemeAttributeSet)var4).freeStaleObject(var1) || var2;
      }

      return var2;
   }

   private String getNameWithState(String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private String getNameWithAppState(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getString(int var1) {
      return this._resourceBundle.getString(var1);
   }

   public String getOption(String var1) {
      return (String)this._options.get(var1);
   }

   public Image getApplicationIcon(String var1, int var2, int var3, Image var4, int var5) {
      return this.getApplicationIcon(var1, null, var2, var3, var4, var5);
   }

   public Image getApplicationIcon(String var1, String var2, int var3, int var4, Image var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public ThemeAttributeSet getAttributeSet(Tag var1) {
      if (var1 == null) {
         return null;
      }

      long var2 = this.getKey(var1.hashCode(), null, 0);
      return (ThemeAttributeSet)this._attributeSets.get(var2);
   }

   public ThemeAttributeSet getAttributeSet(Tag var1, String var2, int var3) {
      return this.getAttributeSet(var1, var2, var3, false);
   }

   public ThemeAttributeSet getAttributeSet(Tag var1, String var2, int var3, boolean var4) {
      if (var1 == null) {
         return null;
      }

      Object var5 = null;

      while (true) {
         long var6 = this.getKey(var1.hashCode(), var2, var3);
         var5 = this._attributeSets.get(var6);
         if (var4 || var5 != null) {
            break;
         }

         if (var3 != 0) {
            var3 = 0;
         } else if (var2 != null) {
            var2 = null;
         } else {
            if (var1.hashCode() == 0) {
               break;
            }

            var1 = Tag.get(null);
         }
      }

      return (ThemeAttributeSet)var5;
   }

   public ThemeAttributeSet getAttributeSet(Field var1) {
      return !this._attributeSets.isEmpty() && var1 != null ? this.getAttributeSet(var1.getTag(), var1.getId(), var1.getState()) : null;
   }

   public ThemeAttributeSet getAttributeSet(Field var1, int var2) {
      return !this._attributeSets.isEmpty() && var1 != null ? this.getAttributeSet(var1.getTag(), var1.getId(), var2) : null;
   }

   public Bitmap getBitmap(String var1) {
      Bitmap var2 = null;
      EncodedImage var3 = this.getImage(var1);
      if (var3 != null) {
         var2 = var3.getBitmap();
      }

      return var2;
   }

   public int getColor(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public String getIdleScreenName() {
      return this._idleScreenName;
   }

   public String getThumbnailName() {
      return this._thumbnailName;
   }

   public EncodedImage getImage(String var1) {
      String var2 = TraceBack.getCallingModuleName(0);
      return this.getImage(var1, var2, false);
   }

   public EncodedImage getImage(String var1, boolean var2) {
      String var3 = TraceBack.getCallingModuleName(0);
      return this.getImage(var1, var3, var2);
   }

   public EncodedImage getImage(String var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private long getKey(int var1, String var2, int var3) {
      if (var2 == null && var3 == 0) {
         return var1;
      }

      long var4 = var1 | var3 << 24;
      if (var2 != null) {
         var4 |= (long)var2.hashCode() << 32;
      }

      return var4;
   }

   public Manager getLayout(String var1, Object var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int getRibbonIconHeight() {
      return this._ribbonIconHeight;
   }

   public int getRibbonIconWidth() {
      return this._ribbonIconWidth;
   }

   public Border getBorder(String var1) {
      return (Border)this._borders.get(var1);
   }

   public Bitmap[] getScrollbar(String var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getBorderStyle() {
      return this._borderStyle;
   }

   public Enumeration ringtones() {
      return this._ringtones.keys();
   }

   public byte[] getRingtone(String var1) {
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

   private String getNameForState(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static Bitmap getThemeBitmap(int var0) {
      if (var0 >= 0 && var0 < 4) {
         return ThemeManager.getActiveTheme()._themeBitmaps[var0];
      } else {
         throw new Object();
      }
   }

   public WeakReference getWeakReference() {
      return this._weakReference;
   }

   void incrementThemeLoadingCount() {
      this._themeLoadingCount++;
   }

   void initializeIconCollection(NamedIconCollection var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   void initializeIconCollectionHelper(NamedIconCollection var1, Hashtable var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public boolean isLabelOnOwnLine() {
      return this._isLabelOnOwnLine;
   }
}
