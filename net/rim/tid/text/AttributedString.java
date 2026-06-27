package net.rim.tid.text;

import net.rim.device.api.ui.Ui;
import net.rim.device.internal.ui.StringBufferGap;

public class AttributedString {
   private int _length;
   private AttributedString$Run _run;
   private int _cursor;
   private AttributedString$Run _cursor_run;
   private int _cursor_run_start;
   private StringBufferGap _text;
   private long _new_attrib;
   private long _new_xattrib;
   private long _global_attrib;
   public static final long FONT_HEIGHT_MASK;
   public static final long ANTI_ALIAS_MODE_MASK;
   public static final long BOLD_MASK;
   public static final long ITALIC_MASK;
   public static final long FONT_NAME_INDEX_MASK;
   public static final long BASELINE_OFFSET_MASK;
   public static final long UNDERLINE_MASK;
   public static final long STRIKETHROUGH_MASK;
   public static final long JUSTIFY_MASK;
   public static final long PAR_DIR_MASK;
   public static final long HIGHLIGHT_MASK;
   public static final long PASSWORD_MODE_MASK;
   public static final long SYSTEM_ATTRIB_MASK;
   public static final long FOREGROUND_COLOR_MASK;
   public static final long BACKGROUND_COLOR_MASK;
   public static final long FONT_ATTRIB_MASK;
   public static final int FONT_HEIGHT_SHIFT;
   public static final int ANTI_ALIAS_MODE_SHIFT;
   public static final int BOLD_SHIFT;
   public static final int ITALIC_SHIFT;
   public static final int FONT_NAME_INDEX_SHIFT;
   public static final int BASELINE_OFFSET_SHIFT;
   public static final int UNDERLINE_SHIFT;
   public static final int STRIKETHROUGH_SHIFT;
   public static final int JUSTIFY_SHIFT;
   public static final int PAR_DIR_SHIFT;
   public static final int HIGHLIGHT_SHIFT;
   public static final int FOREGROUND_COLOR_SHIFT;
   public static final int BACKGROUND_COLOR_SHIFT;
   public static final long NO_ANTIALIAS;
   public static final long STANDARD_ANTIALIAS;
   public static final long SUB_PIXEL_RENDERING;
   public static final long LOW_RESOLUTION_ANTIALIAS;
   public static final long NO_BOLD;
   public static final long BOLD;
   public static final long EXTRA_BOLD;
   public static final long NO_ITALIC;
   public static final long ITALIC;
   public static final long STANDARD_BASELINE;
   public static final long SUPERSCRIPT;
   public static final long SUBSCRIPT;
   public static final long NO_UNDERLINE;
   public static final long SOLID_UNDERLINE;
   public static final long BROKEN_UNDERLINE;
   public static final long DOTTED_UNDERLINE;
   public static final long NO_STRIKETHROUGH;
   public static final long SOLID_STRIKETHROUGH;
   public static final long BROKEN_STRIKETHROUGH;
   public static final long DOTTED_STRIKETHROUGH;
   public static final long JUSTIFY_STANDARD;
   public static final long JUSTIFY_REVERSE;
   public static final long JUSTIFY_FULL;
   public static final long JUSTIFY_CENTER;
   public static final long PAR_DEFAULT_LEFT_TO_RIGHT;
   public static final long PAR_DEFAULT_RIGHT_TO_LEFT;
   public static final long PAR_FORCE_LEFT_TO_RIGHT;
   public static final long PAR_FORCE_RIGHT_TO_LEFT;
   public static final long NO_HIGHLIGHT;
   public static final long HIGHLIGHT;
   public static final long X_ATTRIB_PICTURE_FLOAT_MASK;
   public static final long X_ATTRIB_PICTURE_CLEAR_MASK;
   public static final long X_ATTRIB_PICTURE_FLOAT_SHIFT;
   public static final long X_ATTRIB_PICTURE_CLEAR_SHIFT;
   public static final long PICTURE_FLOAT_NONE;
   public static final long PICTURE_FLOAT_LEFT;
   public static final long PICTURE_FLOAT_RIGHT;
   public static final long PICTURE_CLEAR_NONE;
   public static final long PICTURE_CLEAR_LEFT;
   public static final long PICTURE_CLEAR_RIGHT;
   public static final long PICTURE_CLEAR_BOTH;
   public static final long HAN_STYLE_MASK;
   public static final long HAN_STYLE_SHIFT;
   public static final long UNSPECIFIED_HAN;
   public static final long TRADITIONAL_HAN;
   public static final long SIMPLIFIED_HAN;
   public static final long JAPANESE_HAN;
   public static final long KOREAN_HAN;
   public static final int PICTURE_LINE_BREAK_DEFAULT;
   public static final int MAX_FONT_SIZE;

   public static int fontHeight(long var0) {
      return (int)((var0 & 63) >> 0);
   }

