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

   public PasswordEditField(String var1, String var2) {
      this(var1, var2, 1000000, 536870912);
   }

   public PasswordEditField(String var1, String var2, int var3, long var4) {
   }

   @Override
   protected StringBufferGap getDisplayText() {
      char[] var1 = new char[super._text.length()];
      Arrays.fill(var1, '*');
      return (StringBufferGap)(new Object((String)(new Object(var1))));
   }

   @Override
   public final boolean isSelectionCopyable() {
      return false;
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      int var3 = Keypad.key(var1);
      return !Trackball.isSupported() || var3 != 273;
   }

   @Override
   protected void notifyTextChanged(FormatParams var1, boolean var2) {
      if (var2) {
         int var3 = this.getLabelLength();
         if (var1._changedTextStart + var1._newLength > var3) {
            int var4 = Math.max(var1._changedTextStart, var3);
            this.setAttrib(var4, var1._changedTextStart + var1._newLength, 268435456, 268435456, 0, 0);
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
   public final void selectionCopy(Clipboard var1) {
   }

   private static long verifyStyle(long var0) {
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      if ((var0 & 13510798882111488L) == 0) {
         var0 |= 4503599627370496L;
      }

      return var0;
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
   public void setLabel(String var1) {
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
   public void setText(String var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void setText(String var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
