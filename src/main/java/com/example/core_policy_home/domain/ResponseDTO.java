package com.example.core_policy_home.domain;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {

  private String code;
  private String message;
  private T data;

  private static final String SUCCESS = "success";
  private static final String ERROR = "error";

  public static <T> ResponseDTO<T> of(T data) {
    return new ResponseDTO<>(ErrorCodeEnum.TB000.name(), SUCCESS, data);
  }

  public static <T> ResponseEntity<ResponseDTO<T>> ofSuccess(T data, String message) {
    ResponseDTO<T> result = of(data);
    result.setMessage(message);
    return ResponseEntity.ok(result);
  }

  public static <T> ResponseEntity<ResponseDTO<T>> ofSuccess(T data) {
    return ResponseEntity.ok(of(data));
  }

  public static ResponseEntity<ResponseDTO<String>> ofError(String message) {
    return ofError(ErrorCodeEnum.TB001, message);
  }

  public static ResponseEntity<ResponseDTO<String>> ofError(ErrorCodeEnum codeEnum, String message) {
    return ResponseEntity.badRequest().body(new ResponseDTO<>(codeEnum.name(), message, null));
  }

  public static <T> ResponseEntity<ResponseDTO<T>> ofError(ErrorCodeEnum codeEnum, String message, T data) {
    return ResponseEntity.badRequest().body(new ResponseDTO<>(codeEnum.name(), message, data));
  }
}
