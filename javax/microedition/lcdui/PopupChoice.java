package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.util.Arrays;

final class PopupChoice extends BasicChoice {
   HorizontalFieldManager _popupContainer = (HorizontalFieldManager)(new Object());
   private ObjectChoiceField _popup = (ObjectChoiceField)(new Object());
   private BitmapField[] _popupImages;
   private String[] _popupStrings;

   PopupChoice() {
      this._popup.setChangeListener(new PopupChoice$PopupChangeListener(this));
      this._popupStrings = new String[0];
      this._popupImages = new BitmapField[0];
      super._type = 4;
   }

   @Override
   final Field addToForm(FieldChangeListener var1) {
      super._changeListener = var1;
      this._popupContainer.setChangeListener(null);
      this._popupContainer.setChangeListener(var1);
      return super._container;
   }

   @Override
   protected final String doGetString(int var1) {
      return this._popupStrings[var1];
   }

   @Override
   protected final Image doGetImage(int var1) {
      BitmapField var2 = this._popupImages[var1];
      return var2 != null ? (Image)var2.getCookie() : null;
   }

   public final void setCookie(Object var1) {
      this._popup.setCookie(var1);
   }

   @Override
   public final int getSelectedIndex() {
      return super._currentlySelectedIndex;
   }

   @Override
   public final void setLayout(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected final void doInsert(int var1, String var2, Image var3) {
      Arrays.insertAt(this._popupStrings, var2, var1);
      if (var3 != null) {
         Arrays.insertAt(this._popupImages, new Object(var3.getBitmap()), var1);
         this._popupImages[var1].setCookie(var3);
      } else {
         Arrays.insertAt(this._popupImages, null, var1);
      }

      if (this.size() == 0) {
         this._popupContainer.insert(this._popup, 0);
         if (this._popupImages[0] != null) {
            this._popupContainer.insert(this._popupImages[0], 0);
         }

         if (this._popupContainer.getManager() == null) {
            super._container.insert(this._popupContainer, 0);
         }

         super._currentlySelectedIndex = 0;
      }

      this._popup.setChoices(this._popupStrings);
   }

   @Override
   protected final void doDelete(int var1) {
      Arrays.removeAt(this._popupStrings, var1);
      Arrays.removeAt(this._popupImages, var1);
      this._popup.setChoices(this._popupStrings);
      if (this.size() > 0) {
         this._popup.setSelectedIndex(0);
      } else {
         if (this.size() == 0) {
            this._popupContainer.deleteAll();
         }
      }
   }

   @Override
   protected final void doSet(int var1, String var2, Image var3) {
      int var4 = this.getSelectedIndex();
      this._popupStrings[var1] = var2;
      this._popupImages[var1] = (BitmapField)(new Object(var3.getBitmap()));
      this._popup.setChoices(this._popupStrings);
      this._popup.setSelectedIndex(var4);
   }

   @Override
   protected final boolean doIsSelected(int var1) {
      return super._currentlySelectedIndex == var1;
   }

   @Override
   protected final void doSetSelectedIndex(int var1, boolean var2) {
      if (var2) {
         this._popup.setSelectedIndex(var1);
      }
   }

   @Override
   protected final void doSetSelectedFlags(boolean[] var1) {
      int var2 = this.size();
      int var3 = 0;

      while (var3 < var2 && !var1[var3]) {
         var3++;
      }

      if (var3 == var2) {
         var3 = 0;
      }

      this._popup.setSelectedIndex(var3);
   }

   @Override
   protected final void setFieldFont(net.rim.device.api.ui.Font var1, int var2) {
      if (super._currentlySelectedIndex == var2) {
         this._popup.setFont(var1);
      }
   }
}
