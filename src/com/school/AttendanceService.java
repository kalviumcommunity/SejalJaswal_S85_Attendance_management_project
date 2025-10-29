package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService {

    private List<AttendanceRecord> attendanceLog;
    private FileStorageService storageService;
    private RegistrationService registrationService;

    // Constructor with dependencies injected
    public AttendanceService(FileStorageService storageService, RegistrationService registrationService) {
        this.attendanceLog = new ArrayList<>();
        this.storageService = storageService;
        this.registrationService = registrationService;
    }

    // 1️⃣ Overloaded Method: Mark attendance using Student and Course objects
    public void markAttendance(Student student, Course course, String status) {
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
        System.out.println("Marked attendance for " + student.getName() +
                " in " + course.getCourseName() + " as " + status);
    }

    // 2️⃣ Overloaded Method: Mark attendance using IDs (lookups via
    // RegistrationService)
    public void markAttendance(int studentId, int courseId, String status) {
        Student student = registrationService.findStudentById(studentId);
        Course course = registrationService.findCourseById(courseId);

        if (student != null && course != null) {
            markAttendance(student, course, status);
        } else {
            System.out.println("❌ Error: Could not find Student or Course for given IDs (" +
                    studentId + ", " + courseId + ")");
        }
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