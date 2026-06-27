package net.rim.device.api.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.EmoticonStringPattern;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.StringPattern;
import net.rim.device.api.util.StringPattern$Match;
import net.rim.device.api.util.StringPatternContainer;
import net.rim.device.api.util.StringPatternEnumerator;
import net.rim.device.api.util.StringPatternRepository$Internal;
import net.rim.device.internal.ui.FormatParams;
import net.rim.tid.text.AttributedString$Iterator;

public class ActiveAutoTextEditField extends AutoTextEditField implements CookieProvider, Runnable, ActiveRegionSupport$ActiveRegionFieldIf {
   private IntHashtable _cookieID;
   private int _regionCount;
   private boolean _invertCookieRegion = true;
   private boolean _snapToCookie = true;
   private int _pendingVersion;
   private StringPatternContainer _patterns;
   private StringPatternEnumerator _enumerator;
   private StringPattern$Match _match = (StringPattern$Match)(new Object());
   private int[] _pendingOffsets;
   private long[] _pendingCookieID;
   private int _pendingRegionCount;
   private Thread _waitingThread;
   private Thread _activeThread;
   private AttributedString$Iterator _threadSafeIterator;
   private ActiveRegionSupport _arSupport;
   private SmileySupport _smileySupport;
   private boolean _scanForSmileys = true;
   private boolean _inPostResults = false;
   private int[] _sectionLen = new int[3];
   private static final int REGION_INCREMENT;

   public void scanForActiveRegions() {
      this._pendingVersion++;
      if (this._waitingThread == null) {
         ThreadPool.post(this);
      }
   }

   protected boolean regionHasCookie() {
      return this._arSupport.isInCookieRegion(this.getCaretPosition());
   }

   protected boolean regionHasCookie(int var1) {
      return var1 < this._regionCount && var1 >= 0;
   }

   protected int getRegion() {
      return this.getRegion(this.getCursorPosition() + this.getLabelLength());
   }

   protected int getRegion(int var1) {
      return this._arSupport.getRegion(var1);
   }

   protected String getRegionText(int var1) {
      return this._arSupport.getRegionText(var1, super._text);
   }

   protected synchronized int drawText(Graphics var1, int var2, int var3, int var4, int var5) {
      return 0;
   }

