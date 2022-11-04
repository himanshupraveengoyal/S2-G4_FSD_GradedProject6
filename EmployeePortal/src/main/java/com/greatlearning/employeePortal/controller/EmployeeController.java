package com.greatlearning.employeePortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeePortal.model.Employee;
import com.greatlearning.employeePortal.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// 3. Now Your application should be able to add employees data in the db if and
	// only if the authenticated user is ADMIN-
	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	// 4. Your application should provide an endpoint to list all the employees
	// stored in the database.
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// 5. Your application should provide endpoint to fetch or get an employee
	// record specifically
	// based on the id of that employee
	@GetMapping("/getEmployeeBasedOnId/{id}")
	public Employee getEmployeeBasedOnId(@PathVariable long id) {
		try {
			return employeeService.getEmployeeBasedOnId(id);
		} catch (Exception e) {
			return null;
		}
	}

	// 6. creating put mapping that updates the employee detail
	@PostMapping("/updateEmployee")
	private Employee update(@RequestBody Employee employee) {
		try {
			Employee e=getEmployeeBasedOnId(employee.getId());
			if(e==null) return null;
			employeeService.update(employee);
			return employee;
		} catch (Exception e) {
			return null;
		}
	}

	// 7.creating a delete mapping that deletes a specified employee
	@DeleteMapping("/deleteEmployee/{id}")
	private String deleteEmployee(@PathVariable("id") Long id) {
		try {
			employeeService.delete(id);
			return "Deleted employee id - " + id;
		} catch (Exception e) {
			return "Employee not found";
		}
	}

}