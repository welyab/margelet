package com.welyab.margelet.types;

import com.google.gson.annotations.SerializedName;

public class ResponseParameters {

    @SerializedName("migrate_to_chat_id")
    private Integer migrateToChatId;

    @SerializedName("retry_after")
    private Integer retryAfter;
}
