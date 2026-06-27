package net.rim.device.cldc.io.utility;

public class URL {
   private String _urlWithoutRIMParams;
   private String _scheme;
   private String _host;
   private int _port;
   private String _path;
   private String _query;
   private String _fragment;
   private URLParameters _rimParameters;
   private static final String[] RIM_PARAMETERS;
   private static final String STRING_NetPart;

   public URL(String var1) {
      this.initialize(var1);
   }

   public URL(String var1, String var2) {
   }

   private void initialize(String var1) {
      if (var1 == null) {
         throw new Object();
      }

      this.parseUrlString(var1);
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public String getScheme() {
      return this._scheme;
   }

   public void setScheme(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getHost() {
      return this._host;
   }

   public int getPort() {
      return this._port;
   }

   public String getPath() {
      return this._path;
   }

   public String getQuery() {
      return this._query;
   }

   public String getFragment() {
      return this._fragment;
   }

   public URLParameters getRIMParameters() {
      return this._rimParameters;
   }

   public String toStringWithoutRIMParams() {
      return this._urlWithoutRIMParams;
   }

   private void parseUrlString(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean validateScheme() {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void parseSchemeSpecificPart(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setRIMParameters(URLParameters var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
