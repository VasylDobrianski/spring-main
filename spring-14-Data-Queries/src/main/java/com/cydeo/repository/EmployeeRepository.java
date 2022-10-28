package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    //Display all employees with email address ""
    List<Employee> findByEmail(String email);

    //Display all employees with firstname "" and last name "", also show all employees with an email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstname, String lastname, String email);

    //Display all employees that first name is not ""
    List<Employee> findByFirstNameIsNot(String firstname);

    //Display all employees where last name starts with ""
    List<Employee> findByLastNameStartsWith(String pattern);

    //Display all employee with salary higher than ""
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employee with salary less than ""
    List<Employee> findBySalaryLessThan(Integer salary);

    //Display all employee that have been hired between "" and ""
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employee where salary greater and equal to "" in order
    List<Employee> findBySalaryGreaterThanEqualOrderBySalary(Integer salary);

    //Display top 3 unique employees that is making less than ""
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do no have email address
    List<Employee> findByEmailIsNull();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT employee FROM Employee employee WHERE employee.email='amcnee1@google.es'")
    Employee retrieveEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email='amcnee1@google.es'")
    Integer retrieveEmployeeSalary();


}
