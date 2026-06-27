package net.rim.device.api.ui.component;

import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.internal.ui.SystemIcon;
import net.rim.device.internal.ui.Tree;

public class TreeField extends Field implements VariableRowHeightProvider {
   private Tree _data;
   private String _emptyString;
   private int _emptyStyle;
   private int _rowHeightSet;
   private int _rowHeight;
   private int _focusRectX;
   private int _focusRectY;
   private int _focusRectWidth;
   private int _focusRectHeight;
   private TreeFieldCallback _callback;
   private int _indent;
   private int _focusNode;
   private boolean _haveFocus;
   private int _lastStartNode;
   private int _lastStartNodeIndex;
   private int _iconWidth;
   private int _iconGap;
   private RowHeightAdjuster _rowHeightAdjuster;
   private boolean _drawFocus;
   private boolean _iconFocusChanged;
   private static Tag TAG;
   private static final int ROW_HEIGHT_FONT;
   private static MenuItem _expandItem;
   private static MenuItem _collapseItem;

   public Object getCookie(int var1) {
      return this._data.getCookie(var1);
   }

   public int getCurrentNode() {
      return this._focusNode;
   }

   public boolean getExpanded(int var1) {
      return this._data.getNodeExpansion(var1);
   }

   public int getFirstChild(int var1) {
      return this._data.getFirstChild(var1);
   }

   public int getFirstRoot() {
      return this._data.getFirstRoot();
   }

   public int getNextSibling(int var1) {
      return this._data.getNextSibling(var1);
   }

   public int getNodeCount() {
      return this._data.getNodeCount();
   }

   public int getParent(int var1) {
      return this._data.getParent(var1);
   }

   public int getPreviousSibling(int var1) {
      return this._data.getPreviousSibling(var1);
   }

   public int getRowHeight() {
      return this._rowHeight;
   }

   public boolean getVisible(int var1) {
      return this._data.getVisible(var1);
   }

   public void invalidateNode(int var1) {
      Manager var2 = this.getManager();
      if (this._lastStartNode != -1 && var2 != null) {
         if (this._data.getVisible(var1)) {
            int var3 = (var2.getVisibleHeight() + this._rowHeight - 1) / this._rowHeight;
            int var4 = -1;
            int var5 = this._lastStartNode;

            for (int var6 = 0; var6 < var3; var6++) {
               if (var5 == var1) {
                  var4 = this._lastStartNodeIndex + var6;
                  break;
               }

               var5 = this._data.nextNode(var5, false);
               if (var5 == -1) {
                  break;
               }
            }

            if (var4 == -1) {
               var5 = this._lastStartNode;

               for (int var8 = 0; var8 < var3; var8++) {
                  var5 = this._data.previousNode(var5, false);
                  if (var5 == -1) {
                     return;
                  }

                  if (var5 == var1) {
                     var4 = this._lastStartNodeIndex - var8 - 1;
                     break;
                  }
               }
            }

            this.invalidate(0, this.getYForRow(var4), this.getWidth(), this.getRowHeight(var4));
         }
      }
   }

   public int getVisibleNodeCount() {
      return this._data.getVisibleCount();
   }

