package com.self.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private Integer id;
	private String title;
	private String content;
	private Date addedDate;
	private String imageName;
	private CategoryDto category;
	private UserDto user;

}
