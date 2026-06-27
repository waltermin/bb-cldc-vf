package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

public class LegendField extends ChartField {
   private String _title;
   private String[] _labels = new String[0];

   public LegendField() {
      this(0);
   }

   public LegendField(long var1) {
      super(var1);
   }

   @Override
   public int getPreferredHeight() {
      int var1 = Font.getDefault().getHeight();
      int var2 = (int)(4608308318706860032L * var1 * this._labels.length);
      if (this._title != null) {
         var2 += var1;
      }

      return var2;
   }

   @Override
   public int getPreferredWidth() {
      Font var1 = Font.getDefault();
      int var2 = 0;

      for (int var3 = this._labels.length - 1; var3 >= 0; var3--) {
         if (var1.getAdvance(this._labels[var3]) > var2) {
            var2 = var1.getAdvance(this._labels[var3]);
         }
      }

      if (var1.getAdvance(this._title) > var2) {
         var2 = var1.getAdvance(this._title);
      }

      return var2 + var1.getAdvance((char)32) + var1.getHeight();
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = this.getPreferredWidth();
      Font var3 = Font.getDefault();
      int var6 = var3.getHeight();
      int var7 = var3.getAdvance(' ');
      int var5;
      if (this._title != null) {
         int var4 = (var2 - var3.getAdvance(this._title)) / 2;
         var1.setFont(var3);
         var1.drawText(this._title, var4, 0);
         var5 = (int)(var6 * 4608308318706860032L);
      } else {
         var5 = (int)(var6 * 4598175219545276416L);
      }

      int var10 = var6 + var7;
      int var8 = this._labels.length;

      for (int var9 = 0; var9 < var8; var9++) {
         var1.setColor(this.getColor(var9));
         var1.fillRect(0, var5, var6, var6);
         var1.setColor(0);
         var1.drawRect(0, var5, var6, var6);
         var1.drawText(this._labels[var9], var10, var5);
         var5 += (int)(var6 * 4608308318706860032L);
      }
   }

   public int getNumLabels() {
      return this._labels.length;
   }

   public String getLabel(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getTitle() {
      return this._title;
   }

   public void setChartData(String[] var1, int[] var2) {
      ChartField.assertLegal(var1.length == var2.length);

      for (int var3 = var1.length - 1; var3 >= 0; var3--) {
         if (var1[var3] == null) {
            throw new Object();
         }
      }

      this._labels = new String[var1.length];
      System.arraycopy(var1, 0, this._labels, 0, var1.length);
      super.setChartData(var2);
      this.updateChart();
   }

   public void setLabel(int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setTitle(String var1) {
      if (this._title == null && var1 != null || this._title != null && !this._title.equals(var1)) {
         this._title = var1;
         this.updateChart();
      }
   }
}
