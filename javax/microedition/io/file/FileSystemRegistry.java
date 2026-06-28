package javax.microedition.io.file;

import java.util.Enumeration;
import net.rim.device.internal.io.file.FileSystem;

public class FileSystemRegistry {
   FileSystemRegistry() {
   }

   public static boolean addFileSystemListener(FileSystemListener listener) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static boolean removeFileSystemListener(FileSystemListener listener) {
      return FileSystem.removeFileSystemListener(listener);
   }

   public static Enumeration listRoots() {
      return FileSystem.getRoots();
   }
}
