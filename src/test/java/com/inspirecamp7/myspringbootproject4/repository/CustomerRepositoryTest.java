package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testInsert() {
        //Given-When-Then
        //Given (준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("B001");
        customer.setCustomerName("스프링");
        //When  (실행단계)
        Customer addedCustomer = customerRepository.save(customer);
        //Then  (검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getId()).isEqualTo("B001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링B");
    }

}