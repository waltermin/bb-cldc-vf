package net.rim.device.internal.deviceoptions;

import java.util.Vector;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.internal.proxy.Proxy;

public final class DeviceOptions implements GlobalEventListener {
   private Vector _legacyListeners = new Vector();
   private Vector _providers = new Vector();
   private OptionsProviderChangeListener _listener;
   private LongHashtable _globalEventListeners = new LongHashtable(9);
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

   public static final void addLegacyDeviceOptionsListener(LegacyDeviceOptionsListener listener) {
      DeviceOptions instance = getInstance();
      synchronized (instance._legacyListeners) {
         instance._legacyListeners.addElement(listener);
      }
   }

   public static final void removeLegacyDeviceOptionsListener(LegacyDeviceOptionsListener listener) {
      DeviceOptions instance = getInstance();
      synchronized (instance._legacyListeners) {
         instance._legacyListeners.removeElement(listener);
      }
   }

   public static final void addOptionsProvider(OptionsProvider provider) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void removeOptionsProvider(OptionsProvider provider) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final Vector getOptionsProviders() {
      return this._providers;
   }

   public final synchronized void setLegacyDeviceOptions(DataBuffer buffer) {
      Vector listeners = this._legacyListeners;
      synchronized (listeners) {
         int size = listeners.size();
         int position = buffer.getPosition();

         for (int i = 0; i < size; i++) {
            ((LegacyDeviceOptionsListener)listeners.elementAt(i)).setLegacyDeviceOptions(buffer);
            buffer.setPosition(position);
         }
      }
   }

   public static final long readTime(DataBuffer buffer) {
      long result = buffer.readUnsignedByte();
      result += buffer.readUnsignedByte() * 60;
      result += buffer.readUnsignedByte() * 3600;
      result *= 1000;
      buffer.skipBytes(7);
      return result;
   }

   @Override
   public final void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      GlobalEventListener globalEventListener = (GlobalEventListener)this._globalEventListeners.get(guid);
      if (globalEventListener != null) {
         globalEventListener.eventOccurred(guid, data0, data1, object0, object1);
      }
   }

   public static final void setListener(OptionsProviderChangeListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
