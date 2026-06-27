package net.rim.device.cldc.io.comm;

import com.sun.cldc.io.ConnectionBaseInterface;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.StreamConnection;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.IOPort;
import net.rim.device.api.system.USBPortListener;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.USBPortInternal;

public final class Protocol implements StreamConnection, USBPortListener, ConnectionBaseInterface {
   private InputStream _in;
   private OutputStream _out;
   private IOPort _port;
   private Protocol$Semaphore _readSemaphore = new Protocol$Semaphore();
   private Protocol$Semaphore _writeSemaphore = new Protocol$Semaphore();
   private IOException _exception;
   private int _channel;
   static final int BUFFER_SIZE;

   @Override
   public final void close() {
      ApplicationControl.assertLocalConnectionAllowed(true);
      Application.getApplication().removeIOPortListener(this);
      if (this._port != null) {
         this._port.close();
         this._port = null;
      }

      USBPortInternal.deregisterChannel(this._channel);
      this._exception = null;
   }

   @Override
   public final Connection openPrim(String var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getProperties(String var1) {
      return 128;
   }

   final void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final int read(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final OutputStream openOutputStream() {
      if (this._out == null) {
         this._out = new IOPortOutputStream(this);
      }

      return this._out;
   }

   @Override
   public final DataOutputStream openDataOutputStream() {
      return (DataOutputStream)(new Object(this.openOutputStream()));
   }

   @Override
   public final DataInputStream openDataInputStream() {
      return (DataInputStream)(new Object(this.openInputStream()));
   }

   @Override
   public final InputStream openInputStream() {
      if (this._in == null) {
         this._in = new IOPortInputStream(this);
      }

      return this._in;
   }

   @Override
   public final void connected() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void disconnected() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void receiveError(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dataReceived(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dataSent() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void patternReceived(byte[] var1) {
   }

   @Override
   public final int getChannel() {
      return this._channel;
   }

   @Override
   public final void dataNotSent() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void connectionRequested() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
