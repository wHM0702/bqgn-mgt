package com.kjde1033.bqgnmgt.controller;


import com.alibaba.fastjson.JSON;
import com.kjde1033.bqgnmgt.dao.AreaDao;
import com.kjde1033.bqgnmgt.dao.UserDao;
import com.kjde1033.bqgnmgt.model.dto.ResultDto;
import com.kjde1033.bqgnmgt.model.dto.UserModel;
import com.kjde1033.bqgnmgt.model.entity.Area;
import com.kjde1033.bqgnmgt.model.entity.User;
import com.kjde1033.bqgnmgt.model.vo.AreaVo;
import com.kjde1033.bqgnmgt.service.UserService;
import com.kjde1033.bqgnmgt.utils.CommonUtils;
import com.kjde1033.bqgnmgt.utils.RedisUtils;
import com.kjde1033.bqgnmgt.utils.ResultUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//相当于在每个方法上加ResponseBody
public class UserController {

    @Resource
    private UserDao userDao;
    @Resource
    private AreaDao areaDao;
    @Resource
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/getAllUsers")
    public ResultDto getAllUsers(){
        List<User> users = userDao.getAllUser();
        return ResultUtils.returnSuccess(users);
    }

    @RequestMapping("/updTeacherInfo")
    public ResultDto updTeacherInfo(int tid,
                                    @RequestParam(required = false,defaultValue = "") String comment,
                                    @RequestParam(required = false,defaultValue = "") String desc){
        if(comment.equals("")&&desc.equals("")){
            //不需要修改
            return ResultUtils.returnFailed(402,"修改信息不正确");
        }else {
            //需要修改
            int i  = userDao.updTeacherInfo(tid,comment,desc);
            if(i>0){
                //改成功
                return ResultUtils.returnSuccess();
            }else{
                return  ResultUtils.returnFailed(500,"数据库操作异常");
            }
        }
    }


    @RequestMapping("/addTeacher")
    public ResultDto addTeacher(@RequestBody UserModel model){
        System.out.println(model.getType());
        int id = CommonUtils.getId();
        User user = new User();
        //两个对象中相同属性复制一下，第一个参数是源头，第二个参数是目标
        BeanUtils.copyProperties(model,user);
        user.setUserId(id);
        if(model.getType().equals("教员")){
            user.setType(4);
        }
        int i = userDao.addUser(user);
        int j = userDao.addTeacher(id,model.getComment(),model.getDesc());
        if(i>0&&j>0){
            return ResultUtils.returnSuccess();
        }else{
            return ResultUtils.returnFailed(500,"数据库操作异常");
        }
    }

    @RequestMapping("/addMaster")
    public ResultDto addMaster(@RequestBody UserModel model){
        System.out.println(model.getType());
        int id = CommonUtils.getId();
        User user = new User();
        BeanUtils.copyProperties(model,user);
        user.setUserId(id);
        //添加
        int i = userDao.addUser(user);
        int j = userDao.addMaster(id,model.getComment(),model.getDesc());

        if(i>0&&j>0){
            return ResultUtils.returnSuccess();
        }else{
            return ResultUtils.returnFailed(500,"数据库操作异常");
        }
    }

    @RequestMapping("/checkPhone/{phone}")
    public ResultDto checkPhone(@PathVariable("phone") String phone){
        if(userService.checkUserPhone(phone)){
            return ResultUtils.returnSuccess();
        }else {
            return ResultUtils.returnFailed(506);
        }
    }

    @RequestMapping("/getArea")
    public ResultDto getArea(String aid){
//        List<String> option = (List<String>)(List)redisUtils.getListKey();
 //       Set<String> listKey = redisUtils.getListKey();
//        Map<String,String> result = new HashMap<>();
//        for (String options : option) {
//            result.put("options",options);
//        }
//        for (String s : listKey) {
//            System.out.println(s);
//        }
        aid="100";
        List<Object> listValue = redisUtils.getListValue(aid);
        return ResultUtils.returnSuccess(listValue);
    }





//    @RequestMapping("/getSubArea")
//    public String getSubArea(int aid){
//        //下级信息：sub-xxx
//        String key = "SUB-"+aid;
//        String value;
//        if(redisUtils.exist(key)){
//            //redis中存在，直接查询返回
//            value = (String)redisUtils.get(key);
//        }else{
//            //redis没有，先去查mysql
//            List<Area> subArea = areaDao.getSubArea(aid);
//            //查完mysql，将结果存入redis
//             value = JSON.toJSONString(subArea);
//            redisUtils.set(key,value);
//        }
//        //返回结果
//        return value;
//    }

    @RequestMapping("/getSubArea")
    public ResultDto getSubArea(@RequestBody AreaVo areaVo){
        System.out.println(areaVo.getCity());
        return ResultUtils.returnSuccess();
    }



}
