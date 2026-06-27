package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.MessageFormat;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.GlyphMetrics;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Trackball;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntIntHashtable;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.MediaIcon;
import net.rim.tid.im.layout.SLKeyLayout;
import net.rim.tid.itie.EventHandler;

class SymbolScreen$SymbolField extends Field {
   private Tag TAG;
   private Tag TAG_NEXT;
   private ThemeAttributeSet _tasNext;
   protected byte _maxKeysInRow;
   protected int _yStep;
   protected int _keyWidth;
   protected int _keyHeight;
   protected int _replHeight;
   protected int _maxIBearingY;
   private final int HORIZONTAL_SPACE;
   private Font _keyFont;
   private Font _symbolFont;
   private char[][][] _keyCodes;
   private String[][][] _layout;
   protected IntIntHashtable _map;
   private String _description;
   private byte _focusRow;
   private byte _focusColumn;
   private boolean _swapPages;
   private GlyphMetrics _glyphMetrics;
   protected int _currentPage;
   private MessageFormat _pageFormatter;
   private StringBuffer _pageBuffer;
   protected int _pagesStandard;
   protected int[] _pages;
   private final SymbolScreen this$0;
   protected static final int XTAB;
   protected static final int YTAB;
   protected static final int VSPACE;
   protected static final int ARROW_SIGN;
   private static final int NUMBER_OF_ROWS;
   private static final int NUMBER_OF_MAPPED_PAGES;

   protected SymbolScreen$SymbolField(SymbolScreen var1) {
   }

   @Override
   protected void applyFont() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void $initLayoutKeyCodes() {
      throw new RuntimeException("cod2jar: array creation");
   }

   private int keyOrdinalNumber(char var1) {
      int var3 = this._keyCodes[0].length;
      int var2 = 0;

      while (var2 < var3 && this._keyCodes[0][var2] != var1) {
         var2++;
      }

      if (var2 < var3) {
         return var2;
      }

      int var4 = this._keyCodes[1].length;
      var2 = 0;

      while (var2 < var4 && this._keyCodes[1][var2] != var1) {
         var2++;
      }

      if (var2 < var4) {
         return var2 + var3;
      }

      var3 += var4;
      var4 = this._keyCodes[2].length;
      var2 = 0;

      while (var2 < var4 && this._keyCodes[2][var2] != var1) {
         var2++;
      }

      return var2 < var4 ? var2 + var3 : -1;
   }

   private void $initDisplayKeys() {
      throw new RuntimeException("cod2jar: array creation");
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      Theme var1 = ThemeManager.getActiveTheme();
      this._tasNext = var1.getAttributeSet(this.TAG_NEXT);
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      char var2 = this.getFocusChar();
      char var3 = (char)this._map.get(Character.toUpperCase(var2));
      return (AccessibleContext)(new Object(String.valueOf(var3), String.valueOf(var2), 22, 4, this, (AccessibleText)(new Object())));
   }

