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

   public KeywordFilteredListFinder(long style) {
      super(style);
      this._displayUpperCaseCharsInSearchText = true;
      this._allowSpacesInSearchText = true;
      this._acceptYomiSearch = false;
   }

   public KeywordFilteredListFinder(String text) {
      this(text, null, true);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String text, long complementarySearchFieldStyle) {
      this(text, null, true, complementarySearchFieldStyle);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String text, String findLabel, boolean showCaretOnEmptySearch) {
      this(text, findLabel, showCaretOnEmptySearch, 0);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
   }

   public KeywordFilteredListFinder(String text, String findLabel, boolean showCaretOnEmptySearch, long complementarySearchFieldStyle) {
   }

   private String composeLanguageAdjustedPattern() {
      if (this._acceptYomiSearch && this.getInputContext().getActiveInputMethodID() == 512) {
         StringBuffer compositeSearchPattern = (StringBuffer)(new Object());
         AttributedString attributedString = this.getAttributedText();
         int composedTextEnd = this.getComposedTextEnd();
         boolean hasHanSymbols = false;

         for (int i = this.getLabelLength(); i < composedTextEnd; i++) {
            char ch = attributedString.charAt(i);
            hasHanSymbols |= CharacterUtilities.isHan(ch);
            compositeSearchPattern.append(ch);
         }

         if (hasHanSymbols) {
            int lengthBefore = compositeSearchPattern.length();
            int converted = KanaConversionUtils.kanaToHalfWidth(compositeSearchPattern, 0, compositeSearchPattern.length(), compositeSearchPattern);
            if (converted != lengthBefore) {
               compositeSearchPattern.setLength(lengthBefore);
            } else {
               compositeSearchPattern.delete(0, lengthBefore);
            }
         }

         return compositeSearchPattern.toString();
      } else {
         return null;
      }
   }

   private String composeSearchPattern(InputMethodEvent event) {
      String langPattern = this.composeLanguageAdjustedPattern();
      if (langPattern != null) {
         return langPattern;
      }

      int labelLen = super.getLabelLength();
      return super.getText(labelLen, this.getComposedTextStart() - labelLen + event.getConvertedCharacterCount());
   }

   @Override
   public void focusChangeNotify(int action) {
      boolean value = this._preventCall;
      this._preventCall = true;
      super.focusChangeNotify(action);
      this._preventCall = value;
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

   private void initiateSearch(String pattern) {
      this._listField.initiateSearch(pattern);
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent event) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public boolean isDirty() {
      return false;
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean keyControl(char key, int status, int time) {
      return key == 128 ? super.keyControl(key, status, time) : false;
   }

   public void linkToField(KeywordFilterCollectionListField field) {
      this._listField = field;
      field.setInputProcessor(this);
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      return amount;
   }

   @Override
   protected void paint(Graphics graphics) {
      super.paint(graphics);
      if (this._drawFocusIndicator) {
         this.getFocusRect(this._focusRect);
         this.drawFocus(graphics, true);
      }
   }

   private void performSearch(String newSearchPattern) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void redoSearch(boolean force) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void resetSearch() {
      this._searchPattern = null;
      this.redoSearch(false);
      this.setText(null);
   }

   public void setAllowSpacesInSearchText(boolean allowSpacesInSearchText) {
      this._allowSpacesInSearchText = allowSpacesInSearchText;
      if (!this._allowSpacesInSearchText && this._searchPattern != null) {
         this._searchPattern = this._searchPattern.replace(' ', '_');
      }
   }

   public void acceptYomiSearch(boolean acceptYomiSearch) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBaseText(String baseText) {
      boolean sameText = StringUtilities.strEqual(this._baseText, baseText);
      this._baseText = baseText;
      if (!sameText) {
         this.updateText();
      }
   }

   public void setDisplayUpperCaseCharsInSearchText(boolean displayUpperCaseCharsInSearchText) {
      this._displayUpperCaseCharsInSearchText = displayUpperCaseCharsInSearchText;
      if (!this._displayUpperCaseCharsInSearchText && this._searchPattern != null) {
         this._searchPattern = this._searchPattern.toLowerCase();
      }
   }

   public void setFocusIndicatorEnabled(boolean enabled) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSearchPattern(String newPattern) {
      this.setSearchPattern(newPattern, true);
   }

   private void setSearchPattern(String newPattern, boolean forceUpdate) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void updateInputStyle() {
      super.updateInputStyle();
      SLControlObject controlObject = (SLControlObject)this.getInputContext().getInputMethodControlObject();
      KeywordFilterList list = this._listField.getKeywordFilterList();
      if (list != null) {
         controlObject.actionPerformed(38, list.getSearcher());
      }
   }

   private void updateText() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
