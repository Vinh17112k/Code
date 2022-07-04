package com.example.core_policy_home.validator;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.exception.InputInvalidException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validate Customer Fee
 *
 * @author FPT_KhanhPN5
 * @version v1
 * @date 1/15/2021
 */
@Slf4j
@Component
public class CommonValidator {

  @Autowired
  private Validator validator;

  public <T> void validateInput(T t) {
    validator.validate(t).forEach(violation -> {
      if (violation.getConstraintDescriptor().getAnnotation() instanceof NotNull) {
        whenNull(ErrorCodeEnum.TB007, violation);
      } else if (violation.getConstraintDescriptor().getAnnotation() instanceof NotBlank) {
        whenBlank(ErrorCodeEnum.TB007, violation);
      } else if (violation.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero) {
        whenNotPositiveOrZero(ErrorCodeEnum.TB046, violation);
      } else if (violation.getConstraintDescriptor().getAnnotation() instanceof Size) {
        whenInvalidSize(ErrorCodeEnum.TB045, violation);
      } else {
        throw new InputInvalidException(ErrorCodeEnum.TB099);
      }
    });
  }

  public <T> void whenNull(ErrorCodeEnum codeEnum, ConstraintViolation<T> violation,
      Object... args) {
    throw new InputInvalidException(codeEnum,
        args == null || args.length <= 0 ? violation.getPropertyPath().toString() : args);
  }

  public <T> void whenBlank(ErrorCodeEnum codeEnum, ConstraintViolation<T> violation,
      Object... args) {
    throw new InputInvalidException(codeEnum,
        args == null || args.length <= 0 ? violation.getPropertyPath().toString() : args);
  }

  public <T> void whenInvalidSize(ErrorCodeEnum codeEnum, ConstraintViolation<T> violation) {
    throw new BusinessLogicException(codeEnum, violation.getPropertyPath().toString(),
        violation.getConstraintDescriptor().getAttributes().get("min"),
        violation.getConstraintDescriptor().getAttributes().get("max"));
  }

  public <T> void whenNotPositiveOrZero(ErrorCodeEnum codeEnum, ConstraintViolation<T> violation) {
    throw new InputInvalidException(codeEnum, violation.getPropertyPath().toString());
  }
}
