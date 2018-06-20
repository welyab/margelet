package com.welyab.margelet.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientPool {

    public static CloseableHttpClient getClient() {
	return HttpClientBuilder.create().build();
    }
}
