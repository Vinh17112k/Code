package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceGetAllResponseDTO extends BaseDTO implements Serializable {
  @JsonProperty("id")
  private List<String> id;
  private List<String> name;
  private List<String> processCode;
  private List<String> serviceCode;
}
