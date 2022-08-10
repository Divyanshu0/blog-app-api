package com.self.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	@Size(max = 10, min = 3, message = "Not a valid password !")
	private String password;
	@NotBlank
	private String about;

}
