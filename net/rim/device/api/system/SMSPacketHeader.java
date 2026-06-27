package net.rim.device.api.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.internal.system.RadioInternal;

public final class SMSPacketHeader extends SMSParameters implements RadioPacketHeader {
   private boolean _useDefaultProtocolId;
   private boolean _useDefaultMessageCoding;
   private boolean _useDefaultMessageClass;
   private boolean _userDataHeaderPresent;
   private boolean _useDefaultValidityPeriod;
   private boolean _useDefaultDeliveryPeriod;
   private boolean _statusReportRequest;
   private boolean _waitingIndValid;
   private boolean _waitingIndStore;
   private boolean _waitingIndActive;
   private int _waitingIndType;
   private int _numMessages;
   private int _id;
   private int _recordStatus;
   private long _timestamp;
   private boolean _fromSIM;
   private boolean _replyPath;
   public static final int WAITING_INDICATOR_TYPE_VOICEMAIL;
   public static final int WAITING_INDICATOR_TYPE_FAX;
   public static final int WAITING_INDICATOR_TYPE_EMAIL;
   public static final int WAITING_INDICATOR_TYPE_OTHER;
   public static final String[] INDICATOR_TYPE_NAMES;
   public static final int RECORD_STATUS_UNREAD;
   public static final int RECORD_STATUS_MS_ORIGINATED;
   public static final int RECORD_STATUS_REPORT_REQUESTED;
   public static final int RECORD_STATUS_REPORT_RECEIVED;
   public static final int SMS_ID_NOT_ON_SIM;
   private static int _maxPacketBits;
   private static final int SEGMENT_HEADER_BYTES;
   private static final int SEGMENT_HEADER_BITS;

   public SMSPacketHeader() {
      this.reset();
   }

   @Override
   public final void reset() {
      super.reset();
      this._useDefaultProtocolId = true;
      this._useDefaultMessageCoding = true;
      this._useDefaultMessageClass = true;
      this._userDataHeaderPresent = false;
      this._useDefaultValidityPeriod = true;
      this._useDefaultDeliveryPeriod = true;
      this._statusReportRequest = false;
      this._waitingIndValid = false;
      this._fromSIM = false;
      this._replyPath = false;
      this._timestamp = 0;
      _maxPacketBits = 0;
   }

   public final void setProtocolId(int var1, int var2) {
      this._useDefaultProtocolId = false;
      super.setProtocolMeaning(var1);
      super.setProtocolId(var2);
   }

   @Override
   public final void setMessageCoding(int var1) {
      this._useDefaultMessageCoding = false;
      super.setMessageCoding(var1);
   }

   @Override
   public final void setMessageClass(int var1) {
      this._useDefaultMessageClass = false;
      super.setMessageClass(var1);
   }

   public final boolean isUserDataHeaderPresent() {
      return this._userDataHeaderPresent;
   }

