package net.rim.tid.itie;

public class LinguisticData {
   private byte[][][] _data;
   private int _id;
   private int _type;
   private int _version;
   private String _name;
   private String _diagnosticMessage;
   private String _codFile;
   LinguisticData _next;
   public static final byte LINGDATA_GENERIC_FORMAT;
   public static final byte LINGDATA_NGRAMM_FORMAT;
   public static final byte LINGDATA_FAST_FORMAT;
   public static final byte LINGDATA_SHORTCUTS_FORMAT;
   public static final byte LINGDATA_GENERIC_TYPE;
   public static final byte LINGDATA_GROUP_SPECIFIC_TYPE;
   public static final byte LINGDATA_AUXILIARY_TYPE;
   public static final byte LINGDATA_SLANG_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY1_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY2_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY3_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY4_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY5_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY6_TYPE;
   public static final byte LINGDATA_SUPPLEMENTARY7_TYPE;
   public static final int LINGDATA_LOAD_OK;
   public static final int LINGDATA_LOAD_ERROR;
   public static final int LINGDATA_LOAD_IGNORE;
   public static final int LINGDATA_UNLOAD_OK;
   public static final int LINGDATA_UNLOAD_ERROR;
   public static final int LINGDATA_UNLOAD_NOT_FOUND;

   public LinguisticData(String var1, int var2, int var3, byte[][][] var4) {
      this(var1, var2, var3, var4, null);
   }

   public LinguisticData(String var1, int var2, int var3, byte[][][] var4, String var5) {
      this(var1, var2, var3, var4, null, null);
   }

   public LinguisticData(String var1, int var2, int var3, byte[][][] var4, String var5, String var6) {
      if (var1 != null && var4 != null) {
         this._name = var1;
         this._data = var4;
         this._type = var2;
         this._version = var3;
         this._diagnosticMessage = var5;
         this._codFile = var6;
      } else {
         throw new Object();
      }
   }

   public byte[][][] getData() {
      return this._data;
   }

   public int getType() {
      return this._type;
   }

   public int getVersion() {
      return this._version;
   }

   public String getName() {
      return this._name;
   }

   void setID(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getID() {
      return this._id;
   }

   void addToChain(LinguisticData var1) {
      if (this._next != null) {
         this._next.addToChain(var1);
      } else {
         this._next = var1;
      }
   }

   String getCodFileName() {
      return this._codFile;
   }

   public String getDiagnosticMessage() {
      return this._diagnosticMessage;
   }
}
