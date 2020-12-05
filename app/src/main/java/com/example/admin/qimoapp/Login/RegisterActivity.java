package com.example.admin.qimoapp.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.qimoapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    private EditText et_register_user, et_register_password,
            et_register_password_sure, et_register_answer;
    private Spinner spinner_question;
    private Button btn_register_sure, btn_register_back;
    private ArrayAdapter<String> adapter_register;
    private List<String> list_register;
    private String register_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        //注册的用户名
        et_register_user = (EditText) this
                .findViewById(R.id.layout_register_et_user);
        //注册的密码
        et_register_password = (EditText) this
                .findViewById(R.id.layout_register_et_password);
        //再一次确认密码
        et_register_password_sure = (EditText) this
                .findViewById(R.id.layout_register_et_password_sure);
        //Spinner其实就是一个列表选择框。不过Android的列表选择框并不需要显示下拉列表，而是相当于弹出一个菜单供用户选择。
        spinner_question = (Spinner) this
                .findViewById(R.id.layout_register_spinner);
        //密保问题的答案
        et_register_answer = (EditText) this
                .findViewById(R.id.layout_register_et_answer);
        //提交确认
        btn_register_sure = (Button) this
                .findViewById(R.id.layout_register_btn_register_sure);
        //放弃返回
        btn_register_back = (Button) this
                .findViewById(R.id.layout_register_btn_back);

        //密保问题选项
        list_register=new ArrayList<String>();
        list_register.add("你的学号是？");
        list_register.add("你的生日是？");
        list_register.add("你的手机号是？");
        list_register.add("你的小学班主任名字是？");
        list_register.add("你最好的朋友名字是？");
        list_register.add("你最喜欢的明星是？");

        adapter_register=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_register);
        //Adapter的setDropDownViewResource可以设置下拉菜单的显示方式
        adapter_register.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_question.setAdapter(adapter_register);
        spinner_question.setOnItemSelectedListener(this);
        btn_register_sure.setOnClickListener(this);
        btn_register_back.setOnClickListener(this);
        findViewById(R.id.reg_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            //关闭当前页面
            case R.id.layout_register_btn_back:
                finish();
                break;
            case R.id.layout_register_btn_register_sure:
                String register_username = et_register_user.getText().toString();
                String register_password = et_register_password.getText()
                        .toString();
                String register_password_sure = et_register_password_sure.getText()
                        .toString();
                String register_answer = et_register_answer.getText().toString();
                if (register_username.isEmpty()) {
                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (register_password.isEmpty()) {
                    Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (register_password_sure.isEmpty()) {
                    Toast.makeText(this, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (register_question.isEmpty()) {
                    Toast.makeText(this, "密保问题不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (register_answer.isEmpty()) {
                    Toast.makeText(this, "密保答案不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!register_password.equals(register_password_sure)) {
                    Toast.makeText(this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //实例化SQLiteHelper类，从中得到一个读写的数据库
                MyDataHelper helper = new MyDataHelper(this, "system3.db");
                SQLiteDatabase dbWriter = helper.getWritableDatabase();
                Cursor c = dbWriter.rawQuery("select *from user3 where username=?",
                        new String[] { register_username });
                while (c.moveToFirst()) {
                    Toast.makeText(this, "该用户名已被注册！",  Toast.LENGTH_SHORT).show();
                    return;
                }
                //将图片转化为位图
                Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.user);
                //创建一个字节数组输出流，流的大小为size
                int size=bitmap.getWidth()*bitmap.getHeight()*4;
                ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
                //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                //将字节数组输出流转化为字节数组byte[]
                byte[] imagedata= baos.toByteArray();
                ContentValues cv = new ContentValues();
                cv.put("username", register_username);
                cv.put("pwd", register_password);
                cv.put("question", register_question);
                cv.put("answer", register_answer);
                cv.put("nickname","昵称");
                cv.put("sex","");
                cv.put("location","");
                cv.put("pic",imagedata);
                dbWriter.insert("user3", null, cv);
                cv.clear();
                /*
                 * Cursor c = db.rawQuery("select *from user", null); if (c != null)
                 * { String[] cols = c.getColumnNames(); while (c.moveToNext()) {
                 * for (String ColumnNames : cols) { if(c.equals(register_user)){
                 * Toast.makeText(this, "该用户名已被注册!", 2000).show(); return; } } } }
                 */
                c.close();
                dbWriter.close();
                Toast.makeText(this, "注册成功，跳转至登录界面！",  Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.reg_view:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0);

                break;
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        register_question=list_register.get(arg2);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}