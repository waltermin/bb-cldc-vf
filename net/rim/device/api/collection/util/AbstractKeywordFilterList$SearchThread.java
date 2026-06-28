package net.rim.device.api.collection.util;

final class AbstractKeywordFilterList$SearchThread extends Thread {
   private KeywordSearcher _searcher;
   private final AbstractKeywordFilterList this$0;

   AbstractKeywordFilterList$SearchThread(AbstractKeywordFilterList _1, KeywordSearcher searcher) {
      this.this$0 = _1;
      this._searcher = searcher;

      try {
         this.setPriority(this.getPriority() - 1);
      } catch (IllegalArgumentException var4) {
      }
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void swapRequests() {
      this.this$0._currentSearchRequest.done();
      AbstractKeywordFilterList$SearchRequest tmp = this.this$0._currentSearchRequest;
      this.this$0._currentSearchRequest = this.this$0._nextSearchRequest;
      this.this$0._nextSearchRequest = tmp;
   }
}
