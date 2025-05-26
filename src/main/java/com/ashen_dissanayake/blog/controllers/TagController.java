package com.ashen_dissanayake.blog.controllers;

import com.ashen_dissanayake.blog.domain.dtos.CreateTagsRequest;
import com.ashen_dissanayake.blog.domain.dtos.TagResponse;
import com.ashen_dissanayake.blog.domain.entities.Tag;
import com.ashen_dissanayake.blog.mappers.TagMapper;
import com.ashen_dissanayake.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
   private final TagService tagService;
   private final TagMapper tagMapper;

   @GetMapping
   public ResponseEntity<List<TagResponse>> getAllTags() {
      List<Tag> tags = tagService.getAllTags();
      List<TagResponse> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();

      return new ResponseEntity<>(tagResponses, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
      List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
      List<TagResponse> createdTagResponse = savedTags.stream().map(tagMapper::toTagResponse).toList();

      return new ResponseEntity<>(createdTagResponse, HttpStatus.CREATED);
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
      tagService.deleteTag(id);
      
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
