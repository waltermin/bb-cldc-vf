package javax.microedition.io.file;

import java.util.Enumeration;
import net.rim.device.internal.io.file.FileSystem;

public class FileSystemRegistry {
   FileSystemRegistry() {
   }

   public static boolean addFileSystemListener(FileSystemListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean removeFileSystemListener(FileSystemListener var0) {
      return FileSystem.removeFileSystemListener(var0);
   }

   public static Enumeration listRoots() {
      return FileSystem.getRoots();
   }
}
