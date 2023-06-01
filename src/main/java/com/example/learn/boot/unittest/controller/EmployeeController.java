package com.example.learn.boot.unittest.controller;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping({"/", "/list"})
    @ResponseBody
    public ResponseEntity employeeList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.listAll());
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity create(@RequestBody EmployeeDTO dto) {
        EmployeeDTO savedDTO = employeeService.save(dto);
        if(dto ==null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }
}
