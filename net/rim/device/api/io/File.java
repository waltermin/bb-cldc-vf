package net.rim.device.api.io;

import net.rim.device.api.io.file.FileIOException;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.io.file.FileSystem;
import net.rim.device.internal.io.file.FileSystemInfo;

public final class File {
   public static final int FILESYSTEM_PATRIOT;

   private File() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }

   public static final boolean isSupported() {
      return RadioInfo.areWAFsSupported(8) && FileSystem.isSupported();
   }

   public static final boolean isFileSystemSupported(int var0) {
      return isSupported() ? FileSystem.isFileSystemSupported(var0) : false;
   }

   public static final void delete(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void rename(int var0, String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getFileSystemTotalSpace(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getFileSystemFreeSpace(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getFileSize(int var0, String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String findFirst(int var0, String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean findFirst(int var0, String var1, FileInfo var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String findNext(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean findNext(int var0, FileInfo var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final FileSystemInfo getFileSystemInfo(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final int open(int var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void close(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean checkStatus(int var0) {
      switch (var0) {
         case -1:
            throw new FileIOException(var0);
         case 0:
         default:
            return true;
         case 1:
            return false;
      }
   }

   static final FileEventDispatcher getEventDispatcher() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final long read(int var0, byte[] var1) {
      assertPermission();
      return FileSystem.read(var0, var1);
   }

   static final int write(int var0, byte[] var1, int var2) {
      assertPermission();
      return FileSystem.write(var0, var1, var2);
   }
}
