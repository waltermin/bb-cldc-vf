package net.rim.device.internal.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldVisitor;
import net.rim.device.api.util.IntStack;

public class TextScrapeVisitor implements FieldVisitor {
   private StringBuffer _buffer = (StringBuffer)(new Object());
   private boolean _addLineFeed = true;
   private IntStack stack = (IntStack)(new Object());

   public StringBuffer getStringBuffer() {
      return this._buffer;
   }

   @Override
   public boolean visit(Field var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }
}
