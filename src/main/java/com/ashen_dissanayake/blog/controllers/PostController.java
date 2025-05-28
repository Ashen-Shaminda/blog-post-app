package com.ashen_dissanayake.blog.controllers;

import com.ashen_dissanayake.blog.domain.CreatePostRequest;
import com.ashen_dissanayake.blog.domain.UpdatePostRequest;
import com.ashen_dissanayake.blog.domain.dtos.CreatePostRequestDto;
import com.ashen_dissanayake.blog.domain.dtos.PostDto;
import com.ashen_dissanayake.blog.domain.dtos.UpdatePostRequestDto;
import com.ashen_dissanayake.blog.domain.entities.Post;
import com.ashen_dissanayake.blog.domain.entities.User;
import com.ashen_dissanayake.blog.mappers.PostMapper;
import com.ashen_dissanayake.blog.services.PostService;
import com.ashen_dissanayake.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
   private final PostService postService;
   private final PostMapper postMapper;
   private final UserService userService;

   @GetMapping
   public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID categoryId, @RequestParam(required = false) UUID tagId) {
      List<Post> posts = postService.getAllPosts(categoryId, tagId);
      List<PostDto> postDtos = posts
              .stream()
              .map(postMapper::toDto).toList();

      return new ResponseEntity<>(postDtos, HttpStatus.OK);
   }

   @GetMapping(path = "/drafts")
   public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
      User loggedInUser = userService.getUserById(userId);
      List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
      List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();

      return new ResponseEntity<>(postDtos, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto, @RequestAttribute UUID userId) {
      User loggedInUser = userService.getUserById(userId);
      CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
      Post createdPost = postService.createPost(loggedInUser, createPostRequest);
      PostDto createdPostDto = postMapper.toDto(createdPost);

      return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
   }

   @PutMapping(path = "/{id}")
   public ResponseEntity<PostDto> updatePost(@PathVariable UUID id, @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {
      UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
      Post updatedPost = postService.updatePost(id, updatePostRequest);
      PostDto updatedPostDto = postMapper.toDto(updatedPost);

      return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
   }

   @GetMapping(path = "/{id}")
   public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
      Post post = postService.getPost(id);
      PostDto postDto = postMapper.toDto(post);

      return ResponseEntity.ok(postDto);
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
      postService.deletePost(id);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
