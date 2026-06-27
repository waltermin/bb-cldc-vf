package net.rim.device.api.ui.component;

public class ObjectChoiceField extends ChoiceField {
   private Object[] _choices;

   public ObjectChoiceField() {
      this(null, null, 0, 0);
   }

   public ObjectChoiceField(String var1, Object[] var2) {
      this(var1, var2, 0, 0);
   }

   public ObjectChoiceField(String var1, Object[] var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public ObjectChoiceField(String var1, Object[] var2, int var3, long var4) {
      super(var1, var2 != null ? var2.length : 0, var3, var4);
      int var6 = var2 != null ? var2.length : 0;
      this._choices = new Object[var6];
      if (var6 != 0) {
         System.arraycopy(var2, 0, this._choices, 0, var6);
      }
   }

   public ObjectChoiceField(String var1, Object[] var2, Object var3) {
      super(var1, var2.length, 0);
      this.setChoices(var2);
      this.setSelectedIndex(var3);
   }

   @Override
   public Object getChoice(int var1) {
      if (this._choices.length == 0 && var1 == 0) {
         return null;
      } else if (var1 >= 0 && this._choices.length > var1) {
         return this._choices[var1];
      } else {
         throw new Object(Integer.toString(var1));
      }
   }

   public void setChoices(Object[] var1) {
      int var2 = var1 != null ? var1.length : 0;
      this._choices = new Object[var2];
      if (var2 != 0) {
         System.arraycopy(var1, 0, this._choices, 0, var2);
      }

      this.setSize(var2);
      super._selectedWidth = 0;
   }
}
