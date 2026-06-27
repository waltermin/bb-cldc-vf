package net.rim.device.cldc.io.dns;

import java.util.Vector;

public class DNSRequest {
   private DNSListener _listener;
   private DNSMessageIPv4 _message;
   private DNSMessageIPv4 _answer;
   private int _timestamp;
   private int _status;
   private int _queryType;
   private int _apnId;
   private byte[] _curDnsIP;
   private String _curDnsName;
   private int _dnsPort;
   private Vector _result;
   private String _name;
   private int _flags;
   private boolean _done;
   private DNSRequest _prevRequest;
   private int _timeout;
   private byte[] _primaryDnsIP;
   private byte[] _secondaryDnsIP;
   private int _cnames;
   private int _referrals;
   boolean _doSimulatorHack;
   public static final int QUERY_FOR_IP_ADDRESS;
   public static final int QUERY_FOR_HOSTNAME;
   public static final int DEFAULT_SRC_PORT;
   public static final int FLAG_APPEND_APN_TO_NAME;
   public static final int FLAG_USE_SECONDARY_DNS;
   public static final int DEFAULT_TIMEOUT;

   public DNSRequest(String var1, DNSListener var2, int var3) {
      this(var1, var2, var3, getDefaultServerAddress(var3, 1), getDefaultServerAddress(var3, 2), 19780);
   }

   public DNSRequest(String var1, DNSListener var2, int var3, byte[] var4, byte[] var5, int var6) {
      this._queryType = 1;
      this._timeout = 10000;
      this.init(var1, var2, var3, var4, var5, var6);
   }

   public DNSRequest(byte[] var1, DNSListener var2, int var3) {
      this(var1, var2, var3, getDefaultServerAddress(var3, 1), getDefaultServerAddress(var3, 2), 19780);
   }

   public DNSRequest(byte[] var1, DNSListener var2, int var3, byte[] var4, byte[] var5, int var6) {
      this._queryType = 12;
      this._timeout = 10000;
      this.init(makeInverseQueryHostname(var1), var2, var3, var4, var5, var6);
   }

   public DNSListener getListener() {
      return this._listener;
   }

   DNSMessageIPv4 getQuery() {
      return this._message;
   }

   public DNSMessageIPv4 getAnswer() {
      return this._answer;
   }

   void setAnswer(DNSMessageIPv4 var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getTimestamp() {
      return this._timestamp;
   }

   void setTimestamp(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getStatus() {
      return this._status;
   }

   void setStatus(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getQueryType() {
      return this._queryType;
   }

   public int getApnId() {
      return this._apnId;
   }

   public byte[] getCurrentIp() {
      return this._curDnsIP;
   }

   public String getCurrentNsName() {
      return this._curDnsName;
   }

   void setCurrentIpSettings(byte[] var1, String var2) {
      this._curDnsIP = var1;
      this._curDnsName = var2;
   }

   public int getSrcPort() {
      return this._dnsPort;
   }

   public Vector getResult() {
      return this._result;
   }

   void setResult(Vector var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getQueryString() {
      return this._name;
   }

   public int getFlags() {
      return this._flags;
   }

   public boolean isFlagSet(int var1) {
      return (this._flags & var1) != 0;
   }

   void setFlag(int var1) {
      this._flags |= var1;
   }

   void clearFlag(int var1) {
      this._flags &= ~var1;
   }

   public boolean isDone() {
      return this._done;
   }

   void setDone(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public DNSRequest getPreviousRequest() {
      return this._prevRequest;
   }

   void setPreviousRequest(DNSRequest var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getPacketId() {
      return this._message.getID();
   }

   void setPacketId(int var1) {
      this._message.setID(var1);
   }

   public byte[] getPrimaryDnsIp() {
      return this._primaryDnsIP;
   }

   public void setPrimaryDnsIp(byte[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] getSecondaryDnsIp() {
      return this._secondaryDnsIP;
   }

   public void setSecondaryDnsIp(byte[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getTimeout() {
      return this._timeout;
   }

   public void setTimeout(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getCnameAttempts() {
      return this._cnames;
   }

   void setCnameAttempts(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getReferralAttempts() {
      return this._referrals;
   }

   void setReferralAttempts(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void init(String var1, DNSListener var2, int var3, byte[] var4, byte[] var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static String makeInverseQueryHostname(byte[] var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] getDefaultServerAddress(int var0, int var1) {
      throw new RuntimeException("cod2jar: array init");
   }
}
