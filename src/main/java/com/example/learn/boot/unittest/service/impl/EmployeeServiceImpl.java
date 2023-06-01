package com.example.learn.boot.unittest.service.impl;

import com.example.learn.boot.unittest.converter.EmployeeConverter;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.repository.EmployeeRepository;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeConverter employeeConverter;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> listAll() {
        return employeeConverter.convertToDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        try{
            EmployeeDTO savedDto =  employeeConverter.convertToDto(
                    employeeRepository.saveAndFlush(employeeConverter.convertToEntity(dto)));
            return savedDto;
        }catch (Exception e){
            return null;
        }
    }
}
