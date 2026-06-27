package net.rim.device.internal.media.metadata;

import javax.microedition.media.control.MetaDataControl;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.EncodedImage;

public class MetadataHandlerFactory {
   protected static final long GUID;
   private static MetadataHandlerFactory _instance;

   MetadataHandlerFactory() {
   }

   public static MetaDataControl extract(EncodedImage var0) {
      if (_instance == null) {
         ApplicationRegistry var1 = ApplicationRegistry.getApplicationRegistry();
         _instance = (MetadataHandlerFactory)var1.waitFor(-3049172664916265609L);
      }

      return _instance.extractMetadata(var0);
   }

   protected MetaDataControl extractMetadata(EncodedImage var1) {
      throw null;
   }
}
