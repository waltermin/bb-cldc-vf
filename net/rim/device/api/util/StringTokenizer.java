package net.rim.device.api.util;

import java.util.Enumeration;

public class StringTokenizer implements Enumeration {
   private int currentPosition;
   private int newPosition;
   private int maxPosition;
   private String str;
   private String delimiters;
   private boolean retDelims;
   private boolean delimsChanged;
   private char maxDelimChar;
   private char[] oneChar;

   public boolean hasMoreTokens() {
      this.newPosition = this.skipDelimiters(this.currentPosition);
      return this.newPosition < this.maxPosition;
   }

   public String nextToken() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public String nextToken(String delim) {
      this.delimiters = delim;
      this.delimsChanged = true;
      this.setMaxDelimChar();
      return this.nextToken();
   }

   public int countTokens() {
      int count = 0;

      for (int currpos = this.currentPosition; currpos < this.maxPosition; count++) {
         currpos = this.skipDelimiters(currpos);
         if (currpos >= this.maxPosition) {
            return count;
         }

         currpos = this.scanToken(currpos);
      }

      return count;
   }

   @Override
   public Object nextElement() {
      return this.nextToken();
   }

   @Override
   public boolean hasMoreElements() {
      return this.hasMoreTokens();
   }

   private int skipDelimiters(int startPos) {
      int position;
      for (position = startPos; !this.retDelims && position < this.maxPosition; position++) {
         char c = this.str.charAt(position);
         if (c > this.maxDelimChar) {
            break;
         }

         if (this.delimiters.indexOf(c) < 0) {
            return position;
         }
      }

      return position;
   }

   private int scanToken(int startPos) {
      int position;
      for (position = startPos; position < this.maxPosition; position++) {
         char c = this.str.charAt(position);
         if (c <= this.maxDelimChar && this.delimiters.indexOf(c) >= 0) {
            break;
         }
      }

      if (this.retDelims && startPos == position) {
         char c = this.str.charAt(position);
         if (c <= this.maxDelimChar && this.delimiters.indexOf(c) >= 0) {
            position++;
         }
      }

      return position;
   }

   private void setMaxDelimChar() {
      if (this.delimiters == null) {
         this.maxDelimChar = 0;
      } else {
         char m = 0;
         int delimitersLength = this.delimiters.length();

         for (int i = 0; i < delimitersLength; i++) {
            char c = this.delimiters.charAt(i);
            if (m < c) {
               m = c;
            }
         }

         this.maxDelimChar = m;
      }
   }

   public StringTokenizer(String str, String delim, boolean returnDelims) {
   }

   public StringTokenizer(String str, char delim) {
   }

   private void initializer(String aString, String delim, boolean returnDelims) {
      if (aString != null && delim != null) {
         this.currentPosition = 0;
         this.newPosition = -1;
         this.str = aString;
         this.maxPosition = this.str.length();
         this.delimiters = delim;
         this.retDelims = returnDelims;
         this.setMaxDelimChar();
      } else {
         throw new NullPointerException();
      }
   }

   public StringTokenizer(String str) {
   }

   public StringTokenizer(String str, String delim) {
   }
}
