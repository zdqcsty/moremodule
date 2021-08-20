package com.example.java_base.serial;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.130.7.206", 10009);
            ObjectInput objectInput = new ObjectInputStream(socket.getInputStream());
//            UserInfo userInfo = (UserInfo) objectInput.readObject();
            HashMap map = (HashMap) objectInput.readObject();

            System.out.println(map.toString());

//            System.out.println(userInfo.getAge());
//            System.out.println(userInfo.getName());
            objectInput.close();
//            System.out.println(userInfo);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
