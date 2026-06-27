package net.rim.tid.itie;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.XYRect;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.InputMethodRequests;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputMethodContext;
import net.rim.tid.im.ISupplementaryInputData;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedTextIterator;
import net.rim.tid.text.TextHitInfo;
import net.rim.vm.WeakReference;

public final class IMContext extends InputContext implements InputMethodContext {
   private WeakReference _event;
   private EventHandler _eventHandler;
   private LingDataRegistry _lingDataRegistry;
   private SecureBufferRegistry _secureBufferRegistry;
   private static IMContext _instance;

   final EventHandler getEventHandler() {
      return this._eventHandler;
   }

   final InputMethod getInputMethod() {
      return super._inputMethod;
   }

   @Override
   public final int dispatchInputMethodEvent(
      int var1, int var2, AttributedString var3, long var4, int var6, int var7, TextHitInfo var8, TextHitInfo var9, ISupplementaryInputData var10
   ) {
      return this.dispatchInputMethodEvent(var1, var2, var3, var4, var6, var7, var8, var9, var10, (byte)4);
   }

   @Override
   public final int dispatchInputMethodEvent(
      int var1, int var2, AttributedString var3, long var4, int var6, int var7, TextHitInfo var8, TextHitInfo var9, ISupplementaryInputData var10, byte var11
   ) {
      IComponent var12 = this.getInputComponent();
      super._lastIMEventResult = 1;
      if (var12 != null) {
         InputMethodEvent var13 = this.getIMEvent(var12, var1, var2, var3, var4, var6, var7, var8, var9, var10, var11);
         switch (var1) {
            case 1099:
            case 1103:
               break;
            case 1100:
            default:
               super._lastIMEventResult = var12.inputMethodTextChanged(var13);
               break;
            case 1101:
            case 1102:
            case 1104:
               super._lastIMEventResult = var12.caretPositionChanged(var13);
         }
      }

      return super._lastIMEventResult;
   }

   @Override
   public final void setIMCookieCache(Object var1) {
      IComponent var2 = this.getInputComponent();
      if (var2 != null) {
         var2.setIMCookieCache(var1);
      }
   }

