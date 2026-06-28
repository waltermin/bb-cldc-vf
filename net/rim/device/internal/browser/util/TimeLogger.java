package net.rim.device.internal.browser.util;

import net.rim.vm.Array;

public final class TimeLogger {
   private StringBuffer _tempSB = new StringBuffer();
   private long[] _totalTime = new long[14];
   private long[] _startTime = new long[4];
   private long[] _timerIds = new long[4];
   private static final long SINGLETON_REGISTRATION_KEY;
   public static final long EVENTLOGGER_GUID;
   private static TimeLogger _instance;
   public static boolean _loggingEnabled;
   public static final int TIMER_WTP_WAIT_INVOKE_RESPONSE;
   public static final int TIMER_WTP_WAIT_SEG_RESPONSE;
   public static final int TIMER_BROWSER_FETCH;
   public static final int TIMER_BROWSER_RENDER;
   public static final int TIMER_BROWSER_START_WAP_STACK;
   public static final int TIMER_BROWSER_ENTER_BROWSER;
   public static final int TIMER_WSP_HEADER_ENCODE;
   public static final int TIMER_BROWSER_JAVASCRIPT_RUN;
   public static final int TIMER_BROWSER_JAVASCRIPT_DESERIALIZE;
   public static final int TIMER_BROWSER_JAVASCRIPT_DOCUMENT_CREATE;
   public static final int TIMER_BROWSER_GZIP;
   public static final int TIMER_BROWSER_STYLE_SHEET_FETCH;
   public static final int TIMER_BROWSER_STYLE_SHEET_PROCESS;
   public static final int TIMER_PIPE_READ_WAIT;
   private static final int NUM_TIMERS;
   private static final String TIMER_WTP_WAIT_INVOKE_RESPONSE_DESCRIPTION;
   private static final String TIMER_WTP_WAIT_SEG_RESPONSE_DESCRIPTION;
   private static final String TIMER_BROWSER_FETCH_DESCRIPTION;
   private static final String TIMER_BROWSER_RENDER_DESCRIPTION;
   private static final String TIMER_BROWSER_START_WAP_STACK_DESCRIPTION;
   private static final String TIMER_BROWSER_ENTER_BROWSER_DESCRIPTION;
   private static final String TIMER_WSP_HEADER_ENCODE_DESCRIPTION;
   private static final String TIMER_BROWSER_JAVASCRIPT_RUN_DESCRIPTION;
   private static final String TIMER_BROWSER_JAVASCRIPT_DESERIALIZE_DESCRIPTION;
   private static final String TIMER_BROWSER_JAVASCRIPT_DOCUMENT_CREATE_DESCRIPTION;
   private static final String TIMER_BROWSER_GZIP_DESCRIPTION;
   private static final String TIMER_BROWSER_STYLE_SHEET_FETCH_DESCRIPTION;
   private static final String TIMER_BROWSER_STYLE_SHEET_PROCESS_DESCRIPTION;
   private static final String TIMER_PIPE_READ_WAIT_DESCRIPTION;

   private TimeLogger() {
   }

   public static final TimeLogger getInstance() {
      return _instance;
   }

   public final void startLogging() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized void startTimer(int timerId, int uniqueId) {
      long id = timerId << 32 | uniqueId;
      int count = this._timerIds.length;

      for (int i = 0; i < count; i++) {
         if (this._timerIds[i] == -1) {
            this._timerIds[i] = id;
            this._startTime[i] = System.currentTimeMillis();
            return;
         }
      }

      int originalLen = this._timerIds.length;
      Array.resize(this._timerIds, this._timerIds.length << 1);
      Array.resize(this._startTime, this._startTime.length << 1);
      this._timerIds[originalLen] = id;
      this._startTime[originalLen] = System.currentTimeMillis();
   }

   public final synchronized void stopTimer(int timerId, int uniqueId) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void appendSummary(StringBuffer sb, int timerId, String description) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getText() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
