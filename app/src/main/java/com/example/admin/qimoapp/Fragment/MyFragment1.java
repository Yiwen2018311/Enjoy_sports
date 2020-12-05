package com.example.admin.qimoapp.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.admin.qimoapp.Personalinfo.Setting;
import com.example.admin.qimoapp.R;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment1 extends Fragment implements View.OnClickListener{
    private ImageButton im1_1,im1_2,im1_3,im1_4;
    public MyFragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        im1_1=(ImageButton)view.findViewById(R.id.im1_1);
        im1_2=(ImageButton)view.findViewById(R.id.im1_2);
        im1_3=(ImageButton)view.findViewById(R.id.im1_3);
        im1_4=(ImageButton)view.findViewById(R.id.im1_4);
        im1_1.setOnClickListener(this);
        im1_2.setOnClickListener(this);
        im1_3.setOnClickListener(this);
        im1_4.setOnClickListener(this);
        return view;
    }
    //按钮事件处理，v为当前点击的按钮
    public void onClick(View v){
        switch(v.getId()){
            case R.id.im1_1:
                Intent intent1 = new Intent(getActivity(),showknowledge.class);
                intent1.putExtra("flag",1);
                getActivity().startActivity(intent1);
                break;
            case R.id.im1_2:
                Intent intent2 = new Intent(getActivity(),showknowledge.class);
                intent2.putExtra("flag",2);
                getActivity().startActivity(intent2);
                break;
            case R.id.im1_3:
                Intent intent3 = new Intent(getActivity(),showknowledge.class);
                intent3.putExtra("flag",3);
                getActivity().startActivity(intent3);
                break;
            case R.id.im1_4:
                Intent intent4 = new Intent(getActivity(),showknowledge.class);
                intent4.putExtra("flag",4);
                getActivity().startActivity(intent4);
                break;
          default:
                break;
        }
    }

}