   public static boolean bold(long var0) {
      return (var0 & 134217984) != 0;
   }

   public static boolean italic(long var0) {
      return (var0 & 512) != 0;
   }

   public static int fontNameIndex(long var0) {
      return (int)((var0 & 64512) >> 10);
   }

   public static long setForegroundColor(long var0, int var2) {
      return var0 & -281470681743361L | Ui.convertColorTo16bit(var2) << 32;
   }

   public AttributedString() {
      this._text = (StringBufferGap)(new Object());
      this._run = new AttributedString$Run();
      this._cursor_run = this._run;
   }

   public AttributedString(String var1) {
      this.set(var1, 0, 0);
   }

   public AttributedString(String var1, long var2, long var4) {
      this.set(var1, var2, var4);
   }

   public AttributedString(StringBuffer var1) {
      this.set(var1.toString(), 0, 0);
   }

   public AttributedString(StringBufferGap var1) {
      this.set(var1.toString(), 0, 0);
   }

   public AttributedString(AttributedString var1) {
      this.set(var1.getIterator());
   }

   public AttributedString(AttributedString var1, int var2, int var3) {
      this.set(var1.getIterator(var2, var3));
   }

   public void set(String var1) {
      this.set(var1, 0, 0, 0);
   }

   public void set(String var1, long var2, long var4) {
      this.set(var1, var2, var4, 0);
   }

   public void set(String var1, long var2, long var4, long var6) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void set(AttributedString$Iterator var1) {
      this._run = null;
      this._text = null;
      this._length = var1.length();
      this._global_attrib = var1.getGlobalAttrib();
      AttributedString$Run var2 = null;
      this._text = (StringBufferGap)(new Object(var1.text().getText(var1.pos(), this._length)));

      do {
         AttributedString$Run var3 = new AttributedString$Run(var2, null, var1.runLength(), var1.runAttrib(), var1.runXAttrib(), var1.runPictureInfo());
         if (var2 == null) {
            this._run = var3;
         } else {
            var2._next = var3;
         }

         var2 = var3;
      } while (var1.next());

      this._cursor = 0;
      this._cursor_run = this._run;
      this._cursor_run_start = 0;
   }

   public void set(StringBuffer var1) {
      this.set(var1.toString());
   }

   public void set(StringBufferGap var1) {
      this.set(var1.toString());
   }

   public String getString() {
      return this._text.toString();
   }

   @Override
   public String toString() {
      return this._text.toString();
   }

   public int length() {
      return this._length;
   }

   public char charAt(int var1) {
      return this._text.charAt(var1);
   }

   private void seekRun(int var1) {
      if (this._cursor != var1) {
         if (var1 > this._cursor) {
            while (var1 >= this._cursor_run_start + this._cursor_run._length && this._cursor_run._next != null) {
               this._cursor_run_start = this._cursor_run_start + this._cursor_run._length;
               this._cursor_run = this._cursor_run._next;
            }
         } else {
            while (var1 < this._cursor_run_start && this._cursor_run_start > 0) {
               this._cursor_run = this._cursor_run._prev;
               this._cursor_run_start = this._cursor_run_start - this._cursor_run._length;
            }
         }

         this._cursor = var1;
      }
   }

   public void seek(int var1) {
      if (var1 >= 0 && var1 <= this._length) {
         this._text.seek(var1);
         if (this._cursor != var1) {
            this.seekRun(var1);
         }
      } else {
         throw new Object();
      }
   }

   public void setInsertAttrib(long var1) {
      this._new_attrib = var1;
   }

   public void setInsertXAttrib(long var1) {
      this._new_xattrib = var1;
   }

   public void setGlobalAttrib(long var1, long var3) {
      this._global_attrib = this._global_attrib & (var3 ^ -1) | var1 & var3;
   }

   public long getGlobalAttrib() {
      return this._global_attrib;
   }

