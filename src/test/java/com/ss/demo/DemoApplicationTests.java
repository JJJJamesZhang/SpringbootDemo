package com.ss.demo;

import com.ss.demo.entity.Employee;
import com.ss.demo.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void testRepository() {
		Employee employee = new Employee();
		employee.setEmpName("Andy");
		employee.setAge(30);
		employee.setDepartmentId(1);
		Employee employee1 = employeeRepository.save(employee);
		System.out.println(employee1);
//		List<Employee> employees = employeeRepository.findAll();
//		System.out.println(employees);
	}

}
