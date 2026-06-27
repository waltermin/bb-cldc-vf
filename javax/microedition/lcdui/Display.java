package javax.microedition.lcdui;

import java.util.Hashtable;
import java.util.Timer;
import java.util.Vector;
import javax.microedition.midlet.MIDlet;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.ui.Keypad;
import net.rim.device.internal.system.InternalServices;

public class Display {
   private MIDlet _midlet;
   private Displayable _current;
   private Displayable _lastDisplayable;
   private Display$SwitchDisplayablesRunnable _switchDisplayablesRunnable;
   private Display$CallSeriallyQueue _callSeriallyRunnables;
   private static Hashtable _midletMap;
   private static Vector _displayOrder;
   public static final int LIST_ELEMENT;
   public static final int CHOICE_GROUP_ELEMENT;
   public static final int ALERT;
   public static final int COLOR_BACKGROUND;
   public static final int COLOR_FOREGROUND;
   public static final int COLOR_HIGHLIGHTED_BACKGROUND;
   public static final int COLOR_HIGHLIGHTED_FOREGROUND;
   public static final int COLOR_BORDER;
   public static final int COLOR_HIGHLIGHTED_BORDER;
   static int _bestAlertImageHeight;
   static int _bestAlertImageWidth;
   static int _bestElementImageHeight;
   static int _bestElementImageWidth;
   static Display$FlashBacklightTimerTask _flashBacklightTask;

   private Display(MIDlet var1) {
      this._midlet = var1;
      this._switchDisplayablesRunnable = null;
      this._callSeriallyRunnables = new Display$CallSeriallyQueue(null);
   }

   MIDlet getMIDlet() {
      return this._midlet;
   }

   private static void moveDisplayToFront(Display var0) {
      _displayOrder.removeElement(var0);
      _displayOrder.insertElementAt(var0, 0);
   }

   private static void moveDisplayToBack(Display var0) {
      _displayOrder.removeElement(var0);
      _displayOrder.addElement(var0);
   }

   static Displayable getCurrentDisplayable() {
      return _displayOrder.size() > 0 ? ((Display)_displayOrder.elementAt(0))._current : null;
   }

