package net.rim.device.api.ui.component;

import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.internal.ui.FormatParams;

public class EmailAddressEditField extends EditField {
   private boolean _convertSpace = true;
   private boolean _inBackspace;

   public EmailAddressEditField(String var1, String var2) {
      super(var1, var2, 1000000, 2147483648L);
      this.commonInit();
   }

   public EmailAddressEditField(String var1, String var2, int var3) {
      super(var1, var2, var3, 2147483648L);
      this.commonInit();
   }

   public EmailAddressEditField(String var1, String var2, int var3, long var4) {
      super(var1, var2, var3, var4);
      this.commonInit();
   }

   private void commonInit() {
      this.setFilter((TextFilter)(new Object()));
      this.setNonSpellCheckable(true);
      this.setAllowUnicodeInput(false);
   }

   @Override
   protected boolean backspace() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected int insert(String var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean insert(char var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void notifyTextChanged(FormatParams var1, boolean var2) {
      super.notifyTextChanged(var1, var2);
      boolean var3 = this._convertSpace;
      if (!this._inBackspace) {
         this._convertSpace = true;
      }

      int var4 = var1._changedTextStart;
      if (var2 && var1._newLength == 1 && super._text.charAt(var4) == ' ') {
         int var5 = super._text.getText().indexOf('@', this.getLabelLength(), super._text.length());
         if (var5 == -1) {
            if (var3) {
               super._text.replace(var4, var4 + 1, '@');
               return;
            }
         } else if (var3) {
            super._text.replace(var4, var4 + 1, '.');
         }
      }
   }
}
