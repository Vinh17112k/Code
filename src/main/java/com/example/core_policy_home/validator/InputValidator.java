package com.example.core_policy_home.validator;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.MessageCodeEnum;
import com.example.core_policy_home.exception.InputInvalidException;
import com.example.core_policy_home.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Validate thông tin đầu vào
 *
 * @author HoangTD5
 * @version v1
 * @date 11/6/2020
 */
@Slf4j
@Component
public class InputValidator {

  /**
   * Validate thông tin đầu vào
   *
   * @param object Thông tin đầu vào
   */
  public void validateInput(Object object) {
    if (Boolean.TRUE.equals(DataUtils.isNull(object))) {
      throw new InputInvalidException(ErrorCodeEnum.TB003, MessageCodeEnum.MTB000);
    }
  }

  public void validateInputMapping(Object objOld, Object objNew, String message) {
    if (Boolean.TRUE.equals(DataUtils.isNull(objOld))
        || Boolean.TRUE.equals(DataUtils.isNull(objNew))) {
      throw new InputInvalidException(ErrorCodeEnum.TB003, message);
    }
  }

  public void validateNumeric(String id){
    if(!DataUtils.isNumeric(id)){
      throw new InputInvalidException(ErrorCodeEnum.E02,id);
    }
  }
}
