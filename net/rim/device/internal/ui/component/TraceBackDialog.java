package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.vm.TraceBack;

public final class TraceBackDialog extends Dialog {
   private Object _backTrace;
   private UiApplication _app;

   private TraceBackDialog(UiApplication var1, String var2, Object var3) {
      super(var2, CommonResource.getStringArray(10004), null, 0, Bitmap.getPredefinedBitmap(2), 0);
      this._app = var1;
      this._backTrace = var3;
   }

   @Override
   protected final boolean openProductionBackdoor(int var1) {
      switch (var1) {
         case 1145197894:
            return super.openProductionBackdoor(var1);
         case 1145197895:
         default:
            Object var2 = new Object();
            TraceBackDialog$TraceBackScreen var3 = new TraceBackDialog$TraceBackScreen(this);
            int var4 = 0;

            while (true) {
               String var5 = TraceBack.getMessage(this._backTrace, var4);
               if (var5 == null) {
                  Object var6 = new Object(((StringBuffer)var2).toString());
                  var3.add((Field)var6);
                  this._app.pushGlobalScreen(var3, 10, 2);
                  return true;
               }

               ((StringBuffer)var2).append(var5);
               ((StringBuffer)var2).append('\n');
               var4++;
            }
      }
   }

   public static final void show(UiApplication var0, String var1, Object var2) {
      TraceBackDialog var3 = new TraceBackDialog(var0, var1, var2);
      var0.pushGlobalScreen(var3, 11, 2);
   }
}
