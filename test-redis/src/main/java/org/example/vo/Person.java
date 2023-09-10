package org.example.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * @author zl-cai
 * @date 2023/9/9  23:17
 */

public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    String name;
    String companyName;

    public Person(String name, String companyName) {
        this.name = name;
        this.companyName = companyName;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
