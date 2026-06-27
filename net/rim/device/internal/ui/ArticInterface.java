package net.rim.device.internal.ui;

import net.rim.device.api.ui.XYRect;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.TextHitInfo;

public class ArticInterface {
   public static final int ALLOW_LINE_BREAK_ON_NON_BREAK_SPACE;
   public static final int LINE_BREAK_CJK_AS_ALPHABETIC;
   public static final int FORBID_ILLEGAL_LINE_BREAK;
   private static final int KMoveNext;
   private static final int KMovePrev;
   private static final int KMoveRight;
   private static final int KMoveLeft;
   private static final int KMoveDown;
   private static final int KMoveUp;
   public static ArticInterface$Layout _layout;

   private ArticInterface() {
   }

   public static ArticInterface$Layout Format(
      int var0,
      int var1,
      int var2,
      int var3,
      int var4,
      boolean var5,
      int var6,
      AttributedString var7,
      ArticInterface$Line var8,
      int var9,
      int var10,
      int var11,
      boolean var12,
      int var13
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static native void Format(
      ArticInterface$Layout var0,
      int var1,
      int var2,
      int var3,
      int var4,
      int var5,
      boolean var6,
      int var7,
      AttributedString var8,
      ArticInterface$Line var9,
      int var10,
      int var11,
      int var12,
      boolean var13,
      int var14
   );

   public static native void DocPosToCaret(XYRect var0, AttributedString var1, ArticInterface$Line var2, int var3, int var4, int var5, boolean var6);

   public static native void PointToDocPos(
      TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5, int var6, int var7
   );

   private static native void DocPosMove(int var0, TextHitInfo var1, XYRect var2, AttributedString var3, ArticInterface$Line var4, int var5, int var6);

   public static void DocPosNext(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(0, var0, var1, var2, var3, var4, var5);
   }

   public static void DocPosPrev(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(1, var0, var1, var2, var3, var4, var5);
   }

   public static void DocPosLeft(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(3, var0, var1, var2, var3, var4, var5);
   }

   public static void DocPosRight(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(2, var0, var1, var2, var3, var4, var5);
   }

   public static void DocPosDown(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(4, var0, var1, var2, var3, var4, var5);
   }

   public static void DocPosUp(TextHitInfo var0, XYRect var1, AttributedString var2, ArticInterface$Line var3, int var4, int var5) {
      DocPosMove(5, var0, var1, var2, var3, var4, var5);
   }

   public static native int AdjustDocPos(StringBufferGap var0, TextHitInfo var1, int var2);
}
