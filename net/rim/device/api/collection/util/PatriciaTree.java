package net.rim.device.api.collection.util;

public class PatriciaTree {
   private PatriciaTreeData _data;
   private int _leafIndex;
   private int _numLeaves;
   private static final int[] _bitCountMasks;

   public PatriciaTree(PatriciaTreeData var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._data = var1;
   }

   public void insert(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void doInsert(int var1) {
      if (this._data.size() == 0) {
         this._data.insert(0, 0, 0, var1, 0);
      } else {
         this.lookupId(var1);
         int var6 = this._data.compareBits(var1, this._data.getLeaf(this._leafIndex));
         if (var6 == 0) {
            throw new Object();
         }

         int var8;
         if (var6 > 0) {
            var8 = var6 - 1;
         } else {
            var8 = -var6 - 1;
         }

         int var7 = this._leafIndex;
         int var4 = 0;
         this._leafIndex = 0;
         this._numLeaves = this._data.size();

         while (this._numLeaves > 1) {
            int var2 = this._data.getBitNumber(var4);
            int var3 = this._data.getLeftNodes(var4);
            if (var2 > var8) {
               break;
            }

            if (var7 <= this._leafIndex + var3) {
               this._data.adjustLeftNodes(var4, 1);
               var4++;
               this._numLeaves = var3 + 1;
            } else {
               var3++;
               this._numLeaves -= var3;
               var4 += var3;
               this._leafIndex += var3;
            }
         }

         int var10;
         if (var6 > 0) {
            var10 = this._numLeaves - 1;
            this._leafIndex = this._leafIndex + this._numLeaves;
         } else {
            var10 = 0;
         }

         this._data.insert(var10, var8, var4, var1, this._leafIndex);
      }
   }

   public void delete(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void doDelete(int var1) {
      this.lookupId(var1);
      if (this._numLeaves != 0) {
         if (this._data.getLeaf(this._leafIndex) == var1) {
            this.deleteLeaf(this._leafIndex);
         }
      }
   }

   public void deleteLeaf(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int search(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void lookupPrefix(Object var1) {
      int var2 = 0;
      this._leafIndex = 0;
      this._numLeaves = this._data.size();
      if (this._numLeaves != 0 && var1 != null) {
         while (this._numLeaves > 1) {
            int var3 = this._data.getBitNumber(var2);
            int var4 = this._data.getLeftNodes(var2);
            int var5 = this._data.getPrefixBit(var1, var3);
            if (var5 < 0) {
               break;
            }

            if (var5 == 0) {
               this._numLeaves = var4 + 1;
               var2++;
            } else {
               var4++;
               this._numLeaves -= var4;
               var2 += var4;
               this._leafIndex += var4;
            }
         }

         if (!this._data.prefixMatches(var1, this._data.getLeaf(this._leafIndex))) {
            this._numLeaves = 0;
         }
      }
   }

   private void lookupId(int var1) {
      int var2 = 0;
      this._leafIndex = 0;
      this._numLeaves = this._data.size();
      if (this._numLeaves != 0) {
         while (this._numLeaves > 1) {
            int var3 = this._data.getBitNumber(var2);
            int var4 = this._data.getLeftNodes(var2);
            int var5 = this._data.getBit(var1, var3);
            if (var5 < 0) {
               throw new Object();
            }

            if (var5 == 0) {
               this._numLeaves = var4 + 1;
               if (this._numLeaves == 1) {
                  return;
               }

               var2++;
            } else {
               var4++;
               this._numLeaves -= var4;
               var2 += var4;
               this._leafIndex += var4;
            }
         }
      }
   }

   public static int getStringBit(String var0, int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int getStringBit(String var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int compareStringBits(String var0, int var1, int var2, int var3, String var4, int var5, int var6, int var7) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int compareStringBits(String var0, int var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int bitDifference(int var0, int var1, int var2, int var3) {
      if (var1 == var2) {
         return 0;
      }

      var2 ^= var1;
      var0 += var3;
      int var4 = _bitCountMasks.length;

      for (byte var6 = 16; --var4 >= 0; var6 >>= 1) {
         int var5 = var2 & _bitCountMasks[var4];
         if (var5 != 0) {
            var0 -= var6;
            var2 = var5;
         }
      }

      return (var1 & var2) == 0 ? -var0 : var0;
   }

   public void dump() {
      this.dump(0, 0, 0, this._data.size());
   }

   private void dump(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean validate() {
      boolean var2 = true;
      int var1 = this._data.size();
      return var1 < 2 ? true : this.validateNode(0, var1, -1);
   }

   private boolean validateNode(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
