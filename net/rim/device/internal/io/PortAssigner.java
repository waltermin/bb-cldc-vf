package net.rim.device.internal.io;

import java.util.Hashtable;
import javax.microedition.io.Connection;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntIntHashtable;
import net.rim.device.internal.io.tunnel.TunnelCredentialsProvider;

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

   public PortAssigner(int var1) {
      String var2 = null;
      switch (var1) {
         case 6:
            var2 = STR_TCP;
            this._id = -1053140461870259212L;
            this._protocolType = var1;
            break;
         case 17:
            var2 = STR_UDP;
            this._id = -7261558872584336485L;
            this._protocolType = var1;
            break;
         default:
            throw new Object();
      }

      this._apnPortMap = (Hashtable)(new Object(2));
      this._apnPortMapAux = (Hashtable)(new Object(2));
      this._apnName = TunnelCredentialsProvider.getInstance().getApn();
      this._promiscuousApnPortHolder = new PortAssigner$PromiscuousApnPortHolder(this, null);
      EventLogger.register(this._id, var2, 2);
   }

   public static final PortAssigner getInstance(int var0) {
      long var1 = 0;
      switch (var0) {
         case 6:
            var1 = -1053140461870259212L;
            break;
         case 17:
            var1 = -7261558872584336485L;
            break;
         default:
            throw new Object();
      }

      ApplicationRegistry var3 = ApplicationRegistry.getApplicationRegistry();
      PortAssigner var4 = (PortAssigner)var3.getOrWaitFor(var1);
      if (var4 == null) {
         var4 = new PortAssigner(var0);
         var3.put(var1, var4);
      }

      return var4;
   }

   public final PortAssigner$PortAssignedConnectionString checkPorts(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void registerConnection(int var1, Object var2) {
      this.registerConnection(var1, var2, null, true);
   }

   public final void registerConnection(int var1, String var2) {
      this.registerConnection(var1, _mockConnection, var2, false);
   }

   public final void registerConnection(int var1, Object var2, String var3) {
      this.registerConnection(var1, var2, var3, false);
   }

   private final boolean registerConnection(int var1, Object var2, String var3, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void deregisterConnection(int var1, Object var2) {
      this.deregisterConnection(var1, var2, null, true);
   }

   public final void deregisterConnection(int var1, String var2) {
      this.deregisterConnection(var1, _mockConnection, var2, false);
   }

   public final void deregisterConnection(int var1, Object var2, String var3) {
      this.deregisterConnection(var1, var2, var3, false);
   }

   public final void deregisterConnection(int var1, Object var2, String var3, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean isPortBound(int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final boolean isPortBoundInternal(int var1, IntHashtable var2, IntIntHashtable var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getUnusedPort(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
