package com.example.hexagonal.application.port.out.category;

import com.example.hexagonal.domain.category.Category;

public interface LoadCategoryPort {
    public Category loadCategory(Long categoryId);
}
