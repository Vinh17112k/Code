package com.example.core_policy_home.validator;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.MessageCodeEnum;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.exception.InputInvalidException;
import com.example.core_policy_home.repository.ProcessRepository;
import com.example.core_policy_home.util.DataUtils;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProcessValidator {

  @Autowired
  private InputValidator inputValidator;
  @Autowired
  private ProcessRepository processRepository;

  /**
   * Validate thông tin thêm mới process
   *
   * @param input Thông tin process
   */
  public void processInsertUpdate(ProcessRequestDTO input, ProcessEditRequestDTO update) {
    log.debug("validate require");
    if(input!=null){
      this.validateRequire(input);
      this.validateLength(input);
      this.validFormulaNameSame(input.getName(), -1L);
      this.validFormulaProcessCodeSame(input.getProcessCode(), -1L);
    }else if(update!=null){
      this.validateRequireUpdate(update);
      this.validateLengthUpdate(update);
      this.validFormulaNameSameUpdate(update.getName(), update.getId());
      this.validFormulaProcessCodeSameUpdate(update.getProcessCode(), update.getId());
    }
  }
  public void validFormulaProcessCodeSame(String processCode, Long processId) {
    if (processId != null) {
      boolean isExits = processRepository.existsByProcessCode(processCode);

      if (Boolean.TRUE.equals(isExits)) {
        throw new BusinessLogicException(ErrorCodeEnum.TB017, MessageCodeEnum.MTB045);
      }
    }
  }
  public void validFormulaNameSame(String processName, Long processId) {
    if (processId == null) {
      boolean isExits = processRepository.existsByName(processName);
      log.debug("isExits"+isExits);
      if (Boolean.TRUE.equals(isExits)) {
        throw new BusinessLogicException(ErrorCodeEnum.TB017, MessageCodeEnum.MTB044);
      }
    }

  }
  public void validFormulaNameSameUpdate(String name, Long processId) {
    String originName = processRepository.getName(processId);
    if (!name.equalsIgnoreCase(originName)&&Boolean.TRUE.equals(isDuplicateString(name, processId))) {
        throw new InputInvalidException(ErrorCodeEnum.PS0008, name);
    }
  }
  public void validFormulaProcessCodeSameUpdate(String processCode, Long processId) {
    String originProcessCode = processRepository.getProcessCode(processId);
    if (!processCode.equalsIgnoreCase(originProcessCode)&&Boolean.TRUE.equals(isDuplicateProcessCode(processCode, processId))) {
        throw new InputInvalidException(ErrorCodeEnum.PS0008, processCode);
    }
  }
  public Boolean isDuplicateString(String name, Long id) {
    if (Boolean.TRUE.equals(DataUtils.nullOrEmpty(name))) {
      return false;
    }
    if (Boolean.TRUE.equals(DataUtils.isNull(id))) {
      return false;
    }
    Long countProcess = processRepository.countProcessAllByName(name.trim(), id);
    return countProcess > 0L ? Boolean.TRUE : Boolean.FALSE;
  }
  public Boolean isDuplicateProcessCode(String processCode, Long id) {
    if (Boolean.TRUE.equals(DataUtils.nullOrEmpty(processCode))) {
      return false;
    }
    if (Boolean.TRUE.equals(DataUtils.isNull(id))) {
      return false;
    }
    Long countProcess = processRepository.countProcessAllByProcessCode(processCode.trim());
    return countProcess > 0L ? Boolean.TRUE : Boolean.FALSE;
  }
  private void validateRequire(ProcessRequestDTO request) {
    if (Boolean.TRUE.equals(DataUtils.isNullOrEmptyStr(request.getName()))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB007, MessageCodeEnum.MTB044);
    }
    if (Boolean.TRUE.equals(DataUtils.isNullOrEmptyStr(request.getProcessCode()))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB007, MessageCodeEnum.MTB045);
    }
  }
  private void validateLength(ProcessRequestDTO request) {
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getName(),100))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB044);
    }
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getProcessCode(),20))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB045);
    }
  }
  private void validateRequireUpdate(ProcessEditRequestDTO request) {
    if (Boolean.TRUE.equals(DataUtils.isNullOrEmptyStr(request.getName()))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB007, MessageCodeEnum.MTB044);
    }
    log.debug("Mã Process");
    if (Boolean.TRUE.equals(DataUtils.isNullOrEmptyStr(request.getProcessCode()))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB007, MessageCodeEnum.MTB045);
    }
  }
  private void validateLengthUpdate(ProcessEditRequestDTO request) {
    log.debug("Tên process");
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getName(),100))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB044);
    }
    log.debug("Mã Process");
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getProcessCode(),20))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB045);
    }
  }
  public void checkExsitsOptional(Optional<Process> process) {
    if (Boolean.FALSE.equals(process.isPresent())) {
      throw new BusinessLogicException(ErrorCodeEnum.TB048, MessageCodeEnum.MTB044);
    }
  }
}
