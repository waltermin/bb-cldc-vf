package net.rim.device.api.crypto;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.util.Arrays;

public class DigestFactory {
   private static final String[] DIGESTS;
   private static Hashtable _hashtable;

   protected DigestFactory() {
   }

   public static Digest getInstance(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean isDigestWeakByPolicy(String var0) {
      int var1 = Arrays.getIndex(DIGESTS, var0);
      if (var1 == -1) {
         return false;
      }

      int var2 = ITPolicy.getInteger(24, 83, 0);
      return (var2 & 1 << var1) != 0;
   }

   public static void register(DigestFactory var0) {
      if (var0 == null) {
         throw new Object();
      }

      String[] var1 = var0.getFactoryAlgorithms();

      for (int var2 = 0; var2 < var1.length; var2++) {
         if (_hashtable.get(var1[var2]) == null) {
            _hashtable.put(var1[var2], var0);
         }
      }
   }

   public static Enumeration getAlgorithms() {
      return _hashtable.keys();
   }

   protected String[] getFactoryAlgorithms() {
      throw null;
   }

   protected Digest create(String var1) {
      throw null;
   }
}
