package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.SortableCollection;
import net.rim.device.api.util.Comparator;

public class BigSortedReadableList extends BigUnsortedReadableList implements SortableCollection {
   private Comparator _comparator;

   protected int binarySearch(Object var1, int var2, int var3) {
      int var4 = super._elements.size();
      if (var2 >= 0 && var3 <= var4 && var2 <= var3) {
         int var5 = var2;
         int var6 = var3 - 1;

         while (var5 <= var6) {
            int var7 = var5 + var6 >> 1;
            int var8 = this._comparator.compare(var1, super._elements.elementAt(var7));
            if (var8 == 0) {
               var6 = var7;
               if (var7 == var5) {
                  return var5;
               }
            } else if (var8 < 0) {
               var6 = var7 - 1;
            } else {
               var5 = var7 + 1;
            }
         }

         return -(var5 + 1);
      } else {
         throw new Object();
      }
   }

   protected void sort() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setComparator(Comparator var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Comparator getComparator() {
      return this._comparator;
   }

   public BigSortedReadableList(CollectionEventSource var1, Comparator var2) {
      this(var2);
      var1.addCollectionListener(this);
      if (var1 instanceof Collection) {
         this.reload(var1);
      }
   }

   @Override
   protected void reload(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public BigSortedReadableList(Comparator var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._comparator = var1;
   }

   @Override
   protected void doAdd(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected boolean doUpdate(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
