package net.rim.device.api.ui.component;

import net.rim.device.api.util.EmoticonStringPattern;
import net.rim.device.api.util.StringPattern$Match;
import net.rim.device.internal.ui.FormatParams;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

class SmileySupport {
   private TextField _field;
   private EmoticonStringPattern _smileyFacility;
   private StringPattern$Match _match = (StringPattern$Match)(new Object());
   private static int _size;

   SmileySupport(TextField var1) {
      this._field = var1;
   }

   void setPattern(EmoticonStringPattern var1) {
      this._smileyFacility = var1;
      _size = this._smileyFacility.emoticonSize();
   }

   EmoticonStringPattern getSmileyFacility() {
      return this._smileyFacility;
   }

   void showSymbolScreen() {
      if (this._smileyFacility != null) {
         SmileySymbolScreen.show(this._smileyFacility, this._field);
      } else {
         SymbolScreen.show(this._field);
      }
   }

   boolean isSmileyAvailable() {
      if (this._smileyFacility == null) {
         return false;
      }

      AttributedString$Iterator var1 = this._field._text.getIterator();

      while (!(var1.runPicture() instanceof SmileySupport$SmileyPicture)) {
         if (!var1.next()) {
            return false;
         }
      }

      return true;
   }

   String getDecodedString(int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   StringBufferGap getDecodedTextAbstractString(int var1, int var2) {
      Object var3 = new Object();
      ((StringBuffer)var3).append(this.getDecodedText(var1, var2));
      Object var4 = new Object();
      ((StringBufferGap)var4).insert((StringBuffer)var3);
      return (StringBufferGap)var4;
   }

   void scanForSmileys(FormatParams var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int getDecodedStringLength(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char[] getDecodedText(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int insertSmiley(StringPattern$Match var1) {
      Object var2 = new Object();
      if (!this.isHttpConflict(var1)) {
         ((AttributedString)var2).insert(new SmileySupport$SmileyPicture(this, (int)var1.id));
         this._field._text.replace(var1.beginIndex, var1.endIndex, ((AttributedString)var2).getIterator(), 0, 0);
         return this._match.endIndex - this._match.beginIndex - 1;
      } else {
         return 0;
      }
   }

   private boolean isHttpConflict(StringPattern$Match var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private int smileyScan(int var1, int var2) {
      if (this._smileyFacility == null) {
         return 0;
      }

      int var3 = var2;
      StringBufferGap var4 = this._field.getDisplayText();

      while (var1 < var2 && this._smileyFacility.findMatch(var4, var1, var2, this._match)) {
         int var5 = this.insertSmiley(this._match);
         var2 -= var5;
         var1++;
      }

      return var2 - var3;
   }
}
