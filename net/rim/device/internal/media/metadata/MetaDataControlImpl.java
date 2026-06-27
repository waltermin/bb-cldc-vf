package net.rim.device.internal.media.metadata;

import java.util.Hashtable;
import javax.microedition.media.control.MetaDataControl;
import net.rim.device.api.media.MetaDataObject;
import net.rim.device.api.media.control.BinaryMetaDataControl;

public class MetaDataControlImpl implements MetaDataControl, BinaryMetaDataControl {
   private Hashtable _metaData = (Hashtable)(new Object());
   private MetaDataObject[] _binaryObjects = new MetaDataObject[0];

   public void put(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void addObject(MetaDataObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int size() {
      return this._metaData.size();
   }

   public boolean containsKey(String var1) {
      return this._metaData.containsKey(var1);
   }

   @Override
   public String[] getKeys() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getKeyValue(String var1) {
      if (var1 != null) {
         Object var2 = this._metaData.get(var1);
         if (var2 != null) {
            return (String)var2;
         }
      }

      throw new Object();
   }

   @Override
   public MetaDataObject[] getMetaDataObjects() {
      return this._binaryObjects;
   }
}
