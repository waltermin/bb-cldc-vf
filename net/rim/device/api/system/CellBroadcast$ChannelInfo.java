package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;

public final class CellBroadcast$ChannelInfo extends CellBroadcast$Info implements Persistable {
   private String _nickname;

   public CellBroadcast$ChannelInfo(int var1) {
      super(var1);
      this.setEnabled(true);
   }

   private CellBroadcast$ChannelInfo() {
   }

   public final String getNickname() {
      return this._nickname;
   }

   public final void setNickname(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final CellBroadcast$ChannelInfo clone() {
      CellBroadcast$ChannelInfo var1 = new CellBroadcast$ChannelInfo();
      var1._nickname = this._nickname;
      this.copyInto(var1);
      return var1;
   }
}
