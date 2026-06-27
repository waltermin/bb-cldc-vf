package net.rim.device.api.ui.component;

import java.util.Vector;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.internal.i18n.CollatorImpl;

public class ObjectListField extends ListField implements DrawStyle, ListFieldCallback {
   private Vector _list = (Vector)(new Object());
   private int _prefWidth;
   private CollatorImpl _collator = (CollatorImpl)(new Object());
   private static String EMPTY_ROW;
   private static final int ATTRIBUTES_MASK;

   public void set(Object[] var1) {
      if (var1 == null) {
         var1 = new Object[0];
      }

      this._list.setSize(var1.length);

      for (int var2 = var1.length - 1; var2 >= 0; var2--) {
         this._list.setElementAt(var1[var2], var2);
      }

      this.setSize(var1.length, 0);
      this.fieldChangeNotify(Integer.MIN_VALUE);
   }

   public Object get(int var1) {
      return this._list.elementAt(var1);
   }

   public void set(int var1, Object var2) {
      if (var2 == null) {
         throw new Object();
      }

      this._list.setElementAt(var2, var1);
      this.fieldChangeNotify(Integer.MIN_VALUE);
      this.invalidate(var1);
   }

   public void insert(int var1, Object var2) {
      if (var2 == null) {
         throw new Object();
      }

      this._list.insertElementAt(var2, var1);
      super.insert(var1);
   }

   @Override
   public int indexOfList(ListField var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public int getPreferredWidth(ListField var1) {
      return this._prefWidth;
   }

   @Override
   public Object get(ListField var1, int var2) {
      return this._list.elementAt(var2);
   }

   @Override
   public void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      String var6 = this._list.elementAt(var3).toString();
      int var7 = (int)(this.getStyle() & 71);
      int var8 = var1.adjustRowHeight(var2.getFont(), var3, var6);
      var2.drawText(var6, 0, Integer.MAX_VALUE, 0, var4 + var8, var7, var5);
   }

   @Override
   public void insert(int var1) {
      this.insert(var1, EMPTY_ROW);
   }

   public ObjectListField(long var1) {
      super(0, var1);
      this.setCallback(this);
   }

   @Override
   protected void layout(int var1, int var2) {
      if (this.isStyle(1152921504606846976L)) {
         Font var3 = this.getFont();
         this._prefWidth = 0;
         int var4 = this._list.size();

         for (int var5 = var4 - 1; var5 >= 0; var5--) {
            int var6 = var3.getBounds(this._list.elementAt(var5).toString());
            if (this._prefWidth < var6) {
               this._prefWidth = var6;
            }
         }
      } else {
         this._prefWidth = var1;
      }

      super.layout(var1, var2);
   }

   @Override
   public void delete(int var1) {
      this._list.removeElementAt(var1);
      super.delete(var1);
   }

   public ObjectListField() {
      this(0);
   }
}
