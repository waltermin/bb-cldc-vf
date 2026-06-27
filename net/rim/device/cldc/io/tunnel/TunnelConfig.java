package net.rim.device.cldc.io.tunnel;

import net.rim.device.api.system.QOSInfo;

public final class TunnelConfig {
   private String _name;
   private final String _description;
   private final QOSInfo _qos;
   private final String _username;
   private final String _password;
   private final TunnelListener _listener;
   private int _priority;
   private int _maxAttempts = Integer.MAX_VALUE;
   private int _startBackoff = 15000;
   private int _maxBackoff = 900000;
   private int _activateTmo = 155000;
   private int _deactivateTmo = 60000;
   private int _delayTmo;
   private int _shortDelayTmo;
   private int _updateTmo;
   private int _lingerTmo;
   public static final int PRIORITY_HIGH;
   public static final int PRIORITY_NORMAL;
   public static final int PRIORITY_LOW;
   public static final int DEF_MAX_ATTEMPTS;
   public static final int DEF_START_BACKOFF;
   public static final int DEF_MAX_BACKOFF;
   public static final int DEF_ACTIVATE_TIMEOUT;
   public static final int DEF_DEACTIVATE_TIMEOUT;
   public static final int DEF_DELAY_TIMEOUT;
   public static final int DEF_SHORT_DELAY_TIMEOUT;
   public static final int DEF_UPDATE_TIMEOUT;
   public static final int DEF_LINGER_TIMEOUT;

   public TunnelConfig(String var1, String var2, TunnelListener var3) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = var1;
      this._description = var2;
      this._qos = null;
      this._username = null;
      this._password = null;
      this._priority = 5;
      this._listener = var3;
   }

   public TunnelConfig(String var1, String var2, QOSInfo var3, String var4, String var5, TunnelListener var6) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = var1;
      this._description = var2;
      this._qos = var3;
      this._username = var4;
      this._password = var5;
      this._priority = 5;
      this._listener = var6;
   }

   public TunnelConfig(
      String var1,
      String var2,
      QOSInfo var3,
      String var4,
      String var5,
      TunnelListener var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      int var14
   ) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = var1;
      this._description = var2;
      this._qos = var3;
      this._username = var4;
      this._password = var5;
      this._priority = 5;
      this._listener = var6;
      this._maxAttempts = var7;
      this._startBackoff = var8;
      this._maxBackoff = var9;
      this._activateTmo = var10;
      this._deactivateTmo = var11;
      this._delayTmo = var12;
      this._shortDelayTmo = var13;
      this._updateTmo = var14;
   }

   public final String getName() {
      return this._name;
   }

   public final void setName(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getDescription() {
      return this._description;
   }

   public final QOSInfo getQos() {
      return this._qos;
   }

   public final String getUsername() {
      return this._username;
   }

   public final String getPassword() {
      return this._password;
   }

   public final int getPriority() {
      return this._priority;
   }

   public final void setPriority(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final TunnelListener getListener() {
      return this._listener;
   }

   public final boolean equivalent(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean equivalentQos(QOSInfo var1) {
      if (this._qos == null) {
         return var1 == null ? true : var1.isDefaultQos();
      } else {
         return var1 == null ? this._qos.isDefaultQos() : this._qos.equals(var1);
      }
   }

   public final int getMaxAttempts() {
      return this._maxAttempts;
   }

   public final void setMaxAttempts(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getBackoff(int var1) {
      if (var1 > 31) {
         return this._maxBackoff;
      }

      long var2 = ((long)1 << var1) * this._startBackoff;
      return var2 > this._maxBackoff ? this._maxBackoff : (int)var2;
   }

   public final int getActivateTimeout() {
      return this._activateTmo;
   }

   public final int getDeactivateTimeout() {
      return this._deactivateTmo;
   }

   public final int getDelayTimeout() {
      return this._delayTmo;
   }

   public final int getShortDelayTimeout() {
      return this._shortDelayTmo;
   }

   public final int getUpdateTimeout() {
      return this._updateTmo;
   }

   public final int getLingerTimeout() {
      return this._lingerTmo;
   }

   public final void setLingerTimeout(int var1) {
      this._lingerTmo = Math.min(var1, 1800);
   }
}
