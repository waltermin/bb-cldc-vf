package net.rim.device.api.ui;

public final class PMEGraphics {
   private int _returnState;
   private int _lastNode;
   private XYRect _lastViewport = (XYRect)(new Object());
   private XYRect _lastClip = (XYRect)(new Object());
   private boolean _enabled;
   private int _iNodeRoot = -1;
   private int[] _nodes;
   private int[][][] _coords;
   private byte[][][] _pointTypes;
   private Object[] _images;
   private Object[] _foreignObjects;
   private char[][][] _strings;
   private String[] _fontFamilies;
   private String _defaultFontFamily;
   private XYRect _viewport = (XYRect)(new Object());
   private int[] _transform;
   private XYRect _clip = (XYRect)(new Object());
   private int _clipColour = 16711680;
   private int[] _nodeList;
   private int[] _leafNodeList;
   private XYRect _boundsDirty;
   private int _drawBounds = 0;
   private int _boundsColour = 255;
   private int _bkgrndColour = -1;
   private int[] _stats;
   private long _statsResetTime;
   private int _cacheID;
   private PMEGraphics$GfxContext _gfxContext = new PMEGraphics$GfxContext();
   public static final long REG_ID;
   static PMEGraphics$PMERegistryOptions _regOptions;
   public static final int PMEOPTION_DISABLE;
   public static final int PMEOPTION_SHOW_CLIP;
   public static final int PMEOPTION_SHOW_BOUNDS;
   public static final int PMEOPTION_LOG_STATS;
   public static final int PMEGRAPHICS_FAIL;
   public static final int PMEGRAPHICS_FAIL_ALLOC;
   public static final int PMEGRAPHICS_FAIL_INDEX;
   public static final int PMEGRAPHICS_FAIL_ATTR;
   public static final int PMEGRAPHICS_FAIL_REF;
   public static final int PMEGRAPHICS_FAIL_FILL;
   public static final int PMEGRAPHICS_FAIL_UPDATE;
   public static final int PMEGRAPHICS_OK;
   public static final int PMEGRAPHICS_UNKNOWN_NODE;
   public static final int PMEGRAPHICS_STATS_UPDATE;
   public static final int PMEGRAPHICS_STATS_UPDATE_RECT;
   public static final int PMEGRAPHICS_STATS_UPDATE_ELLIPSE;
   public static final int PMEGRAPHICS_STATS_UPDATE_PATH;
   public static final int PMEGRAPHICS_STATS_UPDATE_IMAGE;
   public static final int PMEGRAPHICS_STATS_UPDATE_TEXT;
   public static final int PMEGRAPHICS_STATS_RENDER;
   public static final int PMEGRAPHICS_STATS_RENDER_RECT;
   public static final int PMEGRAPHICS_STATS_RENDER_ELLIPSE;
   public static final int PMEGRAPHICS_STATS_RENDER_PATH;
   public static final int PMEGRAPHICS_STATS_RENDER_IMAGE;
   public static final int PMEGRAPHICS_STATS_RENDER_TEXT;
   public static final int PMEGRAPHICS_STATS_FILL_DATA;
   public static final int PMEGRAPHICS_STATS_UPDATE_DATA;
   public static final int PMEGRAPHICS_STATS_UPDATE_VP;
   public static final int PMEGRAPHICS_STATS_RENDER_VP;
   public static final int PMEGRAPHICS_STATS_NUM;
   public static final int PMEGRAPHICS_STATS_SIZE;

