package java.io;

import net.rim.device.api.util.StringUtilities;

public class DataOutputStream extends OutputStream implements DataOutput {
   protected OutputStream out;

   public DataOutputStream(OutputStream var1) {
      this.out = var1;
   }

   @Override
   public void write(int var1) {
      this.out.write(var1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this.out.write(var1, var2, var3);
   }

   @Override
   public void flush() {
      this.out.flush();
   }

   @Override
   public void close() {
      this.out.close();
   }

   @Override
   public final void writeBoolean(boolean var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public final void writeByte(int var1) {
      this.write(var1);
   }

   @Override
   public final void writeShort(int var1) {
      this.write(var1 >>> 8 & 0xFF);
      this.write(var1 >>> 0 & 0xFF);
   }

   @Override
   public final void writeChar(int var1) {
      this.write(var1 >>> 8 & 0xFF);
      this.write(var1 >>> 0 & 0xFF);
   }

   @Override
   public final void writeInt(int var1) {
      this.write(var1 >>> 24 & 0xFF);
      this.write(var1 >>> 16 & 0xFF);
      this.write(var1 >>> 8 & 0xFF);
      this.write(var1 >>> 0 & 0xFF);
   }

   @Override
   public final void writeLong(long var1) {
      this.write((int)(var1 >>> 56) & 0xFF);
      this.write((int)(var1 >>> 48) & 0xFF);
      this.write((int)(var1 >>> 40) & 0xFF);
      this.write((int)(var1 >>> 32) & 0xFF);
      this.write((int)(var1 >>> 24) & 0xFF);
      this.write((int)(var1 >>> 16) & 0xFF);
      this.write((int)(var1 >>> 8) & 0xFF);
      this.write((int)(var1 >>> 0) & 0xFF);
   }

   @Override
   public final void writeFloat(float var1) {
      this.writeInt(Float.floatToIntBits(var1));
   }

   @Override
   public final void writeDouble(double var1) {
      this.writeLong(Double.doubleToLongBits(var1));
   }

   @Override
   public final void writeChars(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final void writeUTF(String var1) {
      StringUtilities.writeUTF(var1, this);
   }
}
