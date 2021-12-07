package com.kjde1033.bqgnmgt.dao;


import com.kjde1033.bqgnmgt.model.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaDao {

    List<Area> getAll();

    int getName(@Param("id") String id);

    List<Area> getSubArea(@Param("aid") int aid);

}
