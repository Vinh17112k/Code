package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class ServiceUpgradeEditRequestDTO extends BaseDTO {
  @NotBlank
  @NotNull
//  @Schema(description = "Id dịch vụ", example = "1")
  private Long id;
  @NotBlank
//  @Schema(description = "Tên của dịch vụ", example = "Tien dien")
  private String name;
  @NotBlank
//  @Schema(description = "ServiceCode của service", example = "HN002")
  private String serviceCode;
//  @Schema(description = "ProcessCode của service", example = "686803")
  private Set<Long> listProcessId;
//  @Schema(description = "Mô tả của dịch vụ", example = "Tinh toan tien dien cua Ha Noi")
  private String description;
//  @Schema(description = "Trạng thái của dịch vụ (1 là active, 0 là inactive)", example = "1")
  @NotNull
  private Integer status;
}
