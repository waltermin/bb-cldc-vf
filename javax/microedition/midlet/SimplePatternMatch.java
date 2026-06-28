package javax.microedition.midlet;

class SimplePatternMatch {
   private int _offset;
   private String _candidate;
   private String _pattern;
   private int _requiredCount;
   private boolean _gteq;

   public SimplePatternMatch(String pattern) {
      this._pattern = pattern;
   }

   public boolean match(String candidate) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean matchRegion(StringBuffer region, int anchor) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private boolean hasRequiredCharCount() {
      throw new RuntimeException("cod2jar: string-special");
   }
}
