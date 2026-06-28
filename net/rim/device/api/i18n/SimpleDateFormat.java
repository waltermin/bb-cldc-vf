package net.rim.device.api.i18n;

import java.util.Calendar;

public class SimpleDateFormat extends DateFormat {
   private char[] _compiledPattern;
   private int _compiledLength;
   private int _numFields;
   private String _pattern;
   private String _patternResult;
   private DateFormatSymbols _symbols;
   private int _localeCode;
   private boolean _localeFixed;
   private int _style;
   private static final int TEXT_FIELD;
   private static final int CHAR_FIELD;
   private static final int NI_FIELD;

   private SimpleDateFormat(Locale locale, boolean localeFixed) {
      if (locale == null) {
         throw new IllegalArgumentException();
      }

      this._localeFixed = localeFixed;
      this._localeCode = locale.getCode();
      if (this._localeFixed) {
         this._symbols = DateFormatSymbols.getInstance(locale);
      } else {
         this._symbols = DateFormatSymbols.getInstance();
      }
   }

   public SimpleDateFormat(int style) {
      this(Locale.getDefault(), false);
      this.applyPattern(style);
   }

   public SimpleDateFormat(String pattern) {
      this(Locale.getDefault(), false);
      this.applyPattern(pattern);
   }

   public SimpleDateFormat(String pattern, Locale locale) {
      this(locale, true);
      this.applyPattern(pattern);
   }

   public String getPattern() {
      return this._patternResult;
   }

   public void applyPattern(int style) {
      this._pattern = null;
      this._style = style;
      this._patternResult = this._symbols.getPattern(style);
      this.compilePattern(this._patternResult);
   }

   public void applyPattern(String pattern) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private void checkLocale() {
      if (!this._localeFixed) {
         Locale locale = Locale.getDefault();
         if (locale.getCode() != this._localeCode) {
            DateFormatSymbols.settingChanged();
            this._patternResult = this._pattern;
            if (this._patternResult == null) {
               this._patternResult = this._symbols.getPattern(this._style);
            }

            this.compilePattern(this._patternResult);
         }
      }
   }

   private void compilePattern(String pattern) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static int findLength(String s, int offset, char ch) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public StringBuffer format(Calendar calendar, StringBuffer toAppendTo_o, FieldPosition pos_io) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int[] getFields() {
      this.checkLocale();
      int[] fields = new int[this._numFields];
      char[] compiledPattern = this._compiledPattern;
      int compiledLength = this._compiledLength;
      int patternIndex = 0;
      int fieldIndex = 0;
      patternIndex = 0;

      while (patternIndex < compiledLength) {
         int type = compiledPattern[patternIndex++];
         switch (type) {
            case 65532:
               fields[fieldIndex++] = type;
               patternIndex++;
               break;
            case 65533:
               fields[fieldIndex++] = -1;
               break;
            case 65534:
               patternIndex++;
               break;
            case 65535:
            default:
               int len = compiledPattern[patternIndex++];
               patternIndex += len;
         }
      }

      return fields;
   }

   public String toPattern() {
      return this._patternResult;
   }
}
