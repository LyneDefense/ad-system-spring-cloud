package com.pinjhu.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//lombok自动填充get和set方法
@Data
//lombok自动构造无参数的构造函数
@NoArgsConstructor
//lombok自动构造全参数的构造函数
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
