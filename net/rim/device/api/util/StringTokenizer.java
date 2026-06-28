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
      throw new RuntimeException("cod2jar: string-special");
   }

   private int scanToken(int startPos) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void setMaxDelimChar() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public StringTokenizer(String str, String delim, boolean returnDelims) {
   }

   public StringTokenizer(String str, char delim) {
   }

   private void initializer(String aString, String delim, boolean returnDelims) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public StringTokenizer(String str) {
   }

   public StringTokenizer(String str, String delim) {
   }
}
