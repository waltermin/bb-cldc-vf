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

   public String nextToken(String var1) {
      this.delimiters = var1;
      this.delimsChanged = true;
      this.setMaxDelimChar();
      return this.nextToken();
   }

   public int countTokens() {
      int var1 = 0;

      for (int var2 = this.currentPosition; var2 < this.maxPosition; var1++) {
         var2 = this.skipDelimiters(var2);
         if (var2 >= this.maxPosition) {
            return var1;
         }

         var2 = this.scanToken(var2);
      }

      return var1;
   }

   @Override
   public Object nextElement() {
      return this.nextToken();
   }

   @Override
   public boolean hasMoreElements() {
      return this.hasMoreTokens();
   }

   private int skipDelimiters(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int scanToken(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void setMaxDelimChar() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public StringTokenizer(String var1, String var2, boolean var3) {
   }

   public StringTokenizer(String var1, char var2) {
   }

   private void initializer(String var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public StringTokenizer(String var1) {
   }

   public StringTokenizer(String var1, String var2) {
   }
}
