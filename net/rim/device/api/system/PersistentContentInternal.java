package net.rim.device.api.system;

public final class PersistentContentInternal {
   private PersistentContentInternal() {
   }

   public static final void lock() {
      PersistentContent.getInstance().lock();
   }

   public static final void unlock(String var0) {
      PersistentContent.getInstance().unlock(var0);
   }

   public static final void setContentProtection(String var0, boolean var1, int var2) {
      PersistentContent.getInstance().setContentProtection(var0, var1, var2);
   }

   public static final void changePassword(String var0, String var1) {
      PersistentContent.getInstance().changePassword(var0, var1);
   }

   public static final boolean doesEncryptionKeyExist() {
      return PersistentContent.getInstance().doesEncryptionKeyExist();
   }

   public static final void setContentCompression(boolean var0) {
      PersistentContent.getInstance().setContentCompression(var0);
   }

   public static final void registerPersistentContentIndicator(PersistentContentListener var0) {
      PersistentContent.getInstance().registerPersistentContentIndicator(var0);
   }

   public static final byte[] decodeByteArray(Object var0, boolean var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final byte[] getD() {
      return PersistentContent.getInstance().getD();
   }

   public static final byte[] getBChecksum() {
      return PersistentContent.getInstance().getBChecksum();
   }

   public static final void clearK() {
      PersistentContent.getInstance().clearK();
   }

   public static final boolean setK(byte[] var0, byte[] var1) {
      return PersistentContent.getInstance().setK(var0, var1);
   }
}
