package com.pinjhu.ad.index;

public enum DataLevel {

    LEVEL2("2","Level2"),
    LEVEL3("3","Level3"),
    LEVEL4("4","Level4");

    private  String level;
    private  String desc;

    DataLevel(String level,String desc){

        this.level = level;
        this.desc = desc;
    }
}
