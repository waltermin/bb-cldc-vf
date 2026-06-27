package net.rim.device.api.util;

import java.util.Vector;

public final class StringPatternEnumerator {
   private StringPatternContainer _patterns;
   private AbstractString _string;
   private StringPattern$Match _nextMatch = new StringPattern$Match();
   private boolean _nextMatchIsValid;
   private int[] _beginIndicies;
   private int[] _endIndicies;
   private long[] _matchIndicies;
   private int[] _prefixLengths;
   int _beginIndex;
   int _prevEndIndex;
   int _finalEndIndex;
   private boolean _endOfEnum;
   private Vector _queuedMatches;

   public StringPatternEnumerator(Object var1, StringPatternContainer var2) {
      this._patterns = var2;
      int var3 = this._patterns.size();
      this._beginIndicies = new int[var3];
      this._endIndicies = new int[var3];
      this._matchIndicies = new long[var3];
      this._prefixLengths = new int[var3];
      this._queuedMatches = (Vector)(new Object(1));
      if (var1 == null) {
         this._string = null;
         this._endOfEnum = true;
      } else {
         this.setStringToScan(var1);
         this.resetScanRange(0, this._string.length());
      }
   }

   public StringPatternEnumerator(Object var1, int var2, int var3, StringPatternContainer var4) {
      this(var1, var4);
      if (this._string != null) {
         this.resetScanRange(var2, var3);
      }
   }

   public final void reset() {
      this.resetScanRange(this._beginIndex, this._finalEndIndex);
   }

   public final void reset(Object var1) {
      this.setStringToScan(var1);
      this.resetScanRange(0, this._string.length());
   }

   public final void reset(Object var1, int var2, int var3) {
      this.setStringToScan(var1);
      this.resetScanRange(var2, var3);
   }

   public final boolean hasMoreMatches() {
      if (this._endOfEnum) {
         return false;
      }

      if (!this._nextMatchIsValid) {
         this.findNextMatch();
         if (!this._nextMatchIsValid) {
            this._endOfEnum = true;
            return false;
         }
      }

      return true;
   }

   public final boolean nextMatch(StringPattern$Match var1) {
      if (!this.hasMoreMatches()) {
         return false;
      } else {
         var1.setMatch(this._nextMatch);
         if (this._queuedMatches.size() > 0) {
            this._nextMatch.setMatch((StringPattern$Match)this._queuedMatches.elementAt(0));
            this._queuedMatches.removeElementAt(0);
            return true;
         } else {
            this._prevEndIndex = this._nextMatch.endIndex;
            this._nextMatchIsValid = false;
            return true;
         }
      }
   }

   private final void findNextMatch() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void setStringToScan(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void resetScanRange(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
