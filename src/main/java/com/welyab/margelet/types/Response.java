/*
 * Copyright 2018 Welyab da Silva Paula
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.welyab.margelet.types;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("ok")
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
