package com.welyab.margelet;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.reflect.TypeToken;

import com.welyab.margelet.types.Response;
import com.welyab.margelet.types.ResponseParameters;
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
 * Telegram docs: <a
 * href="https://core.telegram.org/bots/api">https://core.telegram.org/bots/api</a>
 * 
 * @author Welyab Paula
 */
public class Margelet {

    /**
     * The API token to auth the bot to access Telegram Bot API.
     */
    private final String apiToken;

    /**
     * The configuration.
     */
    private final ImmutableMap<String, Object> configurations;

    /**
     * Creates a new Telegram Bot Client.
     * 
     * @param apiToken The Telegram API token.
     */
    public Margelet(String apiToken) {
	this(
		Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null"),
		ImmutableMap.of()
	);
    }

    /**
     * Creates a new Telegram Bot Client.
     * 
     * @param apiToken The Telegram API token.
     * @param configurations The configuration.
     */
    public Margelet(String apiToken, ImmutableMap<String, Object> configurations) {
	Preconditions.checkNotNull(apiToken, "Parameter 'apiToken' cannot be null");
	Preconditions.checkNotNull(configurations, "Parameter 'configurations' cannot be null");
	this.apiToken = apiToken;
	this.configurations = configurations;
    }

    /**
     * Retrieves the current configuration object.
     * 
     * @return The configuration.
     */
    public ImmutableMap<String, Object> getConfigurations() {
	return configurations;
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
     * @return A {@link User} wrapped into a <code>Response</code>
     */
    public Response<User> getMe() {
	return callMethod(
		Constants.METHOD_GET_ME,
		ImmutableMap.of(),
		User.class,
		ImmutableMap.of()
	);
    }

    public <E> Response<List<E>> callMethodList(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Class<E> type,
	    ImmutableMap<String, Object> configurations
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configurations, "Parameter 'configurations' cannot be null");

	TypeToken<?> typeToken = TypeToken.getParameterized(List.class, type);

	@SuppressWarnings("unchecked")
	Response<List<E>> callMethod = (Response<List<E>>) callMethod(
		methodName,
		parameters,
		typeToken.getType(),
		configurations
	);

	return callMethod;
    }

    public <E> Response<E> callMethod(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    Class<E> type,
	    ImmutableMap<String, Object> configurations
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configurations, "Parameter 'configurations' cannot be null");

	Type _type = type;

	@SuppressWarnings("unchecked")
	Response<E> response = (Response<E>) callMethod(
		methodName,
		parameters,
		_type,
		configurations
	);

	return response;
    }

    public Response<Map<String, Object>> callMethod(
	    String methodName,
	    ImmutableMap<String, Object> parameters,
	    ImmutableMap<String, Object> configurations
    ) {
	TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);

	@SuppressWarnings("unchecked")
	Response<Map<String, Object>> response = (Response<Map<String, Object>>) callMethod(
		methodName,
		parameters,
		typeToken.getType(),
		configurations
	);

	return response;
    }

    /**
     *
     * The request is made to the URL designed in {@link Constants#API_URL}.
     * 
     * @param methodName The method name to call in the Telegram API.
     * @param parameters Parameters to passing to the called method.
     * @param type The expected result type.
     * @param configurations Specific configurations to method calling process.
     *            This configurations will override possible present
     *            configuration in <code>TelegramBot</code>.
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
	    ImmutableMap<String, Object> configurations
    ) {
	Preconditions.checkNotNull(methodName, "Parameter 'methodName' cannot be null");
	Preconditions.checkNotNull(parameters, "Parameter 'parameters' cannot be null");
	Preconditions.checkNotNull(type, "Parameter 'type' cannot be null");
	Preconditions.checkNotNull(configurations, "Parameter 'configurations' cannot be null");

	Response<?> response = MethodInvoker
		.builder()
		.setMethod(methodName)
		.addParameters(parameters)
		.addConfigurations(configurations)
		.build()
		.invoke(type, apiToken);

	return response;
    }
}
