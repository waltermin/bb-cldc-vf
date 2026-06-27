package net.rim.device.api.ui.container;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.ui.component.ImageField;

public class DialogFieldManager extends Manager {
   private Field _icon;
   private VerticalFieldManager _vfmLabel;
   private RichTextField _label;
   private Manager _fm;
   private Manager _middleManager;
   private DialogFieldManager$ButtonManager _buttonManager;
   private Manager _bottomManager;
   private DialogFieldManager$FocusNullField _focusNullField;
   private static final Tag TAG_ICON;
   private static final int PADDING;
   private static final int FRACTION_RESERVED_FOR_BUTTONS;

   public DialogFieldManager() {
      this(0);
   }

   public DialogFieldManager(long var1) {
      this(var1, 299067162755072L);
   }

   public DialogFieldManager(long var1, long var3) {
      this(var1, var3, false);
   }

   public DialogFieldManager(long var1, long var3, boolean var5) {
      super(var1);
      this._vfmLabel = new VerticalFieldManager(var3);
      this._vfmLabel.setNonfocusableOverride(true);
      this.add(this._vfmLabel);
      if (var5) {
         this._fm = new DialogFieldManager$1(this, 299067162755072L);
      } else {
         this._fm = new VerticalFieldManager(299067162755072L);
      }

      this.add(this._fm);
      this._middleManager = new VerticalFieldManager();
      this._fm.add(this._middleManager);
      this._buttonManager = new DialogFieldManager$ButtonManager();
      this._fm.add(this._buttonManager);
      this._bottomManager = new VerticalFieldManager();
      this._fm.add(this._bottomManager);
      this._focusNullField = new DialogFieldManager$FocusNullField(null);
      this.add(this._focusNullField);
   }

   public void addButtonField(ButtonField var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void addCustomField(Field var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void deleteCustomField(Field var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void deleteCustomFields() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      if (var1 < this._middleManager.getFieldCount()) {
         return this.getCustomField(var1);
      } else {
         return var1 < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
            ? this.getButtonField(var1 - this._middleManager.getFieldCount())
            : this._bottomManager.getField(var1 - this._buttonManager.getFieldCount() - this._middleManager.getFieldCount());
      }
   }

   @Override
   public int getAccessibleChildCount() {
      return this._middleManager.getFieldCount() + this._buttonManager.getFieldCount() + this._bottomManager.getFieldCount();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      if (var1 >= 0 && var1 <= this.getAccessibleSelectionCount()) {
         if (this._middleManager.getFieldWithFocusIndex() > 0) {
            var1 += this._middleManager.getFieldWithFocusIndex();
         }

         if (this._buttonManager.getFieldWithFocusIndex() > 0) {
            var1 += this._buttonManager.getFieldWithFocusIndex();
         }

         if (this._bottomManager.getFieldWithFocusIndex() > 0) {
            var1 += this._bottomManager.getFieldWithFocusIndex();
         }

         if (var1 < this._middleManager.getFieldCount()) {
            return this.getCustomField(var1);
         } else {
            return var1 < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
               ? this.getButtonField(var1 - this._middleManager.getFieldCount())
               : this._bottomManager.getField(var1 - this._buttonManager.getFieldCount() - this._middleManager.getFieldCount());
         }
      } else {
         throw new Object();
      }
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   public Field getButtonField(int var1) {
      return this.getButtonManager().getField(var1);
   }

   public Manager getButtonManager() {
      return this._buttonManager;
   }

   public Field getCustomField(int var1) {
      return this.getCustomManager().getField(var1);
   }

   public Manager getCustomManager() {
      return this._middleManager;
   }

   public Manager getBottomManager() {
      return this._bottomManager;
   }

   public Manager getMiddleManager() {
      return this._middleManager;
   }

   public boolean hasButtons() {
      return this._buttonManager.getFieldCount() != 0;
   }

   public boolean hasCustomFields() {
      return this._middleManager.getFieldCount() > 0;
   }

   public boolean hasMiddleFields() {
      return this._middleManager.getFieldCount() > 0;
   }

   public void insertCustomField(Field var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      if (var1 < this._middleManager.getFieldCount()) {
         return this._middleManager.getFieldWithFocusIndex() == var1;
      } else {
         return var1 < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
            ? this._buttonManager.getFieldWithFocusIndex() == var1 - this._middleManager.getFieldCount()
            : this._bottomManager.getFieldWithFocusIndex() == var1 - this._middleManager.getFieldCount() - this._buttonManager.getFieldCount();
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      int var4 = super.moveFocus(var1, var2, var3);
      if (var4 != 0 && this._label != null) {
         int var5 = this._vfmLabel.getVerticalScroll();
         var5 += this._label.getFont().getHeight() * var1;
         var5 = MathUtilities.clamp(0, var5, Math.max(0, this._vfmLabel.getVirtualHeight() - this._vfmLabel.getContentHeight()));
         this._vfmLabel.setVerticalScroll(var5);
      }

      return var4;
   }

   public void setIcon(BitmapField var1) {
      if (this._icon != null) {
         int var2 = this._icon.getIndex();
         this._icon = null;
         this.deleteRange(var2, 1);
      }

      if (var1 != null) {
         var1.setTag(TAG_ICON);
         this._icon = var1;
         this.add(var1);
      }
   }

   public void setIcon(ImageField var1) {
      if (this._icon != null) {
         int var2 = this._icon.getIndex();
         this._icon = null;
         this.deleteRange(var2, 1);
      }

      if (var1 != null) {
         var1.setTag(TAG_ICON);
         var1.setPreferredSize(Display.getWidth() >> 2, Display.getHeight() >> 2);
         this._icon = var1;
         this.add(var1);
      }
   }

   public void setMessage(RichTextField var1) {
      if (this._label != null) {
         this._vfmLabel.delete(this._label);
      }

      this._label = var1;
      if (var1 != null) {
         this._vfmLabel.add(var1);
      }
   }

   @Override
   protected void sublayout(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }
}
