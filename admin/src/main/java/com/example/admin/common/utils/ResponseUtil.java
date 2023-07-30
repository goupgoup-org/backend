package com.example.admin.common.utils;

import com.goupgoup_backend.common.dto.ResponseDto;
import com.goupgoup_backend.common.dto.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUtil {
    public static <T> ResponseDto<T> SUCCESS (String message, T data) {
        return new ResponseDto(ResponseStatus.SUCCESS, message, data);
    }

    public static <T>ResponseDto<T> FAILURE (String message, T data) {
        return new ResponseDto(ResponseStatus.FAILURE, message, data);
    }

    public static <T>ResponseDto<T> ERROR (String message, T data) {
        return new ResponseDto(ResponseStatus.ERROR, message, data);
    }

}
