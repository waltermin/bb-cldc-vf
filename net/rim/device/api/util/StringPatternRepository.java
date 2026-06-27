package net.rim.device.api.util;

import net.rim.device.internal.applicationcontrol.ApplicationControl;

public final class StringPatternRepository {
   private StringPatternContainer _container = new StringPatternContainer(new StringPattern[0]);
   private static final long NAME_OF_OBJECT;
   private static StringPatternRepository _patternRepository;

   private static final void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }

   private StringPatternRepository() {
   }

   private final synchronized void add(StringPattern var1) {
      StringPattern[] var2 = this._container.getElements();
      int var3 = var2.length;
      StringPattern[] var4 = new StringPattern[var3 + 1];
      System.arraycopy(var2, 0, var4, 0, var3);
      var4[var3] = var1;
      this._container = new StringPatternContainer(var4);
   }

   private final synchronized void remove(long[] var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final synchronized StringPatternContainer getContainer() {
      return this._container;
   }

   public static final void addPattern(StringPattern var0) {
      assertPermission();
      if (var0 == null) {
         throw new Object();
      }

      getInstance().add(var0);
   }

   public static final void removeExternalPatterns(long[] var0) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final StringPatternRepository getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
