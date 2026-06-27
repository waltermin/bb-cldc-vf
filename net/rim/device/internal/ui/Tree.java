package net.rim.device.internal.ui;

public final class Tree {
   private int _nodeCount;
   private int _visibleCount;
   private int _firstFreeNode;
   private byte _defaultInfo;
   private short[] _parent;
   private short[] _firstChild;
   private short[] _nextSibling;
   private short[] _previousSibling;
   private Object[] _cookie;
   private byte[] _info;
   private static final int START_SIZE;
   private static final int SUPERROOT;
   private static final int INFO_EXPANDED;
   private static final int INFO_VISIBLE;
   private static final int INFO_OCCUPIED;
   private static final int INFO_DEFAULT;

   public Tree() {
      this.init(true);
   }

   private final void init(boolean var1) {
      this._parent = new short[8];
      this._firstChild = new short[8];
      this._nextSibling = new short[8];
      this._previousSibling = new short[8];
      this._cookie = new Object[8];
      this._info = new byte[8];
      this._nodeCount = 1;
      this._visibleCount = 0;
      this.setDefaultExpansion(var1);
      this._parent[0] = -1;
      this._firstChild[0] = -1;
      this._nextSibling[0] = -1;
      this._previousSibling[0] = -1;
      this._info[0] = 7;
      this.initFreeListPointers(1);
   }

   public final void deleteAll() {
      int var1 = this._cookie.length;
      if (var1 > 8) {
         this.init((this._defaultInfo & 1) != 0);
      } else {
         for (int var2 = 1; var2 < var1; var2++) {
            this._cookie[var2] = null;
            this._info[var2] = 0;
         }

         this._nodeCount = 1;
         this._visibleCount = 0;
         this._firstChild[0] = -1;
         this.initFreeListPointers(1);
      }
   }

   private final void initFreeListPointers(int var1) {
      this._firstFreeNode = var1;
      short[] var2 = this._nextSibling;
      int var3 = var2.length;

      while (var1 < var3) {
         int var4 = var1 + 1;
         var2[var1] = (short)var4;
         var1 = var4;
      }

      var2[var1 - 1] = -1;
   }

