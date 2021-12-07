package com.kjde1033.bqgnmgt.service;

import com.kjde1033.bqgnmgt.model.entity.ClassType;
import com.kjde1033.bqgnmgt.model.vo.ClassVo;
import com.kjde1033.bqgnmgt.model.vo.TeacherVo;

import java.util.List;

public interface TeacherService {

    List<TeacherVo> getTeacherList(String query,int size,int pageNum);

    int queryTeacherCount(String query);

    boolean updTeacher( Integer teacherId,  String comment);

    boolean delTeacher( Integer teacherId);

    List<ClassVo> queryClassCdts(int pagenum,int size,String query,int type,int status);

    int queryClassCount(String query);

    boolean updClassInfo(ClassVo model) throws Exception;

    List<ClassType> getAllClassType();

    boolean addClass(ClassVo classVo);
}