   public final void setFillContext(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setStrokeContext(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int clear(Object var1) {
      if (var1 instanceof Graphics) {
         if (this._bkgrndColour != -1) {
            Graphics var2 = (Graphics)var1;
            var2.setColor(this._bkgrndColour);
            var2.fillRect(this._clip.x, this._clip.y, this._clip.width, this._clip.height);
         }

         this._returnState = 0;
      } else {
         this._returnState = 2;
      }

      return this._returnState;
   }

   public final int render(Object var1, int var2) {
      if (var1 instanceof Graphics) {
         this._returnState = this.renderNative((Graphics)var1, var2);
      } else {
         this._returnState = 2;
      }

      return this._returnState;
   }

   public final int renderList(Object var1, int var2) {
      if (var1 instanceof Graphics) {
         this._returnState = this.renderListNative((Graphics)var1, var2);
      } else {
         this._returnState = 2;
      }

      return this._returnState;
   }

   public final int renderLeafNodes(Object var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final int renderNode(Object var1, int var2) {
      if (var1 instanceof Graphics) {
         return 1;
      }

      this._returnState = 2;
      return this._returnState;
   }

   public final int update(int var1) {
      if (this._boundsDirty == null) {
         this._boundsDirty = (XYRect)(new Object());
      }

      int var2 = this.getLeafCount(this._iNodeRoot);
      if (this._leafNodeList == null || var2 > this._leafNodeList.length) {
         this._leafNodeList = new int[var2];
      }

      return this.updateNative(var1);
   }

   public final XYRect getDirtyBounds() {
      return this._boundsDirty;
   }

   private final native int getLeafCount(int var1);

   private final native int renderNative(Graphics var1, int var2);

   private final native int renderListNative(Graphics var1, int var2);

   private final native int updateNative(int var1);

   public final boolean setNodes(int[] var1) {
      if (this._nodes != var1) {
         this._nodes = var1;
         return true;
      } else {
         return false;
      }
   }

   public final void setRootIndex(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean setCoords(int[][][] var1) {
      if (this._coords != var1) {
         this._coords = var1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean setPointTypes(byte[][][] var1) {
      if (this._pointTypes != var1) {
         this._pointTypes = var1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean setImages(Object[] var1) {
      if (this._images != var1) {
         this._images = var1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean setForeignObjects(Object[] var1) {
      if (this._foreignObjects != var1) {
         this._foreignObjects = var1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean setStrings(char[][][] var1) {
      if (this._strings != var1) {
         this._strings = var1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean setFontFamilies(String[] var1) {
      if (this._fontFamilies != var1) {
         this._fontFamilies = var1;
         return true;
      } else {
         return false;
      }
   }

   public final void setDefaultFontFamily(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setNodeList(int[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setClip(int var1, int var2, int var3, int var4) {
      this._clip.set(var1, var2, var3, var4);
   }

   public final int getLastReturn() {
      return this._returnState;
   }

   public final int getLastNode() {
      return this._lastNode;
   }

   public final int getNextListNode(int var1) {
      if (this._nodeList == null) {
         return -1;
      }

      int var2 = this._nodeList.length - 1;
      byte var3 = -1;

      for (int var4 = 0; var4 < var2; var4++) {
         if (this._nodeList[var4] == var1) {
            return this._nodeList[var4 + 1];
         }
      }

      return var3;
   }

   public final int getNextLeafNode(int var1) {
      if (this._leafNodeList == null) {
         return -1;
      }

      int var2 = this._leafNodeList.length - 1;
      byte var3 = -1;

      for (int var4 = 0; var4 < var2; var4++) {
         if (this._leafNodeList[var4] == var1) {
            return this._leafNodeList[var4 + 1];
         }
      }

      return var3;
   }

   public final int[] getLeafNodeList() {
      return this._leafNodeList;
   }

   public final XYRect getLastViewport() {
      return this._lastViewport;
   }

   public final XYRect getLastClip() {
      return this._lastClip;
   }

   public final void setViewport(int var1, int var2, int var3, int var4) {
      this._viewport.set(var1, var2, var3, var4);
   }

   public final void setTransform(int[] var1) {
      if (this._transform == null) {
         this._transform = new int[9];
      }

      if (this._transform != null && var1.length == 9) {
         System.arraycopy(var1, 0, this._transform, 0, 9);
      }
   }

   public final void setBackgroundColour(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getBackgroundColour() {
      return this._bkgrndColour;
   }

   public final void setStats(int[] var1) {
      if (this._stats != var1) {
         this._stats = var1;
         this.resetStats(-1);
      }
   }

   public final int getStatsCount(int var1) {
      if (this._stats != null) {
         for (byte var2 = 0; var2 < this._stats.length; var2 += 4) {
            if (this._stats[var2] == var1) {
               return this._stats[var2 + 1];
            }
         }
      }

      return -1;
   }

   public final int getStatsDuration(int var1) {
      if (this._stats != null) {
         for (byte var2 = 0; var2 < this._stats.length; var2 += 4) {
            if (this._stats[var2] == var1) {
               return this._stats[var2 + 2];
            }
         }
      }

      return -1;
   }

   public final int getStatsAvgDuration(int var1) {
      int var2 = this.getStatsCount(var1);
      int var3 = this.getStatsDuration(var1);
      return var2 > 0 && var3 > 0 ? var3 / var2 : -1;
   }

   public static final boolean isDisabled() {
      return _regOptions == null ? false : _regOptions._disabled;
   }

   public static final boolean showClip() {
      return _regOptions == null ? false : _regOptions._showClip;
   }

   public static final boolean showBounds() {
      return _regOptions == null ? false : _regOptions._showBounds;
   }

   public static final boolean logStats() {
      return _regOptions == null ? false : _regOptions._logStats;
   }

   public static final boolean toggleRegOption(int var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void renderClip(Object var1) {
      Graphics var2 = (Graphics)var1;
      var2.setColor(0);
      this._clipColour >>= 8;
      if (this._clipColour == 0) {
         this._clipColour = 16711680;
      }

      var2.drawRect(this._clip.x + 1, this._clip.y + 1, this._clip.width - 2, this._clip.height - 2);
   }

   public final void renderStats(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void resetStats(int var1) {
      if (this._stats != null) {
         for (byte var2 = 0; var2 < this._stats.length; var2 += 4) {
            if (this._stats[var2] == var1 || var1 < 0) {
               this._stats[var2 + 1] = 0;
               this._stats[var2 + 2] = 0;
               if (this._stats[var2] == var1) {
                  break;
               }
            }
         }
      }

      if (var1 < 0) {
         this._statsResetTime = System.currentTimeMillis();
      }
   }

   public final void startStats(int var1) {
      if (this._stats != null) {
         for (byte var2 = 0; var2 < this._stats.length; var2 += 4) {
            if (this._stats[var2] == var1) {
               this._stats[var2 + 3] = (int)(System.currentTimeMillis() - this._statsResetTime);
               return;
            }
         }
      }
   }

   public final void stopStats(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }
}
