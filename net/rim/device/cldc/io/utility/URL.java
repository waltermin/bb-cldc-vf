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

   public URL(String url) {
      this.initialize(url);
   }

   public URL(String scheme, String restOfUrl) {
   }

   private void initialize(String url) {
      if (url == null) {
         throw new Object();
      }

      this.parseUrlString(url);
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public String getScheme() {
      return this._scheme;
   }

   public void setScheme(String scheme) {
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

   private void parseUrlString(String url) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean validateScheme() {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void parseSchemeSpecificPart(String url) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setRIMParameters(URLParameters params) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
