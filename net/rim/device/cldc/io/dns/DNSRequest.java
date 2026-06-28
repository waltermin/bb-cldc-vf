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

   public DNSRequest(String hostname, DNSListener listener, int apnId) {
      this(hostname, listener, apnId, getDefaultServerAddress(apnId, 1), getDefaultServerAddress(apnId, 2), 19780);
   }

   public DNSRequest(String hostname, DNSListener listener, int apnId, byte[] primaryDnsAddr, byte[] secondaryDnsAddr, int srcPort) {
      this._queryType = 1;
      this._timeout = 10000;
      this.init(hostname, listener, apnId, primaryDnsAddr, secondaryDnsAddr, srcPort);
   }

   public DNSRequest(byte[] ipAddr, DNSListener listener, int apnId) {
      this(ipAddr, listener, apnId, getDefaultServerAddress(apnId, 1), getDefaultServerAddress(apnId, 2), 19780);
   }

   public DNSRequest(byte[] ipAddr, DNSListener listener, int apnId, byte[] primaryDnsAddr, byte[] secondaryDnsAddr, int srcPort) {
      this._queryType = 12;
      this._timeout = 10000;
      this.init(makeInverseQueryHostname(ipAddr), listener, apnId, primaryDnsAddr, secondaryDnsAddr, srcPort);
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

   void setAnswer(DNSMessageIPv4 answer) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getTimestamp() {
      return this._timestamp;
   }

   void setTimestamp(int timestamp) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getStatus() {
      return this._status;
   }

   void setStatus(int status) {
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

   void setCurrentIpSettings(byte[] nsIp, String nsName) {
      this._curDnsIP = nsIp;
      this._curDnsName = nsName;
   }

   public int getSrcPort() {
      return this._dnsPort;
   }

   public Vector getResult() {
      return this._result;
   }

   void setResult(Vector vec) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getQueryString() {
      return this._name;
   }

   public int getFlags() {
      return this._flags;
   }

   public boolean isFlagSet(int flag) {
      return (this._flags & flag) != 0;
   }

   void setFlag(int flag) {
      this._flags |= flag;
   }

   void clearFlag(int flag) {
      this._flags &= ~flag;
   }

   public boolean isDone() {
      return this._done;
   }

   void setDone(boolean done) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public DNSRequest getPreviousRequest() {
      return this._prevRequest;
   }

   void setPreviousRequest(DNSRequest req) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getPacketId() {
      return this._message.getID();
   }

   void setPacketId(int id) {
      this._message.setID(id);
   }

   public byte[] getPrimaryDnsIp() {
      return this._primaryDnsIP;
   }

   public void setPrimaryDnsIp(byte[] ip) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] getSecondaryDnsIp() {
      return this._secondaryDnsIP;
   }

   public void setSecondaryDnsIp(byte[] ip) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getTimeout() {
      return this._timeout;
   }

   public void setTimeout(int to) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getCnameAttempts() {
      return this._cnames;
   }

   void setCnameAttempts(int newAttempts) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getReferralAttempts() {
      return this._referrals;
   }

   void setReferralAttempts(int newAttempts) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void init(String queryStr, DNSListener listener, int apnId, byte[] primaryDnsAddr, byte[] secondaryDnsAddr, int port) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static String makeInverseQueryHostname(byte[] ipAddr) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] getDefaultServerAddress(int apnId, int type) {
      throw new RuntimeException("cod2jar: array init");
   }
}
