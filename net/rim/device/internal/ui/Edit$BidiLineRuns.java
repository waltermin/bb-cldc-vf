package net.rim.device.internal.ui;

import net.rim.device.api.util.Arrays;

public class Edit$BidiLineRuns {
   public int[] _runs;
   public byte[] _bidiState;
   private boolean _ignoreState = true;
   public static final int PARAGRAPH_DIR_LTR;
   public static final int PARAGRAPH_DIR_RTL;
   public static final int PARAGRAPH_DIR_FORCED_LTR;
   public static final int PARAGRAPH_DIR_FORCED_RTL;

   Edit$BidiLineRuns() {
      this._runs = new int[0];
      this._bidiState = new byte[0];
   }

   private Edit$BidiLineRuns(int[] var1, byte[] var2) {
      this._runs = var1;
      this._bidiState = var2;
      this._ignoreState = false;
   }

   public void ignore(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isIgnored() {
      return this._ignoreState;
   }

   public Edit$BidiLineRuns clone() {
      return this._ignoreState
         ? null
         : new Edit$BidiLineRuns(Arrays.copy(this._runs), this._bidiState != null && this._bidiState.length > 0 ? Arrays.copy(this._bidiState) : null);
   }

   public boolean isCompleteForwardRun() {
      if (this._runs.length == 3) {
         return this._runs[2] == 0;
      }

      int var1 = 0;

      for (int var2 = 0; var2 < this._runs.length; var2++) {
         if (this._runs[var2++] != var1) {
            return false;
         }

         var1 += this._runs[var2++];
         if (this._runs[var2] != 0) {
            return false;
         }
      }

      return true;
   }
}
