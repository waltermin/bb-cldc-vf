package javax.microedition.lcdui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.util.MathUtilities;

public class Gauge extends Item {
   private VerticalFieldManager _container = (VerticalFieldManager)(new Object(1152921504606846976L));
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

   private boolean validateMaxValue(int maxValue) {
      return maxValue > 0 || !this._interactive && maxValue == -1;
   }

   private boolean validateValue(int value) {
      return this._interactive || this._maxValue != -1 || value >= 0 && value <= 3;
   }

   public Gauge(String label, boolean interactive, int maxValue, int initialValue) {
      synchronized (Application.getEventLock()) {
         this._label = label;
         this._interactive = interactive;
         if (!this.validateMaxValue(maxValue)) {
            throw new Object();
         }

         this._maxValue = maxValue;
         if (maxValue != -1) {
            initialValue = MathUtilities.clamp(0, initialValue, maxValue);
         }

         if (!this.validateValue(initialValue)) {
            throw new Object();
         }

         this._value = initialValue;
         this.updatePeer();
         this._container.setCookie(this);
         this.setPeer(this._container);
      }
   }

   @Override
   Field addToForm(FieldChangeListener changeListener) {
      this._changeListener = changeListener;
      this._peer.setChangeListener(null);
      this._peer.setChangeListener(changeListener);
      return this._container;
   }

   public void setValue(int value) {
      synchronized (Application.getEventLock()) {
         if (!this.validateValue(value)) {
            throw new Object();
         }

         if (this._maxValue != -1) {
            this._value = MathUtilities.clamp(0, value, this._maxValue);
         } else {
            this._value = value;
         }

         this.updatePeer();
      }
   }

   public int getValue() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void setMaxValue(int maxValue) {
      synchronized (Application.getEventLock()) {
         if (!this.validateMaxValue(maxValue)) {
            throw new Object();
         }

         int oldMaxValue = this.getMaxValue();
         this._maxValue = maxValue;
         if (oldMaxValue == -1) {
            if (maxValue > -1) {
               this._value = 0;
            }
         } else if (maxValue == -1) {
            this._value = 0;
         }

         if (this._maxValue != -1) {
            this._value = MathUtilities.clamp(0, this._value, this._maxValue);
         }

         this.updatePeer();
      }
   }

   public int getMaxValue() {
      synchronized (Application.getEventLock()) {
         return this._maxValue;
      }
   }

   public boolean isInteractive() {
      synchronized (Application.getEventLock()) {
         return this._interactive;
      }
   }

   @Override
   public void setLabel(String label) {
      synchronized (Application.getEventLock()) {
         this._label = label;
         this.updatePeer();
      }
   }

   @Override
   public String getLabel() {
      synchronized (Application.getEventLock()) {
         return this._label;
      }
   }
}
