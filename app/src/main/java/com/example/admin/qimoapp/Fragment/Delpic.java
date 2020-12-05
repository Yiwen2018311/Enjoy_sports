package com.example.admin.qimoapp.Fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.qimoapp.Login.MyDataHelper2;
import com.example.admin.qimoapp.MainActivity;
import com.example.admin.qimoapp.Personalinfo.PhotoUtils;
import com.example.admin.qimoapp.R;

public class Delpic  extends AppCompatActivity implements View.OnClickListener{
    private byte[] imname;
    private int imid;
    private String imid2;
    private Bitmap mybitmap;
    private Button delpic,backpic;
    private ImageView mypic22;
    private PhotoUtils photoUtils = new PhotoUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpic);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        imname = (byte[]) bundle.getByteArray("picname");
        imid =(int)bundle.getInt("picid");
        if(imname==null){
        }else {
            mybitmap = photoUtils.byte2bitmap(imname);
            mypic22 = (ImageView) findViewById(R.id.mypic22);
            mypic22.setImageBitmap(mybitmap);
        }
        imid2=Integer.toString(imid);
        delpic=(Button)findViewById(R.id.delpic);
        backpic=(Button)findViewById(R.id.backpic);
        delpic.setOnClickListener(this);
        backpic.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delpic:
                delepic();
                Intent intent2 = new Intent(Delpic.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.backpic:
                finish();
                break;
        }
    }
    public void delepic(){
        MyDataHelper2 helper = new MyDataHelper2(Delpic.this, "system4.db");
        SQLiteDatabase db2 = helper.getReadableDatabase();
        db2.delete("user3add","_id2=?",new String[]{imid2});
    }

}
