package com.zerobase.zbonlinecourseproject.course.controller;

import com.zerobase.zbonlinecourseproject.admin.dto.CategoryDto;
import com.zerobase.zbonlinecourseproject.admin.service.CategoryService;
import com.zerobase.zbonlinecourseproject.common.model.ApiResponse;
import com.zerobase.zbonlinecourseproject.course.dto.CourseDto;
import com.zerobase.zbonlinecourseproject.course.model.CourseParam;
import com.zerobase.zbonlinecourseproject.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCourseList(CourseParam parameter) {
        try {
            List<CourseDto> courseList = courseService.frontList(parameter);
            List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());

            int courseTotalCount = categoryList != null ?
                categoryList.stream()
                    .mapToInt(CategoryDto::getCourseCount)
                    .sum() : 0;

            Map<String, Object> responseData = Map.of(
                "courseList", courseList,
                "categoryList", categoryList,
                "courseTotalCount", courseTotalCount
            );

            return ResponseEntity.ok(new ApiResponse<>("SUCCESS",
                "강좌 목록을 성공적으로 조회했습니다.", responseData));

        } catch (Exception e) {
            throw new IllegalArgumentException("강좌 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseDetail(@PathVariable Long id) {
        try {
            CourseDto detail = courseService.frontDetail(id);
            if (detail == null) {
                throw new IllegalArgumentException("해당 강좌를 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(new ApiResponse<>("SUCCESS",
                "강좌 상세 정보를 성공적으로 조회했습니다.", detail));

        } catch (Exception e) {
            throw new IllegalArgumentException("강좌 상세 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}