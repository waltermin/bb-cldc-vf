package java.rmi;

import java.io.IOException;

public class RemoteException extends IOException {
   public Throwable detail;

   public RemoteException() {
   }

   public RemoteException(String s) {
   }

   public RemoteException(String s, Throwable throwable) {
      super(s);
      this.detail = throwable;
   }

   @Override
   public String getMessage() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
