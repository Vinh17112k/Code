package com.example.core_policy_home.exception;


import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.locale.Translator;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private final ErrorCodeEnum code;
  private final transient Object[] args;

  public ApplicationException(ErrorCodeEnum code, Object... args) {
    super(Translator.toMessage(code, args));
    this.code = code;
    this.args = args;
  }
}
