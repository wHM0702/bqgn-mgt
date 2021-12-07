package com.kjde1033.bqgnmgt.dao;

import com.kjde1033.bqgnmgt.model.entity.ClassEntity;
import com.kjde1033.bqgnmgt.model.entity.ClassType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ClassDao {

    List<ClassEntity> queryClassEntityCdts(@Param("index") int index,@Param("size")int size,@Param("query")String query,@Param("type")int type,@Param("status")int status);

    String getUserNameByUid(@Param("uid") int uid);

    String getTypeStrByTid(@Param("tid") int tid);

    int queryClassCount(@Param("query") String query);

    int updClass(@Param("cid") int cid,@Param("teacher") int teacher,@Param("master") int master,@Param("status") int status,@Param("endTime") Date endTime);

    List<ClassType> getAllClassType();

    int addClass(@Param("cname") String cname,@Param("type") int type,@Param("teacher") int teacher,@Param("master") int master,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}
