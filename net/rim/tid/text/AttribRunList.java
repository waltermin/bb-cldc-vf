package net.rim.tid.text;

import net.rim.vm.Array;

public final class AttribRunList {
   public int[] iOffsets;
   public int[] iAttributes;
   public int iOffsetNo;
   private static final int OFFSETS_ARRAY_GROWTH_SIZE;
   private static AttribRunList iTempList;

   public AttribRunList() {
      this(10);
   }

   public AttribRunList(int var1) {
      this.iOffsets = new int[var1];
      this.iAttributes = new int[var1];
   }

   public AttribRunList(AttribRunList var1) {
      this(var1.iOffsets.length);
      int var2 = var1.iOffsets.length;
      System.arraycopy(var1.iOffsets, 0, this.iOffsets, 0, var2);
      System.arraycopy(var1.iAttributes, 0, this.iAttributes, 0, var2 - 1);
      this.iOffsetNo = var1.iOffsetNo;
   }

   private AttribRunList(AttribRunList var1, int var2, int var3) {
   }

   public final void init(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final int getSize() {
      return this.iOffsetNo - 1;
   }

   public final void insertAttrib(int var1, int var2, int var3) {
      iTempList.init(var1, var3);
      this.replace(var2, var2, iTempList);
   }

   public final void replace(int var1, int var2, AttribRunList var3) {
      if (var3 == null || var3.iOffsetNo == 0) {
         if (var1 == var2) {
            return;
         }

         var3 = null;
      }

      if (this.iOffsetNo == 0) {
         this.insertOffsets(0, var1, var3);
      } else {
         int var4 = -1;
         int var5 = -1;
         int var6 = this.iOffsetNo;

         for (int var7 = 0; var7 < var6; var7++) {
            if (var4 == -1 && this.iOffsets[var7] >= var1) {
               var4 = var7;
            }

            if (this.iOffsets[var7] > var2) {
               var5 = var7 - 1;
               break;
            }

            if (this.iOffsets[var7] == var2) {
               var5 = var7;
               break;
            }
         }

         int var11 = var5 - var4;
         int var8;
         int var9;
         switch (var11) {
            case -2:
               var8 = var4 + 1;
               var9 = var5 + 1;
               this.iOffsets[var4] = var1;
               this.iAttributes[var4] = this.iAttributes[var5];
               break;
            case -1:
            default:
               var9 = var4;
               var8 = var4;
               break;
            case 0:
               var8 = var9 = var5 + 1;
               this.iOffsets[var5] = var1;
         }

         int var10 = var2 - var1;

         while (var9 < this.iOffsetNo) {
            this.iOffsets[var8] = this.iOffsets[var9] - var10;
            this.iAttributes[var8 - 1] = this.iAttributes[var9 - 1];
            var9++;
            var8++;
         }

         if (var11 >= 0) {
            this.iOffsetNo -= var5 - var4;
         }

         if (this.iOffsetNo < 2) {
            this.iOffsetNo = 0;
         }

         this.insertOffsets(var4, var1, var3);
         this.combineRuns();
      }
   }

   private final void insertOffsets(int var1, int var2, AttribRunList var3) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private final void insertOffset(int var1, int var2) {
      int var3 = this.iOffsetNo;
      this.iOffsetNo += var1;
      this.insureSize(this.iOffsetNo);
      if (var1 != 0 && var2 >= 0 && var2 < var3) {
         int var4 = var2 == 0 ? var2 : var2 - 1;
         int var5 = var3 - var4 - 1;
         System.arraycopy(this.iOffsets, var4 + 1, this.iOffsets, var4 + var1 + 1, var5);
         System.arraycopy(this.iAttributes, var4, this.iAttributes, var4 + var1, var5);
      }
   }

   private final void insureSize(int var1) {
      if (var1 > this.iOffsets.length) {
         Array.resize(this.iOffsets, this.iOffsetNo + 10);
         Array.resize(this.iAttributes, this.iOffsetNo + 10);
      }
   }

   private final void combineRuns() {
      int var1 = 0;
      int var2 = this.iOffsetNo - 1;

      for (int var3 = 1; var3 < var2; var3++) {
         if (this.iAttributes[var3 - 1] == this.iAttributes[var3]) {
            this.iOffsetNo--;
         } else {
            var1++;
         }

         if (var1 != var3) {
            this.iOffsets[var1 + 1] = this.iOffsets[var3 + 1];
            this.iAttributes[var1] = this.iAttributes[var3];
         }
      }
   }

   final void setAttrib(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final AttribRunList sublist(int var1, int var2) {
      return new AttribRunList(this, var1, var2);
   }
}
