package com.zerobase.zbonlinecourseproject.course.service;

import com.zerobase.zbonlinecourseproject.course.dto.CourseDto;
import com.zerobase.zbonlinecourseproject.course.entity.Course;
import com.zerobase.zbonlinecourseproject.course.entity.TakeCourse;
import com.zerobase.zbonlinecourseproject.course.mapper.CourseMapper;
import com.zerobase.zbonlinecourseproject.course.model.CourseInput;
import com.zerobase.zbonlinecourseproject.course.model.CourseParam;
import com.zerobase.zbonlinecourseproject.course.model.ServiceResult;
import com.zerobase.zbonlinecourseproject.course.model.TakeCourseInput;
import com.zerobase.zbonlinecourseproject.course.repository.CourseRepository;
import com.zerobase.zbonlinecourseproject.course.repository.TakeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TakeCourseRepository takeCourseRepository;
    private final CourseMapper courseMapper;


    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public boolean add(CourseInput parameter) {
        if (!parameter.isValid()) {
            throw new IllegalArgumentException("필수 항목이 입력되지 않았습니다.");
        }

        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());

        Course course = Course.builder()
            .categoryId(parameter.getCategoryId())
            .subject(parameter.getSubject())
            .keyword(parameter.getKeyword())
            .summary(parameter.getSummary())
            .contents(parameter.getContents())
            .price(parameter.getPrice())
            .salePrice(parameter.getSalePrice())
            .saleEndDt(saleEndDt)
            .regDt(LocalDateTime.now())
            .filename(parameter.getFilename())
            .urlFilename(parameter.getUrlFilename())
            .build();
        
        courseRepository.save(course);
        return true;
    }

    @Override
    public boolean set(CourseInput parameter) {
        if (!parameter.isValid()) {
            throw new IllegalArgumentException("필수 항목이 입력되지 않았습니다.");
        }

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());
        if (optionalCourse.isEmpty()) {
            throw new IllegalArgumentException("수정할 강좌 정보가 없습니다.");
        }

        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());
        Course course = optionalCourse.get();
        
        course.setCategoryId(parameter.getCategoryId());
        course.setSubject(parameter.getSubject());
        course.setKeyword(parameter.getKeyword());
        course.setSummary(parameter.getSummary());
        course.setContents(parameter.getContents());
        course.setPrice(parameter.getPrice());
        course.setSalePrice(parameter.getSalePrice());
        course.setSaleEndDt(saleEndDt);
        course.setUdtDt(LocalDateTime.now());
        course.setFilename(parameter.getFilename());
        course.setUrlFilename(parameter.getUrlFilename());

        courseRepository.save(course);
        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter) {

        long totalCount = courseMapper.selectListCount(parameter);

        List<CourseDto> list = courseMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (CourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public CourseDto getById(long id) {
        return courseRepository.findById(id).map(CourseDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    courseRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<CourseDto> frontList(CourseParam parameter) {
        if (parameter == null || parameter.getCategoryId() <= 0) {
            List<Course> courseList = courseRepository.findAll();
            return CourseDto.of(courseList);
        }

        Optional<List<Course>> optionalCourses = courseRepository.findByCategoryId(parameter.getCategoryId());
        return optionalCourses.map(CourseDto::of).orElse(null);
    }

    @Override
    public CourseDto frontDetail(long id) {

        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            return CourseDto.of(optionalCourse.get());
        }
        return null;
    }

    /**
     * 수강신청
     */
    @Override
    public ServiceResult req(TakeCourseInput parameter) {

        ServiceResult result = new ServiceResult();

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getCourseId());
        if (!optionalCourse.isPresent()) {
            result.setResult(false);
            result.setMessage("강좌 정보가 존재하지 않습니다.");
            return result;
        }

        Course course = optionalCourse.get();

        //이미 신청정보가 있는지 확인
        String[] statusList = {TakeCourse.STATUS_REQ, TakeCourse.STATUS_COMPLETE};
        long count = takeCourseRepository.countByCourseIdAndUserIdAndStatusIn(course.getId(), parameter.getUserId(), Arrays.asList(statusList));

        if (count > 0) {
            result.setResult(false);
            result.setMessage("이미 신청한 강좌 정보가 존재합니다.");
            return result;
        }

        TakeCourse takeCourse = TakeCourse.builder()
            .courseId(course.getId())
            .userId(parameter.getUserId())
            .payPrice(course.getSalePrice())
            .regDt(LocalDateTime.now())
            .status(TakeCourse.STATUS_REQ)
            .build();
        takeCourseRepository.save(takeCourse);

        result.setResult(true);
        result.setMessage("");
        return result;
    }

    @Override
    public List<CourseDto> listAll() {

        List<Course> courseList = courseRepository.findAll();

        return CourseDto.of(courseList);
    }

}









