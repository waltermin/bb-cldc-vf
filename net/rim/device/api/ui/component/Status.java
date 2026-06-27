package net.rim.device.api.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.internal.ui.Image;
import net.rim.device.internal.ui.ImageBitmap;
import net.rim.device.internal.ui.component.ImageField;

public final class Status extends PopupScreen {
   private Status$PopScreenRunnable _popScreenRunnable = new Status$PopScreenRunnable(this);
   private int _id = -1;
   private long _shown;
   private boolean _allowDismiss;
   private boolean _block;
   private int _priority;
   private RichTextField _label;
   private DialogFieldManager _dfm;
   static final int DEFAULT_TIME;
   public static final long GLOBAL_STATUS;
   private static final int MIN_DISMISS_TIME;

   private Status(String var1, Bitmap var2, long var3, boolean var5, boolean var6, int var7) {
      this(var1, ImageBitmap.create(var2), var3, var5, var6, var7);
   }

   private Status(String var1, Image var2, long var3, boolean var5, boolean var6, int var7) {
      super((Manager)(new Object()), var3);
      this.setAcceptsInput(var5);
      this._dfm = (DialogFieldManager)this.getDelegate();
      this._label = new RichTextField(var1, 36028797018963968L);
      this._dfm.setMessage(this._label);
      this._allowDismiss = var5;
      this._block = var6;
      this._priority = var7;
      if (var2 != null) {
         Object var8 = new Object();
         ((ImageField)var8).setImage(var2);
         ((ImageField)var8).setPreferredSize(Display.getWidth() >> 2, Display.getHeight() >> 2);
         this._dfm.setIcon((ImageField)var8);
      }
   }

   private final void show0(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void dismiss() {
      if (this._allowDismiss && System.currentTimeMillis() - this._shown > 500) {
         if (this._id != -1) {
            Application.getApplication().cancelInvokeLater(this._id);
         }

         this._popScreenRunnable.run();
      }
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      if (super.keyChar(var1, var2, var3) || var1 != ' ' && var1 != 27) {
         return false;
      }

      this.dismiss();
      return true;
   }

   @Override
   protected final boolean keyDown(int var1, int var2) {
      return Keypad.key(var1) == 261 ? true : super.keyDown(var1, var2);
   }

   @Override
   protected final boolean trackwheelClick(int var1, int var2) {
      if (!super.trackwheelClick(var1, var2)) {
         this.dismiss();
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected final boolean stylusTap(int var1, int var2, int var3, int var4) {
      return super.stylusTap(var1, var2, var3, var4) ? true : this.trackwheelClick(var3, var4);
   }

   public static final void show(String var0) {
      show(var0, Bitmap.getPredefinedBitmap(0), 2000);
   }

   public static final void show(String var0, int var1) {
      show(var0, Bitmap.getPredefinedBitmap(0), var1);
   }

   public static final void show(String var0, Bitmap var1, int var2) {
      show(var0, var1, var2, 0, true, true, 50);
   }

   public static final void show(String var0, Bitmap var1, int var2, long var3, boolean var5, boolean var6, int var7) {
      Status var8 = new Status(var0, var1, var3, var5, var6, var7);
      var8.show0(var2);
   }

   public static final void show(String var0, Image var1, int var2, long var3, boolean var5, boolean var6, int var7) {
      Status var8 = new Status(var0, var1, var3, var5, var6, var7);
      var8.show0(var2);
   }
}
