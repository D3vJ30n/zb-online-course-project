package com.zerobase.zbonlinecourseproject.course.model;

import lombok.Data;

@Data
public class TakeCourseInput {

    long courseId;
    String userId;


    long takeCourseId;//take_course.id
}
