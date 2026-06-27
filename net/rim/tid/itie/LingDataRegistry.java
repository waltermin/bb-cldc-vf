package net.rim.tid.itie;

import java.util.Vector;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntHashtable;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.spi.InputMethod;

public class LingDataRegistry {
   private IMContext _context;
   private int _idCounter;
   private IntHashtable _store = (IntHashtable)(new Object());
   private Vector _lingDataRegistryListeners;

   LingDataRegistry(IMContext var1) {
      this._context = var1;
   }

   public static int registerLingData(int var0, LinguisticData var1) {
      LingDataRegistry var2 = InputContext.getInstance(false).getLingDataRegistry();
      int var3 = var2.addData(var0, var1);
      if (var3 != -1) {
         IMManager var4 = InputContext.getInstance(false).getInputMethodsManager();
         if (var4 != null) {
            var4.forceLocaleReRegister(Locale.get(var0));
         }
      }

      return var3;
   }

   private synchronized boolean isValid(int var1, LinguisticData var2, LinguisticData[] var3) {
      if (var2.getType() >> 4 != 1 && !this.hasDataFor(var1, (byte)1)) {
         return false;
      }

      for (LinguisticData var4 = (LinguisticData)this._store.get(var1); var4 != null; var4 = var4._next) {
         if (var4.getType() == var2.getType()) {
            if (var4.getVersion() < var2.getVersion()) {
               var3[0] = var4;
               return true;
            }

            return false;
         }
      }

      return true;
   }

   private synchronized int addData(int var1, LinguisticData var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized int unloadLingData(int var1, String var2, int var3) {
      if (var1 != 0) {
         return this.unload(var1, var2, var3);
      }

      int var4 = 0;
      IntEnumeration var5 = this._store.keys();

      while (var5.hasMoreElements()) {
         var1 = var5.nextElement();
         var4 |= this.unload(var1, var2, var3);
      }

      return var4;
   }

   private int unload(int var1, String var2, int var3) {
      int var4 = 0;
      if (var2 == null && var3 == 0) {
         for (LinguisticData var8 = (LinguisticData)this._store.get(var1); var8 != null; var8 = var8._next) {
            var4 |= this.unload(var8, var1, var3);
         }

         this.update(var1, null);
         return var4;
      } else {
         LinguisticData var5 = (LinguisticData)this._store.get(var1);
         LinguisticData var6 = this.find(var5, var2, var3);
         var4 = this.unload(var6, var1, var3);
         this.removeFromStore(var1, var5, var6);
         return var4;
      }
   }

   private int unload(LinguisticData var1, int var2, int var3) {
      if (var1 != null && (var3 == 0 || var1.getType() == var3)) {
         if (var1.getCodFileName() != null) {
            int var4 = CodeModuleManager.getModuleHandle(var1.getCodFileName());
            int var5 = CodeModuleManager.deleteModuleEx(var4, true);
            if (var5 != 0 && var5 != 6) {
               return 2;
            }
         }

         return this.unloadFromIM(var1, var2);
      } else {
         return 4;
      }
   }

   private int unloadFromIM(LinguisticData var1, int var2) {
      if (this._context.getLocale().getCode() != var2) {
         return 1;
      }

      InputMethod var3 = this._context.getInputMethod();
      return var3 != null ? var3.unloadLinguisticData(var1.getID()) : 1;
   }

   private synchronized void removeFromStore(int var1, LinguisticData var2, LinguisticData var3) {
      if (var2 != null && var3 != null) {
         if (var3 == var2) {
            this.update(var1, var2._next);
         } else {
            while (var2._next != null) {
               if (var2._next == var3) {
                  var2._next = var3._next;
                  return;
               }
            }
         }
      }
   }

   private LinguisticData find(LinguisticData var1, String var2, int var3) {
      while (var1 != null) {
         if (var1.getType() == var3 && (var2 == null || var2.equals(var1.getName()))) {
            return var1;
         }

         var1 = var1._next;
      }

      return null;
   }

   synchronized LinguisticData getLingData(int var1) {
      return (LinguisticData)this._store.get(var1);
   }

   synchronized void update(int var1, LinguisticData var2) {
      if (var2 != null) {
         this._store.put(var1, var2);
         this.fireLingDataRegistration(var1, true);
      } else {
         this._store.remove(var1);
         this.fireLingDataRegistration(var1, false);
      }
   }

   public synchronized int[] getAvailableLocals() {
      int[] var1 = new int[this._store.size()];
      this._store.keysToArray(var1);
      return var1;
   }

   public synchronized String[] getLingDataNames(int var1) {
      String[] var2 = new String[0];

      for (LinguisticData var3 = (LinguisticData)this._store.get(var1); var3 != null; var3 = var3._next) {
         Arrays.add(var2, var3.getName());
      }

      return var2;
   }

   public boolean hasDataFor(int var1, byte var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean hasDataFor(int var1, byte var2, byte var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized void addLingDataRegistryListener(LingDataRegistryListener var1) {
      if (this._lingDataRegistryListeners == null) {
         this._lingDataRegistryListeners = (Vector)(new Object());
      }

      this._lingDataRegistryListeners.addElement(var1);
   }

   public synchronized void removeLingDataRegistryListener(LingDataRegistryListener var1) {
      if (this._lingDataRegistryListeners != null) {
         this._lingDataRegistryListeners.removeElement(var1);
      }
   }

   private synchronized void fireLingDataRegistration(int var1, boolean var2) {
      if (this._lingDataRegistryListeners != null) {
         int var3 = this._lingDataRegistryListeners.size();

         for (int var4 = 0; var4 < var3; var4++) {
            LingDataRegistryListener var5 = (LingDataRegistryListener)this._lingDataRegistryListeners.elementAt(var4);
            var5.registered(var1, var2);
         }
      }
   }
}
