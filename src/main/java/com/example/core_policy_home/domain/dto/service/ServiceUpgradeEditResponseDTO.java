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
public class ServiceUpgradeEditResponseDTO extends BaseDTO {

  @Schema(description = "Id của service", example = "11")
  private long id;
  @Schema(description = "Tên của dịch vụ", example = "Tien dien")
  private String name;
  @Schema(description = "ServiceCode của dịch vụ", example = "HN002")
  private String serviceCode;
  @Schema(description = "Mô tả của dịch vụ", example = "Tinh toan tien dien cua Ha Noi")
  private String description;
  @Schema(description = "Trạng thái của dịch vụ (1 la active, 0 la inactive)", example = "1")
  private Integer status;
  @Schema(description = "Thời điểm tạo mới service", example = "11/11/2020")
  private LocalDateTime createdAt;
  @Schema(description = "Người tạo service", example = "thangnd")
  private String createdBy;
}
