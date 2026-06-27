package net.rim.device.internal.system;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.SIMCardEFListener;

public class SIMCardEfHandler implements Runnable, SIMCardEFListener {
   private int _efId;
   private int _code;
   private byte[] _buffer;
   private int _fileStatus;
   private int _fileStructure;
   private int _fileSize;
   private int _recordLength;
   private int _numRecords;
   private int _recordNumber;
   private int _state;
   private SIMCardEfTask _task;
   private SIMCardEfHandlerCallback _callback;
   private SIMCardEfHandler$PleaseWaitDialog _dialog;
   private Application _application;
   private Object _gate;
   private static final int STATE_IDLE;
   private static final int STATE_RUNNING;
   private static final int STATE_INFO_WAIT;
   private static final int STATE_READ_WAIT;
   private static final int STATE_WRITE_WAIT;

   public void startTask(SIMCardEfTask var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized int writeRequest(int var1, int var2, int var3, byte[] var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isRunning() {
      return this._task != null;
   }

   public int getReturnCode() {
      return this._code;
   }

   public int getFileStatus() {
      return this._fileStatus;
   }

   public int getFileStructure() {
      return this._fileStructure;
   }

   public int getFileSize() {
      return this._fileSize;
   }

   public int getRecordLength() {
      return this._recordLength;
   }

   public int getNumRecords() {
      return this._numRecords;
   }

   public synchronized int infoRequest(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized int readRequest(int var1, int var2, int var3, byte[] var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int readRequest(int var1, byte[] var2) {
      return this.readRequest(this._efId, this._fileStructure, var1, var2);
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void responseEFInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void responseEFRead(int var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void responseEFWrite(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public SIMCardEfHandler(SIMCardEfHandlerCallback var1) {
      this._callback = var1;
   }

   public SIMCardEfHandler() {
   }

   private void processResponse(int var1) {
      this._code = var1;
      super.notify();
   }

   private void waitForComplete() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void closeWaitingDialog() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
