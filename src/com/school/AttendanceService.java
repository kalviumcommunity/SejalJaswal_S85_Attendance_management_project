package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService {

    private List<AttendanceRecord> attendanceLog;
    private FileStorageService storageService;

    // Constructor
    public AttendanceService(FileStorageService storageService) {
        this.attendanceLog = new ArrayList<>();
        this.storageService = storageService;
    }

    // 1️⃣ Overloaded Method: Mark attendance using Student and Course objects
    public void markAttendance(Student student, Course course, String status) {
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
        System.out.println("Marked attendance for " + student.getName() +
                           " in " + course.getCourseName() + " as " + status);
    }

    // 2️⃣ Overloaded Method: Mark attendance using IDs and lookup
    public void markAttendance(int studentId, int courseId, String status,
                               List<Student> allStudents, List<Course> allCourses) {
        Student student = findStudentById(studentId, allStudents);
        Course course = findCourseById(courseId, allCourses);

        if (student != null && course != null) {
            markAttendance(student, course, status);
        } else {
            System.out.println("❌ Error: Could not find Student or Course for given IDs (" +
                               studentId + ", " + courseId + ")");
        }
    }

    // Helper method: Find Student by ID
    private Student findStudentById(int id, List<Student> students) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Helper method: Find Course by ID
    private Course findCourseById(int id, List<Course> courses) {
        for (Course c : courses) {
            if (c.getCourseId() == id) {
                return c;
            }
        }
        return null;
    }

    // 3️⃣ Display all attendance records
    public void displayAttendanceLog() {
        if (attendanceLog.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }
        attendanceLog.forEach(AttendanceRecord::displayRecord);
    }

    // 4️⃣ Display attendance for a specific student
    public void displayAttendanceLog(Student student) {
        List<AttendanceRecord> filtered = attendanceLog.stream()
                .filter(r -> r.getStudent().equals(student))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No attendance records found for student: " + student.getName());
        } else {
            filtered.forEach(AttendanceRecord::displayRecord);
        }
    }

    // 5️⃣ Display attendance for a specific course
    public void displayAttendanceLog(Course course) {
        List<AttendanceRecord> filtered = attendanceLog.stream()
                .filter(r -> r.getCourse().equals(course))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No attendance records found for course: " + course.getCourseName());
        } else {
            filtered.forEach(AttendanceRecord::displayRecord);
        }
    }

    // 6️⃣ Save attendance data to file
    public void saveAttendanceData() {
        if (attendanceLog.isEmpty()) {
            System.out.println("No attendance data to save.");
            return;
        }
        storageService.saveData(attendanceLog, "attendance_log.txt");
        System.out.println("✅ Attendance data saved successfully to attendance_log.txt");
    }
}