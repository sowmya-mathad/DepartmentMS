package com.ssk.ems.DepartmentMS.service;

import com.ssk.ems.DepartmentMS.dto.DepartmentDTO;
import com.ssk.ems.DepartmentMS.entity.DepartmentEntity;
import com.ssk.ems.DepartmentMS.exception.RecordNotFoundException;
import com.ssk.ems.DepartmentMS.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentDTO getDepartmentByEmpId(Long empId) throws RecordNotFoundException {
        DepartmentDTO departmentDTO = null;
//        Optional<DepartmentEntity> optionalEntity= departmentRepo.findById(empId);
        Optional<DepartmentEntity> optionalEntity = Optional.of(GetDepartmentEntityByEmpId());
        if (optionalEntity.isPresent()) {
            return mapEntityToDTO(optionalEntity.get());
        } else {
            throw new RecordNotFoundException("Employee is not associated to amy department");
        }
    }

    public DepartmentDTO getDepartmentsByDeptId(Long deptId) throws RecordNotFoundException {
        DepartmentDTO departmentDTO = null;
        Optional<DepartmentEntity> optionalEntity = departmentRepository.findById(deptId);
//        Optional<DepartmentEntity> optionalEntity= Optional.of(GetDepartmentEntityByEmpId());
        if (optionalEntity.isPresent()) {
            return mapEntityToDTO(optionalEntity.get());
        } else {
            throw new RecordNotFoundException("Department is not associated to amy department");
        }
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
        return departmentEntityList.stream().map(this::mapEntityToDTO).toList();
//         List<DepartmentEntity> optionalEntity= buildDepartmentDTO();
//        return optionalEntity.stream().map(dept -> mapEntityToDTO(dept)).toList();
    }

    private DepartmentDTO mapEntityToDTO(DepartmentEntity departmentEntity) {
        return new DepartmentDTO(departmentEntity.getDeptId(), departmentEntity.getDeptName(),
                departmentEntity.getDeptCode(), departmentEntity.getDeptDescription());
    }

    public void deleteDepartmentById(Long id) throws RecordNotFoundException {
        Optional<DepartmentEntity> employee = departmentRepository.findById(id);

        if (employee.isPresent()) {
            departmentRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Department record exist for given id");
        }
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        if (departmentEntity.isPresent()) {
            DepartmentEntity entity = mapDtoToModel(departmentDTO);
            entity = departmentRepository.save(entity);
            return mapEntityToDTO(entity);
        } else {
            throw new InternalError("Error in creating record");
        }
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO)
            throws RecordNotFoundException {
        DepartmentEntity departmentEntityentity = mapDtoToModel(departmentDTO);
        departmentEntityentity = departmentRepository.save(departmentEntityentity);

        return mapEntityToDTO(departmentEntityentity);
    }

    private DepartmentEntity mapDtoToModel(DepartmentDTO departmentDTO) {
        return new DepartmentEntity(departmentDTO.getDeptId(), departmentDTO.getDeptName(), departmentDTO.getDeptCode(),
                departmentDTO.getDeptDescription());
    }

    private DepartmentEntity mapDTOToEntity(DepartmentDTO departmentDTO) {
        return new DepartmentEntity(departmentDTO.getDeptId(), departmentDTO.getDeptName(),
                departmentDTO.getDeptCode(), departmentDTO.getDeptDescription());
    }

    private List<DepartmentEntity> buildDepartmentDTO() {
        List<DepartmentEntity> lst = new ArrayList<DepartmentEntity>();

        DepartmentEntity dt = new DepartmentEntity(1L, "Accounts", "Acc", "Account Related Activities");
        DepartmentEntity dt1 = new DepartmentEntity(2L, "Human Resource", "HR", "Human Resource Activities");
        DepartmentEntity dt2 = new DepartmentEntity(3L, "Sales", "Sale", " Sales Activities");
        lst.add(dt);
        lst.add(dt1);
        lst.add(dt2);

        return lst;
    }

    private DepartmentEntity GetDepartmentEntityByEmpId() {
        DepartmentEntity dt = new DepartmentEntity(1L, "Accounts", "Acc", "Account Related Activities");
        return dt;
    }
}
