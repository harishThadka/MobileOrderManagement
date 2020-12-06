package com.example.MOM.Repository;

import com.example.MOM.Models.*;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}