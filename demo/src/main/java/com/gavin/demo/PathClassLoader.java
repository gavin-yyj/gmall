package com.gavin.demo;

import java.io.*;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 14:21 2020/6/12
 * @Description:
 */

public class PathClassLoader extends ClassLoader {
    private String classPath;
    private String packageName = "net.xulingbo.classloader";

    public PathClassLoader(ClassLoader parent, String classPath) {
        this.classPath = classPath;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(!packageName.startsWith(name)){
            byte[] classData = getData(name);
            if(classData == null){
                throw new ClassNotFoundException();
            }else{
                return defineClass(name,classData,0,classData.length);
            }
        }else{
            return super.loadClass(name);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.',File.separatorChar)+".class";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[2048];
            int num = 0;
            while((num = fileInputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes,0,num);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            System.out.println("文件路径不存在");
        } catch (IOException e) {
            System.out.println("文件格式有误，无法读取");
        }
        return null;
    }
}
