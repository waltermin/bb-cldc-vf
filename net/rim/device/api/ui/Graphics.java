package net.rim.device.api.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.vm.TraceBack;

public final class Graphics implements DrawStyle {
   private Bitmap _backBufferBitmap;
   private int[] _transformationMatrix;
   private int _isIdentity;
   private Object _stack;
   private int _stackSize;
   private XYRect _objectClippingRect;
   private Screen _currentScreen;
   private XYRect[] _overlappedRectArray;
   private int _overlappedRectArrayStart;
   private int _clipX;
   private int _clipY;
   private int _clipWidth;
   private int _clipHeight;
   private int _offsetX;
   private int _offsetY;
   private Font _font;
   private int _fgColour;
   private int _globalAlpha;
   private int _bgColour;
   private int _stipple;
   private Bitmap _bgBitmap;
   private int _bgBitmapOffsetX;
   private int _bgBitmapOffsetY;
   private int _tileBgBitmap;
   private int _styleFlag;
   private int _strokeWidth;
   private int _strokeStyle;
   private int _surfaceData0;
   private int _surfaceData1;
   private int _surfaceData2;
   private int _surfaceData3;
   private int _surfaceData4;
   private int _surfaceData5;
   private int _surfaceData6;
   private int _surfaceData7;
   private int _surfaceData8;
   private int _surfaceData9;
   private int _surfaceData10;
   private int _surfaceData11;
   private int _surfaceData12;
   private int _surfaceData13;
   private int _surfaceData14;
   private int _surfaceData15;
   private int _surfaceData16;
   private int _surfaceData17;
   private int _surfaceData18;
   private int _surfaceData19;
   private int _surfaceData20;
   private int _surfaceData21;
   private int _surfaceData22;
   private int _surfaceData23;
   private int _surfaceData24;
   private int _surfaceData25;
   private int _surfaceData26;
   private int _surfaceData27;
   private int _surfaceData28;
   private int _surfaceData29;
   public static final int BLACK;
   public static final int WHITE;
   public static final int FULL_BLACK;
   public static final int FULL_WHITE;
   public static final int ROP_SRC_COPY;
   public static final int ROP_SRCMONOEXPAND_COPY;
   public static final int ROP_SRC_ALPHA;
   public static final int ROP_SRCMONOEXPAND_ALPHA;
   public static final int ROP_CONST_GLOBALALPHA;
   public static final int ROP_SRC_GLOBALALPHA;
   public static final int ROP_SRC_ALPHA_GLOBALALPHA;
   public static final int ROP2_Grey;
   public static final int ROP2_0;
   public static final int ROP2_DSon;
   public static final int ROP2_DSna;
   public static final int ROP2_Sn;
   public static final int ROP2_SDna;
   public static final int ROP2_Dn;
   public static final int ROP2_DSx;
   public static final int ROP2_DSan;
   public static final int ROP2_DSa;
   public static final int ROP2_DSxn;
   public static final int ROP2_D;
   public static final int ROP2_DSno;
   public static final int ROP2_S;
   public static final int ROP2_SDno;
   public static final int ROP2_DSo;
   public static final int ROP2_1;
   public static final int DRAWSTYLE_AALINES;
   public static final int DRAWSTYLE_AAPOLYGONS;
   public static final int DRAWSTYLE_DITHERED_SHADING;
   public static final int DRAWSTYLE_FOCUS;
   public static final int DRAWSTYLE_SELECT;
   public static final int DRAWSTYLE_QUICK;
   private static final int DRAWSTYLE_DEFAULT;
   public static final byte CURVEDPATH_END_POINT;
   public static final byte CURVEDPATH_QUADRATIC_BEZIER_CONTROL_POINT;
   public static final byte CURVEDPATH_CUBIC_BEZIER_CONTROL_POINT;
   public static final int STROKESTYLE_LINECAP_BUTT;
   public static final int STROKESTYLE_LINEJOIN_MITER;
   public static final boolean SCREEN_HAS_BORDER;
   public static final int NO_GAMMA;
   public static final int EXPONENTIAL_GAMMA;
   public static final int SIGMOID_GAMMA;

   Graphics() {
      this._objectClippingRect = (XYRect)(new Object());
      this.createFrontbufferSurface();
      this.createStack(Display.getWidth(), Display.getHeight());
   }

