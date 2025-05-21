package com.ashen_dissanayake.blog.handler;

import lombok.Builder;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
