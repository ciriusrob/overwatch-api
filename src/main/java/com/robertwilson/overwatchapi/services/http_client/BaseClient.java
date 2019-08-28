package com.robertwilson.overwatchapi.services.http_client;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.services.http_client
 * Class: BaseClient
 */
@Service
public class BaseClient
{
    @Value("${overwatch.base-url}")
    private String baseUrl;

    @Async
    public CompletableFuture<Object> get( String path ) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, HttpClientErrorException
    {
        SSLContext sslContext = new SSLContextBuilder()
            .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .addInterceptorFirst((HttpRequestInterceptor) ( httpRequest, httpContext ) -> httpRequest.setHeader("User-Agent", "some-user-agent"))
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        String url = String.format("%s/%s", baseUrl, path);
        ResponseEntity<Object> response
            = new RestTemplate(requestFactory).exchange(url, HttpMethod.GET, null, Object.class);

        return CompletableFuture.completedFuture(response);
    }
}
