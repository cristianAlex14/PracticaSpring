package com.average.school.average.utils;

import com.average.school.average.dto.ResponseDTO;

public class ResponseUtil {

    public static <T> ResponseDTO<T> success(T data, String message) {
        return ResponseDTO.<T>builder()
                .code(1)
                .message(message)
                .title("Éxito")
                .data(data)
                .build();
    }

    public static ResponseDTO<Void> error(String title, String message) {
        return ResponseDTO.<Void>builder()
                .code(0)
                .message(message)
                .title(title)
                .data(null)
                .build();
    }
}