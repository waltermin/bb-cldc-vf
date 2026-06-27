package net.rim.device.api.io;

public final class FileInfo {
   private long _fileSize;
   private long _lastModified;
   private String _fileName;
   private int _attributes;
   public static final int ATTRIBUTE_ARCHIVE;
   public static final int ATTRIBUTE_READ_ONLY;
   public static final int ATTRIBUTE_SYSTEM;
   public static final int ATTRIBUTE_HIDDEN;
   public static final int ATTRIBUTE_SET_MASK;
   public static final int ATTRIBUTE_DIRECTORY;
   public static final int ATTRIBUTE_VOLUME;

   public FileInfo() {
   }

   public FileInfo(String var1, long var2, long var4, int var6) {
      this._fileName = var1;
      this._fileSize = var2;
      this._lastModified = var4;
      this._attributes = var6;
   }

   public final String getFileName() {
      return this._fileName;
   }

   public final long getFileSize() {
      return this._fileSize;
   }

   public final long getLastModified() {
      return this._lastModified;
   }

   public final int getAttributes() {
      return this._attributes;
   }
}
