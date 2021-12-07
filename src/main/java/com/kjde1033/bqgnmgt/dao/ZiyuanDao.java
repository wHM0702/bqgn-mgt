package com.kjde1033.bqgnmgt.dao;

import com.kjde1033.bqgnmgt.model.entity.Ziyuan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZiyuanDao {

    int addZiyuan(@Param("rname")String rname,@Param("rpath")String rpath);

    List<Ziyuan> getAllZiyuan();
}
