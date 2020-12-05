package com.example.admin.qimoapp.Personalinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.qimoapp.R;


public class Aboutus extends AppCompatActivity {
    private TextView tv_title,tv_forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_forward=(TextView)findViewById(R.id.tv_forward);
        tv_forward.setText("");
        tv_title.setText("关于");
    }
}
