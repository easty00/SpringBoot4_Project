package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
        customer.setCustomerId("B004");
        customer.setCustomerName("스프링B");
        //When  (실행단계)
        Customer addedCustomer = customerRepository.save(customer);
        //Then  (검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("B004");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링B");
    }

    //2. Customer 조회
    @Test
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B004");
        //Optional이 담고 있는 Customer가 값을 가지고 있으면?
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getCustomerId()).isEqualTo("B004");
        }
        // 함수형 인터페이스 Customer 의 추상메서드 void accept(T t)
        // 컨수머에 담겨진 T가 바로 넘어옴
        optionalCustomer.ifPresent(customer -> System.out.println(customer.getCustomerName()));
    }

}