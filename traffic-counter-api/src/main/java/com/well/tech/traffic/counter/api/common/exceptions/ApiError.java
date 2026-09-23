package com.well.tech.traffic.counter.api.common.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Standard API error response")
public record ApiError(

        @Schema(
                description = "HTTP status code",
                example = "404"
        )
        int status,

        @Schema(
                description = "HTTP error reason",
                example = "Not Found"
        )
        String error,

        @Schema(
                description = "Detailed error message",
                example = "Traffic device not found with id: 550e8400-e29b-41d4-a716-446655440000"
        )
        String message,

        @Schema(
                description = "Request path where the error occurred",
                example = "/api/v1/traffic-devices/550e8400-e29b-41d4-a716-446655440000"
        )
        String path,

        @Schema(
                description = "Date and time when the error occurred",
                example = "2026-09-23T10:30:00Z"
        )
        Instant timestamp
) {
}