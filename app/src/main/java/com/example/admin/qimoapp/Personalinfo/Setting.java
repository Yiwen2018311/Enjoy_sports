package com.example.admin.qimoapp.Personalinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.admin.qimoapp.MainActivity;
import com.example.admin.qimoapp.R;
import com.example.admin.qimoapp.Login.LoginActivity;


public class Setting extends AppCompatActivity implements View.OnClickListener {
    private Button exit,iv_person_info,setting_reback,setting_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);
        exit = (Button) findViewById(R.id.exit);
        iv_person_info = (Button) findViewById(R.id.iv_person_info);
        setting_reback=(Button) findViewById(R.id.setting_reback);
        setting_more=(Button) findViewById(R.id.setting_more);
        exit.setOnClickListener(this);
        iv_person_info.setOnClickListener(this);
        setting_reback.setOnClickListener(this);
        setting_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            //按下退出登录按钮的逻辑
            case R.id.exit:
                SharedPreferences sharedPreferences = this.getSharedPreferences("choose", this.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//
                editor.putInt("choose",1);
                editor.commit();
                Intent intent = new Intent(Setting.this, LoginActivity.class);
                startActivity(intent);
                finish();//关闭当前活动
                break;
            //按下个人信息框的逻辑
            case R.id.iv_person_info:
                Intent intent1 = new Intent(Setting.this, PersonInfo.class);
                startActivity(intent1);
                break;
            case R.id.setting_reback:
                SharedPreferences sharedPreferences2 = this.getSharedPreferences("choose", this.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();//
                editor2.putInt("choose",4);
                editor2.commit();
                Intent intent2 = new Intent(Setting.this, MainActivity.class);
                startActivity(intent2);
                finish();//关闭当前活动
                break;
            case R.id.setting_more:
                Intent intent3 = new Intent(Setting.this, Aboutus.class);
                startActivity(intent3);
                break;
        }
    }

}
