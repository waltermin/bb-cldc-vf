package net.rim.device.internal.ui;

import java.util.Vector;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.vm.Message;
import net.rim.vm.MessageQueue;
import net.rim.vm.Process;

class RecordKeystrokes extends MainScreen {
   private Vector _messages;
   private RichTextField _output;

   public RecordKeystrokes() {
   }

   public void fillqueue(int repeat) {
      if (this._messages != null) {
         Message[] msgs = new Message[this._messages.size() * repeat];
         int i = 0;

         for (int r = 0; r < repeat; r++) {
            for (int m = 0; m < this._messages.size(); m++) {
               msgs[i] = new Message();
               msgs[i].copy((Message)this._messages.elementAt(m));
               i++;
            }
         }

         MessageQueue queue = Process.currentProcess().getMessageQueue();
         synchronized (queue) {
            int needed = queue.getSize() + msgs.length + 5;
            if (needed > queue.getMaxCapacity()) {
               queue.setMaxCapacity(needed);
            }

            i = 0;

            while (i < msgs.length && queue.enqueue(msgs[i])) {
               i++;
            }
         }
      }
   }

   private void output(String msg) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyUp(int keycode, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyStatus(int keycode, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean trackwheelRoll(int amount, int status, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean trackwheelClick(int status, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean trackwheelUnclick(int status, int time) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
