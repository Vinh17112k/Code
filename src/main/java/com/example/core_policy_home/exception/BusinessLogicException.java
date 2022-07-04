package com.example.core_policy_home.exception;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;

public class BusinessLogicException extends ApplicationException {

  public BusinessLogicException(ErrorCodeEnum code, Object... args) {
    super(code, args);
  }
}
