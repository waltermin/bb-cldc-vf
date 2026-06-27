package net.rim.device.api.ui;

import net.rim.device.api.util.Comparator;

class MenuItem$1 implements Comparator {
   @Override
   public int compare(Object var1, Object var2) {
      return ((MenuItem)var1).getOrdinal() - ((MenuItem)var2).getOrdinal();
   }
}
