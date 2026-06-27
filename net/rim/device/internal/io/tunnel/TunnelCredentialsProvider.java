package net.rim.device.internal.io.tunnel;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMPersistentStore;
import net.rim.device.internal.proxy.Proxy;

public final class TunnelCredentialsProvider implements GlobalEventListener {
   private TunnelCredentials _tunnelCredentials;
   private PersistentObject _persistentObject = RIMPersistentStore.getPersistentObject(1004147189966295995L);
   private static final long GUID;

   public static final TunnelCredentialsProvider getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      TunnelCredentialsProvider var1 = (TunnelCredentialsProvider)var0.getOrWaitFor(1004147189966295995L);
      if (var1 == null) {
         var1 = new TunnelCredentialsProvider();
         var0.put(1004147189966295995L, var1);
      }

      return var1;
   }

   private TunnelCredentialsProvider() {
      this._tunnelCredentials = (TunnelCredentials)this._persistentObject.getContents();
      if (this._tunnelCredentials == null) {
         this._tunnelCredentials = (TunnelCredentials)(new Object(true, true, true));
         this._persistentObject.setContents(this._tunnelCredentials, 51);
         this._persistentObject.commit();
      }

      Proxy.getInstance().addGlobalEventListener(this);
   }

   public final String getApn() {
      return this._tunnelCredentials.apn;
   }

   public final String getApnUsername() {
      return this._tunnelCredentials.apnUsername;
   }

   public final String getApnPassword() {
      return this._tunnelCredentials.apnPassword;
   }

   public final boolean isEditingOptionsAllowed() {
      return this._tunnelCredentials.editingOptionsAllowed;
   }

   public final boolean isOutgoingSocketsAllowed() {
      return this._tunnelCredentials.outgoingSocketsAllowed;
   }

   public final boolean isIncomingSocketsAllowed() {
      return this._tunnelCredentials.incomingSocketsAllowed;
   }

   public final synchronized void setApn(String var1) {
      this._tunnelCredentials.apn = var1;
      this._persistentObject.commit();
   }

   public final synchronized void setApnUsername(String var1) {
      this._tunnelCredentials.apnUsername = var1;
      this._persistentObject.commit();
   }

   public final synchronized void setApnPassword(String var1) {
      this._tunnelCredentials.apnPassword = var1;
      this._persistentObject.commit();
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
