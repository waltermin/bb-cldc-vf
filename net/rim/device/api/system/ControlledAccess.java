package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

public final class ControlledAccess implements Persistable {
   private Object _obj;
   private CodeSigningKey _readKey;
   private CodeSigningKey _replaceKey;

   public ControlledAccess(Object var1) {
      this(var1, CodeSigningKey.get(var1));
   }

   public ControlledAccess(Object var1, CodeSigningKey var2) {
      this(var1, var2, var2);
   }

   public ControlledAccess(Object var1, CodeSigningKey var2, CodeSigningKey var3) {
      this._obj = var1;
      this._readKey = var2;
      this._replaceKey = var3;
   }

   final Object getObject() {
      return this._obj;
   }

   final void assertReadPermission(int var1) {
      assertSignature(var1, this._readKey);
   }

   final void assertReplacePermission(int var1) {
      assertSignature(var1, this._replaceKey);
   }

   private static final void assertSignature(int var0, CodeSigningKey var1) {
      if (var1 != null && !CodeModuleManager.verifySignature(var0, var1.getSignerIdAsInt(), var1.getPublicKeyInternal())) {
         throw new Object(var1);
      }
   }

   public final boolean checkKeys(CodeSigningKey var1, CodeSigningKey var2) {
      return (var1 == null || var1.equals(this._readKey)) && (var2 == null || var2.equals(this._replaceKey));
   }

   public final void assertKeys(CodeSigningKey var1, CodeSigningKey var2) {
      if (!this.checkKeys(var1, var2)) {
         throw new Object();
      }
   }

   public static final boolean verifyCodeModuleSignature(int var0, int var1) {
      return verifyCodeModuleSignature(var0, CodeSigningKey.getBuiltInKey(var1));
   }

   public static final boolean verifyCodeModuleSignature(int var0, CodeSigningKey var1) {
      return CodeModuleManager.verifySignature(var0, var1.getSignerIdAsInt(), var1.getPublicKeyInternal());
   }

   public static final void assertRRISignature(int var0) {
      if (!verifyCodeModuleSignature(var0, 51)) {
         throw new Object();
      }
   }

   public static final void assertRCISignature(int var0) {
      if (!verifyCodeModuleSignature(var0, 4801362)) {
         throw new Object();
      }
   }

   public static final boolean verifySignatures(boolean var0, int var1) {
      if (var0) {
         Process var2 = Process.currentProcess();
         int var3 = var2.getModuleHandle();
         if (!verifyCodeModuleSignature(var3, var1)) {
            return false;
         }
      }

      int[] var4 = TraceBack.getCallingModules();

      for (int var5 = var4.length - 1; var5 >= 0; var5--) {
         if (!verifyCodeModuleSignature(var4[var5], var1)) {
            return false;
         }
      }

      return true;
   }

   public static final boolean verifyRRISignature(int var0) {
      return verifyCodeModuleSignature(var0, 51);
   }

   public static final boolean verifyRRISignatures(boolean var0) {
      return verifySignatures(var0, 51);
   }

   public static final void assertRRISignatures(boolean var0) {
      if (!verifyRRISignatures(var0)) {
         throw new Object();
      }
   }

   public static final boolean verifyRCISignatures(boolean var0) {
      return verifySignatures(var0, 4801362);
   }

   public static final void assertRCISignatures(boolean var0) {
      if (!verifyRCISignatures(var0)) {
         throw new Object();
      }
   }
}
