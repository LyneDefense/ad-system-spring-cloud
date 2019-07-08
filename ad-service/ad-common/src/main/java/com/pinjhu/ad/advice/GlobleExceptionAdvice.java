package com.pinjhu.ad.advice;

import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobleExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req,
                                                    AdException ex){
        CommonResponse<String> response = new CommonResponse<>(-1,"error");
        response.setData(ex.getMessage());
        return response;
    }
}
