package net.rim.device.api.ui.theme;

public class Tag {
   private String _name;
   private int _id;

   Tag(String var1, int var2) {
      this._name = var1;
      this._id = var2;
   }

   public static Tag create(String var0) {
      return ThemeManager.createTag(var0);
   }

   public static Tag get(String var0) {
      return ThemeManager.getTag(var0);
   }

   @Override
   public int hashCode() {
      return this._id;
   }

   @Override
   public String toString() {
      return this._name;
   }
}
