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
 * This object represents an incoming update.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#update">https://core.telegram.org/bots/api#update</a>.
 * 
 * @author Welyab Paula
 */
public class Update {

    @SerializedName("update_id")
    @SuppressWarnings("javadoc")
    private Integer updateId;

    @SerializedName("message")
    @SuppressWarnings("javadoc")
    private Message message;

    @SerializedName("edited_message")
    @SuppressWarnings("javadoc")
    private Message editedMessage;

    @SerializedName("channel_post")
    @SuppressWarnings("javadoc")
    private Message channelPost;

    @SerializedName("edited_channel_post")
    @SuppressWarnings("javadoc")
    private Message editedChannelPost;

    @SerializedName("inline_query")
    @SuppressWarnings("javadoc")
    private InlineQuery inlineQuery;

    @SerializedName("chosen_inline_result")
    @SuppressWarnings("javadoc")
    private ChosenInlineResult chosenInlineResult;

    @SerializedName("callback_query")
    @SuppressWarnings("javadoc")
    private CallbackQuery callbackQuery;

    @SerializedName("shipping_query")
    @SuppressWarnings("javadoc")
    private ShippingQuery shippingQuery;

    @SerializedName("pre_checkout_query")
    @SuppressWarnings("javadoc")
    private PreCheckoutQuery preCheckoutQuery;

    @SuppressWarnings("javadoc")
    public Integer getUpdateId() {
	return updateId;
    }

    @SuppressWarnings("javadoc")
    public void setUpdateId(Integer updateId) {
	this.updateId = updateId;
    }

    @SuppressWarnings("javadoc")
    public Message getMessage() {
	return message;
    }

    @SuppressWarnings("javadoc")
    public void setMessage(Message message) {
	this.message = message;
    }

    @SuppressWarnings("javadoc")
    public Message getEditedMessage() {
	return editedMessage;
    }

    @SuppressWarnings("javadoc")
    public void setEditedMessage(Message editedMessage) {
	this.editedMessage = editedMessage;
    }

    @SuppressWarnings("javadoc")
    public Message getChannelPost() {
	return channelPost;
    }

    @SuppressWarnings("javadoc")
    public void setChannelPost(Message channelPost) {
	this.channelPost = channelPost;
    }

    @SuppressWarnings("javadoc")
    public Message getEditedChannelPost() {
	return editedChannelPost;
    }

    @SuppressWarnings("javadoc")
    public void setEditedChannelPost(Message editedChannelPost) {
	this.editedChannelPost = editedChannelPost;
    }

    @SuppressWarnings("javadoc")
    public InlineQuery getInlineQuery() {
	return inlineQuery;
    }

    @SuppressWarnings("javadoc")
    public void setInlineQuery(InlineQuery inlineQuery) {
	this.inlineQuery = inlineQuery;
    }

    @SuppressWarnings("javadoc")
    public ChosenInlineResult getChosenInlineResult() {
	return chosenInlineResult;
    }

    @SuppressWarnings("javadoc")
    public void setChosenInlineResult(ChosenInlineResult chosenInlineResult) {
	this.chosenInlineResult = chosenInlineResult;
    }

    @SuppressWarnings("javadoc")
    public CallbackQuery getCallbackQuery() {
	return callbackQuery;
    }

    @SuppressWarnings("javadoc")
    public void setCallbackQuery(CallbackQuery callbackQuery) {
	this.callbackQuery = callbackQuery;
    }

    @SuppressWarnings("javadoc")
    public ShippingQuery getShippingQuery() {
	return shippingQuery;
    }

    @SuppressWarnings("javadoc")
    public void setShippingQuery(ShippingQuery shippingQuery) {
	this.shippingQuery = shippingQuery;
    }

    @SuppressWarnings("javadoc")
    public PreCheckoutQuery getPreCheckoutQuery() {
	return preCheckoutQuery;
    }

    @SuppressWarnings("javadoc")
    public void setPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) {
	this.preCheckoutQuery = preCheckoutQuery;
    }

    @Override
    public String toString() {
	return "Update [updateId=" + updateId + "]";
    }
}
