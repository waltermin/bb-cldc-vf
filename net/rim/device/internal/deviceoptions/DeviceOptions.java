package net.rim.device.internal.deviceoptions;

import java.util.Vector;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.internal.proxy.Proxy;

public final class DeviceOptions implements GlobalEventListener {
   private Vector _legacyListeners = (Vector)(new Object());
   private Vector _providers = (Vector)(new Object());
   private OptionsProviderChangeListener _listener;
   private LongHashtable _globalEventListeners = (LongHashtable)(new Object(9));
   private static final long ID;
   private static final int INITIAL_GLOBAL_EVENT_LISTENER_COUNT;
   private static DeviceOptions _instance;

   private DeviceOptions() {
      Proxy.getInstance().addGlobalEventListener(this);
   }

   public static final void DeviceOptionsMain() {
      _instance = new DeviceOptions();
      ApplicationRegistry.getApplicationRegistry().put(4606403601136413534L, _instance);
      init();
   }

   public static final void init() {
      SMSOptions.init();
   }

   public static final DeviceOptions getInstance() {
      if (_instance == null) {
         _instance = (DeviceOptions)ApplicationRegistry.getApplicationRegistry().waitFor(4606403601136413534L);
      }

      return _instance;
   }

   public static final void addLegacyDeviceOptionsListener(LegacyDeviceOptionsListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeLegacyDeviceOptionsListener(LegacyDeviceOptionsListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addOptionsProvider(OptionsProvider var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeOptionsProvider(OptionsProvider var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final Vector getOptionsProviders() {
      return this._providers;
   }

   public final synchronized void setLegacyDeviceOptions(DataBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long readTime(DataBuffer var0) {
      long var1 = var0.readUnsignedByte();
      var1 += var0.readUnsignedByte() * 60;
      var1 += var0.readUnsignedByte() * 3600;
      var1 *= 1000;
      var0.skipBytes(7);
      return var1;
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      Object var7 = this._globalEventListeners.get(var1);
      if (var7 != null) {
         ((GlobalEventListener)var7).eventOccurred(var1, var3, var4, var5, var6);
      }
   }

   public static final void setListener(OptionsProviderChangeListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
