package com.kjde1033.bqgnmgt.controller.master;

import com.kjde1033.bqgnmgt.model.dto.QueryModel;
import com.kjde1033.bqgnmgt.model.dto.ResultDto;
import com.kjde1033.bqgnmgt.model.vo.MasterVo;
import com.kjde1033.bqgnmgt.model.vo.TeacherVo;
import com.kjde1033.bqgnmgt.service.MasterService;
import com.kjde1033.bqgnmgt.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/getMasterList")
    public ResultDto getMasterList(@RequestBody QueryModel queryModel){
        List<MasterVo> masterList = masterService.getMasterList(queryModel.getQuery(),queryModel.getPageSize(), queryModel.getPageNum());
        int total = masterService.queryMasterCount(queryModel.getQuery());
        Map<String,Object> result = new HashMap<>();
        result.put("masterList",masterList);
        result.put("total",total);
        return ResultUtils.returnSuccess(result);
    }

    @GetMapping("/delMaster")
    public ResultDto delMaster(int masterId){
        if(masterService.delMaster(masterId)){
            return ResultUtils.returnSuccess();
        }else {
            return ResultUtils.returnFailed(500);
        }
    }

    @RequestMapping("/getMasterListOut")
    public ResultDto getMasterListOut(@RequestBody QueryModel queryModel){
        List<MasterVo> masterList = masterService.getMasterListOut();
        int total = masterService.queryMastersOutCount(queryModel.getQuery());
        Map<String,Object> result = new HashMap<>();
        result.put("masterList",masterList);
        result.put("total",total);
        for (Map.Entry<String, Object> stringObjectEntry : result.entrySet()) {
            System.out.println(stringObjectEntry.getValue());
        }

        return ResultUtils.returnSuccess(result);
    }


}
