package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseEntity;
import com.example.core_policy_home.model.base.BaseIdEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "mapping_process_service")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SuppressWarnings({"java:S1710"})
@Audited
@AuditOverrides({
        @AuditOverride(forClass = MappingProcessService.class),
        @AuditOverride(forClass = BaseIdEntity.class),
        @AuditOverride(forClass = BaseEntity.class),
})
public class MappingProcessService extends BaseIdEntity {
  @Column(name = "process_id")
  private Long processId;
  @Column(name = "service_id")
  private Long serviceId;
//  @JsonIgnore
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "process_id", referencedColumnName = "id", nullable = false)
//  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  private Process process;
//
//  @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = true)
//  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  private Services service;
}
