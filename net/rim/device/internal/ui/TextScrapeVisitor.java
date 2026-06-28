package net.rim.device.internal.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldVisitor;
import net.rim.device.api.util.IntStack;

public class TextScrapeVisitor implements FieldVisitor {
   private StringBuffer _buffer = new StringBuffer();
   private boolean _addLineFeed = true;
   private IntStack stack = new IntStack();

   public StringBuffer getStringBuffer() {
      return this._buffer;
   }

   @Override
   public boolean visit(Field field, int state) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }
}
