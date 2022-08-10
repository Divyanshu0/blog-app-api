package com.self.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.self.blog.entities.Category;
import com.self.blog.entities.Post;
import com.self.blog.entities.User;
import com.self.blog.exceptions.ResourceNotFoundException;
import com.self.blog.payloads.PostDto;
import com.self.blog.payloads.PostResponse;
import com.self.blog.repositories.CategoryRepo;
import com.self.blog.repositories.PostRepo;
import com.self.blog.repositories.UserRepo;
import com.self.blog.services.PostService;

public class PostServiceImpl implements PostService {

	@Autowired
	private  PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setCategory(category);
		post.setAddedDate(new Date());
		post.setUser(user);
		return modelMapper.map(postRepo.save(post),PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		postRepo.deleteById(post.getPostId());
		
	}

	@Override
	public List<PostDto> getAllPost() {
		 return postRepo.findAll().stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
	    List<Post> posts = postRepo.findByUser(user);
	    return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
	    List<Post> categories = postRepo.findByCategory(category);
	    return categories.stream().map(category1 -> modelMapper.map(category1, PostDto.class)).toList();
	}

	@Override
	public void deleteAllPosts() {
		postRepo.deleteAll();
		
	}

	@Override
	public PostResponse findAllPosts(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = sort.by(sortBy).ascending();
		}
		else {
			sort = sort.by(sortBy).descending();
		}
		
		PageRequest page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> data = postRepo.findAll(page);
		List<Post> postDtos = data.toList();
		
		List<PostDto> listPostDto =  postDtos.stream().map((post)-> modelMapper.map(post, PostDto.class)).toList();
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContents(listPostDto);
	    postResponse.setPageNumber(data.getNumber());
	    postResponse.setLastPage(data.isLast());
	    postResponse.setTotalElements(data.getTotalElements());
	    postResponse.setTotalPages(data.getTotalPages());
	    return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).toList();
		
	}

}
