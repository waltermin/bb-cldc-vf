package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.util.SimpleSortingVector;

public class Item {
   private Field _peer;
   private int _layoutMode;
   int _preferredWidth = -1;
   int _preferredHeight = -1;
   private Screen _owner;
   private ItemCommandListener _itemCommandListener;
   private ItemCommands _commands = new ItemCommands();
   public static final int LAYOUT_DEFAULT;
   public static final int LAYOUT_LEFT;
   public static final int LAYOUT_RIGHT;
   public static final int LAYOUT_CENTER;
   public static final int LAYOUT_TOP;
   public static final int LAYOUT_BOTTOM;
   public static final int LAYOUT_VCENTER;
   public static final int LAYOUT_NEWLINE_BEFORE;
   public static final int LAYOUT_NEWLINE_AFTER;
   public static final int LAYOUT_SHRINK;
   public static final int LAYOUT_EXPAND;
   public static final int LAYOUT_VSHRINK;
   public static final int LAYOUT_VEXPAND;
   public static final int LAYOUT_2;
   public static final int PLAIN;
   public static final int HYPERLINK;
   public static final int BUTTON;
   static final int VALID_LAYOUT;
   static final int FIELD_LAYOUT;
   static final int DRAW_STYLE_LAYOUT;
   static final int RICH_TEXT_FIELD_LAYOUT;

   static final long getFieldLayoutStyle(int var0, int var1) {
      long var2;
      int var5;
      int var6;
      int var7;
      var2 = 0;
      int var4 = var0 & 15;
      var5 = var0 & 240;
      var6 = var0 & 3840;
      var7 = var0 & 61440;
      label51:
      switch (var4) {
         case 0:
            break;
         case 1:
            switch (var1) {
               case 0:
                  var2 |= 4294967296L;
                  break label51;
               case 1:
               default:
                  var2 |= 6;
                  break label51;
               case 2:
                  var2 |= 0;
                  break label51;
            }
         case 2:
            switch (var1) {
               case 0:
                  var2 |= 8589934592L;
                  break label51;
               case 1:
               default:
                  var2 |= 5;
                  break label51;
               case 2:
                  var2 |= 524288;
                  break label51;
            }
         case 3:
         default:
            switch (var1) {
               case 0:
                  var2 |= 12884901888L;
                  break;
               case 1:
               default:
                  var2 |= 4;
                  break;
               case 2:
                  var2 |= 262144;
            }
      }

      switch (var5) {
         case 16:
            var2 |= var1 == 1 ? 48 : 17179869184L;
            break;
         case 32:
            var2 |= var1 == 1 ? 40 : 34359738368L;
            break;
         case 48:
            var2 |= var1 == 1 ? 32 : 51539607552L;
      }

      if (var6 == 2048) {
         var2 |= 1152921504606846976L;
      }

      if (var7 == 8192) {
         var2 |= 2305843009213693952L;
      }

      return var2;
   }

   final Screen getOwner() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void setOwner(Screen var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void setPeer(Field var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   Field getPeer() {
      throw new RuntimeException("cod2jar: exception table");
   }

   Item() {
   }

   public void setLabel(String var1) {
   }

   public String getLabel() {
      return null;
   }

   public int getLayout() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setLayout(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void addCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setItemCommandListener(ItemCommandListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   ItemCommandListener getItemCommandListener() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getPreferredWidth() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getPreferredHeight() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setPreferredSize(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getMinimumWidth() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getMinimumHeight() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setDefaultCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void notifyStateChanged() {
      throw new RuntimeException("cod2jar: exception table");
   }

   SimpleSortingVector getCommands() {
      throw new RuntimeException("cod2jar: exception table");
   }

   Field addToForm(FieldChangeListener var1) {
      throw null;
   }
}
