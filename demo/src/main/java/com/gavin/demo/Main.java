package com.gavin.demo;


import java.util.Scanner;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 22:56 2020/6/11
 * @Description:
 */

public class Main{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();
        int start = str.length()-1;
        int end = str.length()-1;
        while(start>=0){
            //从右往左找第一个为空格的字符
            while(start>=0 && str.charAt(start) != ' '){
                start --;
            }
            sb.append(str.substring(start+1,end+1)+" ");
            //从右往左找第一个不为空格的字符
            while(start>=0 && str.charAt(start) == ' '){
                start --;
            }
            end = start;
        }
        System.out.println(sb.toString().trim());
    }
}
