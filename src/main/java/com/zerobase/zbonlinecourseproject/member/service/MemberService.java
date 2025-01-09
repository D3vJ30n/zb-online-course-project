package com.zerobase.zbonlinecourseproject.member.service;

import com.zerobase.zbonlinecourseproject.admin.dto.MemberDto;
import com.zerobase.zbonlinecourseproject.admin.model.MemberParam;
import com.zerobase.zbonlinecourseproject.course.model.ServiceResult;
import com.zerobase.zbonlinecourseproject.member.model.MemberInput;
import com.zerobase.zbonlinecourseproject.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
    boolean sendResetPassword(ResetPasswordInput parameter);


    /**
     * 회원 목록 리턴(관리자에서만 사용 가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String userId, String password);


    ServiceResult updateMember(MemberInput parameter);

    ServiceResult updateMemberPassword(MemberInput parameter);

    ServiceResult withdraw(String userId, String password);
}
