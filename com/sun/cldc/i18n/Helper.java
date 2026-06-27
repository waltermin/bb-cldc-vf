package com.sun.cldc.i18n;

import com.sun.cldc.i18n.j2me.TextProcessingRegistry;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import net.rim.device.api.util.StringUtilities;
import net.rim.vm.WeakReference;

public final class Helper {
   private static String ISO8859_1;
   private static String ASCII;
   private static String Universal;
   private static String defaultEncoding;
   private static String defaultMEPath;
   private static String _readerName;
   private static Class _readerClass;
   private static String _writerName;
   private static Class _writerClass;
   private static String _lastReaderEncoding;
   private static WeakReference _lastReaderWR;
   private static Object _lastReaderLock;
   private static WeakReference _universalReaderWR;
   private static Helper$LocalStrongReferences _strongReferences;
   public static final long PERSISTENT_CONTENT_LISTENER_ID;
   private static String _lastWriterEncoding;
   private static WeakReference _lastWriterWR;
   private static Object _lastWriterLock;
   private static String[] ALIAS_ASCII;
   private static String[] ALIAS_UTF8;
   private static String[] ALIAS_ISO8859_1;
   private static String[] ALIAS_UTF16BE;
   private static String[] ALIAS_UTF16LE;
   private static String[] ALIAS_UTF7;

   public static final int getSuggestedLocale(String var0) {
      return TextProcessingRegistry.getInstance().getSuggestedLocale(var0);
   }

   public static final String getSuggestedTypeface(String var0) {
      return TextProcessingRegistry.getInstance().getSuggestedTypeface(var0);
   }

   public static final String getSuggestedTypeface(int var0) {
      return TextProcessingRegistry.getInstance().getSuggestedTypeface(var0);
   }

   public static final String getSuggestedEncoding(int var0) {
      return TextProcessingRegistry.getInstance().getSuggestedEncoding(var0);
   }

   public static final String getDefaultEncoding() {
      return defaultEncoding;
   }

   public static final boolean isSupportedEncoding(String var0) {
      String var1 = resolveAlias(var0);
      return var1 != null ? TextProcessingRegistry.getInstance().isSupported(var1, 0) : false;
   }

   public static final String[] getSupportedEncodings() {
      return TextProcessingRegistry.getInstance().getSupported(0);
   }

   public static final Reader getStreamReader(InputStream var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Reader getStreamReader(InputStream var0, String var1) {
      if (var0 != null && var1 != null) {
         var1 = resolveAlias(var1);
         StreamReader var2 = getStreamReaderPrim(mapEncoding(var1));
         return var2.open(var0, var1);
      } else {
         throw new NullPointerException();
      }
   }

   private static final StreamReader getStreamReaderPrim(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Writer getStreamWriter(OutputStream var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Writer getStreamWriter(OutputStream var0, String var1) {
      if (var0 != null && var1 != null) {
         var1 = resolveAlias(var1);
         StreamWriter var2 = getStreamWriterPrim(mapEncoding(var1));
         return var2.open(var0, var1);
      } else {
         throw new NullPointerException();
      }
   }

   private static final StreamWriter getStreamWriterPrim(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] charToByteArray(char[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object byteToCharArray(byte[] var0, int var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] charToByteArray(char[] var0, int var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final String mapEncoding(String var0) {
      if (StringUtilities.strEqualIgnoreCase(defaultEncoding, var0, 1701707776)) {
         return Universal;
      } else {
         return TextProcessingRegistry.getInstance().isSupported(var0, 0) ? Universal : var0;
      }
   }

   private static final int isUniversal(String var0) {
      return TextProcessingRegistry.getInstance().getTextProcessingDataID(var0, 0);
   }

   private static final String resolveAlias(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
