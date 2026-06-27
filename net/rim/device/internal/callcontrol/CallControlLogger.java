package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.StringUtilities;
import net.rim.vm.Array;

public final class CallControlLogger {
   private CallControlLogger$CallEventLogger _myEventLogger;
   private CallControlLogger$CallCommandLogger _myCommandLogger;
   private final String _name;
   private final long _eventGUID;
   private byte[] _buf;
   private StringBuffer _strbuf;
   private int _strbufRootLen;
   private static final long LOGGER_GUID;
   private static final byte[] ACTIVATEBARRING;
   private static final byte[] ACTIVATEWAITING;
   private static final byte[] ADDED;
   private static final byte[] ADDTOCONF;
   private static final byte[] ANSWER;
   private static final byte[] CALLNAME;
   private static final byte[] CANHOLD;
   private static final byte[] CANSWAP;
   private static final byte[] CANINVOKEXFER;
   private static final byte[] CANJOIN;
   private static final byte[] CANPARK;
   private static final byte[] CANSENDTOVM;
   private static final byte[] CONNECTED;
   private static final byte[] DEACTIVATECALLFWD;
   private static final byte[] DELIVERED;
   private static final byte[] DISABLEDTMFECHO;
   private static final byte[] DISCONNECTED;
   private static final byte[] DTMFDATA;
   private static final byte[] ENABLEFDN;
   private static final byte[] END911CALLBACKMODE;
   private static final byte[] FAILED;
   private static final byte[] FALSE;
   private static final byte[] FEATUREREADY;
   private static final byte[] FLASH;
   private static final byte[] GETACTIVECALL;
   private static final byte[] GETCALLFWDNUMBER;
   private static final byte[] GETCALLNUMBER;
   private static final byte[] GETCALLSTATE;
   private static final byte[] GETCLIP;
   private static final byte[] GETDURATION;
   private static final byte[] GETEMERGENCYNUMBER;
   private static final byte[] GETFWDNUMBER;
   private static final byte[] GETHELD;
   private static final byte[] GETINCOMING;
   private static final byte[] GETLINE;
   private static final byte[] GETLINELABEL;
   private static final byte[] GETLINENUMBER;
   private static final byte[] GETLINES;
   private static final byte[] GETMAXCONFMEMBERS;
   private static final byte[] GETNETWORKFEATURES;
   private static final byte[] GETNUMBER;
   private static final byte[] GETVMCOUNT;
   private static final byte[] GETVMNUMBER;
   private static final byte[] GETWAFS;
   private static final byte[] GETXFERSTATE;
   private static final byte[] HELD;
   private static final byte[] HOLD;
   private static final byte[] INCALLDTMF;
   private static final byte[] INCOMING;
   private static final byte[] INITIATED;
   private static final byte[] ISACTIVE;
   private static final byte[] ISCFUACTIVE;
   private static final byte[] ISEMERGENCYNUMBER;
   private static final byte[] ISFDNAVAILABLE;
   private static final byte[] ISFDNENABLED;
   private static final byte[] ISLINEAVAILABLE;
   private static final byte[] ISREDIRECTED;
   private static final byte[] LINECHANGED;
   private static final byte[] LINESUPDATED;
   private static final byte[] MANIPULATEFAILED;
   private static final byte[] NULL;
   private static final byte[] OTAUPDATED;
   private static final byte[] PARK;
   private static final byte[] PRIVACY;
   private static final byte[] QUERYSSOPTION;
   private static final byte[] QUERYSSOPTIONRESULT;
   private static final byte[] REJECTCALL;
   private static final byte[] REMOVECALL;
   private static final byte[] REMOVED;
   private static final byte[] REQUESTENABLEFDN;
   private static final byte[] RESULT;
   private static final byte[] RESUMECALL;
   private static final byte[] RESUMED;
   private static final byte[] SENDSSPWDRESPONSE;
   private static final byte[] SENDTOVM;
   private static final byte[] SETBARRINGPWD;
   private static final byte[] SETFWDNUMBER;
   private static final byte[] SETLINE;
   private static final byte[] SETLINELABEL;
   private static final byte[] SETSSBASICSERVICE;
   private static final byte[] SETUSSDRESPONSE;
   private static final byte[] SSNOTIFY;
   private static final byte[] SSPWDREQUESTED;
   private static final byte[] SSRQFAIL;
   private static final byte[] SSRQINVALIDPWD;
   private static final byte[] SSRQREJECTED;
   private static final byte[] SSRQRELEASED;
   private static final byte[] SSRQSUCCESS;
   private static final byte[] SSUPDATED;
   private static final byte[] SSUSSD;
   private static final byte[] STARTCALL;
   private static final byte[] STARTDTMF;
   private static final byte[] STOPALLCALLS;
   private static final byte[] STOPCALL;
   private static final byte[] STOPDTMF;
   private static final byte[] SUPPORTSEXT;
   private static final byte[] SWAP;
   private static final byte[] TIMER;
   private static final byte[] TRUE;
   private static final byte[] UPDATED;
   private static final byte[] VMCOUNTUPDATED;
   private static final byte[] WAITING;
   private static final byte[] XFER;
   private static final byte[] XFERACTION;
   private static final byte[] XFERUPDATED;

