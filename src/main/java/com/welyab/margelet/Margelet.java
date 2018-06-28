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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.ByteSource;
import com.google.gson.reflect.TypeToken;

import com.welyab.margelet.http.HttpClientPool;
import com.welyab.margelet.types.File;
import com.welyab.margelet.types.Response;
import com.welyab.margelet.types.ResponseParameters;
import com.welyab.margelet.types.Update;
import com.welyab.margelet.types.User;

/**
 * A bridge between Java and Telegram Bot API.
 * 
 * <p>
 * This class maps methods/types available in the Telegram Bot API to Java
 * methods and its respective types.
 * 
 * <pre>
 * Margelet margelet = new Margelet("[bot api token]");
 * Response<User> response = margelet.getMe();
 * if(response.isOk()) {
 *   User user = response.getResult();
 *   ...
 * }
 * </pre>
 * 
 * <p>
 * From Telegram docs:
 * <blockquote>...response from Telegram API contains a JSON object,
 * which always has a <code>Boolean</code> field <code>"ok"</code> and may have
 * an optional String field <code>"description"</code> with a human-readable
 * description of the result. If <code>"ok"</code> equals <code>true</code>, the
 * request was successful and the result of the query can be found in the
 * <code>"result"</code> field. In case of an unsuccessful request,
 * <code>"ok"</code> equals <code>false</code> and the error is explained in the
 * <code>"description"</code>. An Integer <code>"error_code"</code> field is
 * also returned, but its contents are subject to change in the future. Some
 * errors may also have an optional field <code>"parameters"</code> of the type
 * {@link ResponseParameters}, which can help to automatically handle the error.
 * </blockquote>
 * 
 * <p>
 * Here, each method method returns a <code>TelegramResponse</code> and the
 * target API method result can be retrieved by calling
 * {@link Response#getResult()}.
 * 
 * <p>
 * Margelet uses Google Gson to JSON processing, and Apache HttpComponents to
 * make HTTP requests.
 * 
 * <p>
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api">https://core.telegram.org/bots/api</a>
 * 
 * @author Welyab Paula
 * 
 * @see Response
 */
public class Margelet {

    /**
     * The API token to auth the bot to access Telegram Bot API.
     */
    private final String apiToken;

    /**
     * The configuration.
     */
    private final Configuration configuration;

    /**
     * Creates a new Telegram Bot Client.
     * 
     * @param apiToken The Telegram API token.
     */
    public Margelet(String apiToken) {
	this(
		Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null"),
		Configuration.empty()
	);
    }

    /**
     * Creates a new Telegram Bot Client.
     * 
     * @param apiToken The Telegram API token.
     * @param configuration The configuration.
     */
    public Margelet(String apiToken, Configuration configuration) {
	Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");
	this.apiToken = apiToken;
	this.configuration = configuration;
    }

    /**
     * Retrieves the current configuration object.
     * 
     * @return The configuration.
     */
    public Configuration getConfiguration() {
	return configuration;
    }

    /**
     * A simple method for testing your bot's auth token. Requires no
     * parameters. Returns basic information about the bot in form of a
     * {@link User} object.
     * 
     * <p>
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getme">https://core.telegram.org/bots/api#getme</a>
     * 
     * @return A {@link User} wrapped into a <code>Response</code> object.
     */
    public Response<User> getMe() {
	return callMethod(
		Constants.METHOD_GET_ME,
		ImmutableMap.of(),
		User.class,
		Configuration.empty()
	);
    }

    /**
     * Use this method to receive incoming updates using long polling. An Array
     * of Update objects is returned.
     * 
     * <p>
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getting-updates">https://core.telegram.org/bots/api#getting-updates</a>.
     * 
     * @return A list of {@link Update} wrapped into a <code>Update</code>
     *         object.
     */
    public Response<List<Update>> getUpdates() {
	return getUpdates(ImmutableMap.of());
    }

