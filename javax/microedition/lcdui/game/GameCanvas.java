package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class GameCanvas extends Canvas {
   private Image offscreen_buffer;
   public static final int UP_PRESSED;
   public static final int DOWN_PRESSED;
   public static final int LEFT_PRESSED;
   public static final int RIGHT_PRESSED;
   public static final int FIRE_PRESSED;
   public static final int GAME_A_PRESSED;
   public static final int GAME_B_PRESSED;
   public static final int GAME_C_PRESSED;
   public static final int GAME_D_PRESSED;
   private static final int FULLSCREEN_HEIGHT;
   private static final int FULLSCREEN_WIDTH;

   protected GameCanvas(boolean var1) {
   }

   protected Graphics getGraphics() {
      return this.offscreen_buffer.getGraphics();
   }

   public int getKeyStates() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void paint(Graphics var1) {
      var1.drawImage(this.offscreen_buffer, 0, 0, 20);
   }

   public void flushGraphics(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void flushGraphics() {
      this.flushGraphics(0, 0, this.getWidth(), this.getHeight());
   }
}
