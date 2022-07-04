package com.example.core_policy_home.exception;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;

/**
 * Validate dữ liệu đầu vào
 *
 * @author osfpt_nhatnc
 * @version v1
 * @date 10/15/2020
 */
public class InputInvalidException extends ApplicationException {

  public InputInvalidException(ErrorCodeEnum code, Object... args) {
    super(code, args);
  }
}
