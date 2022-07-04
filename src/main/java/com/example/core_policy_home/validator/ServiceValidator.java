package com.example.core_policy_home.validator;

import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.MessageCodeEnum;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.exception.InputInvalidException;
import com.example.core_policy_home.model.ServiceUpgrade;
import com.example.core_policy_home.repository.ProcessRepository;
import com.example.core_policy_home.repository.ServiceUpgradeRepository;
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
public class ServiceValidator {
  @Autowired
  private ProcessRepository processRepository;
  @Autowired
  private ServiceUpgradeRepository serviceUpgradeRepository;
  public void serviceInsertUpdate(ServiceUpgradeEditRequestDTO update) {
    log.debug("validate require");
    this.validateRequireUpdate(update);
    this.validateLengthUpdate(update);
    this.validFormulaNameSameUpdate(update.getName(), update.getId());
    this.validFormulaServiceCodeSameUpdate(update.getServiceCode(), update.getId());
  }
  public void validFormulaServiceCodeSameUpdate(String serviceCode, Long serviceId) {
    String originServiceCode = serviceUpgradeRepository.getServiceCodeById(serviceId);
    if (serviceCode!=null&&!serviceCode.equalsIgnoreCase(originServiceCode)&&Boolean.TRUE.equals(isDuplicateServicesCode(serviceCode, null))) {
      throw new InputInvalidException(ErrorCodeEnum.PS0008, serviceCode);
    }
  }
  public void validFormulaNameSameUpdate(String nane, Long processId) {
    String originName = processRepository.getName(processId);
    if (!nane.equalsIgnoreCase(originName)&&Boolean.TRUE.equals(isDuplicateString(nane, null))) {
        throw new InputInvalidException(ErrorCodeEnum.PS0008, nane);
    }
  }
  public Boolean isDuplicateString(String name, Long id) {
    if (Boolean.TRUE.equals(DataUtils.nullOrEmpty(name))) {
      return false;
    }
    if (Boolean.TRUE.equals(DataUtils.isNull(id))) {
      id = -1L;
    }
    Long countProcess = processRepository.countProcessAllByName(name.trim(), id);
    return countProcess > 0L ? Boolean.TRUE : Boolean.FALSE;
  }
  public Boolean isDuplicateServicesCode(String serviceCode, Long id) {
    if (Boolean.TRUE.equals(DataUtils.nullOrEmpty(serviceCode))) {
      return false;
    }
    if (Boolean.TRUE.equals(DataUtils.isNull(id))) {
      return false;
    }
    Long countService = serviceUpgradeRepository.countServicesAllByServiceCode(serviceCode.trim());
    return countService > 0L ? Boolean.TRUE : Boolean.FALSE;
  }
  private void validateRequireUpdate(ServiceUpgradeEditRequestDTO request) {
    log.debug("Tên process");
    if (Boolean.TRUE.equals(DataUtils.isNullOrEmptyStr(request.getName()))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB007, MessageCodeEnum.MTB044);
    }
  }
  private void validateLengthUpdate(ServiceUpgradeEditRequestDTO request) {
    log.debug("Tên dịch vụ");
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getName(),100))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB044);
    }
  }
  public void checkExsitsOptional(Optional<ServiceUpgrade> services) {
    if (Boolean.FALSE.equals(services.isPresent())) {
      throw new InputInvalidException(ErrorCodeEnum.PS0003, ErrorCodeEnum.SERVICE_NAME);
    }
  }
  private void validateSearchRequest(ServiceUpgradeSearchRequestDTO request) {
    if (Boolean.TRUE.equals(DataUtils.isOverLength(request.getName(), 100))) {
      throw new BusinessLogicException(ErrorCodeEnum.TB047, MessageCodeEnum.MTB043);
    }
  }
}
