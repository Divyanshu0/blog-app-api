package com.self.blog.services;

import java.util.List;

import com.self.blog.payloads.PostDto;
import com.self.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer cateogryId);
    void deletePost(Integer postId);
    List<PostDto> getAllPost();
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
	void deleteAllPosts();
	PostResponse findAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	PostDto updatePost(PostDto postDto, Integer postId);
	List<PostDto> searchPost(String keyword);
	
    
}
