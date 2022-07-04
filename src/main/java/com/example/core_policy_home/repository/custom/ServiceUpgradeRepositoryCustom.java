package com.example.core_policy_home.repository.custom;

import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceUpgradeRepositoryCustom {
    Page<ServiceUpgradeSearchResponseDTO> search(ServiceUpgradeSearchRequestDTO searchReq, Pageable pageable);
}
