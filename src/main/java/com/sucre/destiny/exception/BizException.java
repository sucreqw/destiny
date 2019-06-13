package com.sucre.destiny.exception;

import com.sucre.destiny.enums.ResultCodeEnum;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 874200307902570567L;
    private int code;

    public BizException(int code, String message){
        super(message);
        this.code = code;
    }

    public BizException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
}