   private void deleteRun(AttributedString$Run var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void insertRun(AttributedString$Run var1, int var2, long var3, long var5, AttributedString$PictureInfo var7) {
      AttributedString$Run var8 = var1 == null ? this._run : var1._next;
      AttributedString$Run var9 = new AttributedString$Run(var1, var8, var2, var3, var5, var7);
      if (var1 != null) {
         var1._next = var9;
      } else {
         this._run = var9;
      }

      if (var8 != null) {
         var8._prev = var9;
      }
   }

   private void insertAttribRun(int var1, long var2, long var4, long var6, long var8, AttributedString$PictureInfo var10) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void delete(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void insert(char var1) {
      this._text.insert(var1);
      this.insertAttribRun(1, this._new_attrib, -1, this._new_xattrib, -1, null);
   }

   public void insert(int var1, char var2) {
      this.seek(var1);
      this.insert(var2);
   }

   public void insert(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void insert(StringBuffer var1) {
      if (var1.length() > 0) {
         this._text.insert(var1);
         this.insertAttribRun(var1.length(), this._new_attrib, -1, this._new_xattrib, -1, null);
      }
   }

   public void insert(int var1, String var2) {
      this.seek(var1);
      this.insert(var2);
   }

   public void insert(int var1, AttributedString var2) {
      this.replace(var1, var1, var2, 0, var2.length());
   }

   public void insert(AttributedString$Picture var1) {
      this._text.insert('￼');
      this.insertAttribRun(1, this._new_attrib, -1, this._new_xattrib, -1, var1.getInfo());
   }

   public void insert(int var1, AttributedString$Picture var2) {
      this.seek(var1);
      this.insert(var2);
   }

   public void replace(int var1, int var2, char var3) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         this.seek(var2);
         if (var1 < var2) {
            this.delete(var2 - var1);
         }

         this.insert(var3);
      } else {
         throw new Object();
      }
   }

   public void replace(int var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void replace(int var1, int var2, StringBuffer var3) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         this.seek(var2);
         if (var1 < var2) {
            this.delete(var2 - var1);
         }

         if (var3.length() > 0) {
            this.insert(var3);
         }
      } else {
         throw new Object();
      }
   }

   public void replace(int var1, int var2, AttributedString$Iterator var3) {
      this.replace(var1, var2, var3, -1, -1);
   }

   public void replace(int var1, int var2, AttributedString$Iterator var3, long var4, long var6) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         this.seek(var2);
         if (var1 < var2) {
            this.delete(var2 - var1);
         }

         if (var3.length() <= 0) {
            if (this._length == 0) {
               this._run._attrib = this._run._attrib & (var4 ^ -1) | var3.runAttrib() & var4;
               this._run._xAttrib = this._run._xAttrib & (var6 ^ -1) | var3.runXAttrib() & var6;
            }
         } else {
            this._text.insert(var3.text().getText(var3.pos(), var3.length()));

            do {
               this.insertAttribRun(var3.runLength(), var3.runAttrib(), var4, var3.runXAttrib(), var6, var3.runPictureInfo());
            } while (var3.next());
         }
      } else {
         throw new Object();
      }
   }

   public void replace(int var1, int var2, AttributedString var3, int var4, int var5) {
      AttributedString$Iterator var6 = var3.getIterator(var4, var5);
      this.replace(var1, var2, var6);
   }

   public void replace(int var1, int var2, AttributedString var3) {
      AttributedString$Iterator var4 = var3.getIterator();
      this.replace(var1, var2, var4);
   }

   public void delete(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         if (var1 < var2) {
            this.seek(var2);
            this.delete(var2 - var1);
         }
      } else {
         throw new Object();
      }
   }

   public void setAttrib(int var1, long var2, long var4) {
      this.setAttrib(var1, var2, var4, 0, 0);
   }

   public void setAttrib(int var1, long var2, long var4, long var6, long var8) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setAttrib(int var1, int var2, long var3, long var5) {
      this.setAttrib(var1, var2, var3, var5, 0, 0);
   }

   public void setAttrib(int var1, int var2, long var3, long var5, long var7, long var9) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         int var11 = this._cursor;
         this.seekRun(var1);
         this.setAttrib(var2 - var1, var3, var5, var7, var9);
         this.seekRun(var11);
      } else {
         throw new Object();
      }
   }

   public String getText(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._length) {
         return this._text.getText(var1, var2 - var1);
      } else {
         throw new Object();
      }
   }

   public StringBufferGap getText() {
      return this._text;
   }

   public void setAttribToZero(int var1, int var2, long var3) {
      this.setAttrib(var1, var2, 0, var3);
   }

   public AttributedString$Iterator getIterator() {
      return new AttributedString$Iterator(this);
   }

   public AttributedString$Iterator getIterator(int var1, int var2) {
      return new AttributedString$Iterator(this, var1, var2);
   }

   public void assertValid() {
      if (this._run == null) {
         throw new Object();
      }

      if (this._cursor_run == null) {
         throw new Object();
      }

      if (this._length < 0) {
         throw new Object();
      }

      if (this._length != this._text.length()) {
         throw new Object();
      }

      if (this._cursor >= this._cursor_run_start && this._cursor <= this._cursor_run_start + this._cursor_run._length) {
         AttributedString$Run var1 = this._run;
         int var2 = 0;
         int var3 = 0;

         while (var1 != null) {
            if (var1 == this._cursor_run) {
               var3 = var2;
            }

            if (var1._pictureInfo != null && var1._length != 1) {
               throw new Object();
            }

            var2 += var1._length;
            var1 = var1._next;
         }

         if (var3 != this._cursor_run_start) {
            throw new Object();
         }

         if (var2 != this._length) {
            throw new Object();
         }
      } else {
         throw new Object();
      }
   }
}
