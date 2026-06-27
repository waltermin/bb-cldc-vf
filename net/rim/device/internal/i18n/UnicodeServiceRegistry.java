package net.rim.device.internal.i18n;

public final class UnicodeServiceRegistry {
   private int _flags;
   private static final long REGISTRY_KEY;
   public static final int UNICODE_ENCODED;
   private static UnicodeServiceRegistry _instance;

   private UnicodeServiceRegistry() {
   }

   public static final UnicodeServiceRegistry getInstance() {
      return _instance;
   }

   public final int setFlags(int var1) {
      int var2 = this._flags;
      this._flags = var1;
      return var2;
   }

   public final int getFlags() {
      return this._flags;
   }
}
