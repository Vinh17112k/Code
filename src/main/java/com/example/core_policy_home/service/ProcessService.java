package com.example.core_policy_home.service;

import com.example.core_policy_home.constants.enums.SuggestionProcessEnum;
import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import com.example.core_policy_home.model.Process;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProcessService {
  ProcessDTO create(ProcessRequestDTO processAddRequestDTO);

  ProcessDTO updateProcess(ProcessEditRequestDTO processUpdateRequestDTO);
  Boolean delete(Long id);
  Process findById(Long id);
  Page<SearchProcessResponse> search(SearchProcessRequest searchRequest, Pageable pageable);
  List<ProcessResponseNameAndId> getAllProcessNameAndId();
  ProcessGetAllResponseDTO searchProcess(SuggestionProcessEnum field, String search, Pageable pageable);
  List<ProcessResponseNameAndId> searchProcessAll(SuggestionProcessEnum field, String search, Pageable pageable);
}
