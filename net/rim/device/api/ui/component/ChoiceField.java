package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngine;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.accessibility.AccessibleValue;
import net.rim.device.api.ui.text.TextRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.StringProvider;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.device.internal.ui.security.component.LockIconField;
import net.rim.tid.im.layout.SLKeyLayout;

public class ChoiceField extends Field implements FieldLabelProvider {
   private TextRect _label = (TextRect)(new Object(this));
   private String _labelText;
   private int _xPos;
   private int _yPos;
   private int _numChoices;
   private int _selectedIndex;
   private String _optionsMenuText;
   boolean _isLabelOwnLine;
   private long _rbId;
   private int _rbKey;
   private String _rbName;
   private int _cachedLocaleCode;
   private String _emptyString;
   private Object _cachedChoice;
   private String _cachedChoiceString;
   private int _lengthOfLongestLine;
   private byte[] _lengths;
   int _selectedX;
   int _selectedWidth;
   private int _accessibleState = 0;
   private final StringBufferGap _buffer = (StringBufferGap)(new Object());
   private static Tag TAG;
   private static Tag TAG_LABEL;
   public static final int CONTEXT_CHANGE_OPTION;
   public static final long FORCE_SINGLE_LINE;
   public static final long RESTRICTED_STYLE;
   public static final long NUMERIC_STYLE;
   protected static final int PADDING;
   protected static final int CHANGE_OPTION_ORDERING;
   private static MenuItem _changeOptionsItem;

   void changeOptionDialog() {
      this.internalChangeOptionDialog();
   }

   boolean internalChangeOptionDialog() {
      if (this.isEditable() && this.getOriginal() == this && this._numChoices != 0) {
         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(1, new Object(1024), new Object(512), this);
         }

         this.removeAccessibleState(1024);
         this.addAccessibleState(512);
         ChoiceField var1 = this.getChangeOptionChoiceField(null);
         ChoiceInPlaceScreen var2 = new ChoiceInPlaceScreen(this, var1, 0);
         var2.setFont(this.getFont());
         boolean var3 = true;
         Screen var4 = this.getScreen();
         if (var4 != null && var4.isGlobal()) {
            UiEngine var5 = Ui.getUiEngine();
            var5.pushGlobalScreen(var2, var5.getGlobalPriority(var4), 5);
         } else {
            var3 = var2.doModal();
         }

         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(1, new Object(512), new Object(1024), this);
         }

