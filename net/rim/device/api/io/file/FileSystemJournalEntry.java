package net.rim.device.api.io.file;

public final class FileSystemJournalEntry {
   private String _path;
   private String _oldPath;
   private int _event;
   private long _usn;
   public static final int FILE_ADDED;
   public static final int FILE_DELETED;
   public static final int FILE_CHANGED;
   public static final int FILE_RENAMED;

   private FileSystemJournalEntry() {
   }

   private FileSystemJournalEntry(long var1, String var3, String var4, int var5) {
      this._usn = var1;
      this._path = var3;
      this._oldPath = var4;
      this._event = var5;
   }

   public final String getPath() {
      return this._path;
   }

   public final String getOldPath() {
      return this._oldPath;
   }

   public final int getEvent() {
      return this._event;
   }

   public final long getUSN() {
      return this._usn;
   }

   public static final FileSystemJournalEntry createEntry(long var0, String var2, String var3, int var4) {
      return new FileSystemJournalEntry(var0, var2, var3, var4);
   }
}
