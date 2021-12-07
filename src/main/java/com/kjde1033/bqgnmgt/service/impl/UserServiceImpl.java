package com.kjde1033.bqgnmgt.service.impl;

import com.kjde1033.bqgnmgt.dao.UserDao;
import com.kjde1033.bqgnmgt.model.entity.Menu;
import com.kjde1033.bqgnmgt.model.entity.User;
import com.kjde1033.bqgnmgt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUser(String phone, String pwd) {
        User user = userDao.getUser(phone,pwd);
        if(user!=null){
            if(phone.equals(user.getPhone()) && pwd.equals(user.getPassword())){
                return user;
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Menu> getMenusByType(int userType) {
        List<Menu> menuList = userDao.getFirstLevelMenusByType(userType);
        for (Menu m:menuList) {
            m.setChildren(userDao.getSecondLevelMenuBySubIdAndUserType(userType,m.getMenuId()));
        }
        return menuList;
    }

    @Override
    public boolean updUser(Integer teacherId, String userName,String phone, Integer age, String gender) {
        int i = userDao.updUser(teacherId, userName,phone, age, gender);
        if(i>0){
            return true;

        }
        return false;
    }

    @Override
    public boolean checkUserPhone(String phone) {
        User userPhone = userDao.getUserPhone(phone);
        if(userPhone==null){
            return true;
        }
            return false;
    }

    @Override
    public boolean delUser(Integer teacherId) {
        int i = userDao.delUser(teacherId);
        if(i>0){
            return true;
        }
        return false;
    }
}
