package net.rim.device.internal.browser.markup;

import java.io.IOException;

public final class MarkupWrongMIMEType extends IOException {
   private String _newMIMEType;

   public MarkupWrongMIMEType(String var1) {
      this._newMIMEType = var1;
   }

   public final String getNewMIMEType() {
      return this._newMIMEType;
   }
}
