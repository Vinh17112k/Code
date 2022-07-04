package com.example.core_policy_home.domain.dto.service;

import com.example.core_policy_home.domain.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * Thông tin dịch vụ
 *
 * @author : HoangTD
 * @since : 11/25/2020, Wed
 **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Thông tin dịch vụ")
@Validated
public class ServiceDTO extends BaseDTO {

  private Long id;
  private String code;
  private String processCode;
  private String serviceCode;
  private String name;
  private String description;
  private Integer status;
  private Integer level;
  private List<ServiceDTO> children;

  private Long parentId;
}
