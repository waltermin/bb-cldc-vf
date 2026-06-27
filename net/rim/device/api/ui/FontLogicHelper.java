package net.rim.device.api.ui;

public class FontLogicHelper {
   static final int MIN_HEIGHT_ARABIC_STROKE_BOLD;
   static final int MIN_HEIGHT_ARABIC_NO_STROKE_BOLD;
   static final int MIN_HEIGHT_ARABIC_STROKE_NORMAL;
   static final int MIN_HEIGHT_ARABIC_NO_STROKE_NORMAL;
   static final int MIN_HEIGHT_ASIAN_STROKE_BOLD;
   static final int MIN_HEIGHT_ASIAN_NO_STROKE_BOLD;
   static final int MIN_HEIGHT_ASIAN_STROKE_NORMAL;
   static final int MIN_HEIGHT_ASIAN_NO_STROKE_NORMAL;
   private static TextGraphics _textGraphics;

   public static boolean fontLegible(TextGraphics var0, int var1) {
      switch (var1 & -65536) {
         case 1634861056:
         case 1751449600:
            return fontLegible(var0, 15, 13, 12, 10);
         case 1784741888:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var4 = var0.getStyle() & 7168;
               if (var4 != 3072) {
                  return false;
               }
            }

            return fontLegible(var0, 19, 19, 15, 15);
         case 1802436608:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var3 = var0.getStyle() & 7168;
               if (var3 != 4096) {
                  return false;
               }
            }

            return fontLegible(var0, 19, 19, 15, 15);
         case 2053636096:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var2 = var0.getStyle() & 7168;
               if (var1 == 2053653326) {
                  if (var2 != 2048) {
                     return false;
                  }
               } else if (var2 != 1024) {
                  return false;
               }
            }

            return fontLegible(var0, 19, 19, 15, 15);
         default:
            return true;
      }
   }

   private static boolean fontLegible(TextGraphics var0, int var1, int var2, int var3, int var4) {
      int var5 = var0.getHeight();
      if (var5 >= var1) {
         return true;
      } else if (var5 >= var2) {
         return (var0.getStyle() & 1) == 0 || var0.getEffects() != 768;
      } else if (var5 >= var3) {
         return (var0.getStyle() & 1) == 0;
      } else {
         return var5 >= var4 ? (var0.getStyle() & 1) == 0 && var0.getEffects() != 768 : false;
      }
   }

   public static boolean fontLegible(Font var0, int var1) {
      _textGraphics.setFontSpec(var0);
      return fontLegible(_textGraphics, var1);
   }

   public static boolean getSuggestedFont(TextGraphics var0, int var1, boolean var2) {
      switch (var1 & -65536) {
         case 1634861056:
         case 1751449600:
            return getSuggestedFont(var0, 15, 13, 12, 10, var2);
         case 1784741888:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var5 = var0.getStyle() & -7169 | 3072;
               var0.setStyle(var5);
            }

            return getSuggestedFont(var0, 19, 19, 15, 15, var2);
         case 1802436608:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var4 = var0.getStyle() & -7169 | 4096;
               var0.setStyle(var4);
            }

            return getSuggestedFont(var0, 19, 19, 15, 15, var2);
         case 2053636096:
            if (!var0.getTypefaceName().equals(getAltFontFamily(var1).getName())) {
               int var3;
               if (var1 == 2053653326) {
                  var3 = var0.getStyle() & -7169 | 2048;
               } else {
                  var3 = var0.getStyle() & -7169 | 1024;
               }

               var0.setStyle(var3);
            }

            return getSuggestedFont(var0, 19, 19, 15, 15, var2);
         default:
            return true;
      }
   }

   private static boolean getSuggestedFont(TextGraphics var0, int var1, int var2, int var3, int var4, boolean var5) {
      int var6 = var0.getHeight();
      int var7 = var0.getStyle();
      if (var6 >= var1) {
         return true;
      }

      if (var6 >= var2) {
         if ((var7 & 1) != 0 && var0.getEffects() == 768) {
            var0.setStyle(var7 & -2);
            return true;
         } else {
            return true;
         }
      } else if (var6 >= var3) {
         if ((var7 & 1) != 0) {
            var0.setStyle(var7 & -2);
            return true;
         } else {
            return true;
         }
      } else if (var6 >= var4) {
         if ((var7 & 1) != 0) {
            var0.setStyle(var7 & -2);
         }

         if (var0.getEffects() != 768) {
            return true;
         } else if (var5) {
            var0.setHeight(var3);
            return true;
         } else {
            return false;
         }
      } else {
         if ((var7 & 1) != 0) {
            var0.setStyle(var7 & -2);
         }

         if (!var5) {
            return false;
         } else if (var0.getEffects() == 768) {
            var0.setHeight(var3);
            return true;
         } else {
            var0.setHeight(var4);
            return true;
         }
      }
   }

   public static Font getSuggestedFont(Font var0, int var1, boolean var2) {
      FontFamily var3 = getAltFontFamily(var1);
      _textGraphics.setFontSpec(var0);
      getSuggestedFont(_textGraphics, var1, var2);
      return var3.getFont(
         _textGraphics.getStyle(),
         _textGraphics.getHeightWithLeading(),
         0,
         1,
         _textGraphics.getEffects(),
         65536,
         0,
         0,
         65536,
         0,
         0,
         _textGraphics.getEffectsStrokeColor(),
         _textGraphics.getEffectsFillColor()
      );
   }

   public static FontFamily getAltFontFamily(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean useAltFont(int var0) {
      switch (var0 & -65536) {
         case 1667301376:
         case 1668481024:
         case 1684340736:
         case 1701576704:
         case 1701707776:
         case 1702035456:
         case 1718747136:
         case 1752498176:
         case 1769209856:
         case 1852571648:
         case 1886126080:
         case 1886650368:
         case 1953628160:
            return false;
         default:
            return true;
      }
   }
}
