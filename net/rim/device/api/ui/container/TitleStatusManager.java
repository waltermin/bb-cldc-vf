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

   public TitleStatusManager(long style) {
      super(validateStyle(style));
      this._vfm = new VerticalFieldManager(validateStyleVFM(style));
      this._vfm.setTag(CLIENT_TAG);
      super.add(this._vfm);
   }

   @Override
   public void setId(String idName) {
      super.setId(idName);
      if (this._titleManager != null) {
         this._titleManager.setId(idName);
      }

      this._vfm.setId(idName);
      if (this._statusManager != null) {
         this._statusManager.setId(idName);
      }
   }

   public Manager getMainManager() {
      return this._vfm;
   }

   @Override
   public void add(Field field) {
      this.getMainManager().add(field);
   }

   @Override
   public void delete(Field field) {
      this.getMainManager().delete(field);
   }

   @Override
   public void deleteRange(int start, int count) {
      this.getMainManager().deleteRange(start, count);
   }

   @Override
   public String getAccessibleName() {
      return this._title != null ? this._title.getAccessibleName() : null;
   }

   @Override
   public void insert(Field field, int index) {
      this.getMainManager().insert(field, index);
   }

   @Override
   public void replace(Field oldField, Field newField) {
      this.getMainManager().replace(oldField, newField);
   }

   @Override
   public void setHorizontalQuantization(int horizontalQuanta) {
      this.getMainManager().setHorizontalQuantization(horizontalQuanta);
   }

   public void setBanner(Field banner) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setStatus(Field status) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTitle(Field title) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTitle(String text) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setTitle(ResourceBundleFamily family, int id) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setVerticalQuantization(int verticalQuanta) {
      this.getMainManager().setVerticalQuantization(verticalQuanta);
   }

   @Override
   protected void sublayout(int width, int height) {
      this.setExtent(width, height);
      int bottom = height;
      int bfmheight = 0;
      if (this._bannerManager != null) {
         this.layoutChild(this._bannerManager, width, height);
         this.setPositionChild(this._bannerManager, 0, 0);
         bfmheight = this._bannerManager.getHeight();
      }

      height -= bfmheight;
      int tfmheight = 0;
      if (this._titleManager != null) {
         this.layoutChild(this._titleManager, width, height);
         this.setPositionChild(this._titleManager, 0, bfmheight);
         tfmheight = this._titleManager.getHeight();
      }

      height -= tfmheight;
      int sfmheight = 0;
      if (this._statusManager != null) {
         this.layoutChild(this._statusManager, width, height);
         sfmheight = this._statusManager.getHeight();
         this.setPositionChild(this._statusManager, 0, bottom - sfmheight);
      }

      height -= sfmheight;
      this.setPositionChild(this._vfm, 0, tfmheight + bfmheight);
      this.layoutChild(this._vfm, width, height);
   }

   private static long validateStyle(long style) {
      style &= -4486007441326081L;
      if (Graphics.isColor()) {
         style |= 3221225472L;
      }

      return style;
   }

   private static long validateStyleVFM(long style) {
      if ((style & 844424930131968L) == 0) {
         style |= 281474976710656L;
      }

      if ((style & 52776558133248L) == 0) {
         style |= 17592186044416L;
      }

      if ((style & 3377699720527872L) == 0) {
         style |= 2251799813685248L;
      }

      if ((style & 211106232532992L) == 0) {
         style |= 140737488355328L;
      }

      style &= 292716448017547264L;
      return style | 3458764513820540928L;
   }
}
