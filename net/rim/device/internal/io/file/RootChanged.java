package net.rim.device.internal.io.file;

import javax.microedition.io.file.FileSystemListener;

final class RootChanged implements Runnable {
   private FileSystemListener _listener;
   private String _rootName;
   private int _state;

   public RootChanged(FileSystemListener var1, int var2, String var3) {
      this._listener = var1;
      this._state = var2;
      this._rootName = var3;
   }

   @Override
   public final void run() {
      this._listener.rootChanged(this._state, this._rootName);
   }
}
