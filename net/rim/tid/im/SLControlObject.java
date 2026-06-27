package net.rim.tid.im;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.tid.im.conv.repository.IDataSearchRepository;
import net.rim.tid.im.layout.SLKeyLayout;

public class SLControlObject {
   private MinimalInputMethod _inputMethod;

   protected SLControlObject() {
   }

   public SLControlObject(MinimalInputMethod var1) {
      this._inputMethod = var1;
   }

   public Object getViewComponent(int var1) {
      return null;
   }

   public int actionPerformed(int var1, Object var2) {
      return this._inputMethod.actionPerformed(null, var1, var2);
   }

   public SLKeyLayout getKeyLayout() {
      return this._inputMethod.getKeyLayout();
   }

   public byte[] getIMProperties(byte var1) {
      return this._inputMethod.getIMProperties(var1);
   }

   public boolean getIMStyleAsBoolean(int var1) {
      return false;
   }

   public void setIMProperties(byte var1, byte[] var2) {
      this._inputMethod.setIMProperties(var1, var2);
   }

   public String[] getShortcuts() {
      return null;
   }

   public int removeShortcut(String var1, String var2) {
      return -1;
   }

   public int addShortcut(String var1, String var2) {
      return -1;
   }

   public void addDataRepository(IDataSearchRepository var1, int var2) {
   }

   public boolean setFilter(TextFilter var1) {
      return this._inputMethod.setFilter(var1);
   }

   public int setTextInputStyle(int var1) {
      return this._inputMethod.setTextInputStyle(var1);
   }

   public boolean setKeyLayoutLocale(Locale var1) {
      return this._inputMethod.setKeyLayoutLocale(var1);
   }

   public boolean isCorrectWord(StringBufferGap var1, int var2, int var3) {
      return this._inputMethod.isCorrectWord(var1, var2, var3);
   }

   public int getInputMode() {
      return -1;
   }

   public char getPeriodSymbol() {
      return this._inputMethod.getPeriodSymbol();
   }

   public Object getAdditionalSymbolData(int var1) {
      return null;
   }
}
