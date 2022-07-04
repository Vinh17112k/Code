package com.example.core_policy_home.service;


import com.example.core_policy_home.constants.enums.SuggestionEnum;
import com.example.core_policy_home.domain.dto.service.ServiceGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import com.example.core_policy_home.model.ServiceUpgrade;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ServiceService{
  Page<ServiceUpgradeSearchResponseDTO> search(ServiceUpgradeSearchRequestDTO searchRequest, Pageable pageable);

  ServiceUpgrade addServiceUpgrade(ServiceUpgradeAddRequestDTO data);
  ServiceUpgrade updateServiceUpgrade(ServiceUpgradeEditRequestDTO data);

  ServiceGetAllResponseDTO searchUpgrade(SuggestionEnum field, String search, Pageable pageable);
  Boolean deleteUpgrade(Long id);
  ServiceUpgrade getServiceUpgrade(long id);
//  ServiceUpgrade findById(Long id);
}
