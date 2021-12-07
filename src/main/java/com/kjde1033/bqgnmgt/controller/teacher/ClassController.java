package com.kjde1033.bqgnmgt.controller.teacher;

import com.kjde1033.bqgnmgt.model.dto.QueryModel;
import com.kjde1033.bqgnmgt.model.dto.ResultDto;
import com.kjde1033.bqgnmgt.model.entity.ClassType;
import com.kjde1033.bqgnmgt.model.vo.ClassVo;
import com.kjde1033.bqgnmgt.service.TeacherService;
import com.kjde1033.bqgnmgt.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/getClassList")
    public ResultDto getClassList(@RequestBody QueryModel queryModel){
        List<ClassVo> classVos = teacherService.queryClassCdts(queryModel.getPageNum(),queryModel.getPageSize(),queryModel.getQuery(),queryModel.getType(),queryModel.getStatus());
        int total = teacherService.queryClassCount(queryModel.getQuery());
        Map<String,Object> result = new HashMap<>();
        result.put("classVos",classVos);
        result.put("total",total);
        return ResultUtils.returnSuccess(result);
    }

    @PostMapping("/editClass")
    public ResultDto editClass(@RequestBody ClassVo classVo){
        try {
            boolean b = teacherService.updClassInfo(classVo);
            if(b){
                return ResultUtils.returnSuccess();
            }else{
                return ResultUtils.returnFailed(407,"no change");
            }
        } catch (Exception e) {
            return ResultUtils.returnFailed(500,e.getMessage());
        }
    }

    @GetMapping("/getTypeList")
    public ResultDto getTypeList(){
        List<ClassType> allClassType = teacherService.getAllClassType();
        return ResultUtils.returnSuccess(allClassType);
    }

    @PostMapping("/addClass")
    public ResultDto addClass(@RequestBody ClassVo classVo){
        if(teacherService.addClass(classVo)){
          return ResultUtils.returnSuccess();
        } else {
            return ResultUtils.returnFailed(400);
        }

    }
}
