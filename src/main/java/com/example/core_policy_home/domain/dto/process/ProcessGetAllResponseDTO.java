package com.example.core_policy_home.domain.dto.process;

import com.example.core_policy_home.domain.BaseDTO;
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
public class ProcessGetAllResponseDTO extends BaseDTO implements Serializable {
  private List<String> name;
  private List<String> processCode;
}
