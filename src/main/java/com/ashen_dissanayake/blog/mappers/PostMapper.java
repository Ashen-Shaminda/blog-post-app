package com.ashen_dissanayake.blog.mappers;

import com.ashen_dissanayake.blog.domain.CreatePostRequest;
import com.ashen_dissanayake.blog.domain.UpdatePostRequest;
import com.ashen_dissanayake.blog.domain.dtos.CreatePostRequestDto;
import com.ashen_dissanayake.blog.domain.dtos.PostDto;
import com.ashen_dissanayake.blog.domain.dtos.UpdatePostRequestDto;
import com.ashen_dissanayake.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
   @Mapping(target = "author", source = "author")
   @Mapping(target = "category", source = "category")
   @Mapping(target = "tags", source = "tags")
   PostDto toDto(Post post);

   CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

   UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
