package com.example.core_policy_home.domain.dto.process;

import com.example.core_policy_home.domain.BaseDTO;
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
public class ProcessResponseNameAndId extends BaseDTO {

    private Long id;
    private String name;
}
