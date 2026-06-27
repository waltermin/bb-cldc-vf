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

   private SimpleDateFormat(Locale var1, boolean var2) {
      if (var1 == null) {
         throw new Object();
      }

      this._localeFixed = var2;
      this._localeCode = var1.getCode();
      if (this._localeFixed) {
         this._symbols = DateFormatSymbols.getInstance(var1);
      } else {
         this._symbols = DateFormatSymbols.getInstance();
      }
   }

   public SimpleDateFormat(int var1) {
      this(Locale.getDefault(), false);
      this.applyPattern(var1);
   }

   public SimpleDateFormat(String var1) {
      this(Locale.getDefault(), false);
      this.applyPattern(var1);
   }

   public SimpleDateFormat(String var1, Locale var2) {
      this(var2, true);
      this.applyPattern(var1);
   }

   public String getPattern() {
      return this._patternResult;
   }

   public void applyPattern(int var1) {
      this._pattern = null;
      this._style = var1;
      this._patternResult = this._symbols.getPattern(var1);
      this.compilePattern(this._patternResult);
   }

   public void applyPattern(String var1) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private void checkLocale() {
      if (!this._localeFixed) {
         Locale var1 = Locale.getDefault();
         if (var1.getCode() != this._localeCode) {
            DateFormatSymbols.settingChanged();
            this._patternResult = this._pattern;
            if (this._patternResult == null) {
               this._patternResult = this._symbols.getPattern(this._style);
            }

            this.compilePattern(this._patternResult);
         }
      }
   }

   private void compilePattern(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static int findLength(String var0, int var1, char var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public StringBuffer format(Calendar var1, StringBuffer var2, FieldPosition var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int[] getFields() {
      this.checkLocale();
      int[] var1 = new int[this._numFields];
      char[] var2 = this._compiledPattern;
      int var3 = this._compiledLength;
      int var4 = 0;
      int var5 = 0;
      var4 = 0;

      while (var4 < var3) {
         char var6 = var2[var4++];
         switch (var6) {
            case '￼':
               var1[var5++] = var6;
               var4++;
               break;
            case '�':
               var1[var5++] = -1;
               break;
            case '\ufffe':
               var4++;
               break;
            case '\uffff':
            default:
               char var7 = var2[var4++];
               var4 += var7;
         }
      }

      return var1;
   }

   public String toPattern() {
      return this._patternResult;
   }
}
