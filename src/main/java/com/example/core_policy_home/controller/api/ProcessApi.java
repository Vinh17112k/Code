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
import java.util.List;
import org.hibernate.annotations.Parameter;
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
@RequestMapping(path = "public/v1/process")
@SuppressWarnings("squid:S1710")
public interface ProcessApi {
  @PostMapping(produces = "application/json", consumes = {
      MediaType.APPLICATION_JSON_VALUE})
  ProcessDTO create(@RequestBody ProcessRequestDTO data);
  @PutMapping(produces = "application/json", consumes = {
          MediaType.APPLICATION_JSON_VALUE})
  ProcessDTO update(@RequestBody ProcessEditRequestDTO data);

  @DeleteMapping("{id}")
  boolean delete(
          @PathVariable(value = "id") Long id);
  @PostMapping("/search")
  ResponseEntity<ResponseDTO<Page<SearchProcessResponse>>> search(@RequestBody SearchProcessRequest searchRequest,
                                                                  @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                                  @PageableDefault(size = 30) Pageable pageable);
//  @GetMapping("/all-process-name")
//  ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllProcessNameAndId();
  @GetMapping(value = "suggestion-upgrade")
  ProcessGetAllResponseDTO getAllUpgrade(
          @RequestParam(value = "field") SuggestionProcessEnum field,
          @RequestParam(value = "search", required = false) String search,
          @SortDefault(sort = "id", direction = Sort.Direction.ASC)
          @PageableDefault(size = 30) Pageable pageable);
    @GetMapping(value = "suggestion-name-code")
    ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllUpgradeProcess(
            @RequestParam(value = "field") SuggestionProcessEnum field,
            @RequestParam(value = "search", required = false) String search,
            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            @PageableDefault(size = 30) Pageable pageable);
}
