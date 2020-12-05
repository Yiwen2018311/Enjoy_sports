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


public class ForgetPwdActivity_one extends Activity implements View.OnClickListener {
    private EditText et_forgetpwd_username;
    private Button btn_forget_ok, btn_forget_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forgetpwd_one);
        et_forgetpwd_username = (EditText) this
                .findViewById(R.id.layout_forgetpwd_et_username);
        btn_forget_ok = (Button) this
                .findViewById(R.id.layout_forgetpwd_btn_ok);
        btn_forget_back = (Button) this
                .findViewById(R.id.layout_forgetpwd_btn_back);

        btn_forget_ok.setOnClickListener(this);
        btn_forget_back.setOnClickListener(this);
        findViewById(R.id.findpwd_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {

            case R.id.layout_forgetpwd_btn_ok:
                String forgetpwd_username = et_forgetpwd_username.getText()
                        .toString();
                if (forgetpwd_username.isEmpty()) {
                    Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                }
                MyDataHelper helper = new MyDataHelper(this, "system3.db");
                SQLiteDatabase dbread = helper.getReadableDatabase();
                Cursor c_forget_user = dbread.rawQuery(
                        "select *from user3 where username=?",
                        new String[] { forgetpwd_username });
                /*
                 * if (c_forget_user != null) { String[] columns =
                 * c_forget_user.getColumnNames(); while
                 * (c_forget_user.moveToNext()) { for (String ColumnName : columns)
                 * { Log.i("info", c_forget_user.getString(c_forget_user
                 * .getColumnIndex(ColumnName))); } } }
                 */
                if (c_forget_user.moveToFirst()) {
                    Toast.makeText(this, "请稍等！", Toast.LENGTH_SHORT).show();
                    String forget_username = c_forget_user.getString(1);
                    Intent intent_forget_one_two = new Intent(
                            ForgetPwdActivity_one.this, ForgetPwdActivity_two.class);
                    intent_forget_one_two.putExtra(
                            "forget_username",
                            forget_username);
                    startActivity(intent_forget_one_two);
                }
                if (!(c_forget_user.moveToFirst())) {
                    Toast.makeText(this, "该用户不存在！", Toast.LENGTH_SHORT).show();
                }
                c_forget_user.close();
                dbread.close();
                break;

            case R.id.layout_forgetpwd_btn_back:
                finish();
                break;
            case R.id.findpwd_one:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);

                break;
        }

    }
}
