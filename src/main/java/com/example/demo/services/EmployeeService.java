package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    public Employee saveData(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getById(int id){
        return employeeRepository.findById(id).get();
    }

    public Employee getByName(String name){
        return employeeRepository.findByNameEquals(name);
    }

    public Employee update(int id, Employee employee){
        Employee emp = getById(id);
        emp.setId(id);
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        return employeeRepository.save(emp);
    }

    public Employee deleteById(int id){
        Employee emp = getById(id);
        employeeRepository.deleteById(id);
        return emp;
    }
}
