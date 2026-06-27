package net.rim.tid.awt.im;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontRegistry;
import net.rim.device.api.ui.InvokableAction;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.FocusEvent;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.awt.im.repository.CustomDictionary;
import net.rim.tid.awt.im.repository.CustomWordsRepository;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputModeChangeListener;
import net.rim.tid.im.spellcheck.SpellCheckUtilities;
import net.rim.tid.itie.EventHandler;
import net.rim.tid.itie.IComponent;
import net.rim.tid.itie.IMManager;
import net.rim.tid.itie.ISecureInputMethodBuffer;
import net.rim.tid.itie.LingDataRegistry;
import net.rim.tid.text.AttributedString;
import net.rim.vm.WeakReference;

public class InputContext {
   protected InputMethod _inputMethod;
   protected InputMethod _previousIM;
   protected int _lastIMEventResult;
   protected boolean _isComponentAllowed = true;
   protected boolean _isIMSwitcherEnabled = true;
   protected IMManager _manager;
   protected Locale _lastUsedLocale;
   private Locale _cachedLocale;
   private boolean _iInitialised;
   private Thread _initializingThread;
   private int _focusGainedAppId = -1;
   private InvokableActionProducer _invokableActionProducer;
   private boolean _imSwitcherAllowed;
   private static final long REGISTRY_NAME;
   protected static WeakReference _component;
   private static InputContext _context;

   protected InputContext() {
   }

   protected synchronized boolean selectInputMethod(Locale var1, String var2, int var3) {
      boolean var4 = false;
      if (var1 != null && (this.isUnicodeInputAllowed() || this._inputMethod == null || var3 == 2 || var3 == 1)) {
         this.endComposition();
         Locale var5;
         if (this._inputMethod != null) {
            var5 = this._inputMethod.getLocale();
            if (this._inputMethod.setLocale(var1, var3)) {
               var4 = true;
            } else {
               this.enableClientWindowNotification(this._inputMethod, false);
               this._inputMethod.hideWindows();
               this._inputMethod.deactivate(false);
            }
         } else {
            var5 = Locale.getDefaultInputForSystem();
         }

         if (!SpellCheckUtilities.isSpellCheckVariant(var5)) {
            this._lastUsedLocale = var5;
         }

         if (!var4) {
            InputMethod var6 = this._manager.getInputMethod(var1, var2);
            if (var6 != null) {
               this._previousIM = this._inputMethod;
               var4 = this.changeIM(var1, var6);
               if (!var4) {
                  this._previousIM = null;
               } else {
                  this._inputMethod = var6;
                  int var7 = this.getHanMaskForLocale(this.getLocale());
                  if (var7 != 0) {
                     Font var8 = FontRegistry.getDefaultFont();
                     Font var9 = this.getFontForHanMask(var8, var7);
                     if (var8 != var9) {
                        FontRegistry.setDefaultFont(var9);
                     }

                     Font var10 = Font.getDefault();
                     if (var9 != var10 && (var10.getStyle() & var7) != var7) {
                        Font.setDefaultFont(this.getFontForHanMask(var10, var7));
                     }
                  }

                  this.updateHanMask(this.getInputComponent(), var1);
               }
            }
         }

         return var4;
      } else {
         return var4;
      }
   }

   public boolean selectInputMethod(Locale var1) {
      return this.selectInputMethod(var1, null, 0);
   }

   public void releaseComponent() {
      this.endComposition();
      _component.set(null);
   }

   public IComponent getInputComponent() {
      return (IComponent)_component.get();
   }

   public long getAvailableInputMethods() {
      return this._manager.getAvailableInputMethodIDs();
   }

   public long getActiveInputMethodID() {
      return this._manager.getLatestRequestedInputMethodID();
   }

   public boolean isAutoPeriodOn() {
      switch (Locale.getDefaultInputForSystem().getCode() & -65536) {
         case 1784741887:
            return true;
         case 1784741888:
         default:
            return false;
      }
   }

