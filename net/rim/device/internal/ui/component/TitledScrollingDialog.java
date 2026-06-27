package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;

public class TitledScrollingDialog extends PopupDialog implements FieldChangeListener {
   protected Font _boldFont = Font.getDefault();
   protected ButtonField _ok;
   protected VerticalFieldManager _nonScrollingRegion;
   protected SeparatorField _nonScrollingSeparator;
   protected VerticalIndentFieldManager _scrollingRegion;
   private static final int INDENT_PIXEL_WIDTH;

   protected void setTitle(String var1) {
      Object var2 = new Object(var1, 64);
      ((LabelField)var2).setFont(this._boldFont);
      this.setTitle((Field)var2);
   }

   protected void setTitle(Field var1) {
      Manager var2 = this.getDelegate();
      var2.insert(var1, 0);
      var2.insert((Field)(new Object()), 1);
   }

   protected void populateDialog() {
      this._ok = (ButtonField)(new Object(CommonResource.getString(100), 12884901888L));
      this._ok.setChangeListener(this);
      this.addScrollingField(this._ok);
   }

   protected RichTextField addScrollingLabelAndValue(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void addScrollingField(Field var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void addNonScrollingField(Field var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void addNonScrollingField(Field var1) {
      this.addNonScrollingField(var1, this._nonScrollingRegion.getFieldCount());
   }

   protected void addNonScrollingText(String var1) {
      Object var2 = new Object(var1, 64);
      ((LabelField)var2).setFont(this._boldFont);
      this.addNonScrollingField((Field)var2);
   }

   protected void deleteAllNonScrollingFields() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean handleFieldChanged(Field var1, int var2) {
      if (var1 == this._ok) {
         this.close(0);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      this.handleFieldChanged(var1, var2);
   }

   public TitledScrollingDialog() {
      this(0);
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1) {
         new TitledScrollingDialog$PopulateDialogThread(this).start();
      }
   }

   public TitledScrollingDialog(long var1) {
      super((Manager)(new Object(3458764513820540928L)), var1);
      this._boldFont = this._boldFont.derive(this._boldFont.getStyle() | 1);
      this._nonScrollingRegion = (VerticalFieldManager)(new Object(1152921504606846976L));
      this._scrollingRegion = new VerticalIndentFieldManager(1153220571769602048L);
      Manager var3 = this.getDelegate();
      var3.add(this._nonScrollingRegion);
      var3.add(this._scrollingRegion);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (super.keyChar(var1, var2, var3)) {
         return true;
      } else if (var1 == 27) {
         this.close(-1);
         return true;
      } else {
         return false;
      }
   }
}
