package com.example.core_policy_home.mapper;

import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeGetResponseDTO;
import com.example.core_policy_home.model.ServiceUpgrade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ServiceUpgradeMapper {
  ServiceUpgrade fromDtoUpgrade(ServiceUpgradeAddRequestDTO dto);
  ServiceUpgradeAddResponseDTO fromEntityToAddUpgradeResponse(ServiceUpgrade services);
  ServiceUpgradeEditResponseDTO fromEntityToEditUpgradeResponse(ServiceUpgrade services);
  ServiceUpgradeGetResponseDTO fromEntityUpgradeToGetResponse(ServiceUpgrade services);
}
