package javax.microedition.lcdui;

import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class Gauge extends Item {
   private VerticalFieldManager _container;
   private int _maxValue;
   private int _value;
   private boolean _interactive;
   private String _label;
   private RichTextField _labelField;
   private Field _peer;
   private FieldChangeListener _changeListener;
   private int _currentIncrementalUpdatingAnimationIndex;
   private EncodedImage _animationImage;
   public static final int INDEFINITE;
   public static final int CONTINUOUS_IDLE;
   public static final int INCREMENTAL_IDLE;
   public static final int CONTINUOUS_RUNNING;
   public static final int INCREMENTAL_UPDATING;

   private EncodedImage getAnimationImage() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void updatePeer() {
      throw new RuntimeException("cod2jar: type check");
   }

   private boolean validateMaxValue(int var1) {
      return var1 > 0 || !this._interactive && var1 == -1;
   }

   private boolean validateValue(int var1) {
      return this._interactive || this._maxValue != -1 || var1 >= 0 && var1 <= 3;
   }

   public Gauge(String var1, boolean var2, int var3, int var4) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._changeListener = var1;
      this._peer.setChangeListener(null);
      this._peer.setChangeListener(var1);
      return this._container;
   }

   public void setValue(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getValue() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setMaxValue(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getMaxValue() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isInteractive() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
