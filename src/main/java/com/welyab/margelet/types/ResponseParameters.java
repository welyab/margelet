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

/**
 * Contains information about why a request was unsuccessful.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#responseparameters">https://core.telegram.org/bots/api#responseparameters</a>
 * 
 * @author Welyab Paula
 */
public class ResponseParameters {

    @SerializedName("migrate_to_chat_id")
    @SuppressWarnings("javadoc")
    private Integer migrateToChatId;

    @SerializedName("retry_after")
    @SuppressWarnings("javadoc")
    private Integer retryAfter;

    @SuppressWarnings("javadoc")
    public Integer getMigrateToChatId() {
	return migrateToChatId;
    }

    @SuppressWarnings("javadoc")
    public void setMigrateToChatId(Integer migrateToChatId) {
	this.migrateToChatId = migrateToChatId;
    }

    @SuppressWarnings("javadoc")
    public Integer getRetryAfter() {
	return retryAfter;
    }

    @SuppressWarnings("javadoc")
    public void setRetryAfter(Integer retryAfter) {
	this.retryAfter = retryAfter;
    }
}
