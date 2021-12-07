package com.kjde1033.bqgnmgt.dao;

import com.kjde1033.bqgnmgt.model.vo.MasterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MasterDao {

    List<MasterVo> queryMasters(@Param("query") String query,@Param("index") int index, @Param("size") int size);

    int queryMasterCount(@Param("query") String query);

    int updMasterStatus(@Param("masterId") int masterId);

    List<MasterVo> queryMastersOut();

    int queryMastersOutCount(@Param("query") String query);
}
