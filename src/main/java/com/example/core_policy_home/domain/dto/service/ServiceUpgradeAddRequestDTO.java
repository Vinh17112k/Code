package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUpgradeAddRequestDTO extends BaseDTO {
  private String name;
  private String serviceCode;
  private Set<Long> listProcessId;
  private String description;
  private Integer status;
}
