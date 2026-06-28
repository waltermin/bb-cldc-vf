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

   public MessageFormat(String pattern) {
   }

   private MessageFormat(String pattern, Locale locale) {
   }

   public void applyPattern(String newPattern) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final int findKeyword(String s, String[] list) {
      for (int i = 0; i < list.length; i++) {
         if (s.equals(list[i])) {
            return i;
         }
      }

      return -1;
   }

   @Override
   public final StringBuffer format(Object arguments, StringBuffer result, FieldPosition ignored) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static String format(String pattern, Object[] arguments) {
      MessageFormat temp = new MessageFormat(pattern);
      return temp.format(arguments);
   }

   private StringBuffer format(Object[] arguments, StringBuffer result, FieldPosition status, int recursionProtection) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int[] getFields() {
      int[] arguments = new int[this.maxOffset + 1];
      System.arraycopy(this.argumentNumbers, 0, arguments, 0, this.maxOffset + 1);
      return arguments;
   }

   public Format[] getFormats() {
      int length = this._formats.length;
      Format[] formats = new Format[length];
      System.arraycopy(this._formats, 0, formats, 0, length);
      return formats;
   }

   public Locale getLocale() {
      return this._locale;
   }

   @Override
   public int hashCode() {
      return this.pattern.hashCode();
   }

   private void makeFormat(int position, int offsetNumber, StringBuffer[] segments) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setFormats(Format[] formats) {
      int length = formats == null ? 0 : formats.length;
      this._formats = new Format[length];
      if (formats != null) {
         System.arraycopy(formats, 0, this._formats, 0, length);
      }
   }

   public void setFormat(int variable, Format format) {
      if (this._formats.length <= variable) {
         Array.resize(this._formats, variable + 1);
      }

      this._formats[variable] = format;
   }

   public void setLocale(Locale locale) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
