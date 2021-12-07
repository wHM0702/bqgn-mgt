package com.kjde1033.bqgnmgt.config;


import com.kjde1033.bqgnmgt.dao.AreaDao;
import com.kjde1033.bqgnmgt.model.entity.Area;
import com.kjde1033.bqgnmgt.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisInit {

    @Resource
    private AreaDao areaDao;
    @Autowired
    private RedisUtils redisUtils;

    @PostConstruct
    //@PostConstruct:Java中该注解的说明：@PostConstruct该注解被用来修饰一个非静态的void（）方法。
    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    // PostConstruct在构造函数之后执行，init（）方法之前执行。
    //通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：
    public RedisInit redisInit(){
        List<Area> all = areaDao.getAll();
        for (Area area : all) {
            redisUtils.set(area.getId(),area.getAreaName());
        }
        return new RedisInit();
    }
}
