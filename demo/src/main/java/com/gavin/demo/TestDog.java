package com.gavin.demo;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 22:03 2020/6/13
 * @Description:
 */

class Animal{
    public void move(){
        System.out.println("the animal is moving");
    }
}

class Dog extends Animal{
    @Override
    public void move(){
        System.out.println("the dog can run");
    }
    public void bark(){
        System.out.println("the dog can bark");
    }
}

public class TestDog{
    public static void main(String args[]){
        Animal a = new Animal();
        Animal b = new Dog();
//        ((Dog) b).bark();
    }
}
