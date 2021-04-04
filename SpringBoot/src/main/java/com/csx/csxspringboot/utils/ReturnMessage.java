package com.csx.csxspringboot.utils;


/**
 * @ClassName: ReturnMessage
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/4/4 19:12
 */
public class ReturnMessage {

    private Integer responseCode;
    private String message;
    private Object data;

    public static String retSuccess(){

        return "Success";
    }

    public static String retSuccess(String message){
        return message;
    }


}
