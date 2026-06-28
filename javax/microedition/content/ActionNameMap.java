package javax.microedition.content;

public final class ActionNameMap {
   private final String[] _actions;
   private final String[] _actionnames;
   private final String _locale;

   public ActionNameMap(String[] actions, String[] actionnames, String locale) {
   }

   public final String getActionName(String action) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getAction(String actionname) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getLocale() {
      return this._locale;
   }

   public final int size() {
      return this._actions.length;
   }

   public final String getAction(int index) {
      if (index >= 0 && index < this.size()) {
         return this._actions[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final String getActionName(int index) {
      if (index >= 0 && index < this.size()) {
         return this._actionnames[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
