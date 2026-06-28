package net.rim.device.api.ui.theme;

import java.util.Enumeration;
import net.rim.device.resources.Resource;

class DefaultResourceFetcher implements ResourceFetcher {
   private Resource _resources;
   private String _moduleName;
   private boolean _initialized;

   DefaultResourceFetcher() {
   }

   DefaultResourceFetcher(String moduleName) {
   }

   @Override
   public void setResourcesFromModule(String moduleName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public Resource getResources() {
      return this._resources;
   }

   @Override
   public Enumeration listResources() {
      this.checkState();
      Enumeration e = this._resources.getResourceKeys();
      return (Enumeration)(e != null ? e : new Object());
   }

   @Override
   public byte[] fetchResource(String name) {
      this.checkState();
      return this._resources.getResource(name);
   }

   @Override
   public String getBaseURL() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void checkState() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