   @Override
   public final int getInsertPositionOffset() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getInsertPositionOffset() : -1;
   }

   @Override
   public final AttributedTextIterator getCommittedText(int var1, int var2, String[] var3) {
      return null;
   }

   @Override
   public final Object getIMCookieCache() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getIMCookieCache() : null;
   }

   @Override
   public final void getTextLocation(TextHitInfo var1, XYRect var2) {
      InputMethodRequests var3 = this.getInputMethodRequests();
      if (var3 != null) {
         var3.getTextLocation(var1, var2);
      }
   }

   @Override
   public final void setComposedText(int var1, int var2) {
      InputMethodRequests var3 = this.getInputMethodRequests();
      if (var3 != null) {
         var3.setComposedText(var1, var2);
      }
   }

   @Override
   public final AttributedString getAttributedText() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getAttributedText() : null;
   }

   @Override
   public final int getComposedTextStart() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getComposedTextStart() : -1;
   }

   @Override
   public final int getComposedTextEnd() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getComposedTextEnd() : -1;
   }

   @Override
   public final int getLabelLength() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getLabelLength() : -1;
   }

   @Override
   public final int getCaretPosition() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getCaretPosition() : -1;
   }

   @Override
   public final int getAnchorPosition() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getAnchorPosition() : -1;
   }

   @Override
   public final int getLatestCommittedTextStart() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getLatestCommittedTextStart() : -1;
   }

   @Override
   public final int getLatestCommittedTextEnd() {
      InputMethodRequests var1 = this.getInputMethodRequests();
      return var1 != null ? var1.getLatestCommittedTextEnd() : -1;
   }

   @Override
   public final TextHitInfo getLocationOffset(int var1, int var2) {
      return null;
   }

   @Override
   public final AttributedTextIterator cancelLatestCommittedText() {
      return null;
   }

   @Override
   public final AttributedTextIterator getSelectedText() {
      return null;
   }

   @Override
   public final int getSelectionStart() {
      return -1;
   }

   @Override
   public final int getSelectionOffset() {
      return -1;
   }

   @Override
   public final int getSelectionEnd() {
      return -1;
   }

   @Override
   public final int getCommittedTextLength() {
      return -1;
   }

   @Override
   public final AttributedTextIterator getText(int var1, int var2, boolean var3) {
      return null;
   }

   @Override
   public final void actionPerformed(int var1, Object var2) {
      IComponent var3 = this.getInputComponent();
      if (var3 != null) {
         var3.actionPerformed(var1, var2);
      }
   }

   @Override
   public final int dispatchInputMethodEvent(int var1, AttributedString var2, long var3, int var5, int var6, TextHitInfo var7, TextHitInfo var8) {
      return this.dispatchInputMethodEvent(var1, 0, var2, var3, var5, var6, var7, var8, null, (byte)4);
   }

   @Override
   public final InputMethod getInputMethod(Locale var1) {
      return var1 != null ? super._manager.getInputMethod(var1, null) : null;
   }

   private final void handleException(Throwable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final synchronized boolean selectInputMethod(Locale var1, String var2, int var3) {
      if (super.selectInputMethod(var1, var2, var3)) {
         this.loadLingData(var1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public final void enableLookup(boolean var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private final void loadLingData(Locale var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void processLingDataLoad(LinguisticData var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void addSecureBuffer(ISecureInputMethodBuffer var1) {
      this._secureBufferRegistry.registerBuffer(var1);
   }

   @Override
   protected final boolean changeIM(Locale var1, InputMethod var2) {
      boolean var3 = var2.setLocale(var1);
      if (var3) {
         var2.setInputMethodContext(this);
         var2.endComposition();
      }

      return var3;
   }

   private final InputMethodEvent getIMEvent(
      IComponent var1,
      int var2,
      int var3,
      AttributedString var4,
      long var5,
      int var7,
      int var8,
      TextHitInfo var9,
      TextHitInfo var10,
      ISupplementaryInputData var11,
      byte var12
   ) {
      InputMethodEvent var13 = (InputMethodEvent)this._event.get();
      if (var13 == null) {
         var13 = new InputMethodEvent(var1, var2, var3, var4, var5, var7, var8, var9, var10);
         var13.setCaretShape(var12);
         this._event.set(var13);
      } else {
         var13.init(var1, var2, var3, var4, var5, var7, var8, var9, var10, true, var12);
      }

      var13.setSupplementaryInputData(var11);
      return var13;
   }

   @Override
   public final void dispatchEvent(Event var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final void notifyClientWindowChange() {
   }

   @Override
   public final void endComposition() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final synchronized boolean addIMDescriptor(String var1, String var2, boolean var3) {
      boolean var4 = super._manager.addIMDescriptor(var1, var2);
      if (var3 && var4 && super._inputMethod != null) {
         this.loadLingData(super._inputMethod.getLocale());
      }

      return var4;
   }

   public static final IMContext getInstance0() {
      if (_instance == null) {
         _instance = new IMContext();
      }

      return _instance;
   }

   private IMContext() {
      super._manager = new IMManager();
      this._event = (WeakReference)(new Object(null));
      this._lingDataRegistry = new LingDataRegistry(this);
      this._eventHandler = new EventHandler();
      this._secureBufferRegistry = (SecureBufferRegistry)(new Object());
   }

   @Override
   public final LingDataRegistry getLingDataRegistry() {
      return this._lingDataRegistry;
   }

   private final InputMethodRequests getInputMethodRequests() {
      IComponent var1 = this.getInputComponent();
      return var1 != null ? var1.getInputMethodRequests() : null;
   }
}
