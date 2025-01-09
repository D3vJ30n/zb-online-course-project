package com.zerobase.zbonlinecourseproject.course.controller;

import com.zerobase.zbonlinecourseproject.common.model.ApiResponse;
import com.zerobase.zbonlinecourseproject.course.dto.CourseDto;
import com.zerobase.zbonlinecourseproject.course.dto.TakeCourseDto;
import com.zerobase.zbonlinecourseproject.course.model.ServiceResult;
import com.zerobase.zbonlinecourseproject.course.model.TakeCourseParam;
import com.zerobase.zbonlinecourseproject.course.service.CourseService;
import com.zerobase.zbonlinecourseproject.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/takecourse")
public class AdminTakeCourseController {

    private final CourseService courseService;
    private final TakeCourseService takeCourseService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> list(TakeCourseParam parameter) {
        try {
            parameter.init();
            List<TakeCourseDto> list = takeCourseService.list(parameter);

            long totalCount = 0;
            if (!CollectionUtils.isEmpty(list)) {
                totalCount = list.get(0).getTotalCount();
            }

            List<CourseDto> courseList = courseService.listAll();

            Map<String, Object> responseData = Map.of(
                "list", list,
                "totalCount", totalCount,
                "courseList", courseList
            );

            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", 
                "수강 신청 목록을 성공적으로 조회했습니다.", responseData));

        } catch (Exception e) {
            throw new IllegalArgumentException("수강 신청 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Void>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            ServiceResult result = takeCourseService.updateStatus(id, status);
            if (!result.isResult()) {
                throw new IllegalArgumentException(result.getMessage());
            }

            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", 
                "수강 상태가 성공적으로 업데이트되었습니다.", null));

        } catch (Exception e) {
            throw new IllegalArgumentException("수강 상태 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}