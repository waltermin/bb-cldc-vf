package net.rim.device.api.ui.theme;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.StringTokenizer;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.vm.TraceBack;

public class Theme$Factory {
   private String _name;
   private String _parent;
   private String _vendorID;
   private boolean _allowUserWallpaper = true;
   private int _targetDisplayColorDepth;
   private int _targetDisplayHeight;
   private int _targetDisplayWidth;
   private boolean _isRemovable = true;
   private boolean _isActivatable = true;
   private ResourceFetcher _resourceFetcher;
   private boolean _suppressMissedCallDialog = false;

   protected Theme$Factory() {
   }

   protected Theme$Factory(String name, String parent) {
      this._name = name;
      this._parent = parent;
      String moduleName = TraceBack.getCallingModuleName(0);
      this._resourceFetcher = new DefaultResourceFetcher(moduleName);
   }

   public String getIdExtension() {
      return null;
   }

   public void setAllowUserWallpaper(boolean allow) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean allowUserWallpaper() {
      return this._allowUserWallpaper;
   }

   public void setVendorID(String vendor) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isVendorIDValid(int vendor) {
      if (this._vendorID == null) {
         return false;
      }

      StringTokenizer st = new StringTokenizer(this._vendorID);

      while (st.hasMoreTokens()) {
         String next = st.nextToken();

         try {
            if (vendor == Integer.parseInt(next)) {
               return true;
            }
         } catch (Exception var5) {
         }
      }

      return false;
   }

   public String getName() {
      return this._name;
   }

   public String getName(Locale locale) {
      return this.getName();
   }

   public final String getParent() {
      return this._parent;
   }

   public int getPriority() {
      return 1073741823;
   }

   public ResourceFetcher getResourceFetcher() {
      return this._resourceFetcher;
   }

   public final int getTargetDisplayColorDepth() {
      return this._targetDisplayColorDepth;
   }

   public final int getTargetDisplayHeight() {
      return this._targetDisplayHeight;
   }

   public final int getTargetDisplayWidth() {
      return this._targetDisplayWidth;
   }

   public final boolean isActivatable() {
      String name = this.getIdExtension();
      int handle = name == null ? 0 : CodeModuleManager.getModuleHandle(name);
      return handle != 0 ? this._isActivatable && ApplicationControl.isThemeDataAllowed(handle) : this._isActivatable;
   }

   public final boolean isRemovable() {
      return this._isRemovable;
   }

   public void populate(Theme$Writer theme) {
   }

   protected int remove() {
      int rc = CodeModuleManager.deleteModuleEx(CodeModuleManager.getModuleHandleForObject(this), true);
      switch (rc) {
         case 0:
            return 0;
         case 6:
            return 1;
         default:
            return 2;
      }
   }

   protected final void setActivatable(boolean isActivatable) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected final void setRemovable(boolean isRemovable) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setResourceFetcher(ResourceFetcher resourceFetcher) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected final void setTargetDisplay(int width, int height, int colorDepth) {
      this._targetDisplayWidth = width;
      this._targetDisplayHeight = height;
      this._targetDisplayColorDepth = colorDepth;
   }

   public void setSuppressMissedCallDialog(boolean suppress) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean getSuppressMissedCallDialog() {
      return this._suppressMissedCallDialog;
   }
}
