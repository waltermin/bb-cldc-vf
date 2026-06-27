package net.rim.device.api.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.TraceBack;

public final class Clipboard {
   private Object _object;
   private long _pastePriorityTimeout = 60000;
   private long _pastePriorityExpiration;
   private boolean _notYetPasted = false;
   private static final long REGISTRY_NAME;
   private static final long PASTE_PRIORITY_TIMEOUT;
   private static Clipboard _instance;

   private Clipboard() {
   }

   public final Object get() {
      return this._object != null && !this.isClipboardDisabled() && !this.isClipboardAccessRestricted(this._object) ? this._object : null;
   }

   public static final synchronized Clipboard getClipboard() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _instance = (Clipboard)var0.getOrWaitFor(-2401061171848633112L);
         if (_instance == null) {
            _instance = new Clipboard();
            var0.put(-2401061171848633112L, _instance);
         }
      }

      return _instance;
   }

   private final boolean isClipboardAccessRestricted(Object var1) {
      if (!ApplicationControl.isIPCAllowed(true)) {
         return true;
      }

      if (var1 != null && !(var1 instanceof Object) && !(var1 instanceof Object)) {
         int var2 = TraceBack.getCallingModule(2);
         if (!ControlledAccess.verifyCodeModuleSignature(var2, 51)) {
            return true;
         }
      }

      return false;
   }

   private final boolean isClipboardDisabled() {
      return ITPolicy.getBoolean(24, 36, false);
   }

   public final boolean isNotYetPasted() {
      return this._notYetPasted;
   }

   public final boolean isTimeForPasteAsDefaultNotPassed() {
      return InternalServices.getUptime() < this._pastePriorityExpiration;
   }

   public final Object put(Object var1) {
      this._pastePriorityExpiration = InternalServices.getUptime();
      if (this.isClipboardDisabled()) {
         this._object = null;
         return null;
      }

      if (this.isClipboardAccessRestricted(var1)) {
         throw new Object();
      }

      Object var2 = this._object;
      this._object = var1;
      if (var1 != null) {
         this._pastePriorityExpiration = this._pastePriorityExpiration + this._pastePriorityTimeout;
      }

      return var2;
   }

   public final void setNotYetPasted(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setPastePriorityTimeout(long var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (var1 > 0) {
         this._pastePriorityTimeout = var1;
      } else {
         this._pastePriorityTimeout = 60000;
      }
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
