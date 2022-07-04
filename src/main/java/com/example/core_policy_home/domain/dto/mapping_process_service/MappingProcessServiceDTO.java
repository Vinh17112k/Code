package com.example.core_policy_home.domain.dto.mapping_process_service;

import com.example.core_policy_home.domain.BaseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessDTO;
import com.example.core_policy_home.domain.dto.service.ServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * mapping process and service
 *
 * @author vinhtq
 * @version v1
 * @date 06/20/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated
public class MappingProcessServiceDTO extends BaseDTO {

  private Long id;
  private ProcessDTO process;
  private ServiceDTO service;
}
