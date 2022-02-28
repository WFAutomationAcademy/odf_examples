package com.workfusion.examples.aa_examples_bots.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HttpRequester {

    private static final int CONNECT_TIMEOUT_MILLIS = 10_000;
    private static final int CONNECTION_REQUEST_TIMEOUT_MILLIS = 10_000;
    private static final int SOCKET_TIMEOUT_MILLIS = 10_000;

    private static final Logger LOG = LoggerFactory.getLogger(HttpRequester.class);

    public String sendGet(String targetUrl, List<NameValuePair> urlParameters) {

        URI uri = createURI(targetUrl, urlParameters);

        //Create request instance
        HttpGet get = new HttpGet(uri);

        return executeRequestAndGetResponse(get);
    }

    public String sendPost(String targetUrl, List<NameValuePair> urlParameters,
                           List<NameValuePair> headers, String bodyJson) {

        URI uri = createURI(targetUrl, urlParameters);

        //Create request instance
        HttpPost post = new HttpPost(uri);

        //Add headers
        for (NameValuePair pair : headers) {
            post.addHeader(pair.getName(), pair.getValue());
        }
        //Create body entity
        HttpEntity entity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
        post.setEntity(entity);

        return executeRequestAndGetResponse(post);
    }

    private URI createURI(String targetUrl, List<NameValuePair> urlParameters) {
        try {
            //Create URI from string representation of url and list of parameters
            URIBuilder uriBuilder = new URIBuilder(targetUrl);
            if (urlParameters != null && !urlParameters.isEmpty()) {
                uriBuilder.setParameters(urlParameters);
            }
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("An error occurred during instantiating URI");
        }
    }

    private CloseableHttpClient getHttpClient() {
        //Create request config to set timeouts
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT_MILLIS)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS)
                .setSocketTimeout(SOCKET_TIMEOUT_MILLIS).build();
        //Create and return HTTP client
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    private String executeRequestAndGetResponse(HttpUriRequest request) {
        //Get closable http client
        try (CloseableHttpClient httpClient = getHttpClient();
             //Execute request and get closable response
             CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //Get response body entity and return as string
                return EntityUtils.toString(response.getEntity());
            }
            LOG.error(EntityUtils.toString(response.getEntity()));
            throw new RuntimeException("Received response with status " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            throw new RuntimeException("An error occurred during sending HTTP request", e);
        }
    }
}
