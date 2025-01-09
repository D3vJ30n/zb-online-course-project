package com.zerobase.zbonlinecourseproject.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseInput {
    
    private Long id;
    private Long categoryId;
    
    private String subject;
    private String keyword;
    private String summary;
    private String contents;
    
    private long price;
    private long salePrice;
    private String saleEndDtText;
    
    private String filename;
    private String urlFilename;
    
    // 추가 검증 메서드
    public boolean isValid() {
        return (categoryId != null && 
                subject != null && !subject.trim().isEmpty() &&
                summary != null && !summary.trim().isEmpty());
    }
}
