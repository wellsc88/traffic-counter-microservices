package com.well.tech.traffic.counter.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "Generic paginated response")
public record PageResponse<T>(

        @Schema(
                description = "Elements returned on the current page"
        )
        List<T> content,

        @Schema(
                description = "Current page number (zero-based)",
                example = "0"
        )
        int page,

        @Schema(
                description = "Number of elements requested per page",
                example = "10"
        )
        int size,

        @Schema(
                description = "Total number of elements matching the request",
                example = "25"
        )
        long totalElements,

        @Schema(
                description = "Total number of pages",
                example = "3"
        )
        int totalPages
) {

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}