package net.rim.device.api.ui.component;

import java.util.Vector;
import net.rim.device.api.ui.FieldChangeListener;

public class RadioButtonGroup {
   private boolean _notifyReselected;
   private Vector _buttons = (Vector)(new Object());
   private int _selected = -1;
   private FieldChangeListener _changeListener;

   public int add(RadioButtonField var1) {
      if (var1.getGroup() != null) {
         throw new Object();
      }

      var1._group = this;
      var1._index = this._buttons.size();
      this._buttons.addElement(var1);
      return var1._index;
   }

   public FieldChangeListener getChangeListener() {
      return this._changeListener;
   }

   public final boolean getNotifyReselected() {
      return this._notifyReselected;
   }

   public int getSelectedIndex() {
      return this._selected;
   }

   public final int getSize() {
      return this._buttons.size();
   }

   public void remove(RadioButtonField var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setChangeListener(FieldChangeListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setNotifyReselected(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSelectedIndex(int var1) {
      this.setSelectedIndex(var1, Integer.MIN_VALUE);
   }

   void setSelectedIndex(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
