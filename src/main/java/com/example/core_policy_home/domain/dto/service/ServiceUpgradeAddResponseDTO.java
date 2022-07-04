package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUpgradeAddResponseDTO extends BaseDTO {

  private long id;
  private String name;
  private String serviceCode;
  private String description;
  private Integer status;
  private LocalDateTime createdAt;
  private String createdBy;
}
