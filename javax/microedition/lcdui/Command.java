package javax.microedition.lcdui;

public class Command {
   private String _label;
   private String _longLabel;
   private String _menuLabel;
   private int _commandType;
   private int _priority;
   public static final int SCREEN;
   private static final int FIRST_COMMAND_TYPE;
   public static final int BACK;
   public static final int CANCEL;
   public static final int OK;
   public static final int HELP;
   public static final int STOP;
   public static final int EXIT;
   public static final int ITEM;
   private static final int LAST_COMMAND_TYPE;

   public Command(String label, int commandType, int priority) {
   }

   public Command(String shortLabel, String longLabel, int commandType, int priority) {
      this(shortLabel, commandType, priority);
      if (longLabel != null) {
         this._longLabel = longLabel;
         this._menuLabel = longLabel;
      }
   }

   public String getLabel() {
      return this._label;
   }

   public String getLongLabel() {
      return this._longLabel;
   }

   public int getCommandType() {
      return this._commandType;
   }

   public int getPriority() {
      return this._priority;
   }

   String getMenuLabel() {
      return this._menuLabel;
   }

   void setMenuLabel(String menuLabel) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
