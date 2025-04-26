package org.example.core;

//回复类 JSON格式
public class Response {

    private int code;//状态码
    private String businessCode;//业务码
    private String message;//用户友好提示
    private Object data;//数据

    // 构造方法
    public Response(int code, String businessCode, String message) {
        this(code, businessCode, message, null);
    }

    public Response() {
    }
    public Response(int code, String businessCode, String message, Object data) {
        this.code = code;
        this.businessCode = businessCode;
        this.message = message;
        this.data = data;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}