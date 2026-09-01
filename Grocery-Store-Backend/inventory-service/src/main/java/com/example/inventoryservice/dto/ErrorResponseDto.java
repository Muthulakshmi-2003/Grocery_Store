package com.example.inventoryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data  @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Builder
public class ErrorResponseDto {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;
}
