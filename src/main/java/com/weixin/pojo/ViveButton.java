package com.weixin.pojo;


public class ViveButton extends AbstractButton {
    private String url;
    private String type ="view";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public ViveButton(String url,String name){
        super(name);
        this.url = url;
    }
    public String getType(){
        return this.type;
    }

    public String getName(){
        return super.getName();
    }

    @Override
    public String toString() {
        return "ViveButton{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                ",name="+getName()+
                '}';
    }
}
