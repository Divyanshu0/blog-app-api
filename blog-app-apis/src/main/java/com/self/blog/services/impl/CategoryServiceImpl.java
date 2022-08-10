package com.self.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.self.blog.entities.Category;
import com.self.blog.entities.User;
import com.self.blog.exceptions.ResourceNotFoundException;
import com.self.blog.payloads.CategoryDto;
import com.self.blog.repositories.CategoryRepo;
import com.self.blog.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
    @Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryRepo.save(dtoToUser(categoryDto));
		return categoryToDto(category);
	}

	@Override
	public CategoryDto updateUser(CategoryDto categoryDto, Integer id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return categoryToDto(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		return categoryToDto(categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)));
	}

	@Override
	public List<CategoryDto> findAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map(this::categoryToDto).collect(Collectors.toList());

	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
	}

	@Override
	public void deleteAllCategory() {
		categoryRepo.deleteAll();
	}

	private Category dtoToUser(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}

	private CategoryDto categoryToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}
}
