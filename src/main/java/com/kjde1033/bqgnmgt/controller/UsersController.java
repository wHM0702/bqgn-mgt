package com.kjde1033.bqgnmgt.controller;

import com.alibaba.fastjson.JSON;
import com.kjde1033.bqgnmgt.model.dto.ResultDto;
import com.kjde1033.bqgnmgt.model.dto.UserModel;
import com.kjde1033.bqgnmgt.model.entity.Menu;
import com.kjde1033.bqgnmgt.model.entity.User;
import com.kjde1033.bqgnmgt.service.UserService;
import com.kjde1033.bqgnmgt.utils.CommonUtils;
import com.kjde1033.bqgnmgt.utils.RedisUtils;
import com.kjde1033.bqgnmgt.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/login")
    public ResultDto login(@RequestBody UserModel userModel){
        User user = userService.getUser(userModel.getPhone(), userModel.getPassword());
        if(user!=null){
            //登录信息正确
            String token = commonUtils.generateToken(user);
            //将这个人的这个token存到Redis当中
            saveToken(token,user);
            //将它的token传给前端
            return ResultUtils.returnSuccess(token,user);
        }else{
            return ResultUtils.returnFailed(403,"手机号或密码错误");
        }
    }

    @RequestMapping("/getMenus")
    public ResultDto getMenus(HttpServletRequest request){
        String token = request.getHeader("token");
        int userType = new Integer(token.substring(0,1));
        List<Menu> result = userService.getMenusByType(userType);
        return ResultUtils.returnSuccess(result);
    }

    @RequestMapping("/getTest")
    public ResultDto getTest(HttpServletRequest request){
        String tk = request.getHeader("auth");
        System.out.println(tk);
        return ResultUtils.returnSuccess("xxxx");
    }


    private void saveToken(String token,User user){
        String userId = user.getUserId()+"";
        boolean b = redisUtils.exist(userId);
        if(b){
            //说明这个人已经是登录状态
            String tokenOld = (String) redisUtils.get(userId);
            redisUtils.delete(userId);
            redisUtils.delete(tokenOld);
        }

        redisUtils.set(user.getUserId()+"",30*60,token);
        String userStr = JSON.toJSONString(user);
        System.out.println("str:"+userStr);
        redisUtils.set(token,30*60,userStr);
    }
}
