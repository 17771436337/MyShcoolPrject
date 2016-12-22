package com.example.a.myapplication.http;


import android.os.Bundle;
import android.util.Log;

import com.example.a.myapplication.util.JsonUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by yutianran on 16/2/24.
 */
public class OkHttpUtil {
    Request.Builder  builder;
    private OkHttpClient okHttpClient;
    private Callback callback;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpUtil getInstance() {
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder {
        private static final OkHttpUtil mInstance = new OkHttpUtil();
    }


    private OkHttpUtil() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        //okHttpClient.setCache(new Cache(CarConstant.HTTP_CACHE_DIR, CarConstant.CACHE_SIZE));
        okHttpClient.setRetryOnConnectionFailure(true);
        builder = new Request.Builder();
    }
    public <T> void addRequestPut(String url, Map<String, String> params, final HttpCallBack<T> callBack) {
        Request request = builder
                .url(url)
                .put(buildParams(params))
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                isDebug(body);
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T) JsonUtil.getInstance().stringToObj(body, actualTypeArguments));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
            }
        });

    }
    public <T> void addRequestPost(String url, Map<String, String> params, final HttpCallBack<T> callBack) {
        if (params == null || params.size() == 0) {
            return;
        }
        RequestBody requestBody= buildParams(params);
        Request request = builder
                .url(url)
                .post(requestBody)
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                isDebug(body);
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    callBack.onSuccss((T) JsonUtil.getInstance().stringToObj(body, actualTypeArguments));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
            }
        });

    }
    public <T> void addRequestDetele(String url, final HttpCallBack<T> callBack) {
        Request request =  builder

                .url(url)
                .delete()
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T) JsonUtil.getInstance().stringToObj(body, actualTypeArguments));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
            }
        });
    }

    public <T> void addRequestGet(String url, final HttpCallBack<T> callBack) {
        Request request =  builder
                .url(url)
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                isDebug(body);
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T)JsonUtil.getInstance().stringToObj(body, actualTypeArguments));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
                isDebug(res);
            }
        });

    }

    public String addRequestNoCallGet(String url) {
        Request request =  builder
                .url(url)
                .get()
                .build();
        final Call callnocallget = okHttpClient.newCall(request);
        String body="";
        try {
            Response response = callnocallget.execute();
            body = response.body().string();
            isDebug(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    public String addRequestNoCallPost(String url, Map<String, String> params) {
        Request request =  builder
                .url(url)
                .post(buildParams(params))
                .build();
        String body="";
        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            body = response.body().string();
            isDebug(body);
        } catch (Exception e) {
            e.printStackTrace();
            isDebug(e.getMessage());
        }
        return body;
    }


    public <T> void addRequest(String url, int tag, Bundle params, final HttpCallBack<T> callBack) {
        if (params == null || params.size() == 0) {
            return;
        }
        final Request request = new Request.Builder().url(url).tag(tag).post(buildParamsBundle(params)).build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                isDebug(body);
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T) JsonUtil.getInstance().stringToObj(body, actualTypeArguments));
                } catch (Exception e) {
                    isDebug(e.getMessage());
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
                callBack.onFailure(res);
            }
        });

    }

    public <T> void addRequest(String url, int tag, final HttpCallBack<T> callBack) {
        final Request request = new Request.Builder().url(url).tag(tag).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                try {
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
            }
        });
    }

    public <T> void uploadRequest(String url, String type, File file, final HttpCallBack<T> callBack) {
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM)
                .addFormDataPart("key", "JZmSPfcWJ3U1mRFN32mTEi")
                .addFormDataPart("secret", "f6e4636d155441209470a906aac892a1")
                .addFormDataPart("typeId ", type)
                .addFormDataPart("format", "json")
                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        RequestBody.create(MEDIA_TYPE_MARKDOWN, file);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T)JsonUtil.getInstance().stringToObj(body, actualTypeArguments));

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
                callBack.onFailure(arg1.getMessage());
            }
        });
    }

    public <T> void uploadPic(String url, File file, final HttpCallBack<T> callBack) {
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM)
                .addFormDataPart("img", file.getName(), RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        RequestBody.create(MEDIA_TYPE_MARKDOWN, file);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();
                isDebug(body);
                try {
                    Type[] types = callBack.getClass().getGenericInterfaces();
                    ParameterizedType parameterizedType = (ParameterizedType) types[0];
                    final Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
                    //// TODO: 2016/3/10 ???????????????
                    callBack.onSuccss((T) JsonUtil.getInstance().stringToObj(body, actualTypeArguments));

                } catch (Exception e) {
                    callBack.onFailure(e.getMessage());
                    isDebug(e.getMessage());
                }
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                final String res = arg1.getMessage();
            }
        });
    }


    public void removeRequest(int tag) {
        okHttpClient.cancel(tag);
    }

    private RequestBody buildParamsJosn(Map<String, String> params) {
        RequestBody requestBody = RequestBody.create(JSON, new Gson().toJson(params));
        return requestBody;
    }

    private RequestBody buildParams(Map<String, String> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody requestBody = builder.build();
        return requestBody;
    }

    private RequestBody buildParamsBundle(Bundle bundle) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Set<String> entries = bundle.keySet();
        for (String entrie : entries) {
            builder.add(entrie, bundle.getString(entrie));
        }

        RequestBody requestBody = builder.build();
        return requestBody;
    }

    public interface HttpCallBack<T> {
        void onSuccss(T t);

        void onFailure(String error);
    }

    public void isDebug(String json){
        Log.e("网络请求返回的json",json);
    }
}