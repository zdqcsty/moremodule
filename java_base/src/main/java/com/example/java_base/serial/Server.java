package com.example.java_base.serial;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10009);
            Socket socket = serverSocket.accept();
            ObjectOutput objectOutput = new ObjectOutputStream(socket.getOutputStream());

//            UserInfo userInfo = new UserInfo();
//            userInfo.setAge(12);
//            userInfo.setName("Tom");

            Map<Integer, String> map = new HashMap();
            map.put(1, "zeng");

            objectOutput.writeObject(map);

            //            objectOutput.writeObject(userInfo);
            objectOutput.flush();
            objectOutput.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
