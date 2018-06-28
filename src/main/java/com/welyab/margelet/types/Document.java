package com.welyab.margelet.types;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents a general file (as opposed to photos, voice messages
 * and audio files).
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#document">https://core.telegram.org/bots/api#document</a>.
 * 
 * @author Welyab Paula
 */
public class Document {

    @SerializedName("file_id")
    @SuppressWarnings("javadoc")
    private String fileId;

    @SerializedName("thumb")
    @SuppressWarnings("javadoc")
    private PhotoSize thumb;

    @SerializedName("file_name")
    @SuppressWarnings("javadoc")
    private String fileName;

    @SerializedName("mime_type")
    @SuppressWarnings("javadoc")
    private String mimeType;

    @SerializedName("file_size")
    @SuppressWarnings("javadoc")
    private Integer fileSize;

    @SuppressWarnings("javadoc")
    public String getFileId() {
	return fileId;
    }

    @SuppressWarnings("javadoc")
    public void setFileId(String fileId) {
	this.fileId = fileId;
    }

    @SuppressWarnings("javadoc")
    public PhotoSize getThumb() {
	return thumb;
    }

    @SuppressWarnings("javadoc")
    public void setThumb(PhotoSize thumb) {
	this.thumb = thumb;
    }

    @SuppressWarnings("javadoc")
    public String getFileName() {
	return fileName;
    }

    @SuppressWarnings("javadoc")
    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    @SuppressWarnings("javadoc")
    public String getMimeType() {
	return mimeType;
    }

    @SuppressWarnings("javadoc")
    public void setMimeType(String mimeType) {
	this.mimeType = mimeType;
    }

    @SuppressWarnings("javadoc")
    public Integer getFileSize() {
	return fileSize;
    }

    @SuppressWarnings("javadoc")
    public void setFileSize(Integer fileSize) {
	this.fileSize = fileSize;
    }

    @Override
    public String toString() {
	return "Document [fileId=" + fileId + "]";
    }
}
