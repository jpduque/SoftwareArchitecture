package com.UCDC.entities;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    private int Id;
    private int StudentCode;
    private String StudentName;
    private String StudentSurname;
    private String StudentMail;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(int studentCode) {
        StudentCode = studentCode;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentSurname() {
        return StudentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        StudentSurname = studentSurname;
    }

    public String getStudentMail() {
        return StudentMail;
    }

    public void setStudentMail(String studentMail) {
        StudentMail = studentMail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", StudentCode=" + StudentCode +
                ", StudentName='" + StudentName + '\'' +
                ", StudentSurname='" + StudentSurname + '\'' +
                ", StudentMail='" + StudentMail + '\'' +
                '}';
    }
}
