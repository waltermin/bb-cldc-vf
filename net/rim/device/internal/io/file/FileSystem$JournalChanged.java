package net.rim.device.internal.io.file;

import net.rim.device.api.io.file.FileSystemJournalListener;

class FileSystem$JournalChanged implements Runnable {
   private FileSystemJournalListener _listener;
   private final FileSystem this$0;

   public FileSystem$JournalChanged(FileSystem var1, FileSystemJournalListener var2) {
      this.this$0 = var1;
      this._listener = var2;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
