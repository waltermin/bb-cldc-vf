package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.TextMetrics;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntIntHashtable;

final class RowHeightAdjuster implements VariableRowHeightProvider {
   private IntIntHashtable _rowHeightExceptions = (IntIntHashtable)(new Object());
   private int _rowHeightExceptionsSum;
   private int _rowHeight;
   private int _size;
   private int _lastStartNodeIndex;
   private int _lastStartNodeYPos;
   private TextMetrics _metrics = (TextMetrics)(new Object());
   private int _currentY;
   private int _currentHeight;
   private int _currentAdjustment;
   private int _maxHeight;
   private int _maxAdjustment;
   private boolean _initialized;

   public final int getHeight() {
      return Math.max(this._rowHeight, (this._size - this._rowHeightExceptions.size()) * this._rowHeight + this._rowHeightExceptionsSum);
   }

   public final void clear() {
      this._rowHeightExceptions.clear();
      this._rowHeightExceptionsSum = 0;
   }

   public final int getRowForY(int var1) {
      if (!this.hasVariableLineHeights()) {
         return Math.max(0, var1 / this._rowHeight);
      }

      int var2 = 0;
      int var3 = Math.abs(this._lastStartNodeYPos - var1);
      if (var1 <= var3) {
         int var4 = this.getRowHeight(0);

         while (var4 < var1) {
            var4 += this.getRowHeight(++var2);
         }
      } else if (this.getHeight() - this.getRowHeight(this._size - 1) - var1 <= var3) {
         var2 = this._size;

         for (int var5 = this.getHeight(); var5 >= var1; var5 -= this.getRowHeight(var2)) {
            var2--;
         }
      } else {
         var2 = this._lastStartNodeIndex;
         if (this._lastStartNodeYPos < var1) {
            int var6 = this._lastStartNodeYPos + this.getRowHeight(var2);

            while (var6 < var1) {
               var6 += this.getRowHeight(++var2);
            }
         } else {
            for (int var7 = this._lastStartNodeYPos; var7 > var1; var7 -= this.getRowHeight(var2)) {
               var2--;
            }
         }
      }

      return var2;
   }

   public final int getYForRow(int var1) {
      int var2 = 0;
      if (var1 < 0) {
         return 0;
      }

      if (!this.hasVariableLineHeights()) {
         var2 = var1 * this._rowHeight;
      } else {
         int var3 = Math.abs(this._lastStartNodeIndex - var1);
         if (var1 <= var3) {
            for (int var4 = 0; var4 < var1; var4++) {
               var2 += this.getRowHeight(var4);
            }
         } else if (this._size - var1 <= var3) {
            var2 = this.getHeight();

            for (int var5 = this._size - 1; var5 >= var1; var5--) {
               var2 -= this.getRowHeight(var5);
            }
         } else {
            var2 = this._lastStartNodeYPos;
            if (this._lastStartNodeIndex < var1) {
               for (int var6 = this._lastStartNodeIndex; var6 < var1; var6++) {
                  var2 += this.getRowHeight(var6);
               }
            } else {
               for (int var7 = this._lastStartNodeIndex - 1; var7 >= var1; var7--) {
                  var2 -= this.getRowHeight(var7);
               }
            }
         }
      }

      this._lastStartNodeIndex = var1;
      this._lastStartNodeYPos = var2;
      return var2;
   }

   public final int getRowHeight(int var1) {
      int var2 = this._rowHeightExceptions.get(var1);
      if (var2 < 0) {
         var2 = this._rowHeight;
      }

      return var2 & 0xFF;
   }

   public final void setRowHeight(int var1) {
      if (var1 != this._rowHeight) {
         this._rowHeight = var1;
      }
   }

   public final void setSize(int var1) {
      if (var1 != this._size) {
         this._size = var1;
         this._rowHeightExceptions.clear();
         this._rowHeightExceptionsSum = 0;
      }
   }

   public final boolean setRowHeight(int var1, int var2) {
      if (var2 > this._maxHeight) {
         this._maxHeight = var2;
      }

      return this.finish(var1);
   }

   public final boolean hasVariableLineHeights() {
      return this._rowHeightExceptions.size() != 0;
   }

   public final void insertedRow(int var1) {
      if (this.hasVariableLineHeights()) {
         Object var2 = new Object(this._rowHeightExceptions.size());
         IntEnumeration var3 = this._rowHeightExceptions.keys();

         while (var3.hasMoreElements()) {
            int var4 = var3.nextElement();
            int var5 = this._rowHeightExceptions.get(var4);
            if (var4 >= var1) {
               var4++;
            }

            ((IntIntHashtable)var2).put(var4, var5);
         }

         this._rowHeightExceptions = (IntIntHashtable)var2;
      }

      this._size++;
   }

   public final void deletedRow(int var1) {
      if (this.hasVariableLineHeights()) {
         Object var2 = new Object(this._rowHeightExceptions.size());
         IntEnumeration var3 = this._rowHeightExceptions.keys();

         while (var3.hasMoreElements()) {
            int var4 = var3.nextElement();
            int var5 = this._rowHeightExceptions.get(var4);
            if (var4 > var1) {
               var4--;
            } else if (var4 == var1) {
               continue;
            }

            ((IntIntHashtable)var2).put(var4, var5);
         }

         this._rowHeightExceptions = (IntIntHashtable)var2;
      }

      this._size--;
   }

   public final void start(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final boolean finish(int var1) {
      this.checkReduceHeight();
      this._initialized = false;
      this._maxHeight = 0;
      return this.setRowHeight(var1, this._currentHeight, this._currentAdjustment);
   }

   @Override
   public final int getAdjustedY(Font var1, StringBuffer var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final int getAdjustedY(Font var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final int getAdjustedY(int var1) {
      return this._currentY;
   }

   private final int getRowInfo(int var1) {
      int var2 = this._rowHeightExceptions.get(var1);
      if (var2 < 0) {
         var2 = this._rowHeight;
      }

      return var2;
   }

   public RowHeightAdjuster() {
   }

   private final int calculateYValue(Font var1, int var2) {
      int var3 = var1.getBaseline();
      int var4 = Math.max(-this._metrics.iBoundsTlY, var3);
      int var5 = Math.max(this._metrics.iBoundsBrY, var1.getDescent());
      int var6 = Math.max(var1.getHeight(), var4 + var5);
      int var7 = var4 - var3;
      if (var6 > this._maxHeight) {
         this._maxHeight = var6;
      }

      if (this._maxHeight > this._currentHeight) {
         this._currentHeight = this._maxHeight;
      }

      if (var7 > this._maxAdjustment) {
         this._maxAdjustment = var7;
      }

      if (this._maxAdjustment > this._currentAdjustment) {
         this._currentAdjustment = this._maxAdjustment;
      }

      this._currentY = var2 + this._currentAdjustment;
      return this._currentY;
   }

   public RowHeightAdjuster(int var1, int var2) {
      this._rowHeight = var1;
      this._size = var2;
   }

   private final boolean setRowHeight(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void checkReduceHeight() {
      if (this._maxHeight > 0
         && (this._maxAdjustment < this._currentAdjustment || this._currentHeight != this._maxHeight || this._currentHeight < this._rowHeight)) {
         this._currentHeight = Math.max(this._maxHeight, this._rowHeight);
         this._currentAdjustment = this._maxAdjustment;
         this._currentY = this._currentY - (this._currentAdjustment - this._maxAdjustment);
      }
   }
}
