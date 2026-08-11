package com.SaralSewa.SaralSewa.shared.core.dto.response;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ServerResponseCodeConstant;
import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> extends ModelBase {

    private HttpStatus httpStatus;
    private String message;
    private int code;
    private T data;
    private boolean isAsyncRequest;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .code(ServerResponseCodeConstant.SUCCESS)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return ApiResponse.<T>builder()
                .code(ServerResponseCodeConstant.SUCCESS)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
