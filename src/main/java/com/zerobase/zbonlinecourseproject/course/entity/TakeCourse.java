package com.zerobase.zbonlinecourseproject.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TakeCourse implements TakeCourseCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;
    private String userId;

    private Long payPrice;    // 결제금액
    private String status;    // 상태(수강신청, 결재완료, 수강취소)

    private LocalDateTime regDt;    // 신청일

    // 수강 신청 상태
    public static final String STATUS_REQ = "REQ";    // 수강신청
    public static final String STATUS_COMPLETE = "COMPLETE";    // 결제완료
    public static final String STATUS_CANCEL = "CANCEL";    // 수강취소
}
