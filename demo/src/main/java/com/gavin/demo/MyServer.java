package com.gavin.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 17:48 2020/6/11
 * @Description:
 */

public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777, 10);
            while (true){
                //这里底层就在进行三次握手，如果握手失败就会抛出异常
                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                Thread.sleep(1000);
                String recvString = dataInputStream.readUTF();
                System.out.println("客户端接收到："+ recvString);
                //发送
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String sendString = "服务端向客户端发起了一条对话 ";
                dataOutputStream.writeUTF(sendString);
                System.out.println(sendString);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
