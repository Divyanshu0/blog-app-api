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

import com.self.blog.payloads.UserDto;
import com.self.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto user = userService.createUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public UserDto findById(@PathVariable("id") int id) {
		return userService.getUserById(id);

	}

	@PutMapping("/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
		return userService.updateUser(userDto, id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	@DeleteMapping
	public void deleteAllUsers() {
		userService.deleteAllUsers();
	}

	@GetMapping
	public List<UserDto> findAll() {
		return userService.findAllUserDto();
	}

}
