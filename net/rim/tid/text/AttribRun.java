package net.rim.tid.text;

public final class AttribRun {
   public int iStart;
   public int iLength;
   public int iAttrib;

   public AttribRun() {
   }

   public AttribRun(int var1, int var2, int var3) {
      this.iStart = var1;
      this.iLength = var2;
      this.iAttrib = var3;
   }

   public final void init(AttribRun var1) {
      this.iStart = var1.iStart;
      this.iLength = var1.iLength;
      this.iAttrib = var1.iAttrib;
   }
}
