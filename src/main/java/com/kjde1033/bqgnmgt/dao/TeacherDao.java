package com.kjde1033.bqgnmgt.dao;

import com.kjde1033.bqgnmgt.model.vo.TeacherVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {

    List<TeacherVo> queryTeachers(@Param("query") String query,@Param("index") int index,@Param("size") int size);

    int queryTeacherCount(@Param("query") String query);

    int updTeacher(@Param("teacherId") Integer teacherId,@Param("comment") String comment);

    int delTeacher(@Param("tid") Integer tid);
}
