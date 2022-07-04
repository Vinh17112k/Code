package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import com.example.core_policy_home.domain.dto.process.ProcessResponseNameAndId;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ServiceUpgradeGetResponseDTO extends BaseDTO {
    @Schema(description = "Id của service", example = "11")
    private long id;
    @Schema(description = "Tên của dịch vụ", example = "Tien dien")
    private String name;
    @Schema(description = "ServiceCode", example = "HN002")
    private String serviceCode;
    @Schema(description = "Mô tả của dịch vụ", example = "Tinh toan tien dien cua Ha Noi")
    private String description;
    @Schema(description = "Trạng thái của dịch vụ (1 là active, 0 là inactive)", example = "1")
    private Integer status;
    private List<ProcessResponseNameAndId> processCode;
}
