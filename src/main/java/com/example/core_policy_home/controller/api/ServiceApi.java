package com.example.core_policy_home.controller.api;

import com.example.core_policy_home.constants.enums.SuggestionEnum;
import com.example.core_policy_home.domain.ResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeGetResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "public/v1/services")
@SuppressWarnings("squid:S1710")
public interface ServiceApi {
    @PostMapping("/search")
    ResponseEntity<ResponseDTO<Page<ServiceUpgradeSearchResponseDTO>>> search(@RequestBody ServiceUpgradeSearchRequestDTO searchRequest,
                                                                              @SortDefault(sort = "id", direction = Direction.ASC)
                                                                              @PageableDefault(size = 30) Pageable pageable);

    @PostMapping("upgrade")
    ServiceUpgradeAddResponseDTO create(@RequestBody ServiceUpgradeAddRequestDTO data);
    @PutMapping("upgrade")
//    @Operation(summary = "Update thông tin service với id tương ứng", tags = {"services"})
    ServiceUpgradeEditResponseDTO update(@RequestBody ServiceUpgradeEditRequestDTO data);

    @GetMapping(value = "suggestion-upgrade")
    @SuppressWarnings({"java:S1710"})
    ServiceGetAllResponseDTO getAllUpgrade(
            @RequestParam(value = "field") SuggestionEnum field,
            @RequestParam(value = "search", required = false) String search,
            @SortDefault(sort = "id", direction = Direction.ASC)
            @PageableDefault(size = 30) Pageable pageable);
    @DeleteMapping(value = "/delete-upgrade/{id}")
    boolean deleteUpgrade(
            @PathVariable(value = "id") Long id);

    @GetMapping("/detail/{id}")
    ServiceUpgradeGetResponseDTO getServiceUpgrade(
            @PathVariable(value = "id") Long id);
}