   private final void validateNodeIdSuperOK(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void validateNodeId(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setDefaultExpansion(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void deleteSubtree(int var1) {
      this.validateNodeId(var1);
      short var2 = this._parent[var1];
      if (this._firstChild[var2] == var1) {
         this._firstChild[var2] = this._nextSibling[var1];
      } else {
         this._nextSibling[this._previousSibling[var1]] = this._nextSibling[var1];
      }

      if (this._nextSibling[var1] != -1) {
         this._previousSibling[this._nextSibling[var1]] = this._previousSibling[var1];
      }

      int var3 = 0;
      int var4 = 0;
      int var5 = var1;

      while (true) {
         short var6 = this._firstChild[var5];
         if (var6 == -1) {
            while (true) {
               var3++;
               if ((this._info[var5] & 2) != 0) {
                  var4++;
               }

               short var7 = this._nextSibling[var5];
               this._info[var5] = 0;
               this._cookie[var5] = null;
               this._nextSibling[var5] = (short)this._firstFreeNode;
               this._firstFreeNode = var5;
               if (var5 == var1) {
                  this._nodeCount -= var3;
                  this._visibleCount -= var4;
                  return;
               }

               var6 = var7;
               if (var6 == -1) {
                  var5 = this._parent[var5];
               } else {
                  var5 = var6;

                  while (true) {
                     var6 = this._firstChild[var5];
                     if (var6 == -1) {
                        break;
                     }

                     var5 = var6;
                  }
               }
            }
         }

         var5 = var6;
      }
   }

   public final int addChildNode(int var1, Object var2) {
      this.validateNodeIdSuperOK(var1);
      short var3 = this.allocateNode();
      this._parent[var3] = (short)var1;
      this._previousSibling[var3] = -1;
      short var4 = this._firstChild[var1];
      this._firstChild[var1] = var3;
      this._nextSibling[var3] = var4;
      if (var4 != -1) {
         this._previousSibling[var4] = var3;
      }

      this._cookie[var3] = var2;
      if ((this._info[var1] & 3) != 3) {
         this._info[var3] = (byte)(this._info[var3] & -3);
         return var3;
      } else {
         this._visibleCount++;
         return var3;
      }
   }

   public final int addSiblingNode(int var1, Object var2) {
      this.validateNodeId(var1);
      short var3 = this.allocateNode();
      this._parent[var3] = this._parent[var1];
      short var4 = this._nextSibling[var1];
      this._nextSibling[var3] = var4;
      this._previousSibling[var3] = (short)var1;
      this._nextSibling[var1] = var3;
      if (var4 != -1) {
         this._previousSibling[var4] = var3;
      }

      this._cookie[var3] = var2;
      if ((this._info[var1] & 2) == 0) {
         this._info[var3] = (byte)(this._info[var3] & -3);
         return var3;
      } else {
         this._visibleCount++;
         return var3;
      }
   }

   public final int getNodeCount() {
      return this._nodeCount - 1;
   }

   public final int getFirstRoot() {
      return this._firstChild[0];
   }

   public final int getFirstChild(int var1) {
      this.validateNodeIdSuperOK(var1);
      return this._firstChild[var1];
   }

   public final int getNextSibling(int var1) {
      this.validateNodeId(var1);
      return this._nextSibling[var1];
   }

   public final int getPreviousSibling(int var1) {
      this.validateNodeId(var1);
      return this._previousSibling[var1];
   }

   public final int getParent(int var1) {
      this.validateNodeId(var1);
      return this._parent[var1];
   }

   public final void setNodeExpansion(int var1, boolean var2) {
      this.validateNodeId(var1);
      if (var2) {
         if (!this.getNodeExpansion(var1)) {
            this._info[var1] = (byte)(this._info[var1] | 1);

            for (int var3 = this.nextSubtreeNode(var1, var1, false); var3 != -1; var3 = this.nextSubtreeNode(var3, var1, false)) {
               this._info[var3] = (byte)(this._info[var3] | 2);
               this._visibleCount++;
            }
         }
      } else if (this.getNodeExpansion(var1)) {
         for (int var4 = this.nextSubtreeNode(var1, var1, false); var4 != -1; var4 = this.nextSubtreeNode(var4, var1, false)) {
            this._info[var4] = (byte)(this._info[var4] & -3);
            this._visibleCount--;
         }

         this._info[var1] = (byte)(this._info[var1] & -2);
      }
   }

   public final int getVisibleCount() {
      return this._visibleCount;
   }

   public final boolean getNodeExpansion(int var1) {
      this.validateNodeId(var1);
      return this.getNodeExpansion0(var1);
   }

   private final boolean getNodeExpansion0(int var1) {
      return (this._info[var1] & 1) != 0;
   }

   public final boolean getVisible(int var1) {
      this.validateNodeId(var1);
      return (this._info[var1] & 2) != 0;
   }

   public final int nextNode(int var1, boolean var2) {
      return this.nextSubtreeNode(var1, 0, var2);
   }

   public final int nextSubtreeNode(int var1, int var2, boolean var3) {
      this.validateNodeIdSuperOK(var1);
      this.validateNodeIdSuperOK(var2);
      if (var3 || this.getNodeExpansion0(var1)) {
         short var4 = this._firstChild[var1];
         if (var4 != -1) {
            return var4;
         }
      }

      if (var1 == var2) {
         return -1;
      }

      short var6 = this._nextSibling[var1];
      if (var6 != -1) {
         return var6;
      }

      for (short var5 = this._parent[var1]; var5 != -1 && var5 != var2; var5 = this._parent[var5]) {
         var6 = this._nextSibling[var5];
         if (var6 != -1) {
            return var6;
         }
      }

      return -1;
   }

   public final int getLastNode(int var1, boolean var2) {
      this.validateNodeIdSuperOK(var1);

      while (var2 || this.getNodeExpansion0(var1)) {
         short var3 = this._firstChild[var1];
         if (var3 == -1) {
            break;
         }

         var1 = var3;

         while (true) {
            var3 = this._nextSibling[var1];
            if (var3 == -1) {
               break;
            }

            var1 = var3;
         }
      }

      return var1 == 0 ? -1 : var1;
   }

   public final int previousNode(int var1, boolean var2) {
      this.validateNodeIdSuperOK(var1);
      if (var1 != 0) {
         short var3 = this._previousSibling[var1];
         if (var3 == -1) {
            short var4 = this._parent[var1];
            if (var4 == 0) {
               var4 = -1;
            }

            return var4;
         }

         var1 = var3;
      }

      return this.getLastNode(var1, var2);
   }

   public final int getIndexOf(int var1, boolean var2) {
      this.validateNodeId(var1);
      int var3 = 0;
      int var4 = this._firstChild[0];

      while (var4 != var1) {
         var3++;
         var4 = this.nextNode(var4, var2);
      }

      return var3;
   }

   public final int getNodeDepth(int var1) {
      this.validateNodeId(var1);
      int var2 = 0;

      while (true) {
         var1 = this._parent[var1];
         if (var1 == 0) {
            return var2;
         }

         var2++;
      }
   }

   public final void setCookie(int var1, Object var2) {
      this.validateNodeId(var1);
      this._cookie[var1] = var2;
   }

   public final Object getCookie(int var1) {
      this.validateNodeId(var1);
      return this._cookie[var1];
   }

   private final short allocateNode() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