    /**
     * Use this method to get basic info about a file and prepare it for
     * downloading.
     * 
     * <p>
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getfile">https://core.telegram.org/bots/api#getfile</a>.
     * 
     * @param fileId File identifier to get info about
     * 
     * @return A {@link File} wrapped into a <code>Response</code> object.
     * 
     * @see #downloadFile(String)
     */
    public Response<File> getFile(String fileId) {
	return callMethod(
		Constants.METHOD_GET_FILE,
		ImmutableMap.of(Constants.FILE_ID, fileId),
		File.class,
		Configuration.empty()
	);
    }

    /**
     * Use this method to receive incoming updates using long polling. An Array
     * of Update objects is returned.
     * 
     * <p>
     * Telegram docs: <a
     * href="https://core.telegram.org/bots/api#getting-updates">https://core.telegram.org/bots/api#getting-updates</a>.
     * 
     * @param parameters The list of parameters.
     * 
     * @return A list of {@link Update} wrapped into a <code>Update</code>.
     */
    public Response<List<Update>> getUpdates(ImmutableMap<String, Object> parameters) {
	return callMethodList(
		Constants.METHOD_GET_UPDATES,
		parameters,
		Update.class,
		Configuration.empty()
	);
    }

    /**
     * Downloads the content of a file stored in the Telegram cloud.
     * 
     * <p>
     * Before calling this method, it's need to call the method
     * {@link #getFile(String)}, that works as preparation for file downloading.
     * 
     * @param filePath The file path to download. This value can be obtained
     *            from an <code>File</code> object, that is the response of
     *            method {@link #getFile(String)}.
     * 
     * @return The file representation in an <code>ByteSource</code> object.
     * 
     * @see Margelet#getFile(String)
     * @see File
     */
    public ByteSource downloadFile(String filePath) {
	Preconditions.checkNotNull(filePath, "Parameter 'filePath' cannot be null");
	String targetUrl = StringUtils.replaceEach(
		Constants.URL_FILE,
		new String[] {
			Constants.URL_FILE_PARAM_API_TOKEN,
			Constants.URL_FILE_PARAM_FILE_PATH
		},
		new String[] {
			apiToken,
			filePath
		}
	);
	try (CloseableHttpClient httpClient = HttpClientPool.getClient(configuration)) {
	    HttpGet httpGet = new HttpGet(targetUrl);
	    try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
		HttpEntity httpEntity = httpResponse.getEntity();
		byte[] bytes = EntityUtils.toByteArray(httpEntity);
		return ByteSource.wrap(bytes);
	    }
	} catch (IOException e) {
	    throw new MargeletException("Fail to download file", e);
	}
    }

    /**
     * A low level method caller for Telegram Bot API. This method receives
     * the method name to be called in the Telegram Bot API and the expected
     * response type, where this type will be returned as list of given type.
     * 
     * <p>
     * Similar to other Telegram Bot API methods, here is returned a
     * <code>Response</code> object.
     * 
     * <p>
     * A list of valid Telegram Bot API method names may be found in the
     * {@link Constants} class. The list of method name entries starts with
     * <code>"METHOD_"</code>.
     * 
     * <p>
     * The request is made to the URL designed in {@link Constants#URL_API}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param type The expected result type.
     * @param configuration Specific configuration to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>Margelet</code>.
     * 
     * @return A response from Telegram service. The <code>result</code> field
     *         will be a list. The list element is expected to be an type of
     *         given <code>type</code> parameter.
     * 
     * @see Result
     * @see Constants
     */
    public <E> Response<List<E>> callMethodList(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Class<E> type,
	    Configuration configuration
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");

	Type _type = type;

	Response<List<?>> response = callMethodList(
		methodName,
		parameters,
		_type,
		configuration
	);

	return unsafeCast(response);
    }

    /**
     * A low level method caller for Telegram Bot API. This method receives
     * the method name to be called in the Telegram Bot API and the expected
     * response type, where this type will be returned as list of given type.
     * 
     * <p>
     * Similar to other Telegram Bot API methods, here is returned a
     * <code>Response</code> object.
     * 
     * <p>
     * A list of valid Telegram Bot API method names may be found in the
     * {@link Constants} class. The list of method name entries starts with
     * <code>"METHOD_"</code>.
     * 
     * <p>
     * The request is made to the URL designed in {@link Constants#URL_API}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param type The expected result type.
     * @param configuration Specific configuration to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>Margelet</code>.
     * 
     * @return A response from Telegram service. The <code>result</code> field
     *         will be a list. The list element is expected to be an type of
     *         given <code>type</code> parameter.
     * 
     * @see Result
     * @see Constants
     */
    public Response<List<?>> callMethodList(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Type type,
	    Configuration configuration
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");

	TypeToken<?> typeToken = TypeToken.getParameterized(List.class, type);

	@SuppressWarnings("unchecked")
	Response<List<?>> callMethod = (Response<List<?>>) callMethod(
		methodName,
		parameters,
		typeToken.getType(),
		configuration
	);

	return callMethod;
    }

    /**
     * A low level method caller for Telegram Bot API. This method receives
     * the method name to be called in the Telegram Bot API and the expected
     * response type.
     * 
     * <p>
     * Similar to other Telegram Bot API methods, here is returned a
     * <code>Response</code> object.
     * 
     * <p>
     * A list of valid Telegram Bot API method names may be found in the
     * {@link Constants} class. The list of method name entries starts with
     * <code>"METHOD_"</code>.
     * 
     * <p>
     * The request is made to the URL designed in {@link Constants#URL_API}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param type The expected result type.
     * @param configuration Specific configuration to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>Margelet</code>.
     * 
     * @return The telegram result as a type of given <i>type</i> parameter,
     *         wrapped into a <code>Response</code> object.
     * 
     * @see Result
     * @see Constants
     */
    public <E> Response<E> callMethod(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Class<E> type,
	    Configuration configuration
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");

	// cast trick
	Type _type = type;

	@SuppressWarnings("unchecked")
	Response<E> response = (Response<E>) callMethod(
		methodName,
		parameters,
		_type,
		configuration
	);

	return response;
    }

    /**
     * A low level method caller for Telegram Bot API. This method receives
     * the method name to be called in the Telegram Bot API.
     * 
     * <p>
     * Similar to other Telegram Bot API methods, here is returned a
     * <code>Response</code> object.
     * 
     * <p>
     * A list of valid Telegram Bot API method names may be found in the
     * {@link Constants} class. The list of method name entries starts with
     * <code>"METHOD_"</code>.
     * 
     * <p>
     * The request is made to the URL designed in {@link Constants#URL_API}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param configuration Specific configuration to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>Margelet</code>.
     * 
     * @return A response from Telegram services. The <code>result</code> field
     *         will be a implementations of <code>java.util.Map</code>, as a
     *         generic result for JSON processing.
     * 
     * @see Result
     * @see Constants
     */
    public Response<Map<String, Object>> callMethod(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Configuration configuration
    ) {
	TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);

	@SuppressWarnings("unchecked")
	Response<Map<String, Object>> response = (Response<Map<String, Object>>) callMethod(
		methodName,
		parameters,
		typeToken.getType(),
		configuration
	);

	return response;
    }

    /**
     * A low level method caller for Telegram Bot API. This method receives
     * the method name to be called in the Telegram Bot API and the expected
     * response type.
     * 
     * <p>
     * Similar to other Telegram Bot API methods, here is returned a
     * <code>Response</code> object.
     * 
     * <p>
     * A list of valid Telegram Bot API method names may be found in the
     * {@link Constants} class. The list of method name entries starts with
     * <code>"METHOD_"</code>.
     * 
     * <p>
     * The request is made to the URL designed in {@link Constants#URL_API}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param type The expected result type.
     * @param configuration Specific configuration to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>Margelet</code>.
     * 
     * @return The telegram result as a type of given <i>type</i> parameter,
     *         wrapped into a <code>Response</code> object.
     * 
     * @see Result
     * @see Constants
     */
    public Response<?> callMethod(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Type type,
	    Configuration configuration
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");

	configuration = this.configuration.merge(configuration);

	TelegramMethod caller = new TelegramMethod(methodName, type, apiToken);
	return caller.call(parameters, configuration);
    }

    @SuppressWarnings("javadoc")
    private static <E> E unsafeCast(Object value) {
	@SuppressWarnings("unchecked")
	E e = (E) value;
	return e;
    }
}
