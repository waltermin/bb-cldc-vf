package java.rmi;

import java.io.IOException;

public class RemoteException extends IOException {
   public Throwable detail;

   public RemoteException() {
   }

   public RemoteException(String var1) {
   }

   public RemoteException(String var1, Throwable var2) {
      super(var1);
      this.detail = var2;
   }

   @Override
   public String getMessage() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
