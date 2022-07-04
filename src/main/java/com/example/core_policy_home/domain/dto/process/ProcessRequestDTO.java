package com.example.core_policy_home.domain.dto.process;

import com.example.core_policy_home.domain.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ProcessRequestDTO extends BaseDTO {
  @NotBlank
  @NotNull
  @Schema(description = "Name của process", example = "Tien dien")
  private String name;
  @NotBlank
  @NotNull
  @Schema(description = "ProcessCode của process", example = "686803")
  private String processCode;
  @NotNull
  @Schema(description = "Trạng thái của process (1 là active, 0 là inactive)", example = "1")
  private Integer status;
  @Schema(description = "Mô tả của process", example = "Tinh toan tien dien cua Ha Noi")
  private String description;
}
