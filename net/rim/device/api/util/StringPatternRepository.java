package net.rim.device.api.util;

import net.rim.device.api.system.ApplicationRegistry;
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

   private final synchronized void add(StringPattern pattern) {
      StringPattern[] origPatterns = this._container.getElements();
      int count = origPatterns.length;
      StringPattern[] newPatterns = new StringPattern[count + 1];
      System.arraycopy(origPatterns, 0, newPatterns, 0, count);
      newPatterns[count] = pattern;
      this._container = new StringPatternContainer(newPatterns);
   }

   private final synchronized void remove(long[] ids) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final synchronized StringPatternContainer getContainer() {
      return this._container;
   }

   public static final void addPattern(StringPattern pattern) {
      assertPermission();
      if (pattern == null) {
         throw new Object();
      }

      getInstance().add(pattern);
   }

   public static final void removeExternalPatterns(long[] ids) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final StringPatternRepository getInstance() {
      if (_patternRepository != null) {
         return _patternRepository;
      }

      ApplicationRegistry appRegistry = ApplicationRegistry.getApplicationRegistry();
      synchronized (appRegistry) {
         _patternRepository = (StringPatternRepository)appRegistry.get(175320883679689398L);
         if (_patternRepository == null) {
            _patternRepository = new StringPatternRepository();
            appRegistry.put(175320883679689398L, _patternRepository);
         }
      }

      return _patternRepository;
   }
}
