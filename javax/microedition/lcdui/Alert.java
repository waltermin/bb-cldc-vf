package javax.microedition.lcdui;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.util.SimpleSortingVector;

public class Alert extends Screen {
   private DialogFieldManager _dfm;
   private RichTextField _title;
   private BitmapField _image;
   private Image _midpImage;
   private RichTextField _text;
   private AlertType _alertType;
   private int _timeout = this.getDefaultTimeout();
   private Display _display;
   private Displayable _nextDisplayable;
   private Gauge _indicator;
   private CommandListener _commandListener;
   private SimpleSortingVector _commands;
   private VerticalFieldManager _buttonContainer;
   private static int _timerId;
   public static final int FOREVER;
   public static final Command DISMISS_COMMAND;

   public Alert(String var1) {
      super(null);
      this.init(var1, null, null, null);
   }

   public Alert(String var1, String var2, Image var3, AlertType var4) {
      super(null);
      this.init(var1, var2, var3, var4);
   }

   private void init(String var1, String var2, Image var3, AlertType var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void show(Display var1, Displayable var2) {
      this._display = var1;
      this._nextDisplayable = var2;
      this.getPeer().doLayout();
      this.updateModalState();
      if (this._timeout > 0) {
         _timerId = Application.getApplication().invokeLater(new Alert$AlertRunnable(this), this._timeout, false);
      }
   }

   void dismiss() {
      if (_timerId != -1) {
         Application.getApplication().cancelInvokeLater(_timerId);
         _timerId = -1;
      }

      if (this._commandListener != null) {
         Command var1;
         if (this._buttonContainer.getFieldCount() == 0) {
            var1 = DISMISS_COMMAND;
         } else {
            var1 = (Command)this._buttonContainer.getFieldWithFocus().getCookie();
         }

         this._commandListener.commandAction(var1, this);
      }

      if (this._nextDisplayable != null && this._display.getCurrent() == this) {
         this._display.setCurrent(this._nextDisplayable);
         this._nextDisplayable = null;
      }
   }

   public int getDefaultTimeout() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getTimeout() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setTimeout(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public AlertType getType() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setType(AlertType var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getString() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setString(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Image getImage() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setImage(Image var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setIndicator(Gauge var1) {
      if (var1 == null
         || !var1.isInteractive()
            && var1.getOwner() == null
            && var1.getCommands().size() == 0
            && var1.getItemCommandListener() == null
            && var1.getLabel() == null
            && var1._preferredHeight == -1
            && var1._preferredWidth == -1
            && var1.getLayout() == 0) {
         if (this._indicator != null) {
            this._dfm.deleteCustomField(this._indicator.getPeer());
         }

         if (var1 != null) {
            this._dfm.addCustomField(var1.getPeer());
            var1.setOwner(this);
         }

         this._indicator = var1;
      } else {
         throw new Object();
      }
   }

   public Gauge getIndicator() {
      return this._indicator;
   }

   @Override
   public void addCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void removeCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setCommandListener(CommandListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getTitle() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setTitle(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void updateModalState() {
      Manager var1 = this._dfm.getCustomManager();
      boolean var2 = var1.getVirtualHeight() > var1.getHeight() || this._buttonContainer.getFieldCount() > 1;
      if (!var2 && this._text != null) {
         var2 = this._text.getHeight() > this._dfm.getHeight();
      }

      if (var2) {
         this.setTimeout(-2);
      }
   }
}
