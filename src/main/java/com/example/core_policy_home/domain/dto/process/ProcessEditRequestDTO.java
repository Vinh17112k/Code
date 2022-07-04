package com.example.core_policy_home.domain.dto.process;

import com.example.core_policy_home.domain.BaseDTO;
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
public class ProcessEditRequestDTO extends BaseDTO {
//  @NotBlank
  @NotNull
  private Long id;
  @NotBlank
  @NotNull
  private String name;
  @NotBlank
  @NotNull
  private String processCode;
  private Integer status;
  private String description;
}
