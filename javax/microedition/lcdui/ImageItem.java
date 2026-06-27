package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;

public class ImageItem extends Item {
   private ImageItem$ImageItemManager _container;
   private LabelField _label;
   private BitmapField _image;
   private String _altText;
   private Image _midpImage;
   int _appearanceMode;
   public static final int LAYOUT_DEFAULT;
   public static final int LAYOUT_LEFT;
   public static final int LAYOUT_RIGHT;
   public static final int LAYOUT_CENTER;
   public static final int LAYOUT_NEWLINE_BEFORE;
   public static final int LAYOUT_NEWLINE_AFTER;

   public ImageItem(String var1, Image var2, int var3, String var4) {
   }

   public ImageItem(String var1, Image var2, int var3, String var4, int var5) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      return this._container;
   }

   public Image getImage() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setImage(Image var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getAltText() {
      return this._altText;
   }

   public void setAltText(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setLayout(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getAppearanceMode() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
