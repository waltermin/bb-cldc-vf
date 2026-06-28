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

   public TunnelConfig(String name, String description, TunnelListener listener) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = name;
      this._description = description;
      this._qos = null;
      this._username = null;
      this._password = null;
      this._priority = 5;
      this._listener = listener;
   }

   public TunnelConfig(String name, String description, QOSInfo qos, String username, String password, TunnelListener listener) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = name;
      this._description = description;
      this._qos = qos;
      this._username = username;
      this._password = password;
      this._priority = 5;
      this._listener = listener;
   }

   public TunnelConfig(
      String name,
      String description,
      QOSInfo qos,
      String username,
      String password,
      TunnelListener listener,
      int maxAttempts,
      int startBackoff,
      int maxBackoff,
      int activateTmo,
      int deactivateTmo,
      int delayTmo,
      int shortDelayTmo,
      int updateTmo
   ) {
      this._delayTmo = DEF_DELAY_TIMEOUT;
      this._shortDelayTmo = 10000;
      this._updateTmo = 0;
      this._lingerTmo = 300;
      this._name = name;
      this._description = description;
      this._qos = qos;
      this._username = username;
      this._password = password;
      this._priority = 5;
      this._listener = listener;
      this._maxAttempts = maxAttempts;
      this._startBackoff = startBackoff;
      this._maxBackoff = maxBackoff;
      this._activateTmo = activateTmo;
      this._deactivateTmo = deactivateTmo;
      this._delayTmo = delayTmo;
      this._shortDelayTmo = shortDelayTmo;
      this._updateTmo = updateTmo;
   }

   public final String getName() {
      return this._name;
   }

   public final void setName(String name) {
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

   public final void setPriority(int priority) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final TunnelListener getListener() {
      return this._listener;
   }

   public final boolean equivalent(Object object) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean equivalentQos(QOSInfo qos) {
      if (this._qos == null) {
         return qos == null ? true : qos.isDefaultQos();
      } else {
         return qos == null ? this._qos.isDefaultQos() : this._qos.equals(qos);
      }
   }

   public final int getMaxAttempts() {
      return this._maxAttempts;
   }

   public final void setMaxAttempts(int maxAttempts) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getBackoff(int index) {
      if (index > 31) {
         return this._maxBackoff;
      }

      long ret = ((long)1 << index) * this._startBackoff;
      return ret > this._maxBackoff ? this._maxBackoff : (int)ret;
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

   public final void setLingerTimeout(int lingerTimeout) {
      this._lingerTmo = Math.min(lingerTimeout, 1800);
   }
}
