package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.i18n.MessageFormatUtilities;

public class SimpleChoiceDialog extends PopupDialog implements FieldChangeListener {
   private DialogFieldManager _dfm = (DialogFieldManager)this.getDelegate();
   private int _selectedIndex;
   private Object[] _choices;
   private int _defaultChoice;

   public void select(int var1) {
      this._selectedIndex = var1;
      this.close(0);
   }

   public int getSelectedIndex() {
      return this._selectedIndex;
   }

   protected void select() {
      Field var1 = this.getLeafFieldWithFocus();
      if (var1 != null) {
         this.select(var1.getIndex());
      }
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 instanceof Object) {
         this.select();
      }
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      if (var1 && this._dfm.getCustomManager().getFieldCount() > 0) {
         this._dfm.getCustomField(Math.max(0, this._defaultChoice)).setFocus();
      }

      super.onUiEngineAttached(var1);
   }

   public SimpleChoiceDialog(String var1, Object[] var2, int var3, Bitmap var4, long var5) {
      this((RichTextField)(new Object(var1, 36028797086072832L)), var2, var3, var4, var5);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public SimpleChoiceDialog(RichTextField var1, Object[] var2, int var3, Bitmap var4, long var5) {
      super((Manager)(new Object()), var5);
      this._dfm.setMessage(var1);
      if (var2 != null) {
         this._choices = var2;
         int var7 = var2.length;

         for (int var8 = 0; var8 < var7; var8++) {
            Object var9 = new Object(var2[var8].toString(), 12884901888L);
            ((ButtonField)var9).setChangeListener(this);
            this._dfm.addCustomField((Field)var9);
         }
      }

      this._defaultChoice = var3;
      if (var4 != null) {
         this._dfm.setIcon((BitmapField)(new Object(var4, 32)));
      }
   }

   public SimpleChoiceDialog(String var1, Object[] var2, int var3, Bitmap var4) {
      this(var1, var2, var3, var4, 0);
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      super.trackwheelClick(var1, var2);
      this.select();
      return true;
   }

   public static boolean askYesNoQuestion(String var0) {
      return askYesNoQuestion(var0, null);
   }

   public static boolean askYesNoQuestion(String var0, String var1) {
      SimpleChoiceDialog var2 = createYesNoQuestionDialog(var0, var1, 0);
      var2.show();
      return var2.getCloseReason() == -1 ? false : var2.getSelectedIndex() == 0;
   }

   public static boolean askYesNoQuestionOnBackground(String var0) {
      return askYesNoQuestionOnBackground(var0, null);
   }

   public static boolean askYesNoQuestionOnBackground(String var0, String var1) {
      SimpleChoiceDialog var2 = createYesNoQuestionDialog(var0, var1, 134217728);
      BackgroundDialog.show(var2);
      return var2.getCloseReason() == -1 ? false : var2.getSelectedIndex() == 0;
   }

   private static SimpleChoiceDialog createYesNoQuestionDialog(String var0, String var1, long var2) {
      Object var4 = var1 == null ? new Object(var0, 45035996273704960L) : MessageFormatUtilities.getBoldArgumentField(var0, new String[]{var1});
      String[] var5 = CommonResource.getStringArray(10012);
      Bitmap var6 = Bitmap.getPredefinedBitmap(1);
      return new SimpleChoiceDialog((RichTextField)var4, var5, 1, var6, var2);
   }
}
