package com.szh.testpic.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sunzhijun on 2017/12/19.
 */
@Entity
public class Student {

    @Id
    private long id;
    private String name;
    @Generated(hash = 1314737876)
    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}