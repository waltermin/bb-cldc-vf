package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.Phone;

class AbstractCallCommandHandler extends Phone {
   private int _order;
   private boolean _registered;
   private AbstractCallCommandHandler _next;
   private static final long HANDLERS_GUID;
   private static AbstractCallCommandHandler _first;

   protected AbstractCallCommandHandler(int var1) {
      this._order = var1;
   }

   public final void register() {
      if (this._order >= 100 && this._order <= 2000) {
         internalRegister(this);
      } else {
         throw new Object();
      }
   }

   public final void deregister() {
      this._registered = false;
      this.onDeregistration();
   }

   public final boolean isRegistered() {
      return this._registered;
   }

   protected void onRegistration() {
   }

   protected void onDeregistration() {
   }

   protected final Phone getNext() {
      AbstractCallCommandHandler var1 = this._next;

      while (!var1.isRegistered()) {
         var1 = var1._next;
      }

      return var1;
   }

   static void internalRegister(AbstractCallCommandHandler var0) {
      if (var0._next != null) {
         if (!var0._registered) {
            var0._registered = true;
            var0.onRegistration();
         }
      } else {
         int var1 = var0._order;
         AbstractCallCommandHandler var2 = null;

         AbstractCallCommandHandler var3;
         for (var3 = getFirstHandler(); var3 != null; var3 = var3._next) {
            if (var3._order == var1) {
               AbstractCallCommandHandler var4 = var3;
               var3 = var4._next;
               var4._next = null;
               var4.deregister();
               break;
            }

            if (var3._order < var1) {
               break;
            }

            var2 = var3;
         }

         var0._next = var3;
         if (var2 == null) {
            setFirstHandler(var0);
         } else {
            var2._next = var0;
         }

         var0._registered = true;
         var0.onRegistration();
      }
   }

   private static AbstractCallCommandHandler getFirstHandler() {
      if (_first == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _first = (AbstractCallCommandHandler)var0.getOrWaitFor(-6648725956671550232L);
      }

      return _first;
   }

   private static void setFirstHandler(AbstractCallCommandHandler var0) {
      _first = var0;
      ApplicationRegistry var1 = ApplicationRegistry.getApplicationRegistry();
      var1.replace(-6648725956671550232L, _first);
   }

   @Override
   public final String getCallName(int var1) {
      return this.getCallName(var1, false);
   }

   @Override
   public final String getCallPhoneNumber(int var1) {
      return this.getCallPhoneNumber(var1, false);
   }
}
