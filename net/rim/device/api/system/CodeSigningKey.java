package net.rim.device.api.system;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.Persistable;

public final class CodeSigningKey implements Persistable {
   private int _signerId;
   private byte[] _publicKey;
   private String _description;
   public static final int RRI_SIGNER_ID;
   public static final int RRT_SIGNER_ID;
   public static final int RCC_SIGNER_ID;
   public static final int RCI_SIGNER_ID;
   public static final int RCR_SIGNER_ID;
   public static final int RBB_SIGNER_ID;
   public static final int RBA_SIGNER_ID;
   public static final int RATT_SIGNER_ID;
   private static IntHashtable _builtInKeys;

   private CodeSigningKey() {
   }

   public CodeSigningKey(String var1, byte[] var2, String var3) {
      this(convert(var1), var2, var3);
   }

   public CodeSigningKey(int var1, byte[] var2, String var3) {
      this._signerId = var1;
      this._publicKey = var2;
      this._description = var3;
   }

   public final String getSignerId() {
      return convert(this._signerId);
   }

   public final int getSignerIdAsInt() {
      return this._signerId;
   }

   public final byte[] getPublicKey() {
      return Arrays.copy(this._publicKey);
   }

   final byte[] getPublicKeyInternal() {
      return this._publicKey;
   }

   public final String getDescription() {
      return this._description;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final native CodeSigningKey get(Object var0);

   public static final native CodeSigningKey get(int var0, int var1);

   public static final CodeSigningKey get(int var0, String var1) {
      return get(var0, convert(var1));
   }

   public static final CodeSigningKey getBuiltInKey(int var0) {
      CodeSigningKey var1 = (CodeSigningKey)_builtInKeys.get(var0);
      if (var1 == null) {
         var1 = getBuiltInKey2(var0);
         _builtInKeys.put(var0, var1);
      }

      return var1;
   }

   private static final native CodeSigningKey getBuiltInKey2(int var0);

   public static final int convert(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String convert(int var0) {
      byte[] var1 = new byte[]{(byte)var0, (byte)(var0 >> 8), (byte)(var0 >> 16), (byte)(var0 >> 24)};
      int var2 = 0;

      while (var2 < 4 && var1[var2] != 0) {
         var2++;
      }

      return new String(var1, 0, var2);
   }
}
