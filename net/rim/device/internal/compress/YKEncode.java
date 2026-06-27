package net.rim.device.internal.compress;

public final class YKEncode {
   private YKCleaner _cleaningHelper = new YKCleaner(this);
   private boolean yk_mixedMode;
   private boolean yk_passthroughMode;
   private byte[] yk_context;

   public YKEncode(boolean var1) {
      this.yk_mixedMode = var1;
      this.yk_passthroughMode = false;
   }

   public static final native int yk_get_codec_version();

   public final byte[] yk_encode(byte[] var1, int var2, int var3) {
      this._cleaningHelper.register();
      return this.yk_encode0(var1, var2, var3);
   }

   private final native byte[] yk_encode0(byte[] var1, int var2, int var3);

   public final native byte[] flush();

   public final void loadContextMap(byte[] var1, boolean var2) {
      this._cleaningHelper.register();
      this.loadContextMap0(var1, var2);
   }

   public final native void loadContextMap0(byte[] var1, boolean var2);

   public final void yk_load_side_data(byte[] var1, int var2, int var3) {
      this._cleaningHelper.register();
      this.yk_load_side_data0(var1, var2, var3);
   }

   private final native void yk_load_side_data0(byte[] var1, int var2, int var3);

   public final byte[] yk_uninit() {
      this._cleaningHelper.unregister();
      return this.yk_uninit0();
   }

   private final native byte[] yk_uninit0();

   public final boolean getPassthroughMode() {
      return this.yk_passthroughMode;
   }

   public final void setPassthroughMode(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public static final native boolean isSupported();
}
