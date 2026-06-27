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

   public StringMatch(String var1, boolean var2) {
      this(var1, var2, true);
   }

   public StringMatch(String var1, boolean var2, boolean var3) {
      this(var1, var2, var3, true);
   }

   public StringMatch(String var1, boolean var2, boolean var3, boolean var4) {
   }

   public StringMatch(String[] var1, boolean var2, boolean var3) {
   }

   public StringMatch(String var1) {
      this(var1, true);
   }

   public final int indexOf(String var1) {
      return this.KMPMatch(var1, 0);
   }

   public final int indexOf(String var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final int indexOf(StringBuffer var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final int numStringsInPattern() {
      return this._patterns.length;
   }

   public final int getLastMatchedPattern() {
      return this._lastMatchedIndex;
   }
}
