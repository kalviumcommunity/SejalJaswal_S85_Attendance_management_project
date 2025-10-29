package com.school;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {

    private final FileStorageService storageService;

    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final List<Staff> staffMembers = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();

    public RegistrationService(FileStorageService storageService) {
        this.storageService = storageService;
    }

    // Registration methods
    public Student registerStudent(String name, String gradeLevel) {
        Student s = new Student(name, gradeLevel);
        students.add(s);
        return s;
    }

    public Teacher registerTeacher(String name, String subject) {
        Teacher t = new Teacher(name, subject);
        teachers.add(t);
        return t;
    }

    public Staff registerStaff(String name, String department) {
        Staff st = new Staff(name, department);
        staffMembers.add(st);
        return st;
    }

    public Course createCourse(String courseName) {
        Course c = new Course(courseName);
        courses.add(c);
        return c;
    }

    // Getters
    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // Finders
    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getCourseId() == id)
                return c;
        }
        return null;
    }

    // Aggregation
    public List<Person> getAllPeople() {
        List<Person> result = new ArrayList<>();
        result.addAll(students);
        result.addAll(teachers);
        result.addAll(staffMembers);
        return result;
    }

    // Persistence
    public void saveAllRegistrations() {
        if (!students.isEmpty()) {
            storageService.saveData(students, "students.txt");
        } else {
            System.out.println("No students to save.");
        }

        if (!teachers.isEmpty()) {
            storageService.saveData(teachers, "teachers.txt");
        } else {
            System.out.println("No teachers to save.");
        }

        if (!staffMembers.isEmpty()) {
            storageService.saveData(staffMembers, "staff.txt");
        } else {
            System.out.println("No staff members to save.");
        }

        if (!courses.isEmpty()) {
            storageService.saveData(courses, "courses.txt");
        } else {
            System.out.println("No courses to save.");
        }
    }
}