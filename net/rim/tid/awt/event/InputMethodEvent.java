package net.rim.tid.awt.event;

import net.rim.tid.awt.Event;
import net.rim.tid.im.ISupplementaryInputData;
import net.rim.tid.itie.IComponent;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.TextHitInfo;

public final class InputMethodEvent extends Event {
   private AttributedString _text;
   private int _committedCharacterCount;
   private int _convertedCharacterCount;
   private TextHitInfo _caret;
   private TextHitInfo _visiblePosition;
   private int _modifiers;
   private long _attribTextMask;
   private boolean _overrideCommittedTextAttributes;
   private ISupplementaryInputData _supplementaryInputData;
   private byte _caretShape;
   public static final int INPUT_METHOD_FIRST;
   public static final int INPUT_METHOD_TEXT_CHANGED;
   public static final int CARET_POSITION_CHANGED_UNCOMMITTED;
   public static final int CARET_POSITION_CHANGED_COMMITTED;
   public static final int INPUT_METHOD_RESTART;
   public static final int CARET_SHAPE_CHANGED;
   public static final int CARET_POSITION_CHANGED;
   public static final int INPUT_METHOD_LAST;

   public InputMethodEvent(IComponent var1, int var2, int var3, AttributedString var4, long var5, int var7, int var8, TextHitInfo var9, TextHitInfo var10) {
      super(var1, var2, Event.INPUT_METHOD_EVENT_MASK);
      this.init(var1, var2, var3, var4, var5, var7, var8, var9, var10);
   }

   public InputMethodEvent(
      IComponent var1, int var2, int var3, AttributedString var4, long var5, int var7, int var8, TextHitInfo var9, TextHitInfo var10, int var11
   ) {
      super(var1, var2, var11 | Event.INPUT_METHOD_EVENT_MASK);
      this.init(var1, var2, var3, var4, var5, var7, var8, var9, var10);
   }

   public final void init(IComponent var1, int var2, int var3, AttributedString var4, long var5, int var7, int var8, TextHitInfo var9, TextHitInfo var10) {
      this.init(var1, var2, var3, var4, var5, var7, var8, var9, var10, true);
   }

   public final void init(
      IComponent var1, int var2, int var3, AttributedString var4, long var5, int var7, int var8, TextHitInfo var9, TextHitInfo var10, boolean var11
   ) {
      this.init(var1, var2, var3, var4, var5, var7, var8, var9, var10, var11, (byte)4);
   }

   public final void init(
      IComponent var1, int var2, int var3, AttributedString var4, long var5, int var7, int var8, TextHitInfo var9, TextHitInfo var10, boolean var11, byte var12
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public InputMethodEvent(IComponent var1, int var2, TextHitInfo var3, TextHitInfo var4) {
      this(var1, var2, 0, null, 0, 0, 0, var3, var4, 0);
   }

   public final AttributedString getText() {
      return this._text;
   }

   public final long getTextMask() {
      return this._attribTextMask;
   }

   public final int getModifiers() {
      return this._modifiers;
   }

   public final int getCommittedCharacterCount() {
      return this._committedCharacterCount;
   }

   public final int getConvertedCharacterCount() {
      return this._convertedCharacterCount;
   }

   public final TextHitInfo getCaret() {
      return this._caret;
   }

   public final TextHitInfo getVisiblePosition() {
      return this._visiblePosition;
   }

   public final boolean isOverrideCommittedTextAttributes() {
      return this._overrideCommittedTextAttributes;
   }

   public final void setCommittedTextAttributesOverride(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final StringBuffer getOriginatingKeys() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getOriginatingKeys() : null;
   }

   public final int getOriginatingKeysCommittedCount() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getOriginatingKeysCommittedCount() : 0;
   }

   public final String[] getSupplementarySearchReadings() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getSupplementarySearchReadings() : null;
   }

   public final StringBuffer getAlternativeIdeographicReading() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getAlternativeIdeographicReading() : null;
   }

   public final int getAlternativeReadingCommittedCharacterCount() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getAlternativeReadingCommittedCharacterCount() : 0;
   }

   public final void setSupplementaryInputData(ISupplementaryInputData var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final StringBuffer getOriginalReading() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getOriginalReading() : null;
   }

   public final int getOriginalReadingCommitedCharacterCount() {
      return this._supplementaryInputData != null ? this._supplementaryInputData.getOriginalReadingCommitedCharacterCount() : 0;
   }

   public final byte getCaretShape() {
      return this._caretShape;
   }

   public final void setCaretShape(byte var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
