package net.rim.device.api.ui.container;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.theme.Tag;

class TitleStatusManager extends Manager {
   private VerticalFieldManager _vfm;
   private VerticalFieldManager _bannerManager;
   private VerticalFieldManager _titleManager;
   private VerticalFieldManager _statusManager;
   private Field _title;
   private static Tag BANNER_TAG;
   private static Tag TITLE_TAG;
   private static Tag CLIENT_TAG;
   private static Tag STATUS_TAG;

   public TitleStatusManager(long var1) {
      super(validateStyle(var1));
      this._vfm = new VerticalFieldManager(validateStyleVFM(var1));
      this._vfm.setTag(CLIENT_TAG);
      super.add(this._vfm);
   }

   @Override
   public void setId(String var1) {
      super.setId(var1);
      if (this._titleManager != null) {
         this._titleManager.setId(var1);
      }

      this._vfm.setId(var1);
      if (this._statusManager != null) {
         this._statusManager.setId(var1);
      }
   }

   public Manager getMainManager() {
      return this._vfm;
   }

   @Override
   public void add(Field var1) {
      this.getMainManager().add(var1);
   }

   @Override
   public void delete(Field var1) {
      this.getMainManager().delete(var1);
   }

   @Override
   public void deleteRange(int var1, int var2) {
      this.getMainManager().deleteRange(var1, var2);
   }

   @Override
   public String getAccessibleName() {
      return this._title != null ? this._title.getAccessibleName() : null;
   }

   @Override
   public void insert(Field var1, int var2) {
      this.getMainManager().insert(var1, var2);
   }

   @Override
   public void replace(Field var1, Field var2) {
      this.getMainManager().replace(var1, var2);
   }

   @Override
   public void setHorizontalQuantization(int var1) {
      this.getMainManager().setHorizontalQuantization(var1);
   }

   public void setBanner(Field var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setStatus(Field var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTitle(Field var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTitle(String var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setTitle(ResourceBundleFamily var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setVerticalQuantization(int var1) {
      this.getMainManager().setVerticalQuantization(var1);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      this.setExtent(var1, var2);
      int var3 = var2;
      int var4 = 0;
      if (this._bannerManager != null) {
         this.layoutChild(this._bannerManager, var1, var2);
         this.setPositionChild(this._bannerManager, 0, 0);
         var4 = this._bannerManager.getHeight();
      }

      var2 -= var4;
      int var5 = 0;
      if (this._titleManager != null) {
         this.layoutChild(this._titleManager, var1, var2);
         this.setPositionChild(this._titleManager, 0, var4);
         var5 = this._titleManager.getHeight();
      }

      var2 -= var5;
      int var6 = 0;
      if (this._statusManager != null) {
         this.layoutChild(this._statusManager, var1, var2);
         var6 = this._statusManager.getHeight();
         this.setPositionChild(this._statusManager, 0, var3 - var6);
      }

      var2 -= var6;
      this.setPositionChild(this._vfm, 0, var5 + var4);
      this.layoutChild(this._vfm, var1, var2);
   }

   private static long validateStyle(long var0) {
      var0 &= -4486007441326081L;
      if (Graphics.isColor()) {
         var0 |= 3221225472L;
      }

      return var0;
   }

   private static long validateStyleVFM(long var0) {
      if ((var0 & 844424930131968L) == 0) {
         var0 |= 281474976710656L;
      }

      if ((var0 & 52776558133248L) == 0) {
         var0 |= 17592186044416L;
      }

      if ((var0 & 3377699720527872L) == 0) {
         var0 |= 2251799813685248L;
      }

      if ((var0 & 211106232532992L) == 0) {
         var0 |= 140737488355328L;
      }

      var0 &= 292716448017547264L;
      return var0 | 3458764513820540928L;
   }
}
