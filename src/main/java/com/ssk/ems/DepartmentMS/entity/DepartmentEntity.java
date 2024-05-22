package com.ssk.ems.DepartmentMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long deptId;

    @Column(name="deptName")
    @NonNull
    String deptName;

    @Column(name="deptCode")
    @NonNull
    String deptCode;

    @Column(name="deptDescription")
    String deptDescription;
}
