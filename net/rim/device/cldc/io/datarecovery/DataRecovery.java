package net.rim.device.cldc.io.datarecovery;

import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.proxy.Proxy;

public class DataRecovery implements GlobalEventListener {
   protected final int _linkType;
   protected final long _guid;
   protected int _errorLevel;
   protected long _lastRecoveryTime;
   protected int _currentRecoveryBackoff;
   protected boolean _primed;
   protected Object[] _listeners;
   public static final long GUID;
   public static final long GUID_WIFI;
   public static final int LINK_TYPE_CELLULAR;
   public static final int LINK_TYPE_WIFI;
   public static final int CONNECTION_TYPE_RELAY;
   public static final int CONNECTION_TYPE_ROUTER;
   public static final int CONNECTION_TYPE_UNC;
   public static final int CONNECTION_TYPE_VPN;
   public static final int REPORT_ACTIVITY;
   public static final int REPORT_NO_RESPONSE;
   public static final int REPORT_UNREACHABLE;
   public static final int REPORT_TX_FLOW_CTRL;
   protected static final int THRESHOLD_RECOVERY;
   protected static final int THRESHOLD_RELAY_UNREACHABLE;
   protected static final int START_RECOVERY_BACKOFF;
   protected static final int MAX_RECOVERY_BACKOFF;

   public synchronized void addListener(DataRecoveryListener var1) {
      this._listeners = ListenerUtilities.addListener(this._listeners, var1);
   }

   public synchronized void removeListener(DataRecoveryListener var1) {
      this._listeners = ListenerUtilities.removeListener(this._listeners, var1);
   }

   public void fileReport(int var1) {
      this.fileReport(var1, 1, 1);
   }

   public void fileReport(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized boolean isConnectionAvailable() {
      return this._errorLevel < 4;
   }

   protected void informListeners(Object[] var1, int var2, int var3) {
      if (var1 != null) {
         Proxy.getInstance().invokeRunnable(new DataRecoveryRunnable(var1, var2, var3));
      }
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static DataRecovery getInstance(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static DataRecovery getInstance() {
      return getInstance(1);
   }

   protected DataRecovery(int var1, long var2) {
   }
}
