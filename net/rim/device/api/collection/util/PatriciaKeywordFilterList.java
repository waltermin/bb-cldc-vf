package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.util.BitSet;
import net.rim.device.api.util.StringUtilities;

public class PatriciaKeywordFilterList extends AbstractKeywordFilterList {
   private PatriciaTree _keywordTree;
   public boolean _haltSearch;

   public PatriciaKeywordFilterList(ReadableList var1, PatriciaTree var2) {
      super(var1);
      this._keywordTree = var2;
      this.setSearcher(new KeywordSearcher(this));
   }

   @Override
   protected void haltSearch() {
      this._haltSearch = true;
   }

   protected int[] getIDsBySortOrder() {
      throw null;
   }

   @Override
   public Object[] getElements(KeywordPrefixSearchResult var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean matches(Object var1) {
      return false;
   }

   @Override
   public void reset(Collection var1) {
      this.clearPrefixCache();
      this.clearFilteredElementList();
      Object var2 = this.getCriteria();
      if (var2 != null) {
         this.setCriteria(var2, null);
      } else {
         this.fireReset();
      }
   }

   @Override
   public synchronized KeywordPrefixSearchResult search(String[] var1) {
      KeywordPrefixCache var2 = this.getPrefixCache();
      this._haltSearch = false;
      byte var4 = 0;
      byte[] var5 = null;
      int var6 = var1.length;

      for (int var3 = 0; var3 < var6; var3++) {
         for (int var7 = 0; var7 < var6; var7++) {
            if (StringUtilities.startsWithIgnoreCase(var1[var3], var1[var7], 1701707776)) {
               var4++;
            }
         }
      }

      if (var4 > var6) {
         var5 = new byte[0];
         var2 = null;
      }

      if (this._haltSearch) {
         return null;
      }

      Object var17 = new Object();
      Object var8 = new Object();
      Object var10 = var17;
      PatriciaKeywordSearchResult var11 = new PatriciaKeywordSearchResult(this, var5, (BitSet)var8);

      for (int var12 = 0; var12 < var6; var12++) {
         String var13 = var1[var12];
         boolean var14 = false;
         if (var2 != null) {
            if (var12 == 0) {
               BitSet var15 = var2.getPrimaryEntry(var13);
               if (var15 != null) {
                  ((BitSet)var8).or(var15);
                  var15 = var2.getSecondaryEntry(var13);
                  if (var15 != null) {
                     ((BitSet)var10).or(var15);
                     var14 = true;
                  }
               }
            } else {
               BitSet var19 = var2.getSecondaryEntry(var13);
               if (var19 != null) {
                  ((BitSet)var10).or(var19);
                  var14 = true;
               }
            }
         }

         if (!var14) {
            var11._wordNumber = var12;
            var11._theSet = (BitSet)var10;
            this._keywordTree.search(var13, var11);
            if (var2 != null) {
               if (var12 == 0) {
                  var2.putPrimaryEntry(var13, (BitSet)var8);
               }

               var2.putSecondaryEntry(var13, (BitSet)var10);
            }
         }

         if (this._haltSearch) {
            return null;
         }

         if (var12 != 0) {
            ((BitSet)var17).and((BitSet)var10);
            if (var12 < var6 - 1) {
               ((BitSet)var10).reset();
            }
         } else if (var6 > 1) {
            var10 = new Object();
         }

         if (this._haltSearch) {
            return null;
         }
      }

      if (var5 != null) {
         for (int var16 = ((BitSet)var17).getFirstSet(); var16 != -1; var16 = ((BitSet)var17).getNextSet(var16 + 1)) {
            if (var5[var16] < var4) {
               ((BitSet)var17).fastClear(var16);
            }
         }
      }

      ((BitSet)var8).and((BitSet)var17);
      Object var9 = var17;
      ((BitSet)var9).xor((BitSet)var8);
      return this._haltSearch ? null : new KeywordPrefixSearchResult((BitSet)var8, (BitSet)var9);
   }
}
