package javax.microedition.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import net.rim.device.internal.io.RIMConnector;
import net.rim.vm.TraceBack;

public class Connector {
   public static final int READ;
   public static final int WRITE;
   public static final int READ_WRITE;

   private Connector() {
   }

   public static Connection open(String var0) {
      int var1 = TraceBack.getCallingModule(0);
      return RIMConnector.open(var1, var0, 3, false);
   }

   public static Connection open(String var0, int var1) {
      int var2 = TraceBack.getCallingModule(0);
      return RIMConnector.open(var2, var0, var1, false);
   }

   public static Connection open(String var0, int var1, boolean var2) {
      int var3 = TraceBack.getCallingModule(0);
      return RIMConnector.open(var3, var0, var1, var2);
   }

   public static DataInputStream openDataInputStream(String var0) {
      int var1 = TraceBack.getCallingModule(0);
      return RIMConnector.openDataInputStream(var1, var0);
   }

   public static DataOutputStream openDataOutputStream(String var0) {
      int var1 = TraceBack.getCallingModule(0);
      return RIMConnector.openDataOutputStream(var1, var0);
   }

   public static InputStream openInputStream(String var0) {
      int var1 = TraceBack.getCallingModule(0);
      return RIMConnector.openDataInputStream(var1, var0);
   }

   public static OutputStream openOutputStream(String var0) {
      int var1 = TraceBack.getCallingModule(0);
      return RIMConnector.openDataOutputStream(var1, var0);
   }
}
