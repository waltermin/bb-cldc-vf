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
   protected CyclicQueue _datagrams = new CyclicQueue(8);
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
      if (this._isActive) {
         this._transport.close(this);
         this._isActive = false;
         synchronized (this._datagrams) {
            this._datagrams.notifyAll();
         }

         int length;
         Datagram[] sendingDatagrams;
         synchronized (this._sendingDatagrams) {
            length = this._sendingDatagrams.length;
            if (length <= 0) {
               return;
            }

            sendingDatagrams = new Datagram[length];
            System.arraycopy(this._sendingDatagrams, 0, sendingDatagrams, 0, length);
         }

         for (int i = length - 1; i >= 0; i--) {
            this._transport.superCancel(sendingDatagrams[i]);
         }
      }
   }

   @Override
   public Connection openPrim(String name, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int getProperties(String _1) {
      throw null;
   }

   protected void checkForClosed() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTrafficLogger(TrafficLogger logger) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void cancel(Datagram datagram) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected boolean isAddressed(String address) {
      try {
         return this.isAddressed(this.newDatagramAddressBase(address, false));
      } catch (Throwable t) {
         return false;
      }
   }

   protected boolean isAddressed(DatagramAddressBase address) {
      return false;
   }

   public void copyFlagsInto(DatagramBase dg) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public Datagram newDatagram() {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, 0, null);
   }

   public void datagramProcessed(int dgId) {
      if (dgId != 0) {
         this._transport.datagramProcessed(dgId);
      }
   }

   public void handleDatagramStatus(int datagramId, int event, Object context) {
      if (this._listener != null) {
         try {
            this._listener.updateDatagramStatus(datagramId, event, context);
            return;
         } catch (Throwable var5) {
         }
      }
   }

   public Datagram newDatagram(byte[] buffer) {
      this.checkForClosed();
      return this._transport.newDatagram(buffer, 0, buffer.length, null);
   }

   public int allocateDatagramId(Datagram datagram) {
      return this._transport.allocateDatagramId(datagram);
   }

   public void setConnectionListener(ConnectionListener listener) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Datagram newDatagram(byte[] buffer, int offset, int length) {
      this.checkForClosed();
      return this._transport.newDatagram(buffer, offset, length, null);
   }

   public Datagram newDatagram(byte[] buffer, int offset, int length, String address) {
      this.checkForClosed();
      return this._transport.newDatagram(buffer, offset, length, address);
   }

   public DatagramAddressBase newDatagramAddressBase(String address, boolean swap) {
      return this._transport.newDatagramAddressBase(address, swap);
   }

   public DatagramAddressBase newDatagramAddressBase(DatagramAddressBase addressBase, boolean swap) {
      return this._transport.newDatagramAddressBase(addressBase, swap);
   }

   public void processReceivedDatagram(Datagram datagram) {
      synchronized (this._datagrams) {
         this._datagrams.enqueue(datagram);
         this._datagrams.notify();
      }

      if (this._connectionListener != null) {
         try {
            this._connectionListener.dataAvailable(this);
            return;
         } catch (Throwable var5) {
         }
      }
   }

   public DatagramStatusListener getDatagramStatusListener() {
      return this._listener;
   }

   public void setDatagramStatusListener(DatagramStatusListener listener) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] setup(int callType, Object context) {
      return this._transport.setup(callType, context);
   }

   public void setTimeout(long timeout) {
      this._timeout = timeout;
      this._isTimeOutSet = true;
   }

   @Override
   public boolean isFlagSet(int flag) {
      return (this._validFlags & flag) != 0 && (this._flags & flag) != 0;
   }

   @Override
   public int getFlag(int flag) {
      if ((this._validFlags & flag) != 0) {
         return (this._flags & flag) != 0 ? 1 : 0;
      } else {
         return -1;
      }
   }

   @Override
   public void setFlag(int flag, boolean value) {
      if (value) {
         this._flags |= flag;
      } else {
         this._flags &= ~flag;
      }

      this._validFlags |= flag;
   }

   @Override
   public Object getProperty(String name) {
      return this._properties == null ? null : this._properties.get(name);
   }

   @Override
   public Object setProperty(String name, Object data) {
      if (this._properties == null) {
         this._properties = new Hashtable();
      }

      return this._properties.put(name, data);
   }

   @Override
   public Datagram newDatagram(byte[] buffer, int length, String address) {
      this.checkForClosed();
      return this._transport.newDatagram(buffer, 0, length, address);
   }

   @Override
   public Datagram newDatagram(byte[] buffer, int length) {
      this.checkForClosed();
      return this._transport.newDatagram(buffer, 0, length, null);
   }

   @Override
   public Datagram newDatagram(int length, String address) {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, length, address);
   }

   @Override
   public Datagram newDatagram(int length) {
      this.checkForClosed();
      return this._transport.newDatagram(null, 0, length, null);
   }

   @Override
   public void receive(Datagram datagram) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void send(Datagram datagram) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public String getLocalAddress() {
      throw new RuntimeException("cod2jar: type check");
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
