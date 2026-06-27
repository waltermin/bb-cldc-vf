package net.rim.device.api.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.InputStream;
import java.io.OutputStream;
import net.rim.vm.Array;
import net.rim.vm.Memory;

public class DataBuffer implements DataInput, DataOutput, Persistable {
   private byte[] _buffer;
   private int _start;
   private int _position;
   private int _length;
   private boolean _useBigEndianFlag;

   public void setData(byte[] var1, int var2, int var3) {
      this.setData(var1, var2, var3, this._useBigEndianFlag);
   }

   public void setData(byte[] var1, int var2, int var3, boolean var4) {
      if (var2 >= 0 && var3 >= 0) {
         if (var1 != null) {
            this._buffer = var1;
            this._length = Math.min(this._buffer.length, var2 + var3);
         } else if (var3 != 0) {
            this._buffer = new byte[var3];
            this._length = 0;
         } else {
            this._buffer = null;
            this._length = 0;
         }

         this._start = Math.min(this._length, var2);
         this._position = this._start;
         this._useBigEndianFlag = var4;
      } else {
         throw new Object();
      }
   }

   public void reset() {
      this._buffer = null;
      this._length = 0;
      this._start = 0;
      this._position = 0;
   }

   public int getPosition() {
      return this._position - this._start;
   }

   public void setPosition(int var1) {
      this._position = MathUtilities.clamp(this._start, this._start + var1, this._length);
   }

   public void zero() {
      if (this._buffer != null && Memory.getSecureOldObjects()) {
         Arrays.fill(this._buffer, (byte)0, this._start, this._length - this._start);
      }
   }

   public int getLength() {
      return this._length - this._start;
   }

   public void setLength(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      var1 += this._start;
      this.ensureBuffer(var1);
      this._length = var1;
      this._position = Math.min(this._length, this._position);
   }

   public void ensureLength(int var1) {
      this.ensureBuffer(this._start + var1);
   }

   public void ensureCapacity(int var1) {
      this.ensureBuffer(this._position + var1);
   }

   protected void ensureBuffer(int var1) {
      if (var1 >= this._length) {
         if (this._buffer == null) {
            this._buffer = new byte[var1];
         } else if (var1 > this._buffer.length) {
            int var2 = var1 - this._buffer.length;
            Array.extend(this._buffer, var2);
         }

         this._length = var1;
      }
   }

   public void trim() {
      this.trim(true);
   }

   public void trim(boolean var1) {
      if (this._buffer != null) {
         this._length = this._position;
         if (var1) {
            Array.resize(this._buffer, this._length);
         }
      }
   }

   public void trimHead(boolean var1) {
      if (this._buffer != null && this._position > 0) {
         int var2 = this._length - this._position;
         System.arraycopy(this._buffer, this._position, this._buffer, 0, var2);
         this._length = var2;
         this._position = 0;
         if (var1) {
            Array.resize(this._buffer, this._length);
         }
      }
   }

   public int available() {
      return this._length - this._position;
   }

   public boolean eof() {
      return this._position >= this._length;
   }

   public void rewind() {
      this._position = this._start;
   }

   public boolean isBigEndian() {
      return this._useBigEndianFlag;
   }

   public void setBigEndian(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] toArray() {
      if (this._buffer != null) {
         byte[] var1 = new byte[this._length - this._start];
         System.arraycopy(this._buffer, this._start, var1, 0, var1.length);
         return var1;
      } else {
         return new byte[0];
      }
   }

   public byte[] getArray() {
      return this._buffer != null ? this._buffer : new byte[0];
   }

   public int getArrayStart() {
      return this._start;
   }

   public int getArrayPosition() {
      return this._position;
   }

   public int getArrayLength() {
      return this._length;
   }

   public void writeCompressedLong(long var1) {
      byte var3 = 63;

      int var4;
      for (var4 = 10; var3 > 0 && ((int)(var1 >>> var3) & 127) == 0; var4--) {
         var3 -= 7;
      }

      this.ensureBuffer(this._position + var4);

      while (var3 > 0) {
         this.nextByte(128 | (int)(var1 >>> var3));
         var3 -= 7;
      }

      this.nextByte((int)(var1 & 127));
   }

   public void writeCompressedInt(int var1) {
      byte var2 = 28;

      int var3;
      for (var3 = 5; var2 > 0 && (var1 >>> var2 & 127) == 0; var3--) {
         var2 -= 7;
      }

      this.ensureBuffer(this._position + var3);

      while (var2 > 0) {
         this.nextByte(128 | var1 >>> var2);
         var2 -= 7;
      }

      this.nextByte(var1 & 127);
   }

