package net.rim.device.api.i18n;

public class CompiledResourceBundle extends ResourceBundle {
   private final long _bundleId;
   private final int[] _ids;
   private final short[] _offsets;
   private final byte[] _data;
   private int _offset;
   private static String[] ENCODINGS;

   protected CompiledResourceBundle(Locale var1, long var2, int[] var4, short[] var5, byte[] var6) {
      super(var1);
      this._bundleId = var2;
      this._ids = var4;
      this._offsets = var5;
      this._data = var6;
   }

   @Override
   long getId() {
      return this._bundleId;
   }

   @Override
   protected Object handleGetObject(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private Object read() {
      String[] var1 = null;
      byte var2 = this.readTag();
      switch (var2) {
         case 91:
            byte var3 = this.readTag();
            short var4 = this.readLength();
            if (var3 == 115) {
               String[] var5 = new String[var4];

               for (int var6 = 0; var6 < var4; var6++) {
                  var5[var6] = this.readPartString();
               }

               var1 = var5;
            }
         default:
            return var1;
         case 115:
            return this.readPartString();
      }
   }

   private short readLength() {
      short var1 = (short)(this._data[this._offset] << 8 | 0xFF & this._data[this._offset + 1]);
      this._offset += 2;
      return var1;
   }

   private String readPartString() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private byte readTag() {
      byte var1 = this._data[this._offset];
      this._offset++;
      return var1;
   }
}
