package com.welyab.margelet.types;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents a file ready to be downloaded.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#file">https://core.telegram.org/bots/api#file</a>.
 * 
 * @author Welyab Paula
 */
public class File {

    @SerializedName("file_id")
    @SuppressWarnings("javadoc")
    private String fileId;

    @SerializedName("file_size")
    @SuppressWarnings("javadoc")
    private Integer fileSize;

    @SerializedName("file_path")
    @SuppressWarnings("javadoc")
    private String filePath;

    @SuppressWarnings("javadoc")
    public String getFileId() {
	return fileId;
    }

    @SuppressWarnings("javadoc")
    public void setFileId(String fileId) {
	this.fileId = fileId;
    }

    @SuppressWarnings("javadoc")
    public Integer getFileSize() {
	return fileSize;
    }

    @SuppressWarnings("javadoc")
    public void setFileSize(Integer fileSize) {
	this.fileSize = fileSize;
    }

    @SuppressWarnings("javadoc")
    public String getFilePath() {
	return filePath;
    }

    @SuppressWarnings("javadoc")
    public void setFilePath(String filePath) {
	this.filePath = filePath;
    }

    @Override
    public String toString() {
	return "File [fileId=" + fileId + "]";
    }
}
