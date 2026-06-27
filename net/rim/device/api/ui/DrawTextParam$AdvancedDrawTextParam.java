package net.rim.device.api.ui;

public class DrawTextParam$AdvancedDrawTextParam {
   public int iStartOffset;
   public int iBaseLine;
   public int iSupplementaryRotation;
   public int iWordSpacing;
   public boolean iOverrideWordSpacing;
   public boolean iKern;
   public boolean iAllowStartOverlap;
   public static int ALPHABETIC_BASELINE;
   public static int IDEOGRAPHIC_BASELINE;
   public static int HANGING_BASELINE;
   public static int MATHEMATICAL_BASELINE;
   public static int CENTRAL_BASELINE;
   public static int MIDDLE_BASELINE;
   public static int TEXT_BEFORE_EDGE_BASELINE;
   public static int TEXT_AFTER_EDGE_BASELINE;

   public DrawTextParam$AdvancedDrawTextParam() {
      this.reset();
   }

   public void reset() {
      this.iStartOffset = 0;
      this.iBaseLine = ALPHABETIC_BASELINE;
      this.iSupplementaryRotation = 0;
      this.iWordSpacing = 0;
      this.iOverrideWordSpacing = false;
      this.iKern = true;
      this.iAllowStartOverlap = false;
   }
}
