package com.zerobase.zbonlinecourseproject.member.service;

import com.zerobase.zbonlinecourseproject.components.MailComponents;
import com.zerobase.zbonlinecourseproject.member.entity.Member;
import com.zerobase.zbonlinecourseproject.member.model.MemberInput;
import com.zerobase.zbonlinecourseproject.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MailComponents mailComponents;

    @Test
    @DisplayName("회원가입 및 인증메일 전송 테스트")
    void registerAndSendMailTest() {
        // Given
        MemberInput parameter = new MemberInput();
        parameter.setUserId("jxxdxmxx2@gmail.com");    // 실제 수신할 이메일 주소
        parameter.setUserName("테스트사용자");
        parameter.setPhone("01012345678");
        parameter.setPassword("password123!");

        // When
        boolean result = memberService.register(parameter);

        // Then
        assertTrue(result);

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        assertTrue(optionalMember.isPresent());

        Member savedMember = optionalMember.get();
        assertEquals(parameter.getUserId(), savedMember.getUserId());
        assertEquals(parameter.getUserName(), savedMember.getUserName());
        assertEquals(parameter.getPhone(), savedMember.getPhone());
        assertNotNull(savedMember.getPassword());
        assertFalse(savedMember.isEmailAuthYn());
        assertNotNull(savedMember.getEmailAuthKey());

        // 이메일 인증 링크 확인을 위한 출력
        System.out.println("=================================");
        System.out.println("회원가입 완료");
        System.out.println("이메일: " + savedMember.getUserId());
        System.out.println("인증키: " + savedMember.getEmailAuthKey());
        System.out.println("=================================");
    }

    @Test
    @DisplayName("이메일 인증 테스트")
    void emailAuthTest() {
        // Given
        String emailAuthKey = "0cf8dc5d-bd4c-4645-8241-1761432297e9";  // 회원가입 테스트에서 발급된 실제 인증키

        // When
        boolean result = memberService.emailAuth(emailAuthKey);

        // Then
        assertTrue(result);
        
        // 인증 상태 확인
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(emailAuthKey);
        assertTrue(optionalMember.isPresent());
        Member member = optionalMember.get();
        assertTrue(member.isEmailAuthYn());
        assertNotNull(member.getEmailAuthDt());
        
        // 인증 완료 상태 출력
        System.out.println("=================================");
        System.out.println("이메일 인증 완료");
        System.out.println("이메일: " + member.getUserId());
        System.out.println("인증 시간: " + member.getEmailAuthDt());
        System.out.println("=================================");
    }
}