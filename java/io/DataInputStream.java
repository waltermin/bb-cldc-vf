package java.io;

public class DataInputStream extends InputStream implements DataInput {
   protected InputStream in;

   public DataInputStream(InputStream var1) {
      this.in = var1;
   }

   @Override
   public int read() {
      return this.in.read();
   }

   @Override
   public final int read(byte[] var1) {
      return this.in.read(var1, 0, var1.length);
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public final void readFully(byte[] var1) {
      this.readFully(var1, 0, var1.length);
   }

   @Override
   public final void readFully(byte[] var1, int var2, int var3) {
      if (var3 < 0) {
         throw new IndexOutOfBoundsException();
      }

      int var4 = 0;

      while (var4 < var3) {
         int var5 = this.read(var1, var2 + var4, var3 - var4);
         if (var5 < 0) {
            throw new EOFException();
         }

         var4 += var5;
      }
   }

   @Override
   public final int skipBytes(int var1) {
      int var2 = 0;
      int var3 = 0;

      while (var2 < var1 && (var3 = (int)this.skip(var1 - var2)) > 0) {
         var2 += var3;
      }

      return var2;
   }

   @Override
   public final boolean readBoolean() {
      int var1 = this.read();
      if (var1 < 0) {
         throw new EOFException();
      } else {
         return var1 != 0;
      }
   }

   @Override
   public final byte readByte() {
      int var1 = this.read();
      if (var1 < 0) {
         throw new EOFException();
      } else {
         return (byte)var1;
      }
   }

   @Override
   public final int readUnsignedByte() {
      int var1 = this.read();
      if (var1 < 0) {
         throw new EOFException();
      } else {
         return var1;
      }
   }

   @Override
   public final short readShort() {
      return (short)this.readUnsignedShort();
   }

   @Override
   public final int readUnsignedShort() {
      int var1 = this.read();
      int var2 = this.read();
      if ((var1 | var2) < 0) {
         throw new EOFException();
      } else {
         return (var1 << 8) + (var2 << 0);
      }
   }

   @Override
   public final char readChar() {
      return (char)this.readUnsignedShort();
   }

   @Override
   public final int readInt() {
      int var1 = this.read();
      int var2 = this.read();
      int var3 = this.read();
      int var4 = this.read();
      if ((var1 | var2 | var3 | var4) < 0) {
         throw new EOFException();
      } else {
         return (var1 << 24) + (var2 << 16) + (var3 << 8) + (var4 << 0);
      }
   }

   @Override
   public final long readLong() {
      return ((long)this.readInt() << 32) + (this.readInt() & 4294967295L);
   }

   @Override
   public final float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public final double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public final String readUTF() {
      return readUTF(this);
   }

   public static final String readUTF(DataInput var0) {
      int var1 = var0.readUnsignedByte();
      int var2 = var0.readUnsignedByte();
      int var3 = (var1 << 8) + (var2 << 0);
      StringBuffer var4 = new StringBuffer(var3);
      byte[] var5 = new byte[var3];
      int var9 = 0;
      var0.readFully(var5, 0, var3);

      while (var9 < var3) {
         int var6 = var5[var9] & 255;
         switch (var6 >> 4) {
            case -1:
            case 8:
            case 9:
            case 10:
            case 11:
               throw new UTFDataFormatException();
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default:
               var9++;
               var4.append((char)var6);
               break;
            case 12:
            case 13:
               var9 += 2;
               if (var9 > var3) {
                  throw new UTFDataFormatException();
               }

               byte var10 = var5[var9 - 1];
               if ((var10 & 192) != 128) {
                  throw new UTFDataFormatException();
               }

               var4.append((char)((var6 & 31) << 6 | var10 & 63));
               break;
            case 14:
               var9 += 3;
               if (var9 > var3) {
                  throw new UTFDataFormatException();
               }

               byte var7 = var5[var9 - 2];
               byte var8 = var5[var9 - 1];
               if ((var7 & 192) != 128 || (var8 & 192) != 128) {
                  throw new UTFDataFormatException();
               }

               var4.append((char)((var6 & 15) << 12 | (var7 & 63) << 6 | (var8 & 63) << 0));
         }
      }

      return (String)(new Object(var4));
   }

   @Override
   public long skip(long var1) {
      return this.in.skip(var1);
   }

   @Override
   public int available() {
      return this.in.available();
   }

   @Override
   public void close() {
      this.in.close();
   }

   @Override
   public synchronized void mark(int var1) {
      this.in.mark(var1);
   }

   @Override
   public synchronized void reset() {
      this.in.reset();
   }

   @Override
   public boolean markSupported() {
      return this.in.markSupported();
   }
}
