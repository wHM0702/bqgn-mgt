package com.kjde1033.bqgnmgt.utils;

import com.kjde1033.bqgnmgt.model.dto.ResultDto;

public class ResultUtils {

    public static ResultDto returnSuccess(){
        ResultDto dto = new ResultDto();
        dto.setCode(200);
        dto.setMsg("success");
        return dto;
    }

    public static ResultDto returnSuccess(String msg){
        ResultDto dto = new ResultDto();
        dto.setCode(200);
        dto.setMsg(msg);
        return dto;
    }

    public static ResultDto returnSuccess(Object data){
        ResultDto dto = new ResultDto();
        dto.setCode(200);
        dto.setMsg("success");
        dto.setData(data);
        return dto;
    }

    public static ResultDto returnSuccess(String msg,Object data){
        ResultDto dto = new ResultDto();
        dto.setCode(200);
        dto.setMsg(msg);
        dto.setData(data);
        return dto;
    }

    public static ResultDto returnFailed(int code){
        ResultDto dto = new ResultDto();
        dto.setCode(code);
        return dto;
    }

    public static ResultDto returnFailed(int code,String msg){
        ResultDto dto = new ResultDto();
        dto.setCode(code);
        dto.setMsg(msg);
        return dto;
    }
}
