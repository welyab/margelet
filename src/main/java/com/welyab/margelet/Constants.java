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

package com.welyab.margelet;

/**
 * Margelet constants.
 * 
 * <p>
 * Contains endpoint URLs for Telegram Services, Telegram Bot API method names
 * and configuration properties for Margelet.
 * 
 * <p>
 * The list Telegram Bot API method names is the set of constants starting with
 * <code>"METHOD_"</code>. This list may change in future releases of Margelet
 * as Telegram Bot API changes its API.
 * 
 * </p>
 * The list of Margelet configurations properties is the set of constants
 * starting with <code>"CONFIG_"</code>.
 * 
 * @author Welyab Paula
 */
public class Constants {

    @SuppressWarnings("javadoc")
    private Constants() {
    }

    /**
     * The main URL provided by Telegram Bot API to access services.
     * 
     * <p>
     * This URL expect two parameters, the <code>apiToken</code> and
     * <code>methodName</code>.
     * 
     * <p>
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#making-requests">https://core.telegram.org/bots/api#making-requests</a>.
     * 
     * <p>
     * In the most cases, you will not need work with this constant. It is used
     * inside library internals to mount request URL.
     * 
     * @see #URL_API_PARAM_API_TOKEN
     * @see #URL_API_PARAM_METHOD_NAME
     */
    public static final String URL_API = "https://api.telegram.org/bot{apiToken}/{methodName}";

    /**
     * Parameter name marker to URL specified in {@link #URL_API}; this
     * parameter defines your API token to communicate with Telegram Bot API.
     * 
     * <p>
     * Should be used only with {@link #URL_API}.
     * 
     * <p>
     * In the most cases, you will not need work with this constant. It is used
     * inside library internals to mount request URL.
     */
    public static final String URL_API_PARAM_API_TOKEN = "{apiToken}";

    /**
     * Parameter name marker to URL specified in {@link #URL_API}; this
     * parameter defines which method will be called in the Telegram Bot API.
     * 
     * <p>
     * Should be used only with {@link #URL_API}.
     * 
     * <p>
     * In the most cases, you will not need work with this constant. It is used
     * inside library internals to mount request URL.
     */
    public static final String URL_API_PARAM_METHOD_NAME = "{methodName}";

    public static final String URL_FILE = "https://api.telegram.org/file/bot{apiToken}/{file_path}";

    public static final String URL_FILE_PARAM_API_TOKEN = "{apiToken}";

    public static final String URL_FILE_PARAM_FILE_PATH = "{file_path}";

    // ============================================================================
    // Telegram API methods
    // All entries starts with "METHOD_"
    // The list may change as Telegram Bot API changes as well
    // ============================================================================

    /**
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getme">https://core.telegram.org/bots/api#getme</a>
     */
    public static final String METHOD_GET_ME = "getMe";

    /**
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getupdates">https://core.telegram.org/bots/api#getupdates</a>
     */
    public static final String METHOD_GET_UPDATES = "getUpdates";

    /**
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getfile">https://core.telegram.org/bots/api#getfile</a>
     */
    public static final String METHOD_GET_FILE = "getFile";

    public static final String FILE_ID = "file_id";

    // ============================================================================
    // Available configurations provided by com.welyab.margelet
    // and its default values
    // All entries starts with "CONFIG_"
    // ============================================================================

    /**
     * Indicates that all method of Margelet that produces printable JSON
     * strings will format them.
     * 
     * <p>
     * Default value: {@link #CONFIG_FORMAT_JSON_DEFAULT}.
     */
    public static final String CONFIG_FORMAT_JSON = "TelegramBots.CONFIG_FORMAT_JSON";

    /**
     * Default value for the configuration property {@link #CONFIG_FORMAT_JSON}.
     */
    public static final Boolean CONFIG_FORMAT_JSON_DEFAULT = Boolean.FALSE;
}
