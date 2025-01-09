package com.zerobase.zbonlinecourseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZbOnlineCourseProjectApplication {

        /*
    @PostConstruct
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Spring boot application running in UTC timezone :" + new Date());

    }*/

    public static void main(String[] args) {
        SpringApplication.run(ZbOnlineCourseProjectApplication.class, args);
    }

}
