package com.kjde1033.bqgnmgt.service.impl;

import com.kjde1033.bqgnmgt.dao.ClassDao;
import com.kjde1033.bqgnmgt.dao.TeacherDao;
import com.kjde1033.bqgnmgt.model.entity.ClassEntity;
import com.kjde1033.bqgnmgt.model.entity.ClassType;
import com.kjde1033.bqgnmgt.model.vo.ClassVo;
import com.kjde1033.bqgnmgt.model.vo.TeacherVo;
import com.kjde1033.bqgnmgt.service.TeacherService;
import com.kjde1033.bqgnmgt.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherDao teacherDao;
    @Resource
    private ClassDao classDao;
    @Autowired
    private CommonUtils commonUtils;

    @Override
    public List<TeacherVo> getTeacherList(String query,int size, int pageNum) {
        int index = (pageNum-1)*size;
        return teacherDao.queryTeachers(query,index,size);
    }

    @Override
    public int queryTeacherCount(String query) {
        return teacherDao.queryTeacherCount(query);
    }

    @Override
    public boolean updTeacher(Integer teacherId, String comment) {
        int i = teacherDao.updTeacher(teacherId, comment);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delTeacher(Integer teacherId) {
        int i = teacherDao.delTeacher(teacherId);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<ClassVo> queryClassCdts(int pagenum, int size, String query,int type,int status) {
        int index = (pagenum-1)*size;
        //查出相应的班级信息
        List<ClassEntity> classEntities = classDao.queryClassEntityCdts(index, size, query,type,status);
        //加工班级信息中的某种属性
        List<ClassVo> classVoList = new ArrayList<>();
        for (ClassEntity c : classEntities) {
            ClassVo vo = new ClassVo();
            BeanUtils.copyProperties(c,vo);
            vo.setMasterName(classDao.getUserNameByUid(vo.getMaster()));
            vo.setTeacherName(classDao.getUserNameByUid(vo.getTeacher()));
            vo.setTypeStr(classDao.getTypeStrByTid(vo.getType()));
            vo.setStartTimeStr(commonUtils.DateToYMD(vo.getStartTime()));
            vo.setEndTimeStr(commonUtils.DateToYMD(vo.getEndTime()));
            classVoList.add(vo);
        }
        return classVoList;
    }

    @Override
    public int queryClassCount(String query) {
        return classDao.queryClassCount(query);
    }

    @Override
    public boolean updClassInfo(ClassVo classVo) throws Exception{
        classVo.setEndTime(commonUtils.StrToDate(classVo.getEndTimeStr()));
        int i = classDao.updClass(classVo.getCid(), classVo.getTeacher(), classVo.getMaster(), classVo.getStatus(), classVo.getEndTime());
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<ClassType> getAllClassType() {
        return classDao.getAllClassType();
    }

    @Override
    public boolean addClass(ClassVo classVo) {
        classVo.setEndTime(commonUtils.StrToDate(classVo.getEndTimeStr()));
        classVo.setStartTime(commonUtils.StrToDate(classVo.getStartTimeStr()));
        int i = classDao.addClass(classVo.getCname(), classVo.getType(), classVo.getTeacher(), classVo.getMaster(), classVo.getStartTime(), classVo.getEndTime());
        if(i>0){
            return true;
        }
        return false;
    }
}
