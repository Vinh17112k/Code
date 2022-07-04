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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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

@RequestMapping(path = "${application-context-name}/public/v1/services")
@Tag(name = "services", description = "The Services API V1")
@SuppressWarnings("squid:S1710")
public interface ServiceApi {
    @PostMapping("/search")
    ResponseEntity<ResponseDTO<Page<ServiceUpgradeSearchResponseDTO>>> search(@RequestBody ServiceUpgradeSearchRequestDTO searchRequest,
                                                                              @Parameter(hidden = true)
                                                                              @SortDefault(sort = "id", direction = Direction.ASC)
                                                                              @PageableDefault(size = 30) Pageable pageable);

    @PostMapping("upgrade")
    @Operation(summary = "Tạo mới service", tags = {"services"})
    ServiceUpgradeAddResponseDTO create(@RequestBody ServiceUpgradeAddRequestDTO data);
    @PutMapping("upgrade")
//    @Operation(summary = "Update thông tin service với id tương ứng", tags = {"services"})
    ServiceUpgradeEditResponseDTO update(@RequestBody ServiceUpgradeEditRequestDTO data);

    @GetMapping(value = "suggestion-upgrade")
    @Operation(summary = "Đưa ra các gợi ý cho web", tags = {"services"})
    @SuppressWarnings({"java:S1710"})
    @Parameters(
            {@Parameter(
                    in = ParameterIn.QUERY,
                    name = "page",
                    example = "0",
                    description = ""
            ), @Parameter(
                    in = ParameterIn.QUERY,
                    name = "size",
                    example = "10",
                    description = ""
            ), @Parameter(
                    in = ParameterIn.QUERY,
                    name = "sort",
                    example = "createdAt,desc",
                    description = ""
            )}
    )
    ServiceGetAllResponseDTO getAllUpgrade(
            @Parameter(description = "Trường để lấy suggestion, nhận 1 trong các giá trị trong enum (nếu all thì lấy tất cả bộ id-code-name)", example =
                    "SERVICE_ID, SERVICE_NAME, SERVICE_SERVICE_CODE, SERVICE_PROCESS_CODE")
            @RequestParam(value = "field") SuggestionEnum field,
            @Parameter(description = "Trường để search", example = "abc")
            @RequestParam(value = "search", required = false) String search,
            @Parameter(hidden = true)
            @SortDefault(sort = "id", direction = Direction.ASC)
            @PageableDefault(size = 30) Pageable pageable);
    @DeleteMapping(value = "/delete-upgrade/{id}")
    boolean deleteUpgrade(
            @Parameter(description = "id của service cần xóa", example = "1")
            @PathVariable(value = "id") Long id);

    @GetMapping("/detail/{id}")
    @Operation(summary = "Lấy thông tin chi tiết service", tags = {"services"})
    ServiceUpgradeGetResponseDTO getServiceUpgrade(
            @Parameter(description = "id của service cần lấy", example = "1")
            @PathVariable(value = "id") Long id);
}
