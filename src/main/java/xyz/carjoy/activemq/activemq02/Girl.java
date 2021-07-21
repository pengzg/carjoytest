package xyz.carjoy.activemq.activemq02;

import java.io.Serializable;

public class Girl implements Serializable {


    public Girl(String name, Integer age, float price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    private String name;
    private Integer age;

    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
