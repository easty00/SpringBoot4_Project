package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    // 등록한 데이터를 반드시 확인하고 싶으니 롤백처리를 하지 말라고 요청
    @Rollback(value = false)
    void testInsert() {
        //Given-When-Then
        //Given (준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("AB001");
        customer.setCustomerName("스프링AB");
        //When  (실행단계)
        Customer addedCustomer = customerRepository.save(customer);
        //Then  (검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("AB001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링AB");
    }

    //Customer 수정
    @Test
    void testUpdate() {

    }

    //2. Customer 조회 (값이 일치하는 Customer 존재하는 경우)
    @Test
    @Transactional(readOnly = true) //오직 조회만 시킬 때 트랜잭션을 묶어주면 된다
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

    // 3. Customer 조회 (값이 일치하는 Customer 존재하지 않는 경우)
    @Test
    void testReadNotFound() {
        //Optional orElseGet(Supplier supplier)
        //함수형 인터페이스 Supplier 추상메서드 T get()

        Customer existCustomer = customerRepository.findByCustomerId("B004") //Optional<Customer>
                .orElseGet(() -> new Customer());

        // Customer가 값을 가지는 경우 성공. 데이터가 없으면 실패함
        assertThat(existCustomer).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001") //Optional<Customer>
                .orElseGet(() -> new Customer());

        // Customer가 값이 없는 경우 성공
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }

    @Test
    void testReadNotFoundException() {
        //Optional orElseThrow(Supplier<? extend X> supplier)
        // <X extends Throwable>
        // 함수형 인터페이스 Supplier 추상메서드 X get()
        Customer existCustomer = customerRepository.findByCustomerId("B004")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(existCustomer.getCustomerId()).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }


}