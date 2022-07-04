package com.example.core_policy_home.controller.impl;

import com.example.core_policy_home.constants.enums.SuggestionProcessEnum;
import com.example.core_policy_home.controller.api.ProcessApi;
import com.example.core_policy_home.domain.ResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import com.example.core_policy_home.service.ProcessService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vinhtq
 * process controller v1
 */
@RestController
public class ProcessController implements ProcessApi {
  @Autowired
  private ProcessService processService;
  @Override
  public ProcessDTO create(@RequestBody ProcessRequestDTO data) {
    return processService.create(data);
  }

  @Override
  public ProcessDTO update(@RequestBody ProcessEditRequestDTO data) {
    return processService.updateProcess(data);
  }

  @Override
  public boolean delete(Long id) {
    return processService.delete(id);
  }

  @Override
  public ResponseEntity<ResponseDTO<Page<SearchProcessResponse>>> search(
      SearchProcessRequest searchRequest, Pageable pageable) {
    return ResponseDTO.ofSuccess(processService.search(searchRequest, pageable));
  }
//  @Override
//  public ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllProcessNameAndId() {
//    return ResponseDTO.ofSuccess(processService.getAllProcessNameAndId());
//  }

  @Override
  public ProcessGetAllResponseDTO getAllUpgrade(SuggestionProcessEnum field, String search, Pageable pageable) {
    if (search != null) {
      search = search.trim();
      search = search.toLowerCase();
    }
    return processService.searchProcess(field, search, pageable);
  }
  @Override
  public ResponseEntity<ResponseDTO<List<ProcessResponseNameAndId>>> getAllUpgradeProcess(SuggestionProcessEnum field, String search, Pageable pageable){
    if (search != null) {
      search = search.trim();
      search = search.toLowerCase();
    }
    return ResponseDTO.ofSuccess(processService.searchProcessAll(field, search, pageable));
  }
}
