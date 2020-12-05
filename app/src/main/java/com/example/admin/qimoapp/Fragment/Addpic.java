package com.example.admin.qimoapp.Fragment;

import android.Manifest;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.admin.qimoapp.Login.MyDataHelper2;
import com.example.admin.qimoapp.MainActivity;
import com.example.admin.qimoapp.Personalinfo.PhotoUtils;
import com.example.admin.qimoapp.Personalinfo.TitleLayout;
import com.example.admin.qimoapp.R;
import java.io.FileNotFoundException;

import static android.provider.MediaStore.EXTRA_OUTPUT;

public class Addpic extends AppCompatActivity implements View.OnClickListener{
    private TitleLayout tl_title;
    private EditText addtext;
    private ImageView addimg;
    private String myid,myname,mytext;
    private Bitmap mybitmap;

    private Uri imageUri;  //拍照功能的地址
    private static final int TAKE_PHOTO = 1;
    private static final int FROM_ALBUMS = 2;
    private PopupWindow popupWindow;
    private String imagePath;  //从相册中选的地址
    private PhotoUtils photoUtils = new PhotoUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpic);
        SharedPreferences sp=getSharedPreferences("userid", MODE_PRIVATE);
        myid=sp.getString("userid","未获取到");
        myname=myid;

        tl_title = (TitleLayout) findViewById(R.id.tl_title);
        addtext = (EditText) findViewById(R.id.mytext2);
        addimg=(ImageView)findViewById(R.id.mypic2);
        addimg.setOnClickListener(this);

        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        tl_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytext=addtext.getText().toString();
                MyDataHelper2 helper = new MyDataHelper2(Addpic.this, "system4.db");
                SQLiteDatabase db2 = helper.getReadableDatabase();
                ContentValues cv = new ContentValues();
                if(mybitmap!=null) {
                    byte[] imagedata = photoUtils.bitmap2byte(mybitmap);
                    cv.put("mypic",imagedata);
                }
                cv.put("username2",myname);
                cv.put("mytext",mytext);
                db2.insert("user3add", null, cv);
                setResult(RESULT_OK);
                setchoose();
                Intent intent2 = new Intent(Addpic.this, MainActivity.class);
                startActivity(intent2);
                finish();//关闭当前活动
            }
        });
    }

    public void setchoose(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("choose", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//
        editor.putInt("choose",3);
        editor.commit();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mypic2:
                show_popup_windows();
                break;
        }
    }
    //处理拍摄照片回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        switch (requestCode){
            //拍照得到图片
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        //将拍摄的图片展示并更新数据库
                        mybitmap = BitmapFactory.decodeStream((getContentResolver().openInputStream(imageUri)));
                        mybitmap=photoUtils.imageZoom(mybitmap,300.00);
                        addimg.setImageBitmap(mybitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            //从相册中选择图片
            case FROM_ALBUMS:
                if(resultCode == RESULT_OK){
                    //判断手机版本号
                    if(Build.VERSION.SDK_INT >= 19){
                        imagePath =  photoUtils.handleImageOnKitKat(this, data);
                    }else {
                        imagePath = photoUtils.handleImageBeforeKitKat(this, data);
                    }
                }
                if(imagePath != null){
                    //将拍摄的图片展示并更新数据库
                    mybitmap = BitmapFactory.decodeFile(imagePath);
                    mybitmap=photoUtils.imageZoom(mybitmap,300.00);
                    addimg.setImageBitmap(mybitmap);
                }else{
                    Log.d("food","没有找到图片");
                }
                break;
            default:
                break;
        }
    }
    private void show_popup_windows(){
            RelativeLayout layout_photo_selected = (RelativeLayout) getLayoutInflater().inflate(R.layout.photo_select,null);
            if(popupWindow==null){
                popupWindow = new PopupWindow(layout_photo_selected, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            }
            //显示popupwindows
            popupWindow.showAtLocation(layout_photo_selected, Gravity.CENTER, 0, 0);
            //设置监听器
            TextView take_photo =  (TextView) layout_photo_selected.findViewById(R.id.take_photo);
            TextView from_albums = (TextView)  layout_photo_selected.findViewById(R.id.from_albums);
            LinearLayout cancel = (LinearLayout) layout_photo_selected.findViewById(R.id.cancel);
            //拍照按钮监听
            take_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(popupWindow != null && popupWindow.isShowing()) {
                        imageUri = photoUtils.take_photo_util(Addpic.this, "com.example.admin.qimoapp.fileprovider", "output_image.jpg");
                        //调用相机，拍摄结果会存到imageUri也就是outputImage中
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, TAKE_PHOTO);
                        //去除选择框
                        popupWindow.dismiss();
                    }
                }
            });
            //相册按钮监听
            from_albums.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //申请权限
                    if(ContextCompat.checkSelfPermission(Addpic.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(Addpic.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }else {
                        //打开相册
                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                        intent.setType("image/*");
                        startActivityForResult(intent, FROM_ALBUMS);
                    }
                    //去除选择框
                    popupWindow.dismiss();
                }
            });
            //取消按钮监听
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });

        }
}
