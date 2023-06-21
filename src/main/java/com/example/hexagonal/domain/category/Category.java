package com.example.hexagonal.domain.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    private Long categoryId;

    private Long parentCategoryId;

    private String categoryName;

    public static Category withId(Long categoryId, Long parentCategoryId, String categoryName) {
        return new Category(categoryId, parentCategoryId, categoryName);
    }
}
