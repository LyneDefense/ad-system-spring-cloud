package com.pinjhu.ad.constant;

import lombok.Getter;

@Getter
public enum CommonStatus {

    VALID (1,"有效状态"),
    INVALID(0,"无效状态");

    private int status;
    private String description;

    CommonStatus(int status,String description){
        this.status = status;
        this.description = description;
    }


}
