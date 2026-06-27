package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class CustomItem extends Item {
   private VerticalFieldManager _container;
   private CustomLabelField _label;
   private CustomField _field;
   protected static final int TRAVERSE_HORIZONTAL;
   protected static final int TRAVERSE_VERTICAL;
   protected static final int KEY_PRESS;
   protected static final int KEY_RELEASE;
   protected static final int KEY_REPEAT;
   protected static final int POINTER_PRESS;
   protected static final int POINTER_RELEASE;
   protected static final int POINTER_DRAG;
   protected static final int NONE;

   protected CustomItem(String var1) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._field.setChangeListener(null);
      this._field.setChangeListener(var1);
      return this._container;
   }

   public int getGameAction(int var1) {
      return Display.getGameAction(var1);
   }

   protected final int getInteractionModes() {
      return 31;
   }

   protected int getMinContentWidth() {
      throw null;
   }

   protected int getMinContentHeight() {
      throw null;
   }

   protected int getPrefContentWidth(int var1) {
      throw null;
   }

   protected int getPrefContentHeight(int var1) {
      throw null;
   }

   protected void sizeChanged(int var1, int var2) {
   }

   protected final void invalidate() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void paint(Graphics var1, int var2, int var3) {
      throw null;
   }

   protected final void repaint() {
      this._field.callInvalidate();
   }

   protected final void repaint(int var1, int var2, int var3, int var4) {
      this._field.callInvalidate(var1, var2, var3, var4);
   }

   protected boolean traverse(int var1, int var2, int var3, int[] var4) {
      return false;
   }

   protected void traverseOut() {
   }

   protected void keyPressed(int var1) {
   }

   protected void keyReleased(int var1) {
   }

   protected void keyRepeated(int var1) {
   }

   protected void pointerPressed(int var1, int var2) {
   }

   protected void pointerReleased(int var1, int var2) {
   }

   protected void pointerDragged(int var1, int var2) {
   }

   protected void showNotify() {
   }

   protected void hideNotify() {
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
