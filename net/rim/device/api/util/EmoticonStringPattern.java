package net.rim.device.api.util;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.ui.Graphics;

public class EmoticonStringPattern extends StringPattern {
   public static long SYSTEM_DEFAULT_KEY;

   public static EmoticonStringPattern getSystemDefault() {
      return (EmoticonStringPattern)ApplicationRegistry.getApplicationRegistry().get(SYSTEM_DEFAULT_KEY);
   }

   public int emoticonSize() {
      throw null;
   }

   public void drawEmoticon(Graphics var1, int var2, int var3, int var4) {
      throw null;
   }

   public String emoticonReplacementText(int var1) {
      throw null;
   }

   public String emoticonDescription(int var1) {
      throw null;
   }

   public int[][][] emoticonScreenLayouts() {
      throw null;
   }
}
