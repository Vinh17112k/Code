package com.example.core_policy_home.service.impl;

import com.example.core_policy_home.constants.Constants;
import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.constants.enums.SuggestionEnum;
import com.example.core_policy_home.domain.dto.service.ServiceGetAllResponseDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeAddRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeEditRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import com.example.core_policy_home.exception.BusinessLogicException;
import com.example.core_policy_home.exception.InputInvalidException;
import com.example.core_policy_home.mapper.ServiceUpgradeMapper;
import com.example.core_policy_home.model.MappingProcessService;
import com.example.core_policy_home.model.Process;
import com.example.core_policy_home.model.ServiceUpgrade;
import com.example.core_policy_home.repository.MappingProcessServiceRepository;
import com.example.core_policy_home.repository.ProcessRepository;
import com.example.core_policy_home.repository.ServiceUpgradeRepository;
import com.example.core_policy_home.service.ServiceService;
import com.example.core_policy_home.util.DataUtils;
import com.example.core_policy_home.validator.CommonValidator;
import com.example.core_policy_home.validator.InputValidator;
import com.example.core_policy_home.validator.MappingProcessServiceValidator;
import com.example.core_policy_home.validator.ProcessValidator;
import com.example.core_policy_home.validator.ServiceValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@SuppressWarnings({"squid:S3776", "squid:S1199"})
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private CommonValidator commonValidator;
    @Autowired
    private ServiceValidator serviceValidator;
    @Autowired
    private ProcessValidator processValidator;
    @Autowired
    private MappingProcessServiceValidator mappingProcessServiceValidator;
    @Autowired
    private MappingProcessServiceRepository mappingProcessServiceRepository;

    @Autowired
    private ServiceUpgradeRepository serviceUpgradeRepository;

    private final ServiceUpgradeMapper serviceUpgradeMapper;


    @Override
    public Page<ServiceUpgradeSearchResponseDTO> search(ServiceUpgradeSearchRequestDTO searchRequest, Pageable pageable) {
        return serviceUpgradeRepository.search(searchRequest, pageable);
    }

    @Override
    public ServiceUpgrade addServiceUpgrade(ServiceUpgradeAddRequestDTO data) {
        commonValidator.validateInput(data);
        if (!StringUtils.isEmpty(data.getServiceCode()) && !checkDuplicateCode(
                data.getServiceCode().trim())) {
            throw new InputInvalidException(ErrorCodeEnum.PS0008, ErrorCodeEnum.SERVICE_CODE);
        }
        if (Boolean.TRUE.equals(isDuplicateNameUpgrade(data.getName(), null))) {
            throw new InputInvalidException(ErrorCodeEnum.PS0008, data.getName());
        }
        ServiceUpgrade s = serviceUpgradeMapper.fromDtoUpgrade(data);
//        s.setCode(Constants.PREFIX_SERVICE + getServiceCodeSequence());
        serviceUpgradeRepository.save(s);
        Set<Long> listProcessId = data.getListProcessId();
        if (listProcessId != null) {
            addMappingProcessService(listProcessId,s);
        }
        return s;
    }

    @Override
    public ServiceUpgrade updateServiceUpgrade(ServiceUpgradeEditRequestDTO data) {
        inputValidator.validateInput(data.getId());
        boolean isExitsById = serviceUpgradeRepository.existsById(data.getId());
        if (!isExitsById) {
            throw new InputInvalidException(ErrorCodeEnum.PS0003, ErrorCodeEnum.SERVICE_NAME);
        }
        Optional<ServiceUpgrade> services = serviceUpgradeRepository.findById(data.getId());
        serviceValidator.checkExsitsOptional(services);
        serviceValidator.serviceInsertUpdate(data);
        services.get().setName(data.getName());
        services.get().setServiceCode(data.getServiceCode());
        services.get().setStatus(data.getStatus());
        services.get().setDescription(data.getDescription());
        if (data.getStatus() == null) {
            services.get().setStatus(Constants.StatusProcess.ACTIVE);
        } else services.get().setStatus(data.getStatus());
        Set<Long> listProcessId = data.getListProcessId();
        boolean checkExistByServiceId = mappingProcessServiceRepository.existsByServiceId(data.getId());
        if (listProcessId != null && !listProcessId.isEmpty()) {
            if(checkExistByServiceId){
                deleteMappingProcessService(data.getId());
            }
            addMappingProcessService(listProcessId, services.get());
        }else{
            if(checkExistByServiceId){
                deleteMappingProcessService(data.getId());
            }
        }
        return serviceUpgradeRepository.save(services.get());
    }

    @Override
    public ServiceGetAllResponseDTO searchUpgrade(SuggestionEnum field, String search, Pageable pageable) {
        ServiceGetAllResponseDTO dto = new ServiceGetAllResponseDTO();
        switch (field) {
            case SERVICE_NAME:
                dto.setName(serviceUpgradeRepository.findAllServiceName(search, pageable));
                break;
            case SERVICE_SERVICE_CODE:
                dto.setServiceCode(serviceUpgradeRepository.findAllServiceCode(search, pageable));
                break;
            case SERVICE_PROCESS_CODE:
                dto.setProcessCode(processRepository.findAllProcessCode(search, pageable));
                break;
            default:
                throw new InputInvalidException(ErrorCodeEnum.TB007, "Field");
        }
        return dto;
    }

    public boolean checkDuplicateCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return true;
        }
        List<ServiceUpgrade> list = serviceUpgradeRepository.findAllByServiceCode(code);
        return list.isEmpty();
    }

    public Boolean isDuplicateNameUpgrade(String name, Long id) {
        if (Boolean.TRUE.equals(DataUtils.nullOrEmpty(name))) {
            return false;
        }
        if (Boolean.TRUE.equals(DataUtils.isNull(id))) {
            id = -1L;
        }
        Long countService = serviceUpgradeRepository.countServiceUpgradeAllByName(name.trim(), id);
        return countService > 0L ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean deleteUpgrade(Long aLong) {
        inputValidator.validateInput(aLong);
        Optional<ServiceUpgrade> serviceUpgrade = serviceUpgradeRepository.findById(aLong);
        if (!serviceUpgrade.isPresent()) {
            throw new BusinessLogicException(ErrorCodeEnum.TB026, aLong);
        }
        serviceUpgrade.get().setDeleted(Boolean.TRUE);
        serviceUpgradeRepository.save(serviceUpgrade.get());
        return true;
    }

    @Override
    public ServiceUpgrade getServiceUpgrade(long id) {
        ServiceUpgrade services = serviceUpgradeRepository.findById(id).get();
        if (services != null) {
            return services;
        } else {
            throw new BusinessLogicException(ErrorCodeEnum.E01, "id");
        }
    }
    private void addMappingProcessService(Set<Long> listProcessId, ServiceUpgrade s) {
        List<MappingProcessService> mappingProcessServices = new ArrayList<>();
        for (Long id : listProcessId) {
            MappingProcessService mappingProcessService = new MappingProcessService();
            Optional<Process> process = processRepository.findById(id);
            if (process.isPresent()) {
                mappingProcessService.setProcessId(process.get().getId());
                mappingProcessService.setServiceId(s.getId());
                mappingProcessServices.add(mappingProcessService);
            } else {
                throw new InputInvalidException(ErrorCodeEnum.PS0009, id);
            }
        }
        mappingProcessServiceRepository.saveAll(mappingProcessServices);
    }

    private void deleteMappingProcessService(Long serviceId) {
        mappingProcessServiceRepository.deleteAllByServiceId(serviceId);
    }
}

