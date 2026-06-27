package net.rim.tid.im.util;

import net.rim.tid.awt.event.KeyEvent;

public class InputMethodHelper {
   public static boolean isIgnorableFunctionalKeyEvent(KeyEvent var0) {
      switch (var0.getKeyCode()) {
         case 17:
         case 18:
         case 19:
         case 273:
         case 4098:
            return true;
         default:
            return false;
      }
   }
}
