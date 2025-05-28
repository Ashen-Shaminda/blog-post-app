package com.ashen_dissanayake.blog.services;

import com.ashen_dissanayake.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
   User getUserById(UUID id);
}
