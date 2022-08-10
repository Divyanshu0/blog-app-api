package com.self.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.self.blog.entities.User;
import com.self.blog.exceptions.ResourceNotFoundException;
import com.self.blog.payloads.UserDto;
import com.self.blog.repositories.UserRepo;
import com.self.blog.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userRepo.save(dtoToUser(userDto));
		return userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		user.setName(userDto.getName());
    	user.setId(userDto.getId());
    	user.setAbout(userDto.getAbout());
    	user.setPassword(userDto.getPassword());
    	user.setEmail(userDto.getEmail());
    	userRepo.save(user);
    	return userToDto(user);
    	

	}

	@Override
	public UserDto getUserById(Integer userId) {
	User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
	return userToDto(user);
	}

	@Override
	public List<UserDto> findAllUserDto() {
	   List<User> users = userRepo.findAll();
	   return users.stream().map(this::userToDto).collect(Collectors.toList()); 
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepo.deleteById(userId);

	}
    private User dtoToUser(UserDto userDto) {
    	return modelMapper.map(userDto, User.class);
//    	User user = new User();
//    	user.setName(userDto.getName());
//    	user.setId(userDto.getId());
//    	user.setAbout(userDto.getAbout());
//    	user.setPassword(userDto.getPassword());
//    	user.setEmail(userDto.getEmail());
//		return user;
    	
    }
    private UserDto userToDto(User user) {
//    	UserDto user = new UserDto();
//    	user.setName(userDto.getName());
//    	user.setId(userDto.getId());
//    	user.setAbout(userDto.getAbout());
//    	user.setPassword(userDto.getPassword());
//    	user.setEmail(userDto.getEmail());
//		return user;
    	return modelMapper.map(user, UserDto.class);
	
    }

	@Override
	public void deleteAllUsers() {
		userRepo.deleteAll();
		
	}
}
