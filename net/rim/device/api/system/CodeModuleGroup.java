package net.rim.device.api.system;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.util.CRC32;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.CodeModuleGroupProperties;
import net.rim.device.internal.system.CodeModuleGroupPropertiesCollection;
import net.rim.vm.TraceBack;

public final class CodeModuleGroup {
   private int _flags;
   private String _groupName;
   private Vector _moduleNames;
   private Vector _dependencies;
   private String _friendlyName;
   private String _description;
   private String _version;
   private String _vendor;
   private String _copyright;
   private CodeModuleGroupProperties _properties;
   private int _currentLocaleCode;
   private Hashtable _friendlyNameTable;
   private Hashtable _descriptionTable;
   private Hashtable _vendorTable;
   private Hashtable _versionTable;
   private Hashtable _copyrightTable;
   private int _handle;
   private String _midletSigner;
   private byte[] _midletSignerHash;
   public static final int FLAG_REQUIRED;
   public static final int FLAG_HIDDEN;
   public static final int FLAG_DEPENDENCY;
   public static final int FLAG_LIBRARY;

   CodeModuleGroup(int var1) {
      this._handle = var1;
      this._moduleNames = (Vector)(new Object());
      this._dependencies = (Vector)(new Object());
      this._currentLocaleCode = Locale.getDefault().getCode();
   }

   public CodeModuleGroup(String var1) {
   }

   private final void assertPermission() {
      ApplicationControl.assertCMMApiAllowed(true);
   }

   public final String getName() {
      return this._groupName;
   }

   public final Enumeration getModules() {
      return this._moduleNames.elements();
   }

   public final int getHandle() {
      return this._handle;
   }

   public final boolean containsModule(String var1) {
      return this._moduleNames.contains(var1);
   }

   public final void addModule(String var1) {
      this._moduleNames.addElement(var1);
   }

   public final Enumeration getDependencies() {
      return this._dependencies.elements();
   }

   public final void addDependency(String var1) {
      this._dependencies.addElement(var1);
   }

   public final boolean containsDependency(String var1) {
      return this._dependencies.contains(var1);
   }

   public final int getFlags() {
      return this._flags;
   }

   public final void setFlag(int var1, boolean var2) {
      if (var2) {
         this._flags |= var1;
      } else {
         this._flags &= ~var1;
      }
   }

   public final String getFriendlyName() {
      return this._friendlyName == null ? this._groupName : this._friendlyName;
   }

   public final void setFriendlyName(String var1) {
      this._friendlyName = var1;
      if (this._friendlyNameTable != null) {
         this._friendlyNameTable.put(Locale.get(this._currentLocaleCode), var1);
      }
   }

   public final String getFriendlyName(Locale var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._friendlyNameTable == null) {
         this.localize();
      }

      Object var2 = this._friendlyNameTable.get(var1);
      if (var2 == null) {
         var2 = this.getFriendlyName();
      }

