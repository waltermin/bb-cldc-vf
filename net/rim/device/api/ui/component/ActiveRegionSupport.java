package net.rim.device.api.ui.component;

import java.util.Vector;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.FactoryUtil;
import net.rim.device.api.util.StringPatternContainer;
import net.rim.device.internal.ui.ArticInterface$Line;
import net.rim.device.internal.ui.ArticInterface$LineInfo;
import net.rim.device.internal.ui.Formatter;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

class ActiveRegionSupport {
   private int _currentRunIndex;
   private int _currentRunStart;
   private AttributedString$Iterator _runIterator;
   private ActiveRegionSupport$ActiveRegionFieldIf _arField;
   private int _adjustedAmount;
   private boolean _endsOnCookie;
   private static final long COOKIES_ATTRIB_MASK;
   private static final long COOKIES_ATTRIB_SHIFT;

   ActiveRegionSupport(AttributedString$Iterator var1, ActiveRegionSupport$ActiveRegionFieldIf var2) {
      this._runIterator = var1;
      this._arField = var2;
      this.init();
   }

   public boolean isInCookieRegion(int var1) {
      this.adjustCurrentRun(var1);
      return this.isInCookieRegion();
   }

   private boolean isInCookieRegion() {
      long var1 = this._runIterator.runXAttrib() & 65504;
      return var1 != 0 && this._arField.isCookieValid((int)(var1 >> 5) - 1);
   }

   public int getRunStart() {
      return this._currentRunStart;
   }

   public int getRunEnd() {
      return this._currentRunStart + this._runIterator.runLength();
   }

   public long getRunAttrib() {
      return this._runIterator.runAttrib();
   }

   public long getRunXAttrib() {
      return this._runIterator.runXAttrib();
   }

   public int pos() {
      return this._runIterator.pos();
   }

   public boolean nextActiveRegion() {
      boolean var1 = true;

      while (var1) {
         if (this.isInCookieRegion()) {
            return true;
         }

         var1 = this.nextRun();
      }

      return false;
   }

   public boolean prevActiveRegion() {
      boolean var1 = true;

      while (var1) {
         if (this.isInCookieRegion()) {
            return true;
         }

         var1 = this.prevRun();
      }

      return false;
   }

   public void getFocusRect(XYRect var1, TextField var2) {
      if (this.isInCookieRegion(var2.getCaretPosition())) {
         int var3 = this.getSameCookieRunStart(this._arField);
         int var4 = this.getSameCookieRunEnd(this._arField);
         if (var3 < var4) {
            XYRect var5 = Ui.getTmpXYRect();
            ArticInterface$LineInfo var6 = var2.getLineInfoForDocPos(var3, true);
            ArticInterface$Line var7 = var6._line;
            int var8 = var6._top;

            for (int var9 = var6._start; var7 != null && var9 < var4; var7 = var7._next) {
               int var10 = var9 <= var3 ? var3 : var9;
               int var11 = var9 + var7._textLength > var4 ? var4 : var9 + var7._textLength;
               Formatter.getTextBounds(var10, var11, var5, var7, var9, var8);
               var1.union(var5);
               var8 += var5.height;
               var9 += var7._textLength + var7._skippedCharacters;
            }

            Ui.returnTmpXYRect(var5);
         }
      }
   }

   public int scrollVertically(int var1, TextField var2, ArticInterface$Line var3) {
      this._adjustedAmount = 0;
      this._endsOnCookie = true;
      if (var1 > 0) {
         for (boolean var6 = this.scrollToNextActiveRegion((ActiveRegionSupport$ActiveRegionFieldIf)var2, var2.getCaretPosition());
            var6;
            var6 = this.nextActiveRegion()
         ) {
            int var4 = this._currentRunStart;
            ArticInterface$LineInfo var7 = var2.getLineInfoForDocPos(var4, true);
            ArticInterface$Line var8 = var7._line;
            int var9 = 0;

            for (int var10 = var1; var10 > 0 && var8 != var3; var9++) {
               var3 = var3._next;
               var10--;
            }

            if (var8 != var3) {
               this._endsOnCookie = false;
            }

            this._adjustedAmount += var9;
            var1 -= Math.max(1, var9);
            var3 = var8;
            if (var1 == 0) {
               return var1;
            }

            this.nextRun();
         }
      } else {
         for (boolean var12 = this.scrollToPrevActiveRegion((ActiveRegionSupport$ActiveRegionFieldIf)var2, var2.getCaretPosition());
            var12;
            var12 = this.prevActiveRegion()
         ) {
            int var11 = this.getRunEnd() - 1;
            ArticInterface$LineInfo var13 = var2.getLineInfoForDocPos(var11, true);
            ArticInterface$Line var14 = var13._line;
            int var15 = 0;

            for (int var16 = -var1; var16 > 0 && var14 != var3; var15++) {
               var3 = var3._prev;
               var16--;
            }

            if (var14 != var3) {
               this._endsOnCookie = false;
            }

            this._adjustedAmount -= var15;
            var1 += Math.max(1, var15);
            var3 = var14;
            if (var1 == 0) {
               return var1;
            }

            this.prevRun();
         }
      }

      return var1;
   }

   public int getAdjustedAmount() {
      return this._adjustedAmount;
   }

   public boolean endsOnCookie() {
      return this._endsOnCookie;
   }

   public int getRegion(int var1) {
      return !this.adjustCurrentRun(var1) ? -1 : this._currentRunIndex;
   }

