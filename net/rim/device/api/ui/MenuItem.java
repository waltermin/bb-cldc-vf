package net.rim.device.api.ui;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.accessibility.AccessibleValue;
import net.rim.device.api.ui.menu.MenuItemPrefab;
import net.rim.device.api.util.Comparator;
import net.rim.device.internal.ui.Image;

public class MenuItem implements Runnable, AccessibleContext {
   private ResourceBundle _bundle;
   private int _id;
   private int _ordinal;
   private int _priority;
   private Locale _locale;
   private String _text;
   private Image _icon;
   public static final byte SPELL_CHECK_ITEMS;
   private static String SEPARATOR_STRING;
   public static final int SEPARATOR_ID;
   public static final int CHANGE_OPTION;
   public static final int COPY;
   public static final int CUT;
   public static final int PASTE;
   public static final int SELECT;
   public static final int CANCEL_SELECT;
   public static final int SPELL_RUN;
   public static final int SPELL_CONTINUE;
   public static final int SPELL_STOP;
   public static final int CLOSE;
   public static final int SAVE_CLOSE;
   public static final int MODE_SELECT;
   public static final int MODE_VIEW;
   public static final int FULL_MENU;
   public static final Comparator ORDINAL_COMPARATOR;

   public MenuItem(ResourceBundle var1, int var2, int var3, int var4) {
      if (var4 >= 0 && var3 >= 0) {
         this._bundle = var1;
         this._id = var2;
         this._ordinal = var3;
         this._priority = var4;
         if (var1 != null) {
            this._locale = Locale.getDefault();
            this._text = this._bundle.getString(var2);
         }

         this._icon = null;
      } else {
         throw new Object();
      }
   }

   public MenuItem(String var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public MenuItem(String var1, int var2, int var3, Image var4) {
      if (var3 >= 0 && var2 >= 0) {
         this._text = var1;
         this._ordinal = var2;
         this._priority = var3;
         this._icon = var4;
      } else {
         throw new Object();
      }
   }

   public ResourceBundle getBundle() {
      return this._bundle;
   }

   public Image getIcon() {
      return this._icon;
   }

   public Bitmap getIconBitmap() {
      return null;
   }

   public int getId() {
      return this._id;
   }

   public int getOrdinal() {
      return this._ordinal;
   }

   public static MenuItem getPrefab(int var0) {
      return MenuItemPrefab.get(var0);
   }

   public int getPriority() {
      return this._priority;
   }

   public Field getTarget() {
      return ContextMenu.getInstance().getTarget();
   }

   public boolean isSeparator() {
      return this._bundle == null && this._id == -1 || SEPARATOR_STRING.equals(this._text);
   }

   public static MenuItem separator(int var0) {
      return new MenuItem$2(SEPARATOR_STRING, var0, Integer.MAX_VALUE);
   }

   public void setIcon(Image var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setOrdinal(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setPriority(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setText(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public String toString() {
      if (this._bundle != null && this._locale != Locale.getDefault()) {
         this._locale = Locale.getDefault();
         this._text = this._bundle.getString(this._id);
      }

      return this._text;
   }

   public boolean isGroupItem(byte var1) {
      return false;
   }

   @Override
   public int getAccessibleRole() {
      return 3;
   }

   @Override
   public String getAccessibleName() {
      return this.toString();
   }

   @Override
   public String getAccessibleDescription() {
      return null;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return null;
   }

   @Override
   public int getAccessibleChildCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return null;
   }

   @Override
   public int getAccessibleStateSet() {
      return 1;
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return false;
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return null;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return false;
   }

   @Override
   public void run() {
      throw null;
   }
}
