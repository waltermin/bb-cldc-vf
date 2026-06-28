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
import net.rim.device.api.system.UnsupportedOperationException;
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

   public static final int getMaxReadSize(int nativeRootId) {
      synchronized (_fileSystem) {
         if (_fileSystem._maxReadSize[nativeRootId] == 0) {
            FileSystemInfo fsInfo = new FileSystemInfo();
            int status = getFileSystemInfo(nativeRootId, fsInfo);
            if (status != 0) {
               throw new Object(status);
            }

            _fileSystem._maxReadSize[nativeRootId] = fsInfo.getMaxReadLength();
         }

         return _fileSystem._maxReadSize[nativeRootId];
      }
   }

   public static final int getMaxWriteSize(int nativeRootId) {
      synchronized (_fileSystem) {
         if (_fileSystem._maxWriteSize[nativeRootId] == 0) {
            FileSystemInfo fsInfo = new FileSystemInfo();
            int status = getFileSystemInfo(nativeRootId, fsInfo);
            if (status != 0) {
               throw new Object(status);
            }

            _fileSystem._maxWriteSize[nativeRootId] = fsInfo.getMaxWriteLength();
         }

         return _fileSystem._maxWriteSize[nativeRootId];
      }
   }

   public static final int mount(int nativeRootId, String rootName, Class clazz) {
      synchronized (_fileSystem) {
         if (nativeRootId != -1) {
            int result = mount0(nativeRootId);
            if (result != 0) {
               return result;
            }
         }

         if (_fileSystem._registeredRoots.put(rootName, clazz) == null) {
            _fileSystem.notifyRootChanged(0, rootName);
            return 0;
         } else {
            return 19;
         }
      }
   }

   public static final void unmount(int nativeRootId, String rootName) {
      synchronized (_fileSystem) {
         if (nativeRootId != -1) {
            closeAllStreams(nativeRootId);
            if (unmount0(nativeRootId) != 0) {
               return;
            }
         }

         if (_fileSystem._registeredRoots.remove(rootName) != null) {
            _fileSystem.notifyRootChanged(1, rootName);
         }
      }
   }

   public static final void flushAllStreams(int rootId) {
      throw new RuntimeException("cod2jar: invokevirtual: receiver not in world");
   }

   public static final void closeAllStreams(int rootId) {
      throw new RuntimeException("cod2jar: invokevirtual: receiver not in world");
   }

   public static final String getRootName(int root) {
      return root == 1 ? SDCARD_ROOT_STR : null;
   }

   public static final void rootChanged(String root) {
      synchronized (_fileSystem._journalLock) {
         _fileSystem.notifyRootChanged(1, root);
         _fileSystem.notifyRootChanged(0, root);
      }
   }

   public static final Class getRoot(String root) {
      return (Class)_fileSystem._registeredRoots.get(root);
   }

   public static final boolean addFileSystemListener(FileSystemListener listener, Application app, boolean weakRef) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void checkForDeadListeners() {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean removeFileSystemListener(FileSystemListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean addJournalListener(FileSystemJournalListener listener, Application app, boolean weakRef) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean removeJournalListener(FileSystemJournalListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final FileSystemJournalEntry getJournalEntry(long usn) {
      return _fileSystem.getJournalEntryInternal(usn);
   }

   private final FileSystemJournalEntry getJournalEntryInternal(long usn) {
      synchronized (this._journalLock) {
         if (this._currentTail == -1) {
            return null;
         }

         if (usn > this._entryUSN[this._currentTail]) {
            return null;
         }

         int nextIndex = this._currentTail + 1;
         if (nextIndex >= 100) {
            nextIndex = 0;
         }

         if (usn < this._entryUSN[nextIndex]) {
            return null;
         }

         int index = (int)(usn % 100);
         if (this._entryCache[index] == null) {
            this._entryCache[index] = FileSystemJournalEntry.createEntry(usn, this._entryPath[index], this._entryOldPath[index], this._entryEvent[index]);
         }

         return this._entryCache[index];
      }
   }

   public static final long getNextJournalUSN() {
      return _fileSystem._nextUSN;
   }

   public static final void addFileJournalEntry(String path, int action) {
      _fileSystem.addFileJournalEntryInternal(path, path, action);
   }

   public static final void addFileJournalEntry(String path, String oldPath, int action) {
      _fileSystem.addFileJournalEntryInternal(path, oldPath, action);
   }

   public static final void suspendCommitOnDelete() {
      _fileSystem._commitOnDelete = false;
   }

   public static final void resumeCommitOnDelete() {
      _fileSystem._commitOnDelete = true;
   }

   private final void addFileJournalEntryInternal(String path, String oldPath, int action) {
      synchronized (this._journalLock) {
         this._currentTail++;
         if (this._currentTail >= 100) {
            this._currentTail = 0;
         }

         this._entryPath[this._currentTail] = path;
         this._entryOldPath[this._currentTail] = oldPath;
         this._entryUSN[this._currentTail] = this._nextUSN;
         this._entryEvent[this._currentTail] = action;
         this._entryCache[this._currentTail] = null;
         this._nextUSN += 1;
         this.notifyJournalChanged();
      }
   }

   private final void notifyRootChanged(int state, String rootName) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void notifyJournalChanged() {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isSupported() {
      return InternalServices.isSoftwareCapable(0);
   }

   public static final boolean isFileSystemSupported(int fs) {
      if (isSupported()) {
         try {
            return isFileSystemSupported0(fs);
         } catch (UnsupportedOperationException var2) {
         }
      }

      return false;
   }

   public static final void registerInputStream(int rootId, InputStream in) {
      boolean rimApp = ControlledAccess.verifyRRISignatures(true);
      if (rimApp) {
         Arrays.add(_fileSystem._openedRIMInputStreams[rootId], in);
      } else {
         Arrays.add(_fileSystem._openedInputStreams[rootId], in);
      }
   }

   public static final void registerOutputStream(int rootId, OutputStream out) {
      boolean rimApp = ControlledAccess.verifyRRISignatures(true);
      if (rimApp) {
         Arrays.add(_fileSystem._openedRIMOutputStreams[rootId], out);
      } else {
         Arrays.add(_fileSystem._openedOutputStreams[rootId], out);
      }
   }

   public static final void deregisterOutputStream(int rootId, OutputStream out) {
      Arrays.remove(_fileSystem._openedOutputStreams[rootId], out);
      Arrays.remove(_fileSystem._openedRIMOutputStreams[rootId], out);
   }

   public static final void deregisterInputStream(int rootId, InputStream in) {
      Arrays.remove(_fileSystem._openedInputStreams[rootId], in);
      Arrays.remove(_fileSystem._openedRIMInputStreams[rootId], in);
   }

   public static final long open(int fs, String name, int mode) {
      boolean rimApp = ControlledAccess.verifyRRISignatures(true);
      return !rimApp && _fileSystem._openedInputStreams[fs].length + _fileSystem._openedOutputStreams[fs].length >= 8 ? 11 : open0(fs, name, mode);
   }

   public static final int delete(int fs, String name) {
      int result = delete0(fs, name);
      if (result == 0 && _fileSystem._commitOnDelete) {
         commit(fs, 0);
      }

      return result;
   }

   public static final int rename(int fs, String oldName, String newName) {
      int result = rename0(fs, oldName, newName);
      if (result == 0) {
         commit(fs, 0);
      }

      return result;
   }

   public static final int mkdir(int fs, String name) {
      int result = mkdir0(fs, name);
      if (result == 0) {
         commit(fs, 0);
      }

      return result;
   }

   public static final int rmdir(int fs, String name) {
      int result = rmdir0(fs, name);
      if (result == 0) {
         commit(fs, 0);
      }

      return result;
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
