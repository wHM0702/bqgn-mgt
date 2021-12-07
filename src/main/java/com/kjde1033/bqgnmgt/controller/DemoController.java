package com.kjde1033.bqgnmgt.controller;

import com.kjde1033.bqgnmgt.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "这是王好明写的用户相关的操作")
public class DemoController {



    @ApiOperation(value = "这是乘法功能",notes = "输入两个数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a",dataType = "double",example = "123"),
            @ApiImplicitParam(name = "b",dataType = "double",example = "356")
    })
    @RequestMapping(value = "/t1",method = RequestMethod.GET)
    public String t1(int a, int b){
        return a*b+"";
    }
}
