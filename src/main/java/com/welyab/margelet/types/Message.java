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

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * This object represents a message.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api#message">https://core.telegram.org/bots/api#message</a>.
 * 
 * @author Welyab Paula
 */
public class Message {

    @SerializedName("message_id")
    @SuppressWarnings("javadoc")
    private Integer messageId;

    @SerializedName("from")
    @SuppressWarnings("javadoc")
    private User from;

    @SerializedName("date")
    @SuppressWarnings("javadoc")
    private Integer date;

    @SerializedName("chat")
    @SuppressWarnings("javadoc")
    private Chat chat;

    @SerializedName("forward_from")
    @SuppressWarnings("javadoc")
    private User forwardFrom;

    @SerializedName("forward_from_chat")
    @SuppressWarnings("javadoc")
    private Chat forwardFromChat;

    @SerializedName("forward_from_message_id")
    @SuppressWarnings("javadoc")
    private Integer forwardFromMessageId;

    @SerializedName("forward_signature")
    @SuppressWarnings("javadoc")
    private String forwardSignature;

    @SerializedName("forward_date")
    @SuppressWarnings("javadoc")
    private Integer forwardDate;

    @SerializedName("reply_to_message")
    @SuppressWarnings("javadoc")
    private Message replyToMessage;

    @SerializedName("edit_date")
    @SuppressWarnings("javadoc")
    private Integer editDate;

    @SerializedName("media_group_id")
    @SuppressWarnings("javadoc")
    private String mediaGroupId;

    @SerializedName("author_signature")
    @SuppressWarnings("javadoc")
    private String authorSignature;

    @SerializedName("text")
    @SuppressWarnings("javadoc")
    private String text;

    @SerializedName("entities")
    @SuppressWarnings("javadoc")
    private List<MessageEntity> entities;

    @SerializedName("caption_entities")
    @SuppressWarnings("javadoc")
    private List<MessageEntity> captionEntities;

    @SerializedName("audio")
    @SuppressWarnings("javadoc")
    private Audio audio;

    @SerializedName("document")
    @SuppressWarnings("javadoc")
    private Document document;

    @SerializedName("game")
    @SuppressWarnings("javadoc")
    private Game game;

    @SerializedName("photo")
    @SuppressWarnings("javadoc")
    private List<PhotoSize> photo;

    @SerializedName("sticker")
    @SuppressWarnings("javadoc")
    private Sticker sticker;

    @SerializedName("video")
    @SuppressWarnings("javadoc")
    private Video video;

    @SerializedName("voice")
    @SuppressWarnings("javadoc")
    private Voice voice;

    @SerializedName("video_note")
    @SuppressWarnings("javadoc")
    private VideoNote videoNote;

    @SerializedName("caption")
    @SuppressWarnings("javadoc")
    private String caption;

    @SerializedName("contact")
    @SuppressWarnings("javadoc")
    private Contact contact;

    @SerializedName("location")
    @SuppressWarnings("javadoc")
    private Location location;

    @SerializedName("venue")
    @SuppressWarnings("javadoc")
    private Venue venue;

    @SerializedName("new_chat_members")
    @SuppressWarnings("javadoc")
    private List<User> newChatMembers;

    @SerializedName("left_chat_member")
    @SuppressWarnings("javadoc")
    private User leftChatMember;

    @SerializedName("new_chat_title")
    @SuppressWarnings("javadoc")
    private String newChat_title;

    @SerializedName("new_chat_photo")
    @SuppressWarnings("javadoc")
    private List<PhotoSize> new_chat_photo;

    @SerializedName("delete_chat_photo")
    @SuppressWarnings("javadoc")
    private Boolean deleteChatPhoto;

    @SerializedName("group_chat_created")
    @SuppressWarnings("javadoc")
    private Boolean groupChatCreated;

    @SerializedName("supergroup_chat_created")
    @SuppressWarnings("javadoc")
    private Boolean supergroupChatCreated;

    @SerializedName("channel_chat_created")
    @SuppressWarnings("javadoc")
    private Boolean channel_chat_created;

    @SerializedName("migrate_to_chat_id")
    @SuppressWarnings("javadoc")
    private Integer migrateToChatId;

    @SerializedName("migrate_from_chat_id")
    @SuppressWarnings("javadoc")
    private Integer migrateFromChatId;

    @SerializedName("pinned_message")
    @SuppressWarnings("javadoc")
    private Message pinnedMessage;

    @SerializedName("invoice")
    @SuppressWarnings("javadoc")
    private Invoice invoice;

    @SerializedName("successful_payment")
    @SuppressWarnings("javadoc")
    private SuccessfulPayment successfulPayment;

    @SerializedName("connected_website")
    @SuppressWarnings("javadoc")
    private String connectedWebsite;

    @SuppressWarnings("javadoc")
    public Integer getMessageId() {
	return messageId;
    }

    @SuppressWarnings("javadoc")
    public void setMessageId(Integer messageId) {
	this.messageId = messageId;
    }

    @SuppressWarnings("javadoc")
    public User getFrom() {
	return from;
    }