   @Override
   public synchronized Object getCookieWithFocus() {
      return this._arSupport.getCookieWithFocus(this.getCaretPosition());
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean isCookieValid(int var1) {
      return true;
   }

   @Override
   public Object getCookie(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean regionsHaveSameCookie(int var1, int var2) {
      return false;
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (this.isSelecting() || !this._invertCookieRegion) {
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
         this.highlightSelectedArea(var1, var2, this._arSupport.getRunStart(), this._arSupport.getRunEnd());
         XYRect var3 = Ui.getTmpXYRect();
         super.getFocusRect(var3);
         int var4 = var3.y;
         int var5 = var3.height >> 2;
         var4 += var3.height - var5;
         this.drawHighlightRegion(var1, 1, var2, var3.x, var4, var3.width, var5);
         Ui.returnTmpXYRect(var3);
      }
   }

   @Override
   public synchronized void getFocusRect(XYRect var1) {
      super.getFocusRect(var1);
      this._arSupport.init();
      this._arSupport.getFocusRect(var1, this);
   }

   @Override
   public void selectionCopy(Clipboard var1) {
      if (!this.isSelecting() && this.regionHasCookie() && this._invertCookieRegion) {
         var1.put(new Object(super._text, this._arSupport.getRunStart(), this._arSupport.getRunEnd()));
      } else {
         super.selectionCopy(var1);
      }
   }

   @Override
   public void selectionDelete() {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public boolean isSelectionCopyable() {
      return !this.isSelecting() && this._invertCookieRegion ? this.regionHasCookie() : super.isSelectionCopyable();
   }

   @Override
   public boolean isSelectionDeleteable() {
      return this.isSelecting() || !this._invertCookieRegion ? super.isSelectionDeleteable() : !this.isStyle(9007199254740992L) && this.regionHasCookie();
   }

   @Override
   protected int scrollHorizontally(int var1) {
      int var2 = super.scrollHorizontally(var1);
      this._snapToCookie = false;
      return var2;
   }

   @Override
   protected int scrollVertically(int var1) {
      if (!this._snapToCookie || this.isSelecting()) {
         return super.scrollVertically(var1);
      }

      if (var1 == 0) {
         return 0;
      }

      var1 = this._arSupport.scrollVertically(var1, this, this.getCursorLine()._line);
      if (var1 != 0) {
         return super.scrollVertically(var1 + this._arSupport.getAdjustedAmount());
      }

      super.scrollVertically(this._arSupport.getAdjustedAmount());
      if (this._arSupport.endsOnCookie()) {
         int var2 = MathUtilities.clamp(this._arSupport.getRunStart(), this.getCaretPosition(), this._arSupport.getRunEnd() - 1);
         this.setSelection(var2, true, var2);
      }

      return 0;
   }

   public ActiveAutoTextEditField(String var1, String var2, int var3, long var4, StringPatternContainer var6) {
      super(var1, var2, var3, var4);
      this._threadSafeIterator = super._text.getIterator();
      this._arSupport = new ActiveRegionSupport(super._text.getIterator(), this);
      this._smileySupport = (SmileySupport)(new Object(this));
      this._pendingOffsets = new int[16];
      this._pendingCookieID = new long[8];
      this._pendingRegionCount = 0;
      if (var6 != null) {
         for (int var7 = 0; var7 < var6.size(); var7++) {
            Object var8 = var6.getAt(var7);
            if (var8 instanceof Object) {
               this._smileySupport.setPattern((EmoticonStringPattern)var8);
               if (var6.size() > 1) {
                  StringPattern[] var9 = new StringPattern[var6.size() - 1];

                  for (int var10 = 0; var10 < var6.size(); var10++) {
                     if (var10 != var7) {
                        var9[var10 < var7 ? var10 : var10 - 1] = (StringPattern)var6.getAt(var10);
                     }
                  }

                  this._patterns = (StringPatternContainer)(new Object(var9));
                  this._enumerator = (StringPatternEnumerator)(new Object(null, this._patterns));
               }
               break;
            }
         }
      }

      if (this._enumerator == null) {
         this._patterns = StringPatternRepository$Internal.getStringPatterns();
         this._enumerator = (StringPatternEnumerator)(new Object(null, this._patterns));
      }
   }

   @Override
   protected void makeContextMenu(ContextMenu var1, int var2) {
      super.makeContextMenu(var1, var2);
      if (var2 != 65537) {
         Object var3 = this.getCookieWithFocus();
         MenuItem var4 = ActiveRegionSupport.addCookieMenuItems(this, var3, var1, null);
         if (var4 != null && this._invertCookieRegion) {
            var1.setDefaultItem(var4);
         }

         if (var3 == null) {
            AbstractString var5 = this.getTextAbstractString(true);
            int var6 = this.getCursorPosition();
            ActiveRegionSupport.addCookieMenuItems(this, var5, 0, var6, var5.length(), this._patterns, var1, null, var2);
         }
      }
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      this._invertCookieRegion = true;
      this._snapToCookie = true;
   }

   @Override
   protected void notifyTextChanged(FormatParams var1, boolean var2) {
      super.notifyTextChanged(var1, var2);
      if (this._scanForSmileys && !this._inPostResults && this.getComposedTextStart() == this.getComposedTextEnd() && var2) {
         this._smileySupport.scanForSmileys(var1);
      }

      this._arSupport.init();
      if (var2 && !this._inPostResults) {
         this.scanForActiveRegions();
      }
   }

   public ActiveAutoTextEditField(String var1, String var2) {
      this(var1, var2, 1000000);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private synchronized int initialize() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean scanForActiveRegions(int var1) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   private boolean nextActiveRegion(AttributedString$Iterator var1) {
      while ((var1.runXAttrib() & 65504) == 0) {
         if (!var1.next()) {
            return false;
         }
      }

      return true;
   }

   private boolean prevActiveRegion(AttributedString$Iterator var1) {
      while ((var1.runXAttrib() & 65504) == 0) {
         if (!var1.prev()) {
            return false;
         }
      }

      return true;
   }

   private void postResults(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      if (var1 == 128 && this.isSymbolScreenAllowed() && !this.getScreen().isGlobal()) {
         this._smileySupport.showSymbolScreen();
         return true;
      } else {
         return super.keyControl(var1, var2, var3);
      }
   }

   @Override
   public void actionPerformed(int var1, Object var2) {
      if ((var1 & 0xFF) > 0) {
         switch (var1 & 0xFF) {
            case 113:
               if (this.isSymbolScreenAllowed() && !this.getScreen().isGlobal()) {
                  this._smileySupport.showSymbolScreen();
               }

               return;
         }
      }

      super.actionPerformed(var1, var2);
   }

   @Override
   protected boolean isSymbolScreenAllowed() {
      return this._smileySupport.getSmileyFacility() != null ? this.isEditable() : super.isSymbolScreenAllowed();
   }

   @Override
   public String getText() {
      return this._smileySupport != null && this._smileySupport.isSmileyAvailable()
         ? this._smileySupport.getDecodedString(this.getLabelLength(), super._text.length())
         : super.getText();
   }

   private AbstractString getTextAbstractString(boolean var1) {
      return var1 ? super.getTextAbstractString() : this.getTextAbstractString();
   }

   @Override
   public AbstractString getTextAbstractString() {
      return !this._smileySupport.isSmileyAvailable()
         ? super.getTextAbstractString()
         : this._smileySupport.getDecodedTextAbstractString(this.getLabelLength(), super._text.length());
   }

   @Override
   public String getText(int var1, int var2) {
      return !this._smileySupport.isSmileyAvailable() ? super.getText(var1, var2) : this._smileySupport.getDecodedString(var1, var1 + var2);
   }

   @Override
   public int getDecodedTextLength(int var1, int var2) {
      return this._smileySupport != null && this._smileySupport.isSmileyAvailable() ? this._smileySupport.getDecodedStringLength(var1, var2) : var2 - var1;
   }

   @Override
   public boolean paste(Clipboard var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int insert(String var1, int var2, boolean var3, boolean var4) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public synchronized void setText(String var1) {
      super.setText(var1);
   }

   public ActiveAutoTextEditField(String var1, String var2, int var3) {
      this(var1, var2, var3, 4503599627370496L);
   }

   public ActiveAutoTextEditField(String var1, String var2, int var3, long var4) {
      this(var1, var2, var3, var4, null);
   }

   @Override
   protected void fieldChangeNotify(int var1) {
      super.fieldChangeNotify(var1);
      if (this.isDirty()) {
         this._invertCookieRegion = false;
         this._snapToCookie = false;
      }
   }
}
