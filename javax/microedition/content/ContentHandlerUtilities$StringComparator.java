package javax.microedition.content;

import net.rim.device.api.util.Comparator;

final class ContentHandlerUtilities$StringComparator implements Comparator {
   public ContentHandlerUtilities$StringComparator() {
   }

   @Override
   public final int compare(Object var1, Object var2) {
      Object var3 = var1;
      Object var4 = var2;
      return ((String)var3).compareTo((String)var4);
   }
}
