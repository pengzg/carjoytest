package main.java.xyz.carjoy.db;

public class Student extends Person{

    public String className;
    public String address;

    public Student(){
        super();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void main(String[] args) {

    }
}
