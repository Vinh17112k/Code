package com.example.core_policy_home.mapper;

import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.process.ProcessEditRequestDTO;
import com.example.core_policy_home.domain.dto.process.ProcessRequestDTO;
import com.example.core_policy_home.model.Process;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface ProcessMapper {

  ProcessMapper INSTANCE = Mappers.getMapper(ProcessMapper.class);

  Process fromDto(ProcessRequestDTO dto);
  Process fromUpdateDto(ProcessEditRequestDTO dto);
  ProcessDTO modelToDto(Process model);
}
