package com.example.admin.qimoapp.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.qimoapp.R;

public class ForgetPwdActivity_two extends Activity implements View.OnClickListener {
    private EditText et_forgetpwd_two_question, et_forgetpwd_two_answer;
    private Button et_forgetpwd_two_btn_ok;
    Cursor c_forgetpwd_user;
    SQLiteDatabase dbread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forgetpwd_two);

        et_forgetpwd_two_question = (EditText) this
                .findViewById(R.id.layout_forgetpwd_two_et_question);
        et_forgetpwd_two_answer = (EditText) this
                .findViewById(R.id.layout_forgetpwd_two_et_answer);
        et_forgetpwd_two_btn_ok = (Button) this
                .findViewById(R.id.layout_forgetpwd_two_btn_ok);

        Intent intent_forget_one_two = getIntent();
        String forget_username_two = intent_forget_one_two
                .getStringExtra("forget_username");
        MyDataHelper helper = new MyDataHelper(this, "system3.db");
        dbread = helper.getReadableDatabase();
        c_forgetpwd_user = dbread.rawQuery(
                "select *from user3 where username=?",
                new String[]{forget_username_two});
        if (c_forgetpwd_user.moveToFirst()) {
            String forgetpwd_question = c_forgetpwd_user.getString(3);
            et_forgetpwd_two_question.setText(forgetpwd_question);
        }
        et_forgetpwd_two_btn_ok.setOnClickListener(this);
        findViewById(R.id.findpwd_two).setOnClickListener(this);
    }
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.layout_forgetpwd_two_btn_ok:
                if (c_forgetpwd_user.moveToFirst()) {
                    String forgetpwd_pwd = c_forgetpwd_user.getString(2);
                    String forgetpwd_answer = c_forgetpwd_user.getString(4);
                    String et__answer_now = et_forgetpwd_two_answer.getText().toString();
                    if (et__answer_now.equals(forgetpwd_answer)) {
                        Toast.makeText(this, "密保正确！你的密码是：" + forgetpwd_pwd, Toast.LENGTH_SHORT).show();
                    }
                    if (!(et__answer_now.equals(forgetpwd_answer))) {
                        Toast.makeText(this, "密保错误！请重新输入：", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                c_forgetpwd_user.close();
                dbread.close();
                finish();
                break;
            case R.id.findpwd_two:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);

                break;
        }
    }
}