   public void setEmptyString(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getEmptyString() {
      return this._emptyString;
   }

   public int getEmptyStringStyle() {
      return this._emptyStyle;
   }

   public void setCurrentNode(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void deleteAll() {
      int var1 = this.getFirstRoot();

      while (var1 != -1) {
         int var2 = this.getNextSibling(var1);
         this.deleteSubtree(var1);
         var1 = var2;
      }
   }

   public void deleteSubtree(int var1) {
      boolean var2 = this._data.getVisible(var1);

      for (int var3 = this._focusNode; var3 > 0; var3 = this._data.getParent(var3)) {
         if (var3 == var1) {
            int var4 = this._data.getLastNode(var1, false);
            this._focusNode = this._data.nextNode(var4, false);
            if (this._focusNode == -1) {
               this._focusNode = this._data.previousNode(var1, false);
            }
            break;
         }
      }

      this._data.deleteSubtree(var1);
      if (var2) {
         this.updateLayout();
      }
   }

   public int addChildNode(int var1, Object var2) {
      int var3 = this._data.addChildNode(var1, var2);
      if (this._haveFocus && this._focusNode == -1) {
         this._focusNode = var3;
      }

      if (this._data.getVisible(var3)) {
         this.updateLayout();
      }

      return var3;
   }

   public int addSiblingNode(int var1, Object var2) {
      int var3 = this._data.addSiblingNode(var1, var2);
      if (this._data.getVisible(var3)) {
         this.updateLayout();
      }

      return var3;
   }

   public void setDefaultExpanded(boolean var1) {
      this._data.setDefaultExpansion(var1);
   }

   public void setExpanded(int var1, boolean var2) {
      boolean var3 = this._data.getNodeExpansion(var1);
      if (var3 != var2) {
         this._data.setNodeExpansion(var1, var2);
         if (this._data.getVisible(var1)) {
            this.updateLayout();
         }
      }
   }

   public int nextNode(int var1, int var2, boolean var3) {
      return this._data.nextSubtreeNode(var1, var2, var3);
   }

   public int previousNode(int var1, boolean var2) {
      return this._data.previousNode(var1, var2);
   }

   public int getLastNode(int var1, boolean var2) {
      return this._data.getLastNode(var1, var2);
   }

   public void setCookie(int var1, Object var2) {
      this._data.setCookie(var1, var2);
   }

   public void setIndentWidth(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this._indent = var1;
   }

   public void setRowHeight(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setRowHeightInLines(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int getIndentForNode(int var1) {
      return this._data.getNodeDepth(var1) * this._indent;
   }

   public int getRowForY(int var1) {
      return this._rowHeightAdjuster.getRowForY(var1);
   }

   public int getRowHeight(int var1) {
      return this._rowHeightAdjuster.getRowHeight(var1);
   }

   int getYForRow(int var1) {
      return this._rowHeightAdjuster.getYForRow(var1);
   }

   public int getLineNumberForNode(int var1) {
      return this._data.getIndexOf(var1, false);
   }

   @Override
   public int getAdjustedY(Font var1, StringBuffer var2, int var3, int var4, int var5) {
      return this._rowHeightAdjuster.getAdjustedY(var1, var2, var3, var4, var5);
   }

   @Override
   public int getAdjustedY(int var1) {
      return this._rowHeightAdjuster.getAdjustedY(var1);
   }

   @Override
   public int getAdjustedY(Font var1, String var2, int var3) {
      return this._rowHeightAdjuster.getAdjustedY(var1, var2, var3);
   }

   private void calcFocusRect() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      this._drawFocus = var2;
      super.drawFocus(var1, var2);
   }

   @Override
   public int getAccessibleRole() {
      return 27;
   }

   @Override
   public int getAccessibleChildCount() {
      return this.getNodeCount();
   }

   @Override
   public void getFocusRect(XYRect var1) {
      this.calcFocusRect();
      var1.set(this._focusRectX, this._focusRectY, this._focusRectWidth, this._focusRectHeight);
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (super.getAccessibleStateSet() & var1) != 0;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this._focusNode == var1 + 1;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 != ' ') {
         return false;
      }

      if (this._focusNode == -1) {
         return false;
      }

      if (this._data.nextSubtreeNode(this._focusNode, this._focusNode, true) == -1) {
         return true;
      }

      boolean var4 = !this._data.getNodeExpansion(this._focusNode);
      this._data.setNodeExpansion(this._focusNode, var4);
      this.updateLayout();
      if (var4) {
         this.showDescendants(this._focusNode);
         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(1, new Object(1024), new Object(512), this);
            return true;
         }
      } else if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(1, new Object(512), new Object(1024), this);
      }

      return true;
   }

   private void showDescendants(int var1) {
      if (this._data.getNodeExpansion(var1)) {
         int var2 = (this.getManager().getVisibleHeight() + this._rowHeight - 1) / this._rowHeight;
         int var3 = -1;
         int var4 = this._data.nextSubtreeNode(var1, var1, false);

         for (int var5 = 1; var5 < var2 && var4 != -1; var5++) {
            var3 = var4;
            var4 = this._data.nextSubtreeNode(var4, var1, false);
         }

         if (var3 != -1) {
            this.setCurrentNode(var3);
            this.setCurrentNode(var1);
         }
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      if (this._rowHeightSet < 0) {
         this._rowHeight = this.getFont().getHeight() * -this._rowHeightSet;
      }

      this._iconWidth = SystemIcon.COLLECTION.getWidth(this._rowHeight, this._rowHeight);
      this._iconGap = Math.max(1, this._rowHeight >> 3);
      if (this._focusNode == -1) {
         this._lastStartNode = -1;
      } else {
         int var3 = this._data.getIndexOf(this._focusNode, false);
         this._lastStartNode = this._focusNode;
         this._lastStartNodeIndex = var3;
      }

      int var4 = Math.max(1, this._data.getVisibleCount());
      this._rowHeightAdjuster.setSize(var4);
      this._rowHeightAdjuster.setRowHeight(this._rowHeight);
      this.setExtent(var1, this._rowHeightAdjuster.getHeight());
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      int var5 = this.getRowForY(var2);
      int var6 = this.getFirstRoot();

      while (--var5 >= 0) {
         var6 = this._data.nextNode(var6, false);
         if (var6 == -1) {
            break;
         }
      }

      if (var6 != this._focusNode) {
         this.invalidateNode(this._focusNode);
         if (this._data.getFirstChild(var6) != -1) {
            this._iconFocusChanged = true;
         }
      }

      this._focusNode = var6;
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if (this._data.getVisibleCount() != 0 && (var2 & 65536) == 0) {
         while (var1 > 0) {
            int var4 = this._data.nextNode(this._focusNode, false);
            if (var4 == -1) {
               return var1;
            }

            if (var4 != this._focusNode) {
               this.invalidateNode(this._focusNode);
               if (this._data.getFirstChild(var4) != -1) {
                  this._iconFocusChanged = true;
               }
            }

            this._focusNode = var4;
            var1--;
         }

         while (var1 < 0) {
            int var5 = this._data.previousNode(this._focusNode, false);
            if (var5 == -1) {
               return var1;
            }

            if (var5 != this._focusNode) {
               this.invalidateNode(this._focusNode);
               if (this._data.getFirstChild(var5) != -1) {
                  this._iconFocusChanged = true;
               }
            }

            this._focusNode = var5;
            var1++;
         }

         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
         }

         return 0;
      } else {
         return var1;
      }
   }

