package com.kjde1033.bqgnmgt.service;

import com.kjde1033.bqgnmgt.model.entity.Menu;
import com.kjde1033.bqgnmgt.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService {

    User getUser(String phone, String pwd);

    boolean checkUserPhone(String phone);

    List<Menu> getMenusByType(int userType);

    boolean updUser( Integer teacherId, String userName, String phone, Integer age, String gender);

    boolean delUser( Integer teacherId);
}
