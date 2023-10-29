package ggm;

import java.util.Objects;

class Student {
    private String name;
    private String lastName;
    private int age;

    public Student(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

  

   

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", lastName=" + lastName + '}';
    }
    
    
    
}
