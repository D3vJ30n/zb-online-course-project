package com.zerobase.zbonlinecourseproject.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String categoryName;
    private int sortValue;
    private boolean usingYn;
    
    private int courseCount;
    private LocalDateTime regDt;
    
    // 강좌 수를 증가시키는 메서드
    public void incrementCourseCount() {
        this.courseCount++;
    }
    
    // 강좌 수를 감소시키는 메서드
    public void decrementCourseCount() {
        if (this.courseCount > 0) {
            this.courseCount--;
        }
    }
}
