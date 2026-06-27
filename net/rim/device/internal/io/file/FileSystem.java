package net.rim.device.internal.io.file;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.io.file.FileSystemListener;
import net.rim.device.api.io.FileInfo;
import net.rim.device.api.io.file.FileSystemJournalEntry;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.WeakReference;

public final class FileSystem {
   private Hashtable _registeredRoots;
   private Object[] _fileSystemListeners;
   private WeakReference[] _fileSystemApps;
   private Object[] _fileJournalListeners;
   private WeakReference[] _fileJournalApps;
   private boolean[] _fileJournalEventPending;
   private long _nextUSN;
   private String[] _entryPath;
   private String[] _entryOldPath;
   private long[] _entryUSN;
   private int[] _entryEvent;
   private FileSystemJournalEntry[] _entryCache;
   private int _currentTail;
   private boolean _commitOnDelete;
   private Object _journalLock;
   private InputStream[][][] _openedInputStreams;
   private InputStream[][][] _openedRIMInputStreams;
   private OutputStream[][][] _openedOutputStreams;
   private OutputStream[][][] _openedRIMOutputStreams;
   private int[] _maxWriteSize;
   private int[] _maxReadSize;
   public static final int FILESYSTEM_SDCARD;
   public static final int FILESYSTEM_INTERNAL_FLASH;
   public static final int FILE_STATUS_SUCCESS;
   public static String SDCARD_ROOT_STR;
   private static final int JOURNAL_SIZE;
   private static final int MAX_THIRD_PARTY_HANDLES;
   private static final long FILE_SYSTEM_REGISTRY;
   private static FileSystem _fileSystem;

   private FileSystem() {
   }

   private static final void assertPermissions() {
      ApplicationControl.assertFileApiAllowed(true);
      ApplicationControl.assertIPCAllowed(true);
   }

   public static final Enumeration getRoots() {
      return _fileSystem._registeredRoots.keys();
   }

   public static final int getMaxReadSize(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getMaxWriteSize(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int mount(int var0, String var1, Class var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void unmount(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void flushAllStreams(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void closeAllStreams(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getRootName(int var0) {
      return var0 == 1 ? SDCARD_ROOT_STR : null;
   }

   public static final void rootChanged(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Class getRoot(String var0) {
      return (Class)_fileSystem._registeredRoots.get(var0);
   }

   public static final boolean addFileSystemListener(FileSystemListener var0, Application var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void checkForDeadListeners() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean removeFileSystemListener(FileSystemListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean addJournalListener(FileSystemJournalListener var0, Application var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean removeJournalListener(FileSystemJournalListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final FileSystemJournalEntry getJournalEntry(long var0) {
      return _fileSystem.getJournalEntryInternal(var0);
   }

   private final FileSystemJournalEntry getJournalEntryInternal(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getNextJournalUSN() {
      return _fileSystem._nextUSN;
   }

   public static final void addFileJournalEntry(String var0, int var1) {
      _fileSystem.addFileJournalEntryInternal(var0, var0, var1);
   }

   public static final void addFileJournalEntry(String var0, String var1, int var2) {
      _fileSystem.addFileJournalEntryInternal(var0, var1, var2);
   }

   public static final void suspendCommitOnDelete() {
      _fileSystem._commitOnDelete = false;
   }

   public static final void resumeCommitOnDelete() {
      _fileSystem._commitOnDelete = true;
   }

   private final void addFileJournalEntryInternal(String var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void notifyRootChanged(int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void notifyJournalChanged() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isSupported() {
      return InternalServices.isSoftwareCapable(0);
   }

   public static final boolean isFileSystemSupported(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void registerInputStream(int var0, InputStream var1) {
      boolean var2 = ControlledAccess.verifyRRISignatures(true);
      if (var2) {
         Arrays.add(_fileSystem._openedRIMInputStreams[var0], var1);
      } else {
         Arrays.add(_fileSystem._openedInputStreams[var0], var1);
      }
   }

   public static final void registerOutputStream(int var0, OutputStream var1) {
      boolean var2 = ControlledAccess.verifyRRISignatures(true);
      if (var2) {
         Arrays.add(_fileSystem._openedRIMOutputStreams[var0], var1);
      } else {
         Arrays.add(_fileSystem._openedOutputStreams[var0], var1);
      }
   }

   public static final void deregisterOutputStream(int var0, OutputStream var1) {
      Arrays.remove(_fileSystem._openedOutputStreams[var0], var1);
      Arrays.remove(_fileSystem._openedRIMOutputStreams[var0], var1);
   }

   public static final void deregisterInputStream(int var0, InputStream var1) {
      Arrays.remove(_fileSystem._openedInputStreams[var0], var1);
      Arrays.remove(_fileSystem._openedRIMInputStreams[var0], var1);
   }

   public static final long open(int var0, String var1, int var2) {
      boolean var3 = ControlledAccess.verifyRRISignatures(true);
      return !var3 && _fileSystem._openedInputStreams[var0].length + _fileSystem._openedOutputStreams[var0].length >= 8 ? 11 : open0(var0, var1, var2);
   }

   public static final int delete(int var0, String var1) {
      int var2 = delete0(var0, var1);
      if (var2 == 0 && _fileSystem._commitOnDelete) {
         commit(var0, 0);
      }

      return var2;
   }

   public static final int rename(int var0, String var1, String var2) {
      int var3 = rename0(var0, var1, var2);
      if (var3 == 0) {
         commit(var0, 0);
      }

      return var3;
   }

   public static final int mkdir(int var0, String var1) {
      int var2 = mkdir0(var0, var1);
      if (var2 == 0) {
         commit(var0, 0);
      }

      return var2;
   }

   public static final int rmdir(int var0, String var1) {
      int var2 = rmdir0(var0, var1);
      if (var2 == 0) {
         commit(var0, 0);
      }

      return var2;
   }

   public static final native boolean isFileSystemSupported0(int var0);

   public static final native int close(int var0);

   private static final native long open0(int var0, String var1, int var2);

   public static final native long read(int var0, byte[] var1);

   public static final native int write(int var0, byte[] var1, int var2);

   private static final native int delete0(int var0, String var1);

   private static final native int rename0(int var0, String var1, String var2);

   public static final native int getFileSystemInfo(int var0, FileSystemInfo var1);

   public static final native long findFirst(int var0, String var1, FileInfo var2);

   public static final native int findNext(int var0, int var1, FileInfo var2);

   public static final native int findClose(int var0);

   public static final native int format(int var0);

   private static final native int mount0(int var0);

   private static final native int unmount0(int var0);

   private static final native int mkdir0(int var0, String var1);

   private static final native int rmdir0(int var0, String var1);

   public static final native long tell(int var0);

   public static final native int seek(int var0, long var1, int var3);

   public static final native int getFileInfo(int var0, FileInfo var1);

   public static final native int getFileInfo(int var0, String var1, FileInfo var2);

   public static final native int setFileAttrib(int var0, String var1, int var2);

   public static final native int commit(int var0, int var1);

   public static final native int truncate(int var0, long var1);

   public static final native long directorySize(int var0, String var1);
}
