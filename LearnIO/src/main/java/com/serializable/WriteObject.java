package com.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
        try (//创建一个ObjectOutputStream输出流
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            //将对象序列化到文件s
            Person person = new Person("jack", 23);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
