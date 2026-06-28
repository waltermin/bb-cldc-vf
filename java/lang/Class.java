package java.lang;

import java.io.InputStream;

public final class Class {
   private int _VMClassRef;
   private int _VMThread;
   private int _arrayType;
   private int _arrayNumDims;
   private int _arrayNext;

   private Class() {
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Class forName(String className) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final native Class forName0(String var0);

   private static final native Class forNameDims(Class var0, char var1, int var2);

   private final native Object newInstance0();

   public final Object newInstance() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final native boolean isInstance(Object var1);

   public final native boolean isAssignableFrom(Class var1);

   public final native boolean isInterface();

   public final boolean isArray() {
      return this.getArrayType() != 'V';
   }

   private final native char getArrayType();

   private final native int getArrayDims();

   public final String getName() {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final native String getClassName();

   private final native String getPackageName();

   public final InputStream getResourceAsStream(String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final String fixResourceName(String name) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
