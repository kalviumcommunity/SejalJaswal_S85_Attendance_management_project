package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void displaySchoolDirectory(List<Person> people) {
        System.out.println("\n--- School Directory ---");
        if (people.isEmpty()) {
            System.out.println("No people in the directory.");
            return;
        }
        for (Person person : people) {
            person.displayDetails();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- School Administration & Attendance System (Overloaded Commands Demo) ---");

        // --- Data Setup ---
        Student student1 = new Student("Alice Wonderland", "Grade 10");
        Student student2 = new Student("Bob The Builder", "Grade 9");
        Teacher teacher1 = new Teacher("Dr. Emily Carter", "Physics");
        Staff staff1 = new Staff("Mr. John Davis", "Librarian");

        List<Person> schoolPeople = new ArrayList<>();
        schoolPeople.add(student1);
        schoolPeople.add(student2);
        schoolPeople.add(teacher1);
        schoolPeople.add(staff1);

        displaySchoolDirectory(schoolPeople);

        // --- Course Setup ---
        Course course1 = new Course("Intro to Quantum Physics");
        Course course2 = new Course("Advanced Algorithms");
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(course1);
        allCourses.add(course2);

        System.out.println("\n\n--- Available Courses ---");
        for (Course c : allCourses) {
            c.displayDetails();
        }

        // --- Initialize Services ---
        FileStorageService storageService = new FileStorageService();
        AttendanceService attendanceService = new AttendanceService(storageService);

        // --- Extract Students from Directory ---
        List<Student> allStudents = new ArrayList<>();
        for (Person p : schoolPeople) {
            if (p instanceof Student) {
                allStudents.add((Student) p);
            }
        }

        // --- Demonstrate Overloaded markAttendance() Methods ---
        System.out.println("\n\n--- Marking Attendance (Overloaded Methods) ---");

        // 1️⃣ Mark attendance directly using Student and Course objects
        attendanceService.markAttendance(student1, course1, "Present");
        attendanceService.markAttendance(student2, course2, "Absent");

        // 2️⃣ Mark attendance using IDs with lookups
        attendanceService.markAttendance(student1.getId(), course2.getCourseId(), "Late", allStudents, allCourses);

        // --- Display Attendance Logs ---
        System.out.println("\n\n--- Full Attendance Log ---");
        attendanceService.displayAttendanceLog();

        System.out.println("\n\n--- Attendance for Student: " + student1.getName() + " ---");
        attendanceService.displayAttendanceLog(student1);

        System.out.println("\n\n--- Attendance for Course: " + course2.getCourseName() + " ---");
        attendanceService.displayAttendanceLog(course2);

        // --- Save All Data ---
        System.out.println("\n\n--- Saving Attendance Data ---");
        attendanceService.saveAttendanceData();

        System.out.println("\nSession 8: Overloaded Commands Demonstration Complete.");
    }
}