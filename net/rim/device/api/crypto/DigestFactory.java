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

   public static Digest getInstance(String algorithm) {
      if (algorithm == null) {
         throw new Object();
      }

      String checkAlgorithm = algorithm;

      do {
         String baseAlgorithm = RIMFactoryUtilities.getBaseAlgorithm(checkAlgorithm);
         DigestFactory factory = (DigestFactory)_hashtable.get(baseAlgorithm);
         if (factory != null) {
            try {
               return factory.create(baseAlgorithm);
            } catch (ClassCastException e) {
               throw new Object();
            }
         }

         checkAlgorithm = RIMFactoryUtilities.stripBaseAlgorithm(checkAlgorithm);
      } while (checkAlgorithm != null);

      throw new NoSuchAlgorithmException(algorithm);
   }

   public static boolean isDigestWeakByPolicy(String algorithm) {
      int flag = Arrays.getIndex(DIGESTS, algorithm);
      if (flag == -1) {
         return false;
      }

      int policyMask = ITPolicy.getInteger(24, 83, 0);
      return (policyMask & 1 << flag) != 0;
   }

   public static void register(DigestFactory factory) {
      if (factory == null) {
         throw new Object();
      }

      String[] algorithms = factory.getFactoryAlgorithms();

      for (int i = 0; i < algorithms.length; i++) {
         if (_hashtable.get(algorithms[i]) == null) {
            _hashtable.put(algorithms[i], factory);
         }
      }
   }

   public static Enumeration getAlgorithms() {
      return _hashtable.keys();
   }

   protected String[] getFactoryAlgorithms() {
      throw null;
   }

   protected Digest create(String _1) {
      throw null;
   }
}
