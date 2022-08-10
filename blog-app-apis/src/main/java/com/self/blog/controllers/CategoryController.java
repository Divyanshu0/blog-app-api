package com.self.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.blog.payloads.CategoryDto;
import com.self.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public CategoryDto findById(@PathVariable("id") int id) {
		return categoryService.getCategoryById(id);

	}

	@PutMapping("/{id}")
	public CategoryDto updateUser(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
		return categoryService.updateUser(categoryDto, id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
	}

	@DeleteMapping
	public void deleteAllUsers() {
		categoryService.deleteAllCategory();
	}

	@GetMapping
	public List<CategoryDto> findAll() {
		return categoryService.findAllCategory();
	}
}
