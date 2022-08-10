package com.self.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.self.blog.services.CategoryService;
import com.self.blog.services.PostService;
import com.self.blog.services.UserService;
import com.self.blog.services.impl.CategoryServiceImpl;
import com.self.blog.services.impl.PostServiceImpl;
import com.self.blog.services.impl.UserServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	CategoryService categoryService() {
		return new CategoryServiceImpl();
	}

	@Bean
	PostService postService() {
		return new PostServiceImpl();
	}
}
