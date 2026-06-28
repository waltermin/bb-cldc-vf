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
   private StringPattern$Match _match = new StringPattern$Match();
   private static int _size;

   SmileySupport(TextField field) {
      this._field = field;
   }

   void setPattern(EmoticonStringPattern facility) {
      this._smileyFacility = facility;
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

      AttributedString$Iterator iter = this._field._text.getIterator();

      while (!(iter.runPicture() instanceof SmileySupport$SmileyPicture)) {
         if (!iter.next()) {
            return false;
         }
      }

      return true;
   }

   String getDecodedString(int start, int end) {
      throw new RuntimeException("cod2jar: ldc");
   }

   StringBufferGap getDecodedTextAbstractString(int start, int end) {
      StringBuffer sb = new StringBuffer();
      sb.append(this.getDecodedText(start, end));
      StringBufferGap sbg = new StringBufferGap();
      sbg.insert(sb);
      return sbg;
   }

   void scanForSmileys(FormatParams aParams) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int getDecodedStringLength(int start, int end) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char[] getDecodedText(int start, int end) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private int insertSmiley(StringPattern$Match match) {
      AttributedString insertedSmiley = new AttributedString();
      if (!this.isHttpConflict(match)) {
         insertedSmiley.insert(new SmileySupport$SmileyPicture(this, (int)match.id));
         this._field._text.replace(match.beginIndex, match.endIndex, insertedSmiley.getIterator(), 0, 0);
         return this._match.endIndex - this._match.beginIndex - 1;
      } else {
         return 0;
      }
   }

   private boolean isHttpConflict(StringPattern$Match match) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private int smileyScan(int start, int end) {
      if (this._smileyFacility == null) {
         return 0;
      }

      int origEnd = end;
      StringBufferGap string = this._field.getDisplayText();

      while (start < end && this._smileyFacility.findMatch(string, start, end, this._match)) {
         int result = this.insertSmiley(this._match);
         end -= result;
         start++;
      }

      return end - origEnd;
   }
}
