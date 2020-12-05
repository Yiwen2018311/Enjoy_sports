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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.qimoapp.Login.MyDataHelper;
import com.example.admin.qimoapp.Login.MyDataHelper2;
import com.example.admin.qimoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment3 extends Fragment implements View.OnClickListener{
    private View view;
    private List<picConstant> imageInfos=new ArrayList<>();
    private ListView mylist;
    private Button add;
    private String myname,myid,mytext;
    private int picid;
    private byte[] mypic2;
    private Bitmap mybitmap;
    public MyFragment3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_fragment,container,false);
        add=(Button)view.findViewById(R.id.add);
        add.setOnClickListener(this);
        initRecyclerView();
        initdata();
        return view;
    }

    private void initRecyclerView() {
        RecyclerView recVie=(RecyclerView)view.findViewById(R.id.rv_1);
        picAdapter ada=new picAdapter(getActivity(),imageInfos);
        recVie.setAdapter(ada);
        recVie.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
    }


    public void initdata(){
        SharedPreferences sp=getActivity().getSharedPreferences("userid",getActivity().MODE_PRIVATE);
        myid=sp.getString("userid","未获取到");
        myname=myid;
        //实例化SQLiteHelper类，从中得到一个读写的数据库
        MyDataHelper2 helper = new MyDataHelper2(getActivity(), "system4.db");
        SQLiteDatabase dbWriter = helper.getWritableDatabase();
        Cursor c = dbWriter.rawQuery("select *from user3add where username2=?",
                new String[] {myname});
        while (c.moveToNext()) {
            mytext=c.getString(c.getColumnIndex("mytext"));
            mypic2=c.getBlob(c.getColumnIndex("mypic"));
            picid=c.getInt(c.getColumnIndex("_id2"));
            picConstant pic=new picConstant(mytext,mypic2,picid);
            imageInfos.add(pic);
        }
    }
    //按钮事件处理，v为当前点击的按钮
    public void onClick(View v){
        switch(v.getId()){
            case R.id.add:
                Intent intent= new Intent(getActivity(),Addpic.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }

    }

}