   public void writeByteArray(byte[] var1, int var2, int var3, boolean var4) {
      if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         if (var4) {
            this.writeCompressedInt(var3);
         }

         this.ensureBuffer(this._position + var3);
         System.arraycopy(var1, var2, this._buffer, this._position, var3);
         this._position += var3;
      } else {
         throw new Object();
      }
   }

   public void writeByteArray(byte[] var1, int var2, int var3) {
      this.writeByteArray(var1, var2, var3, true);
   }

   public void writeByteArray(byte[] var1) {
      this.writeByteArray(var1, 0, var1.length);
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      int var4 = Math.min(var3, this._length - this._position);
      if (var4 != 0) {
         System.arraycopy(this._buffer, this._position, var1, var2, var4);
      }

      this._position += var4;
      return var4;
   }

   public int read(OutputStream var1) {
      return this.read(var1, this._length - this._position);
   }

   public int read(OutputStream var1, int var2) {
      if (var1 != null && var2 >= 0) {
         int var3 = Math.min(var2, this._length - this._position);
         if (var3 != 0) {
            var1.write(this._buffer, this._position, var3);
            this._position += var3;
         }

         return var3;
      } else {
         throw new Object();
      }
   }

   public byte[] readByteArray() {
      int var1 = this.readCompressedInt();
      if (this._position + var1 > this._length) {
         throw new Object();
      }

      byte[] var2 = new byte[var1];
      System.arraycopy(this._buffer, this._position, var2, 0, var1);
      this._position += var1;
      return var2;
   }

   public void write(DataInput var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void write(InputStream var1, int var2) {
      var2 = Math.min(var2, var1.available());
      this.ensureCapacity(var2);
      this._position = this._position + var1.read(this._buffer, this._position, var2);
   }

   public void write(InputStream var1) {
      this.write(var1, var1.available());
   }

   public void write(DataBuffer var1, int var2) {
      this.write(var1.getArray(), var1.getArrayPosition(), var2);
   }

   public long readCompressedLong() {
      long var1 = 0;
      int var3 = 0;

      while (true) {
         byte var4 = this.readByte();
         var1 |= var4 & 127;
         if ((var4 & 128) == 0) {
            return var1;
         }

         if (++var3 > 9) {
            throw new Object();
         }

         var1 <<= 7;
      }
   }

   public int readCompressedInt() {
      int var1 = 0;
      int var2 = 0;

      while (true) {
         byte var3 = this.readByte();
         var1 |= var3 & 127;
         if ((var3 & 128) == 0) {
            return var1;
         }

         var2++;
         if (var2 > 4 || var2 == 4 && (var1 & 234881024) != 0) {
            throw new Object();
         }

         var1 <<= 7;
      }
   }

   @Override
   public int readUnsignedShort() {
      return this.readShort() & 65535;
   }

   @Override
   public String readUTF() {
      return DataInputStream.readUTF(this);
   }

   @Override
   public int readUnsignedByte() {
      return this.readByte() & 0xFF;
   }

   @Override
   public short readShort() {
      if (this._position + 2 > this._length) {
         throw new Object();
      }

      int var1;
      int var2;
      if (this._useBigEndianFlag) {
         var1 = this.nextByte();
         var2 = this.nextByte();
      } else {
         var2 = this.nextByte();
         var1 = this.nextByte();
      }

      return (short)(var1 << 8 | var2);
   }

   @Override
   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         this.ensureBuffer(this._position + var3);
         System.arraycopy(var1, var2, this._buffer, this._position, var3);
         this._position += var3;
      } else {
         throw new Object();
      }
   }

   @Override
   public final double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public final float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public long readLong() {
      if (this._position + 8 > this._length) {
         throw new Object();
      }

      long var1;
      long var3;
      long var5;
      long var7;
      long var9;
      long var11;
      long var13;
      long var15;
      if (this._useBigEndianFlag) {
         var1 = this.nextByte();
         var3 = this.nextByte();
         var5 = this.nextByte();
         var7 = this.nextByte();
         var9 = this.nextByte();
         var11 = this.nextByte();
         var13 = this.nextByte();
         var15 = this.nextByte();
      } else {
         var15 = this.nextByte();
         var13 = this.nextByte();
         var11 = this.nextByte();
         var9 = this.nextByte();
         var7 = this.nextByte();
         var5 = this.nextByte();
         var3 = this.nextByte();
         var1 = this.nextByte();
      }

      return var1 << 56 | var3 << 48 | var5 << 40 | var7 << 32 | var9 << 24 | var11 << 16 | var13 << 8 | var15;
   }

   @Override
   public int readInt() {
      if (this._position + 4 > this._length) {
         throw new Object();
      }

      int var1;
      int var2;
      int var3;
      int var4;
      if (this._useBigEndianFlag) {
         var1 = this.nextByte();
         var2 = this.nextByte();
         var3 = this.nextByte();
         var4 = this.nextByte();
      } else {
         var4 = this.nextByte();
         var3 = this.nextByte();
         var2 = this.nextByte();
         var1 = this.nextByte();
      }

      return var1 << 24 | var2 << 16 | var3 << 8 | var4;
   }

   @Override
   public void write(int var1) {
      int var2 = this._position;
      int var3 = var2 + 1;
      this.ensureBuffer(var3);
      this._buffer[var2] = (byte)var1;
      this._position = var3;
   }

   @Override
   public void writeBoolean(boolean var1) {
      this.ensureBuffer(this._position + 1);
      this.nextByte(var1 ? 1 : 0);
   }

   @Override
   public void writeByte(int var1) {
      int var2 = this._position;
      int var3 = var2 + 1;
      this.ensureBuffer(var3);
      this._buffer[var2] = (byte)var1;
      this._position = var3;
   }

   @Override
   public void writeChar(int var1) {
      this.ensureBuffer(this._position + 2);
      if (this._useBigEndianFlag) {
         this.nextByte(var1 >>> 8);
         this.nextByte(var1);
      } else {
         this.nextByte(var1);
         this.nextByte(var1 >>> 8);
      }
   }

   @Override
   public void writeChars(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) {
      if (var3 > this._length - this._position) {
         throw new Object();
      }

      if (var3 != 0) {
         System.arraycopy(this._buffer, this._position, var1, var2, var3);
      }

      this._position += var3;
   }

   @Override
   public void readFully(byte[] var1) {
      this.readFully(var1, 0, var1.length);
   }

   @Override
   public char readChar() {
      if (this._position + 2 > this._length) {
         throw new Object();
      }

      int var1;
      int var2;
      if (this._useBigEndianFlag) {
         var1 = this.nextByte();
         var2 = this.nextByte();
      } else {
         var2 = this.nextByte();
         var1 = this.nextByte();
      }

      return (char)(var1 << 8 | var2);
   }

   @Override
   public void writeInt(int var1) {
      this.ensureBuffer(this._position + 4);
      if (this._useBigEndianFlag) {
         this.nextByte(var1 >>> 24);
         this.nextByte(var1 >>> 16);
         this.nextByte(var1 >>> 8);
         this.nextByte(var1);
      } else {
         this.nextByte(var1);
         this.nextByte(var1 >>> 8);
         this.nextByte(var1 >>> 16);
         this.nextByte(var1 >>> 24);
      }
   }

   @Override
   public void writeLong(long var1) {
      this.ensureBuffer(this._position + 8);
      if (this._useBigEndianFlag) {
         this.nextByte((int)(var1 >>> 56));
         this.nextByte((int)(var1 >>> 48));
         this.nextByte((int)(var1 >>> 40));
         this.nextByte((int)(var1 >>> 32));
         this.nextByte((int)(var1 >>> 24));
         this.nextByte((int)(var1 >>> 16));
         this.nextByte((int)(var1 >>> 8));
         this.nextByte((int)var1);
      } else {
         this.nextByte((int)var1);
         this.nextByte((int)(var1 >>> 8));
         this.nextByte((int)(var1 >>> 16));
         this.nextByte((int)(var1 >>> 24));
         this.nextByte((int)(var1 >>> 32));
         this.nextByte((int)(var1 >>> 40));
         this.nextByte((int)(var1 >>> 48));
         this.nextByte((int)(var1 >>> 56));
      }
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
   public void writeShort(int var1) {
      this.ensureBuffer(this._position + 2);
      if (this._useBigEndianFlag) {
         this.nextByte(var1 >>> 8);
         this.nextByte(var1);
      } else {
         this.nextByte(var1);
         this.nextByte(var1 >>> 8);
      }
   }

   @Override
   public void writeUTF(String var1) {
      StringUtilities.writeUTF(var1, this);
   }

   @Override
   public boolean readBoolean() {
      return this.readByte() != 0;
   }

   @Override
   public byte readByte() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public int skipBytes(int var1) {
      if (var1 <= 0) {
         return 0;
      }

      int var2 = Math.min(this._length - this._position, var1);
      this._position += var2;
      return var2;
   }

   public DataBuffer(boolean var1) {
      this.setData(null, 0, 0, var1);
   }

   private final void nextByte(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public DataBuffer(byte[] var1, int var2, int var3, boolean var4) {
      this.setData(var1, var2, var3, var4);
   }

   public DataBuffer(int var1, boolean var2) {
      this.setData(null, 0, var1, var2);
   }

   private final int nextByte() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static int getCompressedIntSize(int var0) {
      byte var1 = 28;

      int var2;
      for (var2 = 5; var1 > 0; var2--) {
         if ((var0 >>> var1 & 127) != 0) {
            return var2;
         }

         var1 -= 7;
      }

      return var2;
   }

   public DataBuffer(DataBuffer var1, int var2) {
      this.setData(var1.getArray(), var1.getArrayPosition(), var2, var1._useBigEndianFlag);
      var1.skipBytes(var2);
   }

   public DataBuffer() {
      this.setData(null, 0, 0, true);
   }
}
