package com.zerobase.zbonlinecourseproject.admin.repository;

import com.zerobase.zbonlinecourseproject.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<List<Category>> findByUsingYnOrderBySortValueDesc(boolean usingYn);
}