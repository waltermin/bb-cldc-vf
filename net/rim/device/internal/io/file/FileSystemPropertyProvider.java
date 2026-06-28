package net.rim.device.internal.io.file;

import net.rim.device.internal.system.SystemPropertyProvider;

public final class FileSystemPropertyProvider implements SystemPropertyProvider {
   @Override
   public final String getProperty(String property) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
