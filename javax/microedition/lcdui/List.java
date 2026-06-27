package javax.microedition.lcdui;

public class List extends Screen implements Choice {
   ChoiceGroup _list;
   private Command _selectCommand;
   public static final Command SELECT_COMMAND;

   public void setSelectCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getString(int var1) {
      return this._list.getString(var1);
   }

   @Override
   public Image getImage(int var1) {
      return this._list.getImage(var1);
   }

   @Override
   public int append(String var1, Image var2) {
      int var3 = this._list.append(var1, var2);
      if (this._list.getType() == 3 && this._list.size() == 1 && this._selectCommand != null) {
         this.addCommand(this._selectCommand);
      }

      return var3;
   }

   @Override
   public void insert(int var1, String var2, Image var3) {
      this._list.insert(var1, var2, var3);
      if (this._list.getType() == 3 && this._list.size() == 1 && this._selectCommand != null) {
         this.addCommand(this._selectCommand);
      }
   }

   @Override
   public void delete(int var1) {
      this._list.delete(var1);
      if (this._list.getType() == 3 && this._list.size() == 0) {
         this.removeCommandInternal(this._selectCommand, false);
      }
   }

   @Override
   public void deleteAll() {
      this._list.deleteAll();
      if (this._list.getType() == 3) {
         this.removeCommandInternal(this._selectCommand, false);
      }
   }

   @Override
   public void set(int var1, String var2, Image var3) {
      this._list.set(var1, var2, var3);
   }

   @Override
   public boolean isSelected(int var1) {
      return this._list.isSelected(var1);
   }

   @Override
   public int getSelectedIndex() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getSelectedFlags(boolean[] var1) {
      return this._list.getSelectedFlags(var1);
   }

   @Override
   public void setSelectedIndex(int var1, boolean var2) {
      this._list.setSelectedIndex(var1, var2);
   }

   @Override
   public void setSelectedFlags(boolean[] var1) {
      this._list.setSelectedFlags(var1);
   }

   @Override
   public int size() {
      return this._list.size();
   }

   @Override
   public void setFitPolicy(int var1) {
      this._list.setFitPolicy(var1);
   }

   @Override
   public int getFitPolicy() {
      return this._list.getFitPolicy();
   }

   @Override
   public void setFont(int var1, Font var2) {
      this._list.setFont(var1, var2);
   }

   @Override
   public Font getFont(int var1) {
      return this._list.getFont(var1);
   }

   private void init(String var1, int var2, String[] var3, Image[] var4, boolean var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public List(String var1, int var2, String[] var3, Image[] var4) {
      super(new MIDPScreen());
      this._selectCommand = SELECT_COMMAND;
      this.init(var1, var2, var3, var4, true);
   }

   @Override
   public void removeCommand(Command var1) {
      this.removeCommandInternal(var1, true);
   }

   public List(String var1, int var2) {
      super(new MIDPScreen());
      this._selectCommand = SELECT_COMMAND;
      this.init(var1, var2, null, null, false);
   }

   private void removeCommandInternal(Command var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
