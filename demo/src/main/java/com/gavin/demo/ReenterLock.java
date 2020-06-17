package com.gavin.demo;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 0:28 2020/6/8
 * @Description:
 */

public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    @Test
    public void test() throws InterruptedException {


        ReenterLock tl = new ReenterLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);

        t2.start();
        t1.start();

        t1.join();
        System.out.println(t1.getName()+":"+i);
        t2.join();
        System.out.println(t2.getName()+":"+i);
    }
}
