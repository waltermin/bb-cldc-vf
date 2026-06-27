package net.rim.device.internal.ui;

import java.util.Vector;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.HolsterListener;

public final class AnimationThread extends Thread implements HolsterListener {
   private static Vector _waitQueue;
   private static Application _theApplication;
   private static Thread _currentThread;
   private static boolean _shutdown;
   private static boolean _paused;

   private AnimationThread() {
      _theApplication.addHolsterListener(this);
   }

   public static final void addAnimation(Animation var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeAnimation(Animation var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void pause() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void resume() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void inHolster() {
      pause();
   }

   @Override
   public final void outOfHolster() {
      resume();
   }
}
