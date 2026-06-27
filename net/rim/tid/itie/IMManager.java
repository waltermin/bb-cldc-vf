package net.rim.tid.itie;

import java.util.Vector;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.util.Arrays;
import net.rim.tid.awt.im.repository.CustomWordsRepository;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputMethodDescriptor;

public final class IMManager {
   private Vector _descriptors;
   private Vector _imethods;
   private long _availableIMs;
   private long _latestIMID;

   IMManager() {
   }

   public final InputMethod getInputMethod(Locale var1) {
      return this.getInputMethod(var1, null);
   }

   private final InputMethodDescriptor getDescriptorForLocale(Locale var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized InputMethod getInputMethod(Locale var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void forceLocaleReRegister(Locale var1) {
      Locale[] var2 = Locale.getAvailableInputLocales();
      if (Arrays.getIndex(var2, var1) == -1) {
         int var3 = this._descriptors.size();

         for (int var4 = 0; var4 < var3; var4++) {
            InputMethodDescriptor var5 = (InputMethodDescriptor)this._descriptors.elementAt(var4);
            var2 = var5.getDisplayLocales();
            if (var2 != null) {
               for (int var6 = 0; var6 < var2.length; var6++) {
                  Locale.addInputLocaleInternal(var2[var6]);
               }
            }
         }
      }
   }

   public final synchronized boolean addIMDescriptor(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void dispose() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final long getAvailableInputMethodIDs() {
      return this._availableIMs;
   }

   public final long getLatestRequestedInputMethodID() {
      return this._latestIMID;
   }

   public final long getInputMethodIDForLocale(Locale var1) {
      InputMethodDescriptor var2 = this.getDescriptorForLocale(var1);
      return var2 == null ? 0 : var2.getInputMethodID();
   }

   public final CustomWordsRepository getRepository(int var1) {
      switch (var1) {
         case 1:
            return this.findAddressBookRepository();
         case 6:
            return this.findYOMIAddressBookRepository();
         default:
            return null;
      }
   }

   private final CustomWordsRepository findAddressBookRepository() {
      int var1 = this._imethods.size();

      for (int var2 = 0; var2 < var1; var2++) {
         InputMethodDescriptor var3 = (InputMethodDescriptor)this._descriptors.elementAt(var2);
         long var4 = var3.getInputMethodID();
         if (var4 == 4096 || var4 == 8192) {
            return var3.getRepository(1);
         }
      }

      return null;
   }

   private final CustomWordsRepository findYOMIAddressBookRepository() {
      int var1 = this._imethods.size();

      for (int var2 = 0; var2 < var1; var2++) {
         InputMethodDescriptor var3 = (InputMethodDescriptor)this._descriptors.elementAt(var2);
         if (var3.getInputMethodID() == 512) {
            return var3.getRepository(6);
         }
      }

      return null;
   }
}