   public static InputContext getInstance(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isInitialised() {
      return this._iInitialised;
   }

   public static InputContext getInstance() {
      return getInstance(true);
   }

   protected boolean changeIM(Locale var1, InputMethod var2) {
      return false;
   }

   protected void notifyClientWindowChange() {
   }

   public void addSecureBuffer(ISecureInputMethodBuffer var1) {
   }

   public void enableClientWindowNotification(InputMethod var1, boolean var2) {
   }

   public boolean addIMDescriptor(String var1, String var2) {
      return this.addIMDescriptor(var1, var2, false);
   }

   public boolean addIMDescriptor(String var1, String var2, boolean var3) {
      return false;
   }

   public Locale getLocale() {
      return this._inputMethod == null ? Locale.getDefaultInputForSystem() : this._inputMethod.getLocale();
   }

   public Locale getLastUsedLocale() {
      return this._lastUsedLocale;
   }

   public void setCompositionEnabled(boolean var1) {
      if (this._inputMethod != null) {
         this._inputMethod.setCompositionEnabled(var1);
      }
   }

   public boolean isCompositionEnabled() {
      return this._inputMethod != null ? this._inputMethod.isCompositionEnabled() : false;
   }

   public void reconvert() {
      if (this._inputMethod != null) {
         this._inputMethod.reconvert();
      }
   }

   public synchronized void dispatchEvent(Event var1) {
      switch (var1.getID()) {
         case 100:
         case 101:
            this.notifyClientWindowChange();
            return;
         case 1004:
            this.focusGained((FocusEvent)var1);
            return;
         case 1005:
            this.focusLost((FocusEvent)var1);
            return;
         default:
            if (this._inputMethod != null) {
               this._inputMethod.dispatchEvent(var1);
            }
      }
   }

   public synchronized void endComposition() {
      if (this._lastIMEventResult == 1) {
         this._lastIMEventResult = 0;
         if (this._previousIM != null) {
            this._previousIM.endComposition();
         }

         this.cleanComponent((IComponent)_component.get());
      }

      if (this._inputMethod != null) {
         this._inputMethod.endComposition();
      }
   }

   private void cleanComponent(IComponent var1) {
      if (var1 != null) {
         InputMethodEvent var2 = new InputMethodEvent(var1, 1103, 0, (AttributedString)(new Object()), 0, 0, 0, null, null);
         var1.inputMethodTextChanged(var2);
      }
   }

   private synchronized void reset(int var1) {
      if (this._inputMethod != null) {
         this._inputMethod.reset(var1);
         if (var1 == 1) {
            this.cleanComponent((IComponent)_component.get());
         }
      }
   }

   public void dispose() {
      if (this._inputMethod != null) {
         this._inputMethod.endComposition();
         this._inputMethod.hideWindows();
         this._inputMethod.deactivate(true);
      }

      this._inputMethod = null;
   }

   public synchronized void notifyAppSwitch(boolean var1, int var2) {
      IComponent var3 = (IComponent)_component.get();
      if (var3 == null) {
         if (var1) {
            this.reset(0);
         }
      } else {
         EventHandler var4 = EventHandler.getInstance();
         if (var1) {
            IComponent var8 = null;
            Field var9 = null;
            if (UiApplication.getUiApplication() != null) {
               Screen var7 = UiApplication.getUiApplication().getActiveScreen();
               if (var7 != null) {
                  var9 = var7.getLeafFieldWithFocus();
                  if (var9 == var3) {
                     var8 = var3;
                  } else if (var9 != null && var3 != null && var9.getInputMethodRequests() == var3.getInputMethodRequests()) {
                     var8 = var3;
                  }
               }
            }

            var4.focusGained(var8, (int)System.currentTimeMillis(), var2);
            return;
         }

         var3.actionPerformed(141, null);
         var4.actionPerformed(1, null);
         UiApplication var5 = UiApplication.getUiApplication();
         if (var5 == null) {
            return;
         }

         Screen var6 = var5.getActiveScreen();
         if (var6 instanceof Object) {
            this.endComposition();
            if (this._inputMethod != null) {
               this._inputMethod.hideWindows();
            }

            var4.focusLost(var3, (int)System.currentTimeMillis(), var2);
            return;
         }
      }
   }

   public synchronized Object getInputMethodControlObject() {
      if (this._inputMethod == null) {
         if (this._iInitialised) {
            this.selectInputMethod(Locale.getDefaultInputForSystem());
         } else {
            getInstance();
         }

         return this._inputMethod != null ? this._inputMethod.getControlObject() : null;
      } else {
         return this._inputMethod.getControlObject();
      }
   }

   public void enableLookup(boolean var1) {
   }

   public synchronized void setIMSwitchEnabled(boolean var1) {
      this._isIMSwitcherEnabled = var1;
   }

   public synchronized boolean isIMSwitchAllowed() {
      return this._imSwitcherAllowed;
   }

   private boolean isIMSwitchAllowedHelper() {
      return !this._isIMSwitcherEnabled ? false : this.isUnicodeInputAllowed();
   }

   private boolean isUnicodeInputAllowed() {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized int getAppId() {
      return this._focusGainedAppId;
   }

   private void switchLocaleIfNeeded(IComponent var1, Locale var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private synchronized void focusGained(FocusEvent var1) {
      IComponent var2 = var1.getSource();
      var1.setSource(null);
      Locale var3 = null;
      if (this._focusGainedAppId == -1 || this._focusGainedAppId == var1.getApplicationId()) {
         this.endComposition();
         if (this._focusGainedAppId == var1.getApplicationId()) {
            if (this._cachedLocale != null && var2 != _component.get()) {
               var3 = this._cachedLocale;
            }

            this.reset(0);
         }

         if (var2.getInputMethodRequests() != null && var2.isInputMethodEnabled()) {
            _component.set(var2);
            var1.setSource(var2);
            this._focusGainedAppId = var1.getApplicationId();
         } else {
            var1.setSource((IComponent)_component.get());
            if (this._focusGainedAppId != -1) {
               this._focusGainedAppId = var1.getApplicationId();
            }
         }
      } else if (var2.getInputMethodRequests() != null && var2.isInputMethodEnabled()) {
         _component.set(var2);
         var1.setSource(var2);
         this._focusGainedAppId = var1.getApplicationId();
         this.reset(1);
      } else {
         var1.setSource((IComponent)_component.get());
      }

      this.switchLocaleIfNeeded(var2, var3);
      var2 = var1.getSource();
      if (this._inputMethod != null && var2 != null) {
         this.updateHanMask(var2, this.getLocale());
         this._inputMethod.dispatchEvent(var1);
         if (var2.getInputMethodRequests() != null && var2.isInputMethodEnabled()) {
            this._isComponentAllowed = true;
            this._inputMethod.activate();
         }
      }

      this._imSwitcherAllowed = this.isIMSwitchAllowedHelper();
   }

   private void updateHanMask(IComponent var1, Locale var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   private Font getFontForHanMask(Font var1, int var2) {
      int var3 = var1.getStyle() & -7169;
      return var1.derive(var3 | var2);
   }

   private int getHanMaskForLocale(Locale var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private synchronized void focusLost(FocusEvent var1) {
      if (var1.getApplicationId() == this._focusGainedAppId) {
         Object var2 = _component.get();
         if (var1.getSource() == var2) {
            if (this._cachedLocale != null) {
               this.selectInputMethod(this._cachedLocale, null, 2);
               this._cachedLocale = null;
            }

            this.endComposition();
            if (this._inputMethod != null) {
               this._inputMethod.dispatchEvent(var1);
            }

            this._focusGainedAppId = -1;
            this._isComponentAllowed = false;
            return;
         }
      } else if (var1.getSource() != null && this._inputMethod != null) {
         this.cleanComponent(var1.getSource());
      }
   }

   public LingDataRegistry getLingDataRegistry() {
      return null;
   }

   public IMManager getInputMethodsManager() {
      return this._manager;
   }

   public int setListener(InputModeChangeListener var1) {
      return this._inputMethod != null ? this._inputMethod.setListener(var1) : 2;
   }

   public InputModeChangeListener getListener() {
      return this._inputMethod != null ? this._inputMethod.getListener() : null;
   }

   public CustomWordsRepository getRepository(int var1) {
      if (var1 == 1 || var1 == 6) {
         return this._manager.getRepository(var1);
      } else {
         return this._inputMethod != null ? this._inputMethod.getRepository(var1) : null;
      }
   }

   public CustomDictionary getCustomDictionary(int var1) {
      return this._inputMethod != null ? this._inputMethod.getCustomDictionary(var1) : null;
   }

   public boolean hasSureType() {
      return (this._manager.getAvailableInputMethodIDs() & 4096) != 0;
   }

   public long getInputMethodIDForLocale(Locale var1) {
      return this._manager.getInputMethodIDForLocale(var1);
   }

   public boolean isSureType() {
      return (this.getActiveInputMethodID() & 4096) != 0;
   }

   public void setInvokableActionProducer(InvokableActionProducer var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public InvokableAction[] getIMActions(Object var1) {
      return this._invokableActionProducer != null ? this._invokableActionProducer.getIMActions(var1) : null;
   }
}
