package net.rim.device.api.io;

import com.sun.cldc.io.ConnectionBaseInterface;
import java.util.Hashtable;
import javax.microedition.io.Connection;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import javax.microedition.io.UDPDatagramConnection;
import net.rim.device.api.util.CyclicQueue;
import net.rim.device.cldc.io.utility.URL;
import net.rim.device.internal.io.TrafficLogger;

public class DatagramConnectionBase implements DatagramConnection, IOProperties, ConnectionBaseInterface, UDPDatagramConnection {
   protected DatagramAddressBase _addressBase;
   protected DatagramAddressBase _receiveFilter;
   protected DatagramTransportBase _transport;
   protected CyclicQueue _datagrams = (CyclicQueue)(new Object(8));
   protected Datagram[] _sendingDatagrams = new Datagram[0];
   protected Hashtable _properties;
   protected int _flags;
   protected int _validFlags;
   protected long _timeout;
   protected boolean _isActive;
   protected DatagramStatusListener _listener;
   protected boolean _isTimeOutSet;
   protected TrafficLogger _tLogger;
   protected ConnectionListener _connectionListener;
   private URL _url;
   private String _name;
   protected static long DEFAULT_TIMEOUT;

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Connection openPrim(String var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getProperties(String var1) {
      throw null;
   }

   protected void checkForClosed() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTrafficLogger(TrafficLogger var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void cancel(Datagram var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected boolean isAddressed(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean isAddressed(DatagramAddressBase var1) {
      return false;
   }

   public void copyFlagsInto(DatagramBase var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public Datagram newDatagram() {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, 0, null);
   }

   public void datagramProcessed(int var1) {
      if (var1 != 0) {
         this._transport.datagramProcessed(var1);
      }
   }

   public void handleDatagramStatus(int var1, int var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Datagram newDatagram(byte[] var1) {
      this.checkForClosed();
      return this._transport.newDatagram(var1, 0, var1.length, null);
   }

   public int allocateDatagramId(Datagram var1) {
      return this._transport.allocateDatagramId(var1);
   }

   public void setConnectionListener(ConnectionListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Datagram newDatagram(byte[] var1, int var2, int var3) {
      this.checkForClosed();
      return this._transport.newDatagram(var1, var2, var3, null);
   }

   public Datagram newDatagram(byte[] var1, int var2, int var3, String var4) {
      this.checkForClosed();
      return this._transport.newDatagram(var1, var2, var3, var4);
   }

   public DatagramAddressBase newDatagramAddressBase(String var1, boolean var2) {
      return this._transport.newDatagramAddressBase(var1, var2);
   }

   public DatagramAddressBase newDatagramAddressBase(DatagramAddressBase var1, boolean var2) {
      return this._transport.newDatagramAddressBase(var1, var2);
   }

   public void processReceivedDatagram(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public DatagramStatusListener getDatagramStatusListener() {
      return this._listener;
   }

   public void setDatagramStatusListener(DatagramStatusListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] setup(int var1, Object var2) {
      return this._transport.setup(var1, var2);
   }

   public void setTimeout(long var1) {
      this._timeout = var1;
      this._isTimeOutSet = true;
   }

   @Override
   public boolean isFlagSet(int var1) {
      return (this._validFlags & var1) != 0 && (this._flags & var1) != 0;
   }

   @Override
   public int getFlag(int var1) {
      if ((this._validFlags & var1) != 0) {
         return (this._flags & var1) != 0 ? 1 : 0;
      } else {
         return -1;
      }
   }

   @Override
   public void setFlag(int var1, boolean var2) {
      if (var2) {
         this._flags |= var1;
      } else {
         this._flags &= ~var1;
      }

      this._validFlags |= var1;
   }

   @Override
   public Object getProperty(String var1) {
      return this._properties == null ? null : this._properties.get(var1);
   }

   @Override
   public Object setProperty(String var1, Object var2) {
      if (this._properties == null) {
         this._properties = (Hashtable)(new Object());
      }

      return this._properties.put(var1, var2);
   }

   @Override
   public Datagram newDatagram(byte[] var1, int var2, String var3) {
      this.checkForClosed();
      return this._transport.newDatagram(var1, 0, var2, var3);
   }

   @Override
   public Datagram newDatagram(byte[] var1, int var2) {
      this.checkForClosed();
      return this._transport.newDatagram(var1, 0, var2, null);
   }

   @Override
   public Datagram newDatagram(int var1, String var2) {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, var1, var2);
   }

   @Override
   public Datagram newDatagram(int var1) {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, var1, null);
   }

   @Override
   public void receive(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void send(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLocalAddress() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getLocalPort() {
      this.checkForClosed();
      return -1;
   }

   @Override
   public int getNominalLength() {
      this.checkForClosed();
      return this._transport.getNominalLength();
   }

   @Override
   public int getMaximumLength() {
      this.checkForClosed();
      return this._transport.getMaximumLength();
   }
}
