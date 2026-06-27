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

   public void copy(DatagramBase var1) {
      this.setData(var1.getData(), var1.getOffset(), var1.getLength());
      this._addressBase = var1._addressBase;
      this._datagramId = var1._datagramId;
      this._properties = var1._properties;
      this._flags = var1._flags;
      this._validFlags = var1._validFlags;
   }

   public DatagramAddressBase getAddressBase() {
      return this._addressBase;
   }

   protected DatagramAddressBase newAddressBase() {
      return new DatagramAddressBase();
   }

   public void resetDataBuffer() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
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

   public void setDatagramId(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setAddressBase(DatagramAddressBase var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public DatagramStatusListener getDatagramStatusListener() {
      return this._listener;
   }

   public void setDatagramStatusListener(DatagramStatusListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void copyFlagsInto(DatagramBase var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
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
   public boolean isFlagSet(int var1) {
      return (this._validFlags & var1) != 0 && (this._flags & var1) != 0;
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
   public void setAddress(Datagram var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setAddress(String var1) {
      if (var1 != null) {
         if (this._addressBase == null) {
            this._addressBase = this.newAddressBase();
         }

         this._addressBase.setAddress(var1);
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
   public void setData(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 + var3 > var1.length) {
         throw new Object();
      }

      this.setData(var1, var2, var3, true);
   }

   @Override
   public int getLength() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public DatagramBase(byte[] var1, int var2, int var3, String var4) {
      super(var1, var2, var3, true);
      if (var1 != null && var2 + var3 > var1.length) {
         throw new Object();
      }

      this.setAddress(var4);
   }

   public DatagramBase(byte[] var1, int var2, int var3) {
      super(var1, var2, var3, true);
      if (var1 != null && var2 + var3 > var1.length) {
         throw new Object();
      }
   }

   @Override
   public void reset() {
      super.reset();
      this.simpleReset();
   }

   @Override
   public void setLength(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public DatagramBase(byte[] var1, int var2, int var3, DatagramAddressBase var4) {
      super(var1, var2, var3, true);
      if (var1 != null && var2 + var3 > var1.length) {
         throw new Object();
      }

      this.setAddressBase(var4);
   }

   public DatagramBase() {
   }
}
