package net.rim.device.api.media;

public class MetaDataObject {
   private String _mimeType;
   private String _filename;
   private String _url;
   private int _pictureType = -1;
   private String _description;
   private byte[] _data;
   public static final int PICTURE_TYPE_UNDEFINED;
   public static final int PICTURE_TYPE_OTHER;
   public static final int PICTURE_TYPE_32x32_FILE_ICON;
   public static final int PICTURE_TYPE_OTHER_FILE_ICON;
   public static final int PICTURE_TYPE_COVER_FRONT;
   public static final int PICTURE_TYPE_COVER_BACK;
   public static final int PICTURE_TYPE_LEAFLET_PAGE;
   public static final int PICTURE_TYPE_MEDIA;
   public static final int PICTURE_TYPE_LEAD_ARTIST;
   public static final int PICTURE_TYPE_ARTIST;
   public static final int PICTURE_TYPE_CONDUCTOR;
   public static final int PICTURE_TYPE_BAND;
   public static final int PICTURE_TYPE_COMPOSER;
   public static final int PICTURE_TYPE_LYRICIST;
   public static final int PICTURE_TYPE_RECORDING_LOCATION;
   public static final int PICTURE_TYPE_DURING_RECORDING;
   public static final int PICTURE_TYPE_DURING_PERFORMANCE;
   public static final int PICTURE_TYPE_VIDEO_SCREEN_CAPTURE;
   public static final int PICTURE_TYPE_A_BRIGHT_COLORED_FISH;
   public static final int PICTURE_TYPE_ILLUSTRATION;
   public static final int PICTURE_TYPE_BAND_LOGOTYPE;
   public static final int PICTURE_TYPE_PUBLISHER_LOGOTYPE;

   public String getMIMEType() {
      return this._mimeType;
   }

   public void setMIMEType(String mimeType) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getFilename() {
      return this._filename;
   }

   public void setFilename(String filename) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getURL() {
      return this._url;
   }

   public void setURL(String url) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getPictureType() {
      return this._pictureType;
   }

   public void setPictureType(int pictureType) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getDescription() {
      return this._description;
   }

   public void setDescription(String description) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public byte[] getData() {
      return this._data;
   }

   public void setData(byte[] data) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