   public final void setUserDataHeaderPresent(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final void setValidityPeriod(int var1) {
      this._useDefaultValidityPeriod = false;
      super.setValidityPeriod(var1);
   }

   public final boolean isValidityPeriodDefault() {
      return this._useDefaultValidityPeriod;
   }

   @Override
   public final void setDeliveryPeriod(int var1) {
      this._useDefaultDeliveryPeriod = false;
      super.setDeliveryPeriod(var1);
   }

   public final boolean isDeliveryPeriodDefault() {
      return this._useDefaultDeliveryPeriod;
   }

   public final boolean getStatusReportRequest() {
      return this._statusReportRequest;
   }

   public final void setStatusReportRequest(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean isMessageWaitingGroup() {
      return this._waitingIndValid;
   }

   public final boolean isMessageWaitingStore() {
      return this._waitingIndStore;
   }

   public final boolean isMessageWaitingActive() {
      return this._waitingIndActive;
   }

   public final int getMessageWaitingType() {
      return this._waitingIndType;
   }

   public final void setMessageWaitingType(int var1) {
      this._waitingIndValid = true;
      this._waitingIndType = var1;
   }

   public final int getNumMessages() {
      return this._numMessages;
   }

   public final void setNumMessages(int var1) {
      this._waitingIndValid = true;
      this._numMessages = var1;
   }

   public final int getID() {
      return this._id;
   }

   public final long getTimestamp() {
      return this._timestamp;
   }

   public final int getRecordStatus() {
      return this._recordStatus;
   }

   public final boolean isFromSIMCard() {
      return this._fromSIM;
   }

   public final boolean isReplyPath() {
      return this._replyPath;
   }

   public static final boolean isMessageCodingSupported(int var0, int var1) {
      switch (var1) {
         case -1:
         case 1:
         case 3:
            return false;
         case 0:
         case 2:
         case 6:
         default:
            if ((var0 & 1) != 0) {
               return true;
            }

            return false;
         case 4:
         case 5:
            return (var0 & 2) != 0;
      }
   }

   public static final boolean isSendSupported() {
      return RadioInfo.areWAFsSupported(3) ? ITPolicy.getBoolean(15, true) : false;
   }

   public static final boolean isSegmentationSupported() {
      return (RadioInfo.getActiveWAFs() & 1) != 0;
   }

   public static final int getBitsPerCharacter(int var0) {
      switch (var0) {
         case -1:
         case 3:
            throw new Object();
         case 0:
         case 4:
         default:
            return 7;
         case 1:
         case 5:
            return 8;
         case 2:
         case 6:
            return 16;
      }
   }

   public static final int getBytesPerCharacter(int var0) {
      switch (var0) {
         case -1:
         case 3:
            throw new Object();
         case 0:
         case 1:
         case 4:
         case 5:
         default:
            return 1;
         case 2:
         case 6:
            return 2;
      }
   }

   private static final int getMaxPacketBits() {
      if (_maxPacketBits == 0) {
         _maxPacketBits = RadioInternal.getMaxSMSPacketSize() * 8;
      }

      return _maxPacketBits;
   }

   public static final int getBitsPerSegment(int var0) {
      return getBitsPerSegment(var0, 0);
   }

   public static final int getBitsPerSegment(int var0, int var1) {
      int var2 = getMaxPacketBits() - 48 - var1 * 8;
      if (var0 == 0) {
         var2 -= 7 - (48 + var1 * 8) % 7;
      }

      return var2;
   }

   public static final int getBitsPerSegmentCDMA(int var0, int var1) {
      int var2 = getMaxPacketBits() - var1 * 8;
      if (var0 == 0) {
         var2 -= 7 - var1 * 8 % 7;
      }

      return var2;
   }

   public static final int getSegments(int var0, int var1) {
      return getSegments(var0, var1, 0);
   }

   public static final int getSegments(int var0, int var1, int var2) {
      int var3 = getMaxPacketBits() - var2 * 8;
      int var4 = var0 * getBitsPerCharacter(var1);
      if (var4 <= var3) {
         return 1;
      }

      int var5 = getBitsPerSegment(var1, var2);
      int var6 = var4 / var5;
      if (var4 % var5 != 0) {
         var6++;
      }

      return var6;
   }

   public static final int getSegmentsCDMA(int var0, int var1, int var2) {
      int var3 = getMaxPacketBits() - var2 * 8;
      int var4 = var0 * getBitsPerCharacter(var1);
      if (var4 <= var3) {
         return 1;
      }

      int var5 = getBitsPerSegmentCDMA(var1, var2);
      int var6 = var4 / var5;
      if (var4 % var5 != 0) {
         var6++;
      }

      return var6;
   }

   public static final int getCharacters(int var0, int var1) {
      int var2 = getBitsPerCharacter(var1);
      if (var0 == 1) {
         return getMaxPacketBits() / var2;
      }

      int var3 = getBitsPerSegment(var1);
      return var3 * var0 / var2;
   }

   public static final boolean validateForMessageCoding(char var0, int var1) {
      switch (var1) {
         case -1:
         case 3:
         case 4:
            return validateForASCIIMessageCoding(var0);
         case 0:
         default:
            return validateForDefaultMessageCoding(var0);
         case 1:
         case 5:
            return validateForISO8859MessageCoding(var0);
         case 2:
         case 6:
            return validateForUCS2MessageCoding(var0);
      }
   }

   public static final boolean validateForDefaultMessageCoding(char var0) {
      switch (var0) {
         case '\n':
         case '\f':
         case '\r':
         case ' ':
         case '!':
         case '"':
         case '#':
         case '$':
         case '%':
         case '&':
         case '\'':
         case '(':
         case ')':
         case '*':
         case '+':
         case ',':
         case '-':
         case '.':
         case '/':
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case ':':
         case ';':
         case '<':
         case '=':
         case '>':
         case '?':
         case '@':
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case 'a':
         case 'b':
         case 'c':
         case 'd':
         case 'e':
         case 'f':
         case 'g':
         case 'h':
         case 'i':
         case 'j':
         case 'k':
         case 'l':
         case 'm':
         case 'n':
         case 'o':
         case 'p':
         case 'q':
         case 'r':
         case 's':
         case 't':
         case 'u':
         case 'v':
         case 'w':
         case 'x':
         case 'y':
         case 'z':
         case '{':
         case '|':
         case '}':
         case '~':
         case '¡':
         case '£':
         case '¤':
         case '¥':
         case '§':
         case '¿':
         case 'Ä':
         case 'Å':
         case 'Æ':
         case 'Ç':
         case 'É':
         case 'Ñ':
         case 'Ö':
         case 'Ø':
         case 'Ü':
         case 'ß':
         case 'à':
         case 'ä':
         case 'å':
         case 'æ':
         case 'è':
         case 'é':
         case 'ì':
         case 'ñ':
         case 'ò':
         case 'ö':
         case 'ø':
         case 'ù':
         case 'ü':
         case 'Γ':
         case 'Δ':
         case 'Θ':
         case 'Λ':
         case 'Ξ':
         case 'Π':
         case 'Σ':
         case 'Φ':
         case 'Ψ':
         case 'Ω':
         case '€':
         case '⌣':
            return true;
         default:
            return false;
      }
   }

   public static final boolean validateForISO8859MessageCoding(char var0) {
      return var0 <= 255;
   }

   public static final boolean validateForASCIIMessageCoding(char var0) {
      return var0 <= 127;
   }

   public static final boolean validateForUCS2MessageCoding(char var0) {
      return true;
   }
}
