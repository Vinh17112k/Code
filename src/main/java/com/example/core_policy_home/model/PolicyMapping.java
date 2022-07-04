package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

@Entity
@Table(name = "policy_mappings")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
    @AuditOverride(forClass = PolicyMapping.class),
    @AuditOverride(forClass = BaseIdEntity.class),
    @AuditOverride(forClass = BaseEntity.class),
})
public class PolicyMapping extends BaseIdEntity {

  @Basic
  @Column(name = "mapping_type", nullable = false)
  private Integer mappingType;

  @Basic
  @Column(name = "system_code", nullable = true, length = 255)
  private String systemCode;

//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "policy_id", referencedColumnName = "id", nullable = false)
//  private Policy policy;
//
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = true)
//  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  private Services service;
//
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "customer_group_id", referencedColumnName = "id", nullable = true)
//  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  private CustomerGroup customerGroup;
  @Column(name="process_id")
  private Long processId;
  @Column(name="service_id")
  private Long serviceId;
  @Column(name="policy_id")
  private Long policyId;
  @Column(name="customer_group_id")
  private Long customerGroupId;

  @Getter
  @AllArgsConstructor
  public enum PolicyMappingType {

    SERVICE(1, "SERVICE"),
    SYSTEM_CODE(2, "SYSTEM_CODE"),
    CUSTOMER_GROUP(3, "CUSTOMER_GROUP"),
    ;

    private final int value;
    private final String code;

  }
}
