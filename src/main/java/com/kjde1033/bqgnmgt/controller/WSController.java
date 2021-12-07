package com.kjde1033.bqgnmgt.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//使用@ServerEndpoint注解监听一个WebSocket请求路径：
@ServerEndpoint("/webscoket")
/*@component： 标注一个类为Spring容器的Bean，（把普通pojo实例化到spring容器中，相当于配置文件中的）
@Component： 标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean。*/
@Component
public class WSController {

    private Session session;

    private static CopyOnWriteArraySet<WSController> sockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        sockets.add(this);
        System.out.println("连上了，目前有"+sockets.size()+"个人在用");
    }

    @OnClose
    public void onClose(){
        sockets.remove(this);
        System.out.println("关闭了，目前有"+sockets.size()+"个人在用");
    }

    @OnMessage
    public void onMessage(Session session,String message){
        for (WSController ws:sockets) {

            if (ws==this){
                continue;
            }

            try {
                ws.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