   private final native void createFrontbufferSurface();

   public Graphics(Bitmap var1) {
   }

   private static final native boolean fitsInCache(Bitmap var0);

   private final native void createBackbufferSurface(Bitmap var1);

   private final void createStack(int var1, int var2) {
      this._clipWidth = var1;
      this._clipHeight = var2;
      this._stackSize = 1;
   }

   private final void init(int var1, int var2) {
      this.resetStack();
      this.setClip(0, 0, var1, var2);
      this.setOffset(0, 0);
      this._font = Font.getDefault();
      this._fgColour = 0;
      this._globalAlpha = 255;
      this._bgColour = 16777215;
      this._stipple = -1;
      this.setBackgroundImage(null, 0, 0);
      this._styleFlag = 4;
      this._strokeWidth = 1;
      this._strokeStyle = 17;
      this._isIdentity = 1;
   }

   static final Graphics getGraphics(Screen var0) {
      UiEngineImpl var1 = var0.getUiEngineImpl();
      Graphics var2 = null;
      if (var1 != null) {
         var1.assertHaveEventLock();
         var2 = var1._fbGraphics;
         int var3 = var1.getLocalGlobalScreenIndex(var0);
         if (var3 != -1) {
            if (var3 <= var1.getLocalGlobalScreenCount() - 2) {
               var2._overlappedRectArray = var1.getOpaqueRegionsArray();
               var2._overlappedRectArrayStart = var3 + 1;
            } else {
               var2._overlappedRectArray = null;
            }
         }
      } else {
         var2 = new Graphics();
      }

      var2.init(Display.getWidth(), Display.getHeight());
      var2.setCurrentScreen(var0);
      return var2;
   }

   static final void releaseGraphics(Screen var0) {
      if (var0 != null) {
         UiEngineImpl var1 = var0.getUiEngineImpl();
         if (var1 != null && var1._fbGraphics._currentScreen == var0) {
            var1._fbGraphics.setCurrentScreen(null);
         }
      }
   }

   static final Graphics getNullGraphics() {
      Graphics var0 = new Graphics();
      var0.init(0, 0);
      var0.setCurrentScreen(null);
      return var0;
   }

   public final native void clear();

   public final native void clear(int var1, int var2, int var3, int var4);

   public final native void clear(XYRect var1);

   public final native boolean copyArea(int var1, int var2, int var3, int var4, int var5, int var6);

   public final native boolean copyArea(XYRect var1, int var2, int var3);

   public final native void draw2dContextList(Object[] var1, int var2);

   public final native void drawARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native void drawArc(int var1, int var2, int var3, int var4, int var5, int var6);

   public final void drawEllipse(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.drawEllipse32(var1 << 16, var2 << 16, var3 << 16, var4 << 16, var5 << 16, var6 << 16, var7 << 16, var8 << 16);
   }

