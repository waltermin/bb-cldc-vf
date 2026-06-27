package net.rim.device.api.io;

import net.rim.device.api.system.MobitexPacketHeader;

public final class MobitexAddress extends DatagramAddressBase {
   public static final int TYPE_TEXT;
   public static final int TYPE_DATA;
   public static final int TYPE_STATUS;
   public static final int TYPE_HPDATA;

   public MobitexAddress() {
      throw new Object();
   }

   public MobitexAddress(int var1, int var2, int var3) {
      this();
   }

   public MobitexAddress(int var1, int var2, int var3, MobitexPacketHeader var4) {
      this();
   }

   public MobitexAddress(DatagramAddressBase var1) {
      this();
   }

   public MobitexAddress(String var1) {
      this();
   }

   public final MobitexPacketHeader getPacketHeader() {
      return null;
   }

   public final void setPacketHeader(MobitexPacketHeader var1) {
   }

   public final int getMan() {
      return 0;
   }

   public final int getType() {
      return 0;
   }

   public final int getHpid() {
      return 0;
   }

   @Override
   public final void setAddress(String var1) {
   }

   @Override
   public final String getAddress() {
      return null;
   }

   @Override
   public final boolean equals(Object var1) {
      return false;
   }

   @Override
   public final int hashCode() {
      return 0;
   }

   public static final String makeAddress(boolean var0, int var1, int var2, int var3) {
      throw new Object();
   }

   public static final void appendAddress(StringBuffer var0, boolean var1, int var2, int var3, int var4) {
      throw new Object();
   }
}
