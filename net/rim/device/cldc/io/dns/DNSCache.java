package net.rim.device.cldc.io.dns;

import java.util.Vector;
import net.rim.device.api.lowmemory.LowMemoryListener;
import net.rim.device.api.util.SimpleSortingVector;

public final class DNSCache implements LowMemoryListener {
   private DNSCacheNode _treeHead;
   private SimpleSortingVector _expiryOrder;
   private int _maxSize;
   private int _curSize;

   public final void emptyCache() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void addToCache(DNSRequest var1) {
      this.addToCache(var1, getCurrentTime());
   }

   public final void addToCache(DNSRequest var1, int var2) {
      DNSMessageIPv4 var3 = var1.getAnswer();
      if (var3.getTC() != 512) {
         Vector[] var4 = new Vector[]{var3.getAnswers(), var3.getAuthorities(), var3.getAdditional()};
         int var5 = 0;

         for (int var8 = 0; var8 < var4.length; var8++) {
            if (this._maxSize >= 0 && var4[var8].size() > this._maxSize) {
               var4[var8] = (Vector)(new Object());
            } else {
               var5 += var4[var8].size();
            }

            for (int var9 = var4[var8].size() - 1; var9 >= 0; var9--) {
               DNSMessageIPv4Resource var6 = (DNSMessageIPv4Resource)var4[var8].elementAt(var9);
               DNSCacheNode var7 = this.getNode(var6.getName());
               if (var7 != null) {
                  this._curSize = this._curSize - var7.removeResources(var6.getType());
               }
            }
         }

         if (this._maxSize >= 0) {
            this.pruneToSize(this._maxSize - var5);
         }

         for (int var12 = 0; var12 < var4.length; var12++) {
            for (int var13 = var4[var12].size() - 1; var13 >= 0; var13--) {
               DNSMessageIPv4Resource var10 = (DNSMessageIPv4Resource)var4[var12].elementAt(var13);
               DNSCacheNode var11 = this.createNode(var10.getName());
               var11.addToResources(new DNSCachedRR(var10, var2));
               this._curSize++;
            }
         }
      }
   }

   public final void addNameError(DNSRequest var1) {
      this.addNameError(var1, getCurrentTime());
   }

   public final void addNameError(DNSRequest var1, int var2) {
      Vector var3 = var1.getAnswer().getAuthorities();

      for (int var5 = var3.size() - 1; var5 >= 0; var5--) {
         DNSMessageIPv4Resource var4 = (DNSMessageIPv4Resource)var3.elementAt(var5);
         if (var4.getType() == 6) {
            this.createNode(var1.getQueryString()).setNameError(var2 + ((DNSMessageIPv4Resource$SOAData)var4.getData()).minimum);
            return;
         }
      }
   }

   public final Vector lookup(String var1, int var2) {
      return this.lookupInternal(var1, var2, null);
   }

   private final Vector lookupInternal(String var1, int var2, Vector var3) {
      DNSCacheNode var4 = this.getNode(var1);
      if (var4 == null || var4.getExpiryTime() < getCurrentTime()) {
         return null;
      }

      if (var4.isNameError()) {
         throw new DNSException(7);
      }

      if (var3 != null && ((Vector)var3).contains(var4)) {
         return null;
      }

      Object var5 = null;
      DNSCachedRR[] var6 = var4.getResources(var2);
      if (var6 != null && var6.length != 0) {
         var5 = new Object(var6.length);

         for (int var9 = 0; var9 < var6.length; var9++) {
            ((Vector)var5).addElement(var6[var9].getData());
         }
      } else {
         var6 = var4.getResources(5);
         if (var6 == null || var6.length == 0) {
            return null;
         }

         if (var3 == null) {
            var3 = new Object();
         }

         ((Vector)var3).addElement(var4);

         for (int var7 = var6.length - 1; var7 >= 0; var7--) {
            var5 = this.lookupInternal((String)var6[var7].getData(), var2, (Vector)var3);
            if (var5 != null) {
               return (Vector)var5;
            }
         }
      }

      return (Vector)var5;
   }

   private final DNSCacheNode getNode(String var1) {
      String[] var2 = splitDomain(var1);
      DNSCacheNode var3 = this._treeHead;

      for (int var4 = var2.length - 1; var4 >= 0; var4--) {
         var3 = var3.getChild(var2[var4]);
         if (var3 == null) {
            return null;
         }
      }

      return var3;
   }

   private final DNSCacheNode createNode(String var1) {
      String[] var2 = splitDomain(var1);
      DNSCacheNode var3 = this._treeHead;

      for (int var5 = var2.length - 1; var5 >= 0; var5--) {
         DNSCacheNode var4 = var3.getChild(var2[var5]);
         if (var4 == null) {
            var4 = new DNSCacheNode(var2[var5]);
            var3.addChild(var4);
            if (var5 == 0) {
               this._expiryOrder.addElement(var4);
            }
         }

         var3 = var4;
      }

      return var3;
   }

   public final void removeExpired() {
      int var1 = getCurrentTime();
      this._expiryOrder.reSort();

      while (this.removeIfExpired(var1)) {
      }

      this._treeHead.removeDeadDescendants();
   }

   private final boolean removeIfExpired(int var1) {
      if (this._expiryOrder.size() > 0) {
         DNSCacheNode var2 = (DNSCacheNode)this._expiryOrder.elementAt(0);
         if (var2.getExpiryTime() < var1) {
            this._curSize = this._curSize - var2.deleteData();
            this._expiryOrder.removeElementAt(0);
            return true;
         }
      }

      return false;
   }

   public final void removeFromCache(String var1, Object var2) {
      this.removeFromCacheInternal(var1, var2, null);
   }

   private final boolean removeFromCacheInternal(String var1, Object var2, Vector var3) {
      DNSCacheNode var4 = this.getNode(var1);
      if (var4 != null) {
         int var5 = var4.removeResourceWithData(var2);
         if (var5 > 0) {
            this._treeHead.removeDeadDescendants();
            if (var4.canBeDeleted()) {
               for (int var8 = this._expiryOrder.size() - 1; var8 >= 0; var8--) {
                  if (this._expiryOrder.elementAt(var8) == var4) {
                     this._expiryOrder.removeElementAt(var8);
                     return true;
                  }
               }
            }

            return true;
         }

         DNSCachedRR[] var6 = var4.getResources(5);
         if (var6 != null && var6.length > 0) {
            if (var3 == null) {
               var3 = new Object();
            }

            ((Vector)var3).addElement(var4);

            for (int var7 = var6.length - 1; var7 >= 0; var7--) {
               if (this.removeFromCacheInternal((String)var6[var7].getData(), var2, (Vector)var3)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final void pruneToSize(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setMaxSize(int var1) {
      this._maxSize = var1;
      if (this._maxSize >= 0) {
         this.pruneToSize(this._maxSize);
         this._treeHead.removeDeadDescendants();
      }
   }

   public final int getMaxSize() {
      return this._maxSize;
   }

   public final int getCurSize() {
      return this._curSize;
   }

   public static final int getCurrentTime() {
      return (int)(System.currentTimeMillis() / 1000);
   }

   public static final String[] splitDomain(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final boolean freeStaleObject(int var1) {
      int var2 = this._curSize;
      if (var1 == 0) {
         this.removeExpired();
      } else {
         this.emptyCache();
      }

      return this._curSize < var2;
   }
}