   public final native void drawEllipse32(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   public final native boolean isRopSupported(int var1);

   public final native void rop(int var1, int var2, int var3, int var4, int var5, Bitmap var6, int var7, int var8);

   public final native void tileRop(int var1, int var2, int var3, int var4, int var5, Bitmap var6, int var7, int var8);

   public final native void drawBitmap(XYRect var1, Bitmap var2, int var3, int var4);

   public final native void drawBitmap(int var1, int var2, int var3, int var4, Bitmap var5, int var6, int var7);

   public final void ropImage(int var1, int var2, int var3, int var4, int var5, EncodedImage var6, int var7, int var8, int var9) {
      this.ropImageInternal(var1, false, var2, var3, var4, var5, var6, var7, var8, var9, var6.getImageType(), var6.getBitmapType(var7), var6.getAlphaType(var7));
   }

   public final void tileRopImage(int var1, int var2, int var3, int var4, int var5, EncodedImage var6, int var7, int var8, int var9) {
      this.ropImageInternal(var1, true, var2, var3, var4, var5, var6, var7, var8, var9, var6.getImageType(), var6.getBitmapType(var7), var6.getAlphaType(var7));
   }

   public final void drawImage(XYRect var1, EncodedImage var2, int var3, int var4, int var5) {
      EncodedImage var6 = var2.getReplacementImage(var1.width, var1.height);
      if (var6 != var2) {
         var6.setScaleX32(var2.getScaleX32());
         var6.setScaleY32(var2.getScaleY32());
      }

      this.drawImageInternal(
         var1.x, var1.y, var1.width, var1.height, var6, var3, var4, var5, var6.getImageType(), var6.getBitmapType(var3), var6.getAlphaType(var3)
      );
   }

   public final void drawImage(int var1, int var2, int var3, int var4, EncodedImage var5, int var6, int var7, int var8) {
      EncodedImage var9 = var5.getReplacementImage(var3, var4);
      if (var9 != var5) {
         var9.setScaleX32(var5.getScaleX32());
         var9.setScaleY32(var5.getScaleY32());
      }

      this.drawImageInternal(var1, var2, var3, var4, var9, var6, var7, var8, var9.getImageType(), var9.getBitmapType(var6), var9.getAlphaType(var6));
   }

   private final native void drawImageInternal(
      int var1, int var2, int var3, int var4, EncodedImage var5, int var6, int var7, int var8, int var9, int var10, int var11
   );

   private final native void ropImageInternal(
      int var1, boolean var2, int var3, int var4, int var5, int var6, EncodedImage var7, int var8, int var9, int var10, int var11, int var12, int var13
   );

   public final void drawFilledPath(int[] var1, int[] var2, byte[] var3, int[] var4) {
      this.drawPath(var1, var2, var3, null, var4, true, true);
   }

   public final void drawShadedFilledPath(int[] var1, int[] var2, byte[] var3, int[] var4, int[] var5) {
      this.drawPath(var1, var2, var3, var4, var5, true, true);
   }

   public final native void drawPathOutline(int[] var1, int[] var2, byte[] var3, int[] var4, boolean var5);

   private final native void drawPath(int[] var1, int[] var2, byte[] var3, int[] var4, int[] var5, boolean var6, boolean var7);

   public final native void drawTexturedPath(
      int[] var1, int[] var2, byte[] var3, int[] var4, int var5, int var6, int var7, int var8, int var9, int var10, Bitmap var11
   );

   public final native int drawTextOnPath(
      StringBuffer var1, int var2, int var3, int var4, int var5, int[] var6, int[] var7, byte[] var8, int[] var9, DrawTextParam var10, TextMetrics var11
   );

   public final native int drawTextOnPath(
      String var1, int var2, int var3, int var4, int var5, int[] var6, int[] var7, byte[] var8, int[] var9, DrawTextParam var10, TextMetrics var11
   );

   public final native int drawTextOnPath(
      StringBufferGap var1, int var2, int var3, int var4, int var5, int[] var6, int[] var7, byte[] var8, int[] var9, DrawTextParam var10, TextMetrics var11
   );

   public final native int drawTextOnPath(
      char[] var1, int var2, int var3, int var4, int var5, int[] var6, int[] var7, byte[] var8, int[] var9, DrawTextParam var10, TextMetrics var11
   );

   public final native void drawLine(int var1, int var2, int var3, int var4);

   public final native void drawPoint(int var1, int var2);

   public final native void drawRect(int var1, int var2, int var3, int var4);

   public final native void drawRect32(int var1, int var2, int var3, int var4);

   public final native void drawRoundRect(int var1, int var2, int var3, int var4, int var5, int var6);

   public final native void drawRGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(char var1, int var2, int var3, int var4, int var5);

   public final int drawText(String var1, int var2, int var3) {
      return this.drawText(var1, 0, Integer.MAX_VALUE, var2, var3, 0, -1);
   }

   public final int drawText(String var1, int var2, int var3, int var4) {
      return this.drawText(var1, 0, Integer.MAX_VALUE, var2, var3, var4, -1);
   }

   public final int drawText(String var1, int var2, int var3, int var4, int var5) {
      return this.drawText(var1, 0, Integer.MAX_VALUE, var2, var3, var4, var5);
   }

   public final native int drawText(byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(char[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(String var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(StringBuffer var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(StringBufferGap var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final native int drawText(String var1, int var2, int var3, int var4, int var5, DrawTextParam var6, TextMetrics var7);

   public final native int drawText(StringBuffer var1, int var2, int var3, int var4, int var5, DrawTextParam var6, TextMetrics var7);

   public final native int drawText(StringBufferGap var1, int var2, int var3, int var4, int var5, DrawTextParam var6, TextMetrics var7);

   public final native int drawText(char[] var1, int var2, int var3, int var4, int var5, DrawTextParam var6, TextMetrics var7);

   public final native void fillArc(int var1, int var2, int var3, int var4, int var5, int var6);

   public final void fillEllipse(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.fillEllipse32(var1 << 16, var2 << 16, var3 << 16, var4 << 16, var5 << 16, var6 << 16, var7 << 16, var8 << 16);
   }

   public final native void fillEllipse32(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   public final native void fillRect(int var1, int var2, int var3, int var4);

   public final native void fillRect32(int var1, int var2, int var3, int var4);

   public final native void fillRoundRect(int var1, int var2, int var3, int var4, int var5, int var6);

   public final void getAbsoluteClippingRect(XYRect var1) {
      var1.x = this._clipX;
      var1.y = this._clipY;
      var1.width = this._clipWidth;
      var1.height = this._clipHeight;
   }

   public final int getBackgroundColor() {
      return this._bgColour;
   }

   public final int getBackgroundOffsetX() {
      return this._bgBitmapOffsetX;
   }

   public final int getBackgroundOffsetY() {
      return this._bgBitmapOffsetY;
   }

   public final XYRect getClippingRect() {
      this._objectClippingRect.x = this._clipX - this._offsetX;
      this._objectClippingRect.y = this._clipY - this._offsetY;
      this._objectClippingRect.width = this._clipWidth;
      this._objectClippingRect.height = this._clipHeight;
      return this._objectClippingRect;
   }

   public final int getContextStackSize() {
      return this._stackSize;
   }

   public final int getColor() {
      return this._fgColour;
   }

   public static final native int getDisplayColor(int var0);

   public final void getDrawingOffset(XYPoint var1) {
      var1.x = this._offsetX;
      var1.y = this._offsetY;
   }

   public final int getGlobalAlpha() {
      return this._globalAlpha;
   }

   public final int[] getMatrix() {
      return this._transformationMatrix;
   }

   public static final int getNumColors() {
      return Display.getNumColors();
   }

   public final int getStipple() {
      return this._stipple;
   }

   public final Font getFont() {
      return this._font;
   }

   public static final int getScreenHeight() {
      return Display.getHeight();
   }

   public static final int getScreenHorizontalResolution() {
      return Display.getHorizontalResolution();
   }

   public static final int getScreenVerticalResolution() {
      return Display.getVerticalResolution();
   }

   public static final int getScreenWidth() {
      return Display.getWidth();
   }

   public final native void invert(int var1, int var2, int var3, int var4);

   public final native void invert(XYRect var1);

   final void nullify() {
      this.init(0, 0);
   }

   public final native void popContext();

   public final native boolean pushRegion(int var1, int var2, int var3, int var4, int var5, int var6);

   public final native boolean pushRegion(XYRect var1);

   public final native boolean pushRegion(XYRect var1, int var2, int var3);

   public final native boolean pushContext(XYRect var1, int var2, int var3);

   public final native boolean pushContext(int var1, int var2, int var3, int var4, int var5, int var6);

   public final void translate(int var1, int var2) {
      this._offsetX += var1;
      this._offsetY += var2;
   }

   public final int getTranslateX() {
      return this._offsetX;
   }

   public final int getTranslateY() {
      return this._offsetY;
   }

   public final boolean isDrawingStyleSet(int var1) {
      return (this._styleFlag & var1) != 0;
   }

   public final void setDrawingStyle(int var1, boolean var2) {
      if (var2) {
         this._styleFlag |= var1;
      } else {
         this._styleFlag &= ~var1;
      }
   }

   public final void setStrokeWidth(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setStrokeStyle(int var1) {
      if (var1 < 16) {
         this._strokeStyle = this._strokeStyle & 240 | var1;
      } else {
         this._strokeStyle = this._strokeStyle & 15 | var1;
      }
   }

   public final void setColor(int var1) {
      this._fgColour = var1 & 16777215;
   }

   public final void setGlobalAlpha(int var1) {
      this._globalAlpha = MathUtilities.clamp(0, var1, 255);
   }

   public final void setStipple(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setBackgroundColor(int var1) {
      this._bgColour = var1 & 16777215;
   }

   public final void setBackgroundImage(Bitmap var1, int var2, int var3) {
      this._bgBitmap = var1;
      this._bgBitmapOffsetX = this._offsetX + var2;
      this._bgBitmapOffsetY = this._offsetY + var3;
      this._tileBgBitmap = 1;
   }

   public final void setFont(Font var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._font = var1;
   }

   private final void setCurrentScreen(Screen var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public static final native void flush();

   static final void updateDisplay() {
      UiEngineImpl var0 = UiEngineImpl.getUiEngine();
      if (var0 != null) {
         var0._offsetX.updateDisplay0();
      }
   }

   private final native void updateDisplay0();

   public static final boolean isColor() {
      return Display.isColor();
   }

   private final void checkIdentity() {
      if (this._transformationMatrix[0] == 65536
         && this._transformationMatrix[1] == 0
         && this._transformationMatrix[2] == 0
         && this._transformationMatrix[3] == 0
         && this._transformationMatrix[4] == 65536
         && this._transformationMatrix[5] == 0
         && this._transformationMatrix[6] == 0
         && this._transformationMatrix[7] == 0
         && this._transformationMatrix[8] == 65536) {
         this._isIdentity = 1;
      } else {
         this._isIdentity = 0;
      }
   }

   public final void setMatrix(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      if (this._transformationMatrix == null) {
         this._transformationMatrix = new int[9];
      }

      this._transformationMatrix[0] = var1;
      this._transformationMatrix[1] = var2;
      this._transformationMatrix[2] = var3;
      this._transformationMatrix[3] = var4;
      this._transformationMatrix[4] = var5;
      this._transformationMatrix[5] = var6;
      this._transformationMatrix[6] = var7;
      this._transformationMatrix[7] = var8;
      this._transformationMatrix[8] = var9;
      this.checkIdentity();
   }

   public final void setMatrix(int[] var1) {
      this.setMatrix(var1, 0);
   }

   public final void setMatrix(int[] var1, int var2) {
      this.setMatrix(
         var1[0 + var2], var1[1 + var2], var1[2 + var2], var1[3 + var2], var1[4 + var2], var1[5 + var2], var1[6 + var2], var1[7 + var2], var1[8 + var2]
      );
   }

   public final void setIdentity() {
      this._transformationMatrix = null;
      this._isIdentity = 1;
   }

   private final native void resetStack();

   private final void setClip(int var1, int var2, int var3, int var4) {
      this._clipX = var1;
      this._clipY = var2;
      this._clipWidth = var3;
      this._clipHeight = var4;
   }

   private final void setOffset(int var1, int var2) {
      this._offsetX = var1;
      this._offsetY = var2;
   }

   public final void setOverlay(int var1, Bitmap var2, int var3, int var4) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.setOverlayInternal(var1, var2, var3, var4, false);
   }

   public final void setOverlay(int var1, Bitmap var2, int var3, int var4, boolean var5) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.setOverlayInternal(var1, var2, var3, var4, var5);
   }

   private final void setOverlayInternal(int var1, Bitmap var2, int var3, int var4, boolean var5) {
      if (var2 != null && var2.getType() != Bitmap.getDefaultType()) {
         throw new Object();
      }

      if (this._backBufferBitmap != null) {
         if (var2 != null) {
            this.drawBitmap(var3, var4, var2.getWidth(), var2.getHeight(), var2, 0, 0);
            return;
         }
      } else if (this._currentScreen == UiEngineImpl.getTopmostLocalGlobalScreen()) {
         setOverlay0(var1, var2, var3 + this._offsetX, var4 + this._offsetY, var5);
      }
   }

   public final boolean setOverlayPosition(int var1, int var2, int var3) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this._currentScreen == UiEngineImpl.getTopmostLocalGlobalScreen() ? setOverlayPosition0(var1, var2 + this._offsetX, var3 + this._offsetY) : false;
   }

   public final boolean isOverlaySet(int var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this._currentScreen == UiEngineImpl.getTopmostLocalGlobalScreen() ? isOverlaySet0(var1) : false;
   }

   public static final native void setGamma(int var0, int var1, int var2, int var3, int var4);

   static final native void setOverlay0(int var0, Bitmap var1, int var2, int var3, boolean var4);

   static final native boolean setOverlayPosition0(int var0, int var1, int var2);

   static final native boolean isOverlaySet0(int var0);

   static final void resetOverlays() {
      for (int var0 = 0; var0 < 5; var0++) {
         setOverlay0(var0, null, 0, 0, false);
      }
   }
}
