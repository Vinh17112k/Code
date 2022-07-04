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
import javax.persistence.Lob;
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

/**
 * Công thức(pol_fee_exprs)
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
@Entity
@Table(name = "pol_fee_exprs")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyFeeExpression extends BaseIdEntity {

  @Basic
  @Column(name = "type", nullable = false)
  private Integer type; // 1: template, 2: cụ thể.

  @Basic
  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Basic
  @Column(name = "description", nullable = true)
  @Lob
  private String description;

  @Basic
  @Column(name = "rounding_type", nullable = true)
  private Integer roundingType;

  @Basic
  @Column(name = "rounding_value", nullable = true)
  private BigDecimal roundingValue;

  @Basic
  @Column(name = "include_surcharge", nullable = true)
  private Boolean includeSurcharge;

  @Basic
  @Column(name = "min_amount", nullable = true)
  private BigDecimal minAmount;

  @Basic
  @Column(name = "max_amount", nullable = true)
  private BigDecimal maxAmount;

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "feeExpression",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//  private List<PolicyFeeExpressionDetail> details = new ArrayList<>();

//  @NotAudited
//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "policy_id", referencedColumnName = "id", nullable = false)
//  private Policy policy;
//
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false)
//  private PolicyFeeExpression template;
//
//  @JsonIgnore
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "template")
//  private List<PolicyFeeExpression> concretes = new ArrayList<>();

  @Column(name = "policy_id")
  private Long policyId;
}