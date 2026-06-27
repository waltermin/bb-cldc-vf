package javax.microedition.lcdui;

import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.SimpleSortingVector;

class ItemCommands implements Comparator {
   private SimpleSortingVector _commands = (SimpleSortingVector)(new Object());
   private Command _defaultCommand;

   public SimpleSortingVector getCommands() {
      return this._commands;
   }

   public void addCommand(Command var1) {
      if (var1 != null) {
         int var2 = this._commands.size();

         for (int var3 = 0; var3 < var2; var3++) {
            if (this._commands.elementAt(var3) == var1) {
               return;
            }
         }

         this._commands.addElement(var1);
      }
   }

   public void removeCommand(Command var1) {
      if (var1 != null && this._commands != null) {
         int var2 = this._commands.size();

         for (int var3 = 0; var3 < var2; var3++) {
            if (this._commands.elementAt(var3) == var1) {
               this._commands.removeElementAt(var3);
               if (var1 == this._defaultCommand) {
                  this._defaultCommand = null;
                  return;
               }
               break;
            }
         }
      }
   }

   public void setDefaultCommand(Command var1) {
      this._defaultCommand = var1;
      if (this._defaultCommand != null) {
         this._commands.addElement(this._defaultCommand);
      }
   }

   @Override
   public int compare(Object var1, Object var2) {
      if (this._defaultCommand != null && var1 == this._defaultCommand) {
         return -1;
      } else {
         return this._defaultCommand != null && var2 == this._defaultCommand ? 1 : ((Command)var1).getPriority() - ((Command)var2).getPriority();
      }
   }

   public ItemCommands() {
      this._commands.setSortComparator(this);
      this._commands.setSort(true);
   }
}
