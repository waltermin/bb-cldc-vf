package net.rim.device.api.ui.theme;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.CodeModuleManager;
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

   protected Theme$Factory(String var1, String var2) {
      this._name = var1;
      this._parent = var2;
      String var3 = TraceBack.getCallingModuleName(0);
      this._resourceFetcher = new DefaultResourceFetcher(var3);
   }

   public String getIdExtension() {
      return null;
   }

   public void setAllowUserWallpaper(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean allowUserWallpaper() {
      return this._allowUserWallpaper;
   }

   public void setVendorID(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isVendorIDValid(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getName() {
      return this._name;
   }

   public String getName(Locale var1) {
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
      String var1 = this.getIdExtension();
      int var2 = var1 == null ? 0 : CodeModuleManager.getModuleHandle(var1);
      return var2 != 0 ? this._isActivatable && ApplicationControl.isThemeDataAllowed(var2) : this._isActivatable;
   }

   public final boolean isRemovable() {
      return this._isRemovable;
   }

   public void populate(Theme$Writer var1) {
   }

   protected int remove() {
      int var1 = CodeModuleManager.deleteModuleEx(CodeModuleManager.getModuleHandleForObject(this), true);
      switch (var1) {
         case 0:
            return 0;
         case 6:
            return 1;
         default:
            return 2;
      }
   }

   protected final void setActivatable(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected final void setRemovable(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setResourceFetcher(ResourceFetcher var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected final void setTargetDisplay(int var1, int var2, int var3) {
      this._targetDisplayWidth = var1;
      this._targetDisplayHeight = var2;
      this._targetDisplayColorDepth = var3;
   }

   public void setSuppressMissedCallDialog(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean getSuppressMissedCallDialog() {
      return this._suppressMissedCallDialog;
   }
}
