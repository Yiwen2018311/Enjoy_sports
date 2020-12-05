package com.example.admin.qimoapp.Personalinfo;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.admin.qimoapp.R;
import com.example.admin.qimoapp.Login.MyDataHelper;


public class EditName extends AppCompatActivity {
    private TitleLayout tl_title;
    private EditText edit_name;
    private String myid,mynickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        SharedPreferences sp=getSharedPreferences("userid", MODE_PRIVATE);
        myid=sp.getString("userid","未获取到");
        MyDataHelper helper = new MyDataHelper(this, "system3.db");
        SQLiteDatabase db2 = helper.getReadableDatabase();
        Cursor c_user = db2.rawQuery(
                "select *from user3 where username=?", new String[] {myid});
        while(c_user.moveToNext()){
            mynickname=c_user.getString(c_user.getColumnIndex("nickname"));
        }
        c_user.close();
        tl_title = (TitleLayout) findViewById(R.id.tl_title);
        edit_name = (EditText) findViewById(R.id.et_edit_name);
        edit_name.setText(mynickname);

        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        tl_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mynickname=edit_name.getText().toString();
                MyDataHelper helper = new MyDataHelper(EditName.this, "system3.db");
                SQLiteDatabase db2 = helper.getReadableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("nickname",mynickname);
                db2.update("user3",cv,"username=?",new String[]{myid});
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        finish();
    }
}
