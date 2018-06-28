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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.welyab.margelet.gson.GsonFactory;
import com.welyab.margelet.http.HttpClientPool;
import com.welyab.margelet.types.Response;

/**
 * A representation of an Telegram Bot API method. This class performs HTTP
 * requests and JSON processing.
 * 
 * @author Welyab Paula
 */
public class TelegramMethod {

    /**
     * The name of the method to be called in Telegram service.
     */
    private final String methodName;

    /**
     * The expected type as result of calling the Telegram method. This result
     * is always wrapped into a <code>Response</code> object. So, the actual
     * returned type from Telegram service will be
     * <code>Response&lt;type&gt;</code>. That type is marked in
     * {@link #responseType} field.
     */
    private final Type type;

    /**
     * The Telegram Bot API auth token.
     */
    private final String apiToken;

    /**
     * The target URL. This value is composite by API auth token and the method
     * name.
     */
    private final String targetUrl;

    /**
     * The actual result type for Telegram method calling. All Telegram methods
     * that return JSON documents as its results are mapped as
     * <code>Response</code> object. The underlying result for an specific
     * method name is placed as the <code>"result"</code> in the
     * <code>Response</code> object. So, this field holds something like
     * <code>Response&lt;type&gt;</code>.
     * 
     * @see #type
     * @see Response
     */
    private final Type responseType;

    /**
     * Creates a new <code>TelegramMethod</code> representation.
     * 
     * <p>
     * This method is thread safe.
     * 
     * @param methodName The name of the method to be called in Telegram
     *            service.
     * @param type The expected type as result of calling the Telegram method.
     *            This result is always wrapped into a <code>Response</code>
     *            object. So, the actual returned type from Telegram service
     *            will be <code>Response&lt;type&gt;</code>. That type is marked
     *            in {@link #responseType} field.
     * @param apiToken The API token to access Telegram services.
     */
    public TelegramMethod(String methodName, Type type, String apiToken) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null");

	this.methodName = methodName;
	this.type = type;
	this.apiToken = apiToken;
	this.targetUrl = createTargetUrl(methodName, apiToken);
	this.responseType = TypeToken.getParameterized(Response.class, type).getType();
    }

    public Response<?> call(
	    ImmutableMap<String, Object> parameters,
	    Configuration configuration
    ) {
	return call(
		(Object) parameters,
		configuration
	);
    }

    public Response<?> call(Object parameters, Configuration configuration) {
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(configuration, "Parameter 'configuration' cannot be null");

	Gson gson = GsonFactory.create(configuration);
	HttpEntity parametersHttpEntity = null;
	if (parameters != null && !((Map<?, ?>) parameters).isEmpty()) {
	    String jsonParameters = gson.toJson(parameters);
	    parametersHttpEntity = new ByteArrayEntity(jsonParameters.getBytes(StandardCharsets.UTF_8));
	}

	try (CloseableHttpClient httpClient = HttpClientPool.getClient(configuration)) {
	    HttpPost request = new HttpPost(targetUrl);
	    if (parametersHttpEntity != null) {
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		request.setEntity(parametersHttpEntity);
	    }
	    try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		InputStream inputStream = responseHttpEntity.getContent();
		InputStreamReader reader = new InputStreamReader(inputStream);
		return gson.fromJson(reader, responseType);
	    }
	} catch (IOException e) {
	    throw new MargeletException("Fail during communication with Telegram services", e);
	}
    }

    public String getMethodName() {
	return methodName;
    }

    public Type getType() {
	return type;
    }

    public String getApiToken() {
	return apiToken;
    }

    private static String createTargetUrl(String methodName, String apiToken) {
	return StringUtils.replaceEach(
		Constants.URL_API,
		new String[] {
			Constants.URL_API_PARAM_API_TOKEN,
			Constants.URL_API_PARAM_METHOD_NAME
		},
		new String[] {
			apiToken,
			methodName
		}
	);
    }
}
