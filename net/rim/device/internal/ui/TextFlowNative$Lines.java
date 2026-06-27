package net.rim.device.internal.ui;

import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

public final class TextFlowNative$Lines {
   private int _count = 0;
   private short[] _length = new short[0];
   private short[] _xOffset = new short[0];
   private short[] _width = new short[0];
   private short[] _widthNominal = new short[0];
   private byte[] _flags = new byte[0];
   private short[] _baseline = new short[0];
   private short[] _height = new short[0];
   private short[] _cellId = new short[0];
   private short[] _bidiStateIndex = new short[0];
   private byte[] _bidiState = new byte[0];
   private int _bidiStateCount = 0;
   public static final int KParStartFlag;
   public static final int KParEndFlag;
   public static final int KForcedStartFlag;
   public static final int KForcedEndFlag;
   public static final int KRightToLeftFlag;
   public static final int KNeedsBidiReorderingFlag;

   public final void reset() {
      this._count = 0;
      this._bidiStateCount = 0;
   }

   private final void grow(int var1, int var2) {
      if (this._length.length < var1) {
         int var3 = this._length.length;
         int var4 = Math.max(var1, this._length.length + Array.getSectionSize(this._length));
         Array.resize(this._length, var4);
         Array.resize(this._xOffset, var4);
         Array.resize(this._width, var4);
         Array.resize(this._widthNominal, var4);
         Array.resize(this._flags, var4);
         Array.resize(this._baseline, var4);
         Array.resize(this._height, var4);
         Array.resize(this._cellId, var4);
         Arrays.fill(this._cellId, (short)0, var3, var4 - var3);
         Array.resize(this._bidiStateIndex, var4);
      }

      this._count = var1;
      if (this._bidiState.length < var2) {
         int var5 = Math.max(var2, this._bidiState.length + Array.getSectionSize(this._bidiState));
         Array.resize(this._bidiState, var5);
      }

      this._bidiStateCount = var2;
   }

   public final void append(TextFlowNative$Lines var1, int var2, int var3) {
      this.replace(this._count, 0, var1, var2, var3);
   }

   public final void append(TextFlowNative$Lines var1) {
      this.replace(this._count, 0, var1, 0, var1._count);
   }

   public final void replace(int var1, int var2, TextFlowNative$Lines var3) {
      this.replace(var1, var2, var3, 0, var3._count);
   }

