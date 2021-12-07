package com.kjde1033.bqgnmgt.dao;

import com.kjde1033.bqgnmgt.model.entity.Menu;
import com.kjde1033.bqgnmgt.model.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    int updTeacherInfo(@Param("tid")int tid,@Param("comment")String comment,@Param("desc")String desc);

    int addUser(User user);

    int addTeacher(@Param("tid")int tid,@Param("comment")String comment,@Param("desc")String desc);

    int addMaster(@Param("tid")int tid,@Param("comment")String comment,@Param("desc")String desc);

    User getUser(@Param("phone")String phone,@Param("pwd")String pwd);

    User getUserPhone(@Param("phone")String phone);

    List<Menu> getFirstLevelMenusByType(@Param("type")int type);

    List<Menu> getSecondLevelMenuBySubIdAndUserType(@Param("type")int type,@Param("subId")int subId);

    int updUser(@Param("teacherId") Integer teacherId,@Param("userName") String userName,@Param("phone") String phone,@Param("age") Integer age,@Param("gender") String gender);

    int delUser(@Param("tid") Integer tid);
}
