package javax.microedition.media;

class SystemTimeBase implements TimeBase {
   private static long offset;

   @Override
   public long getTime() {
      return System.currentTimeMillis() * 1000 - offset;
   }
}
