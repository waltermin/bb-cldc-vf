package net.rim.device.api.ui.component;

import net.rim.device.api.collection.util.KeywordFilterList;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.api.util.StringUtilities;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.im.conv.jp.util.KanaConversionUtils;
import net.rim.tid.text.AttributedString;
import net.rim.vm.TraceBack;

public class KeywordFilteredListFinder extends BasicEditField {
   private boolean _displayUpperCaseCharsInSearchText;
   private boolean _allowSpacesInSearchText;
   private boolean _acceptYomiSearch;
   private String _findString;
   private String _baseText;
   protected boolean _preventCall;
   private String _searchPattern;
   private KeywordFilterCollectionListField _listField;
   private XYRect _focusRect;
   private boolean _drawFocusIndicator;
   private boolean _showCaretOnEmptySearch;

   public KeywordFilteredListFinder() {
      this._displayUpperCaseCharsInSearchText = true;
      this._allowSpacesInSearchText = true;
      this._acceptYomiSearch = false;
   }

   public KeywordFilteredListFinder(long var1) {
      super(var1);
      this._displayUpperCaseCharsInSearchText = true;
      this._allowSpacesInSearchText = true;
      this._acceptYomiSearch = false;
   }

   public KeywordFilteredListFinder(String var1) {
      this(var1, null, true);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String var1, long var2) {
      this(var1, null, true, var2);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String var1, String var2, boolean var3) {
      this(var1, var2, var3, 0);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String var1, String var2, boolean var3, long var4) {
   }

   private String composeLanguageAdjustedPattern() {
      if (this._acceptYomiSearch && this.getInputContext().getActiveInputMethodID() == 512) {
         Object var1 = new Object();
         AttributedString var2 = this.getAttributedText();
         int var3 = this.getComposedTextEnd();
         boolean var4 = false;

         for (int var5 = this.getLabelLength(); var5 < var3; var5++) {
            char var6 = var2.charAt(var5);
            var4 |= CharacterUtilities.isHan(var6);
            ((StringBuffer)var1).append(var6);
         }

         if (var4) {
            int var7 = ((StringBuffer)var1).length();
            int var8 = KanaConversionUtils.kanaToHalfWidth((StringBuffer)var1, 0, ((StringBuffer)var1).length(), (StringBuffer)var1);
            if (var8 != var7) {
               ((StringBuffer)var1).setLength(var7);
            } else {
               ((StringBuffer)var1).delete(0, var7);
            }
         }

         return ((StringBuffer)var1).toString();
      } else {
         return null;
      }
   }

   private String composeSearchPattern(InputMethodEvent var1) {
      String var2 = this.composeLanguageAdjustedPattern();
      if (var2 != null) {
         return var2;
      }

      int var3 = super.getLabelLength();
      return super.getText(var3, this.getComposedTextStart() - var3 + var1.getConvertedCharacterCount());
   }

   @Override
   public void focusChangeNotify(int var1) {
      boolean var2 = this._preventCall;
      this._preventCall = true;
      super.focusChangeNotify(var1);
      this._preventCall = var2;
   }

   public boolean getFocusIndicatorEnabled() {
      return this._drawFocusIndicator;
   }

   private KeywordFilterList getKeywordFilterList() {
      return this._listField.getKeywordFilterList();
   }

   public String getSearchPattern() {
      return super.getText(super.getLabelLength(), super.getCursorPosition());
   }

   private void initiateSearch(String var1) {
      this._listField.initiateSearch(var1);
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public boolean isDirty() {
      return false;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      return var1 == 128 ? super.keyControl(var1, var2, var3) : false;
   }

   public void linkToField(KeywordFilterCollectionListField var1) {
      this._listField = var1;
      var1.setInputProcessor(this);
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      return var1;
   }

   @Override
   protected void paint(Graphics var1) {
      super.paint(var1);
      if (this._drawFocusIndicator) {
         this.getFocusRect(this._focusRect);
         this.drawFocus(var1, true);
      }
   }

   private void performSearch(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void redoSearch(boolean var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void resetSearch() {
      this._searchPattern = null;
      this.redoSearch(false);
      this.setText(null);
   }

   public void setAllowSpacesInSearchText(boolean var1) {
      this._allowSpacesInSearchText = var1;
      if (!this._allowSpacesInSearchText && this._searchPattern != null) {
         this._searchPattern = this._searchPattern.replace(' ', '_');
      }
   }

   public void acceptYomiSearch(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBaseText(String var1) {
      boolean var2 = StringUtilities.strEqual(this._baseText, var1);
      this._baseText = var1;
      if (!var2) {
         this.updateText();
      }
   }

   public void setDisplayUpperCaseCharsInSearchText(boolean var1) {
      this._displayUpperCaseCharsInSearchText = var1;
      if (!this._displayUpperCaseCharsInSearchText && this._searchPattern != null) {
         this._searchPattern = this._searchPattern.toLowerCase();
      }
   }

   public void setFocusIndicatorEnabled(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSearchPattern(String var1) {
      this.setSearchPattern(var1, true);
   }

   private void setSearchPattern(String var1, boolean var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void updateInputStyle() {
      super.updateInputStyle();
      Object var1 = this.getInputContext().getInputMethodControlObject();
      KeywordFilterList var2 = this._listField.getKeywordFilterList();
      if (var2 != null) {
         ((SLControlObject)var1).actionPerformed(38, var2.getSearcher());
      }
   }

   private void updateText() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
