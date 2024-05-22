package com.ssk.ems.DepartmentMS.service;

import com.ssk.ems.DepartmentMS.dto.DepartmentDTO;
import com.ssk.ems.DepartmentMS.entity.DepartmentEntity;
import com.ssk.ems.DepartmentMS.exception.RecordNotFoundException;
import com.ssk.ems.DepartmentMS.repo.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DepartmentServiceTest {
    @Mock
    DepartmentRepository departmentRepo;
    @InjectMocks
    DepartmentService departmentService;

    private DepartmentEntity departmentEntity;
    private DepartmentEntity departmentEntity1;
    private List<DepartmentEntity> departmentEntityList;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    void setUp() {
        departmentDTO = new DepartmentDTO().builder().deptId(1l).deptName("Accounting")
                .deptDescription("Handles Accounts").build();
        departmentEntity = new DepartmentEntity().builder().deptId(1l).deptName("Accounting")
                .deptCode("Acc").deptDescription("Handles Accounts").build();
        departmentEntity1 = new DepartmentEntity().builder().deptId(2l).deptName("Sales")
                .deptCode("sales").deptDescription("Handles Sales").build();
        departmentEntityList = new ArrayList<DepartmentEntity>();
        departmentEntityList.add(departmentEntity);
        departmentEntityList.add(departmentEntity1);
    }

    @AfterEach
    void tearDown()
    {
        departmentDTO = null;
        departmentEntity = null;
        departmentEntityList = null;
    }

//    @Test
//    void getDepartmentByEmpId() throws RecordNotFoundException {
//        Optional<DepartmentDTO> dept =  departmentService.getDepartmentByEmpId(Long.valueOf(1));
//        when(departmentRepo.findByEmpId())
//    }
//
    @Test
    void getDepartmentsByDeptIdTest() throws RecordNotFoundException {
        when(departmentRepo.findById(Long.valueOf(1)))
                .thenReturn(Optional.ofNullable(departmentEntity));
        DepartmentDTO departmentDto = departmentService.getDepartmentsByDeptId(Long.valueOf(1));
        Assertions.assertThat(departmentDto.getDeptId()).isGreaterThan(0);
    }
    @Test
    void getDepartmentsByEmpIdIdTest() throws RecordNotFoundException {
        when(departmentRepo.findById(Long.valueOf(1)))
                .thenReturn(Optional.ofNullable(departmentEntity));
        DepartmentDTO departmentDto = departmentService.getDepartmentByEmpId(Long.valueOf(1));
        Assertions.assertThat(departmentDto.getDeptId()).isGreaterThan(0);
    }
    @Test
    void getDepartmentsTest() throws RecordNotFoundException {
        when(departmentRepo.findAll()).thenReturn(departmentEntityList);
        List<DepartmentDTO> departmentDtoLst = departmentService.getAllDepartments();
        Assertions.assertThat(departmentDtoLst.size()).isGreaterThan(0);
    }
}