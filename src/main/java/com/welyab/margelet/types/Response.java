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

import com.welyab.margelet.Margelet;

/**
 * All requests to Telegram API will return a <code>Response</code> object.
 * 
 * <p>
 * As documented, a response from Telegram will supply a object encoded into a
 * JSON document. This object contains a field named <code>"ok"</code>, with a
 * value <code>true</code>, or <code>false</code>, indicating if the request was
 * successful or not. If success, the actual result can be found in the
 * <code>"result"</code> field.
 * 
 * <p>
 * As a <code>Response</code> is used to many Telegram API methods, the type of
 * <code>"result"</code> field is generic. The class {@link Margelet} has
 * several method mappings that return parameterized types of
 * <code>Response</code>. For example:
 * 
 * <pre>
 * Margelet m = ...
 * Response&lt;User> userResponse = m.getMe();
 * if(userResponse.isOk()) {
 *   User user = userResponse.getResult();
 * }
 * </pre>
 * 
 * The <code><i>getMe</i></code> method is used to test your API Token. It
 * returns the bot information into a <code>User</code> object.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#making-requests">https://core.telegram.org/bots/api#making-requests</a>.
 * 
 * @author Welyab Paula
 *
 * @param <T> The type of underlying method result.
 * 
 * @see Margelet
 */
public class Response<T> {

    @SerializedName("ok")
    @SuppressWarnings("javadoc")
    private boolean ok;

    @SerializedName("description")
    @SuppressWarnings("javadoc")
    private String description;

    @SerializedName("error_code")
    @SuppressWarnings("javadoc")
    private Integer errorCode;

    @SerializedName("parameters")
    @SuppressWarnings("javadoc")
    private ResponseParameters parameters;

    /**
     * The result.
     * 
     * <p>
     * The actual type depends as the JSON document was processed.
     * 
     * @see Response
     * @see Margelet
     */
    @SerializedName("result")
    public T result;

    @SuppressWarnings("javadoc")
    public boolean isOk() {
	return ok;
    }

    @SuppressWarnings("javadoc")
    public void setOk(boolean ok) {
	this.ok = ok;
    }

    @SuppressWarnings("javadoc")
    public String getDescription() {
	return description;
    }

    @SuppressWarnings("javadoc")
    public void setDescription(String description) {
	this.description = description;
    }

    @SuppressWarnings("javadoc")
    public Integer getErrorCode() {
	return errorCode;
    }

    @SuppressWarnings("javadoc")
    public void setErrorCode(Integer errorCode) {
	this.errorCode = errorCode;
    }

    @SuppressWarnings("javadoc")
    public ResponseParameters getParameters() {
	return parameters;
    }

    @SuppressWarnings("javadoc")
    public void setParameters(ResponseParameters parameters) {
	this.parameters = parameters;
    }

    /**
     * The result.
     * 
     * <p>
     * The actual type depends as the JSON document was processed.
     * 
     * @return The result.
     * 
     * @see Response
     * @see Margelet
     */
    public T getResult() {
	return result;
    }

    /**
     * Adjusts the result.
     * 
     * @param result The result.
     * 
     * @see Response
     * @see Margelet
     */
    public void setResult(T result) {
	this.result = result;
    }
}
