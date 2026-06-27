package javax.microedition.content;

public final class ActionNameMap {
   private final String[] _actions;
   private final String[] _actionnames;
   private final String _locale;

   public ActionNameMap(String[] var1, String[] var2, String var3) {
   }

   public final String getActionName(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getAction(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getLocale() {
      return this._locale;
   }

   public final int size() {
      return this._actions.length;
   }

   public final String getAction(int var1) {
      if (var1 >= 0 && var1 < this.size()) {
         return this._actions[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final String getActionName(int var1) {
      if (var1 >= 0 && var1 < this.size()) {
         return this._actionnames[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
