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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.welyab.margelet.http.HttpClientPool;
import com.welyab.margelet.types.Response;

public class MethodInvoker {

    private Gson gson;

    private final List<ImmutableMap<String, Object>> configurationsList;

    private String telegramApiMethodName;

    private MethodInvoker() {
	configurationsList = new ArrayList<>();
    }

    private static final String TELEGRAM_API_URL_PARAMETERS[] = new String[] {
	    Constants.API_URL_PARAM_API_TOKEN,
	    Constants.API_URL_PARAM_METHOD_NAME
    };

    /**
     * 
     * @param type The type of Telegram method response.
     * 
     *            <p>
     *            <i>As documented, each Telegram method call returns a
     *            <code>Response</code></i> object, which contains the target
     *            method result and some other informations.
     * 
     * @param apiToken The Telegram API token.
     * 
     * @return
     * 
     * @see Response
     * @see Margelet
     */
    public Response<?> invoke(Type type, String apiToken) {
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null");

	initGson();

	try (CloseableHttpClient httpClient = HttpClientPool.getClient()) {
	    String targetUri = StringUtils.replaceEach(
		    Constants.API_URL,
		    TELEGRAM_API_URL_PARAMETERS,
		    new String[] {
			    apiToken, telegramApiMethodName
		    }
	    );

	    HttpGet httpGet = new HttpGet(targetUri);
	    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		TypeToken<?> typeToken = TypeToken.getParameterized(Response.class, type);
		return gson.fromJson(json, typeToken.getType());
	    }
	} catch (IOException e) {
	    throw new MargeletException("Fail during communication with Telegram HTTP services", e);
	}
    }

    private Gson initGson() {
	if (gson == null) {
	    GsonBuilder gsonBuilder = new GsonBuilder();

	    boolean formatJson = Boolean.parseBoolean(
		    getConfigurationValue(
			    Constants.CONFIG_FORMAT_JSON,
			    Constants.CONFIG_FORMAT_JSON_DEFAULT
		    ).toString()
	    );
	    if (formatJson) {
		gsonBuilder = gsonBuilder.setPrettyPrinting();
	    }

	    gson = gsonBuilder.create();
	}

	return gson;
    }

    private <E> E getConfigurationValue(String property, E defaultValue) {
	List<ImmutableMap<String, Object>> confList = getConfigurationsList();
	for (int i = confList.size() - 1; i >= 0; i--) {
	    ImmutableMap<String, Object> map = confList.get(i);
	    Object value = map.get(property);
	    if (value != null) {
		@SuppressWarnings("unchecked")
		E v = (E) value;
		return v;
	    }
	}
	return defaultValue;
    }

    public String getTelegramApiMethodName() {
	return telegramApiMethodName;
    }

    private void setTelegramApiMethodName(String telegramApiMethodName) {
	this.telegramApiMethodName = telegramApiMethodName;
    }

    private List<ImmutableMap<String, Object>> getConfigurationsList() {
	return configurationsList;
    }

    public static <E> Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private MethodInvoker methodInvoker;

	private boolean finished;

	private Builder() {
	    methodInvoker = new MethodInvoker();
	    finished = false;
	}

	public Builder setMethod(String telegramApiMethodName) {
	    checkFinished();
	    Preconditions.checkState(
		    StringUtils.isBlank(methodInvoker.getTelegramApiMethodName()),
		    "Parameter 'telegramApiMethodName' already been set"
	    );
	    Preconditions.checkNotNull(
		    telegramApiMethodName,
		    "Parameter 'telegramApiMethodName' cannot be null"
	    );
	    methodInvoker.setTelegramApiMethodName(telegramApiMethodName);
	    return this;
	}

	public Builder addConfiguration(String property, Object value) {
	    checkFinished();
	    Preconditions.checkNotNull(property, "Parameter 'property' cannot be null");
	    Preconditions.checkNotNull(value, "Parameter 'value' cannot be null");
	    addConfigurations(ImmutableMap.of(property, value));
	    return this;
	}

	public Builder addConfigurations(ImmutableMap<String, Object> configurations) {
	    checkFinished();
	    Preconditions.checkNotNull(configurations, "Parameter 'configurations' cannot be null");
	    methodInvoker.getConfigurationsList().add(configurations);
	    return this;
	}

	public Builder addParameter(String parameter, Object value) {
	    checkFinished();
	    Preconditions.checkNotNull(parameter, "Parameter 'parameter' cannot be null");
	    return this;
	}

	public Builder addParameters(ImmutableMap<String, Object> parameters) {
	    checkFinished();
	    Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	    return this;
	}

	public MethodInvoker build() {
	    checkFinished();
	    finished = true;
	    return methodInvoker;
	}

	private void checkFinished() {
	    if (finished) {
		throw new IllegalStateException("The builder already been finished");
	    }
	}
    }
}
