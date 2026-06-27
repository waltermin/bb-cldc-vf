package net.rim.device.api.ui.autotext;

import java.util.Enumeration;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.ReadableSet;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.ApplicationRegistry;

public class AutoText implements ReadableSet, CollectionEventSource {
   protected static final long KEY_NAME;
   public static final int SMART_CASE;
   public static final int SPECIFIED_CASE;

   public static final AutoText getAutoText() {
      return (AutoText)ApplicationRegistry.getApplicationRegistry().waitFor(-8979623067881713245L);
   }

   public Object add(String var1, String var2, int var3, Locale var4) {
      throw null;
   }

   public void remove(String var1, Locale var2) {
      throw null;
   }

   public String getReplacedString(Object var1) {
      throw null;
   }

   public String getReplacementStringPattern(Object var1) {
      throw null;
   }

   public int getReplacementCase(Object var1) {
      throw null;
   }

   public int getLocaleCode(Object var1) {
      throw null;
   }

   public Object checkWord(String var1) {
      throw null;
   }

   public Enumeration getAllKeys() {
      throw null;
   }

   public int getWordCount() {
      throw null;
   }

   public String[] getMacroChoices() {
      throw null;
   }

   public String getMacroText(int var1) {
      throw null;
   }

   public boolean isClauseSeparator(char var1) {
      throw null;
   }

   public String getClauseSeparatorString() {
      throw null;
   }

   public boolean isNoAutoPeriodCharacter(char var1) {
      throw null;
   }

   public String getNoAutoPeriodCharacterString() {
      throw null;
   }

   public boolean isSentenceTerminator(char var1) {
      throw null;
   }

   public int getDataVersion() {
      return 0;
   }

   public void setDataVersion(int var1) {
   }

   @Override
   public int getElements(Object[] var1) {
      throw null;
   }

   @Override
   public Enumeration getElements() {
      throw null;
   }

   @Override
   public boolean contains(Object var1) {
      throw null;
   }

   @Override
   public int size() {
      throw null;
   }

   @Override
   public void removeCollectionListener(Object var1) {
      throw null;
   }

   @Override
   public void addCollectionListener(Object var1) {
      throw null;
   }
}
