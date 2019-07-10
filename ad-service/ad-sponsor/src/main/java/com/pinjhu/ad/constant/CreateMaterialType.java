package com.pinjhu.ad.constant;

import lombok.Getter;

@Getter
public enum  CreateMaterialType {
    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreateMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
