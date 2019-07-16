package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();


    private void addEmployee(){
        Employee employee1 = new Employee(0, "ooclNo1", 20, "F");
        Employee employee2 = new Employee(1, "ooclNo2", 21, "M");
        employees.add(employee1);
        employees.add(employee2);
    }

    @GetMapping()
    public ResponseEntity getAll() {
        addEmployee();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity getOne(@PathVariable Integer employeeId) {
        addEmployee();
        for (Employee value : employees) {
            if (value.getEmployeeId().equals(employeeId)) {
                return ResponseEntity.ok().body(value);
            }
        }
        return null;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Employee employee) {
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{employeeId}")
    public ResponseEntity update(@RequestBody Employee employee, @PathVariable Integer employeeId) {
        addEmployee();
        for (Employee value : employees) {
            if (value.getEmployeeId().equals(employeeId)) {
                value.setEmployeeName(employee.getEmployeeName());
                value.setEmployeeAge(employee.getEmployeeAge());
                value.setEmployeeGender(employee.getEmployeeGender());
                return ResponseEntity.ok().body(value);
            }
        }
        return null;
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity delete(@PathVariable Integer employeeId) {
        addEmployee();
        for (Employee value : employees) {
            if (value.getEmployeeId().equals(employeeId)) {
                employees.remove(value);
            }
        }
        return null;

    }
}
