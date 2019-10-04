package com.ss.demo.service;

import com.ss.demo.entity.Employee;
import com.ss.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public int putEmployee(Employee employee){
//        if (employeeRepository.save(employee) != null){
//            return 1;
//        }else {
//            return 0;
//        }
        try {
            employeeRepository.save(employee);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public void deleteEmployee(Integer id){

    }

    public void updateEmployee(Employee employee){

    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findByEmpId(id);
    }
}
