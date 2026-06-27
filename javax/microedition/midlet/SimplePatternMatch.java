package javax.microedition.midlet;

class SimplePatternMatch {
   private int _offset;
   private String _candidate;
   private String _pattern;
   private int _requiredCount;
   private boolean _gteq;

   public SimplePatternMatch(String var1) {
      this._pattern = var1;
   }

   public boolean match(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean matchRegion(StringBuffer var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private boolean hasRequiredCharCount() {
      throw new RuntimeException("cod2jar: string-special");
   }
}
