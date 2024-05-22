package com.ssk.ems.DepartmentMS.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartmentDTO {
    Long deptId;
    String deptName;
    String deptCode;
    String deptDescription;

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", deptDescription='" + deptDescription + '\'' +
                '}';
    }
}
