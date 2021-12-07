package com.kjde1033.bqgnmgt.service;

import com.kjde1033.bqgnmgt.model.vo.MasterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MasterService {

    List<MasterVo> getMasterList(String query,int size, int pageNum);

    int queryMasterCount(String query);

    boolean delMaster(int masterId);

    List<MasterVo> getMasterListOut();

    int queryMastersOutCount(String query);
}
