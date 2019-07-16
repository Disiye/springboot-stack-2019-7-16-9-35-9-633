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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {

    }

    @Test
    public void return_all_employee_when_get_employee() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/employees")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("ooclNo1", jsonArray.getJSONObject(0).getString("employeeName"));
        assertEquals(20, jsonArray.getJSONObject(0).getInt("employeeAge"));
        assertEquals("F", jsonArray.getJSONObject(0).getString("employeeGender"));
    }

    @Test
    public void return_employee_when_get_employee_0() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/employees/0")).andExpect(status().isOk()).andReturn();
        JSONObject jsonArray = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(0, jsonArray.getInt("employeeId"));
        assertEquals("ooclNo1", jsonArray.getString("employeeName"));
        assertEquals(20, jsonArray.getInt("employeeAge"));
    }

    @Test
    public void return_status_is_created_when_put_new_employee() throws Exception {
        Employee employee = new Employee(1, "ooclNo3", 20, "M");
        JSONObject jsonObject = new JSONObject(employee);
        String objectJson = jsonObject.toString();
        this.mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());
    }

    @Test
    public void return_revised_employee_when_put_employee_info() throws Exception {
        Employee employee = new Employee(2, "ooclNo3", 22, "M");
        JSONObject jsonObject = new JSONObject(employee);
        String objectJson = jsonObject.toString();
        String content = this.mockMvc.perform(put("/employees/0").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject jobj = new JSONObject(content);
        assertEquals("ooclNo3", jobj.get("employeeName"));
        assertEquals("M", jobj.get("employeeGender"));
    }

    @Test
    public void should_return_status_code_200_when_delete_success() throws Exception {
        this.mockMvc.perform(delete("/employees/0")).andExpect(status().isOk());
    }

}