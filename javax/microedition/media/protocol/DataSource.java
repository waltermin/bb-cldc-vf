package javax.microedition.media.protocol;

import javax.microedition.media.Control;
import javax.microedition.media.Controllable;

public class DataSource implements Controllable {
   private String _locator;

   public DataSource(String var1) {
      this._locator = var1;
   }

   public String getLocator() {
      return this._locator;
   }

   public String getContentType() {
      throw null;
   }

   public void connect() {
      throw null;
   }

   public void disconnect() {
      throw null;
   }

   public void start() {
      throw null;
   }

   public void stop() {
      throw null;
   }

   public SourceStream[] getStreams() {
      throw null;
   }

   @Override
   public Control getControl(String var1) {
      throw null;
   }

   @Override
   public Control[] getControls() {
      throw null;
   }
}