   @Override
   protected void onFocus(int var1) {
      this._haveFocus = true;
      if (this._data.getVisibleCount() != 0) {
         switch (var1) {
            case -2:
               break;
            case -1:
               if (this._focusNode == -1) {
                  this._focusNode = this._data.getLastNode(0, false);
               }
               break;
            case 0:
            case 1:
            default:
               if (this._focusNode == -1) {
                  this._focusNode = this._data.nextNode(0, false);
               }
         }
      }

      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }

      super.onFocus(var1);
   }

   @Override
   protected void onUnfocus() {
      this._haveFocus = false;
      this._focusNode = -1;
      super.onUnfocus();
   }

   @Override
   protected void paint(Graphics var1) {
      if (this._data.getVisibleCount() == 0) {
         int var14 = Math.min(this.getContentWidth(), this.getManager().getVisibleWidth());
         var1.drawText(this._emptyString, 0, 0, this._emptyStyle, var14);
      } else {
         XYRect var2 = var1.getClippingRect();
         int var3 = this.getRowForY(var2.y);
         int var4 = Math.min(this._data.getVisibleCount() - 1, this.getRowForY(var2.y + var2.height - 1));
         if (this._lastStartNode == -1) {
            this._lastStartNode = this._data.nextNode(0, false);
            this._lastStartNodeIndex = 0;
         }

         while (this._lastStartNodeIndex > var3) {
            this._lastStartNode = this._data.previousNode(this._lastStartNode, false);
            this._lastStartNodeIndex--;
         }

         while (this._lastStartNodeIndex < var3) {
            this._lastStartNode = this._data.nextNode(this._lastStartNode, false);
            this._lastStartNodeIndex++;
         }

         int var5 = this._lastStartNode;
         int var6 = this.getYForRow(this._lastStartNodeIndex);
         int var7 = this.getContentWidth();
         int var8 = this._rowHeight;
         boolean var9 = false;

         for (int var10 = var3; var10 <= var4; var10++) {
            int var11 = this.getIndentForNode(var5);
            boolean var12 = this._data.getFirstChild(var5) != -1;
            byte var13 = 4;
            if (var12) {
               if (this.getExpanded(var5)) {
                  var13 = 5;
                  if (this._drawFocus && var5 == this._focusNode && SystemIcon.COLLECTION.containsIcon(var8, 17)) {
                     var13 = 17;
                  }
               } else {
                  var13 = 6;
                  if (this._drawFocus && var5 == this._focusNode && SystemIcon.COLLECTION.containsIcon(var8, 18)) {
                     var13 = 18;
                  }
               }
            }

            SystemIcon.COLLECTION.paint(var1, var11, var6, this._iconWidth, var8, var13);
            var11 += this._iconWidth + this._iconGap;
            this._rowHeightAdjuster.start(var10, var6);
            this._callback.drawTreeItem(this, var1, var5, var6, var7 - var11, var11);
            var9 |= this._rowHeightAdjuster.finish(var10);
            var5 = this._data.nextNode(var5, false);
            var6 += this.getRowHeight(var10);
         }

         if (var9) {
            this.updateLayout();
         }

         if (this._iconFocusChanged) {
            int var15 = this.getYForRow(this._lastStartNodeIndex);
            this.invalidate(0, var15, this.getContentWidth(), var6 - var15);
            this._iconFocusChanged = false;
         }
      }
   }

   public TreeField(TreeFieldCallback var1, long var2) {
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void getFocusRectPhantom(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
