package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * Chi tiết cách tính (pol_fee_expr_rules)
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
@Entity
@Table(name = "pol_fee_expr_rules")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
    @AuditOverride(forClass = PolicyFeeExpressionRule.class),
    @AuditOverride(forClass = BaseIdEntity.class),
    @AuditOverride(forClass = BaseEntity.class),
})
public class PolicyFeeExpressionRule extends BaseIdEntity {

  @Basic
  @Column(name = "priority", nullable = false)
  private Integer priority;

  @Basic
  @Column(name = "calculation_method", nullable = false)
  private Integer calculationMethod;

  @Basic
  @Column(name = "amount", nullable = true)
  private BigDecimal amount;

  @Basic
  @Column(name = "percent", nullable = true)
  private BigDecimal percent;

  @Basic
  @Column(name = "min_amount", nullable = true)
  private BigDecimal minAmount;

  @Basic
  @Column(name = "max_amount", nullable = true)
  private BigDecimal maxAmount;

  @Basic
  @Column(name = "block_volume", nullable = true)
  private BigDecimal blockVolume;

  @Basic
  @Column(name = "block_amount", nullable = true)
  private BigDecimal blockAmount;

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "rule",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//  private List<PolicyFeeExpressionCondition> conditions = new ArrayList<>();
//
//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "expression_detail_id", referencedColumnName = "id", nullable = false)
//  private PolicyFeeExpressionDetail detail;

  @Basic
  @Column(name = "expression_detail_id", nullable = true)
  private BigDecimal expressionDetailId;

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    if (this.priority == null) {
      this.priority = 1;
    }
  }

  @Getter
  @AllArgsConstructor
  public enum CalculationMethod {

    FREE(1, "FREE"),
    AS_PERCENTAGE(2, "AS_PERCENTAGE"),
    AS_BLOCK(3, "AS_BLOCK"),
    ;

    private final int value;
    private final String code;
  }
}
