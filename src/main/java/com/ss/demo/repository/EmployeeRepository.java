package com.ss.demo.repository;


import com.ss.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findByEmpId(Integer empId);

    @Query("update Employee set empName = :name")
    void update();

    void deleteByEmpId(Integer empId);
}
