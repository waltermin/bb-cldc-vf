package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ChoiceGroup extends Item implements Choice {
   private VerticalFieldManager _container;
   private LabelField _label;
   private BasicChoice _choiceImpl;

   final int getType() {
      return this._choiceImpl._type;
   }

   public ChoiceGroup(String var1, int var2) {
   }

   public ChoiceGroup(String var1, int var2, String[] var3, Image[] var4) {
   }

   ChoiceGroup(int var1, String[] var2, Image[] var3, boolean var4) {
      this._container = (VerticalFieldManager)(new Object(1152921504606846976L));
      if (var1 >= 1 && var1 <= 3) {
         this.setPeer(this._container);
         this.init(null, var1, var2, var3, var4);
      } else {
         throw new Object();
      }
   }

   private void init(String var1, int var2, String[] var3, Image[] var4, boolean var5) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setLayout(int var1) {
      super.setLayout(var1);
      this._choiceImpl.setLayout(var1);
   }

   @Override
   public int getLayout() {
      return this._choiceImpl.getLayout();
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._choiceImpl.addToForm(var1);
      return this._container;
   }

   @Override
   public int size() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getString(int var1) {
      return this._choiceImpl.getString(var1);
   }

   @Override
   public Image getImage(int var1) {
      return this._choiceImpl.getImage(var1);
   }

   @Override
   public int append(String var1, Image var2) {
      return this._choiceImpl.append(var1, var2);
   }

   @Override
   public void insert(int var1, String var2, Image var3) {
      this._choiceImpl.insert(var1, var2, var3);
   }

   @Override
   public void delete(int var1) {
      this._choiceImpl.delete(var1);
   }

   @Override
   public void deleteAll() {
      this._choiceImpl.deleteAll();
   }

   @Override
   public void set(int var1, String var2, Image var3) {
      this._choiceImpl.set(var1, var2, var3);
   }

   @Override
   public boolean isSelected(int var1) {
      return this._choiceImpl.isSelected(var1);
   }

   @Override
   public int getSelectedIndex() {
      return this._choiceImpl.getSelectedIndex();
   }

   @Override
   public int getSelectedFlags(boolean[] var1) {
      return this._choiceImpl.getSelectedFlags(var1);
   }

   @Override
   public void setSelectedIndex(int var1, boolean var2) {
      this._choiceImpl.setSelectedIndex(var1, var2);
   }

   @Override
   public void setSelectedFlags(boolean[] var1) {
      this._choiceImpl.setSelectedFlags(var1);
   }

   @Override
   public void setFitPolicy(int var1) {
      this._choiceImpl.setFitPolicy(var1);
   }

   @Override
   public int getFitPolicy() {
      return this._choiceImpl.getFitPolicy();
   }

   @Override
   public void setFont(int var1, Font var2) {
      this._choiceImpl.setFont(var1, var2);
   }

   @Override
   public Font getFont(int var1) {
      return this._choiceImpl.getFont(var1);
   }
}
