package com.example.core_policy_home.model;

import com.example.core_policy_home.model.base.BaseIdEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "services")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ServiceUpgrade extends BaseIdEntity {
    @Basic
    @Column(name = "code", nullable = true, length = 255)
    private String serviceCode;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "description", nullable = true)
    @Lob
    private String description;

    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
}
