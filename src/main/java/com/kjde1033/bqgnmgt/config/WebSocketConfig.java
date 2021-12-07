package com.kjde1033.bqgnmgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
@Configuration
public class WebSocketConfig {

/*1、Java面向对象，对象有方法和属性，那么就需要对象实例来调用方法和属性（即实例化）；
2、凡是有方法或属性的类都需要实例化，这样才能具象化去使用这些方法和属性；
3、规律：凡是子类及带有方法或属性的类都要加上注册Bean到Spring IoC的注解；
4、把Bean理解为类的代理或代言人（实际上确实是通过反射、代理来实现的），这样它就能代表类拥有该拥有的东西了
5、我们都在微博上@过某某，对方会优先看到这条信息，并给你反馈，那么在Spring中，你标识一个@符号，
那么Spring就会来看看，并且从这里拿到一个Bean或者给出一个Bean*/
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
