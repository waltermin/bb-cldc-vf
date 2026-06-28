package net.rim.device.internal.media;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.file.FileConnection;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.io.file.FileHandleProvider;

public class FileDataSource extends DataSourceImpl implements SourceInformationProvider {
   private FileConnection _connection;

   public void close() {
      try {
         this.stop();
      } catch (IOException var2) {
      }
   }

   @Override
   public int getFileHandle() {
      if (super._is instanceof FileHandleProvider) {
         FileHandleProvider fhp = (FileHandleProvider)super._is;
         if (!fhp.isInputCiphering()) {
            return fhp.getFileHandle();
         }
      }

      return -1;
   }

   @Override
   public void connect() {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   @Override
   public void disconnect() {
      if (super._connected) {
         if (super._started) {
            try {
               this.stop();
            } catch (IOException var3) {
            }
         }

         try {
            if (this._connection != null) {
               this._connection.close();
            }
         } catch (IOException var2) {
         }

         super._connected = false;
      }
   }

   @Override
   public void start() {
      if (super._connected) {
         if (!super._started) {
            if (this._connection != null) {
               super._is = this._connection.openInputStream();
               super._started = true;
            } else {
               throw new IOException("Connection is null");
            }
         }
      } else {
         throw new IllegalStateException("Not connected");
      }
   }

   public FileDataSource(InputStream stream, String contentType, long contentLength) {
      super(null);
      this.setInputStream(stream);
      if (contentType != null) {
         super._contentType = StringUtilities.toLowerCase(contentType, 1701707776);
      }

      super._contentLength = contentLength;
      super._started = true;
      super._connected = true;
   }

   public FileDataSource(String locator) {
      super(locator);
   }
}
