package net.rim.device.api.ui.component;

class SymbolScreen$Encoding extends LabelField {
   SymbolScreen$Header _parent;
   private int _encodingType;
   private String[] _encodingStrings;
   private final SymbolScreen this$0;

   public SymbolScreen$Encoding(SymbolScreen _1, long style, int encoding, SymbolScreen$Header parent) {
   }

   public void setEncoding(int encodingType) {
      if (encodingType < 0 || encodingType >= this._encodingStrings.length) {
         encodingType = 0;
      }

      this._encodingType = encodingType;
      this.setText(this._encodingStrings[this._encodingType]);
      if (this._parent._code != null) {
         this._parent._code.setType(this._encodingType);
      }
   }

   public int getEncoding() {
      return this._encodingType;
   }

   @Override
   protected boolean trackwheelClick(int status, int time) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public int processKeyEvent(int event, char key, int keycode, int time) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
