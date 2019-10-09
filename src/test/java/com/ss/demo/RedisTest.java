package com.ss.demo;

import com.ss.demo.entity.Employee;
import com.ss.demo.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisService redisService;

    @Test
    public void testRedis(){
//        redisService.save("id:1","Mike");
//        System.out.println(redisService.get("id:1"));
        Employee employee = new Employee();
        employee.setEmpId(2);
        employee.setEmpName("lisi");
        employee.setAge(30);
        redisService.save("1",new Integer(1));

//        Employee employee1 =(Employee) redisService.get(employee.getEmpId());
//        System.out.println(employee1);
    }




}
