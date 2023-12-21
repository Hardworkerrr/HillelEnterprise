package com.hillel.javaee.model;

import java.sql.Date;

public abstract class Person {
    private String name;
    private Date birthday;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
