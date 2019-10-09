package com.ss.demo.repository;


import com.ss.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findByEmpId(Integer empId);

    //, departmentId= :id
    @Query(value = "update Employee set empName = :name where empId = :id")
    @Modifying
    void updateEmployee(@Param("name") String name, @Param("id") Integer id);

    void deleteEmployeeByEmpId(Integer id);
}
