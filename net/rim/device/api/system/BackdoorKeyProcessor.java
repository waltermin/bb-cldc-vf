package net.rim.device.api.system;

import net.rim.device.api.ui.Keypad;
import net.rim.device.internal.system.InternalServices;

public class BackdoorKeyProcessor {
   private int _keys;
   private int _altStatus;
   private BackdoorKeyListener _listener;
   private static boolean _developmentDevice;
   private static boolean _rimBrandedDevice;

   public BackdoorKeyProcessor(boolean var1, BackdoorKeyListener var2) {
      this._listener = var2;
      this.setAltStatus(var1);
   }

   public void setAltStatus(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static boolean isDevelopmentDevice() {
      return _developmentDevice || _rimBrandedDevice && !PersistentContent.isEncryptionEnabled();
   }

   public boolean keyDown(int var1) {
      return this.processKey(Keypad.key(var1), Keypad.status(var1));
   }

   public boolean keyChar(char var1, int var2, int var3) {
      return this.processKey(var1, var2);
   }

   private static int nextKey(int var0) {
      if (!InternalServices.isReducedFormFactor()) {
         return var0;
      }

      switch (var0) {
         case 65:
            return 83;
         case 66:
            return 78;
         case 67:
            return 86;
         case 68:
            return 70;
         case 69:
            return 82;
         case 71:
            return 72;
         case 74:
            return 75;
         case 79:
            return 80;
         case 81:
            return 87;
         case 84:
            return 89;
         case 85:
            return 73;
         case 90:
            return 88;
         default:
            return var0;
      }
   }

   public static int appendKeyDetectingMultitap(int var0, int var1) {
      var1 &= 255;
      int var2;
      return var1 == (var0 & 0xFF) && (var2 = nextKey(var1)) != var1 ? var0 & -256 | var2 : var0 << 8 | var1;
   }

   private boolean processKey(int var1, int var2) {
      if ((var2 & 1) != this._altStatus) {
         this._keys = 0;
         return false;
      } else {
         this._keys = appendKeyDetectingMultitap(this._keys, var1);
         if (this._listener.openProductionBackdoor(this._keys)) {
            this._keys = 0;
            return true;
         } else if (isDevelopmentDevice() && this._listener.openDevelopmentBackdoor(this._keys)) {
            this._keys = 0;
            return true;
         } else {
            return false;
         }
      }
   }
}
