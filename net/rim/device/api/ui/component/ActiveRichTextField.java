package net.rim.device.api.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.AbstractStringWrapper;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.StringPatternContainer;
import net.rim.device.api.util.StringPatternRepository$Internal;
import net.rim.device.internal.ui.FormatParams;
import net.rim.tid.text.AttributedString;
import net.rim.vm.Array;

public class ActiveRichTextField extends RichTextField implements CookieProvider {
   private StringPatternContainer _patterns;
   protected long[] _cookieID;
   protected IntHashtable _cookieIDs;
   private SmileySupport _smileySupport;
   static final int MAX_IDLE_TIMEOUT;
   public static final int SCANFLAG_DISABLE_ALL_THREADING;
   public static final int SCANFLAG_THREAD_ON_CREATE;
   private static int _scanFlags;
   protected static final long INVALID_COOKIE_ID;

   protected void executeBackgroundScan() {
      BackgroundScanThread.post(new ActiveRichTextField$StringPatternScanner(this, null));
   }

   protected Object getContextMenuContext() {
      return null;
   }

   protected int drawText(Graphics var1, String var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return 0;
   }

   protected Manager getMainScreenManager() {
      return null;
   }

   protected MenuItem addCookieMenuItems(CookieProvider var1, int var2, ContextMenu var3, Object var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected MenuItem addCookieMenuItems(CookieProvider var1, Object var2, ContextMenu var3, Object var4) {
      return ActiveRegionSupport.addCookieMenuItems(var1, var2, var3, var4);
   }

   public boolean regionHasCookie() {
      return super._arSupport.isInCookieRegion(this.getCursorPosition());
   }

   public boolean regionHasCookie(int var1) {
      return this._cookieIDs == null ? false : this._cookieIDs.get(var1) != null;
   }

   public void setText(String var1, int[] var2, byte[] var3, Font[] var4, int[] var5, int[] var6) {
      this.setText(var1, scanForActiveRegions(var1, var2, var3, var4, this.getFont(), var5, var6, this.getLabelLength(), this._patterns));
   }

   protected void setText(String var1, ActiveRichTextField$RegionQueue var2) {
      this._cookieID = var2.getSingleCookieRegions();
      this._cookieIDs = var2.cookieID;
      super.setText(var1, var2.offsets, var2.attributes, var2.fonts, null);
      this.setAttributes(var2.foregroundColors, var2.backgroundColors);
   }

   protected int super_scrollVertically(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void setText(String var1, String var2, Font[] var3, int[] var4) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public Object getCookieWithFocus() {
      return super._arSupport.getCookieWithFocus(this.getCursorPosition());
   }

   public static int getScanFlags() {
      return _scanFlags;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   protected void makeContextMenu(ContextMenu var1, int var2) {
      super.makeContextMenu(var1, var2);
      if (var2 != 65537) {
         int var3 = super._arSupport.getCookieWithFocusId(this.getCursorPosition());
         MenuItem var4 = this.addCookieMenuItems(this, var3, var1, this.getContextMenuContext());
         if (var4 != null) {
            var1.setDefaultItem(var4);
            return;
         }

         AbstractStringWrapper var5 = AbstractStringWrapper.createInstance(this.getText());
         int var6 = this.getCursorPosition();
         ActiveRegionSupport.addCookieMenuItems(this, var5, 0, var6, var5.length(), this._patterns, var1, null, var2);
      }
   }

   public ActiveRichTextField(String var1) {
      this(var1, 0);
   }

   protected ActiveRichTextField(String var1, long var2, StringPatternContainer var4, ActiveRichTextField$RegionQueue var5) {
      super(var1, var5.offsets, var5.attributes, var5.fonts, null, var2);
      this._smileySupport = (SmileySupport)(new Object(this));
      this.setAttributes(var5.foregroundColors, var5.backgroundColors);
      this._cookieID = var5.getSingleCookieRegions();
      this._cookieIDs = var5.cookieID;
      this._patterns = var4;
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (this.isSelecting()) {
         XYRect var7 = Ui.getTmpXYRect();
         super.getFocusRect(var7);
         var1.pushContext(var7, 0, 0);
         Ui.returnTmpXYRect(var7);
         super.drawFocus(var1, var2);
         var1.popContext();
      } else if (!this.regionHasCookie()) {
         XYRect var6 = Ui.getTmpXYRect();
         super.getFocusRect(var6);
         var1.pushContext(var6, 0, 0);
         Ui.returnTmpXYRect(var6);
         super.drawFocus(var1, var2);
         var1.popContext();
      } else {
         this.highlightSelectedArea(var1, var2, super._arSupport.getSameCookieRunStart(this), super._arSupport.getSameCookieRunEnd(this));
         if (!this.getScreen().isScrollBehaviourView()) {
            XYRect var3 = Ui.getTmpXYRect();
            super.getFocusRect(var3);
            int var4 = var3.y;
            int var5 = var3.height >> 2;
            var4 += var3.height - var5;
            this.drawHighlightRegion(var1, 1, var2, var3.x, var4, var3.width, var5);
            Ui.returnTmpXYRect(var3);
         }
      }
   }

   @Override
   public void getFocusRect(XYRect var1) {
      super.getFocusRect(var1);
      super._arSupport.getFocusRect(var1, this);
   }

   private static ActiveRichTextField$RegionQueue scanForActiveRegions(String var0, Font var1, int var2, StringPatternContainer var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static ActiveRichTextField$RegionQueue scanForActiveRegions(
      String var0, int[] var1, byte[] var2, Font[] var3, Font var4, int[] var5, int[] var6, int var7, StringPatternContainer var8
   ) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected int scrollVertically(int var1) {
      if (var1 == 0) {
         return 0;
      }

      if (this.isSelecting()) {
         return super.scrollVertically(var1);
      }

      if (this.getScreen().isScrollBehaviourView()) {
         boolean var4;
         if (var1 < 0) {
            var4 = super._arSupport.scrollToPrevActiveRegion(this, this.getCursorPosition());
         } else {
            var4 = super._arSupport.scrollToNextActiveRegion(this, this.getCursorPosition());
         }

         if (var4) {
            this.setCursorPosition(super._arSupport.getRunStart(), 0);
            return 0;
         } else {
            return var1;
         }
      } else {
         var1 = super._arSupport.scrollVertically(var1, this, this.getCursorLine()._line);
         if (var1 != 0) {
            return super.scrollVertically(var1 + super._arSupport.getAdjustedAmount());
         }

         super.scrollVertically(super._arSupport.getAdjustedAmount());
         if (super._arSupport.endsOnCookie()) {
            int var2 = MathUtilities.clamp(super._arSupport.getRunStart(), this.getCaretPosition(), super._arSupport.getRunEnd() - 1);
            this.setSelection(var2, true, var2);
         }

         return 0;
      }
   }

   @Override
   public void selectionCopy(Clipboard var1) {
      if (!this.isSelecting() && super._arSupport.isInCookieRegion(this.getCaretPosition())) {
         var1.put(super._arSupport.getCurrentRegionText(this.getCaretPosition(), super._text));
      } else {
         super.selectionCopy(var1);
      }
   }

   @Override
   public boolean isSelectionCopyable() {
      return this.isSelecting() ? super.isSelectionCopyable() : super._arSupport.isInCookieRegion(this.getCaretPosition());
   }

   public static void setScanFlags(int var0) {
      _scanFlags = var0;
   }

   @Override
   public void setText(String var1) {
      this.setText(var1, scanForActiveRegions(var1, this.getFont(), this.getLabelLength(), this._patterns));
   }

   @Override
   public void setText(String var1, int[] var2, byte[] var3, Font[] var4) {
      this.setText(var1, var2, var3, var4, null, null);
   }

   public ActiveRichTextField(String var1, long var2) {
      this(var1, null, null, null, null, null, var2);
   }

   @Override
   public Object getRegionCookie() {
      return super._arSupport.getCookieWithFocus(this.getCursorPosition());
   }

   private void setText(AttributedString var1, ActiveRichTextField$RegionQueue var2) {
      this._cookieID = var2.getSingleCookieRegions();
      this._cookieIDs = var2.cookieID;
      super.setText(var1, var2.offsets, var2.attributes, var2.fonts, null);
      this.setAttributes(var2.foregroundColors, var2.backgroundColors);
   }

   private void setTextFromBackgroundScanner(String var1, int[] var2, byte[] var3, Font[] var4, int[] var5, int[] var6, ActiveRichTextField$RegionQueue var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Object getRegionCookie(int var1) {
      return super._arSupport.getCookieForRegionIndex(var1);
   }

   @Override
   public void setAttributes(int[] var1, int[] var2) {
      if (var1 != null) {
         this.correctColorArray(var1);
      }

      if (var2 != null) {
         this.correctColorArray(var2);
      }

      super.setAttributes(var1, var2);
   }

   private void correctColorArray(int[] var1) {
      Font[] var2 = this.getFonts(false);
      if (var2.length - var1.length != 1) {
         Array.resize(var1, var2.length);
      } else {
         Array.resize(var1, var2.length);
         var1[var2.length - 1] = -1;
      }
   }

   public ActiveRichTextField(String var1, int[] var2, byte[] var3, Font[] var4, int[] var5, int[] var6, long var7, StringPatternContainer var9) {
   }

   @Override
   protected void notifyTextChanged(FormatParams var1, boolean var2) {
      if (var2) {
         this._smileySupport.scanForSmileys(var1);
      }

      super.notifyTextChanged(var1, var2);
   }

   @Override
   public String getText(int var1, int var2) {
      return !this._smileySupport.isSmileyAvailable() ? super.getText(var1, var2) : this._smileySupport.getDecodedString(var1, var1 + var2);
   }

   @Override
   public int getDecodedTextLength(int var1, int var2) {
      return this._smileySupport != null && this._smileySupport.isSmileyAvailable() ? this._smileySupport.getDecodedStringLength(var1, var2) : var2 - var1;
   }

   public ActiveRichTextField(String var1, int[] var2, byte[] var3, Font[] var4, int[] var5, int[] var6, long var7) {
      this(var1, var2, var3, var4, var5, var6, var7, StringPatternRepository$Internal.getStringPatterns());
   }

   @Override
   public boolean isCookieValid(int var1) {
      return this._cookieIDs != null && this._cookieIDs.containsKey(var1) && var1 >= 0 && this._cookieIDs.get(var1) != null;
   }

   @Override
   public Object getCookie(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
