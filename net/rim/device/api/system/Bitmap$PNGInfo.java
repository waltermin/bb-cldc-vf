package net.rim.device.api.system;

public final class Bitmap$PNGInfo {
   public short width;
   public short height;
   public byte color_type;
   public byte bit_depth;
   public boolean transparency;
   public boolean alpha;
   public int alpha_bit_depth;

   public final void getPNGInfo(byte[] png, int offset, int length) {
      throw new RuntimeException("cod2jar: type check");
   }
}
