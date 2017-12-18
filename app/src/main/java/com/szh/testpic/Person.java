package com.szh.testpic;

/**
 * Gson转换测试类
 * Created by sunzhijun on 2017/12/18.
 */

public class Person {
    private String name;
    private int id;

    public Person() {

    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n\tPerson{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
