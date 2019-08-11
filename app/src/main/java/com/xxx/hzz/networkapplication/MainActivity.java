package com.xxx.hzz.networkapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button httpCilentButton;
    private Button httpConnectionButton;
    private HttpUrlConnectionImp httpUrlConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpCilentButton = (Button)findViewById(R.id.httpCilendId);
        httpCilentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HttpClientActivity.class));
                Log.i(TAG,"Click httpCilentButton");
            }
        });
        httpConnectionButton = (Button)findViewById(R.id.httpConnectionId);
        httpConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpUrlConnect = new HttpUrlConnectionImp();
                httpUrlConnect.httpGet("http://www.baidu.com");
                Log.i(TAG,"Click httpConnectionButton");
            }
        });
    }
}
