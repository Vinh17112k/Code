package com.example.core_policy_home.validator;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.MessageCodeEnum;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.model.MappingProcessService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Validate process
 *
 * @author vinhtq
 * @version v1
 * @date 06/21/2022
 */
@Slf4j
@Component
public class MappingProcessServiceValidator {
  public void checkExsitsOptional(Optional<MappingProcessService> mappingProcessService) {
    if (Boolean.FALSE.equals(mappingProcessService.isPresent())) {
      throw new BusinessLogicException(ErrorCodeEnum.TB048, MessageCodeEnum.MTB044);
    }
  }
}
