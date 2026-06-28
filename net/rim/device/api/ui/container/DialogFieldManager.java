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

   public DialogFieldManager(long style) {
      this(style, 299067162755072L);
   }

   public DialogFieldManager(long style, long messageStyle) {
      this(style, messageStyle, false);
   }

   public DialogFieldManager(long style, long messageStyle, boolean disableQuantization) {
      super(style);
      this._vfmLabel = new VerticalFieldManager(messageStyle);
      this._vfmLabel.setNonfocusableOverride(true);
      this.add(this._vfmLabel);
      if (disableQuantization) {
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

   public void addButtonField(ButtonField field) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void addCustomField(Field f) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void deleteCustomField(Field field) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void deleteCustomFields() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int index) {
      if (index < this._middleManager.getFieldCount()) {
         return this.getCustomField(index);
      } else {
         return index < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
            ? this.getButtonField(index - this._middleManager.getFieldCount())
            : this._bottomManager.getField(index - this._buttonManager.getFieldCount() - this._middleManager.getFieldCount());
      }
   }

   @Override
   public int getAccessibleChildCount() {
      return this._middleManager.getFieldCount() + this._buttonManager.getFieldCount() + this._bottomManager.getFieldCount();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int index) {
      if (index >= 0 && index <= this.getAccessibleSelectionCount()) {
         if (this._middleManager.getFieldWithFocusIndex() > 0) {
            index += this._middleManager.getFieldWithFocusIndex();
         }

         if (this._buttonManager.getFieldWithFocusIndex() > 0) {
            index += this._buttonManager.getFieldWithFocusIndex();
         }

         if (this._bottomManager.getFieldWithFocusIndex() > 0) {
            index += this._bottomManager.getFieldWithFocusIndex();
         }

         if (index < this._middleManager.getFieldCount()) {
            return this.getCustomField(index);
         } else {
            return index < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
               ? this.getButtonField(index - this._middleManager.getFieldCount())
               : this._bottomManager.getField(index - this._buttonManager.getFieldCount() - this._middleManager.getFieldCount());
         }
      } else {
         throw new Object();
      }
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   public Field getButtonField(int index) {
      return this.getButtonManager().getField(index);
   }

   public Manager getButtonManager() {
      return this._buttonManager;
   }

   public Field getCustomField(int index) {
      return this.getCustomManager().getField(index);
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

   public void insertCustomField(Field f, int index) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean isAccessibleChildSelected(int index) {
      if (index < this._middleManager.getFieldCount()) {
         return this._middleManager.getFieldWithFocusIndex() == index;
      } else {
         return index < this._middleManager.getFieldCount() + this._buttonManager.getFieldCount()
            ? this._buttonManager.getFieldWithFocusIndex() == index - this._middleManager.getFieldCount()
            : this._bottomManager.getFieldWithFocusIndex() == index - this._middleManager.getFieldCount() - this._buttonManager.getFieldCount();
      }
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      int result = super.moveFocus(amount, status, time);
      if (result != 0 && this._label != null) {
         int scroll = this._vfmLabel.getVerticalScroll();
         scroll += this._label.getFont().getHeight() * amount;
         scroll = MathUtilities.clamp(0, scroll, Math.max(0, this._vfmLabel.getVirtualHeight() - this._vfmLabel.getContentHeight()));
         this._vfmLabel.setVerticalScroll(scroll);
      }

      return result;
   }

   public void setIcon(BitmapField icon) {
      if (this._icon != null) {
         int index = this._icon.getIndex();
         this._icon = null;
         this.deleteRange(index, 1);
      }

      if (icon != null) {
         icon.setTag(TAG_ICON);
         this._icon = icon;
         this.add(icon);
      }
   }

   public void setIcon(ImageField icon) {
      if (this._icon != null) {
         int index = this._icon.getIndex();
         this._icon = null;
         this.deleteRange(index, 1);
      }

      if (icon != null) {
         icon.setTag(TAG_ICON);
         icon.setPreferredSize(Display.getWidth() >> 2, Display.getHeight() >> 2);
         this._icon = icon;
         this.add(icon);
      }
   }

   public void setMessage(RichTextField label) {
      if (this._label != null) {
         this._vfmLabel.delete(this._label);
      }

      this._label = label;
      if (label != null) {
         this._vfmLabel.add(label);
      }
   }

   @Override
   protected void sublayout(int width, int height) {
      throw new RuntimeException("cod2jar: type check");
   }
}
