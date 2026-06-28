package net.rim.device.internal.io;

import java.util.Hashtable;
import javax.microedition.io.Connection;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntIntHashtable;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.io.tunnel.TunnelCredentialsProvider;
import net.rim.device.internal.system.RadioInternal;
import net.rim.vm.DebugSupport;
import net.rim.vm.WeakReference;

public final class PortAssigner {
   private Hashtable _apnPortMap;
   private Hashtable _apnPortMapAux;
   private String _apnName;
   private int _protocolType = 0;
   private long _id = 0;
   private PortAssigner$PromiscuousApnPortHolder _promiscuousApnPortHolder;
   private static String PORT_ALREADY_BOUND_ERROR_STRING;
   private static String RAW_TCP;
   private static String TRUE;
   private static final int MAX_SRC_PORT_NUM;
   private static final int MIN_SRC_PORT_NUM;
   private static final int SIM_PORT_MIN;
   private static final int SIM_PORT_MAX;
   private static final long ID_UDP;
   private static final long ID_TCP;
   private static String STR_UDP;
   private static String STR_TCP;
   private static final int PORT_OUT_OF_RANGE_SIM;
   private static final int TOO_MANY_PORT_REGISTERED;
   private static final int PORT_RESERVED;
   private static final int PORT_OUT_OF_RANGE;
   private static final int PORT_GENERAL_ERROR;
   private static final int PORT_ALREADY_BOUND;
   private static final int PORT_REG_ERROR;
   private static final int PORT_DEREG_ERROR;
   private static final int PORT_QUERY_ERROR;
   private static final int APN_ERROR;
   private static final int APN_NOT_REGISTERED_ERROR;
   private static Connection _mockConnection;
   private static String EMPTY_APN;

   public PortAssigner(int protocol) {
      String str = null;
      switch (protocol) {
         case 6:
            str = STR_TCP;
            this._id = -1053140461870259212L;
            this._protocolType = protocol;
            break;
         case 17:
            str = STR_UDP;
            this._id = -7261558872584336485L;
            this._protocolType = protocol;
            break;
         default:
            throw new IllegalArgumentException();
      }

      this._apnPortMap = new Hashtable(2);
      this._apnPortMapAux = new Hashtable(2);
      this._apnName = TunnelCredentialsProvider.getInstance().getApn();
      this._promiscuousApnPortHolder = new PortAssigner$PromiscuousApnPortHolder(this, null);
      EventLogger.register(this._id, str, 2);
   }

   public static final PortAssigner getInstance(int protType) {
      long id = 0;
      switch (protType) {
         case 6:
            id = -1053140461870259212L;
            break;
         case 17:
            id = -7261558872584336485L;
            break;
         default:
            throw new IllegalArgumentException();
      }

      ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
      PortAssigner hpa = (PortAssigner)ar.getOrWaitFor(id);
      if (hpa == null) {
         hpa = new PortAssigner(protType);
         ar.put(id, hpa);
      }

      return hpa;
   }

   public final PortAssigner$PortAssignedConnectionString checkPorts(String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void registerConnection(int port, Object connection) {
      this.registerConnection(port, connection, null, true);
   }

   public final void registerConnection(int port, String apn) {
      this.registerConnection(port, _mockConnection, apn, false);
   }

   public final void registerConnection(int port, Object connection, String apn) {
      this.registerConnection(port, connection, apn, false);
   }

   private final boolean registerConnection(int port, Object connection, String apn, boolean promiscuousMode) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void deregisterConnection(int port, Object connection) {
      this.deregisterConnection(port, connection, null, true);
   }

   public final void deregisterConnection(int port, String apn) {
      this.deregisterConnection(port, _mockConnection, apn, false);
   }

   public final void deregisterConnection(int port, Object connection, String apn) {
      this.deregisterConnection(port, connection, apn, false);
   }

   public final void deregisterConnection(int port, Object connection, String apn, boolean promiscuousMode) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final boolean isPortBound(int port, String apn) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final boolean isPortBoundInternal(int port, IntHashtable portMap, IntIntHashtable portMapAux, int apnId) {
      boolean retval = true;
      WeakReference wr = (WeakReference)portMap.get(port);
      if (wr == null) {
         if (portMapAux.get(port) <= 0) {
            retval = false;
         }
      } else if (wr.get() == null && portMapAux.get(port) <= 0) {
         retval = false;
         portMap.remove(port);
      }

      int code = 0;

      try {
         code = RadioInternal.registerPort(this._protocolType, 2, port, apnId);
      } catch (Exception re) {
         EventLogger.logEvent(this._id, 1347580261, 3);
      }

      switch (code) {
         case -101:
            if (!retval) {
               return retval;
            }
         case -103:
         case -102:
            retval = true;
            break;
         case 0:
            if (this._protocolType == 6
               && DeviceInfo.isSimulator()
               && StringUtilities.strEqualIgnoreCase(DebugSupport.getenv(RAW_TCP), TRUE, 1701707776)
               && (port < 19700 || port > 19799)) {
               retval = true;
            }
      }

      return retval;
   }

   public final int getUnusedPort(String apn) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
