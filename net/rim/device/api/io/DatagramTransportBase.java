package net.rim.device.api.io;

import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.device.internal.io.TrafficLogger;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

public class DatagramTransportBase extends TransportBase implements GlobalEventListener {
   public long GUID;
   protected String STR;
   protected DatagramConnectionBase _subConnection;
   protected SendPacketThread _sendThread;
   protected WeakReference[] _superConnections = new WeakReference[0];
   private int _superConnectionsPurge;
   private WeakReference[] _dgsls = new WeakReference[16];
   private int[] _datagramIds = new int[16];
   private int[] _subIds = new int[16];
   private int _nextIdIndex;
   private Datagram _sendCurrentDatagram;
   private Thread _sendCurrentThread;
   private Datagram[] _sendDatagramList = new Datagram[4];
   private Thread[] _sendThreadList = new Thread[4];
   private int _sendListCount;
   private static final int MAX_DATAGRAM_IDS;
   private static final int INITIAL_SEND_CAPACITY;

   @Override
   public void init() {
      throw null;
   }

   public void init(DatagramConnection var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void addConnection(DatagramConnection var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void addSubConnection(DatagramConnectionBase var1) {
      DatagramReceiveThread.getInstance().addConnection(var1, this);
   }

   public void close(DatagramConnection var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getMaximumLength() {
      throw null;
   }

   public int getNominalLength() {
      return this.getMaximumLength();
   }

   protected void superSend(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void addDatagramForSend(Datagram var1, Thread var2) {
      int var3 = this._sendDatagramList.length;
      if (this._sendListCount >= var3) {
         var3 *= 2;
         Array.resize(this._sendDatagramList, var3);
         Array.resize(this._sendThreadList, var3);
      }

      this._sendDatagramList[this._sendListCount] = var1;
      this._sendThreadList[this._sendListCount] = var2;
      this._sendListCount++;
   }

   private int findDatagramForSend(Datagram var1, Thread var2) {
      for (int var3 = this._sendListCount - 1; var3 >= 0; var3--) {
         if (this._sendThreadList[var3] == var2 && this._sendDatagramList[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   private void removeDatagramForSend(int var1) {
      this._sendListCount--;
      System.arraycopy(this._sendDatagramList, var1 + 1, this._sendDatagramList, var1, this._sendListCount - var1);
      System.arraycopy(this._sendThreadList, var1 + 1, this._sendThreadList, var1, this._sendListCount - var1);
      this._sendDatagramList[this._sendListCount] = null;
      this._sendThreadList[this._sendListCount] = null;
      if (this._sendListCount <= 0 && this._sendDatagramList.length > 4) {
         Array.resize(this._sendDatagramList, 4);
         Array.resize(this._sendThreadList, 4);
         this._sendListCount = 0;
      }
   }

   protected final void kickSend() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void send(Datagram var1, DatagramAddressBase var2, IOProperties var3, DatagramStatusListener var4, int var5) {
      this.send(var1);
   }

   public void send(Datagram var1) {
      throw null;
   }

   protected void subSend(Datagram var1, DatagramStatusListener var2, int var3, Datagram var4) {
      if (var3 != 0) {
         int var5 = this._subConnection.allocateDatagramId(var1);
         if (var5 != 0) {
            this.addDgramId(var2, var3, var5);
         }
      }

      if (var4 instanceof DatagramBase && var1 instanceof DatagramBase) {
         ((DatagramBase)var4).copyFlagsInto((DatagramBase)var1);
      }

      this._subConnection.send(var1);
   }

   public void superCancel(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void cancel(Datagram var1) {
   }

   protected void addDgramId(DatagramStatusListener var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Datagram newDatagram(byte[] var1, int var2, int var3, String var4) {
      return new DatagramBase(var1, var2, var3, var4);
   }

   public DatagramAddressBase newDatagramAddressBase(String var1, boolean var2) {
      DatagramAddressBase var3 = new DatagramAddressBase(var1);
      if (var2) {
         var3.swap();
      }

      return var3;
   }

   public DatagramAddressBase newDatagramAddressBase(DatagramAddressBase var1, boolean var2) {
      DatagramAddressBase var3 = new DatagramAddressBase(var1);
      if (var2) {
         var3.swap();
      }

      return var3;
   }

   protected boolean passUpDatagram(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void processReceivedDatagram(Datagram var1) {
      throw null;
   }

   public void superProcessReceivedDatagram(Datagram var1) {
      if (super._tLogger != null) {
         super._tLogger.bytesReceived(this, 1, var1.getAddress(), var1.getLength(), var1.getData());
      }

      this.processReceivedDatagram(var1);
   }

   protected int getNextDatagramId(DatagramBase var1) {
      return 0;
   }

   public int allocateDatagramId(Datagram var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected void forwardDgslEvent(int var1, int var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void passDgslEvent(int var1, int var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void xmitDgslEvent(DatagramStatusListener var1, int var2, int var3, Object var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected int lookupDgramIndexFromSubId(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected int lookupDgramIndexFromDgramId(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected int getDgramIdByIndex(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void addSendRequest(Object var1, Datagram var2) {
      if (this._sendThread == null) {
         this._sendThread = new SendPacketThread();
         ProtocolDaemon.getInstance().startThread(this._sendThread);
      }

      this._sendThread.addRequest(var1, var2);
   }

   protected byte[] setup(int var1, Object var2) {
      return this._subConnection != null ? this._subConnection.setup(var1, var2) : null;
   }

   public void datagramProcessed(int var1) {
      if (this._subConnection != null) {
         this._subConnection.datagramProcessed(var1);
      }
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -1270659756336956134L) {
         this.kickSend();
      }
   }

   @Override
   public void setTrafficLogger(TrafficLogger var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
