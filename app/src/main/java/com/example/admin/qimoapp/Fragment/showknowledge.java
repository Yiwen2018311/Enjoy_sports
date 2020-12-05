package com.example.admin.qimoapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.admin.qimoapp.R;

public class showknowledge extends AppCompatActivity {
    private int choose;
    private TextView tv_title,tv_forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showknowledge);
        WebView webView = (WebView)findViewById(R.id.web_view1);
        tv_title=(TextView)findViewById(R.id.tv_title) ;
        tv_title.setText("运动小常识");
        tv_forward=(TextView)findViewById(R.id.tv_forward) ;
        tv_forward.setText("");
        //让WebView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //调用WebView的setWebViewClient()方法，并传入了一个WebViewClient的实例
        //作用是当从一个网页跳转到另一个网页时，目标网页仍然在当前WebView中显示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        Intent intent=getIntent();
        choose=intent.getIntExtra("flag",0);
        if(choose==1) {
            webView.loadUrl("file:///android_asset/html/show1.html");
        }
        if(choose==2) {
            webView.loadUrl("file:///android_asset/html/show2.html");
        }
        if(choose==3) {
            webView.loadUrl("file:///android_asset/html/show3.html");
        }
        if(choose==4) {
            webView.loadUrl("file:///android_asset/html/show4.html");
        }
    }
}
