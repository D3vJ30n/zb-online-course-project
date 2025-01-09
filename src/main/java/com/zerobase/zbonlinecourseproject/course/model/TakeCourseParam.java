package com.zerobase.zbonlinecourseproject.course.model;

import com.zerobase.zbonlinecourseproject.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {

    long id;
    String status;

    String userId;


    long searchCourseId;
}
