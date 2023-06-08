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

    @GetMapping({"/list"})
    @ResponseBody
    public ResponseEntity employeeList() {

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.listAll());
    }


    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity create(@RequestBody EmployeeDTO dto) {
        EmployeeDTO savedDTO = employeeService.save(dto);
        if (dto.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);

    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        EmployeeDTO employeeDTO1=employeeService.getEmployeeById(id);
        if (employeeDTO1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }else {
            EmployeeDTO employeeDTO = employeeService.deleteEmployeeById(id);

                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO getdto = employeeService.getEmployeeById(id);
        if (getdto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, employeeDTO);
            if (updatedEmployeeDTO.getEmail()== null|| updatedEmployeeDTO.getName()==null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeDTO);
        }
    }
}
