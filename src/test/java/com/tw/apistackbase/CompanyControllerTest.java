package com.tw.apistackbase;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {

    }

    @Test
    public void return_all_company_when_get_company() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/companies")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
//        assertEquals(0, jsonArray.getJSONObject(0).getInt("companyId"));
        assertEquals("OOCL", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(1000, jsonArray.getJSONObject(0).getInt("employeesNumber"));
        assertEquals(1, jsonArray.getJSONObject(1).getInt("companyId"));
        assertEquals("COSCO", jsonArray.getJSONObject(1).getString("companyName"));
        assertEquals(2000, jsonArray.getJSONObject(1).getInt("employeesNumber"));
    }

    @Test
    public void return_oocl_company_when_get_company_0() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/companies/0")).andExpect(status().isOk()).andReturn();
        JSONObject jsonArray = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(0, jsonArray.getInt("companyId"));
        assertEquals("OOCL", jsonArray.getString("companyName"));
        assertEquals(1000, jsonArray.getInt("employeesNumber"));
    }

    @Test
    public void return_oocl_employee_when_get_oocl_employee() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/companies/0/employees")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("ooclNo1", jsonArray.getJSONObject(0).getString("employeeName"));
//        assertEquals(20, jsonArray.getJSONObject(0).getInt("employeeAge"));
        assertEquals("F", jsonArray.getJSONObject(0).getString("employeeGender"));
    }

//    @Test
//    public void should_return_companies_when_paging() throws Exception {
//        String content = this.mockMvc.perform(get("/companies?page=2&pageSize=2"))
//                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//        JSONArray json = new JSONArray(content);
//        assertEquals(2,json.length());
//        assertEquals("C",json.getJSONObject(0).getString("companyName"));
//    }


    @Test
    public void return_status_is_created_when_put_new_company() throws Exception {
        Employee employee1 = new Employee(0, "ooclNo1", 20, "F");
        Employee employee2 = new Employee(1, "ooclNo2", 21, "M");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        Company company = new Company(0, "OOCL", 1000, employees);
        JSONObject jsonObject = new JSONObject(company);
        String objectJson = jsonObject.toString();
        this.mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());
    }

    @Test
    public void return_revised_company_when_put_company_info() throws Exception {
        Employee employee1 = new Employee(2, "ooclNo3", 22, "M");
        Employee employee2 = new Employee(3, "ooclNo4", 23, "F");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        Company company = new Company(0, "OOCLL", 1001, employees);
        JSONObject jsonObject = new JSONObject(company);
        String objectJson = jsonObject.toString();
        String content = this.mockMvc.perform(put("/companies/0").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject jobj = new JSONObject(content);
        assertEquals("OOCLL", jobj.get("companyName"));
        assertEquals(1001, jobj.get("employeesNumber"));
    }

    @Test
    public void should_return_status_code_200_when_delete_success() throws Exception {
        this.mockMvc.perform(delete("/companies/0")).andExpect(status().isOk());
    }
}