package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.EmployeeService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
    @PostMapping
    public Employee saveData(@RequestBody Employee employee){
        log.info("employee = {}",employee);
        return employeeService.saveData(employee);
    }

    @PutMapping("{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee){
        return employeeService.update(id,employee);
    }

    @GetMapping("id/{id}")
    public Employee getById(@PathVariable int id){
        return employeeService.getById(id);
    }

    @GetMapping("name/{name}")
    public Employee getByName(@PathVariable String name){
        return employeeService.getByName(name);
    }

    @DeleteMapping("{id}")
    public Employee deleteById(@PathVariable int id){
        return employeeService.deleteById(id);
    }
}