   @Override
   public int getAccessibleChildCount() {
      int var1 = 0;

      for (int var2 = 0; var2 < this._keyCodes.length; var2++) {
         var1 += this._keyCodes[var2].length;
      }

      return var1;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getAccessibleRole() {
      return 22;
   }

   private char getSymbol(char var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private String getDisplayKey(SLKeyLayout var1, char var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public char getFocusChar() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IllegalStateException: No common supertype for ternary expression
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.getExprType(FunctionExprent.java:224)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.checkExprTypeBounds(FunctionExprent.java:372)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.checkTypeExpr(VarTypeProcessor.java:156)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.checkTypeExprent(VarTypeProcessor.java:132)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.lambda$processVarTypes$2(VarTypeProcessor.java:125)
      //   at org.jetbrains.java.decompiler.modules.decompiler.flow.DirectGraph.iterateExprents(DirectGraph.java:114)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.processVarTypes(VarTypeProcessor.java:125)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.calculateVarTypes(VarTypeProcessor.java:44)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarVersionsProcessor.setVarVersions(VarVersionsProcessor.java:68)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.setVarVersions(VarProcessor.java:47)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:241)
      //
      // Bytecode:
      // 00: bipush 0
      // 01: aload 0
      // 02: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._focusColumn B
      // 05: if_icmpgt 28
      // 08: aload 0
      // 09: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._focusColumn B
      // 0c: aload 0
      // 0d: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._keyCodes [[[C
      // 10: aload 0
      // 11: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._focusRow B
      // 14: aaload
      // 15: arraylength
      // 16: if_icmpge 28
      // 19: aload 0
      // 1a: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._keyCodes [[[C
      // 1d: aload 0
      // 1e: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._focusRow B
      // 21: aaload
      // 22: aload 0
      // 23: getfield net/rim/device/api/ui/component/SymbolScreen$SymbolField._focusColumn B
      // 26: caload
      // 27: ireturn
      // 28: bipush 0
      // 29: ireturn
   }

   private void setFocusChar(char var1) {
      char[][][] var2 = this._keyCodes;
      var1 = Character.toUpperCase(var1);

      for (int var3 = 0; var3 < var2.length; var3++) {
         for (int var4 = 0; var4 < var2[var3].length; var4++) {
            if (var1 == var2[var3][var4]) {
               this._focusRow = (byte)var3;
               this._focusColumn = (byte)var4;
               return;
            }
         }
      }
   }

   @Override
   public void getFocusRect(XYRect var1) {
      int var2 = this.getWidth() - ((this._keyWidth + this.HORIZONTAL_SPACE) * this._layout[this._focusRow].length - this.HORIZONTAL_SPACE) >> 1;
      var2 += this._focusColumn * (this._keyWidth + this.HORIZONTAL_SPACE);
      int var3 = this._focusRow * this._yStep + this._keyFont.getHeight() + 1 - this._maxIBearingY;
      var1.set(var2 - (this.HORIZONTAL_SPACE >> 1), var3, this._keyWidth + this.HORIZONTAL_SPACE, this._replHeight + this._maxIBearingY);
   }

   private char[] getLayoutCodes(int var1) {
      byte var2;
      switch (var1) {
         case 0:
         default:
            var2 = 0;
            break;
         case 1:
            var2 = 1;
            break;
         case 2:
            var2 = 2;
      }

      SLKeyLayout var3 = Keypad.getLayout();
      StringBuffer var4 = var3.getKeyChars(156, var2, false);
      char[] var5 = new char[var4.length()];
      var4.getChars(0, var5.length, var5, 0);
      return var5;
   }

   protected int getMaxSymbolHeight() {
      return this._symbolFont.getHeight();
   }

   protected int getMaxKeyWidth() {
      int var1 = -1;

      for (int var2 = 0; var2 < this._keyCodes.length; var2++) {
         for (int var3 = 0; var3 < this._keyCodes[var2].length; var3++) {
            String[] var4 = this._layout[var2][var3];
            var1 = Math.max(var1, this._keyFont.getBounds(var4) + 3);
         }
      }

      return var1;
   }

   protected int getMaxSymbolWidth() {
      int var1 = 0;
      SLKeyLayout var2 = Keypad.getLayout();

      for (int var4 = 0; var4 < this._keyCodes.length; var4++) {
         for (int var5 = 0; var5 < this._keyCodes[var4].length; var5++) {
            StringBuffer var3 = var2.getKeyChars((int)this._keyCodes[var4][var5], 9, false);
            var1 = Math.max(var1, this._symbolFont.getBounds(var3));
         }
      }

      return var1;
   }

   protected int getNumPages() {
      return this._pagesStandard;
   }

   protected int getNumPagesAvailable() {
      return this._pages.length;
   }

   protected int[] getPages() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < this._focusRow; var3++) {
         var2 += this._keyCodes[var3].length;
      }

      var2 += this._focusColumn;
      return var1 == var2;
   }