    @SuppressWarnings("javadoc")
    public void setFrom(User from) {
	this.from = from;
    }

    @SuppressWarnings("javadoc")
    public Integer getDate() {
	return date;
    }

    @SuppressWarnings("javadoc")
    public void setDate(Integer date) {
	this.date = date;
    }

    @SuppressWarnings("javadoc")
    public Chat getChat() {
	return chat;
    }

    @SuppressWarnings("javadoc")
    public void setChat(Chat chat) {
	this.chat = chat;
    }

    @SuppressWarnings("javadoc")
    public User getForwardFrom() {
	return forwardFrom;
    }

    @SuppressWarnings("javadoc")
    public void setForwardFrom(User forwardFrom) {
	this.forwardFrom = forwardFrom;
    }

    @SuppressWarnings("javadoc")
    public Chat getForwardFromChat() {
	return forwardFromChat;
    }

    @SuppressWarnings("javadoc")
    public void setForwardFromChat(Chat forwardFromChat) {
	this.forwardFromChat = forwardFromChat;
    }

    @SuppressWarnings("javadoc")
    public Integer getForwardFromMessageId() {
	return forwardFromMessageId;
    }

    @SuppressWarnings("javadoc")
    public void setForwardFromMessageId(Integer forwardFromMessageId) {
	this.forwardFromMessageId = forwardFromMessageId;
    }

    @SuppressWarnings("javadoc")
    public String getForwardSignature() {
	return forwardSignature;
    }

    @SuppressWarnings("javadoc")
    public void setForwardSignature(String forwardSignature) {
	this.forwardSignature = forwardSignature;
    }

    @SuppressWarnings("javadoc")
    public Integer getForwardDate() {
	return forwardDate;
    }

    @SuppressWarnings("javadoc")
    public void setForwardDate(Integer forwardDate) {
	this.forwardDate = forwardDate;
    }

    @SuppressWarnings("javadoc")
    public Message getReplyToMessage() {
	return replyToMessage;
    }

    @SuppressWarnings("javadoc")
    public void setReplyToMessage(Message replyToMessage) {
	this.replyToMessage = replyToMessage;
    }

    @SuppressWarnings("javadoc")
    public Integer getEditDate() {
	return editDate;
    }

    @SuppressWarnings("javadoc")
    public void setEditDate(Integer editDate) {
	this.editDate = editDate;
    }

    @SuppressWarnings("javadoc")
    public String getMediaGroupId() {
	return mediaGroupId;
    }

    @SuppressWarnings("javadoc")
    public void setMediaGroupId(String mediaGroupId) {
	this.mediaGroupId = mediaGroupId;
    }

    @SuppressWarnings("javadoc")
    public String getAuthorSignature() {
	return authorSignature;
    }

    @SuppressWarnings("javadoc")
    public void setAuthorSignature(String authorSignature) {
	this.authorSignature = authorSignature;
    }

    @SuppressWarnings("javadoc")
    public String getText() {
	return text;
    }

    @SuppressWarnings("javadoc")
    public void setText(String text) {
	this.text = text;
    }

    @SuppressWarnings("javadoc")
    public List<MessageEntity> getEntities() {
	return entities;
    }

    @SuppressWarnings("javadoc")
    public void setEntities(List<MessageEntity> entities) {
	this.entities = entities;
    }

    @SuppressWarnings("javadoc")
    public List<MessageEntity> getCaptionEntities() {
	return captionEntities;
    }

    @SuppressWarnings("javadoc")
    public void setCaptionEntities(List<MessageEntity> captionEntities) {
	this.captionEntities = captionEntities;
    }

    @SuppressWarnings("javadoc")
    public Audio getAudio() {
	return audio;
    }

    @SuppressWarnings("javadoc")
    public void setAudio(Audio audio) {
	this.audio = audio;
    }

    @SuppressWarnings("javadoc")
    public Document getDocument() {
	return document;
    }

    @SuppressWarnings("javadoc")
    public void setDocument(Document document) {
	this.document = document;
    }

    @SuppressWarnings("javadoc")
    public Game getGame() {
	return game;
    }

    @SuppressWarnings("javadoc")
    public void setGame(Game game) {
	this.game = game;
    }

    @SuppressWarnings("javadoc")
    public List<PhotoSize> getPhoto() {
	return photo;
    }

    @SuppressWarnings("javadoc")
    public void setPhoto(List<PhotoSize> photo) {
	this.photo = photo;
    }

    @SuppressWarnings("javadoc")
    public Sticker getSticker() {
	return sticker;
    }

    @SuppressWarnings("javadoc")
    public void setSticker(Sticker sticker) {
	this.sticker = sticker;
    }

    @SuppressWarnings("javadoc")
    public Video getVideo() {
	return video;
    }

    @SuppressWarnings("javadoc")
    public void setVideo(Video video) {
	this.video = video;
    }

    @SuppressWarnings("javadoc")
    public Voice getVoice() {
	return voice;
    }

    @SuppressWarnings("javadoc")
    public void setVoice(Voice voice) {
	this.voice = voice;
    }

