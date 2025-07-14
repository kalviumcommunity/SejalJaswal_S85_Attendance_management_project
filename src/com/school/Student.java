package com.school;

public class Student {
    int Sid;
    String name;
    public void setter(int Sid, String name) {
        this.Sid = Sid;
        this.name = name;
    }
    public void display() {
        System.out.print("Student ID: " + Sid +" ");
        System.out.println("Name: " + name);
    }
    
}