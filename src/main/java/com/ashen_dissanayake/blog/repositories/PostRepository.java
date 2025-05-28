package com.ashen_dissanayake.blog.repositories;

import com.ashen_dissanayake.blog.domain.PostStatus;
import com.ashen_dissanayake.blog.domain.entities.Category;
import com.ashen_dissanayake.blog.domain.entities.Post;
import com.ashen_dissanayake.blog.domain.entities.Tag;
import com.ashen_dissanayake.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
   List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag t);

   List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

   List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);

   List<Post> findAllByStatus(PostStatus status);

   List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
