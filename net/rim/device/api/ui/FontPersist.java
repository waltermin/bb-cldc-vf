package net.rim.device.api.ui;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMPersistentStore;
import net.rim.device.api.util.Persistable;

class FontPersist implements Persistable {
   public String _fontFamily;
   public int _fontHeight;
   public int _fontStyle;
   public int _fontEffects;
   public int _fontAntialiasing;
   public int[] _fontTransform;
   private static final long DEFAULT_FONT_KEY;

   public FontPersist(Font var1) {
      this._fontFamily = var1.getFontFamily().getName();
      this._fontHeight = var1.getHeight(4194306);
      this._fontStyle = var1.getStyle();
      this._fontEffects = var1.getEffects();
      this._fontAntialiasing = var1.getAntialiasMode();
      this._fontTransform = new int[6];
      System.arraycopy(var1.getTransform(), 0, this._fontTransform, 0, 6);
   }

   static void setDefaultFont(Font var0) {
      FontPersist var1 = new FontPersist(var0);
      PersistentObject var2 = RIMPersistentStore.getPersistentObject(3057605627993471691L);
      var2.setContents(var1, 51);
      var2.commit();
   }

   static Font getDefaultFont() {
      PersistentObject var0 = RIMPersistentStore.getPersistentObject(3057605627993471691L);
      FontPersist var1 = (FontPersist)var0.getContents();
      if (var1 != null) {
         String[] var2 = FontRegistry.getFontFamilies();
         if (var2 != null && var2.length > 0 && var1._fontFamily != null) {
            for (int var3 = 0; var3 < var2.length; var3++) {
               if (var1._fontFamily.equals(var2[var3])) {
                  FontFamily var4 = FontRegistry.get(var2[var3]);
                  return var4.getFont(
                     var1._fontStyle,
                     var1._fontHeight,
                     4194306,
                     var1._fontAntialiasing,
                     var1._fontEffects,
                     var1._fontTransform[0],
                     var1._fontTransform[1],
                     var1._fontTransform[2],
                     var1._fontTransform[3],
                     var1._fontTransform[4],
                     var1._fontTransform[5]
                  );
               }
            }
         }
      }

      return null;
   }
}
