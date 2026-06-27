package net.rim.device.api.i18n;

import net.rim.vm.Array;

public class MessageFormat extends Format {
   private Locale _locale;
   private String pattern;
   private Format[] _formats;
   private int[] offsets;
   private int[] argumentNumbers;
   private int maxOffset;
   private static final String[] typeList;
   private static final String[] dateModifierList;
   private static final int MAX_ARGUMENTS;

   public MessageFormat(String var1) {
   }

   private MessageFormat(String var1, Locale var2) {
   }

   public void applyPattern(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final int findKeyword(String var0, String[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         if (var0.equals(var1[var2])) {
            return var2;
         }
      }

      return -1;
   }

   @Override
   public final StringBuffer format(Object var1, StringBuffer var2, FieldPosition var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static String format(String var0, Object[] var1) {
      MessageFormat var2 = new MessageFormat(var0);
      return var2.format(var1);
   }

   private StringBuffer format(Object[] var1, StringBuffer var2, FieldPosition var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int[] getFields() {
      int[] var1 = new int[this.maxOffset + 1];
      System.arraycopy(this.argumentNumbers, 0, var1, 0, this.maxOffset + 1);
      return var1;
   }

   public Format[] getFormats() {
      int var1 = this._formats.length;
      Format[] var2 = new Format[var1];
      System.arraycopy(this._formats, 0, var2, 0, var1);
      return var2;
   }

   public Locale getLocale() {
      return this._locale;
   }

   @Override
   public int hashCode() {
      return this.pattern.hashCode();
   }

   private void makeFormat(int var1, int var2, StringBuffer[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setFormats(Format[] var1) {
      int var2 = var1 == null ? 0 : var1.length;
      this._formats = new Format[var2];
      if (var1 != null) {
         System.arraycopy(var1, 0, this._formats, 0, var2);
      }
   }

   public void setFormat(int var1, Format var2) {
      if (this._formats.length <= var1) {
         Array.resize(this._formats, var1 + 1);
      }

      this._formats[var1] = var2;
   }

   public void setLocale(Locale var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
