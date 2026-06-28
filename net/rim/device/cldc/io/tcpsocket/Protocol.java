package net.rim.device.cldc.io.tcpsocket;

import com.sun.cldc.io.ConnectionBaseInterface;
import javax.microedition.io.Connection;
import net.rim.device.cldc.io.utility.URL;

public final class Protocol implements ConnectionBaseInterface {
   private static final String LOCAL_PORT;
   private static final String INTERFACE;
   private static final String APN;
   private static final String TUNNEL_AUTH_USERNAME;
   private static final String TUNNEL_AUTH_PASSWORD;
   private static final String RETRY_NO_CONTEXT;

   @Override
   public final int getProperties(String name) {
      return 2;
   }

   @Override
   public final Connection openPrim(String name, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final Connection doConnection(URL url, String name, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final Connection doConnectionNotify(URL url, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
