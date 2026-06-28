package net.rim.device.internal.media.metadata;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.media.control.MetaDataControl;
import net.rim.device.api.media.MetaDataObject;
import net.rim.device.api.media.control.BinaryMetaDataControl;

public class MetaDataControlImpl implements MetaDataControl, BinaryMetaDataControl {
   private Hashtable _metaData = (Hashtable)(new Object());
   private MetaDataObject[] _binaryObjects = new MetaDataObject[0];

   public void put(String key, String value) {
      if (value != null) {
         synchronized (this._metaData) {
            this._metaData.put(key, value);
         }
      }
   }

   public void addObject(MetaDataObject object) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int size() {
      return this._metaData.size();
   }

   public boolean containsKey(String key) {
      return this._metaData.containsKey(key);
   }

   @Override
   public String[] getKeys() {
      synchronized (this._metaData) {
         String[] keys = new String[this._metaData.size()];
         int i = 0;
         Enumeration e = this._metaData.keys();

         while (e.hasMoreElements()) {
            keys[i++] = (String)e.nextElement();
         }

         return keys;
      }
   }

   @Override
   public String getKeyValue(String key) {
      if (key != null) {
         String value = (String)this._metaData.get(key);
         if (value != null) {
            return value;
         }
      }

      throw new Object();
   }

   @Override
   public MetaDataObject[] getMetaDataObjects() {
      return this._binaryObjects;
   }
}
