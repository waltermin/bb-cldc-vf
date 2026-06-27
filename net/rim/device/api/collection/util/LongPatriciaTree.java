package net.rim.device.api.collection.util;

public class LongPatriciaTree {
   private LongPatriciaTreeData _data;
   private int _leafIndex;
   private int _numLeaves;

   public LongPatriciaTree(LongPatriciaTreeData var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._data = var1;
   }

   public void insert(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void doInsert(long var1) {
      if (this._data.size() == 0) {
         this._data.insert(0, 0, 0, var1, 0);
      } else {
         this.lookupId(var1);
         int var7 = this._data.compareBits(var1, this._data.getLeaf(this._leafIndex));
         if (var7 == 0) {
            throw new Object();
         }

         int var9;
         if (var7 > 0) {
            var9 = var7 - 1;
         } else {
            var9 = -var7 - 1;
         }

         int var8 = this._leafIndex;
         int var5 = 0;
         this._leafIndex = 0;
         this._numLeaves = this._data.size();

         while (this._numLeaves > 1) {
            int var3 = this._data.getBitNumber(var5);
            int var4 = this._data.getLeftNodes(var5);
            if (var3 > var9) {
               break;
            }

            if (var8 <= this._leafIndex + var4) {
               this._data.adjustLeftNodes(var5, 1);
               var5++;
               this._numLeaves = var4 + 1;
            } else {
               var4++;
               this._numLeaves -= var4;
               var5 += var4;
               this._leafIndex += var4;
            }
         }

         int var11;
         if (var7 > 0) {
            var11 = this._numLeaves - 1;
            this._leafIndex = this._leafIndex + this._numLeaves;
         } else {
            var11 = 0;
         }

         this._data.insert(var11, var9, var5, var1, this._leafIndex);
      }
   }

   public void delete(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void doDelete(long var1) {
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

   private void lookupId(long var1) {
      int var3 = 0;
      this._leafIndex = 0;
      this._numLeaves = this._data.size();
      if (this._numLeaves != 0) {
         while (this._numLeaves > 1) {
            int var4 = this._data.getBitNumber(var3);
            int var5 = this._data.getLeftNodes(var3);
            int var6 = this._data.getBit(var1, var4);
            if (var6 < 0) {
               throw new Object();
            }

            if (var6 == 0) {
               this._numLeaves = var5 + 1;
               if (this._numLeaves == 1) {
                  return;
               }

               var3++;
            } else {
               var5++;
               this._numLeaves -= var5;
               var3 += var5;
               this._leafIndex += var5;
            }
         }
      }
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
