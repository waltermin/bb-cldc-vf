package net.rim.device.api.util;

import java.util.Enumeration;
import java.util.Vector;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;

public class ContentProtectedVector extends Vector implements Persistable, PersistentContentListener {
   boolean _protected;

   public ContentProtectedVector(int var1, int var2, boolean var3) {
      super(var1, var2);
      if (var3) {
         this.reCrypt();
      }
   }

   public ContentProtectedVector(int var1, int var2) {
      super(var1, var2);
      this.reCrypt();
   }

   public ContentProtectedVector(int var1) {
      super(var1);
      this.reCrypt();
   }

   public ContentProtectedVector(boolean var1) {
      if (var1) {
         this.reCrypt();
      }
   }

   public ContentProtectedVector() {
      this(true);
   }

   @Override
   public synchronized void copyInto(Object[] var1) {
      super.copyInto(var1);
      if (this._protected) {
         for (int var2 = super.elementCount - 1; var2 >= 0; var2--) {
            var1[var2] = PersistentContent.decode(var1[var2]);
         }
      }
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
   public synchronized int indexOf(Object var1, int var2) {
      if (!this._protected) {
         return super.indexOf(var1, var2);
      }

      if (var1 == null) {
         for (int var3 = var2; var3 < super.elementCount; var3++) {
            if (super.elementData[var3] == null) {
               return var3;
            }
         }
      } else {
         for (int var4 = var2; var4 < super.elementCount; var4++) {
            if (var1.equals(PersistentContent.decode(super.elementData[var4]))) {
               return var4;
            }
         }
      }

      return -1;
   }

   @Override
   public synchronized int lastIndexOf(Object var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public synchronized Object elementAt(int var1) {
      return this._protected ? PersistentContent.decode(super.elementAt(var1)) : super.elementAt(var1);
   }

   @Override
   public synchronized Object firstElement() {
      return this._protected ? PersistentContent.decode(super.firstElement()) : super.firstElement();
   }

   @Override
   public synchronized Object lastElement() {
      return this._protected ? PersistentContent.decode(super.lastElement()) : super.lastElement();
   }

   @Override
   public synchronized void setElementAt(Object var1, int var2) {
      if (this._protected) {
         var1 = PersistentContent.encodeObject(var1);
      }

      super.setElementAt(var1, var2);
   }

   @Override
   public synchronized void insertElementAt(Object var1, int var2) {
      if (this._protected) {
         var1 = PersistentContent.encodeObject(var1);
      }

      super.insertElementAt(var1, var2);
   }

   @Override
   public synchronized void addElement(Object var1) {
      if (this._protected) {
         var1 = PersistentContent.encodeObject(var1);
      }

      super.addElement(var1);
   }

   public synchronized boolean isProtected() {
      return this._protected;
   }

   public synchronized boolean checkCrypt() {
      if (!this._protected) {
         return false;
      }

      for (int var1 = 0; var1 < super.elementCount; var1++) {
         if (!PersistentContent.checkEncoding(super.elementData[var1])) {
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

      for (int var1 = 0; var1 < super.elementCount; var1++) {
         super.elementData[var1] = PersistentContent.encodeObject(super.elementData[var1]);
      }

      PersistentContent.addWeakListener(this);
   }

   private void reCrypt2() {
      for (int var1 = 0; var1 < super.elementCount; var1++) {
         super.elementData[var1] = PersistentContent.reEncode(super.elementData[var1]);
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
