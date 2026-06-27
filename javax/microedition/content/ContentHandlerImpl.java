package javax.microedition.content;

import net.rim.device.api.i18n.Locale;

class ContentHandlerImpl implements ContentHandler {
   protected String[] _types;
   protected String[] _suffixes;
   protected String[] _actions;
   protected ActionNameMap[] _actionnames;
   protected String _ID;
   protected String _appName;
   protected String _version;
   protected String _authority;
   protected String _classname;
   protected int _moduleHandle;
   protected boolean _dynamic;

   void setVersion(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   String[] getTypes() {
      return this._types;
   }

   String[] getActions() {
      return this._actions;
   }

   String[] getSuffixes() {
      return this._suffixes;
   }

   boolean isDynamic() {
      return this._dynamic;
   }

   void setDynamic(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   String getClassname() {
      return this._classname;
   }

   ActionNameMap[] getActionNameMaps() {
      return this._actionnames;
   }

   void setAppName(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setModuleHandle(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getModuleHandle() {
      return this._moduleHandle;
   }

   void setClassname(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setAuthority(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public String getAppName() {
      return this._appName;
   }

   @Override
   public int getActionNameMapCount() {
      return this._actionnames.length;
   }

   @Override
   public ActionNameMap getActionNameMap(int var1) {
      if (var1 >= 0 && var1 < this.getActionNameMapCount()) {
         return this._actionnames[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public ActionNameMap getActionNameMap(String var1) {
      for (int var2 = 0; var2 < this._actionnames.length; var2++) {
         if (this._actionnames[var2].getLocale().equals(var1)) {
            return this._actionnames[var2];
         }
      }

      int var3 = var1.lastIndexOf(45);
      return var3 == -1 ? null : this.getActionNameMap(var1.substring(0, var3));
   }

   @Override
   public String getAuthority() {
      return this._authority;
   }

   @Override
   public String getID() {
      return this._ID;
   }

   @Override
   public String getSuffix(int var1) {
      if (var1 >= 0 && var1 < this.getSuffixCount()) {
         return this._suffixes[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int getSuffixCount() {
      return this._suffixes.length;
   }

   @Override
   public ActionNameMap getActionNameMap() {
      return this.getActionNameMap(Locale.getCLDCLocaleString());
   }

   @Override
   public String getType(int var1) {
      if (var1 >= 0 && var1 < this.getTypeCount()) {
         return this._types[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int getTypeCount() {
      return this._types.length;
   }

   @Override
   public int getActionCount() {
      return this._actions.length;
   }

   @Override
   public String getAction(int var1) {
      if (var1 >= 0 && var1 < this.getActionCount()) {
         return this._actions[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public String getVersion() {
      return this._version;
   }

   @Override
   public boolean hasAction(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean hasSuffix(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean hasType(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   ContentHandlerImpl(ContentHandlerImpl var1) {
      this._types = var1.getTypes();
      this._suffixes = var1.getSuffixes();
      this._actions = var1.getActions();
      this._actionnames = var1.getActionNameMaps();
      this._ID = var1.getID();
      this._appName = var1.getAppName();
      this._version = var1.getVersion();
      this._authority = var1.getAuthority();
      this._classname = var1.getClassname();
      this._moduleHandle = var1.getModuleHandle();
      this._dynamic = var1.isDynamic();
   }

   ContentHandlerImpl() {
   }

   @Override
   public String toString() {
      return this._ID;
   }
}
