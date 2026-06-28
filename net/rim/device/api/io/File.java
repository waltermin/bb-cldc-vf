package net.rim.device.api.io;

import net.rim.device.api.io.file.FileIOException;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.io.file.FileSystem;
import net.rim.device.internal.io.file.FileSystemInfo;
import net.rim.device.internal.system.EventDispatchManager;

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

   public static final boolean isFileSystemSupported(int fs) {
      return isSupported() ? FileSystem.isFileSystemSupported(fs) : false;
   }

   public static final void delete(int fs, String fileName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void rename(int fs, String oldFileName, String newFileName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getFileSystemTotalSpace(int fs) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getFileSystemFreeSpace(int fs) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getFileSize(int fs, String fileName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String findFirst(int fs, String pattern) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean findFirst(int fs, String pattern, FileInfo fileInfo) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String findNext(int fs) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean findNext(int fs, FileInfo fileInfo) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final FileSystemInfo getFileSystemInfo(int fs) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final int open(int fs, String fileName, int mode) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void close(int handle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final boolean checkStatus(int status) {
      switch (status) {
         case -1:
            throw new FileIOException(status);
         case 0:
         default:
            return true;
         case 1:
            return false;
      }
   }

   static final FileEventDispatcher getEventDispatcher() {
      EventDispatchManager dispatchManager = EventDispatchManager.getInstance();
      synchronized (dispatchManager) {
         FileEventDispatcher dispatcher = (FileEventDispatcher)dispatchManager.getDispatcher(19);
         if (dispatcher == null) {
            dispatcher = new FileEventDispatcher();
            dispatchManager.setDispatcher(19, dispatcher);
         }

         return dispatcher;
      }
   }

   static final long read(int handle, byte[] data) {
      assertPermission();
      return FileSystem.read(handle, data);
   }

   static final int write(int handle, byte[] data, int length) {
      assertPermission();
      return FileSystem.write(handle, data, length);
   }
}
