package net.rim.device.api.ui.component;

import net.rim.device.api.ui.text.EmailAddressTextFilter;
import net.rim.device.internal.ui.FormatParams;

public class EmailAddressEditField extends EditField {
   private boolean _convertSpace = true;
   private boolean _inBackspace;

   public EmailAddressEditField(String label, String initialValue) {
      super(label, initialValue, 1000000, 2147483648L);
      this.commonInit();
   }

   public EmailAddressEditField(String label, String initialValue, int maxNumChars) {
      super(label, initialValue, maxNumChars, 2147483648L);
      this.commonInit();
   }

   public EmailAddressEditField(String label, String initialValue, int maxNumChars, long style) {
      super(label, initialValue, maxNumChars, style);
      this.commonInit();
   }

   private void commonInit() {
      this.setFilter(new EmailAddressTextFilter());
      this.setNonSpellCheckable(true);
      this.setAllowUnicodeInput(false);
   }

   @Override
   protected boolean backspace() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected int insert(String text, int context) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean insert(char key, int status) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void notifyTextChanged(FormatParams aParams, boolean aIsInsertionOrDeletion) {
      super.notifyTextChanged(aParams, aIsInsertionOrDeletion);
      boolean convertSpace = this._convertSpace;
      if (!this._inBackspace) {
         this._convertSpace = true;
      }

      int start = aParams._changedTextStart;
      if (aIsInsertionOrDeletion && aParams._newLength == 1 && super._text.charAt(start) == ' ') {
         int atIndex = super._text.getText().indexOf('@', this.getLabelLength(), super._text.length());
         if (atIndex == -1) {
            if (convertSpace) {
               super._text.replace(start, start + 1, '@');
               return;
            }
         } else if (convertSpace) {
            super._text.replace(start, start + 1, '.');
         }
      }
   }
}
