package javax.microedition.lcdui;

import net.rim.device.api.util.Comparator;

class Alert$CommandComparator implements Comparator {
   @Override
   public int compare(Object var1, Object var2) {
      return ((Command)var1).getPriority() - ((Command)var2).getPriority();
   }
}
