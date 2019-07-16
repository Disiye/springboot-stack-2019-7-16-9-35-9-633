package com.tw.apistackbase.controller;

public class Employee {
    private Integer employeeId;
    private String employeeName;
    private int employeeAge;
    private String employeeGender;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public Employee() {

    }

    public Employee(Integer employeeId, String employeeName, int employeeAge, String employeeGender) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeGender = employeeGender;
    }
}
