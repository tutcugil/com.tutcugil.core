package com.tutcugil.core.network;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HttpRequest {
    public static final MediaType MEDIA_TYPE_JSON     = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    public static Response make(Request request) throws IOException {
        return new OkHttpClient().newCall(request).execute();
    }

    public static Response get(String url) throws IOException {
        return get(url, new HashMap<String, String>());
    }

    public static Response get(String url, Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                //.headers(Headers.of(headers))
                .url(url)
                .build();

        return client.newCall(request).execute();
    }

    public static Response post(String url, RequestBody body) throws IOException {
        return post(url, body, new HashMap<String, String>());
    }

    public static Response post(String url, RequestBody body, Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient();

        //RequestBody.create(MEDIA_TYPE_JSON, json);
        //RequestBody.create(MEDIA_TYPE_JSON, file);

        Request request = new Request.Builder()
            .url(url)
            //.headers(Headers.of(headers))
            .post(body)
            .build();

        return client.newCall(request).execute();
    }
}
