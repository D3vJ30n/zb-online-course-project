package com.zerobase.zbonlinecourseproject.components;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
class MailComponentsTest {

    @Autowired
    private MailComponents mailComponents;

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMailTest() {
        // Given
        String email = "test@example.com";
        String subject = "테스트 메일";
        String text = "<p>테스트 메일 내용입니다.</p>";

        // When & Then
        assertDoesNotThrow(() -> {
            mailComponents.sendMail(email, subject, text);
            System.out.println("메일 전송 완료: " + email);
        });
    }
} 