   public String getRegionText(int var1, AttributedString var2) {
      return !this.adjustCurrentRunForIndex(var1) ? null : var2.getText(this._currentRunStart, this.getRunEnd());
   }

   public String getCurrentRegionText(int var1, AttributedString var2) {
      this.adjustCurrentRun(var1);
      return var2.getText(this._currentRunStart, this.getRunEnd());
   }

   public void init() {
      this._currentRunStart = 0;
      this._currentRunIndex = 0;
      this._runIterator.set(0, this._runIterator.text().length());
   }

   public boolean adjustCurrentRunForIndex(int var1) {
      while (this._currentRunIndex < var1) {
         if (!this.nextRun()) {
            return false;
         }
      }

      while (this._currentRunIndex > var1) {
         if (!this.prevRun()) {
            return false;
         }
      }

      return true;
   }

   public static void addCookieMenuItems(
      CookieProvider var0, AbstractString var1, int var2, int var3, int var4, StringPatternContainer var5, ContextMenu var6, Object var7, int var8
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static MenuItem addCookieMenuItems(CookieProvider var0, Object var1, ContextMenu var2, Object var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static MenuItem cookieToMenuItem(ActiveFieldCookie var0, CookieProvider var1, ContextMenu var2, Object var3) {
      MenuItem var4 = null;
      Object var5 = new Object();
      var4 = var0.getFocusVerbs(var1, var3, (Vector)var5);
      int var6 = ((Vector)var5).size();

      for (int var7 = 0; var7 < var6; var7++) {
         Object var8 = ((Vector)var5).elementAt(var7);
         if (var2 != null) {
            var2.addItem((MenuItem)var8);
         }
      }

      return var4;
   }

   private static Object createCookie(long var0, String var2) {
      ActiveFieldContext var3 = new ActiveFieldContext(var2);
      var3.setID(var0);
      return FactoryUtil.createInstance(var0, var3);
   }

   public Object getCookieWithFocus(int var1) {
      if (!this.isInCookieRegion(var1)) {
         return null;
      }

      int var2 = (int)((this._runIterator.runXAttrib() & 65504) >> 5) - 1;
      return this._arField.getCookie(var2);
   }

   public int getCookieWithFocusId(int var1) {
      return !this.isInCookieRegion(var1) ? -1 : (int)((this._runIterator.runXAttrib() & 65504) >> 5) - 1;
   }

   public Object getCookieForRegionIndex(int var1) {
      if (!this.adjustCurrentRunForIndex(var1)) {
         return null;
      }

      int var2 = (int)((this._runIterator.runXAttrib() & 65504) >> 5) - 1;
      return this._arField.getCookie(var2);
   }

   private boolean adjustCurrentRun(int var1) {
      while (var1 >= this._currentRunStart + this._runIterator.runLength()) {
         if (!this.nextRun()) {
            return false;
         }
      }

      while (this._currentRunStart > var1) {
         if (!this.prevRun()) {
            return false;
         }
      }

      return true;
   }

   public boolean scrollToNextActiveRegion(ActiveRegionSupport$ActiveRegionFieldIf var1, int var2) {
      boolean var3 = true;
      if (this.isInCookieRegion(var2)) {
         var3 = this.nextDifferentCookieRun(var1);
      }

      return var3 && this.nextActiveRegion();
   }

   public boolean scrollToPrevActiveRegion(ActiveRegionSupport$ActiveRegionFieldIf var1, int var2) {
      boolean var3 = true;
      if (this.isInCookieRegion(var2)) {
         var3 = this.prevDifferentCookieRun(var1);
      }

      return var3 && this.prevActiveRegion();
   }

   public int getSameCookieRunStart(ActiveRegionSupport$ActiveRegionFieldIf var1) {
      int var2 = this._currentRunIndex;
      int var3 = this._currentRunStart;

      while (this.prevRun() && var1.regionsHaveSameCookie(this._currentRunIndex, this._currentRunIndex + 1)) {
         var3 = this._currentRunStart;
      }

      this.adjustCurrentRunForIndex(var2);
      return var3;
   }

   public int getSameCookieRunEnd(ActiveRegionSupport$ActiveRegionFieldIf var1) {
      int var2 = this._currentRunIndex;
      int var3 = this._currentRunStart + this._runIterator.runLength();

      while (this.nextRun() && var1.regionsHaveSameCookie(this._currentRunIndex - 1, this._currentRunIndex)) {
         var3 = this._currentRunStart + this._runIterator.runLength();
      }

      this.adjustCurrentRunForIndex(var2);
      return var3;
   }

   Object createCookie(AttributedString var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean nextDifferentCookieRun(ActiveRegionSupport$ActiveRegionFieldIf var1) {
      while (this.nextRun()) {
         if (!var1.regionsHaveSameCookie(this._currentRunIndex - 1, this._currentRunIndex)) {
            return true;
         }
      }

      return false;
   }

   private boolean prevDifferentCookieRun(ActiveRegionSupport$ActiveRegionFieldIf var1) {
      while (this.prevRun()) {
         if (!var1.regionsHaveSameCookie(this._currentRunIndex, this._currentRunIndex + 1)) {
            return true;
         }
      }

      return false;
   }

   private boolean nextRun() {
      boolean var1 = this._runIterator.next();
      if (var1) {
         this._currentRunStart = this._runIterator.pos();
         this._currentRunIndex++;
      }

      return var1;
   }

   private boolean prevRun() {
      boolean var1 = this._runIterator.prev();
      if (var1) {
         this._currentRunStart = this._runIterator.pos();
         this._currentRunIndex--;
      }

      return var1;
   }
}
