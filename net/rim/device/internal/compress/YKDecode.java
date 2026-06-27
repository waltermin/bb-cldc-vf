package net.rim.device.internal.compress;

public final class YKDecode {
   private YKCleaner _cleaningHelper;
   private boolean yk_mixedMode;
   private byte[] yk_context;

   public YKDecode(boolean var1) {
      this.yk_mixedMode = var1;
      this._cleaningHelper = new YKCleaner(this);
   }

   public static final native int yk_get_codec_version();

   public final byte[] yk_decode(byte[] var1, int var2, int var3) {
      this._cleaningHelper.register();
      return this.yk_decode0(var1, var2, var3);
   }

   private final native byte[] yk_decode0(byte[] var1, int var2, int var3);

   public final void yk_load_side_data(byte[] var1, int var2, int var3) {
      this._cleaningHelper.register();
      this.yk_load_side_data0(var1, var2, var3);
   }

   private final native void yk_load_side_data0(byte[] var1, int var2, int var3);

   public final void loadContextMap(byte[] var1) {
      this._cleaningHelper.register();
      this.loadContextMap0(var1);
   }

   public final native void loadContextMap0(byte[] var1);

   public final byte[] getContextMap() {
      this._cleaningHelper.register();
      return this.getContextMap0();
   }

   private final native byte[] getContextMap0();

   public final void yk_uninit() {
      this._cleaningHelper.unregister();
      this.yk_uninit0();
   }

   private final native void yk_uninit0();

   public static final native boolean isSupported();
}
