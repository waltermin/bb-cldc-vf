package net.rim.device.internal.io.file;

import net.rim.device.api.io.file.FileSystemJournalListener;

class FileSystem$JournalChanged implements Runnable {
   private FileSystemJournalListener _listener;
   private final FileSystem this$0;

   public FileSystem$JournalChanged(FileSystem _1, FileSystemJournalListener listener) {
      this.this$0 = _1;
      this._listener = listener;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: type check");
   }
}
