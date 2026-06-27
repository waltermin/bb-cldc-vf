package net.rim.device.api.io;

import java.io.OutputStream;
import java.util.Vector;

public class SharedOutputStream {
   private int _currentIndex;
   private OutputStream _out;
   private Vector _sharedStreams;

   public SharedOutputStream(OutputStream var1) {
      this._out = var1;
      this._currentIndex = 0;
      this._sharedStreams = (Vector)(new Object());
   }

   public synchronized OutputStream getOutputStream() {
      SharedOutputStreamPart var1 = new SharedOutputStreamPart(this, this._sharedStreams.size());
      this._sharedStreams.addElement(var1);
      return var1;
   }

   synchronized void write(int var1, byte[] var2, int var3, int var4) {
      if (var1 > this._currentIndex) {
         SharedOutputStreamPart var5 = (SharedOutputStreamPart)this._sharedStreams.elementAt(var1);
         if (var5.isWritable()) {
            var5.getStream().write(var2, var3, var4);
         } else {
            throw new Object();
         }
      } else if (var1 == this._currentIndex) {
         this._out.write(var2, var3, var4);
      } else {
         throw new Object();
      }
   }

   synchronized void flush(int var1) {
      if (var1 > this._currentIndex) {
         SharedOutputStreamPart var2 = (SharedOutputStreamPart)this._sharedStreams.elementAt(var1);
         if (var2.isWritable()) {
            var2.getStream().flush();
         } else {
            throw new Object();
         }
      } else if (var1 == this._currentIndex) {
         this._out.flush();
      } else {
         throw new Object();
      }
   }

   synchronized void close(int var1) {
      if (var1 > this._currentIndex) {
         SharedOutputStreamPart var4 = (SharedOutputStreamPart)this._sharedStreams.elementAt(var1);
         var4.setIsWritable(false);
      } else if (var1 == this._currentIndex) {
         this._out.flush();
         this._currentIndex++;
         this.writeSeparator();

         while (this._currentIndex < this._sharedStreams.size()) {
            SharedOutputStreamPart var2 = (SharedOutputStreamPart)this._sharedStreams.elementAt(this._currentIndex);
            NoCopyByteArrayOutputStream var3 = var2.getStream();
            var3.flush();
            this._out.write(var3.getByteArray(), 0, var3.size());
            var3.close();
            Object var5 = null;
            var2.setStream(null);
            if (var2.isWritable()) {
               var2.setIsWritable(false);
               return;
            }

            this.writeSeparator();
            this._currentIndex++;
         }
      } else {
         throw new Object();
      }
   }

   public synchronized void close() {
      this._out.close();
   }

   public void writeSeparator() {
   }
}
