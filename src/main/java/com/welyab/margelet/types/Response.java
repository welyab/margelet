package com.welyab.margelet.types;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("")
    private boolean ok;

    @SerializedName("description")
    private String description;

    @SerializedName("error_code")
    private Integer errorCode;

    @SerializedName("parameters")
    private ResponseParameters parameters;

    @SerializedName("result")
    public T result;

    public boolean isOk() {
	return ok;
    }

    public void setOk(boolean ok) {
	this.ok = ok;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
	this.errorCode = errorCode;
    }

    public ResponseParameters getParameters() {
	return parameters;
    }

    public void setParameters(ResponseParameters parameters) {
	this.parameters = parameters;
    }

    public T getResult() {
	return result;
    }

    public void setResult(T result) {
	this.result = result;
    }
}
