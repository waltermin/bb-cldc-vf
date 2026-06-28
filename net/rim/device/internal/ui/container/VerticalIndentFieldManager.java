package net.rim.device.internal.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.util.Arrays;

public class VerticalIndentFieldManager extends VerticalFieldManager {
   private int[] _indentAmounts = new int[0];
   private static final int MAX_HEIGHT;
   private static final int MAX_WIDTH;

   public VerticalIndentFieldManager() {
      this(0);
   }

   public VerticalIndentFieldManager(long style) {
      super(style);
   }

   public void add(Field field, int indentAmount) {
      Arrays.add(this._indentAmounts, indentAmount);
      super.add(field);
   }

   @Override
   public void add(Field field) {
      this.add(field, 0);
   }

   public void insert(Field field, int index, int indentAmount) {
      Arrays.insertAt(this._indentAmounts, indentAmount, index);
      super.insert(field, index);
   }

   @Override
   public void insert(Field field, int index) {
      this.insert(field, index, 0);
   }

   @Override
   public void delete(Field field) {
      Arrays.removeAt(this._indentAmounts, field.getIndex());
      super.delete(field);
   }

   @Override
   public void deleteRange(int start, int count) {
      for (int i = start + count - 1; i >= start; i--) {
         Arrays.removeAt(this._indentAmounts, i);
      }

      super.deleteRange(start, count);
   }

   @Override
   protected void sublayout(int width, int height) {
      throw new RuntimeException("cod2jar: type check");
   }
}
