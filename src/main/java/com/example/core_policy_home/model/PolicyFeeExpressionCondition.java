package com.example.core_policy_home.model;


import com.example.core_policy_home.config.formatdate.LocalDateTimeDeserializer;
import com.example.core_policy_home.config.formatdate.LocalDateTimeSerializer;
import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Điều kiện tính(pol_fee_expr_conds)
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
@Entity
@Table(name = "pol_fee_expr_conds")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
    @AuditOverride(forClass = PolicyFeeExpressionCondition.class),
    @AuditOverride(forClass = BaseIdEntity.class),
    @AuditOverride(forClass = BaseEntity.class),
})
public class PolicyFeeExpressionCondition extends BaseIdEntity {

  @Basic
  @Column(name = "code", nullable = false, length = 255)
  private String code;

  @Basic
  @Column(name = "sub_code", nullable = true, length = 255)
  private String subCode;

  @Basic
  @Column(name = "value_string", nullable = true, length = 255)
  private String valueString;

  @Basic
  @Column(name = "value_integer", nullable = true)
  private Integer valueInteger;

  @Basic
  @Column(name = "value_decimal", nullable = true)
  private BigDecimal valueDecimal;

  @Basic
  @Column(name = "value_datetime", nullable = true)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime valueDatetime;

  @Basic
  @Column(name = "from_value_string", nullable = true, length = 255)
  private String fromValueString;

  @Basic
  @Column(name = "to_value_string", nullable = true, length = 255)
  private String toValueString;

  @Basic
  @Column(name = "from_value_integer", nullable = true)
  private Integer fromValueInteger;

  @Basic
  @Column(name = "to_value_integer", nullable = true)
  private Integer toValueInteger;

  @Basic
  @Column(name = "from_value_decimal", nullable = true)
  private BigDecimal fromValueDecimal;

  @Basic
  @Column(name = "to_value_decimal", nullable = true)
  private BigDecimal toValueDecimal;

  @Basic
  @Column(name = "from_value_datetime", nullable = true)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime fromValueDatetime;

  @Basic
  @Column(name = "to_value_datetime", nullable = true)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime toValueDatetime;

  @Basic
  @Column(name = "value_boolean", nullable = true)
  private Boolean valueBoolean;

  @Basic
  @Column(name = "value_text", nullable = true)
  @Lob
  private String valueText;

  @Basic
  @Column(name = "priority", nullable = true)
  private Integer priority;

//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "expression_rule_id", referencedColumnName = "id", nullable = false)
//  private PolicyFeeExpressionRule rule;

  @Column(name = "expression_rule_id")
  private Long expressionRuleId;
  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    if (this.priority == null) {
      this.priority = 1;
    }
  }

}
