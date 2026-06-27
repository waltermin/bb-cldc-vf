package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Graphics;

class KeywordFilteredField$ListFieldCallbackImpl implements ListFieldCallback {
   @Override
   public void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      Object var6 = var1;
      Object var7 = ((CollectionListField)var6).getElementAt(var3);
      if (var7 != null) {
         var2.drawText(var7.toString(), 0, var4);
      }
   }

   @Override
   public Object get(ListField var1, int var2) {
      return null;
   }

   @Override
   public int getPreferredWidth(ListField var1) {
      return 0;
   }

   @Override
   public int indexOfList(ListField var1, String var2, int var3) {
      return 0;
   }
}
