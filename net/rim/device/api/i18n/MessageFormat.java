package net.rim.device.api.i18n;

import net.rim.vm.Array;

public class MessageFormat extends Format {
   private Locale _locale = Locale.getDefault();
   private String pattern = "";
   private Format[] _formats = new Format[0];
   private int[] offsets = new int[15];
   private int[] argumentNumbers = new int[15];
   private int maxOffset = -1;
   private static final String[] typeList;
   private static final String[] dateModifierList;
   private static final int MAX_ARGUMENTS;

   public MessageFormat(String pattern) {
      this.applyPattern(pattern);
   }

   private MessageFormat(String pattern, Locale locale) {
      this._locale = locale;
      this.applyPattern(pattern);
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
      throw new RuntimeException("cod2jar: type check");
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
      int oldMaxOffset = this.maxOffset;

      try {
         int argumentNumber = Integer.parseInt(segments[1].toString());
         if (argumentNumber < 0 || argumentNumber >= 15) {
            throw new NumberFormatException();
         }

         this.maxOffset = offsetNumber;
         this.offsets[offsetNumber] = segments[0].length();
         this.argumentNumbers[offsetNumber] = argumentNumber;
      } catch (Exception e) {
         throw new IllegalArgumentException("argument number too large at ");
      }

      Format newFormat;
      newFormat = null;
      label44:
      switch (findKeyword(segments[2].toString(), typeList)) {
         case -1:
         case 1:
            this.maxOffset = oldMaxOffset;
            throw new IllegalArgumentException("Unknown format type");
         case 0:
            break;
         case 2:
         default:
            switch (findKeyword(segments[3].toString(), dateModifierList)) {
               case -1:
                  newFormat = DateFormat.getInstance(48);

                  try {
                     ((SimpleDateFormat)newFormat).applyPattern(segments[3].toString());
                     break label44;
                  } catch (Exception e) {
                     this.maxOffset = oldMaxOffset;
                     throw new IllegalArgumentException("Invalid pattern");
                  }
               case 0:
               default:
                  newFormat = DateFormat.getInstance(48);
                  break label44;
               case 1:
                  newFormat = DateFormat.getInstance(56);
                  break label44;
               case 2:
                  newFormat = DateFormat.getInstance(48);
                  break label44;
               case 3:
                  newFormat = DateFormat.getInstance(40);
                  break label44;
               case 4:
                  newFormat = DateFormat.getInstance(32);
                  break label44;
            }
         case 3:
            switch (findKeyword(segments[3].toString(), dateModifierList)) {
               case -1:
                  newFormat = DateFormat.getInstance(6);

                  try {
                     ((SimpleDateFormat)newFormat).applyPattern(segments[3].toString());
                     break;
                  } catch (Exception e) {
                     this.maxOffset = oldMaxOffset;
                     throw new IllegalArgumentException("Invalid pattern");
                  }
               case 0:
               default:
                  newFormat = DateFormat.getInstance(6);
                  break;
               case 1:
               case 2:
                  newFormat = DateFormat.getInstance(7);
                  break;
               case 3:
               case 4:
                  newFormat = DateFormat.getInstance(6);
                  break;
               case 5:
               case 6:
                  newFormat = DateFormat.getInstance(5);
                  break;
               case 7:
               case 8:
                  newFormat = DateFormat.getInstance(4);
            }
      }

      this.setFormat(offsetNumber, newFormat);
      segments[1].setLength(0);
      segments[2].setLength(0);
      segments[3].setLength(0);
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
