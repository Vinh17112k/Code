package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@SuperBuilder
public class ServiceUpgradeSearchResponseDTO extends BaseDTO {

  @Schema(description = "id service", example = "123123")
  private long id;
  @Schema(description = "service name", example = "tien dien")
  private String name;
  @Schema(description = "service code service", example = "123123")
  private String serviceCode;
  @Schema(description = "process code service", example = "6686803")
  private String processCode;
  @Schema(description = "status của service (0 là off, 1 là on)", example = "1")
  private Integer status;
  @Schema(description = "Thời điểm tạo service", example = "20/10/2020")
  private LocalDateTime createdAt;
  @Schema(description = "Thời điểm update service", example = "21/10/2020")
  private LocalDateTime updatedAt;
}