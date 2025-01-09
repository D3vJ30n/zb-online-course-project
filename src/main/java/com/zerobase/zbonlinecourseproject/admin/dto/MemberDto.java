package com.zerobase.zbonlinecourseproject.admin.dto;

import com.zerobase.zbonlinecourseproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {

    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;


    //추가컬럼
    long totalCount;
    long seq;


    public static MemberDto of(Member member) {

        return MemberDto.builder()
            .userId(member.getUserId())
            .userName(member.getUserName())
            .phone(member.getPhone())
            //.password(member.getPassword())
            .regDt(member.getRegDt())
            .emailAuthYn(member.isEmailAuthYn())
            .emailAuthDt(member.getEmailAuthDt())
            .emailAuthKey(member.getEmailAuthKey())
            .resetPasswordKey(member.getResetPasswordKey())
            .resetPasswordLimitDt(member.getResetPasswordLimitDt())
            .adminYn(member.isAdminYn())
            .userStatus(member.getUserStatus())
            .build();
    }


    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public String getUdtDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return udtDt != null ? udtDt.format(formatter) : "";

    }

}