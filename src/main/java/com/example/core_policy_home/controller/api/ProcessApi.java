package com.example.core_policy_home.controller.api;

import com.example.core_policy_home.constants.enums.SuggestionProcessEnum;
import com.example.core_policy_home.domain.ResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vinhtq
 * interface process api
 */
@RequestMapping(path = "${application-context-name}/public/v1/process")
@Tag(name = "process", description = "The Process API V1")
@SuppressWarnings("squid:S1710")
public interface ProcessApi {
  @PostMapping(produces = "application/json", consumes = {
      MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Tạo mới process", tags = {"process"})
  ProcessDTO create(@RequestBody ProcessRequestDTO data);
  @PutMapping(produces = "application/json", consumes = {
          MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Cập nhật process", tags = {"process"})
  ProcessDTO update(@RequestBody ProcessEditRequestDTO data);

  @DeleteMapping("{id}")
  @Operation(summary = "Xóa process với id tương ứng ", tags = {"process"})
  boolean delete(
          @Parameter(description = "id của process cần xóa", example = "1")
          @PathVariable(value = "id") Long id);

  @Operation(summary = "Search Process", tags = {"process"})
  @PostMapping("/search")
  ResponseEntity<ResponseDTO<Page<SearchProcessResponse>>> search(@RequestBody SearchProcessRequest searchRequest,
                                                                  @Parameter(hidden = true)
                                                                  @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                                  @PageableDefault(size = 30) Pageable pageable);
//  @GetMapping("/all-process-name")
//  ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllProcessNameAndId();
  @GetMapping(value = "suggestion-upgrade")
  @Operation(summary = "Đưa ra các gợi ý cho web", tags = {"process"})
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
  ProcessGetAllResponseDTO getAllUpgrade(
          @Parameter(description = "Trường để lấy suggestion, nhận 1 trong các giá trị trong enum (nếu all thì lấy tất cả bộ id-code-name)", example =
                  "PROCESS_NAME, PROCESS_CODE")
          @RequestParam(value = "field") SuggestionProcessEnum field,
          @Parameter(description = "Trường để search", example = "abc")
          @RequestParam(value = "search", required = false) String search,
          @Parameter(hidden = true)
          @SortDefault(sort = "id", direction = Sort.Direction.ASC)
          @PageableDefault(size = 30) Pageable pageable);
    @GetMapping(value = "suggestion-name-code")
    @Operation(summary = "Đưa ra các gợi ý cho web", tags = {"process"})
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
    ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllUpgradeProcess(
            @Parameter(description = "Trường để lấy suggestion, nhận 1 trong các giá trị trong enum (nếu all thì lấy tất cả bộ id-code-name)", example =
                    "PROCESS_NAME, PROCESS_CODE")
            @RequestParam(value = "field") SuggestionProcessEnum field,
            @Parameter(description = "Trường để search", example = "abc")
            @RequestParam(value = "search", required = false) String search,
            @Parameter(hidden = true)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            @PageableDefault(size = 30) Pageable pageable);
}
