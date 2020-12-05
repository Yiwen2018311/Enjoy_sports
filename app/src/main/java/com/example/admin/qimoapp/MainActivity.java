package com.example.admin.qimoapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.qimoapp.Fragment.MyFragment1;
import com.example.admin.qimoapp.Fragment.MyFragment2;
import com.example.admin.qimoapp.Fragment.MyFragment3;
import com.example.admin.qimoapp.Fragment.MyFragment4;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel,rb_message,rb_better,rb_setting;

    //Fragment Object
    private MyFragment1 fg1;
    private MyFragment2 fg2;
    private MyFragment3 fg3;
    private MyFragment4 fg4;

    private FragmentManager fManager;
    //确定返回的按钮哪一个被按下
    private int mychoose=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        SharedPreferences sp=getSharedPreferences("choose", MODE_PRIVATE);
        if(sp.getInt("choose",-1)!=-1) {
            mychoose = sp.getInt("choose", -1);
        }
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        setBounds(R.drawable.tab_menu_01,rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        setBounds(R.drawable.tab_menu_02,rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        setBounds(R.drawable.tab_menu_03,rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        setBounds(R.drawable.tab_menu_04,rb_setting);
        if(mychoose==1)
            rb_channel.setChecked(true);
        if(mychoose==2)
            rb_message.setChecked(true);
        if(mychoose==3)
            rb_better.setChecked(true);
        if(mychoose==4)
            rb_setting.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_channel:
                if(fg1 == null){
                    fg1 = new MyFragment1();
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_message:
                if(fg2 == null){
                    fg2 = new MyFragment2();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_better:
                if(fg3 == null){
                    fg3 = new MyFragment3();
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.rb_setting:
                if(fg4 == null){
                    fg4 = new MyFragment4();
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    private void setBounds(int drawableId, RadioButton radioButton) {
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(drawableId);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形  (这里的长和宽写死了 自己可以可以修改成 形参传入)
        drawable_news.setBounds(0, 0, 60, 60);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null,drawable_news,null, null);
    }

}