      return (String)var2;
   }

   public final void setFriendlyName(Locale var1, String var2) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._friendlyNameTable == null) {
         this.localize();
      }

      this._friendlyNameTable.put(var1, var2);
      int var3 = var1.getCode();
      if (var3 == this._currentLocaleCode || this._friendlyName == null && this.fuzzyLocaleMatch(var3)) {
         this._friendlyName = var2;
      }
   }

   public final Enumeration getFriendlyNameLocales() {
      if (this._friendlyNameTable == null) {
         this.localize();
      }

      return this._friendlyNameTable.keys();
   }

   public final String getDescription() {
      return this._description;
   }

   public final void setDescription(String var1) {
      this._description = var1;
      if (this._descriptionTable != null) {
         this._descriptionTable.put(Locale.get(this._currentLocaleCode), var1);
      }
   }

   public final String getDescription(Locale var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._descriptionTable == null) {
         this.localize();
      }

      Object var2 = this._descriptionTable.get(var1);
      if (var2 == null) {
         var2 = this._description;
      }

      return (String)var2;
   }

   public final void setDescription(Locale var1, String var2) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._descriptionTable == null) {
         this.localize();
      }

      this._descriptionTable.put(var1, var2);
      int var3 = var1.getCode();
      if (var3 == this._currentLocaleCode || this._description == null && this.fuzzyLocaleMatch(var3)) {
         this._description = var2;
      }
   }

   public final Enumeration getDescriptionLocales() {
      if (this._descriptionTable == null) {
         this.localize();
      }

      return this._descriptionTable.keys();
   }

   public final String getVersion() {
      return this._version;
   }

   public final void setVersion(String var1) {
      this._version = var1;
      if (this._versionTable != null) {
         this._versionTable.put(Locale.get(this._currentLocaleCode), var1);
      }
   }

   public final String getVersion(Locale var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._versionTable == null) {
         this.localize();
      }

      Object var2 = this._versionTable.get(var1);
      if (var2 == null) {
         var2 = this._version;
      }

      return (String)var2;
   }

   public final void setVersion(Locale var1, String var2) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._versionTable == null) {
         this.localize();
      }

      this._versionTable.put(var1, var2);
      int var3 = var1.getCode();
      if (var3 == this._currentLocaleCode || this._version == null && this.fuzzyLocaleMatch(var3)) {
         this._version = var2;
      }
   }

   public final Enumeration getVersionLocales() {
      if (this._versionTable == null) {
         this.localize();
      }

      return this._versionTable.keys();
   }

   public final String getVendor() {
      return this._vendor;
   }

   public final void setVendor(String var1) {
      this._vendor = var1;
      if (this._vendorTable != null) {
         this._vendorTable.put(Locale.get(this._currentLocaleCode), var1);
      }
   }

   public final String getVendor(Locale var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._vendorTable == null) {
         this.localize();
      }

      Object var2 = this._vendorTable.get(var1);
      if (var2 == null) {
         var2 = this._vendor;
      }

      return (String)var2;
   }

   public final void setVendor(Locale var1, String var2) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._vendorTable == null) {
         this.localize();
      }

      this._vendorTable.put(var1, var2);
      int var3 = var1.getCode();
      if (var3 == this._currentLocaleCode || this._vendor == null && this.fuzzyLocaleMatch(var3)) {
         this._vendor = var2;
      }
   }

   public final Enumeration getVendorLocales() {
      if (this._vendorTable == null) {
         this.localize();
      }

      return this._vendorTable.keys();
   }

   public final String getCopyright() {
      return this._copyright;
   }

   public final void setCopyright(String var1) {
      this._copyright = var1;
      if (this._copyrightTable != null) {
         this._copyrightTable.put(Locale.get(this._currentLocaleCode), var1);
      }
   }

   public final String getCopyright(Locale var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._copyrightTable == null) {
         this.localize();
      }

      Object var2 = this._copyrightTable.get(var1);
      if (var2 == null) {
         var2 = this._copyright;
      }

      return (String)var2;
   }

   public final void setCopyright(Locale var1, String var2) {
      if (var1 == null) {
         throw new Object();
      }

      if (this._copyrightTable == null) {
         this.localize();
      }

      this._copyrightTable.put(var1, var2);
      int var3 = var1.getCode();
      if (var3 == this._currentLocaleCode || this._copyright == null && this.fuzzyLocaleMatch(var3)) {
         this._copyright = var2;
      }
   }

   public final Enumeration getCopyrightLocales() {
      if (this._copyrightTable == null) {
         this.localize();
      }

      return this._copyrightTable.keys();
   }

   public final void setMIDletSigner(String var1) {
      ControlledAccess.verifyRRISignature(TraceBack.getCallingModule(0));
      this._midletSigner = var1;
   }

   public final String getMIDletSigner() {
      ControlledAccess.verifyRRISignature(TraceBack.getCallingModule(0));
      return this._midletSigner;
   }

   public final void setMIDletSignerHash(byte[] var1) {
      ControlledAccess.verifyRRISignature(TraceBack.getCallingModule(0));
      this._midletSignerHash = var1;
   }

   public final byte[] getMIDletSignerHash() {
      ControlledAccess.verifyRRISignature(TraceBack.getCallingModule(0));
      return this._midletSignerHash;
   }

   public final Enumeration getPropertyNames() {
      return (Enumeration)(this._properties == null ? new Object() : this._properties.keys());
   }

   public final String getProperty(String var1) {
      return (String)(this._properties == null ? null : this._properties.get(var1));
   }

   public final void setProperty(String var1, String var2) {
      if (this._properties != null) {
         this._properties.put(var1, var2);
         CodeModuleGroupPropertiesCollection.getInstance().updateSyncObject(this._properties, this._properties);
      }
   }

   public final void clearProperties() {
      if (this._properties != null && ControlledAccess.verifyRRISignature(TraceBack.getCallingModule(0))) {
         this._properties.clear();
         CodeModuleGroupPropertiesCollection.getInstance().updateSyncObject(this._properties, this._properties);
      }
   }

   private final boolean fuzzyLocaleMatch(int var1) {
      return var1 == 0 || (var1 & -65536) == (this._currentLocaleCode & -65536);
   }

   private final boolean localize() {
      if (this._handle == 0) {
         this._friendlyNameTable = (Hashtable)(new Object());
         this._descriptionTable = (Hashtable)(new Object());
         this._vendorTable = (Hashtable)(new Object());
         this._versionTable = (Hashtable)(new Object());
         this._copyrightTable = (Hashtable)(new Object());
         return true;
      } else {
         return this.load(true);
      }
   }

   final boolean load() {
      return this.load(false);
   }

   final boolean load(boolean var1) {
      byte[] var2 = CodeModuleGroupManager.getGroupData(this._handle);
      return this.loadFromData(var2, var1);
   }

   final boolean loadFromData(byte[] var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final CodeModuleGroup loadUnpersisted(byte[] var0) {
      CodeModuleGroup var1 = new CodeModuleGroup(0);
      boolean var2 = var1.loadFromData(var0, false);
      return var2 ? var1 : null;
   }

   private final String readString(byte[] var1, DataBuffer var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final String readLocalizedString(byte[] var1, DataBuffer var2, String var3, Hashtable var4) {
      int var5 = var2.readInt();
      String var6 = this.readString(var1, var2);
      if (var4 != null) {
         var4.put(Locale.get(var5), var6);
      }

      return var5 != this._currentLocaleCode && (var3 != null || !this.fuzzyLocaleMatch(var5)) ? var3 : var6;
   }

   private final void readProperty(byte[] var1, DataBuffer var2) {
      String var3 = this.readString(var1, var2);
      String var4 = this.readString(var1, var2);
      if (this._properties != null) {
         Object var5 = this._properties.get(var3);
         if (var5 != null && !((String)var5).equals(var4) || var5 == null && var4 != null) {
            this._properties.put(var3, var4);
            CodeModuleGroupPropertiesCollection.getInstance().updateSyncObject(this._properties, this._properties);
         }
      }
   }

   public final synchronized boolean store() {
      this.assertPermission();
      Object var1 = new Object(false);
      ((DataBuffer)var1).writeInt(2060613291);
      ((DataBuffer)var1).writeInt(0);
      ((DataBuffer)var1).writeInt(this._flags);
      this.writeString((DataBuffer)var1, this._groupName);
      this.writeVector((DataBuffer)var1, 0, this._moduleNames);
      this.writeVector((DataBuffer)var1, 1, this._dependencies);
      this.writeLocalizedHashtable((DataBuffer)var1, 3, this._friendlyName, this._friendlyNameTable);
      this.writeLocalizedHashtable((DataBuffer)var1, 4, this._description, this._descriptionTable);
      this.writeLocalizedHashtable((DataBuffer)var1, 5, this._version, this._versionTable);
      this.writeLocalizedHashtable((DataBuffer)var1, 6, this._vendor, this._vendorTable);
      this.writeLocalizedHashtable((DataBuffer)var1, 7, this._copyright, this._copyrightTable);
      int var2 = ((DataBuffer)var1).getLength() & 3;
      if (var2 != 0) {
         for (int var3 = 3 - var2; var3 >= 0; var3--) {
            ((DataBuffer)var1).write(255);
         }
      }

      byte[] var5 = ((DataBuffer)var1).toArray();
      int var4 = CRC32.update(-1, var5, 12, var5.length - 12);
      var5[4] = (byte)var4;
      var5[5] = (byte)(var4 >>> 8);
      var5[6] = (byte)(var4 >>> 16);
      var5[7] = (byte)(var4 >>> 24);
      this._handle = CodeModuleGroupManager.createGroup(var5);
      return this._handle > 0;
   }

   public final synchronized void delete() {
      this.assertPermission();
      if (this._handle != 0) {
         deleteGroup(this._handle);
         this._handle = 0;
      }
   }

   private final void writeVector(DataBuffer var1, int var2, Vector var3) {
      int var4 = var3.size();

      for (int var5 = 0; var5 < var4; var5++) {
         Object var6 = var3.elementAt(var5);
         var1.writeShort(var2);
         this.writeString(var1, (String)var6);
      }
   }

   private final void writeProperty(DataBuffer var1, String var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void writeString(DataBuffer var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void writeLocalizedString(DataBuffer var1, int var2, int var3, String var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void writeLocalizedHashtable(DataBuffer var1, int var2, String var3, Hashtable var4) {
      if (var4 == null) {
         this.writeLocalizedString(var1, var2, 0, var3);
      } else {
         var3 = (String)var4.get(Locale.get(0));
         this.writeLocalizedString(var1, var2, 0, var3);
         Enumeration var5 = var4.keys();

         while (var5.hasMoreElements()) {
            Object var6 = var5.nextElement();
            int var7 = ((Locale)var6).getCode();
            if (var7 != 0) {
               var3 = (String)var4.get(var6);
               this.writeLocalizedString(var1, var2, var7, var3);
            }
         }
      }
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final CodeModuleGroup load(String var0) {
      return CodeModuleGroupManager.load(var0);
   }

   public static final CodeModuleGroup[] loadAll() {
      return CodeModuleGroupManager.loadAll();
   }

   public static final byte[] getGroupData(int var0) {
      return CodeModuleGroupManager.getGroupData(var0);
   }

   public static final int createGroup(byte[] var0) {
      return CodeModuleGroupManager.createGroup(var0);
   }

   private static final native void deleteGroup(int var0);
}
