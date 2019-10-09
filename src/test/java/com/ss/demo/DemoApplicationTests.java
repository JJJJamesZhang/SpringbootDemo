package com.ss.demo;

import com.ss.demo.entity.Employee;
import com.ss.demo.entity.User;
import com.ss.demo.repository.EmployeeRepository;
import com.ss.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	UserRepository userRepository;

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

	@Test
	public void testGetMethod(){
		Employee employee = employeeRepository.findByEmpId(1);
		System.out.println(employee);
	}

	@Test
	public void testUserRepository(){
		User user = userRepository.findByUsernameAndPassword("zhangsan","1234567");
		System.out.println(user);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void testUpdate(){
		employeeRepository.updateEmployee("zhaosi",3);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void testDelete(){
		employeeRepository.deleteEmployeeByEmpId(1);
	}
}
