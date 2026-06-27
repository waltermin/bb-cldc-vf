package net.rim.device.internal.io.file;

public final class FileSystemInfo {
   private long _totalSpace;
   private long _freeSpace;
   private int _minWriteLength;
   private int _maxWriteLength;
   private int _minReadLength;
   private int _maxReadLength;
   private int _maxFilenameLength;

   public final int getMinWriteLength() {
      return this._minWriteLength;
   }

   public final int getMaxWriteLength() {
      return this._maxWriteLength;
   }

   public final int getMinReadLength() {
      return this._minReadLength;
   }

   public final int getMaxReadLength() {
      return this._maxReadLength;
   }

   public final int getMaxFilenameLength() {
      return this._maxFilenameLength;
   }

   public final long getTotalSpace() {
      return this._totalSpace;
   }

   public final long getFreeSpace() {
      return this._freeSpace;
   }
}
