package net.rim.device.api.ui.component;

import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class KeywordFilteredField extends VerticalFieldManager {
   private KeywordFilteredListFinder _finder;
   private KeywordFilterCollectionListField _listField;
   private KeywordFilteredField$ListFieldCallbackImpl _listFieldCallback;
   private KeywordProvider _keywordProvider;

   public KeywordFilteredField(ReadableList var1, KeywordProvider var2, String var3) {
      this._keywordProvider = var2;
      this._listFieldCallback = new KeywordFilteredField$ListFieldCallbackImpl();
      this._finder = new KeywordFilteredListFinder(null, var3, false);
      this._listField = new KeywordFilterCollectionListField(
         (ReadableList)(new Object(var1, new KeywordFilteredField$KeywordIndexerHelperImpl(this))), this._listFieldCallback
      );
      this.add(this._finder);
      this._finder.linkToField(this._listField);
      this.add(this._listField);
      this._finder.resetSearch();
   }

   @Override
   public void delete(Field var1) {
      throw new Object();
   }

   @Override
   public void deleteRange(int var1, int var2) {
      throw new Object();
   }
}
