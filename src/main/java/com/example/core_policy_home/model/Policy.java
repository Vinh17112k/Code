package com.example.core_policy_home.model;

import com.example.core_policy_home.constants.enums.CommonStatusEnum;
import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
 * Bảng chính sách policies
 *
 * @author HoangTD5
 * @version v1
 * @date 11/17/2020
 */
@Entity
@Table(name = "policies")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
    @AuditOverride(forClass = Policy.class),
    @AuditOverride(forClass = BaseIdEntity.class),
    @AuditOverride(forClass = BaseEntity.class),
})
public class Policy extends BaseIdEntity {

  @Basic
  @Column(name = "policy_type", nullable = false)
  private Integer policyType;

  @Basic
  @Column(name = "documentary_id", nullable = true, length = 255)
  private String documentaryId;

  @Basic
  @Column(name = "name", nullable = true, length = 150)
  private String name;

  @Basic
  @Column(name = "description", nullable = true)
  @Lob
  private String description;

  @Basic
  @Column(name = "effective_from", nullable = true)
  private LocalDateTime effectiveFrom;

  @Basic
  @Column(name = "effective_to", nullable = true)
  private LocalDateTime effectiveTo;

  @Basic
  @Column(name = "status", nullable = true)
  private Integer status;

  @Basic
  @Column(name = "code", nullable = false, length = 255)
  private String code;

  @Basic
  @Column(name = "policy_version", nullable = true)
  private Integer policyVersion;

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//  private List<PolicyFeeExpression> feeExpressions = new ArrayList<>();
//
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy",
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//  private List<PolicyMapping> policyMappings = new ArrayList<>();

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    if (this.status == null) {
      this.status = CommonStatusEnum.ENABLED.getValue();
    }
    if (this.policyType == null) {
      this.policyType = PolicyType.FEE.getValue();
    }
    if (this.code == null) {
      this.code = "";
    }
  }

  @Getter
  @AllArgsConstructor
  public enum PolicyType {

    FEE(1, "FEE"),
    ;

    private final int value;
    private final String code;
  }
}
