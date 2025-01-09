package com.zerobase.zbonlinecourseproject.admin.mapper;

import com.zerobase.zbonlinecourseproject.admin.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> select(CategoryDto parameter);

}