         this.removeAccessibleState(512);
         this.addAccessibleState(1024);
         if (var3) {
            int var6 = var1.getSelectedIndex();
            this.setSelectedIndex(var6, 2);
            return true;
         } else {
            var1.setSelectedIndex(this.getSelectedIndex(), -2147483646);
            return true;
         }
      } else {
         return false;
      }
   }

   protected ChoiceField getChangeOptionChoiceField(String var1) {
      ChoiceField$ChangeOptionChoiceField var2 = new ChoiceField$ChangeOptionChoiceField(this, var1, this._numChoices, this._selectedIndex, this.getStyle());
      var2.setChangeListener(this.getChangeListener());
      return var2;
   }

   public Object getChoice(int var1) {
      throw null;
   }

   void getInPlaceRect(XYRect var1) {
      this.getFocusRect(var1);
   }

   public void setSelectedIndex(Object var1) {
      for (int var2 = 0; var2 < this._numChoices; var2++) {
         if (var1.equals(this.getChoice(var2))) {
            this.setSelectedIndex(var2);
            return;
         }
      }
   }

   public int getSelectedIndex() {
      return this._selectedIndex;
   }

   public int getSize() {
      return this._numChoices;
   }

   public int getWidthOfChoices() {
      if (this._numChoices == 0) {
         return this.getWidthOfEmptyString();
      }

      int var1 = 0;

      for (int var2 = this._numChoices - 1; var2 >= 0; var2--) {
         int var3 = this.getWidthOfChoice(var2);
         if (var1 < var3) {
            var1 = var3;
         }
      }

      return var1;
   }

   protected int getWidthOfChoice(int var1) {
      return this.getFont().getBounds(this.getChoiceCached(var1));
   }

   protected int getHeightOfChoices() {
      int var1 = 0;

      while (var1 < this._lengths.length && this._lengths[var1] != 0) {
         var1++;
      }

      var1 = Math.max(1, var1);
      return this.getChoiceLineHeight() * var1;
   }

   int getChoiceLineHeight() {
      return this.getFont().getHeight();
   }

   void moveChoiceFocus(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void drawChoice(int var1, Graphics var2, int var3, int var4, int var5, int var6) {
      String var7 = this.getChoiceCached(var1);
      if (this._lengthOfLongestLine == 0) {
         var2.drawText(var7, var3, var4, var5, var6);
      } else {
         byte var8 = 0;
         int var9 = this.getChoiceLineHeight();

         for (int var10 = 0; var10 < this._lengths.length && this._lengths[var10] != 0; var10++) {
            var2.drawText(var7, var8, this._lengths[var10], var3, var4, var5, var6);
            var8 += this._lengths[var10];
            var4 += var9;
         }
      }
   }

   int getSelectedWidth() {
      if (this._numChoices == 0) {
         return this.getWidthOfEmptyString();
      } else {
         return this._lengthOfLongestLine == 0 ? this.getWidthOfChoice(this._selectedIndex) : this._lengthOfLongestLine;
      }
   }

   public void setEmptyString(ResourceBundleFamily var1, int var2) {
      if (var1 != null) {
         var1.getString(var2);
         this.setEmptyStringFamily(var1, var2);
      } else {
         this.setEmptyStringFamily(CommonResource.getBundle(), 1012);
      }
   }

   public void setTextRectPos(int var1, int var2) {
      this._xPos = var1;
      this._yPos = var2;
      this.invalidate();
   }

   protected void setSelectedIndex(int var1, int var2) {
      if (var1 >= 0 && this._numChoices > var1 || this._numChoices == 0 && var1 == -1) {
         if (this._selectedIndex != var1) {
            this._selectedIndex = var1;
            this._cachedChoice = null;
            this._cachedChoiceString = null;
            this.fieldChangeNotify(var2);
            this.focusAdd(false);
            int var3 = this.getWidth();
            if (this.shouldUpdateLayout(var3)) {
               Screen var4 = this.getScreen();
               if (var4 != null) {
                  var4.invalidate();
               }

               this.updateLayout();
            } else {
               this.updateLengths(var3);
               this.invalidate();
            }

            this._selectedWidth = 0;
         }
      } else {
         throw new Object();
      }
   }

   public void setSelectedIndex(int var1) {
      this.setSelectedIndex(var1, Integer.MIN_VALUE);
   }

   public void setOptionsMenuText(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void setSize(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setLabel(String var1) {
      this._labelText = var1;
      this._label.setText(var1);
      this.updateLayout();
   }

   @Override
   public String getLabel() {
      return (String)this._label.getText();
   }

   @Override
   public String toString() {
      return this.getChoiceCached(this.getSelectedIndex());
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            return this.internalChangeOptionDialog();
         default:
            return false;
      }
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = false;
      if (this.isEditable()) {
         int var5 = this._selectedIndex;
         if (var1 == ' ') {
            if (this._numChoices != 0) {
               var5 += (var2 & 2) != 0 ? -1 : 1;
               if (var5 >= this._numChoices) {
                  var5 = 0;
               }

               if (var5 < 0) {
                  var5 = this._numChoices - 1;
               }
            }
         } else {
            SLKeyLayout var6 = Keypad.getLayout();
            int var7 = SLKeyLayout.convertStatusToModifiers(var2);
            Object var8;
            if ((var2 & 32768) == 0) {
               var8 = var6.getComplementaryChars(var1, var7);
               if (var8 != null) {
                  var8 = new Object(((StringBuffer)var8).toString());
               }
            } else {
               var8 = new Object();
               ((StringBuffer)var8).append(var1);
            }

            var5 = this.findNextItem((StringBuffer)var8);
            if (var5 == -1) {
               var8 = var6.getKeyChars(var6.getOriginalKeyCode(var1, var7), 8);
               if (var8 != null) {
                  var8 = new Object(((StringBuffer)var8).toString());
               }

               var5 = this.findNextItem((StringBuffer)var8);
               if ((var2 & 1) != 0 && var5 == -1) {
                  var8 = new Object(1);
                  ((StringBuffer)var8).append(var6.getUnaltedChar(var1));
                  var5 = this.findNextItem((StringBuffer)var8);
               }
            }
         }

         if (var5 != -1) {
            this.setSelectedIndex(var5, 0);
            var4 = true;
         }

         if (var4 && Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(1, new Object(1), new Object(2), this);
         }
      }

      return var4;
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      switch (var1) {
         case '\u0082':
            return super.keyControl(var1, var2, var3);
         case '\u0083':
         case '\u0084':
         default:
            return true;
      }
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      return Keypad.key(var1) == 257 && (Keypad.status(var1) & 1) != 0 ? this.internalChangeOptionDialog() : false;
   }

   private int findNextItem(StringBuffer var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void layout(int var1, int var2) {
      this.calculateLengths(var1);
      int var3 = this.getFont().getBounds(this._labelText);
      int var4 = var3 + 2 + this.getSelectedWidth();
      this._label.invalidateLayout();
      this._label.setPosition(this._xPos, this._yPos);
      if (this.isHAlignStyle(4294967296L) || this.isHAlignStyle(12884901888L) || this.isHAlignStyle(8589934592L)) {
         var1 = Math.min(var1, var4);
      }

      if (this.isStyle(536870912)) {
         int var5 = var1 - this.getSelectedWidth();
         if (var5 < 0) {
            var5 = 0;
         }

         this._label.setStyle(64 | this._label.getStyle());
         this._label.layout(var5, var2);
         var2 = Math.max(this._label.getHeight(), this.getChoiceLineHeight());
      } else {
         this._label.layout(Math.min(var3 + 2, var1), var2);
         if (this._lengthOfLongestLine != 0) {
            if (var4 <= var1 && !this._isLabelOwnLine) {
               var2 = Math.max(this._label.getHeight(), this.getHeightOfChoices());
            } else {
               var2 = this._label.getHeight() + this.getHeightOfChoices();
            }
         } else if (var4 <= var1 && !this._isLabelOwnLine) {
            var2 = Math.max(this._label.getHeight(), this.getChoiceLineHeight());
         } else {
            var2 = this._label.getHeight() + this.getChoiceLineHeight();
         }
      }

      this.setExtent(var1, var2);
   }

   private boolean isHAlignStyle(long var1) {
      return (this.getStyle() & 12884901888L) >>> 32 == var1 >>> 32;
   }

   private void calculateLengths(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void updateLengths(int var1) {
      if (this._selectedIndex > -1 && var1 > 0 && !this.isStyle(536870912)) {
         if (!this._isLabelOwnLine && this._label.isLayoutValid() && this._label.getLineCount() == 1) {
            var1 -= this._label.getWidth();
         }

         if (this.getWidthOfChoice(this._selectedIndex) > var1) {
            this.calculateLengths(var1);
            return;
         }
      }

      this._lengthOfLongestLine = 0;
      Arrays.fill(this._lengths, (byte)0);
   }

   private boolean shouldUpdateLayout(int var1) {
      boolean var2 = false;
      if (this._selectedIndex > -1 && var1 > 0) {
         if (!this._isLabelOwnLine && this._label.isLayoutValid() && this._label.getLineCount() == 1) {
            var1 -= this._label.getWidth();
         }

         int var3 = this.getWidthOfChoice(this._selectedIndex);
         int var4 = var1 == 0 ? 1 : var3 / var1 + 1;
         int var5 = 0;

         while (var5 < this._lengths.length && this._lengths[var5] != 0) {
            var5++;
         }

         var5 = Math.max(1, var5);
         var2 = var4 != var5;
         int var6 = this._label.getWidth() + 2 + var3;
         var2 |= var6 > var1 && this._label.isLayoutValid() && this._label.getLineCount() == 1;
      }

      return var2;
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      if (Ui.getMode() < 2 && this.isEditable()) {
         if (this._optionsMenuText == null) {
            var1.addItem(_changeOptionsItem);
            return;
         }

         ChoiceField$ChangeOptionMenuItem var2 = new ChoiceField$ChangeOptionMenuItem(this._optionsMenuText);
         var1.addItem(var2);
      }
   }

   private String getEmptyString() {
      this.checkLocale();
      return this._emptyString;
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void paint(Graphics var1) {
      if (this._selectedWidth == 0) {
         this._selectedWidth = this.getSelectedWidth();
      }

      int var2 = this.getContentWidth();
      byte var3 = 64;
      switch ((int)((this.getStyle() & 12884901888L) >>> 32)) {
         case -1:
            break;
         case 0:
            if (this._isLabelOwnLine) {
               this._selectedX = 0;
               var3 |= 6;
               break;
            }
         case 2:
            var3 |= 5;
            this._selectedX = var2 - this._selectedWidth - 1;
            break;
         case 1:
         default:
            var3 |= 6;
            if (this._isLabelOwnLine) {
               this._selectedX = 0;
            } else {
               this._selectedX = this._label.getExtent().width - 1;
            }
            break;
         case 3:
            var3 |= 4;
            this._selectedX = var2 - this._selectedWidth - 1;
      }

      if (this._selectedX < -1) {
         this._selectedX = 0;
      }

      this._label.paintSelf(var1);
      if (this._numChoices == 0) {
         int var6 = this.getContentHeight() - this.getChoiceLineHeight();
         if (this._isLabelOwnLine) {
            var6 = this.getChoiceLineHeight();
         }

         var1.drawText(this.getEmptyString(), this._selectedX + 1, var6, var3, var2 - this._selectedX - 1);
      } else {
         this.drawChoice(this._selectedIndex, var1, this._selectedX + 1, this.getContentHeight() - this.getHeightOfChoices(), var3, var2 - this._selectedX - 1);
         if (this.isStyle(268435456) && (!this.isEditable() || this._numChoices == 1)) {
            int var4 = this._selectedX - 1 - 5;
            int var5 = this.getContentHeight() - this.getHeightOfChoices() + this.getFont().getBaseline() - 5;
            LockIconField.drawLock(var1, var4, var5);
         }
      }
   }

   @Override
   public void getFocusRect(XYRect var1) {
      int var2 = this.getHeightOfChoices();
      int var3 = this.getContentHeight() - var2;
      int var4 = this._selectedX;
      if (this._isLabelOwnLine) {
         var3--;
         var4++;
      }

      var1.set(var4, var3, this._selectedWidth + 1, var2);
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this._label.applyTheme();
      this._isLabelOwnLine = ThemeManager.getActiveTheme().isLabelOnOwnLine();
   }

   private String getChoiceCached(int var1) {
      if (var1 != this._selectedIndex) {
         return this.getChoice(var1).toString();
      }

      Object var2 = this.getChoice(var1);
      if (this._cachedChoiceString == null || this._cachedChoice != var2) {
         this._cachedChoice = var2;
         this._cachedChoiceString = var2.toString();
      }

      return this._cachedChoiceString;
   }

   private void setEmptyStringFamily(ResourceBundleFamily var1, int var2) {
      this._rbId = var1.getId();
      this._rbName = var1.getName();
      this._rbKey = var2;
      this._cachedLocaleCode = 0;
   }

   @Override
   public int getPreferredWidth() {
      int var1 = this.getWidthOfChoices();
      int var2 = 0;
      if (this._labelText != null) {
         var2 = this.getFont().getBounds(this._labelText);
      }

      if (this._isLabelOwnLine) {
         var1 = Math.max(var1, var2);
      } else {
         var1 += var2;
      }

      return var1 + 2;
   }

   private int getWidthOfEmptyString() {
      return this.getFont().getBounds(this.getEmptyString());
   }

   protected ChoiceField(String var1, int var2, int var3, long var4) {
      super(verifyStyle(var4));
      this.setTag(TAG);
      this._label.setTag(TAG_LABEL);
      this._labelText = var1;
      this._label.setText(var1);
      this._lengths = new byte[0];
      this._numChoices = var2;
      var3 = var2 == 0 ? -1 : var3;
      this.setSelectedIndex(var3);
      this._isLabelOwnLine = ThemeManager.getActiveTheme().isLabelOnOwnLine();
   }

   private void checkLocale() {
      if (this._rbId != 0) {
         int var1 = Locale.getDefault().getCode();
         if (this._cachedLocaleCode != var1) {
            this._cachedLocaleCode = var1;
            ResourceBundleFamily var2 = ResourceBundle.getBundle(this._rbId, this._rbName);
            this._emptyString = var2.getString(this._rbKey);
         }
      }
   }

   protected ChoiceField(String var1, int var2, int var3) {
      this(var1, var2, var3, 0);
   }

   protected ChoiceField(long var1) {
      this(null, 0, 0, var1);
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFont().getHeight();
      return this._isLabelOwnLine ? var1 * 2 + 2 : Math.max(var1, this.getHeightOfChoices());
   }

   protected ChoiceField() {
      this(null, 0, 0, 0);
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this.internalChangeOptionDialog();
   }

   private static long verifyStyle(long var0) {
      if ((var0 & 13510798882111488L) == 0) {
         var0 |= 4503599627370496L;
      }

      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }

   @Override
   public String getAccessibleName() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public String getAccessibleDescription() {
      return null;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return null;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public int getAccessibleStateSet() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (this._accessibleState & var1) != 0;
   }

   @Override
   protected void addAccessibleState(int var1) {
      if (this.isAccessibleStateSet(1)) {
         this.removeAccessibleState(1);
      }

      this._accessibleState |= var1;
   }

   @Override
   protected void removeAccessibleState(int var1) {
      this._accessibleState &= ~var1;
   }

   @Override
   public int getAccessibleRole() {
      return 13;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return this.getScreen();
   }

   @Override
   public int getAccessibleChildCount() {
      return this._numChoices;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this._selectedIndex == var1;
   }
}
