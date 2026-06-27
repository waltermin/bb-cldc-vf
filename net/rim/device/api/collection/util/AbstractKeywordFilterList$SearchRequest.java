package net.rim.device.api.collection.util;

import net.rim.device.api.collection.FilterStatusListener;

final class AbstractKeywordFilterList$SearchRequest {
   Object _searchCriteria = null;
   FilterStatusListener _listener = null;
   boolean _empty = true;

   final void setup(Object var1, FilterStatusListener var2) {
      this._searchCriteria = var1;
      this._listener = var2;
      this._empty = false;
   }

   final boolean isEmpty() {
      return this._empty;
   }

   final void done() {
      this.setup(null, null);
      this._empty = true;
   }
}
