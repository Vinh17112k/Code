package com.example.core_policy_home.service.impl;

import com.example.core_policy_home.constants.Constants;
import com.example.core_policy_home.constants.Constants.StatusProcess;
import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.SuggestionProcessEnum;
import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.exception.InputInvalidException;
import com.example.core_policy_home.mapper.ProcessMapper;
import com.example.core_policy_home.model.Process;
import com.example.core_policy_home.repository.MappingProcessServiceRepository;
import com.example.core_policy_home.repository.ProcessRepository;
import com.example.core_policy_home.service.ProcessService;
import com.example.core_policy_home.validator.InputValidator;
import com.example.core_policy_home.validator.ProcessValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author vinhtq
 * process service
 */
@Slf4j
@Service
@SuppressWarnings({"squid:S3776", "squid:S1199"})
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {
    private final ProcessRepository processRepository;
    private final MappingProcessServiceRepository mappingProcessServiceRepository;
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private ProcessValidator processValidator;
    private final ProcessMapper processMapper;

    @Override
    public ProcessDTO create(ProcessRequestDTO processAddRequestDTO) {
        processValidator.processInsertUpdate(processAddRequestDTO, null);
        Process process = processMapper.fromDto(processAddRequestDTO);
        if (processAddRequestDTO.getStatus() == null) {
            process.setStatus(Constants.StatusProcess.ACTIVE);
        } else process.setStatus(processAddRequestDTO.getStatus());

        processRepository.save(process);
        return processMapper.modelToDto(process);
    }

    @Override
    public ProcessDTO updateProcess(ProcessEditRequestDTO processUpdateRequestDTO) {
        boolean isExitsById = processRepository.existsById(processUpdateRequestDTO.getId());
        if (!isExitsById) {
            throw new InputInvalidException(ErrorCodeEnum.PS0003, ErrorCodeEnum.PROCESS_NAME);
        }
        Process process = findById(processUpdateRequestDTO.getId());
        processValidator.processInsertUpdate(null,processUpdateRequestDTO);
        process.setName(processUpdateRequestDTO.getName());
        process.setProcessCode(processUpdateRequestDTO.getProcessCode());
        process.setStatus(processUpdateRequestDTO.getStatus());
        process.setDescription(processUpdateRequestDTO.getDescription());
        if (processUpdateRequestDTO.getStatus() == null) {
            process.setStatus(StatusProcess.ACTIVE);
        } else process.setStatus(processUpdateRequestDTO.getStatus());
        processRepository.save(process);
        return processMapper.modelToDto(process);
    }
    @Override
    public Process findById(Long aLong) {
        Optional<Process> optionalService = processRepository.findById(aLong);
        return optionalService.orElse(null);
    }

    public Boolean delete(Long aLong) {
        inputValidator.validateInput(aLong);
        Process process = findById(aLong);
        if (process == null) {
            throw new BusinessLogicException(ErrorCodeEnum.TB026, aLong);
        }
        process.setDeleted(Boolean.TRUE);
        processRepository.save(process);
        return true;
    }

    @Override
    public Page<SearchProcessResponse> search(SearchProcessRequest searchRequest, Pageable pageable) {
        return processRepository.findCustom(searchRequest, pageable);
    }

    @Override
    public List<ProcessResponseNameAndId> getAllProcessNameAndId() {
        return processRepository.findAllCustom().stream()
                .map(e -> ProcessResponseNameAndId.builder()
                        .id(e.getId())
                        .name(e.getName() + "_" + e.getProcessCode())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public ProcessGetAllResponseDTO searchProcess(SuggestionProcessEnum field, String search, Pageable pageable) {
        ProcessGetAllResponseDTO dto = new ProcessGetAllResponseDTO();
        switch (field) {
            case PROCEE_NAME:
                dto.setName(processRepository.findAllProcessName(search, pageable));
                break;
            case PROCESS_CODE:
                dto.setProcessCode(processRepository.findAllProcessCode(search, pageable));
                break;
            default:
                throw new InputInvalidException(ErrorCodeEnum.TB007, "Field");
        }
        return dto;
    }
    @Override
    public List<ProcessResponseNameAndId> searchProcessAll(SuggestionProcessEnum field, String search, Pageable pageable) {
        List<ProcessResponseNameAndId> list = new ArrayList<>();
        List<Process> process= processRepository.findAllProcessCodeAll(search,pageable);
        switch (field) {
            case PROCESS_CODE_NAME:
                for (Process p: process
                     ) {
                    ProcessResponseNameAndId dto = new ProcessResponseNameAndId();
                    dto.setId(p.getId());
                    dto.setName(p.getName()+"_"+p.getProcessCode());
                    list.add(dto);
                }
                break;
            default:
                throw new InputInvalidException(ErrorCodeEnum.TB007, "Field");
        }
        return list;
    }
}

