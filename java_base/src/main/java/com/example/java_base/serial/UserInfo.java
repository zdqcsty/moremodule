package com.example.java_base.serial;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3103317843148814899L;
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}