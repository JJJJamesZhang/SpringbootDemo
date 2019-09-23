package com.ss.demo.service;

import com.ss.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    public static Map<Integer, Employee> map = new HashMap<>();

    public List<Employee> getEmployee(){
        List<Employee> employees = new ArrayList<>();
        map.forEach((k,v) -> {
            employees.add(v);
        });
        return employees;
    }

    public void putEmployee(Employee employee){
        map.put(employee.getId(),employee);
    }

    public void deleteEmployee(Integer id){
        map.remove(id);
    }

    public void updateEmployee(Employee employee){
        map.put(employee.getId(),employee);
    }

    public Employee getEmployeeById(Integer id){
        return map.get(id);
    }
}