   protected void initCurrentPageMap(int var1) {
      this.updatePageNumber();
      if (Keypad.getLayout() != null && this._layout != null) {
         this._map.clear();
         int var2 = this._layout.length;

         for (int var4 = 0; var4 < var2; var4++) {
            int var5 = this._layout[var4].length;
            if (this._maxKeysInRow < var5) {
               this._maxKeysInRow = (byte)var5;
            }

            for (int var6 = 0; var6 < var5; var6++) {
               char var3 = this.getSymbol((char)this._keyCodes[var4][var6], var1);
               if (var3 != 0) {
                  this._map.put((int)this._keyCodes[var4][var6], var3);
               }
            }
         }

         this.updateDescription();
      }
   }

   @Override
   protected boolean invokeAction(int var1) {
      if (super.invokeAction(var1)) {
         return true;
      }

      switch (var1) {
         case 1:
            int var2 = this._map.get(Character.toUpperCase(this.getFocusChar()));
            return this.processSymbol(var2);
         default:
            return false;
      }
   }

   protected boolean isCycling() {
      return InternalServices.isReducedFormFactor();
   }

   public boolean isCurrentPageEmpty(BasicEditField var1) {
      IntEnumeration var2 = this._map.elements();

      while (var2.hasMoreElements()) {
         int var3 = var2.nextElement();
         if (var3 != -1 && (var1 == null || var1.validate((char)var3))) {
            return false;
         }
      }

      return true;
   }

   public boolean isEmpty(TextField var1) {
      for (int var2 = 0; var2 < this._pagesStandard; var2++) {
         if (!this.isPageEmpty(var1, var2)) {
            return false;
         }
      }

      return true;
   }

