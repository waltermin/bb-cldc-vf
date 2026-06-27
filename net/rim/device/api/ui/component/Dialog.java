package net.rim.device.api.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.HolsterListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.Image;
import net.rim.device.internal.ui.component.ImageField;

public class Dialog extends PopupScreen implements FieldChangeListener, HolsterListener {
   private RichTextField _label;
   private DialogFieldManager _dfm;
   private ListField _list;
   private Field _focusField;
   private boolean _isModal;
   private boolean _escapeEnabled;
   private int _preferredWidth;
   private int _returnValue;
   private int[] _values;
   private Object[] _choices;
   private int _defaultChoice = Integer.MIN_VALUE;
   private DialogClosedListener _closeListener;
   private CheckboxField _dontAskAgainCheckbox;
   private long _drawStyle = 68;
   private Application _app;
   public static final int D_OK;
   public static final int D_SAVE;
   public static final int D_DELETE;
   public static final int D_YES_NO;
   public static final int D_OK_CANCEL;
   public static final int CANCEL;
   public static final int OK;
   public static final int SAVE;
   public static final int DISCARD;
   public static final int DELETE;
   public static final int YES;
   public static final int NO;
   public static final int LIST;
   public static final int UNDEFINED;
   public static final int GLOBAL_STATUS;
   private static final int[] _resources;
   private static IntHashtable _globalResources;

   public void cancel() {
      this._returnValue = -1;
      this.close();
   }

   public int doModal() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean getDontAskAgainValue() {
      return this.isDontAskAgainChecked();
   }

   public RichTextField getLabel() {
      return this._label;
   }

   public int getSelectedValue() {
      return this._returnValue;
   }

   public void show() {
      this.show(50);
   }

   public void show(int var1) {
      this._isModal = false;
      if (this.isStyle(33554432)) {
         Ui.getUiEngine().pushGlobalScreen(this, var1, 2);
      } else {
         Ui.getUiEngine().pushScreen(this);
      }
   }

