package net.rim.device.api.ui.component;

import java.util.Vector;
import net.rim.device.api.ui.FieldChangeListener;

public class RadioButtonGroup {
   private boolean _notifyReselected;
   private Vector _buttons = new Vector();
   private int _selected = -1;
   private FieldChangeListener _changeListener;

   public int add(RadioButtonField button) {
      if (button.getGroup() != null) {
         throw new IllegalStateException();
      }

      button._group = this;
      button._index = this._buttons.size();
      this._buttons.addElement(button);
      return button._index;
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

   public void remove(RadioButtonField button) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setChangeListener(FieldChangeListener listener) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setNotifyReselected(boolean notifyReselected) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSelectedIndex(int selected) {
      this.setSelectedIndex(selected, Integer.MIN_VALUE);
   }

   void setSelectedIndex(int selected, int context) {
      if (selected < -1 || selected >= this._buttons.size()) {
         throw new IllegalArgumentException();
      }

      if (this._selected == selected) {
         if (this._notifyReselected) {
            RadioButtonField newButton = null;
            if (this._selected != -1) {
               newButton = (RadioButtonField)this._buttons.elementAt(this._selected);
               if (this._changeListener != null) {
                  try {
                     this._changeListener.fieldChanged(newButton, context);
                  } catch (Throwable var6) {
                  }
               }

               newButton.selectionChange(context);
            }
         }
      } else {
         RadioButtonField oldButton = null;
         if (this._selected != -1) {
            oldButton = (RadioButtonField)this._buttons.elementAt(this._selected);
         }

         this._selected = selected;
         if (oldButton != null) {
            oldButton.selectionChange(context);
         }

         RadioButtonField newButton = null;
         if (this._selected != -1) {
            newButton = (RadioButtonField)this._buttons.elementAt(this._selected);
            newButton.selectionChange(context);
         }

         if (this._changeListener != null) {
            if (oldButton != null) {
               try {
                  this._changeListener.fieldChanged(oldButton, context);
               } catch (Throwable var7) {
               }
            }

            if (newButton != null) {
               try {
                  this._changeListener.fieldChanged(newButton, context);
                  return;
               } catch (Throwable var8) {
               }
            }
         }
      }
   }
}
