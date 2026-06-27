package net.rim.device.api.ui.theme;

class Theme$AppIconKey {
   private String _name;
   private int _state;
   private int _hash;
   private int _size;

   Theme$AppIconKey(String var1, int var2, int var3) {
      this._name = var1;
      this._state = var2;
      this._size = var3;
      this._hash = var1.hashCode() ^ (var2 | var3 << 4);
   }

   @Override
   public boolean equals(Object var1) {
      Theme$AppIconKey var2 = (Theme$AppIconKey)var1;
      return this._hash == var2._hash && this._state == var2._state && this._name.equals(var2._name) && this._size == var2._size;
   }

   @Override
   public int hashCode() {
      return this._hash;
   }
}
