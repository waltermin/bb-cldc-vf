package net.rim.device.api.ui.component;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionListener;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.util.Arrays;
import net.rim.vm.TraceBack;
import net.rim.vm.WeakReference;

public class CollectionListField extends ListField implements FocusChangeListener, CollectionListener {
   private Application _application;
   private CollectionListField$UpdaterRunnable _updaterRunnable;
   private CollectionListField$SetFocusedElementRunnable _focusedElementRunnable;
   protected ReadableList _list;
   private WeakReference _collectionListener;
   private Object _elementWithFocus;
   private int _extraRowCount;
   private String[] _extraRowName;
   private boolean _isExtraRowAtBottom = false;

   public void addExtraRowName(String var1) {
      Arrays.add(this._extraRowName, var1);
   }

   protected void doUpdateList() {
      this.setSize(this.getListSize());
   }

   public void updateList() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setList(ReadableList var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setExtraRowAtBottom(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setElementWithFocus(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Object getElementAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Object getElementWithFocus() {
      return this._elementWithFocus;
   }

   public int getExtraRowCount() {
      return this._extraRowCount;
   }

   public void setExtraRowCount(int var1) {
      if (this._extraRowCount != var1) {
         this._extraRowCount = var1;
         this.setSize(this.getListSize());
         this._extraRowName = new String[this._extraRowCount];
      }
   }

   protected int getListSize() {
      return this._list != null ? this._list.size() : 0;
   }

   public Object getSelectedElement() {
      return this.getElementAt(this.getSelectedIndex());
   }

   @Override
   public void reset(Collection var1) {
      this.updateList();
   }

   @Override
   public void focusChanged(Field var1, int var2) {
      this.setElementWithFocus(this.getElementAt(this.getSelectedIndex()));
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      if (this._elementWithFocus == var2) {
         this.setElementWithFocus(var3);
      }

      this.updateList();
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      if (this._elementWithFocus == var2) {
         int var3 = this.getSelectedIndex();
         if (this._list != null && var3 == this._list.size()) {
            var3--;
         }

         this.setElementWithFocus(this.getElementAt(var3));
      }

      this.updateList();
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      this.updateList();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private Application getApplication() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public CollectionListField(ReadableList var1, ListFieldCallback var2, long var3) {
      super(0, var3);
      this._application = Application.getApplication();
      this._collectionListener = (WeakReference)(new Object(this));
      this._updaterRunnable = new CollectionListField$UpdaterRunnable(this);
      this._focusedElementRunnable = new CollectionListField$SetFocusedElementRunnable(this);
      if (var2 != null) {
         this.setCallback(var2);
      }

      this.setList(var1);
      this.setFocusListener(this);
      this.setSize(this.getListSize());
   }

   private void doSetElementWithFocus(Object var1) {
      this._elementWithFocus = var1;
      if (var1 != null && var1 != this.getSelectedElement() && this._list != null) {
         int var2 = this._list.getIndex(var1);
         if (var2 != -1) {
            this.setSelectedIndex(var2 + this.getExtraRowCount());
         }
      }
   }

   @Override
   public void setSize(int var1) {
      if (this._list != null) {
         int var2 = -1;
         if (this._elementWithFocus != null) {
            var2 = this._list.getIndex(this._elementWithFocus);
         }

         if (var2 == -1) {
            var2 = this.getExtraRowCount();
            this.setElementWithFocus(null);
         } else {
            var2 += this.getExtraRowCount();
         }

         this.setSize(var1, var2);
      }
   }

   @Override
   public void setSize(int var1, int var2) {
      if (this._list != null) {
         if (var1 == 0 && this.getEmptyString() != null) {
            var1++;
         }

         var1 += this.getExtraRowCount();
         int var3 = this.getSize();
         super.setSize(var1, var2);
         if (var3 == 0 && var1 > 0 && this.getManager() != null) {
            this.setFocus();
         }

         if (this._elementWithFocus == null) {
            this.setElementWithFocus(this.getSelectedElement());
         }
      }
   }

   public CollectionListField(ReadableList var1, ListFieldCallback var2) {
      this(var1, var2, 0);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }
}
