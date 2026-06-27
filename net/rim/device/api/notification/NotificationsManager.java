package net.rim.device.api.notification;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.util.LongEnumeration;
import net.rim.device.api.util.LongHashtable;
import net.rim.vm.Array;
import net.rim.vm.TraceBack;

public final class NotificationsManager implements NotificationsConstants {
   private static Hashtable _sourcesByObject;
   private static final long SOURCES_BY_OBJECT;
   private static LongHashtable _sourcesById;
   private static final long SOURCES_BY_ID;
   private static LongHashtable _consequences;
   private static final long CONSEQUENCES;
   private static NotificationsEngine _engine;
   private static LongHashtable _listenersById;
   private static final long LISTENERS_BY_ID;
   private static Vector _sourceListeners;
   private static final long SOURCE_LISTENERS;
   private static final long INVALID_VALUE;

   private NotificationsManager() {
   }

   public static final void triggerImmediateEvent(long var0, long var2, Object var4, Object var5) {
      NotificationsManager$Wrapper var6 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var6 != null) {
         if (_engine == null) {
            _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
         }

         if (_engine != null) {
            _engine.triggerImmediateEvent(var6._id, var6._object, var6._level, var2, var4, var5);
         }
      }
   }

   public static final void cancelImmediateEvent(long var0, long var2, Object var4, Object var5) {
      NotificationsManager$Wrapper var6 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var6 != null) {
         if (_engine == null) {
            _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
         }

         if (_engine != null) {
            _engine.cancelImmediateEvent(var6._id, var6._object, var6._level, var2, var4, var5);
         }
      }
   }

   public static final void negotiateDeferredEvent(long var0, long var2, Object var4, long var5, int var7, Object var8) {
      NotificationsManager$Wrapper var9 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var9 != null) {
         if (_engine == null) {
            _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
         }

         if (_engine != null) {
            _engine.negotiateDeferredEvent(var9._id, var9._object, var9._level, var2, var4, var5, var7, var8);
         }
      }
   }

   public static final void cancelDeferredEvent(long var0, long var2, Object var4, int var5, Object var6) {
      NotificationsManager$Wrapper var7 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var7 != null) {
         if (_engine == null) {
            _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
         }

         if (_engine != null) {
            _engine.cancelDeferredEvent(var7._id, var7._object, var7._level, var2, var4, var5, var6);
         }
      }
   }

   public static final void cancelAllDeferredEvents(long var0, int var2, Object var3) {
      NotificationsManager$Wrapper var4 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var4 != null) {
         if (_engine == null) {
            _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
         }

         if (_engine != null) {
            _engine.cancelAllDeferredEvents(var4._id, var4._object, var4._level, var2, var3);
         }
      }
   }

   public static final int getDeferredEventCount(long var0) {
      if (_engine == null) {
         _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
      }

      return _engine != null ? _engine.getDeferredEventCount(var0) : 0;
   }

   public static final Object[] getDeferredEvents(long var0) {
      if (_engine == null) {
         _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
      }

      return _engine != null ? _engine.getDeferredEvents(var0) : null;
   }

   public static final long[] getDeferredEventIds(long var0) {
      if (_engine == null) {
         _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
      }

      return _engine != null ? _engine.getDeferredEventIds(var0) : null;
   }

   public static final void registerSource(long var0, Object var2, int var3, long var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void moveSource(long var0, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void registerSource(long var0, Object var2, int var3) {
      registerSource(var0, var2, var3, -1);
   }

   public static final void deregisterSource(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void hideSource(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void unHideSource(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isHidden(long var0) {
      NotificationsManager$Wrapper var2 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      return var2 != null && var2._hidden;
   }

   public static final Object getSource(long var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      NotificationsManager$Wrapper var2 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      return var2 != null ? var2._object : null;
   }

   public static final long[] getRelatedSourceIds(long var0, boolean var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      long[] var3 = new long[0];
      Enumeration var4 = _sourcesById.elements();

      while (var4.hasMoreElements()) {
         NotificationsManager$Wrapper var5 = (NotificationsManager$Wrapper)var4.nextElement();
         if (var5._relatedId == var0 && (var2 || !var5._hidden)) {
            Array.resize(var3, var3.length + 1);
            var3[var3.length - 1] = var5._id;
         }
      }

      return var3;
   }

   public static final long[] getHiddenSourceIds() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      long[] var0 = new long[0];
      Enumeration var1 = _sourcesById.elements();

      while (var1.hasMoreElements()) {
         NotificationsManager$Wrapper var2 = (NotificationsManager$Wrapper)var1.nextElement();
         if (var2._hidden) {
            Array.resize(var0, var0.length + 1);
            var0[var0.length - 1] = var2._id;
         }
      }

      return var0;
   }

   public static final long getParentSourceID(long var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      NotificationsManager$Wrapper var2 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      return var2 != null ? var2._relatedId : -1;
   }

   public static final long[] enumerateSourceIds() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getSourceId(Object var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      NotificationsManager$Wrapper var1 = (NotificationsManager$Wrapper)_sourcesByObject.get(var0);
      if (var1 == null) {
         throw new Object();
      } else {
         return var1._id;
      }
   }

   public static final int getSourceLevel(long var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      NotificationsManager$Wrapper var2 = (NotificationsManager$Wrapper)_sourcesById.get(var0);
      if (var2 == null) {
         throw new Object();
      } else {
         return var2._level;
      }
   }

   public static final int getSourceLevel(Object var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      NotificationsManager$Wrapper var1 = (NotificationsManager$Wrapper)_sourcesByObject.get(var0);
      if (var1 == null) {
         throw new Object();
      } else {
         return var1._level;
      }
   }

   public static final void registerNotificationsEngineListener(long var0, NotificationsEngineListener var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void deregisterNotificationsEngineListener(long var0, NotificationsEngineListener var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void fireStateChanged(int var0, long var1, long var3, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void fireDefer(long var0, long var2, Object var4, Object var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void fireProceed(long var0, long var2, Object var4, Object var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final Object getListenerByID(long var0) {
      Object var2 = _listenersById.get(var0);
      if (var2 == null) {
         var0 = getParentSourceID(var0);
         var2 = _listenersById.get(var0);
      }

      return var2;
   }

   public static final void registerConsequence(long var0, Consequence var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void deregisterConsequence(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Consequence getConsequence(long var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return (Consequence)_consequences.get(var0);
   }

   public static final LongEnumeration enumerateConsequenceIds() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return _consequences.keys();
   }

   public static final long getLastEventDate() {
      return _engine.getLastEventDate();
   }

   public static final boolean isImmediateEventOccuring(long var0) {
      if (_engine == null) {
         _engine = (NotificationsEngine)ApplicationRegistry.getApplicationRegistry().get(6720217471165517311L);
      }

      return _engine != null ? _engine.isImmediateEventOccuring(var0) : false;
   }

   private static final void sourceUpdated() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addSourceChangedListener(NotificationsManager$SourceListener var0) {
      _sourceListeners.addElement(var0);
   }

   public static final void removeSourceChangedListener(NotificationsManager$SourceListener var0) {
      _sourceListeners.removeElement(var0);
   }
}
