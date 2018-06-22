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
 * This object represents a Telegram user or bot.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#user">https://core.telegram.org/bots/api#user</a>
 * 
 * @author Welyab Paula
 */
public class User {

    @SuppressWarnings("javadoc")
    private Integer id;

    @SerializedName("is_bot")
    private Boolean bot;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("username")
    private String username;

    @SerializedName("language_code")
    private String languageCode;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Boolean getBot() {
	return bot;
    }

    public void setBot(Boolean bot) {
	this.bot = bot;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getLanguageCode() {
	return languageCode;
    }

    public void setLanguageCode(String languageCode) {
	this.languageCode = languageCode;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + "]";
    }
}
