package net.rim.device.api.i18n;

import java.util.Enumeration;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.LongEnumeration;
import net.rim.vm.WeakReference;

class ResourceBundle$1 implements GlobalEventListener {
   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 3542951112318790170L) {
         Enumeration var14 = ResourceBundle._table.elements();

         while (var14.hasMoreElements()) {
            Object var15 = var14.nextElement();
            ResourceBundleFamily var17 = (ResourceBundleFamily)((WeakReference)var15).get();
            if (var17 != null) {
               var17.onModuleLoad();
            }
         }

         RIMGlobalMessagePoster.postGlobalEvent(-7464003439710973532L);
         RIMGlobalMessagePoster.postGlobalEvent(7207871974803693937L);
      } else {
         if (var1 == -1270659756336956134L) {
            boolean var7 = false;
            Enumeration var8 = ResourceBundle._table.elements();

            while (var8.hasMoreElements()) {
               Object var9 = var8.nextElement();
               ResourceBundleFamily var10 = (ResourceBundleFamily)((WeakReference)var9).get();
               if (var10 == null) {
                  var7 = true;
                  break;
               }
            }

            if (var7) {
               LongEnumeration var16 = ResourceBundle._table.keys();

               while (var16.hasMoreElements()) {
                  long var18 = var16.nextElement();
                  Object var12 = ResourceBundle._table.get(var18);
                  ResourceBundleFamily var13 = (ResourceBundleFamily)((WeakReference)var12).get();
                  if (var13 == null) {
                     ResourceBundle._table.remove(var18);
                  }
               }
            }
         }
      }
   }
}
