package com.school;

public class Course {
    int courseId;
    String courseName;
    
    public void setCourseDetails(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    
    public void displayCourse() {
        System.out.print("Course ID: " + courseId +" ");
        System.out.println("Course Name: " + courseName);
    }
    
}
