package javax.microedition.lcdui;

import java.util.Vector;

final class Display$CallSeriallyQueue extends Vector implements Runnable {
   public final void addElement(Runnable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private Display$CallSeriallyQueue() {
   }

   Display$CallSeriallyQueue(Display$1 var1) {
      this();
   }
}
