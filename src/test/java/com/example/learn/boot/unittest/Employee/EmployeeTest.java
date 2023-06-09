package com.example.learn.boot.unittest.Employee;

import com.example.learn.boot.unittest.UnitTestApplicationTests;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeTest extends UnitTestApplicationTests {

    @Test
    public void createEmployeeTest() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Employee");
        dto.setAddress("Kathmandu");
        dto.setEmail("user@user.com");
        dto.setPhone("9876554433");

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Employee"))
                .andExpect(jsonPath("$.address").value("Kathmandu"))
                .andExpect(jsonPath("$.email").value("user@user.com"))
                .andExpect(jsonPath("$.phone").value("9876554433"));
    }
    @Test
    public void createEmployeeTest_badrequest() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setAddress("Kathmandu");
        dto.setEmail("user@user.com");
        dto.setPhone("9876554433");

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void listAll() throws Exception {
        createEmployee("abc", "htd", "abc@gmail.com", "123");
        createEmployee("fish", "water", "fishwater@gmail.com", "11111");


        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("abc"))
                .andExpect(jsonPath("$[0].address").value("htd"))
                .andExpect(jsonPath("$[0].email").value("abc@gmail.com"))
                .andExpect(jsonPath("$[0].phone").value("123"))

                .andExpect(jsonPath("$[1].name").value("fish"))
                .andExpect(jsonPath("$[1].address").value("water"))
                .andExpect(jsonPath("$[1].email").value("fishwater@gmail.com"))
                .andExpect(jsonPath("$[1].phone").value("11111"));
    }
    @Test
    public void listAll_NotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    public void getEmployeeById() throws Exception {
        createEmployee("abc", "htd", "abc@gmail.com", "123");
        createEmployee("fish", "water", "fishwater@gmail.com", "11111");


        mockMvc.perform(MockMvcRequestBuilders.get("/employee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("fish"));


    }
    @Test
    public void getEmployeeById_NotFound() throws Exception {
        createEmployee("abc", "htd", "abc@gmail.com", "123");
        createEmployee("fish", "water", "fishwater@gmail.com", "11111");


        mockMvc.perform(MockMvcRequestBuilders.get("/employee/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteEmployeeById() throws Exception {
        createEmployee("abc", "htd", "abc@gmail.com", "123");

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/del/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteEmployeeById_NotFound() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/del/1")
                .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isNotFound());
    }


@Test
    public void updateEmployeeTest() throws Exception {
        // Create an employee
        createEmployee("abc", "htd", "abc@gmail.com", "123");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"xyz\", \"address\": \"ktm\", \"email\": \"xyz@gmail.com\", \"phone\": \"789\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk());

        // Verify that the employee was updated
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("xyz"))
                .andExpect(jsonPath("$.address").value("ktm"))
                .andExpect(jsonPath("$.email").value("xyz@gmail.com"))
                .andExpect(jsonPath("$.phone").value("789"));
    }
    @Test
    public void updateEmployeeTest_badrequest() throws Exception {
        // Create an employee
        createEmployee("abc", "htd", "abc@gmail.com", "123");
        createEmployee("fish", "water", "fishwater@gmail.com", "11111");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"xyz\", \"address\": \"ktm\", \"email\": \"xyz\", \"phone\": \"789\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/3")
        // Verify that the employee was updated
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEmployeeTest_NotFound() throws Exception {
        // Create an employee


        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"xyz\", \"address\": \"ktm\", \"email\": \"xyz@gmail.com\", \"phone\": \"789\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isNotFound());
    }

}


