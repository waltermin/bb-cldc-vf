package net.rim.device.api.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.api.util.AbstractString;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.tid.util.Utils;
import net.rim.vm.WeakReference;

public class BasicEditField extends TextField {
   public static final long FILTER_DEFAULT;
   public static final long FILTER_EMAIL;
   public static final long FILTER_NUMERIC;
   public static final long FILTER_REAL_NUMERIC;
   public static final long FILTER_UPPERCASE;
   public static final long FILTER_LOWERCASE;
   public static final long FILTER_HEXADECIMAL;
   public static final long FILTER_INTEGER;
   public static final long FILTER_PHONE;
   public static final long FILTER_URL;
   public static final long FILTER_PIN_ADDRESS;
   public static final long FILTER_FILENAME;
   private static WeakReference _changeInputLanguageItem;

   public BasicEditField() {
      super(null, null, 1000000, 0);
   }

   public BasicEditField(long var1) {
      super(null, null, 1000000, var1);
   }

   public BasicEditField(String var1, String var2) {
      super(var1, var2, 1000000, 0);
   }

   public BasicEditField(String var1, String var2, int var3, long var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   protected boolean isComposedTextExist() {
      return this.getComposedTextEnd() != this.getComposedTextStart();
   }

   @Override
   TextFilter getFilterFromStyle(long var1) {
      if ((var1 & 117440512) != 0) {
         this.setAllowUnicodeInput(false);
      }

      return TextFilter.get((int)(var1 >> 24 & 31));
   }

   @Override
   protected void onUnfocus() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      Object var2 = _changeInputLanguageItem.get();
      if (var2 == null) {
         var2 = new BasicEditField$1(this, CommonResource.getBundle(), 10089, 50680656, Integer.MAX_VALUE);
         _changeInputLanguageItem.set(var2);
      }

      if (Utils.getAvailableInputLocales(false).length > 1 && (this.getStyle() & 117440512) == 0) {
         var1.addItem((MenuItem)var2);
      }
   }

   @Override
   protected boolean backspace() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int backspace(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int backspace(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public char charAt(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void clear(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void wipe() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void selectionDelete() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void displayFieldFullMessage() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getLabelLength() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getMaxSize() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getPreferredHeight() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getPreferredWidth() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public String getText() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public String getText(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void getText(int var1, int var2, char[] var3, int var4) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public AbstractString getTextAbstractString() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getTextLength() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int insert(String var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int insert(String var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean isSymbolScreenAllowed() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void update(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setMaxSize(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setCursorPosition(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setCursorPosition(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getCursorPosition() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public boolean paste(Clipboard var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setFilter(TextFilter var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public TextFilter getFilter() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setText(String var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setText(String var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setFont(Font var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
