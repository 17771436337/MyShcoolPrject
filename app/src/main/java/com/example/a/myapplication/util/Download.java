package com.example.a.myapplication.util;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

public class Download {

    String TAG = "aa";
    String url;

    String saveDirPath;

    String fileName;

    public Download(String url, String saveDirPath, String fileName) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.saveDirPath = saveDirPath;
        this.fileName = fileName;
        createDir();
        buildHttpClient();
        downAsynFile();
    }

    public void doDownLoad(BaseCallListener pListener) {
        this.pListener = pListener;

    }

    private void createDir() {
        File fileSaveDir = new File(saveDirPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
    }

    private void downAsynFile() {

        Request request = new Request.Builder().url(url).build();
        // Toast.makeText(App.getContext(), "下载地址：" + url, 1).show();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "文件下载失败");
                pListener.onFail("文件下载失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(
                            saveDirPath + "/" + fileName));

                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    Log.i(TAG, "IOException");
                    e.printStackTrace();
                }
                Log.d(TAG, "文件下载成功");
                pListener.onSuccess("文件下载成功");
            }
        });
    }

    private OkHttpClient okHttpClient;
    Request.Builder builder;

    private void buildHttpClient() {
        this.okHttpClient = new OkHttpClient();
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(3, TimeUnit.SECONDS);
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        //okHttpClient.setCache(new Cache(CarConstant.HTTP_CACHE_DIR, CarConstant.CACHE_SIZE));
        okHttpClient.setRetryOnConnectionFailure(true);
        builder = new Request.Builder();
    }


    private BaseCallListener pListener;

    public interface BaseCallListener {
        public void onSuccess(String pResponse);

        public void onFail(String pResponse);
    }

}