   public CallControlLogger(String var1, int var2) {
   }

   private final void log(int var1) {
      Array.resize(this._buf, var1);
      this.log(this._buf);
   }

   private final void log(byte[] var1) {
      EventLogger.logEvent(this._eventGUID, var1);
      this._strbuf.setLength(this._strbufRootLen);
      StringUtilities.append(this._strbuf, var1, 0, var1.length);
      System.out.println(this._strbuf);
   }

   private final int copy(byte[] var1) {
      Array.resize(this._buf, 64);
      int var2 = var1.length;
      System.arraycopy(var1, 0, this._buf, 0, var2);
      return var2;
   }

   private final int append(int var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int append(int var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int append(int var1, char var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int append(int var1, boolean var2) {
      return this.append(var1, var2 ? TRUE : FALSE);
   }

   private final int append(int var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final synchronized void logEvent(int var1, byte[] var2, boolean var3) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var4 = this.copy(var2);
         var4 = this.append(var4, var3);
         this.log(var4);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2) {
      if (EventLogger.getMinimumLevel() >= var1) {
         this.log(var2);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var4 = this.copy(var2);
         var4 = this.append(var4, var3);
         this.log(var4);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, Object var3) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var4 = this.copy(var2);
         var4 = this.append(var4, var3);
         this.log(var4);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, byte[] var3) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var4 = this.copy(var2);
         var4 = this.append(var4, var3);
         this.log(var4);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, int var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, boolean var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, Object var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, Object var3, int var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, Object var3, Object var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, byte[] var3, byte[] var4) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var5 = this.copy(var2);
         var5 = this.append(var5, var3);
         var5 = this.append(var5, var4);
         this.log(var5);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, int var4, int var5) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var6 = this.copy(var2);
         var6 = this.append(var6, var3);
         var6 = this.append(var6, var4);
         var6 = this.append(var6, var5);
         this.log(var6);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, boolean var3, int var4, Object var5) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var6 = this.copy(var2);
         var6 = this.append(var6, var3);
         var6 = this.append(var6, var4);
         var6 = this.append(var6, var5);
         this.log(var6);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, int var4, Object var5) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var6 = this.copy(var2);
         var6 = this.append(var6, var3);
         var6 = this.append(var6, var4);
         var6 = this.append(var6, var5);
         this.log(var6);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, byte[] var3, int var4, boolean var5) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var6 = this.copy(var2);
         var6 = this.append(var6, var3);
         var6 = this.append(var6, var4);
         var6 = this.append(var6, var5);
         this.log(var6);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, int var4, boolean var5) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var6 = this.copy(var2);
         var6 = this.append(var6, var3);
         var6 = this.append(var6, var4);
         var6 = this.append(var6, var5);
         this.log(var6);
      }
   }

   private final synchronized void logEvent(int var1, byte[] var2, int var3, int var4, int var5, int var6, boolean var7, boolean var8) {
      if (EventLogger.getMinimumLevel() >= var1) {
         int var9 = this.copy(var2);
         var9 = this.append(var9, var3);
         var9 = this.append(var9, var4);
         var9 = this.append(var9, var5);
         var9 = this.append(var9, var6);
         var9 = this.append(var9, var7);
         var9 = this.append(var9, var8);
         this.log(var9);
      }
   }

   private static final String obfuscate(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
