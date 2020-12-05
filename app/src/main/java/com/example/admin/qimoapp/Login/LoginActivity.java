package com.example.admin.qimoapp.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.qimoapp.MainActivity;
import com.example.admin.qimoapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_user, et_password;
    private Button btn_forgetpwd, btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置当前的Activity 无Title并且全屏
        //调用这个方法有个限制,即必须在setContentView(R.layout.main)之前调用
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        //用户名
        et_user = (EditText) this.findViewById(R.id.layout_activity_main_et_user);
        //用户密码
        et_password = (EditText) this.findViewById(R.id.layout_activity_main_pwd_password);
        //忘记密码
        btn_forgetpwd = (Button) this.findViewById(R.id.layout_activity_main_btn_forgetpwd);
        //登陆按钮
        btn_login = (Button) this.findViewById(R.id.layout_activity_main_btn_login);
        //注册按钮
        btn_register = (Button) this.findViewById(R.id.layout_activity_main_btn_register);
        //代表设置事件处理的监听器，点击Button按钮就会进入到onClick函数里面
        btn_forgetpwd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        findViewById(R.id.login_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {

            case R.id.layout_activity_main_btn_forgetpwd:
                //利用intent跳转页面
                Intent intent_forget_one=new Intent(LoginActivity.this,ForgetPwdActivity_one.class);
                this.startActivity(intent_forget_one);
                break;

            case R.id.layout_activity_main_btn_login:
                String login_user = et_user.getText().toString();
                String login_pwd = et_password.getText().toString();
                if (login_user.isEmpty()) {
                    Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (login_pwd.isEmpty()) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyDataHelper helper = new MyDataHelper(this, "system3.db");
                //获取一个可读的数据库对象
                SQLiteDatabase dbRead=helper.getReadableDatabase();
                Cursor c_user = dbRead.rawQuery(
                        "select *from user3 where username=? and pwd=?",
                        new String[] { login_user, login_pwd });
                //cursor.moveToFirst（）指向查询结果的第一个位置
                // 一般通过判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空。
                if (c_user.moveToFirst()) {
                    SharedPreferences sharedPreferences = this.getSharedPreferences("userid", this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();//
                    editor.putString("userid",login_user);
                    editor.commit();
                   // Toast.makeText(this, "登录成功！正在为你跳转界面！", Toast.LENGTH_SHORT).show();
                    Intent intent_function=new Intent(LoginActivity.this,MainActivity.class);
                    this.startActivity(intent_function);
                    return;
                }
                if (!(c_user.moveToFirst())) {
                    Toast.makeText(this, "用户名或密码错误！请重新输入！", Toast.LENGTH_SHORT).show();
                    return;
                }
                c_user.close();
                dbRead.close();
                break;

            case R.id.layout_activity_main_btn_register:
                Intent intent_register = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                this.startActivity(intent_register);
                break;

            case R.id.login_view:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                //隐藏键盘
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);

                break;


        }
    }
}
