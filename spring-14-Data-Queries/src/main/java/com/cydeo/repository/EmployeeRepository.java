package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    //Not Equal
    @Query("select e from Employee e where e.salary <> ?1")
    List<Employee> retrieveEmployeeSalaryNotEqual(int salary);

    //Like /Contains /Startswith /Endswith
    @Query("select e from Employee e where e.firstName like ?1")
    List<Employee> retrieveEmployeeFirstNameLike(String pattern);

    //Less Than
    @Query("select e.firstName from Employee e where e.salary < ?1 ")
    List<Employee> retrieveEmployeeSalaryLessThan(int salary);

    //Greater Than
    @Query("select e from Employee e where e.salary > ?1")
    List<Employee> retrieveEmployeeSalaryGreaterThan(int salary);

    //Between
    @Query("select e.firstName from Employee e where e.salary between ?1 and ?2")
    List<String> retrieveEmployeeFirstNameSalaryBetween(int salary1, int salary2);

    //Before
    @Query("select e from Employee e where e.hireDate > ?1")
    List<Employee> retrieveEmployeeHireDateBefore(LocalDate date);

    //Null
    @Query("select e from Employee e where e.email is null")
    List<Employee> retrieveEmployeeIsNull();

    //Not Null
    @Query("select e from Employee e where e.email is not null")
    List<Employee> retrieveEmployeeIsNotNull();

    //Sorting in Asc Order
    @Query("select e from Employee e order by e.salary")
    List<Employee> retrieveEmployeeSalaryOrderAsc();

    //Sorting in Desc Order
    @Query("select e from Employee e order by e.salary desc")
    List<Employee> retrieveEmployeeSalaryOrderDesc();

    //------------------------------------------------------------------------------------------------------------------
    //NATIVE QUERY
    @Query(value = "select * from employees where salary = ?1",nativeQuery = true)
    List<Employee> retrieveEmployeeDetailBySalary(int salary);

    //Named Parameter
    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> retrieveEmployeeSalary(@Param("salary") int salary);


}
