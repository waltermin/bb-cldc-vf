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

   public BasicEditField(long style) {
      super(null, null, 1000000, style);
   }

   public BasicEditField(String label, String initialValue) {
      super(label, initialValue, 1000000, 0);
   }

   public BasicEditField(String label, String initialValue, int maxNumChars, long style) {
      super(label, initialValue, maxNumChars, style);
   }

   @Override
   protected boolean isComposedTextExist() {
      return this.getComposedTextEnd() != this.getComposedTextStart();
   }

   @Override
   TextFilter getFilterFromStyle(long style) {
      if ((style & 117440512) != 0) {
         this.setAllowUnicodeInput(false);
      }

      return TextFilter.get((int)(style >> 24 & 31));
   }

   @Override
   protected void onUnfocus() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void makeContextMenu(ContextMenu contextMenu) {
      super.makeContextMenu(contextMenu);
      Object changeL = _changeInputLanguageItem.get();
      if (changeL == null) {
         changeL = new BasicEditField$1(this, CommonResource.getBundle(), 10089, 50680656, Integer.MAX_VALUE);
         _changeInputLanguageItem.set(changeL);
      }

      if (Utils.getAvailableInputLocales(false).length > 1 && (this.getStyle() & 117440512) == 0) {
         contextMenu.addItem((MenuItem)changeL);
      }
   }

   @Override
   protected boolean backspace() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int backspace(int count) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int backspace(int count, int context) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public char charAt(int offset) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void clear(int context) {
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
   public String getText(int offset, int length) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void getText(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
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
   public int insert(String text) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int insert(String text, int context) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean isSymbolScreenAllowed() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void layout(int width, int height) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void paint(Graphics graphics) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setLabel(String newLabel) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyControl(char key, int status, int time) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void update(int delta) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setMaxSize(int maxSize) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setCursorPosition(int offset) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setCursorPosition(int offset, int context) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getCursorPosition() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public boolean paste(Clipboard cb) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void moveFocus(int x, int y, int status, int time) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void drawFocus(Graphics graphics, boolean on) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setFilter(TextFilter filter) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public TextFilter getFilter() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setText(String text) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setText(String text, int context) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setFont(Font font) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
