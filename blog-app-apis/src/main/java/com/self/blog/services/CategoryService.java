package com.self.blog.services;

import java.util.List;

import com.self.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto category);

	CategoryDto updateUser(CategoryDto category, Integer id);

	CategoryDto getCategoryById(Integer categoryId);

	List<CategoryDto> findAllCategory();

	void deleteCategory(Integer categoryId);
	
	void deleteAllCategory();

}
