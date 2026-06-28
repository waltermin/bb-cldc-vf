package net.rim.device.internal.ui.component;

import net.rim.device.api.i18n.ResourceBundle;

public class ColorChoiceField$NamedColor {
   private int _color;
   private int _resourceId;
   private static ResourceBundle BUNDLE;

   public ColorChoiceField$NamedColor(int color, int resourceId) {
      this._color = color;
      this._resourceId = resourceId;
   }

   @Override
   public boolean equals(Object object) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getColor() {
      return this._color;
   }

   @Override
   public String toString() {
      return BUNDLE.getString(this._resourceId);
   }
}
