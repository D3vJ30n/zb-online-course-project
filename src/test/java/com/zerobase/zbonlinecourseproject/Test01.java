package com.zerobase.zbonlinecourseproject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Test01 {

    @Test
    void TEST_01() {

        String value = "2025-01-09";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(value, formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();


    }

}
