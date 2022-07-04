package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

/**
 * services
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
//@Entity
//@Table(name = "sys_params")
//@Where(clause = "is_deleted = false")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@SuperBuilder
//public class SysParam extends BaseIdEntity {
//
//  @Basic
//  @Column(name = "code", nullable = false, length = 255)
//  private String code;
//
//  @Basic
//  @Column(name = "name", nullable = true, length = 255)
//  private String name;
//
//  @Basic
//  @Column(name = "description", nullable = true)
//  @Lob
//  private String description;
//
//  @Basic
//  @Column(name = "status", nullable = true)
//  private Integer status;
//
//  @Basic
//  @Column(name = "value_string", nullable = true, length = 255)
//  private String valueString;
//
//  @Basic
//  @Column(name = "value_integer", nullable = true)
//  private Integer valueInteger;
//
//  @Basic
//  @Column(name = "value_decimal", nullable = true)
//  private BigDecimal valueDecimal;
//
//  @Basic
//  @Column(name = "value_datetime", nullable = true)
//  private LocalDateTime valueDatetime;
//
//  @Basic
//  @Column(name = "value_boolean", nullable = true)
//  private Boolean valueBoolean;
//
//  @Basic
//  @Column(name = "is_system", nullable = true)
//  private Boolean isSystem;
//
//  @Basic
//  @Column(name = "weight", nullable = true)
//  private Integer weight;
//
//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
//  private SysParamGroup group;
//
//  @Override
//  @PrePersist
//  public void prePersist() {
//    super.prePersist();
//    if (this.status == null) {
//      this.status = CommonStatusEnum.ENABLED.getValue();
//    }
//  }
//}
