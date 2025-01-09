package com.zerobase.zbonlinecourseproject.course.model;

import com.zerobase.zbonlinecourseproject.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {
    private long categoryId;
}
