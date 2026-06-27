package net.rim.device.api.collection.util;

import net.rim.device.api.util.BitSet;

public class KeywordPrefixSearchResult {
   private BitSet _primarySet;
   private BitSet _secondarySet;

   public KeywordPrefixSearchResult(BitSet var1, BitSet var2) {
      this.setResults(var1, var2);
   }

   public BitSet getPrimaryMatches() {
      return this._primarySet;
   }

   public BitSet getSecondaryMatches() {
      return this._secondarySet;
   }

   public final void setResults(BitSet var1, BitSet var2) {
      this._primarySet = var1;
      this._secondarySet = var2;
   }

   public int getMatchCount() {
      return this._primarySet.getNumSet() + this._secondarySet.getNumSet();
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
