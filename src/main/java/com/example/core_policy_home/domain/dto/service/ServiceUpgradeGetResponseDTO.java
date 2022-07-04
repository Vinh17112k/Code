package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
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
public class ServiceUpgradeGetResponseDTO extends BaseDTO {
    private long id;
    private String name;
    private String serviceCode;
    private String description;
    private Integer status;
    private List<ProcessResponseNameAndId> processCode;
}
