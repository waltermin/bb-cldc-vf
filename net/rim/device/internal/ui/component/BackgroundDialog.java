package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;

public class BackgroundDialog {
   private BackgroundDialog() {
   }

   public static String getInput(String var0, int var1) {
      return getInput(var0, 0, 1000000, var1);
   }

   public static String getInput(String var0, int var1, int var2, int var3) {
      Object var4 = new Object(var0, var1, var2, var3);
      Object var5 = ((BackgroundDialog$GetInputDialogDisplayRunnable)var4).runInCorrectProcess();
      if (((SimpleOKCancelInputDialog)var5).getCloseReason() == -1) {
         throw new Object();
      } else {
         return ((SimpleOKCancelInputDialog)var5).getText();
      }
   }

   public static int getChoice(String var0, Object[] var1, int var2) {
      return getChoice(var0, var1, var2, 50);
   }

   public static int getChoice(String var0, Object[] var1, int var2, int var3) {
      return getChoice(var0, var1, var2, null, var3);
   }

   public static int getChoice(String var0, Object[] var1, int var2, Bitmap var3, int var4) {
      Object var5 = new Object(var0, var1, var2, var3, var4);
      Object var6 = ((BackgroundDialog$GetChoiceDialogDisplayRunnable)var5).runInCorrectProcess();
      return ((SimpleChoiceDialog)var6).getCloseReason() == -1 ? -1 : ((SimpleChoiceDialog)var6).getSelectedIndex();
   }

   public static void showMessage(String var0) {
      showMessage(var0, 50, false);
   }

   public static void showMessageOnProxy(String var0) {
      showMessage(var0, 50, true);
   }

   public static void showMessage(String var0, int var1) {
      showMessage(var0, var1, false);
   }

   private static void showMessage(String var0, int var1, boolean var2) {
      Object var3 = new Object(var0, var1);
      if (var2) {
         ((BackgroundDialog$ShowMessageDialogDisplayRunnable)var3).forceRunInProxy();
      }

      ((BackgroundDialog$ShowMessageDialogDisplayRunnable)var3).runInCorrectProcess();
   }

   public static void show(PopupDialog var0) {
      Object var1 = new Object(var0);
      ((BackgroundDialog$ShowDialogDisplayRunnable)var1).runInCorrectProcess();
   }

   public static void showOnProxy(PopupDialog var0) {
      Object var1 = new Object(var0);
      ((BackgroundDialog$ShowDialogDisplayRunnable)var1).forceRunInProxy();
      ((BackgroundDialog$ShowDialogDisplayRunnable)var1).runInCorrectProcess();
   }
}
