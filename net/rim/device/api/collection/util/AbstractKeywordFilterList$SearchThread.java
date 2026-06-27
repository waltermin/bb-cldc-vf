package net.rim.device.api.collection.util;

final class AbstractKeywordFilterList$SearchThread extends Thread {
   private KeywordSearcher _searcher;
   private final AbstractKeywordFilterList this$0;

   AbstractKeywordFilterList$SearchThread(AbstractKeywordFilterList var1, KeywordSearcher var2) {
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void swapRequests() {
      this.this$0._currentSearchRequest.done();
      AbstractKeywordFilterList$SearchRequest var1 = this.this$0._currentSearchRequest;
      this.this$0._currentSearchRequest = this.this$0._nextSearchRequest;
      this.this$0._nextSearchRequest = var1;
   }
}
