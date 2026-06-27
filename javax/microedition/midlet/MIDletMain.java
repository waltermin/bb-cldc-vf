package javax.microedition.midlet;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.internal.io.PushRegistryHelper;
import net.rim.device.internal.lcdui.Lcdui;
import net.rim.device.internal.lcdui.MIDletInterface;
import net.rim.device.internal.resource.CommonResource;
import net.rim.device.internal.ui.MIDletApplication;
import net.rim.vm.Process;

class MIDletMain extends MIDletApplication implements MIDletInterface, CommonResource {
   private MIDlet _midlet;
   private String _moduleClassName;
   private boolean _active;
   private boolean _foregroundable = false;
   private Hashtable _workerThreadMap = (Hashtable)(new Object());
   private Vector _alarmThreadCache = (Vector)(new Object());
   private static final long MIDLETSUITEMAP_ID;
   private static ResourceBundle _resources;
   private static final long MIDLET_ID_MASK;
   private static final Object dummy;

   private static MIDletMain getInstance(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void removeInstance(String var0, MIDletMain var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void main(String[] var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private MIDletMain(String var1) {
      this.enableKeyUpEvents(true);
      this._moduleClassName = var1;
   }

   @Override
   public void exit() {
      if (!this.keepAliveProcess()) {
         removeInstance(this._moduleClassName, this);
         Process.currentProcess().destroy();
      } else {
         this.pokeWorkerThreads();
         this.requestBackground();
         this._foregroundable = false;
         this._active = false;
         this._midlet = null;
      }
   }

   @Override
   public void registerAlarm(Runnable var1) {
      this._alarmThreadCache.addElement(new Object(var1));
   }

   private boolean keepAliveProcess() {
      if (!this._workerThreadMap.isEmpty()) {
         return true;
      }

      for (int var1 = this._alarmThreadCache.size() - 1; var1 >= 0; var1--) {
         Object var2 = this._alarmThreadCache.elementAt(var1);
         if (var2 != null) {
            if (((WeakReference)var2).get() != null) {
               return true;
            }

            this._alarmThreadCache.removeElement(var2);
         }
      }

      return false;
   }

   @Override
   public boolean acceptsForeground() {
      return this._foregroundable;
   }

   @Override
   public void setForegroundable(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void bringToForeground() {
      this._foregroundable = true;
      ApplicationManager.getApplicationManager().requestForeground(this.getProcessId());
   }

   private static void initializePushRegistry(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void addPushRegistry(String var1, String var2) {
      PushRegistryHelper var3 = PushRegistryHelper.getInstance();
      var3._weakreferencemap.put(var2, new Object(this));
      MIDletMain$MIDletPushRegistryWorkerThread var4 = new MIDletMain$MIDletPushRegistryWorkerThread(this, var2);
      this._workerThreadMap.put(var2, var4);
      if (this.isHandlingEvents()) {
         var4.pause();
      }

      var4.start();
      if (!this.isHandlingEvents()) {
         this._foregroundable = false;
         this.enterEventDispatcher();
      }
   }

   @Override
   public void removePushRegistry(String var1, String var2) {
      MIDletMain$MIDletPushRegistryWorkerThread var3 = (MIDletMain$MIDletPushRegistryWorkerThread)this._workerThreadMap.remove(var2);
      var3.shutdown();
      PushRegistryHelper var4 = PushRegistryHelper.getInstance();
      var4._weakreferencemap.remove(var2);
   }

   @Override
   public void shutdownWorkerThread(String var1) {
      PushRegistryHelper var2 = PushRegistryHelper.getInstance();
      var2._connectionMap.remove(var1);
      var2._filterMap.remove(var1);
      MIDletMain$MIDletPushRegistryWorkerThread var3 = (MIDletMain$MIDletPushRegistryWorkerThread)this._workerThreadMap.get(var1);
      if (var3 != null) {
         var3.shutdown();
      }
   }

   @Override
   public void activate() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void deactivate() {
      this.pokeWorkerThreads();
      if (this._active) {
         this._midlet.pauseApp();
         this._active = false;
      }
   }

   @Override
   public void updateScreen() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected void dispatchInvokeLater(Runnable var1, Object var2, int var3) {
      if (var3 == 1) {
         Lcdui.setInvokeLaterCallback(var1, var2);
      } else {
         super.dispatchInvokeLater(var1, var2, var3);
      }
   }

   @Override
   public void destroyApp(boolean var1) {
      this._midlet.destroyApp(var1);
      this.pokeWorkerThreads();
   }

   private void pokeWorkerThreads() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
