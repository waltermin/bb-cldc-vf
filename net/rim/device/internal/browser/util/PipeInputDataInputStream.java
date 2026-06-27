package net.rim.device.internal.browser.util;

import java.io.DataInputStream;
import java.io.InputStream;

public final class PipeInputDataInputStream extends DataInputStream implements PipeInput {
   private PipeInput _pipeIn;

   public PipeInputDataInputStream(PipeInput var1) {
      super((InputStream)var1);
      this._pipeIn = var1;
   }

   @Override
   public final int readByteArray(PipePtr var1, int var2) {
      return this._pipeIn.readByteArray(var1, var2);
   }

   @Override
   public final int readCompressedInt() {
      return this._pipeIn.readCompressedInt();
   }

   @Override
   public final String readInlineString(String var1) {
      return this._pipeIn.readInlineString(var1);
   }

   @Override
   public final void skipInlineString() {
      this._pipeIn.skipInlineString();
   }

   @Override
   public final Pipe getPipe() {
      return this._pipeIn.getPipe();
   }

   @Override
   public final PipeContext getPosition() {
      return this._pipeIn.getPosition();
   }
}
