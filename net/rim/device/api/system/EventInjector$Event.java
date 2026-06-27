package net.rim.device.api.system;

import net.rim.vm.Message;

public class EventInjector$Event {
   protected Message _msg;
   private static ApplicationManagerImpl _ami;

   EventInjector$Event(int var1, int var2, int var3, int var4, int var5, Object var6, Object var7) {
      this._msg = (Message)(new Object(var1, var2, var3, var4, var5));
      this._msg.setObject0(var6);
      this._msg.setObject1(var7);
   }

   public void post() {
      EventInjector.assertPermission();
      _ami.postMessageToForegroundProcess(this._msg);
   }

   public void setEvent(int var1) {
      this._msg.setEvent(var1);
   }

   public int getEvent() {
      return this._msg.getEvent();
   }

   public void setStatus(int var1) {
      this._msg.setData0(var1);
   }

   public int getStatus() {
      return this._msg.getData0();
   }

   int getDevice() {
      return this._msg.getDevice();
   }
}