   private void switchDisplayables(Displayable var1, Displayable var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static Display getDisplay(MIDlet var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getColor(int var1) {
      switch (var1) {
         case -1:
            throw new Object();
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         default:
            return 0;
      }
   }

   public int getBorderStyle(boolean var1) {
      return 0;
   }

   public boolean isColor() {
      return net.rim.device.api.ui.Graphics.isColor();
   }

   public int numColors() {
      return this.isColor() ? net.rim.device.api.ui.Graphics.getNumColors() : 2;
   }

   public int numAlphaLevels() {
      return this.isColor() ? 16 : 2;
   }

   public Displayable getCurrent() {
      return this._current;
   }

   public void setCurrent(Displayable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void startDisplaySwitch(Application var1, Displayable var2, Displayable var3) {
      if (this._switchDisplayablesRunnable != null) {
         this._switchDisplayablesRunnable.setNewDisplayable(var3);
      } else {
         this._switchDisplayablesRunnable = new Display$SwitchDisplayablesRunnable(this, var2, var3);
         var1.invokeLater(this._switchDisplayablesRunnable);
      }
   }

   public void setCurrent(Alert var1, Displayable var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setCurrentItem(Item var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void callSerially(Runnable var1) {
      this._callSeriallyRunnables.addElement(var1);
   }

   public boolean flashBacklight(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (this.getCurrent().isShown()) {
         if (var1 == 0) {
            if (_flashBacklightTask != null) {
               _flashBacklightTask.cancel();
               _flashBacklightTask = null;
               return true;
            }
         } else {
            int var2 = var1 % 1000;
            int var3 = var1 / 1000;
            if (var2 > 0) {
               var3++;
            }

            if (_flashBacklightTask != null) {
               _flashBacklightTask.cancel();
            }

            int var4;
            if (var3 % 2 == 0) {
               var4 = var3;
            } else {
               var4 = var3 + 1;
            }

            if (Backlight.isEnabled()) {
               Backlight.enable(false);
            }

            _flashBacklightTask = new Display$FlashBacklightTimerTask(var4);
            Object var5 = new Object();
            ((Timer)var5).schedule(_flashBacklightTask, 0, 1000);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean vibrate(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      Displayable var2 = this.getCurrent();
      if (var2 != null && var2.isShown()) {
         net.rim.device.api.system.Alert.stopVibrate();
         if (var1 != 0) {
            net.rim.device.api.system.Alert.startVibrate(var1);
         }

         return true;
      } else {
         return false;
      }
   }

   public int getBestImageWidth(int var1) {
      switch (var1) {
         case 0:
            throw new Object();
         case 1:
         case 2:
         default:
            return _bestElementImageWidth;
         case 3:
            return _bestAlertImageWidth;
      }
   }

   public int getBestImageHeight(int var1) {
      switch (var1) {
         case 0:
            throw new Object();
         case 1:
         case 2:
         default:
            return _bestElementImageHeight;
         case 3:
            return _bestAlertImageHeight;
      }
   }

   static int getGameAction(int var0) {
      switch (var0) {
         case -8:
            return 8;
         case 1:
            return 1;
         case 2:
            return 2;
         case 5:
            return 5;
         case 6:
            return 6;
         default:
            int var1 = Keypad.getHardwareLayout();
            int var2 = InternalServices.getHardwareID();
            if (InternalServices.isReducedFormFactor()) {
               switch ((char)var0) {
                  case '0':
                     return 8;
                  case '2':
                     return 1;
                  case '4':
                     return 2;
                  case '6':
                     return 5;
                  case '8':
                     return 6;
                  case 'A':
                  case 'a':
                     return 10;
                  case 'L':
                  case 'l':
                     return 12;
                  case 'O':
                  case 'o':
                     return 11;
                  case 'Q':
                  case 'q':
                     return 9;
               }
            } else if (var1 != 1364669234 && var2 != -1677720317 && var2 != 469763332 && var2 != 469763334) {
               switch ((char)var0) {
                  case '\b':
                  case 'G':
                  case 'K':
                  case 'S':
                  case 'g':
                  case 'k':
                  case 's':
                     return 5;
                  case ' ':
                     return 8;
                  case 'A':
                  case 'D':
                  case 'H':
                  case 'L':
                  case 'a':
                  case 'd':
                  case 'h':
                  case 'l':
                     return 2;
                  case 'C':
                  case 'F':
                  case 'J':
                  case 'N':
                  case 'c':
                  case 'f':
                  case 'j':
                  case 'n':
                     return 6;
                  case 'O':
                  case 'o':
                     return 11;
                  case 'P':
                  case 'p':
                     return 12;
                  case 'Q':
                  case 'q':
                     return 9;
                  case 'R':
                  case 'U':
                  case 'r':
                  case 'u':
                     return 1;
                  case 'W':
                  case 'w':
                     return 10;
               }
            } else {
               switch ((char)var0) {
                  case ' ':
                     return 8;
                  case '2':
                  case 'E':
                  case 'e':
                     return 1;
                  case '4':
                  case 'S':
                  case 's':
                     return 2;
                  case '6':
                  case 'F':
                  case 'f':
                     return 5;
                  case '8':
                  case 'X':
                  case 'x':
                     return 6;
                  case 'A':
                  case 'a':
                     return 10;
                  case 'L':
                  case 'l':
                     return 12;
                  case 'O':
                  case 'o':
                     return 11;
                  case 'Q':
                  case 'q':
                     return 9;
               }
            }

            if (var0 == 0) {
               throw new Object();
            } else {
               return 0;
            }
      }
   }
}
