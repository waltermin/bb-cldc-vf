package net.rim.device.api.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Trackball;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.ui.FormatParams;
import net.rim.device.internal.ui.StringBufferGap;

public class PasswordEditField extends BasicEditField {
   public PasswordEditField() {
      this(null, null);
   }

   public PasswordEditField(String label, String initialValue) {
      this(label, initialValue, 1000000, 536870912);
   }

   public PasswordEditField(String label, String initialValue, int maxNumChars, long style) {
   }

   @Override
   protected StringBufferGap getDisplayText() {
      char[] buffer = new char[super._text.length()];
      Arrays.fill(buffer, '*');
      return (StringBufferGap)(new Object((String)(new Object(buffer))));
   }

   @Override
   public final boolean isSelectionCopyable() {
      return false;
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      int key = Keypad.key(keycode);
      return !Trackball.isSupported() || key != 273;
   }

   @Override
   protected void notifyTextChanged(FormatParams aParams, boolean aIsInsertionOrDeletion) {
      if (aIsInsertionOrDeletion) {
         int labelLen = this.getLabelLength();
         if (aParams._changedTextStart + aParams._newLength > labelLen) {
            int from = Math.max(aParams._changedTextStart, labelLen);
            this.setAttrib(from, aParams._changedTextStart + aParams._newLength, 268435456, 268435456, 0, 0);
            if (this.getComposedTextStart() != this.getComposedTextEnd()) {
               this.setAttrib(this.getComposedTextStart(), this.getComposedTextEnd(), 0, 268435456, 0, 0);
            }

            if (this.getMaxSize() == this.getDecodedTextLength() && this.getLatestCommittedTextEnd() == this.getDecodedTextLength()) {
               this.setAttrib(this.getLatestCommittedTextStart(), this.getLatestCommittedTextEnd(), 268435456, 268435456, 0, 0);
            }
         }
      }
   }

   @Override
   public final void selectionCopy(Clipboard cb) {
   }

   private static long verifyStyle(long style) {
      if ((style & 54043195528445952L) == 0) {
         style |= 18014398509481984L;
      }

      if ((style & 13510798882111488L) == 0) {
         style |= 4503599627370496L;
      }

      return style;
   }

   @Override
   protected void onUnfocus() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
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
   public void setLabel(String newLabel) {
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
   public void setText(String text) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setText(String text, int context) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
