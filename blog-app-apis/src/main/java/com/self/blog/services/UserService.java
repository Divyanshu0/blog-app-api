package com.self.blog.services;

import java.util.List;

import com.self.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer id);

	UserDto getUserById(Integer userId);

	List<UserDto> findAllUserDto();

	void deleteUser(Integer userId);
	
	void deleteAllUsers();
}
