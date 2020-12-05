package com.example.admin.qimoapp.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.qimoapp.Personalinfo.RoundImageView;
import com.example.admin.qimoapp.R;
import com.example.admin.qimoapp.Login.MyDataHelper;
import com.example.admin.qimoapp.Personalinfo.Setting;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment4 extends Fragment implements View.OnClickListener{
    private ImageView setting;
    private String nickname,myid;
    private byte[] mypic;
    private Bitmap mybitmap;
    private TextView info_name;
    private RoundImageView portrait;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.four_fragment, container, false);
        setting = (ImageView)view.findViewById(R.id.setting);
        setting.setOnClickListener(this);
        SharedPreferences sp=getActivity().getSharedPreferences("userid", MODE_PRIVATE);
        myid=sp.getString("userid","未获取到");
        info_name = (TextView)view.findViewById(R.id.info_name);
        portrait =(RoundImageView)view.findViewById(R.id.portrait);
        init();
        //登录则初始化用户的信息
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击设置按钮的逻辑
            case R.id.setting:
                Intent intent = new Intent(getActivity(), Setting.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void init(){
        MyDataHelper helper = new MyDataHelper(getActivity(), "system3.db");
        SQLiteDatabase db2 = helper.getReadableDatabase();
        Cursor c_user = db2.rawQuery(
                "select * from user3 where username=?", new String[] {myid});
        while(c_user.moveToNext()){
            nickname=c_user.getString(c_user.getColumnIndex("nickname"));
            mypic=c_user.getBlob(c_user.getColumnIndex("pic"));//将Blob数据转化为字节数组
        }
        c_user.close();
        if(nickname!="") {
            info_name.setText(nickname);
        }
        if(mypic!=null){
            mybitmap=BitmapFactory.decodeByteArray(mypic,0, mypic.length);
            portrait.setImageBitmap(mybitmap);
        }
    }

}
