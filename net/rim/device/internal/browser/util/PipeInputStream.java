package net.rim.device.internal.browser.util;

import java.io.InputStream;

public class PipeInputStream extends InputStream implements PipeInput {
   Pipe _pipe;
   PipeContext _context;
   PipeContext _markedContext;

   public int getNumRead() {
      return 0;
   }

   public Pipe getCacheableData() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getEstimatedSize() {
      return this._pipe.getEstimatedSize();
   }

   @Override
   public int readCompressedInt() {
      return this._pipe.readCompressedInt(this._context);
   }

   @Override
   public Pipe getPipe() {
      return this._pipe;
   }

   @Override
   public int readByteArray(PipePtr var1, int var2) {
      return this._pipe.readByteArray(var1, var2, this._context);
   }

   @Override
   public String readInlineString(String var1) {
      return this._pipe.readInlineString(this._context, var1);
   }

   @Override
   public void skipInlineString() {
      this._pipe.skipInlineString(this._context);
   }

   @Override
   public PipeContext getPosition() {
      return this._context;
   }

   @Override
   public boolean markSupported() {
      return true;
   }

   @Override
   public void mark(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void reset() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int read() {
      return this._pipe.read(this._context);
   }

   PipeInputStream(Pipe var1) {
      this._pipe = var1;
      this._context = new PipeContext();
      this._markedContext = new PipeContext();
   }

   @Override
   public void close() {
      this._pipe.closeRead(this._context);
   }

   PipeInputStream(Pipe var1, int var2, int var3, int var4) {
      this._pipe = var1;
      this._context = new PipeContext();
      this._context._currentPacket = var2;
      this._context._currentReadPos = var3;
      this._context._availableBytes = var4;
      this._markedContext = new PipeContext();
      this._markedContext._currentReadPos = var3;
      this._markedContext._currentPacket = var2;
      this._markedContext._availableBytes = var4;
   }

   @Override
   public int available() {
      return this._pipe.available(this._context);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      return this._pipe.read(var1, var2, var3, this._context);
   }

   @Override
   public long skip(long var1) {
      return this._pipe.skip(this._context, var1);
   }
}
