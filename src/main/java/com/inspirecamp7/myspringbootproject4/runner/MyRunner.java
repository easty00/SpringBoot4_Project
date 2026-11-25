package com.inspirecamp7.myspringbootproject4.runner;

import com.inspirecamp7.myspringbootproject4.config.CustomVO;
import com.inspirecamp7.myspringbootproject4.properties.MyBootProperties;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String myBootName;

    @Value("${myboot.age}")
    int mybootAge;

    @Autowired
    Environment environment;

    @Autowired
    MyBootProperties properties;

    @Autowired
    CustomVO customVO;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("CustomerVO = " + customVO);

        System.out.println("환경변수 myboot.name = " + myBootName);
        System.out.println("환경변수 myboot.age = " + mybootAge);
        System.out.println("환경변수 myboot.fullName = " + environment.getProperty("myboot.fullName"));

        System.out.println("MyBootProperties getName = " + properties.getFullName());
        System.out.println("MyBootProperties getAge = " + properties.getAge());

        System.out.println("server.port program argument 확인 " + args.containsOption("server.port"));
        System.out.println("myboot.name program argument 확인 " + args.containsOption("myboot.name"));

        //환경변수 목록을 확인하기
        Set<String> optionNames = args.getOptionNames();
        for (String name: optionNames) {
            System.out.println("name = " + name);
        }

        // 1단계 : Anonymous Inner class (익명 내부 클래스)
        optionNames.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println("Inner Class인 경우 name = " + name);
            }
        });

        //Iterable 의 default void forEach(consumer<? super T> action)
        //함수형 인터페이스Consumer의 추상메서드 void accept(T t)
        //Consumer 인터페이스의 accept() 메서드를 람다식으로 표현한 것
        // 2단계: 람다표현식
        // 한개는 람다표현으로 간단하게, 이것저것 여러가지면 다른걸로?
        optionNames.forEach(optionName -> System.out.println(optionName));

        // 3단계 : Method Reference
        // Consumer 인터페이스의 accept() 메서드를 Method Reference 로 표현
        optionNames.forEach(System.out::println);
    }
}