    @SuppressWarnings("javadoc")
    public VideoNote getVideoNote() {
	return videoNote;
    }

    @SuppressWarnings("javadoc")
    public void setVideoNote(VideoNote videoNote) {
	this.videoNote = videoNote;
    }

    @SuppressWarnings("javadoc")
    public String getCaption() {
	return caption;
    }

    @SuppressWarnings("javadoc")
    public void setCaption(String caption) {
	this.caption = caption;
    }

    @SuppressWarnings("javadoc")
    public Contact getContact() {
	return contact;
    }

    @SuppressWarnings("javadoc")
    public void setContact(Contact contact) {
	this.contact = contact;
    }

    @SuppressWarnings("javadoc")
    public Location getLocation() {
	return location;
    }

    @SuppressWarnings("javadoc")
    public void setLocation(Location location) {
	this.location = location;
    }

    @SuppressWarnings("javadoc")
    public Venue getVenue() {
	return venue;
    }

    @SuppressWarnings("javadoc")
    public void setVenue(Venue venue) {
	this.venue = venue;
    }

    @SuppressWarnings("javadoc")
    public List<User> getNewChatMembers() {
	return newChatMembers;
    }

    @SuppressWarnings("javadoc")
    public void setNewChatMembers(List<User> newChatMembers) {
	this.newChatMembers = newChatMembers;
    }

    @SuppressWarnings("javadoc")
    public User getLeftChatMember() {
	return leftChatMember;
    }

    @SuppressWarnings("javadoc")
    public void setLeftChatMember(User leftChatMember) {
	this.leftChatMember = leftChatMember;
    }

    @SuppressWarnings("javadoc")
    public String getNewChat_title() {
	return newChat_title;
    }

    @SuppressWarnings("javadoc")
    public void setNewChat_title(String newChat_title) {
	this.newChat_title = newChat_title;
    }

    @SuppressWarnings("javadoc")
    public List<PhotoSize> getNew_chat_photo() {
	return new_chat_photo;
    }

    @SuppressWarnings("javadoc")
    public void setNew_chat_photo(List<PhotoSize> new_chat_photo) {
	this.new_chat_photo = new_chat_photo;
    }

    @SuppressWarnings("javadoc")
    public Boolean getDeleteChatPhoto() {
	return deleteChatPhoto;
    }

    @SuppressWarnings("javadoc")
    public void setDeleteChatPhoto(Boolean deleteChatPhoto) {
	this.deleteChatPhoto = deleteChatPhoto;
    }

    @SuppressWarnings("javadoc")
    public Boolean getGroupChatCreated() {
	return groupChatCreated;
    }

    @SuppressWarnings("javadoc")
    public void setGroupChatCreated(Boolean groupChatCreated) {
	this.groupChatCreated = groupChatCreated;
    }

    @SuppressWarnings("javadoc")
    public Boolean getSupergroupChatCreated() {
	return supergroupChatCreated;
    }

    @SuppressWarnings("javadoc")
    public void setSupergroupChatCreated(Boolean supergroupChatCreated) {
	this.supergroupChatCreated = supergroupChatCreated;
    }

    @SuppressWarnings("javadoc")
    public Boolean getChannel_chat_created() {
	return channel_chat_created;
    }

    @SuppressWarnings("javadoc")
    public void setChannel_chat_created(Boolean channel_chat_created) {
	this.channel_chat_created = channel_chat_created;
    }

    @SuppressWarnings("javadoc")
    public Integer getMigrateToChatId() {
	return migrateToChatId;
    }

    @SuppressWarnings("javadoc")
    public void setMigrateToChatId(Integer migrateToChatId) {
	this.migrateToChatId = migrateToChatId;
    }

    @SuppressWarnings("javadoc")
    public Integer getMigrateFromChatId() {
	return migrateFromChatId;
    }

    @SuppressWarnings("javadoc")
    public void setMigrateFromChatId(Integer migrateFromChatId) {
	this.migrateFromChatId = migrateFromChatId;
    }

    @SuppressWarnings("javadoc")
    public Message getPinnedMessage() {
	return pinnedMessage;
    }

    @SuppressWarnings("javadoc")
    public void setPinnedMessage(Message pinnedMessage) {
	this.pinnedMessage = pinnedMessage;
    }

    @SuppressWarnings("javadoc")
    public Invoice getInvoice() {
	return invoice;
    }

    @SuppressWarnings("javadoc")
    public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
    }

    @SuppressWarnings("javadoc")
    public SuccessfulPayment getSuccessfulPayment() {
	return successfulPayment;
    }

    @SuppressWarnings("javadoc")
    public void setSuccessfulPayment(SuccessfulPayment successfulPayment) {
	this.successfulPayment = successfulPayment;
    }

    @SuppressWarnings("javadoc")
    public String getConnectedWebsite() {
	return connectedWebsite;
    }

    @SuppressWarnings("javadoc")
    public void setConnectedWebsite(String connectedWebsite) {
	this.connectedWebsite = connectedWebsite;
    }

    @Override
    public String toString() {
	return "Message [messageId=" + messageId + "]";
    }
}
