package net.rim.device.api.util;

public final class StringMatch implements Persistable {
   private String[] _patterns;
   private int[][][] _failLinks;
   private boolean _caseSensitivity;
   private boolean _allMatch;
   private int _lastMatchedIndex;

   private static final native void KMPInit(String var0, int[] var1);

   private final native int KMPMatch(String var1, int var2);

   private final native int KMPMatch(StringBuffer var1, int var2);

   public StringMatch(String patternToMatch, boolean caseSensitivity) {
      this(patternToMatch, caseSensitivity, true);
   }

   public StringMatch(String patternToMatch, boolean caseSensitivity, boolean spaceDelimitsNewPattern) {
      this(patternToMatch, caseSensitivity, spaceDelimitsNewPattern, true);
   }

   public StringMatch(String patternToMatch, boolean caseSensitivity, boolean spaceDelimitsNewPattern, boolean allMatch) {
   }

   public StringMatch(String[] patterns, boolean caseSensitivity, boolean allMatch) {
   }

   public StringMatch(String pattern) {
      this(pattern, true);
   }

   public final int indexOf(String text) {
      return this.KMPMatch(text, 0);
   }

   public final int indexOf(String text, int offset) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final int indexOf(StringBuffer text, int offset) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final int numStringsInPattern() {
      return this._patterns.length;
   }

   public final int getLastMatchedPattern() {
      return this._lastMatchedIndex;
   }
}
