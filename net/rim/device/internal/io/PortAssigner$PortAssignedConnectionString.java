package net.rim.device.internal.io;

public class PortAssigner$PortAssignedConnectionString {
   private boolean _portWasAssigned;
   private boolean _localPortWasAssigned;
   private int _port = -1;
   private int _localPort = -1;
   private String _connectionString;
   private static String COMPARISON_STRING;
   private static String SLASH_SLASH;

   public PortAssigner$PortAssignedConnectionString(String var1, int var2, boolean var3) {
      this._portWasAssigned = var3;
      this._port = var2;
      this._connectionString = var1;
   }

   public boolean getPortAssigned() {
      return this._portWasAssigned;
   }

   public boolean getLocalPortAssigned() {
      return this._localPortWasAssigned;
   }

   public void setPortAssigned(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getConnectionString() {
      return this._connectionString;
   }

   public void setConnectionString(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getPort() {
      return this._port;
   }

   public void setPort(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getLocalPort() {
      return this._localPort;
   }

   void setLocalPort(int var1) {
      this._localPort = var1;
      this._localPortWasAssigned = true;
   }
}
