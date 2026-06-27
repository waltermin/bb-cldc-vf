package net.rim.device.api.ui.component;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.FilterStatusListener;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.collection.util.KeywordFilterList;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.ui.Ui;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.im.InputMethodRequests;
import net.rim.vm.TraceBack;

public class KeywordFilterCollectionListField extends CollectionListField implements FilterStatusListener {
   protected KeywordFilterList _list;
   private int _searchesInProgress;
   private KeywordFilteredListFinder _inputProcessor;

   public void setSize(int var1, int var2, boolean var3) {
      if (var3 && this.getListSize() == 0) {
         var2 = 0;
      }

      super.setSize(var1, var2);
   }

   public void setKeywordFilterList(KeywordFilterList var1) {
      if (var1 != this._list) {
         this._list = var1;
         super.setList(var1);
      }
   }

   public KeywordFilterList getKeywordFilterList() {
      return this._list;
   }

   void initiateSearch(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void setInputProcessor(KeywordFilteredListFinder var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void filterStarted() {
   }

   @Override
   public void filterDone(boolean var1) {
      if (this._searchesInProgress > 0) {
         this._searchesInProgress--;
         if (this._searchesInProgress == 0) {
            this.updateList();
         }
      }
   }

   @Override
   public void dispatchEvent(Event var1) {
      if (this._inputProcessor == null) {
         super.dispatchEvent(var1);
      } else if (var1.getID() == 1004) {
         var1.setSource(this._inputProcessor);
         this._inputProcessor.dispatchEvent(var1);
      } else {
         this._inputProcessor.dispatchEvent(var1);
      }
   }

   @Override
   protected void doUpdateList() {
      if (this._searchesInProgress == 0) {
         super.doUpdateList();
      }

      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      return this._inputProcessor != null ? this._inputProcessor.keyChar(var1, var2, var3) : super.keyChar(var1, var2, var3);
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      return this._inputProcessor != null ? this._inputProcessor.keyControl(var1, var2, var3) : super.keyControl(var1, var2, var3);
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      return this._inputProcessor != null ? this._inputProcessor.processKeyEvent(var1, var2, var3, var4) : super.processKeyEvent(var1, var2, var3, var4);
   }

   @Override
   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      return this._inputProcessor != null
         ? this._inputProcessor.processNavigationEvent(var1, var2, var3, var4, var5)
         : super.processNavigationEvent(var1, var2, var3, var4, var5);
   }

   @Override
   public void reset(Collection var1) {
      if (this._searchesInProgress == 0) {
         super.reset(var1);
      }
   }

   @Override
   public InputMethodRequests getInputMethodRequests() {
      return this._inputProcessor == null ? super.getInputMethodRequests() : this._inputProcessor.getInputMethodRequests();
   }

   public KeywordFilterCollectionListField(ReadableList var1, ListFieldCallback var2, long var3) {
      super(var1, var2, var3);
      this._list = (KeywordFilterList)var1;
   }

   @Override
   public void setSize(int var1, int var2) {
      this.setSize(var1, var2, true);
   }

   public KeywordFilterCollectionListField(ReadableList var1, ListFieldCallback var2) {
      this(var1, var2, 0);
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._list = (KeywordFilterList)var1;
   }
}
