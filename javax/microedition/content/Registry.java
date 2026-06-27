package javax.microedition.content;

public class Registry {
   Registry() {
   }

   public static Registry getRegistry(String var0) {
      return RegistryImpl.getRegistry(var0);
   }

   public static ContentHandlerServer getServer(String var0) {
      return RegistryImpl.getServer(var0);
   }

   public ContentHandlerServer register(String var1, String[] var2, String[] var3, String[] var4, ActionNameMap[] var5, String var6, String[] var7) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean unregister(String var1) {
      return false;
   }

   public String[] getTypes() {
      return null;
   }

   public String[] getIDs() {
      return null;
   }

   public String[] getActions() {
      return null;
   }

   public String[] getSuffixes() {
      return null;
   }

   public ContentHandler[] forType(String var1) {
      return null;
   }

   public ContentHandler[] forAction(String var1) {
      return null;
   }

   public ContentHandler[] forSuffix(String var1) {
      return null;
   }

   public ContentHandler forID(String var1, boolean var2) {
      return null;
   }

   public ContentHandler[] findHandler(Invocation var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean invoke(Invocation var1, Invocation var2) {
      return false;
   }

   public boolean invoke(Invocation var1) {
      return false;
   }

   public boolean reinvoke(Invocation var1) {
      return false;
   }

   public Invocation getResponse(boolean var1) {
      return null;
   }

   public void cancelGetResponse() {
   }

   public void setListener(ResponseListener var1) {
   }

   public String getID() {
      return null;
   }
}
