package com.xxx.hzz.networkapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by huangzezhan on 2019/8/5.
 */

public class HttpUrlConnectionImp {
    private static final String TAG = "HttpUrlConnectionImp";
    private String mUrl = null;

    public void httpGet(String url) {
        Log.i(TAG, "httpGet url:" + url);
        mUrl = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
//                    connection.connect();
                    //得到响应码
                    int responseCode = connection.getResponseCode();
                    Log.i(TAG,"responseCode:"+responseCode);
                    if(responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream in = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        StringBuilder stringRespond = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            stringRespond.append(line);
                            Log.i(TAG, "readLine line:" + line);
                        }
                        Log.i(TAG, "stringRespond:" + stringRespond);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
