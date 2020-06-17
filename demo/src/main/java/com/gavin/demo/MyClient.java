package com.gavin.demo;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 18:05 2020/6/11
 * @Description:
 */

public class MyClient {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            createSocket(i);
        }
    }

    private static void createSocket(int num) {
        try {
            //这里底层就在进行三次握手，如果握手失败就会抛出异常
            Socket socket = new Socket("localhost", 7777);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            String sendString = "客户端"+num+"向服务器发送一条消息";
            dataOutputStream.writeUTF(sendString);
            System.out.println("发送给服务器："+sendString);
            //接收
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String recvString = dataInputStream.readUTF();
            System.out.println("从服务器收到"+recvString);
            socket.close();

        } catch (IOException e) {
            System.out.println("连接失败，地址错误或服务器拒绝连接");
        }
    }

    @Test
    public void test(){
        String file = "C:/stream.txt";
        String charset = "utf-8";
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
            try {
                writer.write("这是要保存的中文字符");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
            //读取字节转换成字符
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream);
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[64];
            int count = 0;
            try {
                while((count = reader.read(chars)) != -1){
                    buffer.append(chars,0,count);
                }
                System.out.println(buffer.toString());
            }finally {
                reader.close();
            }

        } catch (IOException e) {
            System.out.println("文件不存在");
        }
    }
}
