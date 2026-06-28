package net.rim.device.internal.util;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMPersistentStore;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.api.util.LongEnumeration;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.internal.deviceoptions.DeviceOptions;
import net.rim.device.internal.deviceoptions.OptionsProvider;
import net.rim.device.internal.deviceoptions.OptionsProviderChangeListener;
import net.rim.device.internal.deviceoptions.OptionsProviderChangeSource;

public class OptionsRegistry implements OptionsProvider, OptionsProviderChangeSource {
   private final long _guid;
   private PersistentObject _persistent;
   private LongHashtable _persistentData;
   private Object[] _listeners;
   private OptionsProviderChangeListener _syncListener;
   private final LongHashtable _parameterDefinitions = (LongHashtable)(new Object());
   private static final int TAG;
   private static final int TYPE_STRING;
   private static final int TYPE_INTEGER;
   private static final int TYPE_LONG;
   private static final int TYPE_BYTE_ARRAY;
   private static final int TYPE_DOUBLE;
   private static final int TYPE_BOOLEAN;
   private static final int TYPE_CHAR;

   public void addOptionsRegistryChangeListener(OptionsRegistry$Listener listener) {
      this._listeners = ListenerUtilities.addListener(this._listeners, listener);
   }

   protected void define(long key, OptionsRegistry$ParameterDefinition def) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public char getChar(long key) {
      return this.getCharParam(key).getValue();
   }

   @Override
   public int getUID() {
      return (int)this._guid;
   }

   public boolean getBoolean(long key) {
      return this.getBooleanParam(key).getValue();
   }

