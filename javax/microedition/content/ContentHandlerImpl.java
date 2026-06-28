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

   void setVersion(String version) {
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

   void setDynamic(boolean dynamic) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   String getClassname() {
      return this._classname;
   }

   ActionNameMap[] getActionNameMaps() {
      return this._actionnames;
   }

   void setAppName(String appName) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setModuleHandle(int handle) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getModuleHandle() {
      return this._moduleHandle;
   }

   void setClassname(String classname) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setAuthority(String authority) {
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
   public ActionNameMap getActionNameMap(int index) {
      if (index >= 0 && index < this.getActionNameMapCount()) {
         return this._actionnames[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public ActionNameMap getActionNameMap(String locale) {
      for (int i = 0; i < this._actionnames.length; i++) {
         if (this._actionnames[i].getLocale().equals(locale)) {
            return this._actionnames[i];
         }
      }

      int hashIndex = locale.lastIndexOf(45);
      return hashIndex == -1 ? null : this.getActionNameMap(locale.substring(0, hashIndex));
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
   public String getSuffix(int index) {
      if (index >= 0 && index < this.getSuffixCount()) {
         return this._suffixes[index];
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
   public String getType(int index) {
      if (index >= 0 && index < this.getTypeCount()) {
         return this._types[index];
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
   public String getAction(int index) {
      if (index >= 0 && index < this.getActionCount()) {
         return this._actions[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public String getVersion() {
      return this._version;
   }

   @Override
   public boolean hasAction(String action) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean hasSuffix(String suffix) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean hasType(String type) {
      throw new RuntimeException("cod2jar: ldc");
   }

   ContentHandlerImpl(ContentHandlerImpl handler) {
      this._types = handler.getTypes();
      this._suffixes = handler.getSuffixes();
      this._actions = handler.getActions();
      this._actionnames = handler.getActionNameMaps();
      this._ID = handler.getID();
      this._appName = handler.getAppName();
      this._version = handler.getVersion();
      this._authority = handler.getAuthority();
      this._classname = handler.getClassname();
      this._moduleHandle = handler.getModuleHandle();
      this._dynamic = handler.isDynamic();
   }

   ContentHandlerImpl() {
   }

   @Override
   public String toString() {
      return this._ID;
   }
}
