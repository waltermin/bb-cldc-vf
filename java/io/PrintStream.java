package java.io;

public class PrintStream extends OutputStream {
   private boolean trouble;
   private OutputStreamWriter charOut;
   private OutputStream byteOut;
   private boolean closing = false;

   public PrintStream(OutputStream var1) {
      if (var1 == null) {
         throw new NullPointerException();
      }

      this.byteOut = var1;
      this.charOut = new OutputStreamWriter(var1);
   }

   private void ensureOpen() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void flush() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean checkError() {
      if (this.charOut != null) {
         this.flush();
      }

      return this.trouble;
   }

   protected void setError() {
      this.trouble = true;
   }

   @Override
   public void write(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void write(char[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void write(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void newLine() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void print(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void print(char var1) {
      this.write(String.valueOf(var1));
   }

   public void print(int var1) {
      this.write(String.valueOf(var1));
   }

   public void print(long var1) {
      this.write(String.valueOf(var1));
   }

   public void print(float var1) {
      this.write(String.valueOf(var1));
   }

   public void print(double var1) {
      this.write(String.valueOf(var1));
   }

   public void print(char[] var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void print(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void print(Object var1) {
      this.write(String.valueOf(var1));
   }

   public void println() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void println(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(char var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(float var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(double var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(char[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void println(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