   public final void replace(int var1, int var2, TextFlowNative$Lines var3, int var4, int var5) {
      if (var1 < 0 || var2 < 0 || var1 + var2 > this._count) {
         throw new Object();
      }

      if (var4 >= 0 && var5 >= 0 && var4 + var5 <= var3._count) {
         int var6 = this._count;
         int var7 = this._count + var5 - var2;
         int var8 = this._bidiStateCount;
         int var9 = var1 < this._count ? this._bidiStateIndex[var1] : this._bidiStateCount;
         int var10 = var1 + var2 < this._count ? this._bidiStateIndex[var1 + var2] : this._bidiStateCount;
         int var11 = var10 - var9;
         int var12 = var4 < var3._count ? var3._bidiStateIndex[var4] : var3._bidiStateCount;
         int var13 = var4 + var5 < var3._count ? var3._bidiStateIndex[var4 + var5] : var3._bidiStateCount;
         int var14 = var13 - var12;
         int var15 = this._bidiStateCount + var14 - var11;
         if (var7 != var6 || var15 != var8) {
            this.grow(var7, var15);
            int var16 = var1 + var2;
            int var17 = var1 + var5;
            int var18 = var6 - var16;
            if (var18 > 0) {
               System.arraycopy(this._length, var16, this._length, var17, var18);
               System.arraycopy(this._xOffset, var16, this._xOffset, var17, var18);
               System.arraycopy(this._width, var16, this._width, var17, var18);
               System.arraycopy(this._widthNominal, var16, this._widthNominal, var17, var18);
               System.arraycopy(this._flags, var16, this._flags, var17, var18);
               System.arraycopy(this._baseline, var16, this._baseline, var17, var18);
               System.arraycopy(this._height, var16, this._height, var17, var18);
               System.arraycopy(this._cellId, var16, this._cellId, var17, var18);
               System.arraycopy(this._bidiStateIndex, var16, this._bidiStateIndex, var17, var18);
            }

            var16 = var9 + var11;
            var17 = var9 + var14;
            var18 = var8 - var16;
            if (var18 > 0) {
               System.arraycopy(this._bidiState, var16, this._bidiState, var17, var18);
               int var19 = var17 - var16;

               for (int var20 = var1 + var5; var20 < var7; var20++) {
                  this._bidiStateIndex[var20] = (short)(this._bidiStateIndex[var20] + var19);
               }
            }
         }

         if (var5 > 0) {
            System.arraycopy(var3._length, var4, this._length, var1, var5);
            System.arraycopy(var3._xOffset, var4, this._xOffset, var1, var5);
            System.arraycopy(var3._width, var4, this._width, var1, var5);
            System.arraycopy(var3._widthNominal, var4, this._widthNominal, var1, var5);
            System.arraycopy(var3._flags, var4, this._flags, var1, var5);
            System.arraycopy(var3._baseline, var4, this._baseline, var1, var5);
            System.arraycopy(var3._height, var4, this._height, var1, var5);
            System.arraycopy(var3._cellId, var4, this._cellId, var1, var5);
            System.arraycopy(var3._bidiStateIndex, var4, this._bidiStateIndex, var1, var5);
         }

         if (var14 > 0) {
            System.arraycopy(var3._bidiState, var12, this._bidiState, var9, var14);
         }

         int var22 = var9 - var12;
         int var24 = var1 + var5;

         for (int var26 = var1; var26 < var24; var26++) {
            this._bidiStateIndex[var26] = (short)(this._bidiStateIndex[var26] + var22);
         }
      } else {
         throw new Object();
      }
   }

   public final void appendVerticalSpace(int var1, short var2, short var3, short var4) {
      throw new RuntimeException("cod2jar: unsupported opcode");
   }

   public final void appendZeroHeightCharacters(int var1, short var2, short var3, short var4) {
      throw new RuntimeException("cod2jar: unsupported opcode");
   }

   public final void appendZeroHeightZeroWidthCharacter(short var1, short var2) {
      int var3 = this._count;
      this.grow(this._count + 1, this._bidiStateCount);
      this._length[var3] = 0;
      this._xOffset[var3] = var1;
      this._flags[var3] = 3;
      this._width[var3] = 0;
      this._widthNominal[var3] = 0;
      this._baseline[var3] = 0;
      this._height[var3] = 0;
      this._cellId[var3] = var2;
      this._bidiStateIndex[var3] = (short)this._bidiStateCount;
   }

   public final int getCount() {
      return this._count;
   }

   public final short[] getLengths() {
      return this._length;
   }

   public final short[] getXOffsets() {
      return this._xOffset;
   }

   public final short[] getWidths() {
      return this._width;
   }

   public final short[] getWidthsNominal() {
      return this._widthNominal;
   }

   public final byte[] getFlags() {
      return this._flags;
   }

   public final short[] getBaselines() {
      return this._baseline;
   }

   public final short[] getHeights() {
      return this._height;
   }

   public final short[] getCellIds() {
      return this._cellId;
   }

   public final byte[] getBidiState(int var1) {
      short var2 = this._bidiStateIndex[var1];
      int var3 = (var1 + 1 < this._count ? this._bidiStateIndex[var1 + 1] : this._bidiStateCount) - var2;
      if (var3 <= 0) {
         return null;
      }

      byte[] var4 = new byte[var3];

      for (int var5 = 0; var5 < var3; var5++) {
         var4[var5] = this._bidiState[var2 + var5];
      }

      return var4;
   }
}
