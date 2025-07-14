// Source code is decompiled from a .class file using FernFlower decompiler.
package com.school;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {
     
      Course[] courses = new Course[2];
      courses[0] = new Course();
      courses[0].setCourseDetails(101, "Mathematics");
      courses[1] = new Course();
      courses[1].setCourseDetails(102, "Science");


      for (Course course : courses) {
          course.displayCourse();
      }


      Student[] students = new Student[2];
      students[0] = new Student();
      students[0].setter(1, "Vikram");
      students[1] = new Student();
      students[1].setter(2, "Ashish");

   
      for (Student student : students) {
          student.display();
      }

     
   }
}