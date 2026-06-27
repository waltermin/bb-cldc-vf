package net.rim.device.api.ui.menu;

public class MenuScreenFactory {
   private static final long GUID;
   private static MenuScreenFactory$Instance _instance;

   protected MenuScreenFactory() {
   }

   protected MenuList createListField() {
      throw null;
   }

   public static MenuList createListFieldWithDefault() {
      MenuList var0 = null;
      if (_instance._factory != null) {
         var0 = _instance._factory.createListField();
      }

      if (var0 == null) {
         var0 = new DefaultMenuListField();
      }

      return var0;
   }

   protected MenuScreen createScreen() {
      throw null;
   }

   public static MenuScreen createScreenWithDefault() {
      MenuScreen var0 = null;
      if (_instance._factory != null) {
         var0 = _instance._factory.createScreen();
      }

      if (var0 == null) {
         var0 = new DefaultMenuScreen();
      }

      return var0;
   }

   public static MenuScreenFactory getFactory() {
      return _instance._factory;
   }

   public static void setFactory(MenuScreenFactory var0) {
      _instance._factory = var0;
   }
}
