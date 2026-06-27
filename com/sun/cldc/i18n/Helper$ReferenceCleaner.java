package com.sun.cldc.i18n;

import java.util.Vector;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.api.util.WeakReferenceUtilities;

class Helper$ReferenceCleaner implements PersistentContentListener {
   private boolean _keepStrongReferences = true;
   private Vector _references = (Vector)(new Object());

   private Helper$ReferenceCleaner() {
   }

   public static Helper$ReferenceCleaner getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      if (var0 == null) {
         return new Helper$ReferenceCleaner();
      }

      Helper$ReferenceCleaner var1 = (Helper$ReferenceCleaner)var0.getOrWaitFor(4119503239558518103L);
      if (var1 == null) {
         var1 = new Helper$ReferenceCleaner();
         var0.put(4119503239558518103L, var1);
      }

      return var1;
   }

   public synchronized void addLocalStrongReferences(Helper$LocalStrongReferences var1) {
      WeakReferenceUtilities.purge(this._references);
      this._references.addElement(new Object(var1));
   }

   public boolean keepStrongReferences() {
      return this._keepStrongReferences;
   }

   @Override
   public synchronized void persistentContentStateChanged(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public void persistentContentModeChanged(int var1) {
   }
}
