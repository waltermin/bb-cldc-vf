package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.accessibility.AccessibleValue;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.tid.awt.im.InputContext;

public class ComboField extends HorizontalFieldManager implements FieldChangeListener {
   private BasicEditField _editable;
   private ListField _list;
   private ComboFieldController _control;
   private ComboField$DropListScreen _dropList;
   private String _inputText;
   private static final XYEdges DROP_MARGIN;

   public ComboFieldController getController() {
      return this._control;
   }

   public BasicEditField getEditable() {
      return this._editable;
   }

   public ListField getList() {
      return this._list;
   }

   public String getText() {
      return this._inputText;
   }

   public void hideDropList() {
      if (this._dropList != null && this._dropList.isDisplayed()) {
         Ui.getUiEngine().popScreen(this._dropList);
      }
   }

   protected XYEdges getDropMargin() {
      return DROP_MARGIN;
   }

   public void setController(ComboFieldController var1) {
      this._control = var1;
      this._control.setComboField(this);
   }

   public void setEditable(BasicEditField var1) {
      if (this._editable != null) {
         this.replace(this._editable, var1);
      } else {
         this.add(var1);
      }

      this._editable = var1;
      this._editable.setChangeListener(this);
      this._inputText = this._editable.getText();
   }

   public void setList(ListField var1) {
      this.hideDropList();
      this._list = var1;
      this._dropList = new ComboField$DropListScreen(this);
   }

   public void setText(String var1) {
      this._editable.setText(var1);
      this._editable.setMuddy(true);
   }

   public void showDropList() {
      XYRect var1 = new XYRect(this._editable.getExtent());
      this.transformToScreen(var1);
      XYEdges var2 = this.getDropMargin();
      this._dropList.setPositionAndWidth(var1.x + var2.left, var1.Y2(), var1.width - (var2.left + var2.right));
      if (this._dropList.isDisplayed()) {
         this._dropList.update();
      } else {
         Ui.getUiEngine().pushScreen(this._dropList);
      }
   }

   protected boolean usingSureType() {
      return InputContext.getInstance(false).isSureType();
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 != null && var1.getOriginal() == this._editable && this._control != null) {
         String var3 = this._editable.getText();
         if (!var3.equals(this._inputText)) {
            this._inputText = var3;
            this._control.textChanged(this._inputText, var2);
         }
      }
   }

   @Override
   protected void onUndisplay() {
      super.onUndisplay();
      this.hideDropList();
   }

   public ComboField() {
      this.$initialize(null, null, null);
   }

   public ComboField(BasicEditField var1, ListField var2, ComboFieldController var3) {
      this.$initialize(var1, var2, var3);
   }

   private void $initialize(BasicEditField var1, ListField var2, ComboFieldController var3) {
      this._editable = var1;
      this._list = var2;
      this._control = var3;
      if (this._list != null) {
         this._dropList = new ComboField$DropListScreen(this);
      }

      if (this._control != null) {
         this._control.setComboField(this);
      }

      if (this._editable != null) {
         this.add(this._editable);
         this._editable.setChangeListener(this);
      }
   }

   @Override
   public String getAccessibleName() {
      return null;
   }

   @Override
   public String getAccessibleDescription() {
      return null;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return this._editable instanceof AccessibleText ? this._editable : null;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public int getAccessibleStateSet() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (super.getAccessibleStateSet() & var1) != 0;
   }

   @Override
   public int getAccessibleRole() {
      return 12;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return this.getScreen();
   }

   @Override
   public int getAccessibleChildCount() {
      return 2;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      switch (var1) {
         case -1:
            return null;
         case 0:
         default:
            return this._editable;
         case 1:
            return this._list;
      }
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      if (var1 == 0) {
         Field var2 = super.getFieldWithFocus();
         if (var2 instanceof AccessibleContext && (var2 == this._editable || var2 == this._list)) {
            return var2;
         }
      }

      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      AccessibleContext var2 = this.getAccessibleChildAt(var1);
      AccessibleContext var3 = this.getAccessibleSelectionAt(0);
      return var2.equals(var3);
   }
}