   public void setDialogClosedListener(DialogClosedListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public ListField getListField() {
      return this._list;
   }

   public void setIcon(Image var1) {
      Object var2 = null;
      if (var1 != null) {
         var2 = new Object();
         ((ImageField)var2).setImage(var1);
      }

      this._dfm.setIcon((ImageField)var2);
   }

   public boolean isDontAskAgainChecked() {
      return this._dontAskAgainCheckbox == null ? false : this._dontAskAgainCheckbox.getChecked();
   }

   protected void onHotkeySelected(char var1) {
   }

   public void setIcon(EncodedImage var1) {
      Object var2 = null;
      if (var1 != null) {
         var2 = new Object(null, 65568);
         ((BitmapField)var2).setImage(var1);
      }

      this._dfm.setIcon((BitmapField)var2);
   }

   protected void select() {
      int var1 = this._list != null && this.isStyle(1) ? this._list.getSelectedIndex() : this.getLeafFieldWithFocus().getIndex();
      this.selectOrdinal(var1);
   }

   public void select(int var1) {
      this.selectOrdinal(this.ordinalOfValue(var1));
   }

   public final void setDefault(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDontAskAgainPrompt(boolean var1) {
      if (this._dontAskAgainCheckbox != null != var1) {
         if (var1) {
            this._dontAskAgainCheckbox = (CheckboxField)(new Object(CommonResource.getString(10094), false, 1073741824));
            this._dfm.getBottomManager().add(this._dontAskAgainCheckbox);
            return;
         }

         this._dfm.getBottomManager().delete(this._dontAskAgainCheckbox);
         this._dontAskAgainCheckbox = null;
      }
   }

   public final void setEscapeEnabled(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void outOfHolster() {
   }

   @Override
   public void inHolster() {
      if (this._escapeEnabled) {
         this.cancel();
      }
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 instanceof Object) {
         this.select();
      }
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return this._dfm.getAccessibleSelectionAt(var1);
   }

   @Override
   public int getAccessibleSelectionCount() {
      return this._dfm.getAccessibleSelectionCount();
   }

   public Dialog(String var1, Object[] var2, int[] var3, int var4, Bitmap var5, long var6, DialogFieldManager var8) {
      super(var8, var6);
      this.setup(var1, var2, var3, var4, var5);
   }

   public Dialog(String var1, Object[] var2, int[] var3, int var4, Bitmap var5, long var6, long var8) {
      super((Manager)(new Object()), var6);
      this._drawStyle = var8;
      this.setup(var1, var2, var3, var4, var5);
   }

   @Override
   public int getPreferredWidth() {
      return this._preferredWidth;
   }

   protected static String[] getResourceChoices(int var0) {
      return CommonResource.getStringArray(_resources[var0]);
   }

   protected static String getResourceMessage(int var0) {
      return (String)CommonResource.getBundle().getObject(_resources[var0] + 3, true);
   }

   protected static int[] getResourceValues(int var0) {
      return ((Dialog$DialogResources)_globalResources.get(_resources[var0]))._responses;
   }

   protected static int getResourceDefaultValue(int var0) {
      return ((Dialog$DialogResources)_globalResources.get(_resources[var0]))._defaultResponse;
   }

   public Dialog(int var1, String var2, int var3, Bitmap var4, long var5) {
      super((Manager)(new Object()), var5);
      if (var2 == null) {
         var2 = getResourceMessage(var1);
      }

      String[] var7 = getResourceChoices(var1);
      int[] var8 = getResourceValues(var1);
      this.setEscapeEnabled(true);
      this.setup(var2, var7, var8, var3, var4);
   }

   @Override
   protected boolean invokeAction(int var1) {
      if (super.invokeAction(var1)) {
         return true;
      }

      switch (var1) {
         case 1:
            if (this.isStyle(1)) {
               this.select();
               return true;
            }
         default:
            return false;
      }
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this._dfm.isAccessibleChildSelected(var1);
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      boolean var3 = super.navigationClick(var1, var2);
      if (!var3 && this.isStyle(1)) {
         this.select();
         return true;
      } else {
         return var3;
      }
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected void onExposed() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void onObscured() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public Dialog(int var1, String var2, int var3, Bitmap var4, long var5, boolean var7) {
      super((Manager)(new Object()), var5);
      if (var2 == null) {
         var2 = getResourceMessage(var1);
      }

      String[] var8 = getResourceChoices(var1);
      int[] var9 = getResourceValues(var1);
      this.setEscapeEnabled(true);
      this.setup(var2, var8, var9, var3, var4);
      this.setDontAskAgainPrompt(var7);
   }

   private void setup(String var1, Object[] var2, int[] var3, int var4, Bitmap var5) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void add(Field var1) {
      this._dfm.addCustomField(var1);
   }

   @Override
   public int getAccessibleRole() {
      return 7;
   }

   @Override
   public String getAccessibleName() {
      return this.getLabel().getText();
   }

   public static void inform(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static int ask(int var0) {
      return ask(var0, null);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int ask(int var0, String var1) {
      return ask(var0, var1, getResourceDefaultValue(var0));
   }

   private int ordinalOfValue(int var1) {
      int var2 = var1;
      if (this._values != null) {
         var2 = -1;

         for (int var3 = 0; var3 < this._values.length; var3++) {
            if (this._values[var3] == var1) {
               var2 = var3;
            }
         }
      }

      return var2;
   }

   @Override
   public int getAccessibleChildCount() {
      return this._dfm.getAccessibleChildCount();
   }

   public static int ask(String var0, Object[] var1, int var2) {
      return ask(var0, var1, null, var2);
   }

   public static int ask(String var0, Object[] var1, int[] var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void selectOrdinal(int var1) {
      if (this._values != null) {
         this._returnValue = this._values[var1];
      } else {
         this._returnValue = var1;
      }

      if (this._dfm.hasButtons() && var1 < this._dfm.getButtonManager().getFieldCount()) {
         this._dfm.getButtonField(var1).setFocus();
      }

      this.doPaint();
      this.close();
   }

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void setChoices(Object[] var1, int[] var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public Dialog(String var1, Object[] var2, int[] var3, int var4, Bitmap var5) {
      this(var1, var2, var3, var4, var5, 0);
   }

   public Dialog(String var1, Object[] var2, int[] var3, int var4, Bitmap var5, long var6) {
      super((Manager)(new Object()), var6);
      this.setup(var1, var2, var3, var4, var5);
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return this._dfm.getAccessibleChildAt(var1);
   }

   public static int ask(int var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void alert(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return super.stylusTap(var1, var2, var3, var4) ? true : this.trackwheelClick(var3, var4);
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      boolean var3 = super.trackwheelClick(var1, var2);
      if (!var3 && this.isStyle(1)) {
         this.select();
         return true;
      } else {
         return var3;
      }
   }

   @Override
   protected void onDisplay() {
      this._focusField = this.getLeafFieldWithFocus();
      super.onDisplay();
   }

   @Override
   protected void onUndisplay() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private void addChoice(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
