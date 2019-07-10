package com.pinjhu.ad.constant;

public enum CreateType {

    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");

    private int type;
    private String description;

    CreateType(int type,String description){
        this.type = type;
        this.description = description;
    }

}
