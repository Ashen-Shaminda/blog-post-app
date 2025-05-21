package com.ashen_dissanayake.blog.handler;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