   public OptionsRegistry$BooleanParameter getBooleanParam(long key) {
      OptionsRegistry$BooleanParameter param = (OptionsRegistry$BooleanParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$BooleanParameterDefinition pd = (OptionsRegistry$BooleanParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setBoolean(key, pd._default);
         param = (OptionsRegistry$BooleanParameter)this.get(key);
      }

      return param;
   }

   public byte[] getByteArray(long key) {
      return this.getByteArrayParam(key).getValue();
   }

   public OptionsRegistry$ByteArrayParameter getByteArrayParam(long key) {
      OptionsRegistry$ByteArrayParameter param = (OptionsRegistry$ByteArrayParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$ByteArrayParameterDefinition pd = (OptionsRegistry$ByteArrayParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setByteArray(key, pd._default);
         param = (OptionsRegistry$ByteArrayParameter)this.get(key);
      }

      return param;
   }

   public double getDouble(long key) {
      return this.getDoubleParam(key).getValue();
   }

   public OptionsRegistry$DoubleParameter getDoubleParam(long key) {
      OptionsRegistry$DoubleParameter param = (OptionsRegistry$DoubleParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$DoubleParameterDefinition pd = (OptionsRegistry$DoubleParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setDouble(key, pd._default);
         param = (OptionsRegistry$DoubleParameter)this.get(key);
      }

      return param;
   }

   public int getInt(long key) {
      return this.getIntParam(key).getValue();
   }

   public OptionsRegistry$IntParameter getIntParam(long key) {
      OptionsRegistry$IntParameter param = (OptionsRegistry$IntParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$IntParameterDefinition pd = (OptionsRegistry$IntParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setInt(key, pd._default);
         param = (OptionsRegistry$IntParameter)this.get(key);
      }

      return param;
   }

   public LongEnumeration getKeys() {
      return this._persistentData.keys();
   }

   public long getLong(long key) {
      return this.getLongParam(key).getValue();
   }

   public OptionsRegistry$LongParameter getLongParam(long key) {
      OptionsRegistry$LongParameter param = (OptionsRegistry$LongParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$LongParameterDefinition pd = (OptionsRegistry$LongParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setLong(key, pd._default);
         param = (OptionsRegistry$LongParameter)this.get(key);
      }

      return param;
   }

   protected boolean isBackupParam(long key) {
      return true;
   }

   public void setChar(long key, char value) {
      synchronized (this._persistent) {
         OptionsRegistry$CharParameterDefinition pd = (OptionsRegistry$CharParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$CharParameter param = (OptionsRegistry$CharParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$CharParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public String getString(long key) {
      return this.getStringParam(key).getValue();
   }

   public OptionsRegistry$StringParameter getStringParam(long key) {
      OptionsRegistry$StringParameter param = (OptionsRegistry$StringParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$StringParameterDefinition pd = (OptionsRegistry$StringParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setString(key, pd._default);
         param = (OptionsRegistry$StringParameter)this.get(key);
      }

      return param;
   }

   public OptionsRegistry$CharParameter getCharParam(long key) {
      OptionsRegistry$CharParameter param = (OptionsRegistry$CharParameter)this.get(key);
      if (param == null) {
         OptionsRegistry$CharParameterDefinition pd = (OptionsRegistry$CharParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null) {
            throw new Object();
         }

         this.setChar(key, pd._default);
         param = (OptionsRegistry$CharParameter)this.get(key);
      }

      return param;
   }

   public void removeOptionsRegistryChangeListener(OptionsRegistry$Listener listener) {
      this._listeners = ListenerUtilities.removeListener(this._listeners, listener);
   }

   public void setString(long key, String value) {
      synchronized (this._persistent) {
         OptionsRegistry$StringParameterDefinition pd = (OptionsRegistry$StringParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$StringParameter param = (OptionsRegistry$StringParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$StringParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public void setBoolean(long key, boolean value) {
      synchronized (this._persistent) {
         OptionsRegistry$BooleanParameterDefinition pd = (OptionsRegistry$BooleanParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$BooleanParameter param = (OptionsRegistry$BooleanParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$BooleanParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public void setDouble(long key, double value) {
      synchronized (this._persistent) {
         OptionsRegistry$DoubleParameterDefinition pd = (OptionsRegistry$DoubleParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$DoubleParameter param = (OptionsRegistry$DoubleParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$DoubleParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public void setInt(long key, int value) {
      synchronized (this._persistent) {
         OptionsRegistry$IntParameterDefinition pd = (OptionsRegistry$IntParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$IntParameter param = (OptionsRegistry$IntParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$IntParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public void setLong(long key, long value) {
      synchronized (this._persistent) {
         OptionsRegistry$LongParameterDefinition pd = (OptionsRegistry$LongParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$LongParameter param = (OptionsRegistry$LongParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$LongParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   public void setByteArray(long key, byte[] value) {
      synchronized (this._persistent) {
         OptionsRegistry$ByteArrayParameterDefinition pd = (OptionsRegistry$ByteArrayParameterDefinition)this._parameterDefinitions.get(key);
         if (pd == null || !pd.isValid(value)) {
            throw new Object();
         }

         OptionsRegistry$ByteArrayParameter param = (OptionsRegistry$ByteArrayParameter)this.get(key);
         if (param == null) {
            param = (OptionsRegistry$ByteArrayParameter)(new Object());
            this._persistentData.put(key, param);
         }

         param.setValue(value);
         this._persistent.commit();
      }

      this.notifyListeners(key);
   }

   @Override
   public void setOptionsProviderChangeListener(OptionsProviderChangeListener listener) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setOptionsData(DataBuffer buffer) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void getOptionsData(DataBuffer buffer) {
      throw new RuntimeException("cod2jar: type check");
   }

   private void notifyListeners(long key) {
      if (this._syncListener != null && this.isBackupParam(key)) {
         this._syncListener.optionsProviderChanged(this);
      }

      Object[] listeners = this._listeners;
      if (listeners != null) {
         for (int index = 0; index < listeners.length; index++) {
            ((OptionsRegistry$Listener)listeners[index]).onOptionsRegistryChange(key);
         }
      }
   }

   private Object get(long key) {
      synchronized (this._persistent) {
         return this._persistentData.get(key);
      }
   }

   public OptionsRegistry(long guid) {
      this._guid = guid;
      this._persistent = RIMPersistentStore.getPersistentObject(guid);
      synchronized (this._persistent) {
         this._persistentData = (LongHashtable)this._persistent.getContents();
         if (this._persistentData == null) {
            this._persistentData = (LongHashtable)(new Object());
            this._persistent.setContents(this._persistentData, 51, false);
            this._persistent.commit();
         }
      }

      DeviceOptions.addOptionsProvider(this);
   }
}
