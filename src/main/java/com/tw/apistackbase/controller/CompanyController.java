package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private List<Company> companies = new ArrayList<>();
    private List<Employee> employeesOOCL = new ArrayList<>();
    private List<Employee> employeesCOSCO = new ArrayList<>();

    private void addEmployeeOOCL() {
        employeesOOCL.add(new Employee(0, "ooclNo1", 20, "F"));
        employeesOOCL.add(new Employee(1, "ooclNo2", 21, "M"));
    }

    private void addEmployeeCOSCO() {
        employeesCOSCO.add(new Employee(0, "cosco1", 20, "F"));
        employeesCOSCO.add(new Employee(1, "cosco2", 21, "M"));
    }

    private void addCompany() {
        companies.add(new Company(0, "OOCL", 1000, employeesOOCL));
        companies.add(new Company(1, "COSCO", 2000, employeesCOSCO));
    }

    private void initializationData() {
        addEmployeeOOCL();
        addEmployeeCOSCO();
        addCompany();
    }

    @GetMapping()
    public ResponseEntity getAll() {
        initializationData();
        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("{companyId}")
    public ResponseEntity getOne(@PathVariable Integer companyId) {
        initializationData();
        for (Company value : companies) {
            if (value.getCompanyId() == companyId) {
                return ResponseEntity.ok().body(value);
            }
        }
        return null;
    }

    @GetMapping("{companyId}/employees")
    public ResponseEntity getCompanyEmployees(@PathVariable Integer companyId) {
        initializationData();
        for (Company value : companies) {
            if (value.getCompanyId() == companyId) {
                return ResponseEntity.ok().body(value.getEmployees());
            }
        }
        return null;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Company company) {
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{companyId}")
    public ResponseEntity update(@RequestBody Company company, @PathVariable Integer companyId) {
        initializationData();
        for (Company value : companies) {
            if (value.getCompanyId() == companyId) {
                value.setCompanyName(company.getCompanyName());
                value.setEmployees(company.getEmployees());
                value.setEmployeesNumber(company.getEmployeesNumber());
                return ResponseEntity.ok().body(value);
            }
        }
        return null;
    }

}
