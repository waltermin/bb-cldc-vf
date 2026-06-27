package net.rim.device.internal.io;

import java.util.Vector;
import net.rim.device.api.system.RadioStatusListener;
import net.rim.device.api.util.Arrays;
import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.vm.WeakReference;

final class PortAssigner$PromiscuousApnPortHolder implements RadioStatusListener {
   private int[] _clients;
   private int[] _keys;
   private Object[] _values;
   private Object[] _activeApns;
   private final PortAssigner this$0;

   private PortAssigner$PromiscuousApnPortHolder(PortAssigner var1) {
      this.this$0 = var1;
      this._keys = new int[0];
      this._values = new Object[0];
      this._clients = new int[0];
      this._activeApns = new Object[0];
      ProtocolDaemon var2 = ProtocolDaemon.getInstance();
      if (var2 != null) {
         var2.addRadioListener(this);
      }
   }

   @Override
   public final void signalLevel(int var1) {
   }

   @Override
   public final void networkStarted(int var1, int var2) {
   }

   @Override
   public final void baseStationChange() {
   }

   @Override
   public final void radioTurnedOff() {
   }

   @Override
   public final void networkStateChange(int var1) {
   }

   @Override
   public final void networkScanComplete(boolean var1) {
   }

   @Override
   public final void networkServiceChange(int var1, int var2) {
   }

   @Override
   public final void pdpStateChange(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void registerPromiscuousPort(int var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void deregisterPromiscuousPort(int var1, Object var2) {
      this.removeClient(var1);
      if (this.getClients(var1) <= 0) {
         Object var3 = this.get(var1);
         if (var3 != null) {
            for (int var4 = ((Vector)var3).size() - 1; var4 >= 1; var4--) {
               this.this$0.deregisterConnection(var1, var2, (String)((Vector)var3).elementAt(var4), false);
            }
         }

         this.remove(var1);
      }
   }

   private final void triggerPromiscuousPortsRegistration(String var1) {
      int[] var2 = this.keys();

      for (int var3 = var2.length - 1; var3 >= 0; var3--) {
         int var4 = var2[var3];
         Object var5 = this.get(var4);
         if (var5 != null && ((Vector)var5).size() > 0) {
            Object var6 = ((Vector)var5).elementAt(0);
            Object var7 = var6 != null ? ((WeakReference)var6).get() : null;
            if (var7 != null) {
               if (var5 != null && ((Vector)var5).indexOf(var1) < 0 && this.this$0.registerConnection(var4, var7, var1, false)) {
                  ((Vector)var5).addElement(var1);
               }
            } else {
               this.deregisterPromiscuousPort(var4, var7);
            }
         }
      }
   }

   private final int[] keys() {
      return this._keys;
   }

   private final Object remove(int var1) {
      int var2 = Arrays.getIndex(this._keys, var1);
      Object var3 = null;
      if (var2 >= 0) {
         var3 = this._values[var2];
         Arrays.removeAt(this._keys, var2);
         Arrays.removeAt(this._values, var2);
         Arrays.removeAt(this._clients, var2);
      }

      return var3;
   }

   private final Object get(int var1) {
      int var2 = Arrays.getIndex(this._keys, var1);
      return var2 >= 0 ? this._values[var2] : null;
   }

   private final void put(int var1, Object var2) {
      int var3 = Arrays.getIndex(this._keys, var1);
      if (var3 < 0) {
         var3 = this._keys.length;
         Arrays.add(this._keys, var1);
         Arrays.add(this._values, var2);
         Arrays.add(this._clients, 0);
      }

      this._keys[var3] = var1;
      this._values[var3] = var2;
      this._clients[var3] = 0;
   }

   private final void addClient(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private final void removeClient(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private final int getClients(int var1) {
      int var2 = Arrays.getIndex(this._keys, var1);
      return var2 >= 0 ? this._clients[var2] : 0;
   }

   PortAssigner$PromiscuousApnPortHolder(PortAssigner var1, PortAssigner$1 var2) {
      this(var1);
   }
}
