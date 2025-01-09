package com.zerobase.zbonlinecourseproject.configuration;

import com.zerobase.zbonlinecourseproject.ZbOnlineCourseProjectApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZbOnlineCourseProjectApplication.class);
    }

}
