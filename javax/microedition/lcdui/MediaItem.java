package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.internal.ui.component.MMAPIMediaField;

class MediaItem extends Item {
   private VerticalFieldManager _container;
   private LabelField _label;
   private MMAPIMediaField _media;

   MediaItem(String var1, int var2, int var3) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      return this._container;
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   MMAPIMediaField getMediaField() {
      return this._media;
   }
}
