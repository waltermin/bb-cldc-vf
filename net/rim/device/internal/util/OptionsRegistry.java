package net.rim.device.internal.util;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.api.util.LongEnumeration;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.internal.deviceoptions.OptionsProvider;
import net.rim.device.internal.deviceoptions.OptionsProviderChangeListener;
import net.rim.device.internal.deviceoptions.OptionsProviderChangeSource;

public class OptionsRegistry implements OptionsProvider, OptionsProviderChangeSource {
   private final long _guid;
   private PersistentObject _persistent;
   private LongHashtable _persistentData;
   private Object[] _listeners;
   private OptionsProviderChangeListener _syncListener;
   private final LongHashtable _parameterDefinitions;
   private static final int TAG;
   private static final int TYPE_STRING;
   private static final int TYPE_INTEGER;
   private static final int TYPE_LONG;
   private static final int TYPE_BYTE_ARRAY;
   private static final int TYPE_DOUBLE;
   private static final int TYPE_BOOLEAN;
   private static final int TYPE_CHAR;

   public void addOptionsRegistryChangeListener(OptionsRegistry$Listener var1) {
      this._listeners = ListenerUtilities.addListener(this._listeners, var1);
   }

   protected void define(long var1, OptionsRegistry$ParameterDefinition var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public char getChar(long var1) {
      return this.getCharParam(var1).getValue();
   }

   @Override
   public int getUID() {
      return (int)this._guid;
   }

   public boolean getBoolean(long var1) {
      return this.getBooleanParam(var1).getValue();
   }

   public OptionsRegistry$BooleanParameter getBooleanParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$BooleanParameterDefinition var4 = (OptionsRegistry$BooleanParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setBoolean(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$BooleanParameter)var3;
   }

   public byte[] getByteArray(long var1) {
      return this.getByteArrayParam(var1).getValue();
   }

   public OptionsRegistry$ByteArrayParameter getByteArrayParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$ByteArrayParameterDefinition var4 = (OptionsRegistry$ByteArrayParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setByteArray(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$ByteArrayParameter)var3;
   }

   public double getDouble(long var1) {
      return this.getDoubleParam(var1).getValue();
   }

   public OptionsRegistry$DoubleParameter getDoubleParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$DoubleParameterDefinition var4 = (OptionsRegistry$DoubleParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setDouble(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$DoubleParameter)var3;
   }

   public int getInt(long var1) {
      return this.getIntParam(var1).getValue();
   }

   public OptionsRegistry$IntParameter getIntParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$IntParameterDefinition var4 = (OptionsRegistry$IntParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setInt(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$IntParameter)var3;
   }

   public LongEnumeration getKeys() {
      return this._persistentData.keys();
   }

   public long getLong(long var1) {
      return this.getLongParam(var1).getValue();
   }

   public OptionsRegistry$LongParameter getLongParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$LongParameterDefinition var4 = (OptionsRegistry$LongParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setLong(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$LongParameter)var3;
   }

   protected boolean isBackupParam(long var1) {
      return true;
   }

   public void setChar(long var1, char var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getString(long var1) {
      return this.getStringParam(var1).getValue();
   }

   public OptionsRegistry$StringParameter getStringParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$StringParameterDefinition var4 = (OptionsRegistry$StringParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setString(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$StringParameter)var3;
   }

   public OptionsRegistry$CharParameter getCharParam(long var1) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         OptionsRegistry$CharParameterDefinition var4 = (OptionsRegistry$CharParameterDefinition)this._parameterDefinitions.get(var1);
         if (var4 == null) {
            throw new Object();
         }

         this.setChar(var1, var4._default);
         var3 = this.get(var1);
      }

      return (OptionsRegistry$CharParameter)var3;
   }

   public void removeOptionsRegistryChangeListener(OptionsRegistry$Listener var1) {
      this._listeners = ListenerUtilities.removeListener(this._listeners, var1);
   }

   public void setString(long var1, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setBoolean(long var1, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setDouble(long var1, double var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setInt(long var1, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setLong(long var1, long var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setByteArray(long var1, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setOptionsProviderChangeListener(OptionsProviderChangeListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setOptionsData(DataBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void getOptionsData(DataBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void notifyListeners(long var1) {
      if (this._syncListener != null && this.isBackupParam(var1)) {
         this._syncListener.optionsProviderChanged(this);
      }

      Object[] var3 = this._listeners;
      if (var3 != null) {
         for (int var4 = 0; var4 < var3.length; var4++) {
            ((OptionsRegistry$Listener)var3[var4]).onOptionsRegistryChange(var1);
         }
      }
   }

   private Object get(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public OptionsRegistry(long var1) {
   }
}
