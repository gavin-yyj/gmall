package com.gavin.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 16:17 2020/6/11
 * @Description:
 */

public class JavaWebTest {
    @Test
    public void readFileTest(){
        //创建File，也就是指定文件的路径跟名称，但是不会去检查是否存在
        File file = new File("C:/file.txt");
        try {
            //这里读取文件内容，如果文件不存在，会抛出异常
            FileReader fileReader = new FileReader(file);
            StringBuffer stringBuffer = new StringBuffer();
            char[] chars = new char[1024];
            int len = 0;
            //这里就将读取到的文件内容存入chars缓存区中
            while ((len = fileReader.read(chars))>0){
                stringBuffer.append(chars,0,len);
            }
            System.out.println("文件中的内容是："+stringBuffer.toString());
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("文件读取错误");
        }
    }
}
