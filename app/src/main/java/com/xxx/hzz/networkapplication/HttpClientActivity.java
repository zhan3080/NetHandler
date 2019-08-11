package com.xxx.hzz.networkapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangzezhan on 2019/7/12.
 */

public class HttpClientActivity extends Activity {
    private static final String TAG = "HttpClientActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpclient);
//        httpGet();
        httpGetBaidu();
        Log.i(TAG, "onCreate");

    }

    private void httpGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpGeturl();
            }
        }).start();
    }

    private void httpGeturl() {
        Log.i(TAG, "httpGet");
        //先将参数放入List，再对参数进行URL编码
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("param1", "中国"));
        params.add(new BasicNameValuePair("param2", "value2"));

        //对参数编码
        String param = URLEncodedUtils.format(params, "UTF-8");

        //baseUrl
        String baseUrl = "http://ubs.free4lab.com/php/method.php";

        //将URL与参数拼接
        HttpGet getMethod = new HttpGet(baseUrl + "?" + param);
//        HttpGet getMethod = new HttpGet("http://www.baidu.com");

        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpResponse response = httpClient.execute(getMethod); //发起GET请求

            Log.i(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
            Log.i(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void httpGetBaidu() {
        Log.i(TAG, "httpGetBaidu");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpGet httpGet = new HttpGet("https://www.baidu.com");

                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");//将entity当中的数据转换为字符串

                        //在子线程中将Message对象发出去
//                                                 Message message = new Message();
//                                                 message.what = SHOW_RESPONSE;
//                                                 message.obj = response.toString();
//                                                 handler.sendMessage(message);
                        Log.i(TAG, "message:" + response.toString());
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();//这个start()方法不要忘记了
    }
}
