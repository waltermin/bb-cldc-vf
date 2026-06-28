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

   public static final int getSuggestedLocale(String enc) {
      return TextProcessingRegistry.getInstance().getSuggestedLocale(enc);
   }

   public static final String getSuggestedTypeface(String enc) {
      return TextProcessingRegistry.getInstance().getSuggestedTypeface(enc);
   }

   public static final String getSuggestedTypeface(int localeCode) {
      return TextProcessingRegistry.getInstance().getSuggestedTypeface(localeCode);
   }

   public static final String getSuggestedEncoding(int localeCode) {
      return TextProcessingRegistry.getInstance().getSuggestedEncoding(localeCode);
   }

   public static final String getDefaultEncoding() {
      return defaultEncoding;
   }

   public static final boolean isSupportedEncoding(String enc) {
      String alias = resolveAlias(enc);
      return alias != null ? TextProcessingRegistry.getInstance().isSupported(alias, 0) : false;
   }

   public static final String[] getSupportedEncodings() {
      return TextProcessingRegistry.getInstance().getSupported(0);
   }

   public static final Reader getStreamReader(InputStream is) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Reader getStreamReader(InputStream is, String name) {
      if (is != null && name != null) {
         name = resolveAlias(name);
         StreamReader fr = getStreamReaderPrim(mapEncoding(name));
         return fr.open(is, name);
      } else {
         throw new NullPointerException();
      }
   }

   private static final StreamReader getStreamReaderPrim(String name) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Writer getStreamWriter(OutputStream os) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Writer getStreamWriter(OutputStream os, String name) {
      if (os != null && name != null) {
         name = resolveAlias(name);
         StreamWriter sw = getStreamWriterPrim(mapEncoding(name));
         return sw.open(os, name);
      } else {
         throw new NullPointerException();
      }
   }

   private static final StreamWriter getStreamWriterPrim(String name) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] charToByteArray(char[] buffer, int offset, int length) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Object byteToCharArray(byte[] buffer, int offset, int length, String enc) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] charToByteArray(char[] buffer, int offset, int length, String enc) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final String mapEncoding(String enc) {
      if (StringUtilities.strEqualIgnoreCase(defaultEncoding, enc, 1701707776)) {
         return Universal;
      } else {
         return TextProcessingRegistry.getInstance().isSupported(enc, 0) ? Universal : enc;
      }
   }

   private static final int isUniversal(String enc) {
      return TextProcessingRegistry.getInstance().getTextProcessingDataID(enc, 0);
   }

   private static final String resolveAlias(String enc) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
