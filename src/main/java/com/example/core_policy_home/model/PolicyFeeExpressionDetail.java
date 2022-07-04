package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Chi tiết công thức(pol_fee_expr_details)
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
@Entity
@Table(name = "pol_fee_expr_details")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
    @AuditOverride(forClass = PolicyFeeExpressionDetail.class),
    @AuditOverride(forClass = BaseIdEntity.class),
    @AuditOverride(forClass = BaseEntity.class),
})
public class PolicyFeeExpressionDetail extends BaseIdEntity {

  @Basic
  @Column(name = "priority", nullable = true)
  private Integer priority;

  @Basic
  @Column(name = "calculation_scope", nullable = true)
  private Integer calculationScope;
  @Basic
  @Column(name = "expression_id", nullable = true)
  private Long expressionId;
//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "expression_id", referencedColumnName = "id",
//      nullable = false, insertable = true, updatable = false)
//  private PolicyFeeExpression feeExpression;
//
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "detail",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//  private List<PolicyFeeExpressionRule> rules = new ArrayList<>();

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    if (this.priority == null) {
      this.priority = 1;
    }
    if (this.calculationScope == null) {
      this.calculationScope = CalculationScope.WHOLE_TRANSACTION.getValue();
    }
  }

  @Getter
  @AllArgsConstructor
  public enum CalculationScope {

    WHOLE_TRANSACTION(1, "WHOLE_TRANSACTION"),
    PORTION_WITHIN(2, "PORTION_WITHIN"),
    ;

    private final int value;
    private final String code;
  }
}
