package net.rim.device.internal.system;

import java.util.Vector;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.system.USBPortListener2;

public class USBPasswordRedirectManager implements SystemListener2, USBPortListener2, GlobalEventListener {
   private Vector _channels;
   private Security _security;
   private boolean _allowRedirects;
   private Vector _disallowedChannels;
   private int _initialRadioState;
   private boolean _isRadioAllowedOn;
   private boolean _bluetoothWasOn;
   private static final long GUID;
   public static final long EVENT_LOGGER_GUID;
   public static final String EVENT_LOGGER_NAME;
   public static final int EVENT_REGISTERED;
   public static final int EVENT_CONNECTION_AUTHENTICATION;
   public static final int EVENT_CHANNEL_ALLOWED;
   public static final int EVENT_CHANNEL_WAITING;
   public static final int EVENT_CHANNEL_PROMPT;
   public static final int EVENT_CHANNEL_DISCONNECTED;
   public static final int EVENT_CABLE_CONNECTED;
   public static final int EVENT_USB_ENUMERATED;
   public static final int EVENT_CABLE_DISCONNECTED;
   public static final int EVENT_GET_CHANNEL_NAME_ERROR;
   public static final int EVENT_CHANNEL_PROMPT_ERROR;
   public static final int EVENT_ALLOW_REDIRECTS;
   public static final int EVENT_DISALLOW_REDIRECTS;
   public static final int EVENT_CLEAR_CHANNELS;
   public static final int EVENT_NOCLEAR_CHANNELS;
   public static final int EVENT_ALLOW_CHANNEL_ERROR;
   public static final int EVENT_RADIO_POWER_ON;
   public static final int EVENT_RADIO_POWER_OFF;
   public static final int EVENT_ALLOW_CHANNEL;
   public static final int EVENT_DISALLOW_CHANNEL;
   public static final int EVENT_CANCEL;
   public static final int EVENT_DISMISS;
   public static final int EVENT_OLD_OS;
   public static final int EVENT_NEW_OS;
   private static USBPasswordRedirectManager _passwordManager;
   private static String _knownPassword;
   private static final boolean DEBUG;

   public Vector getAllChannels() {
      return this._channels;
   }

   public void addToDisallowedChannels(String var1) {
      if (!this._disallowedChannels.contains(var1)) {
         this._disallowedChannels.addElement(var1);
      }
   }

   public boolean isRadioAllowedOn() {
      return this._isRadioAllowedOn;
   }

   public synchronized void allowRedirects(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void clearChannels(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void allowChannel(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void connectionAuthenticationRequired(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public synchronized void disconnected(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int getChannel() {
      return -1;
   }

   @Override
   public void disconnected() {
   }

   @Override
   public void receiveError(int var1) {
   }

   @Override
   public void dataReceived(int var1) {
   }

   @Override
   public void dataSent() {
   }

   @Override
   public void patternReceived(byte[] var1) {
   }

   @Override
   public void dataNotSent() {
   }

   @Override
   public void connectionRequested() {
   }

   @Override
   public void connected() {
   }

   @Override
   public void usbConnectionStateChange(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void powerOff() {
   }

   @Override
   public void powerUp() {
   }

   @Override
   public void batteryLow() {
   }

   @Override
   public void batteryGood() {
   }

   @Override
   public void batteryStatusChange(int var1) {
   }

   @Override
   public void powerOffRequested(int var1) {
   }

   @Override
   public void cradleMismatch(boolean var1) {
   }

   @Override
   public void fastReset() {
   }

   @Override
   public void backlightStateChange(boolean var1) {
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 8508406279413621091L || var1 == -594020114676189989L) {
         int var7 = USBPortInternal.getConnectionState();
         if (var7 != -1) {
            this.checkState(var7);
         }
      }
   }

   private USBPasswordRedirectManager() {
   }

   private void enableRadio() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void disableRadio() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void connectionAuthenticationRequiredPrivate(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void checkState(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static USBPasswordRedirectManager getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void initialize() {
      getInstance();
   }

   public static void logEvent(int var0) {
      EventLogger.logEvent(-2691377221057526315L, var0, 0);
   }
}
