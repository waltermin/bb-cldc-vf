package javax.microedition.media;

import java.io.InputStream;
import javax.microedition.media.protocol.DataSource;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;

public final class Manager {
   public static final String TONE_DEVICE_LOCATOR;
   public static final String MIDI_DEVICE_LOCATOR;
   private static final String PME_DEVICE_LOCATOR;
   private static final String RECORD_DEVICE_LOCATOR;
   private static final String NOKIA_RINGTONE_DEVICE_LOCATOR;
   private static final String TONE_CONTENT_TYPE;
   private static SystemTimeBase _systemTimeBase;
   private static Player _playTonePlayer;

   private Manager() {
   }

   public static final String[] getSupportedContentTypes(String protocol) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String[] getSupportedProtocols(String content_type) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final Player createPlayer(String locator) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Player createPlayer(DataSource source) {
      assertPermission();
      return createPlayerImpl(source);
   }

   public static final Player createPlayer(InputStream stream, String type) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final Player createPlayerImpl(DataSource source) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void playTone(int note, int duration, int volume) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final TimeBase getSystemTimeBase() {
      return _systemTimeBase;
   }

   private static final void assertPermission() {
      ApplicationControl.assertMediaPermitted(true, CommonResource.getBundle(), 10177);
   }
}
