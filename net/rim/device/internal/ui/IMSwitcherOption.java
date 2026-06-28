package net.rim.device.internal.ui;

import java.io.EOFException;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.OTASyncCapableSyncItem;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;

public final class IMSwitcherOption extends OTASyncCapableSyncItem {
   private byte _state;
   private PersistentObject _store;
   public static final byte SHOW_ALWAYS;
   public static final byte DONT_SHOW;
   public static final byte DISABLED;
   public static final long REGISTRY_ID;
   public static final long PERSISTENCE_ID;
   private static IMSwitcherOption _instance;
   private static final int DB_VERSION;

   public static final IMSwitcherOption getInstance() {
      return _instance;
   }

   private IMSwitcherOption() {
   }

   @Override
   public final String getSyncName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final String getSyncName(Locale locale) {
      return null;
   }

   @Override
   public final int getSyncVersion() {
      return 0;
   }

   @Override
   public final synchronized boolean setSyncData(DataBuffer buffer, int version) {
      try {
         buffer.readShort();
         buffer.readByte();
         this._state = buffer.readByte();
         return true;
      } catch (EOFException var4) {
         return true;
      }
   }

   @Override
   public final synchronized boolean getSyncData(DataBuffer buffer, int version) {
      buffer.writeShort(1);
      buffer.writeByte(0);
      buffer.writeByte(this._state);
      return true;
   }

   public final void setState(byte state) {
      if (this._state != state && state != 0) {
         this._state = state;
         this._store.setContents(new Object(this._state), 51);
         this._store.commit();
         this.fireSyncItemUpdated();
      }
   }

   public final byte getState() {
      return this._state;
   }
}
