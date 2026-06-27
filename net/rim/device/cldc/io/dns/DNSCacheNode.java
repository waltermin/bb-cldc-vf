package net.rim.device.cldc.io.dns;

import java.util.Vector;

public final class DNSCacheNode {
   private String _domainLabel;
   private Vector _resources;
   private int _expiryTime;
   private boolean _nameError;
   private DNSCacheNode _leftChild;
   private DNSCacheNode _rightSibling;

   public DNSCacheNode(String var1) {
      this._domainLabel = var1;
      this._expiryTime = -1;
   }

   public DNSCacheNode(String var1, DNSCachedRR var2) {
      this._domainLabel = var1;
      this._resources = (Vector)(new Object());
      this._resources.addElement(var2);
      this._expiryTime = var2.getExpiryTime();
   }

   public DNSCacheNode(String var1, DNSCachedRR[] var2) {
      this._domainLabel = var1;
      if (var2 != null && var2.length > 0) {
         this._resources = (Vector)(new Object(var2.length));
         this._expiryTime = var2[0].getExpiryTime();

         for (int var3 = 0; var3 < var2.length; var3++) {
            this._resources.addElement(var2[var3]);
            this._expiryTime = Math.min(this._expiryTime, var2[var3].getExpiryTime());
         }
      } else {
         this._expiryTime = -1;
      }
   }

   public final String getDomainLabel() {
      return this._domainLabel;
   }

   public final void addToResources(DNSCachedRR var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void addToResources(DNSCachedRR[] var1) {
      if (var1.length != 0) {
         if (this._resources == null) {
            this._resources = (Vector)(new Object(var1.length));
            this._expiryTime = var1[0].getExpiryTime();
            this._nameError = false;
         } else {
            this._resources.ensureCapacity(this._resources.size() + var1.length);
         }

         for (int var2 = 0; var2 < var1.length; var2++) {
            this._resources.addElement(var1[var2]);
            this._expiryTime = Math.min(this._expiryTime, var1[var2].getExpiryTime());
         }
      }
   }

   public final int setResources(DNSCachedRR var1) {
      int var2 = this._resources == null ? 0 : this._resources.size();
      this._resources = (Vector)(new Object());
      this._resources.addElement(var1);
      this._expiryTime = var1.getExpiryTime();
      this._nameError = false;
      return var2;
   }

   public final int setResources(DNSCachedRR[] var1) {
      int var2 = this._resources == null ? 0 : this._resources.size();
      if (var1.length > 0) {
         this._resources = (Vector)(new Object(var1.length));
         this._expiryTime = var1[0].getExpiryTime();

         for (int var3 = 0; var3 < var1.length; var3++) {
            this._resources.addElement(var1[var3]);
            this._expiryTime = Math.min(this._expiryTime, var1[var3].getExpiryTime());
         }
      } else {
         this._resources = null;
         this._expiryTime = -1;
      }

      this._nameError = false;
      return var2;
   }

   public final int removeResources(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final int removeResourceWithData(Object var1) {
      int var2 = Integer.MAX_VALUE;
      int var3 = 0;

      for (int var4 = this._resources.size() - 1; var4 >= 0; var4--) {
         DNSCachedRR var5 = (DNSCachedRR)this._resources.elementAt(var4);
         if (var5.getData() == var1) {
            var3++;
            this._resources.removeElementAt(var4);
         } else if (var2 > var5.getExpiryTime()) {
            var2 = var5.getExpiryTime();
         }
      }

      this._expiryTime = var2;
      return var3;
   }

   public final DNSCachedRR[] getResources() {
      if (this._resources == null) {
         return null;
      }

      DNSCachedRR[] var1 = new DNSCachedRR[this._resources.size()];
      this._resources.copyInto(var1);
      return var1;
   }

   public final DNSCachedRR[] getResources(int var1) {
      if (this._resources == null) {
         return null;
      }

      Object var2 = new Object(this._resources.size());

      for (int var4 = this._resources.size() - 1; var4 >= 0; var4--) {
         DNSCachedRR var3 = (DNSCachedRR)this._resources.elementAt(var4);
         if (var3.getType() == var1) {
            ((Vector)var2).addElement(var3);
         }
      }

      if (((Vector)var2).size() == 0) {
         return null;
      }

      DNSCachedRR[] var5 = new DNSCachedRR[((Vector)var2).size()];
      ((Vector)var2).copyInto(var5);
      return var5;
   }

   public final int getExpiryTime() {
      return this._expiryTime;
   }

   public final int setNameError(int var1) {
      int var2 = this.deleteData();
      this._expiryTime = var1;
      this._nameError = true;
      return var2;
   }

   public final boolean isNameError() {
      return this._nameError;
   }

   public final int deleteData() {
      int var1 = this._resources == null ? 0 : this._resources.size();
      this._resources = null;
      this._expiryTime = -1;
      this._nameError = false;
      return var1;
   }

   private final boolean containsData() {
      return this._nameError || this._resources != null && this._resources.size() != 0;
   }

   public final boolean canBeDeleted() {
      return !this.containsData() && this._leftChild == null;
   }

   final void addChild(DNSCacheNode var1) {
      if (var1 != null) {
         if (this._leftChild == null) {
            this._leftChild = var1;
         } else if (var1._domainLabel.compareTo(this._leftChild._domainLabel) <= 0) {
            var1._rightSibling = this._leftChild;
            this._leftChild = var1;
         } else {
            this._leftChild.addSibling(var1);
         }
      }
   }

   private final void removeDeadChildren() {
      if (this._leftChild != null) {
         this._leftChild.removeDeadSiblings();
         if (this._leftChild.canBeDeleted()) {
            this._leftChild = this._leftChild._rightSibling;
         }
      }
   }

   final void removeDeadDescendants() {
      for (DNSCacheNode var1 = this._leftChild; var1 != null; var1 = var1._rightSibling) {
         var1.removeDeadDescendants();
      }

      this.removeDeadChildren();
   }

   final DNSCacheNode getChild() {
      return this._leftChild;
   }

   final DNSCacheNode getChild(String var1) {
      return this._leftChild == null ? null : this._leftChild.getSibling(var1);
   }

   private final void addSibling(DNSCacheNode var1) {
      DNSCacheNode var2 = this;

      while (var2._rightSibling != null && var1._domainLabel.compareTo(var2._rightSibling._domainLabel) >= 0) {
         var2 = var2._rightSibling;
      }

      var1._rightSibling = var2._rightSibling;
      var2._rightSibling = var1;
   }

   private final DNSCacheNode getSibling(String var1) {
      DNSCacheNode var2 = this;

      while (var2 != null && !var2._domainLabel.equals(var1)) {
         var2 = var2._rightSibling;
      }

      return var2;
   }

   private final void removeDeadSiblings() {
      DNSCacheNode var1 = this;

      while (var1._rightSibling != null) {
         if (var1._rightSibling.canBeDeleted()) {
            var1._rightSibling = var1._rightSibling._rightSibling;
         } else {
            var1 = var1._rightSibling;
         }
      }
   }
}
