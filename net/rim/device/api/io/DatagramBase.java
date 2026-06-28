package net.rim.device.api.io;

import java.util.Hashtable;
import javax.microedition.io.Datagram;
import net.rim.device.api.util.DataBuffer;

public class DatagramBase extends DataBuffer implements Datagram, IOProperties {
   protected DatagramAddressBase _addressBase;
   protected Hashtable _properties;
   protected int _flags;
   protected int _validFlags;
   protected int _datagramId;
   protected DatagramStatusListener _listener;
   public static final int DG_NULL_ID;

   public void copy(DatagramBase dgram) {
      this.setData(dgram.getData(), dgram.getOffset(), dgram.getLength());
      this._addressBase = dgram._addressBase;
      this._datagramId = dgram._datagramId;
      this._properties = dgram._properties;
      this._flags = dgram._flags;
      this._validFlags = dgram._validFlags;
   }

   public DatagramAddressBase getAddressBase() {
      return this._addressBase;
   }

   protected DatagramAddressBase newAddressBase() {
      return new DatagramAddressBase();
   }

   public void resetDataBuffer() {
      super.reset();
   }

   public void simpleReset() {
      this._addressBase = null;
      this._flags = 0;
      this._validFlags = 0;
      this._datagramId = 0;
      this._listener = null;
      this._properties = null;
   }

   public int getDatagramId() {
      return this._datagramId;
   }

   public void setDatagramId(int datagramId) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setAddressBase(DatagramAddressBase addressBase) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public DatagramStatusListener getDatagramStatusListener() {
      return this._listener;
   }

   public void setDatagramStatusListener(DatagramStatusListener listener) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void copyFlagsInto(DatagramBase dst) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
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
   public boolean isFlagSet(int flag) {
      return (this._validFlags & flag) != 0 && (this._flags & flag) != 0;
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
   public void setAddress(Datagram datagram) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setAddress(String address) {
      if (address != null) {
         if (this._addressBase == null) {
            this._addressBase = this.newAddressBase();
         }

         this._addressBase.setAddress(address);
      } else {
         this._addressBase = null;
      }
   }

   @Override
   public int getOffset() {
      return this.getArrayStart();
   }

   @Override
   public byte[] getData() {
      return this.getArray();
   }

   @Override
   public String getAddress() {
      return this._addressBase != null ? this._addressBase.getAddress() : null;
   }

   @Override
   public void setData(byte[] buffer, int offset, int length) {
      if (buffer != null && offset + length > buffer.length) {
         throw new IllegalArgumentException();
      }

      this.setData(buffer, offset, length, true);
   }

   @Override
   public int getLength() {
      return super.getLength();
   }

   public DatagramBase(byte[] buffer, int offset, int length, String address) {
      super(buffer, offset, length, true);
      if (buffer != null && offset + length > buffer.length) {
         throw new IllegalArgumentException();
      }

      this.setAddress(address);
   }

   public DatagramBase(byte[] buffer, int offset, int length) {
      super(buffer, offset, length, true);
      if (buffer != null && offset + length > buffer.length) {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void reset() {
      super.reset();
      this.simpleReset();
   }

   @Override
   public void setLength(int length) {
      super.setLength(length);
   }

   public DatagramBase(byte[] buffer, int offset, int length, DatagramAddressBase addressBase) {
      super(buffer, offset, length, true);
      if (buffer != null && offset + length > buffer.length) {
         throw new IllegalArgumentException();
      }

      this.setAddressBase(addressBase);
   }

   public DatagramBase() {
   }
}
