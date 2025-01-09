package com.zerobase.zbonlinecourseproject.admin.dto;

import com.zerobase.zbonlinecourseproject.admin.entity.Category;
import com.zerobase.zbonlinecourseproject.admin.model.CategoryInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    Long id;
    String categoryName;
    int sortValue;
    boolean usingYn;
    int courseCount;
    LocalDateTime regDt;

    public static List<CategoryDto> of(List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryList = new ArrayList<>();
            for (Category x : categories) {
                categoryList.add(of(x));
            }
            return categoryList;
        }

        return null;
    }

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
            .id(category.getId())
            .categoryName(category.getCategoryName())
            .sortValue(category.getSortValue())
            .usingYn(category.isUsingYn())
            .courseCount(category.getCourseCount())
            .regDt(category.getRegDt())
            .build();
    }

    public static CategoryDto from(CategoryInput parameter) {
        return CategoryDto.builder()
            .id(parameter.getId())
            .categoryName(parameter.getCategoryName())
            .sortValue(parameter.getSortValue())
            .usingYn(parameter.isUsingYn())
            .courseCount(parameter.getCourseCount())
            .regDt(parameter.getRegDt())
            .build();
    }

}
