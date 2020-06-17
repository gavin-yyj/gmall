package com.gavin.demo;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 14:15 2020/6/12
 * @Description:
 */

public class URLPathClassLoader extends URLClassLoader {
    private String packageName = "net.xulingbo.classLoader";
    public URLPathClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findClass(name);
        if(aClass != null){
            return aClass;
        }
        if(!packageName.startsWith(name)){
            return super.loadClass(name);
        }else{
            return findClass(name);
        }
    }
}
