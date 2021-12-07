package com.kjde1033.bqgnmgt.controller.teacher;

import com.kjde1033.bqgnmgt.model.dto.QueryModel;
import com.kjde1033.bqgnmgt.model.dto.ResultDto;
import com.kjde1033.bqgnmgt.model.vo.TeacherVo;
import com.kjde1033.bqgnmgt.service.TeacherService;
import com.kjde1033.bqgnmgt.service.UserService;
import com.kjde1033.bqgnmgt.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getTeacherList")
    public ResultDto getTeacherList(@RequestBody QueryModel queryModel){
        List<TeacherVo> teacherList = teacherService.getTeacherList(queryModel.getQuery(),queryModel.getPageSize(), queryModel.getPageNum());
        int total = teacherService.queryTeacherCount(queryModel.getQuery());
        Map<String,Object> result = new HashMap<>();
        result.put("teacherList",teacherList);
        result.put("total",total);
        return ResultUtils.returnSuccess(result);
    }

    @PutMapping("/editTeacher")
    public ResultDto editTeacher(@RequestBody TeacherVo teacherVo){
        boolean b = teacherService.updTeacher(teacherVo.getTeacherId(), teacherVo.getComment());
        boolean b1 = userService.updUser(teacherVo.getTeacherId(),teacherVo.getUserName(), teacherVo.getPhone(), teacherVo.getAge(), teacherVo.getGender());
        if(b && b1){
            return ResultUtils.returnSuccess();
        }else {
            return  ResultUtils.returnFailed(400);
        }
    }

    @PutMapping("/delTeacher/{tid}")
    public ResultDto delTeacher(@PathVariable int tid){
        boolean b = teacherService.delTeacher(tid);
        boolean b1 = userService.delUser(tid);
        if(b && b1){
            return ResultUtils.returnSuccess();
        }else {
            return  ResultUtils.returnFailed(400);
        }
    }

}
