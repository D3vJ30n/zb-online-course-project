package com.zerobase.zbonlinecourseproject.admin.controller;

import com.zerobase.zbonlinecourseproject.admin.dto.CategoryDto;
import com.zerobase.zbonlinecourseproject.admin.model.CategoryInput;
import com.zerobase.zbonlinecourseproject.admin.model.MemberParam;
import com.zerobase.zbonlinecourseproject.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do") // 카테고리 목록
    public String list(Model model, MemberParam parameter) {

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }


    @PostMapping("/admin/category/add.do") // 카테고리 추가
    public String add(Model model, CategoryInput parameter) {

        boolean result = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do") // 카테고리 삭제
    public String del(Model model, CategoryInput parameter) {

        boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/update.do") // 카테고리 수정
    public String update(Model model, CategoryInput parameter) {
        boolean result = categoryService.update(parameter);
        return "redirect:/admin/category/list.do";
    }

}
