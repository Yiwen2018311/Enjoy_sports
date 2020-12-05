package com.example.admin.qimoapp.Fragment;
public class picConstant {
    private String mytext;
    private int picid;
    private byte[] mypic;
    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }
    public String getMytext() {
        return mytext;
    }

    public void setMytext(String mytext) {
        this.mytext = mytext;
    }

    public byte[] getMypic() {
        return mypic;
    }

    public void setMypic(byte[] mypic) {
        this.mypic = mypic;
    }

    public picConstant(String mytext,byte[] mypic,int picid){
        this.mytext=mytext;
        this.mypic=mypic;
        this.picid=picid;
    }

}
