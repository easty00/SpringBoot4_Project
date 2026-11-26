package com.inspirecamp7.myspringbootproject4.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp      // 타임 뿐 아니라 스탬프까지 같이 넣어주어야 함.
    LocalDateTime createdAt = LocalDateTime.now();

//    private String customerAddress;
}