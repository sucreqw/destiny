package com.sucre.destiny.enums;


public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    ERROR(500, "error"),
    ACCOUNT_ERROR(501, "账号错误"),
    PASSWORD_ERROR(502, "密码错误"),
    AUTH_ERROR(503, "权限异常"),
    CONVERT_ERROR(504,"八字转换出错");

    private int code;

    private String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
