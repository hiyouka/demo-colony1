package com.jy.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/client/{userId}")
@Component
public class WebSocketDemo {

//    private static Logger logger = LogManager.getLogger(WebSocketDemo.class.getName());
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    public static ConcurrentHashMap<String, WebSocketDemo> wsClientMap = new ConcurrentHashMap<>();

//    private static CopyOnWriteArraySet<WebSocketDemo> wsClientMap = new CopyOnWriteArraySet<WebSocketDemo>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String userno = "";

    /**
     * 连接建立成功调用的方法
     * @param session 当前会话session
     */
    @OnOpen
    public void onOpen (@PathParam("userId")String userId, Session session){
        System.out.println("+++++++++++++++++++"+userId);
        this.session = session;
        wsClientMap.put(userId,this);
        userno = userId;
        addOnlineCount();
        System.out.println(session.getId()+"有新链接加入，当前链接数为：" + wsClientMap.size());
//        response.addHeader("Connection","upgrade");
//        response.addHeader("Upgrade","websocket");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose (){
//        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        wsClientMap.remove(userno);
        subOnlineCount();
        System.out.println("有一链接关闭，当前链接数为：" + wsClientMap.size());
    }


    /**
     * 收到客户端消息
     * @param message 客户端发送过来的消息
     * @param session 当前会话session
     * @throws IOException
     */
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("来终端的警情消息:" + message);
        sendMsgToAll(message);
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("wsClientMap发生错误!");
        error.printStackTrace();
    }

    /**
     * 给所有客户端群发消息
     * @param message 消息内容
     * @throws IOException
     */
    private void sendMsgToAll(String message) throws IOException {
        for ( WebSocketDemo item : wsClientMap.values()){
            item.session.getBasicRemote().sendText(message);
        }
        System.out.println("成功群送一条消息:" + wsClientMap.size());
    }

    public void sendMessage (String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        System.out.println("成功发送一条消息:" + message);
    }

    private static synchronized  int getOnlineCount(){
        return WebSocketDemo.onlineCount;
    }

    private static synchronized void addOnlineCount(){
        WebSocketDemo.onlineCount++;
    }

    private static synchronized void subOnlineCount(){
        WebSocketDemo.onlineCount--;
    }
}  