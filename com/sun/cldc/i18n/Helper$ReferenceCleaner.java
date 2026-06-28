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
      ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
      if (ar == null) {
         return new Helper$ReferenceCleaner();
      }

      Helper$ReferenceCleaner referenceCleaner = (Helper$ReferenceCleaner)ar.getOrWaitFor(4119503239558518103L);
      if (referenceCleaner == null) {
         referenceCleaner = new Helper$ReferenceCleaner();
         ar.put(4119503239558518103L, referenceCleaner);
      }

      return referenceCleaner;
   }

   public synchronized void addLocalStrongReferences(Helper$LocalStrongReferences localStrongReferences) {
      WeakReferenceUtilities.purge(this._references);
      this._references.addElement(new Object(localStrongReferences));
   }

   public boolean keepStrongReferences() {
      return this._keepStrongReferences;
   }

   @Override
   public synchronized void persistentContentStateChanged(int state) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public void persistentContentModeChanged(int generation) {
   }
}
