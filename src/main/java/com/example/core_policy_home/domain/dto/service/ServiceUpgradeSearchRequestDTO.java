package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.Size;
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
public class ServiceUpgradeSearchRequestDTO extends BaseDTO {
  @Schema(description = "name của service", example = "tien dien")
  @Size(max = 100)
  private String name;
  @Schema(description = "code của service", example = "1121")
  @Size(max = 20)
  private String serviceCode;
  @Schema(description = "processCode của service", example = "112")
  @Size(max = 20)
  private List<String> processCode;
  @Schema(description = "status của service cần tìm kiếm (1 là active, 0 là inactive)", example = "112")
  private Integer status;
}
