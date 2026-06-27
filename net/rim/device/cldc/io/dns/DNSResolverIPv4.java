package net.rim.device.cldc.io.dns;

import java.util.Enumeration;
import java.util.Vector;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.system.UDPPacketHeader;
import net.rim.device.api.system.UDPPacketListener;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.system.SimulatorServices;

public class DNSResolverIPv4 implements UDPPacketListener {
   private IntHashtable _packetIDtoDNSData;
   private IntHashtable _radioIDtoDNSData;
   private int _numQueries;
   private DNSResolverIPv4$DNSResolverIPv4Thread _thread;
   private DNSCache _cache;
   private int RECURSION_ENABLED;
   private static final long GUID;
   private static int _packetID;
   private static final int DNS_HEADER_SIZE;
   private static final int DNS_QUERY_UDP_PORT;
   private static DNSResolverIPv4 _instance;

   public int getAddressByHostname(String var1, DNSListener var2) {
      return this.getAddressByHostname(var1, var2, 0);
   }

   public Vector getAddressByHostname(String var1) {
      return this.getAddressByHostname(var1, 0);
   }

   public int getAddressByHostname(String var1, DNSListener var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Vector getAddressByHostname(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getAddressByHostname(String var1, DNSListener var2, int var3) {
      if (var2 == null) {
         throw new Object();
      }

      DNSRequest var4 = new DNSRequest(var1, var2, var3);
      this.prepRequest(var4, true);
      this._thread.addRequest(var4);
      return var4.getPacketId();
   }

   public Vector getAddressByHostname(String var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int getHostnameByAddress(byte[] var1, DNSListener var2) {
      return this.getHostnameByAddress(var1, var2, 0);
   }

   public Vector getHostnameByAddress(byte[] var1) {
      return this.getHostnameByAddress(var1, 0);
   }

   public int getHostnameByAddress(byte[] var1, DNSListener var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Vector getHostnameByAddress(byte[] var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getHostnameByAddress(byte[] var1, DNSListener var2, int var3) {
      if (var2 == null) {
         throw new Object();
      }

      DNSRequest var4 = new DNSRequest(var1, var2, var3);
      this.prepRequest(var4, true);
      this._thread.addRequest(var4);
      return var4.getPacketId();
   }

   public Vector getHostnameByAddress(byte[] var1, int var2) {
      DNSRequest var3 = new DNSRequest(var1, null, var2);
      this.prepRequest(var3, true);
      Vector var4 = this.executeQuery(var3);
      if (var3.getStatus() != 11) {
         throw new DNSException(var3.getStatus());
      } else {
         return var4;
      }
   }

   public Vector doBlockingQuery(DNSRequest var1) {
      this.prepRequest(var1, false);
      Vector var3 = this.executeQuery(var1);
      int var2 = var1.getQueryType() == 1 ? 1 : 11;
      if (var1.getStatus() != var2) {
         throw new DNSException(var1.getStatus());
      } else {
         return var3;
      }
   }

   public int doQuery(DNSRequest var1) {
      if (var1.getListener() == null) {
         throw new Object();
      }

      this.prepRequest(var1, false);
      this._thread.addRequest(var1);
      return var1.getPacketId();
   }

   public void clearResultFromCache(String var1, Object var2) {
      this._cache.removeFromCache(var1, var2);
   }

   public DNSCache getCache() {
      return this._cache;
   }

   public void setup(long var1, Object var3) {
      if (var1 == -3533413171973668650L) {
         this.RECURSION_ENABLED = 0;
      } else {
         if (var1 == -797472996234301532L) {
            this.RECURSION_ENABLED = 256;
         }
      }
   }

   @Override
   public void packetStatus(int var1, int var2) {
   }

   @Override
   public void packetReceived(UDPPacketHeader var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void packetNotSent(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void packetSent(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private DNSRequest processResponse(DNSRequest var1) {
      Object var2 = null;
      switch (var1.getAnswer().getRcode()) {
         case 0:
         default:
            this._cache.addToCache(var1);
            var2 = new Object();
            var1.setStatus(10);
            boolean var4 = false;
            int var5 = var1.getQueryType() == 1 ? 1 : 12;
            Vector var6 = var1.getAnswer().getAnswers();
            int var7 = var6.size();

            for (int var8 = 0; var8 < var7; var8++) {
               DNSMessageIPv4Resource var3 = (DNSMessageIPv4Resource)var6.elementAt(var8);
               if (var3.getType() == var5) {
                  ((Vector)var2).addElement(var3.getData());
               } else if (var3.getType() == 5) {
                  var4 = true;
               }
            }

            if (((Vector)var2).size() != 0) {
               EventLogger.logEvent(1197736374800106759L, 1382380625, 0);
               return this.endQuery(var1, var1.getQueryType() == 1 ? 1 : 11, (Vector)var2);
            } else {
               DNSRequest var10 = null;
               if (var4) {
                  EventLogger.logEvent(1197736374800106759L, 1130458705, 0);
                  if (var1.getCnameAttempts() < 9) {
                     var1.setCnameAttempts(var1.getCnameAttempts() + 1);
                     var10 = this.setupAliasQuery(var1);
                     if (var10 != null) {
                        var10.setCnameAttempts(var1.getCnameAttempts() + 1);
                     } else {
                        EventLogger.logEvent(1197736374800106759L, 1130458705, 3);
                     }
                  } else {
                     EventLogger.logEvent(1197736374800106759L, 1298233425, 3);
                  }
               } else {
                  EventLogger.logEvent(1197736374800106759L, 1382377041, 0);
                  if (var1.getReferralAttempts() < 21) {
                     var1.setReferralAttempts(var1.getReferralAttempts() + 1);
                     var10 = this.setupReferredQuery(var1);
                     if (var10 != null) {
                        var10.setReferralAttempts(var1.getReferralAttempts() + 1);
                     } else {
                        EventLogger.logEvent(1197736374800106759L, 1382377041, 3);
                     }
                  } else {
                     EventLogger.logEvent(1197736374800106759L, 1298233425, 3);
                  }
               }

               if (var10 != null) {
                  return var10;
               }

               EventLogger.logEvent(1197736374800106759L, 1165128273, 0);
               return this.endQuery(var1, 10, null);
            }
         case 1:
            return this.endQuery(var1, 5, null);
         case 2:
            return this.endQuery(var1, 6, null);
         case 3:
            return this.endQuery(var1, 7, null);
         case 4:
            return this.endQuery(var1, 8, null);
         case 5:
            var1 = this.endQuery(var1, 9, null);
         case -1:
            return var1;
      }
   }

   private DNSRequest endQuery(DNSRequest var1, int var2, Vector var3) {
      while (true) {
         int var4 = var1.getQueryType() == 1 ? 1 : 11;
         switch (var2) {
            case 2:
               byte[] var5 = var1.getCurrentIp();
               String var6 = var1.getCurrentNsName();
               if (var6 != null) {
                  this._cache.removeFromCache(var6, var5);
               }
               break;
            case 6:
            case 7:
               if (this.setupNextAttempt(var1)) {
                  return var1;
               }

               if (var2 == 7 && var1.getAnswer() != null && var1.getAnswer().getAA() == 1024) {
                  this._cache.addNameError(var1);
               }
               break;
            default:
               if (var2 == var4 && (var3 == null || var3.size() == 0)) {
                  var2 = 10;
                  var3 = null;
               }
         }

         var1.setStatus(var2);
         var1.setResult(var3);
         this.dispatchEvent(var1, var2, var3);
         if (var2 != var4 && this.setupForSecondary(var1)) {
            return var1;
         }

         this._packetIDtoDNSData.remove(var1.getPacketId());
         var1.setDone(true);
         if (var1.getPreviousRequest() == null) {
            return var1;
         }

         var1 = var1.getPreviousRequest();
      }
   }

   private void doRawQuery(DNSRequest var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private DNSRequest lookInCache(DNSRequest var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void doSimulatorWorkaround(DNSRequest var1) {
      String var2 = var1.getQueryString();
      Object var3 = null;
      Object[] var4;
      byte var5;
      switch (var1.getQueryType()) {
         case 1:
            var4 = SimulatorServices.dnsLookup(var2, true);
            var5 = 1;
            break;
         case 12:
            var4 = SimulatorServices.dnsLookup(var2, false);
            var5 = 11;
            break;
         default:
            throw new DNSException(-1);
      }

      if (var4 != null) {
         var3 = new Object(var4.length);

         for (int var6 = 0; var6 < var4.length; var6++) {
            ((Vector)var3).addElement(var4[var6]);
         }
      } else {
         var5 = 7;
      }

      var1.setFlag(-1);
      this.endQuery(var1, var5, (Vector)var3);
   }

   private Vector executeQuery(DNSRequest var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean isMatchingPort(int var1) {
      Enumeration var2 = this._packetIDtoDNSData.elements();

      while (var2.hasMoreElements()) {
         if (((DNSRequest)var2.nextElement()).getSrcPort() == var1) {
            return true;
         }
      }

      return false;
   }

   private boolean setupForSecondary(DNSRequest var1) {
      if (!var1.isFlagSet(2)) {
         byte[] var2 = var1.getSecondaryDnsIp();
         if (var2 != null) {
            var1.clearFlag(1);
            var1.setFlag(2);
            var1.setCurrentIpSettings(var2, null);
            return true;
         }
      }

      return false;
   }

   private void failRequest(DNSRequest var1, int var2) {
      if (!var1.isFlagSet(2)) {
         var1.setFlag(2);
      } else {
         var1.setStatus(var2);
         throw new DNSException(var2);
      }
   }

   private static String addAPNToName(String var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private synchronized int getNextPacketId() {
      int var1 = _packetID;
      _packetID = _packetID + 1 & 65535;
      return var1;
   }

   private void dispatchEvent(DNSRequest var1, int var2, Vector var3) {
      if (var1 != null && var1.getListener() != null) {
         var1.getListener().DNSEvent(var1.getPacketId(), var2, var3);
      }
   }

   private void prepRequest(DNSRequest var1, boolean var2) {
      int var3 = this.getNextPacketId();
      var1.setPacketId(var3);
      var1.getQuery().setRD(this.RECURSION_ENABLED);
      var1.setTimestamp(DNSCache.getCurrentTime());
      var1._doSimulatorHack = var2;
      byte[] var4 = var1.getPrimaryDnsIp();
      if (var4 == null) {
         var1.setFlag(2);
         var4 = var1.getSecondaryDnsIp();
      }

      var1.setCurrentIpSettings(var4, null);
      this._packetIDtoDNSData.put(var3, var1);
   }

   private DNSRequest chainQuery(DNSRequest var1, String var2) {
      DNSRequest var3 = new DNSRequest(var2, null, var1.getApnId(), var1.getCurrentIp(), null, var1.getSrcPort());
      var3.setPreviousRequest(var1);
      if (!var1.isFlagSet(2)) {
         var3.setSecondaryDnsIp(var1.getSecondaryDnsIp());
      }

      this.prepRequest(var3, var1._doSimulatorHack);
      return var3;
   }

   private synchronized void incrementQueryCount() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private synchronized void decrementQueryCount() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private UDPPacketHeader makeUDPPacketHeader(byte[] var1, int var2, int var3) {
      Object var4 = new Object();
      ((UDPPacketHeader)var4).setDestinationAddress(var1);
      ((UDPPacketHeader)var4).setSourcePort(var2);
      ((UDPPacketHeader)var4).setDestinationPort(53);
      ((UDPPacketHeader)var4).setAccessPointNumber(var3);
      return (UDPPacketHeader)var4;
   }

   public static DNSResolverIPv4 instance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private DNSResolverIPv4() {
   }

   private DNSRequest setupReferredQuery(DNSRequest var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private DNSRequest setupAliasQuery(DNSRequest var1) {
      Vector var3 = var1.getAnswer().getAnswers();
      Object var4 = null;
      Object var5 = var1.getQueryString();
      int var6 = var3.size();
      String var7 = null;

      for (int var8 = 0; var8 < var6; var8++) {
         DNSMessageIPv4Resource var2 = (DNSMessageIPv4Resource)var3.elementAt(var8);
         if (var2.getType() == 5) {
            var7 = var2.getName();
            if (!StringUtilities.strEqual((String)var5, var7)) {
               break;
            }

            var5 = var2.getData();
            var4 = var5;
         }
      }

      return var4 != null ? this.chainQuery(var1, (String)var4) : null;
   }

   private boolean setupNextAttempt(DNSRequest var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
