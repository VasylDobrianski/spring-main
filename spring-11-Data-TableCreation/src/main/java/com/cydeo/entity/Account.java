package com.cydeo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity
@MappedSuperclass
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;


}
