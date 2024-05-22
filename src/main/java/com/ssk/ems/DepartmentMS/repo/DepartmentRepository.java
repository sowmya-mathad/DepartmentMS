package com.ssk.ems.DepartmentMS.repo;

import com.ssk.ems.DepartmentMS.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
  }
