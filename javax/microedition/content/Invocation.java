package javax.microedition.content;

import javax.microedition.io.Connection;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ControlledAccess;
import net.rim.vm.TraceBack;

public final class Invocation {
   private int _status;
   private String _url;
   private String _type;
   private String _ID;
   private String _action;
   private boolean _responseRequired;
   private String[] _args;
   private byte[] _data;
   private String _invokingAuthority;
   private String _invokingID;
   private String _invokingAppName;
   private String _username;
   private char[] _password;
   private Invocation _previous;
   private Invocation _original;
   private Connection _connection;
   private ApplicationDescriptor _invokerDescriptor;
   private int[] _invokerStack;
   public static final int INIT;
   public static final int ACTIVE;
   public static final int WAITING;
   public static final int HOLD;
   public static final int OK;
   public static final int CANCELLED;
   public static final int ERROR;
   public static final int INITIATED;

   public Invocation() {
      this(null, null, null, true, null);
   }

   public Invocation(String var1, String var2, String var3) {
      this(var1, var2, var3, true, null);
   }

   public Invocation(String var1, String var2) {
      this(var1, var2, null, true, null);
   }

   public Invocation(String var1) {
      this(var1, null, null, true, null);
   }

   public Invocation(String var1, String var2, String var3, boolean var4, String var5) {
      this._url = var1;
      this._type = var2;
      this._ID = var3;
      this._responseRequired = var4;
      this._action = var5;
      this._status = 1;
      this._args = new String[0];
      this._data = new byte[0];
      this._previous = null;
   }

   Invocation(Invocation var1) {
   }

   public final void setArgs(String[] var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final String[] getArgs() {
      return this._args;
   }

   public final void setData(byte[] var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final byte[] getData() {
      return this._data;
   }

   public final String getURL() {
      return this._url;
   }

   public final void setURL(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getType() {
      return this._type;
   }

   public final void setType(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getAction() {
      return this._action;
   }

   public final void setAction(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean getResponseRequired() {
      return this._responseRequired;
   }

   public final void setResponseRequired(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getStatus() {
      return this._status;
   }

   final void setStatus(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getID() {
      return this._ID;
   }

   public final void setID(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final Invocation getPrevious() {
      return this._previous;
   }

   final Invocation getOriginal() {
      return this._original;
   }

   final void setInvokerInfo(String var1, String var2, String var3) {
      this._invokingAuthority = var1;
      this._invokingID = var2;
      this._invokingAppName = var3;
   }

   public final String getInvokingAuthority() {
      return this._status != 2 && this._status != 4 ? null : this._invokingAuthority;
   }

   public final String getInvokingID() {
      return this._status != 2 && this._status != 4 ? null : this._invokingID;
   }

   public final String getInvokingAppName() {
      return this._status != 2 && this._status != 4 ? null : this._invokingAppName;
   }

   public final String findType() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final Connection open(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setCredentials(String var1, char[] var2) {
      this._username = var1;
      this._password = var2;
   }

   final String getUsername() {
      return this._username;
   }

   final char[] getPassword() {
      return this._password;
   }

   final void setPrevious(Invocation var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   final void setInvokerStack(int[] var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._invokerStack = var1;
   }

   final void setInvokerDescriptor(ApplicationDescriptor var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._invokerDescriptor = var1;
   }

   final void populateStackAndDescriptor() {
      if (this._invokerDescriptor == null) {
         this.setInvokerDescriptor(ApplicationDescriptor.currentApplicationDescriptor());
      }

      if (this._invokerStack == null) {
         this.setInvokerStack(TraceBack.getCallingModules());
      }
   }

   final ApplicationDescriptor getInvokerDescriptor() {
      return this._invokerDescriptor;
   }

   final int[] getInvokerStack() {
      return this._invokerStack;
   }
}
