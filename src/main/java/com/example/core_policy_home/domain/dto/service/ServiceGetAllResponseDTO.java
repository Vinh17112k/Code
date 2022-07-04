package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@SuperBuilder
public class ServiceGetAllResponseDTO extends BaseDTO implements Serializable {
  @Schema(description = "id service", example = "[123123, 11, 223 323]")
  @JsonProperty("id")
  private List<String> id;
  @Schema(description = "Tên service", example = "[tien dien, tien nuoc, mua data]")
  private List<String> name;
  @Schema(description = "processCode của service", example = "[A1231, A12, E232]")
  private List<String> processCode;
  @Schema(description = "serviceCode của service", example = "[A1231, A12, E12, WW123, WEW123]")
  private List<String> serviceCode;
}
