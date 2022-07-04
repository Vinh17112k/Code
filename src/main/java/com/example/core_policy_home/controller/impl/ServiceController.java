package com.example.core_policy_home.controller.impl;

import com.example.core_policy_home.constants.enums.SuggestionEnum;
import com.example.core_policy_home.controller.api.ServiceApi;
import com.example.core_policy_home.domain.ResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import com.example.core_policy_home.domain.dto.service.ServiceGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeGetResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import com.example.core_policy_home.mapper.ServiceUpgradeMapper;
import com.example.core_policy_home.model.Process;
import com.example.core_policy_home.repository.ProcessRepository;
import com.example.core_policy_home.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController implements ServiceApi {

  @Autowired
  private ServiceService serviceService;
  @Autowired
  private ProcessRepository processRepository;
  private final ServiceUpgradeMapper serviceUpgradeMapper  = Mappers.getMapper(ServiceUpgradeMapper.class);

  @Override
  @Operation(summary = "add service upgrade")
  public ServiceUpgradeAddResponseDTO create(@RequestBody ServiceUpgradeAddRequestDTO data) {
    return serviceUpgradeMapper.fromEntityToAddUpgradeResponse(serviceService.addServiceUpgrade(data));
  }
  @Override
  @Operation(summary = "update service upgrade")
  public ServiceUpgradeEditResponseDTO update(ServiceUpgradeEditRequestDTO data) {
    return serviceUpgradeMapper.fromEntityToEditUpgradeResponse(serviceService.updateServiceUpgrade(data));
  }

  @Override
  public ServiceGetAllResponseDTO getAllUpgrade(SuggestionEnum field, String search, Pageable pageable) {
    if (search != null) {
      search = search.trim();
      search = search.toLowerCase();
    }
    return serviceService.searchUpgrade(field, search, pageable);
  }

  @Override
  public ResponseEntity<ResponseDTO<Page<ServiceUpgradeSearchResponseDTO>>> search(ServiceUpgradeSearchRequestDTO searchRequest, Pageable pageable) {
    return ResponseDTO.ofSuccess(serviceService.search(searchRequest, pageable));
  }
  @Override
  public boolean deleteUpgrade(Long id) {
    return serviceService.deleteUpgrade(id);
  }

  @Override
  public ServiceUpgradeGetResponseDTO getServiceUpgrade(Long id) {
    ServiceUpgradeGetResponseDTO serviceUpgradeGetResponseDTO = new ServiceUpgradeGetResponseDTO();
    serviceUpgradeGetResponseDTO = serviceUpgradeMapper.fromEntityUpgradeToGetResponse(serviceService.getServiceUpgrade(id));
    List<Process> process = processRepository.findAllProcessOfService(id);
    List<ProcessResponseNameAndId> list = new ArrayList<>();
    for (Process p: process
         ) {
      ProcessResponseNameAndId processResponseNameAndId = new ProcessResponseNameAndId();
      processResponseNameAndId.setId(p.getId());
      processResponseNameAndId.setName(p.getName()+"_"+p.getProcessCode());
      list.add(processResponseNameAndId);
    }
    serviceUpgradeGetResponseDTO.setProcessCode(list);
    return serviceUpgradeGetResponseDTO;
  }
}