   protected boolean isPageEmpty(TextField var1, int var2) {
      for (int var3 = this._layout.length - 1; var3 >= 0; var3--) {
         for (int var4 = this._layout[var3].length - 1; var4 >= 0; var4--) {
            char var5 = this.getSymbol((char)this._keyCodes[var3][var4], var2);
            if (var5 != 0 && (var1 == null || var1.validate(var5))) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      return var1 == 514 ? EventHandler.getInstance().processKeyEvent(var1, var3, var2, var3, var4, true) : super.processKeyEvent(var1, var2, var3, var4);
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      boolean var4 = false;
      if (var1 == 129 || var1 == 130) {
         int var6 = var1 == 129 ? -1 : 1;
         byte var7 = (byte)((this._focusRow + var6 + this._layout.length) % this._layout.length);
         byte var8 = this._focusColumn >= this._layout[var7].length ? (byte)(this._layout[var7].length - 1) : this._focusColumn;
         int var5 = var6 == -1 ? -(this._focusColumn + (this._layout[var7].length - var8)) : this._layout[this._focusRow].length - this._focusColumn + var8;
         if (var5 != 0) {
            return this.this$0.dispatchTrackwheelEvent(519, var5, var2, var3);
         }

         var4 = true;
      }

      if (!var4) {
         var4 = super.keyControl(var1, var2, var3);
      }

      return var4;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      boolean var3 = super.keyDown(var1, var2);
      if (!var3) {
         boolean var4 = (var1 & 257) == 0;
         var1 = Keypad.key(var1);
         char var5 = Keypad.map(var1, 0);
         if (this.this$0._inputMethodID == 512 && (!var4 || this.this$0._pageTimer != -1)) {
            if (var5 > 'ﻠ') {
               var5 -= 'ﻠ';
            }

            var5 = Keypad.getAltedChar(var5);
            if (var5 > 'ﻠ') {
               var5 -= 'ﻠ';
            }

            if ('0' <= var5 && var5 <= '9') {
               if (this.this$0._pageTimer != -1) {
                  Application.getApplication().cancelInvokeLater(this.this$0._pageTimer);
                  this.this$0._pageTimer = -1;
                  this.gotoPage(this.this$0._pendingPageNumber * 10 + var5 - 48 - 1, false);
                  return true;
               }

               this.this$0._pendingPageNumber = var5 - '0';
               this.this$0._pageTimer = Application.getApplication().invokeLater(new SymbolScreen$SymbolField$1(this), 500, false);
               return true;
            }
         }

         switch (var5) {
            case '\b':
            case ' ':
               this.this$0.close();
               return true;
            case '\n':
               int var8 = this._map.get(Character.toUpperCase(this.getFocusChar()));
               return this.processSymbol(var8);
            case '\u0080':
               if (!this.nextPage(var4, true)) {
                  this.this$0.close();
                  return true;
               }

               this.invalidate();
               return true;
            default:
               int var6 = this._map.get(var1);
               var3 = this.processSymbol(var6);
               if (!this._swapPages) {
                  this.setFocusChar(var5);
               }
         }
      }

      this.invalidate();
      return var3;
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private boolean mapContains(int var1) {
      return this._map.contains(var1);
   }

   protected boolean nextPage(boolean var1, boolean var2) {
      if (var1) {
         this._currentPage++;
      } else {
         this._currentPage--;
      }

      if (this._currentPage >= this._pages.length) {
         if (!var2 || this._pages.length <= 1) {
            this._currentPage = this._pages.length - 1;
            return false;
         }

         this._currentPage = 0;
      } else if (this._currentPage < 0) {
         if (!var2) {
            return false;
         }

         this._currentPage = this._pages.length - 1;
      }

      this.initCurrentPageMap(this._pages[this._currentPage]);
      this.invalidate();
      return true;
   }

   protected void gotoPage(int var1) {
      this.gotoPage(var1, true);
   }

   protected void gotoPage(int var1, boolean var2) {
      if (var1 < 0 || var1 >= this._pages.length) {
         if (!var2) {
            return;
         }

         var1 = 0;
      }

      this._currentPage = var1;
      this.initCurrentPageMap(this._pages[this._currentPage]);
      this.invalidate();
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      byte var4 = 0;
      if (Trackball.isSupported() || this.this$0._inputMethodID == 512) {
         if ((var2 & 65536) == 0 && this.this$0._inputMethodID != 512 || (var2 & 1) != 0 && this.this$0._inputMethodID == 512) {
            this._focusRow = (byte)(this._focusRow + var1);
            int var5 = this._currentPage;
            if (this._focusRow > this._layout.length - 1 && var1 > 0) {
               if (this._currentPage < this._pages.length - 1) {
                  this._focusRow = 0;
                  this.nextPage(true, true);
                  if (this._currentPage == var5) {
                     this._focusRow = (byte)(this._layout.length - 1);
                  }

                  this.invalidate();
               } else if (this.this$0._inputMethodID != 512) {
                  this._focusRow = (byte)(this._layout.length - 1);
               } else {
                  this._focusRow = 0;
                  this.gotoPage(0);
               }
            } else if (this._focusRow < 0 && var1 < 0) {
               if (this._currentPage > 0) {
                  this._focusRow = (byte)(this._layout.length - 1);
                  this.nextPage(false, true);
                  if (this._currentPage == var5) {
                     this._focusRow = 0;
                  }

                  this.invalidate();
               } else {
                  this._focusRow = 0;
                  if (this.this$0._inputMethodID == 512) {
                     var4 = -1;
                  }
               }
            }

            while (this._focusColumn > this._layout[this._focusRow].length - 1) {
               this._focusColumn--;
            }
         } else if (this.this$0._inputMethodID != 512) {
            this._focusColumn = (byte)(this._focusColumn + var1);
            if (var1 > 0 && this._focusColumn >= this._layout[this._focusRow].length) {
               this._focusColumn = (byte)(this._layout[this._focusRow].length - 1);
            } else if (var1 < 0 && this._focusColumn <= 0) {
               this._focusColumn = 0;
            }
         }
      }

      if (!Trackball.isSupported() && var2 == 0) {
         this._focusColumn = (byte)(this._focusColumn + var1);
         if (this._focusColumn >= this._layout[this._focusRow].length) {
            this._focusColumn = (byte)(this._focusColumn - this._layout[this._focusRow].length);
            if (this._focusRow == this._layout.length - 1) {
               this._focusRow = 0;
               this.nextPage(true, true);
               this.invalidate();
            } else {
               this._focusRow++;
            }

            this._focusColumn = (byte)MathUtilities.clamp(0, this._focusColumn, this._layout[this._focusRow].length - 1);
         } else if (this._focusColumn < 0) {
            if (this._focusRow == 0) {
               this._focusRow = (byte)(this._layout.length - 1);
               if (this.this$0._inputMethodID == 512) {
                  return var1;
               }

               this.nextPage(false, true);
               this.invalidate();
            } else {
               this._focusRow--;
            }

            this._focusColumn = (byte)(this._focusColumn + this._layout[this._focusRow].length);
            this._focusColumn = (byte)MathUtilities.clamp(0, this._focusColumn, this._layout[this._focusRow].length - 1);
         }
      }

      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }

      return var4;
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected void paintSymbol(Graphics var1, char var2, int var3, int var4, int var5, int var6) {
      if (var2 != 8646) {
         var1.drawText(var2, var3, var4, 4, var5);
         this._symbolFont.getGlyphMetrics(var2, this._glyphMetrics);
         this._maxIBearingY = Math.max(this._maxIBearingY, -(this._symbolFont.getAscent() + this._symbolFont.getLeading() + this._glyphMetrics.iBearingY));
      } else {
         if (this.this$0._inputMethodID == 512) {
            byte var7 = 0;
            if (var7 != -1) {
               MediaIcon.COLLECTION.paint(var1, var3, var4 - 2, var5, var6 + 6, var7);
               return;
            }
         } else {
            int var8 = this._currentPage < this._pages.length - 1 ? 0 : (this.isCycling() ? 4 : -1);
            if (var8 != -1) {
               MediaIcon.COLLECTION.paint(var1, var3, var4, var5, var6, var8);
            }
         }
      }
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      int var3 = this._map.get(Character.toUpperCase(this.getFocusChar()));
      return this.processSymbol(var3);
   }

   private boolean processSymbol(int var1) {
      this._swapPages = false;
      if (var1 != -1) {
         if (var1 == 8646) {
            this._swapPages = true;
            if (!this.nextPage(true, true)) {
               this.this$0.close();
               return true;
            } else {
               this.invalidate();
               return true;
            }
         } else {
            if (this._pages[this._currentPage] >= this._pagesStandard
               || this.this$0._targetEditField != null && this.this$0._targetEditField.validate((char)var1)) {
               this.send((char)var1);
               this.this$0.close();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected void reset() {
      this._pagesStandard = 2;
      this._pages = this.getPages();
      this._currentPage = -1;
      this.nextPage(true, true);
   }

   void send(char var1) {
      if (var1 != ' ' && (this.this$0._targetEditField == null || this.this$0._targetEditField.validate(var1))) {
         byte var2 = 0;
         Screen var3 = this.this$0.getTarget().getScreen();
         var3.processKeyEvent(513, var1, 32768, var2);
      }
   }

   protected void setDescription(String var1) {
      this._description = var1;
      this.invalidate();
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this.trackwheelClick(var3, var4);
   }

   protected void updateDescription() {
      this.setDescription(null);
   }

   protected void updatePageNumber() {
      if (this.this$0._inputMethodID == 512) {
         if (this.this$0._header != null) {
            this.this$0._header.setPageNumber(this._currentPage + 1);
            return;
         }
      } else {
         Object[] var1 = new Object[]{new Object(this._currentPage + 1), new Object(this._pages.length)};
         this._pageBuffer.setLength(0);
         this._pageFormatter.format(var1, this._pageBuffer, null);
      }
   }
}
