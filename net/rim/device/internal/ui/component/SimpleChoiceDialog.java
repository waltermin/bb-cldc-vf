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

   public void select(int index) {
      this._selectedIndex = index;
      this.close(0);
   }

   public int getSelectedIndex() {
      return this._selectedIndex;
   }

   protected void select() {
      Field field = this.getLeafFieldWithFocus();
      if (field != null) {
         this.select(field.getIndex());
      }
   }

   @Override
   public void fieldChanged(Field field, int context) {
      if (field instanceof Object) {
         this.select();
      }
   }

   @Override
   protected void onUiEngineAttached(boolean attached) {
      if (attached && this._dfm.getCustomManager().getFieldCount() > 0) {
         this._dfm.getCustomField(Math.max(0, this._defaultChoice)).setFocus();
      }

      super.onUiEngineAttached(attached);
   }

   public SimpleChoiceDialog(String message, Object[] choices, int defaultChoice, Bitmap bitmap, long style) {
      this((RichTextField)(new Object(message, 36028797086072832L)), choices, defaultChoice, bitmap, style);
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public SimpleChoiceDialog(RichTextField message, Object[] choices, int defaultChoice, Bitmap bitmap, long style) {
      super((Manager)(new Object()), style);
      this._dfm.setMessage(message);
      if (choices != null) {
         this._choices = choices;
         int numChoices = choices.length;

         for (int i = 0; i < numChoices; i++) {
            ButtonField button = (ButtonField)(new Object(choices[i].toString(), 12884901888L));
            button.setChangeListener(this);
            this._dfm.addCustomField(button);
         }
      }

      this._defaultChoice = defaultChoice;
      if (bitmap != null) {
         this._dfm.setIcon((BitmapField)(new Object(bitmap, 32)));
      }
   }

   public SimpleChoiceDialog(String message, Object[] choices, int defaultChoice, Bitmap bitmap) {
      this(message, choices, defaultChoice, bitmap, 0);
   }

   @Override
   protected boolean trackwheelClick(int status, int time) {
      super.trackwheelClick(status, time);
      this.select();
      return true;
   }

   public static boolean askYesNoQuestion(String question) {
      return askYesNoQuestion(question, null);
   }

   public static boolean askYesNoQuestion(String question, String boldArgument) {
      SimpleChoiceDialog dialog = createYesNoQuestionDialog(question, boldArgument, 0);
      dialog.show();
      return dialog.getCloseReason() == -1 ? false : dialog.getSelectedIndex() == 0;
   }

   public static boolean askYesNoQuestionOnBackground(String question) {
      return askYesNoQuestionOnBackground(question, null);
   }

   public static boolean askYesNoQuestionOnBackground(String question, String boldArgument) {
      SimpleChoiceDialog dialog = createYesNoQuestionDialog(question, boldArgument, 134217728);
      BackgroundDialog.show(dialog);
      return dialog.getCloseReason() == -1 ? false : dialog.getSelectedIndex() == 0;
   }

   private static SimpleChoiceDialog createYesNoQuestionDialog(String question, String boldArgument, long style) {
      RichTextField messageField = (RichTextField)(boldArgument == null
         ? new Object(question, 45035996273704960L)
         : MessageFormatUtilities.getBoldArgumentField(question, new String[]{boldArgument}));
      String[] yesNo = CommonResource.getStringArray(10012);
      Bitmap bitmap = Bitmap.getPredefinedBitmap(1);
      return new SimpleChoiceDialog(messageField, yesNo, 1, bitmap, style);
   }
}
