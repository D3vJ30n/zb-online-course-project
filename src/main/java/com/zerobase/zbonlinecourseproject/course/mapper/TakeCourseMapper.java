package com.zerobase.zbonlinecourseproject.course.mapper;

import com.zerobase.zbonlinecourseproject.course.dto.TakeCourseDto;
import com.zerobase.zbonlinecourseproject.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {

    long selectListCount(TakeCourseParam parameter);

    List<TakeCourseDto> selectList(TakeCourseParam parameter);

    List<TakeCourseDto> selectListMyCourse(TakeCourseParam parameter);
}
