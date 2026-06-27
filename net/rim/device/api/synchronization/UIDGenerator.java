package net.rim.device.api.synchronization;

import net.rim.device.api.crypto.RandomSource;
import net.rim.vm.PersistentInteger;

public class UIDGenerator {
   private int _handle = PersistentInteger.getId(-4724165278185536481L, 0);
   private int _nextUID = PersistentInteger.get(this._handle);
   private static final long KEY;
   private static UIDGenerator _generator;
   private static final int BLOCK_SIZE;

   public static int getUniqueScopingValue() {
      int var0;
      do {
         var0 = RandomSource.getInt();
      } while (var0 == 0);

      return var0;
   }

   public static int getUID() {
      return _generator.internalGetUID();
   }

   public static int getUID(int var0) {
      return _generator.internalGetUID();
   }

   public static void resetSeed() {
      _generator.internalResetSeed();
   }

   public static long makeLUID(int var0, int var1) {
      long var2 = var0 & 4294967295L;
      long var4 = var1 & 4294967295L;
      return var2 << 32 | var4;
   }

   private UIDGenerator() {
      if (this._nextUID == 0) {
         this.internalResetSeed();
      } else {
         this.startNewUIDBlock(this._nextUID + 1024);
      }
   }

   private synchronized int startNewUIDBlock(int var1) {
      var1 &= Integer.MAX_VALUE;
      if (var1 == 0) {
         var1++;
      }

      PersistentInteger.set(this._handle, var1);
      this._nextUID = var1;
      return var1;
   }

   private void internalResetSeed() {
      this.startNewUIDBlock(RandomSource.getInt());
   }

   private synchronized int internalGetUID() {
      int var1 = this._nextUID;
      int var2 = PersistentInteger.get(this._handle);
      int var3 = var2 + 1024 & 2147483647;
      int var4 = this._nextUID + 1 & 2147483647;
      if (var4 == 0) {
         var4++;
      }

      if (var2 < var3) {
         if (var4 >= var3) {
            var4 = this.startNewUIDBlock(var4);
         }
      } else if (var4 >= var3 && var4 < var2) {
         var4 = this.startNewUIDBlock(var4);
      }

      this._nextUID = var4;
      return var1;
   }
}
