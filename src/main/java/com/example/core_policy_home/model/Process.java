package com.example.core_policy_home.model;

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

/**
 * @author vinhtq
 * entity Process v1
 */
@Entity
@Table(name = "process")
@Where(clause = "is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Process extends BaseIdEntity {
    @Column(name = "name",nullable = false, length = 255)
    private String name;
    @Column(name = "code",nullable = false, length = 255)
    private String processCode;
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Column(name="status", nullable = true)
    private Integer status;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "process",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    private List<MappingProcessService> mappingProcessServices = new ArrayList<>();
}
