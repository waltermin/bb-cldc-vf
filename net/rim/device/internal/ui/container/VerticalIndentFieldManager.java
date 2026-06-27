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

   public VerticalIndentFieldManager(long var1) {
      super(var1);
   }

   public void add(Field var1, int var2) {
      Arrays.add(this._indentAmounts, var2);
      super.add(var1);
   }

   @Override
   public void add(Field var1) {
      this.add(var1, 0);
   }

   public void insert(Field var1, int var2, int var3) {
      Arrays.insertAt(this._indentAmounts, var3, var2);
      super.insert(var1, var2);
   }

   @Override
   public void insert(Field var1, int var2) {
      this.insert(var1, var2, 0);
   }

   @Override
   public void delete(Field var1) {
      Arrays.removeAt(this._indentAmounts, var1.getIndex());
      super.delete(var1);
   }

   @Override
   public void deleteRange(int var1, int var2) {
      for (int var3 = var1 + var2 - 1; var3 >= var1; var3--) {
         Arrays.removeAt(this._indentAmounts, var3);
      }

      super.deleteRange(var1, var2);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }
}
