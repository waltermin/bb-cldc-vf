package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.SortableCollection;
import net.rim.device.api.util.Comparator;

public class SortedReadableList extends UnsortedReadableList implements SortableCollection {
   private Comparator _comparator;

   public void sort() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setComparator(Comparator var1) {
      if (var1 != this._comparator) {
         this._comparator = var1;
         this.sort();
         this.getListenerManager().fireReset(this);
      }
   }

   @Override
   public Comparator getComparator() {
      return this._comparator;
   }

   public SortedReadableList(CollectionEventSource var1, Comparator var2) {
      this(var2);
      var1.addCollectionListener(this);
      if (var1 instanceof Collection) {
         this.reload(var1);
      }
   }

   public SortedReadableList(Comparator var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._comparator = var1;
   }

   @Override
   protected void reload(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
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
