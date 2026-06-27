package net.rim.device.api.ui.component;

class SymbolScreen$Encoding extends LabelField {
   SymbolScreen$Header _parent;
   private int _encodingType;
   private String[] _encodingStrings;
   private final SymbolScreen this$0;

   public SymbolScreen$Encoding(SymbolScreen var1, long var2, int var4, SymbolScreen$Header var5) {
   }

   public void setEncoding(int var1) {
      if (var1 < 0 || var1 >= this._encodingStrings.length) {
         var1 = 0;
      }

      this._encodingType = var1;
      this.setText(this._encodingStrings[this._encodingType]);
      if (this._parent._code != null) {
         this._parent._code.setType(this._encodingType);
      }
   }

   public int getEncoding() {
      return this._encodingType;
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
