package com.zerobase.zbonlinecourseproject.admin.mapper;

import com.zerobase.zbonlinecourseproject.admin.dto.MemberDto;
import com.zerobase.zbonlinecourseproject.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);

    List<MemberDto> selectList(MemberParam parameter);

}
