package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.container.VerticalFieldManager;

class BasicChoice extends Item implements Choice {
   VerticalFieldManager _container = new BasicChoice$1(this, 1152921504606846976L);
   boolean _onScreen;
   int _currentlySelectedIndex = -1;
   protected FieldChangeListener _changeListener;
   private int _numChoices;
   int _type;
   private int _fitPolicy;
   private Font[] _fonts = new Font[0];

   Field getField(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void doSetSelectedFlags(boolean[] var1) {
      throw null;
   }

   protected void doSetSelectedIndex(int var1, boolean var2) {
      throw null;
   }

   protected boolean doIsSelected(int var1) {
      throw null;
   }

   protected void doSet(int var1, String var2, Image var3) {
      throw null;
   }

   protected void doDelete(int var1) {
      throw null;
   }

   protected void doInsert(int var1, String var2, Image var3) {
      throw null;
   }

   protected Image doGetImage(int var1) {
      throw null;
   }

   protected String doGetString(int var1) {
      throw null;
   }

   protected void setFieldFont(net.rim.device.api.ui.Font var1, int var2) {
      this.getField(var2).setFont(var1);
   }

   @Override
   public void setSelectedIndex(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setSelectedFlags(boolean[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setFitPolicy(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getFitPolicy() {
      return this._fitPolicy;
   }

   @Override
   public void setFont(int var1, Font var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Font getFont(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getSelectedFlags(boolean[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean isSelected(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void set(int var1, String var2, Image var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void deleteAll() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void delete(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void insert(int var1, String var2, Image var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int append(String var1, Image var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Image getImage(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getString(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int size() {
      return this._numChoices;
   }

   @Override
   public int getSelectedIndex() {
      throw null;
   }

   private void checkIndex(int var1) {
      if (var1 < 0 || var1 >= this._numChoices) {
         throw new Object();
      }
   }
}
