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

}
