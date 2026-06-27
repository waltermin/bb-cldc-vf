package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.MediaIcon;
import net.rim.vm.Array;

class SmileySymbolScreen$SmileySymbolField extends SymbolScreen$SymbolField {
   private final SmileySymbolScreen this$0;

   protected SmileySymbolScreen$SmileySymbolField(SmileySymbolScreen var1) {
      super(var1);
      this.this$0 = var1;
   }

   @Override
   protected int getMaxSymbolHeight() {
      return Math.max(super.getMaxSymbolHeight(), SmileySymbolScreen._smileyFacility.emoticonSize());
   }

   @Override
   protected int getMaxSymbolWidth() {
      return Math.max(super.getMaxSymbolWidth(), SmileySymbolScreen._smileyFacility.emoticonSize());
   }

   @Override
   protected int getNumPages() {
      return super.getNumPages() + SmileySymbolScreen._smileyFacility.emoticonScreenLayouts().length;
   }

   @Override
   protected int[] getPages() {
      int[] var1 = super.getPages();
      int[][] var2 = SmileySymbolScreen._smileyFacility.emoticonScreenLayouts();
      if (var2 != null) {
         int var3 = var1.length;
         Array.resize(var1, var3 + var2.length);
         int var4 = super.getNumPages();

         for (int var5 = var4; var5 < var4 + var2.length; var5++) {
            var1[var3] = var5;
            var3++;
         }
      }

      return var1;
   }

   @Override
   protected void initCurrentPageMap(int var1) {
      if (var1 < super._pagesStandard) {
         super.initCurrentPageMap(var1);
      } else {
         this.updatePageNumber();
         int[] var2 = SmileySymbolScreen._smileyFacility.emoticonScreenLayouts()[var1 - super._pagesStandard];
         super._map.clear();

         for (int var3 = var2.length - 1; var3 >= 0; var3--) {
            super._map.put(65 + var3, var2[var3]);
         }

         super._map.put(90, 8646);
         this.updateDescription();
      }
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      int var4 = super.moveFocus(var1, var2, var3);
      this.updateDescription();
      return var4;
   }

   @Override
   protected void updateDescription() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void paintSymbol(Graphics var1, char var2, int var3, int var4, int var5, int var6) {
      boolean var7 = InternalServices.isReducedFormFactor();
      int var8 = super._keyWidth;
      if (var2 == 8646) {
         int var9 = super._currentPage < this.getNumPagesAvailable() - 1 ? 0 : 4;
         if (var9 == 0 || var9 == 4) {
            MediaIcon.COLLECTION.paint(var1, var3, var4, var5, var6, var9);
            return;
         }
      } else {
         if (super._pages[super._currentPage] < super._pagesStandard) {
            var1.drawText(var2, var3 + (var2 == 8646 && !var7 ? var8 - super._keyWidth >> 1 : 0), var4, 4, super._keyWidth - 1);
            return;
         }

         int var12 = SmileySymbolScreen._smileyFacility.emoticonSize();
         int var10 = var3 + (var5 - var12 >> 1);
         int var11 = var4 + (var6 - var12 >> 1);
         SmileySymbolScreen._smileyFacility.drawEmoticon(var1, var2, var10, var11);
      }
   }

   @Override
   protected void reset() {
      super.reset();
      this.setDescription(null);
   }

   @Override
   void send(char var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
