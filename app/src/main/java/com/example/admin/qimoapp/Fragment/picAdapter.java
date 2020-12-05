package com.example.admin.qimoapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.qimoapp.Personalinfo.PhotoUtils;
import com.example.admin.qimoapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class picAdapter extends RecyclerView.Adapter<picAdapter.ViewHolder> {
    Context mcontext;
    private List<picConstant> mImages;//原始数据
    private Bitmap mybitmap;
    private byte[] mybyte;
    private PhotoUtils photoUtils = new PhotoUtils();
    public picAdapter(Context context,List<picConstant> my){
        this.mImages=my;
        this.mcontext=context;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载模板，通过模板资源来创建Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypic_item, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        //设置两个事件监听器
        vh.v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getAdapterPosition();
                picConstant iInfo = mImages.get(pos);
            }
        });
        vh.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getAdapterPosition();
                picConstant g=mImages.get(pos);
                Intent intent = new Intent(mcontext,Delpic.class);
                Bundle bundle=new Bundle();
                bundle.putInt("picid",g.getPicid());
                mybitmap = photoUtils.byte2bitmap(g.getMypic());
                mybitmap = photoUtils.imageZoom(mybitmap,300.00);
                mybyte = photoUtils.bitmap2byte(mybitmap);
                bundle.putByteArray("picname",mybyte);
                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });
        return vh;
    }
    //position表示当前要和哪一个元素进行绑定
    public void onBindViewHolder(ViewHolder holder, int position) {
        picConstant g=mImages.get(position);
        if(g.getMypic()!=null) {
            Bitmap mybitmap = BitmapFactory.decodeByteArray(g.getMypic(), 0, g.getMypic().length);
            holder.iv.setImageBitmap(mybitmap);
        }
        holder.tx.setText(g.getMytext());
    }
    //查看Adapter里面有多少元素
    public int getItemCount() {
        return mImages.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        View v;//表示iv和tv所附着的v
        ImageView iv;
        TextView  tx;
        public ViewHolder(View vie) {
            super(vie);
            v = vie;
            tx=  (TextView)  vie.findViewById(R.id.mytext);
            iv = (ImageView) vie.findViewById(R.id.mypic);
        }
    }

}
