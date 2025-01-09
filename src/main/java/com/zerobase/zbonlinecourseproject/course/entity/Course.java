package com.zerobase.zbonlinecourseproject.course.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String subject;
    private String keyword;
    private String summary;

    @Lob
    private String contents;

    private long price;
    private long salePrice;
    private LocalDate saleEndDt;

    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private String filename;
    private String urlFilename;
}
