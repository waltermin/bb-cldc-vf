package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.NumberUtilities;
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

   public CallControlLogger(String name, int order) {
   }

   private final void log(int len) {
      Array.resize(this._buf, len);
      this.log(this._buf);
   }

   private final void log(byte[] buf) {
      EventLogger.logEvent(this._eventGUID, buf);
      this._strbuf.setLength(this._strbufRootLen);
      StringUtilities.append(this._strbuf, buf, 0, buf.length);
      System.out.println(this._strbuf);
   }

   private final int copy(byte[] arg) {
      Array.resize(this._buf, 64);
      int argLen = arg.length;
      System.arraycopy(arg, 0, this._buf, 0, argLen);
      return argLen;
   }

   private final int append(int offset, byte[] arg) {
      try {
         if (arg == null) {
            arg = NULL;
         }

         this._buf[offset++] = 44;
         int argLen = arg.length;
         System.arraycopy(arg, 0, this._buf, offset, argLen);
         return offset + argLen;
      } catch (IndexOutOfBoundsException ioobe) {
         return offset;
      } catch (Exception e) {
         return offset;
      }
   }

   private final int append(int offset, long arg) {
      try {
         this._buf[offset++] = 44;
         int argLen = NumberUtilities.appendNumber(offset, this._buf, arg, 10);
         return offset + argLen;
      } catch (IndexOutOfBoundsException ioobe) {
         return offset;
      } catch (Exception e) {
         return offset;
      }
   }

   private final int append(int offset, char arg) {
      try {
         this._buf[offset++] = (byte)arg;
         return offset;
      } catch (IndexOutOfBoundsException ioobe) {
         return offset;
      } catch (Exception e) {
         return offset;
      }
   }

   private final int append(int offset, boolean arg) {
      return this.append(offset, arg ? TRUE : FALSE);
   }

   private final int append(int offset, Object arg) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final synchronized void logEvent(int level, byte[] argA, boolean argB) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA) {
      if (EventLogger.getMinimumLevel() >= level) {
         this.log(argA);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, Object argB) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, byte[] argB) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, int argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, boolean argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, Object argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, Object argB, int argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, Object argB, Object argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, byte[] argB, byte[] argC) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, int argC, int argD) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, boolean argB, int argC, Object argD) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, int argC, Object argD) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, byte[] argB, int argC, boolean argD) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, int argC, boolean argD) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         this.log(len);
      }
   }

   private final synchronized void logEvent(int level, byte[] argA, int argB, int argC, int argD, int argE, boolean argF, boolean argG) {
      if (EventLogger.getMinimumLevel() >= level) {
         int len = this.copy(argA);
         len = this.append(len, argB);
         len = this.append(len, argC);
         len = this.append(len, argD);
         len = this.append(len, argE);
         len = this.append(len, argF);
         len = this.append(len, argG);
         this.log(len);
      }
   }

   private static final String obfuscate(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
