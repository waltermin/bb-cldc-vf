package net.rim.device.cldc.io.utility;

public final class SessionStats {
   private int _bytesSent;
   private int _bytesReceived;
   private long _creationTime = System.currentTimeMillis();
   private String _connectedHost;
   private int _connectedPort;

   public final int getBytesSent() {
      return this._bytesSent;
   }

   public final int getBytesReceived() {
      return this._bytesReceived;
   }

   public final long getDuration() {
      return System.currentTimeMillis() - this._creationTime;
   }

   public final void addToSent(int var1) {
      this._bytesSent += var1;
   }

   public final void addToReceived(int var1) {
      this._bytesReceived += var1;
   }

   public final void setConnectedHost(String var1, int var2) {
      this._connectedHost = var1;
      this._connectedPort = var2;
   }

   public final String getConnectedHost() {
      return this._connectedHost;
   }

   public final int getConnectedPort() {
      return this._connectedPort;
   }
}
