package com.zerobase.zbonlinecourseproject.course.mapper;

import com.zerobase.zbonlinecourseproject.course.dto.CourseDto;
import com.zerobase.zbonlinecourseproject.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    long selectListCount(CourseParam parameter);

    List<CourseDto> selectList(CourseParam parameter);

}
