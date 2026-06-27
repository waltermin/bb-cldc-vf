package net.rim.device.api.util;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;

public class ContentProtectedHashtable extends Hashtable implements Persistable, PersistentContentListener {
   private boolean _protected;

   public ContentProtectedHashtable(boolean var1) {
      if (var1) {
         this.reCrypt();
      }
   }

   public ContentProtectedHashtable() {
      this(true);
   }

   public ContentProtectedHashtable(int var1) {
      this(var1, true);
   }

   public ContentProtectedHashtable(int var1, boolean var2) {
      super(var1);
      if (var2) {
         this.reCrypt();
      }
   }

   public ContentProtectedHashtable(Hashtable var1, boolean var2) {
      if (var1 == null) {
         throw new Object();
      }

      Enumeration var3 = var1.keys();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         this.put(var4, var1.get(var4));
      }

      if (var2) {
         this.reCrypt();
      }
   }

   @Override
   public synchronized boolean contains(Object var1) {
      if (!this._protected) {
         return super.contains(var1);
      }

      if (var1 == null) {
         throw new Object();
      }

      Enumeration var2 = super.elements();

      while (var2.hasMoreElements()) {
         Object var3 = PersistentContent.decode(var2.nextElement());
         if (var3.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public synchronized Enumeration elements() {
      Object var1 = super.elements();
      if (this._protected) {
         var1 = new Object((Enumeration)var1);
      }

      return (Enumeration)var1;
   }

   @Override
   public synchronized Object get(Object var1) {
      Object var2 = super.get(var1);
      if (this._protected) {
         var2 = PersistentContent.decode(var2);
      }

      return var2;
   }

   @Override
   public synchronized Object put(Object var1, Object var2) {
      if (this._protected) {
         var2 = PersistentContent.encodeObject(var2);
      }

      Object var3 = super.put(var1, var2);
      if (this._protected) {
         var3 = PersistentContent.decode(var3);
      }

      return var3;
   }

   public synchronized boolean isProtected() {
      return this._protected;
   }

   public synchronized boolean checkCrypt() {
      if (!this._protected) {
         return false;
      }

      Enumeration var1 = this.keys();

      while (var1.hasMoreElements()) {
         Object var2 = var1.nextElement();
         if (!PersistentContent.checkEncoding(super.get(var2))) {
            return false;
         }
      }

      return true;
   }

   public synchronized void reCrypt() {
      if (!this._protected) {
         this.transitionToProtected();
      } else {
         this.reCrypt2();
      }
   }

   private void transitionToProtected() {
      this._protected = true;
      Enumeration var1 = this.keys();

      while (var1.hasMoreElements()) {
         Object var2 = var1.nextElement();
         super.put(var2, PersistentContent.encodeObject(super.get(var2)));
      }

      PersistentContent.addWeakListener(this);
   }

   private void reCrypt2() {
      Enumeration var1 = this.keys();

      while (var1.hasMoreElements()) {
         Object var2 = var1.nextElement();
         super.put(var2, PersistentContent.reEncode(super.get(var2)));
      }
   }

   @Override
   public synchronized void persistentContentModeChanged(int var1) {
      this.reCrypt2();
   }

   @Override
   public synchronized void persistentContentStateChanged(int var1) {
   }
}
