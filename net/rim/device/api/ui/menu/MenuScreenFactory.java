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
      MenuList list = null;
      if (_instance._factory != null) {
         list = _instance._factory.createListField();
      }

      if (list == null) {
         list = new DefaultMenuListField();
      }

      return list;
   }

   protected MenuScreen createScreen() {
      throw null;
   }

   public static MenuScreen createScreenWithDefault() {
      MenuScreen screen = null;
      if (_instance._factory != null) {
         screen = _instance._factory.createScreen();
      }

      if (screen == null) {
         screen = new DefaultMenuScreen();
      }

      return screen;
   }

   public static MenuScreenFactory getFactory() {
      return _instance._factory;
   }

   public static void setFactory(MenuScreenFactory factory) {
      _instance._factory = factory;
   }
}
