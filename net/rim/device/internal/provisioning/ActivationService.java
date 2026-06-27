package net.rim.device.internal.provisioning;

import java.util.Hashtable;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMPersistentStore;
import net.rim.device.api.util.DateTimeUtilities;
import net.rim.device.api.util.LongIntHashtable;

public class ActivationService {
   public Hashtable _listeners = (Hashtable)(new Object(1));
   protected static final long ACTIVATION_SERVICE_ID;
   protected static final long ACTIVATION_KEY_ID;
   public static final byte OTAKEYGEN_ABORT_REASON_NONE;
   public static final byte OTAKEYGEN_ABORT_REASON_GENERAL;
   public static final byte OTAKEYGEN_ABORT_REASON_NO_LONGTERM_KEY;
   public static final byte OTAKEYGEN_ABORT_REASON_SERVICE_RESET;
   public static final byte OTAKEYGEN_ABORT_REASON_MAX_RETRY;
   public static final byte OTAKEYGEN_ABORT_REASON_KEY_VALIDATION;
   public static final byte OTAKEYGEN_ABORT_REASON_USER_ALREADY_ACTIVE;
   public static final byte OTAKEYGEN_ABORT_REASON_USER;
   public static final byte OTAKEYGEN_ABORT_REASON_TIMEOUT;
   public static final byte OTAKEYGEN_ABORT_REASON_NO_PASSWORD;
   public static final byte OTAKEYGEN_ABORT_PIN_ALREADY_REGISTERED;
   public static final byte OTAKEYGEN_ABORT_ITPOLICY_REJECTED;
   public static final byte OTAKEYGEN_ABORT_NOT_AUTHORIZED;
   public static final byte OTAKEYGEN_ABORT_REASON_SEND_ERROR;
   public static final String PROVISIONING_CID;

   public int attemptActivation(String var1, String var2, String var3) {
      throw null;
   }

   public void abortTransaction(int var1, byte var2) {
      throw null;
   }

   public void abortTransaction(String var1, byte var2) {
      throw null;
   }

   public int regenerateKey(String var1, boolean var2) {
      throw null;
   }

   public String getUIDbyEmailAddress(String var1) {
      throw null;
   }

   public boolean isActivationPending(String var1) {
      throw null;
   }

   public boolean isTransactionInProgress(String var1) {
      throw null;
   }

   public boolean isAnyTransactionInProgress() {
      throw null;
   }

   public String[][][] getRegenerationUIDs() {
      throw null;
   }

   public static ActivationService getInstance() {
      return (ActivationService)ApplicationRegistry.getApplicationRegistry().waitFor(-1320069024724775836L);
   }

   public static ActivationService getInstanceNoWait() {
      return (ActivationService)ApplicationRegistry.getApplicationRegistry().get(-1320069024724775836L);
   }

   public static boolean isActivationServiceAvailable() {
      return ApplicationRegistry.getApplicationRegistry().get(-1320069024724775836L) != null;
   }

   public static boolean hasThisDeviceBeenActivated() {
      return getActivatedServices().length > 0;
   }

   public static long[] getActivatedServices() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static void activationComplete(boolean var0, long var1) {
      getOrUpdateHashtable(var1, var0 ? System.currentTimeMillis() : 0);
   }

   protected static void activationComplete(long var0, long var2) {
      getOrUpdateHashtable(var2, var0);
   }

   protected static void serviceIdChanged(long var0, long var2) {
      PersistentObject var4 = RIMPersistentStore.getPersistentObject(6247846804872834637L);
      Object var5 = var4.getContents();
      if (var5 != null) {
         if (((LongIntHashtable)var5).containsKey(var0)) {
            int var6 = ((LongIntHashtable)var5).get(var0);
            ((LongIntHashtable)var5).remove(var0);
            ((LongIntHashtable)var5).put(var2, var6);
         }

         var4.setContents(var5, 51);
         var4.commit();
      }
   }

   private static LongIntHashtable getOrUpdateHashtable(long var0, long var2) {
      PersistentObject var4 = RIMPersistentStore.getPersistentObject(6247846804872834637L);
      Object var5 = var4.getContents();
      if (var5 == null) {
         var5 = new Object();
      }

      if (var0 != -1) {
         if (var2 > 0) {
            ((LongIntHashtable)var5).put(var0, DateTimeUtilities.convertMillisecondsToEpoch(var2));
         } else {
            ((LongIntHashtable)var5).remove(var0);
         }

         var4.setContents(var5, 51);
         var4.commit();
      }

      return (LongIntHashtable)var5;
   }

   public static long getLastSuccessfulActivationDate(long var0) {
      int var2 = getOrUpdateHashtable(-1, 0).get(var0);
      return var2 > 0 ? DateTimeUtilities.convertEpochToMilliseconds(var2) : 0;
   }

   public void createOtaKeyGenSR(String var1, String var2, String var3) {
      throw null;
   }

   public void resetOtaKeyGenSR() {
      throw null;
   }

   public SyncCollection[] getCollections() {
      throw null;
   }

   public boolean isActivationInProgress() {
      throw null;
   }

   public boolean addActivationStatusListener(ActivationStatusListener var1) {
      if (!this._listeners.containsKey(var1.getCollectionName())) {
         this._listeners.put(var1.getCollectionName(), var1);
         return true;
      } else {
         return false;
      }
   }

   public void removeActivationStatusListener(ActivationStatusListener var1) {
      this._listeners.remove(var1.getCollectionName());
   }

   protected void notifyStatusListener(String var1, boolean var2) {
      if (this._listeners.containsKey(var1)) {
         ((ActivationStatusListener)this._listeners.get(var1)).activationComplete(var2);
      }
   }
}
